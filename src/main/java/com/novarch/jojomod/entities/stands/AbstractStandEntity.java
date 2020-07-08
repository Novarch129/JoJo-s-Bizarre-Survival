package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.AbstractStandAttackEntity;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.network.message.server.SSyncStandMasterPacket;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings({"unused", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class AbstractStandEntity extends MobEntity implements IEntityAdditionalSpawnData {
    public boolean ability, attackRush, standOn;
    public int attackTick, attackTicker;
    protected SoundEvent spawnSound;
    private PlayerEntity master;

    public AbstractStandEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * @return Returns a {@link SoundEvent}, the Stand's spawn sound.
     */
    public SoundEvent getSpawnSound() {
        return spawnSound;
    }

    /**
     * Initiates the Stand's default attack, usually a punch ({@link AbstractStandAttackEntity}).
     *
     * @param special Whether the attack is a special attack or not, usually if it's a rush attack.
     */
    public abstract void attack(boolean special);

    /**
     * @return The Stand's current master.
     */
    public PlayerEntity getMaster() {
        return master;
    }

    /**
     * Sets the Stand's master, should never be <code>null</code> as it would most likely crash the game.
     *
     * @param master The {@link PlayerEntity} that will be set as the Stand's master.
     */
    public void setMaster(@Nonnull PlayerEntity master) {
        this.master = master;
    }

    /**
     * Plays the Stand's spawn sound.
     */
    public void playSpawnSound() {
        world.playSound(null, getMaster().getPosition(), spawnSound, SoundCategory.NEUTRAL, 1, 1);
    }

    /**
     * Makes the Stand follow it's master, follow speed is based on distance from it's master.
     */
    protected void followMaster() {
        double distance = master.getDistance(this);
        final double minimum = 0.5;
        final double maximum = 3;

        if (distance < minimum)
            moveStand(distance, master);

        else if (distance > minimum) {
            if (distance < maximum && distance > minimum + 0.3)
                moveStand(distance, master);

            else if (distance > maximum && !world.isRemote)
                setPosition(master.getPosX(), master.getPosY(), master.getPosZ());
        }
    }

    /**
     * Used in followMaster() to shorten code, moves the Stand based on the,
     *
     * @param distance The Stand's distance from it's master.
     * @param player   The Stand's master, used for some distance calculations.
     */
    public void moveStand(double distance, PlayerEntity player) {
        double distanceX = getPosX() - player.getPosX();
        double distanceY = getPosY() - player.getPosY();
        double distanceZ = getPosZ() - player.getPosZ();
        float speed = (float) distance / 45.0f;

        if (distance < 0.5)
            speed = -0.1f;
        if (distanceX > 0.0)
            moveForward += -speed;
        if (distanceX < 0.0)
            moveForward += speed;
        if (distanceY > 0.0)
            moveVertical += -speed;
        if (distanceY < 0.0)
            moveVertical += speed;
        if (distanceZ > 0.0)
            moveStrafing += -speed;
        if (distanceZ < 0.0)
            moveStrafing += speed;
    }

    /**
     * Makes the Stand dodge oncoming attacks, such as TNT, arrows and falling blocks.
     */
    private void dodgeAttacks() {
        if (!world.isRemote) {
            world.getServer().getWorld(dimension).getEntities().forEach(entity -> {

                double distance = entity.getDistance(getMaster());
                double distance2 = Math.PI * 2 * 2 * 2;

                if (!world.isRemote && (entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity) && distance <= distance2) {
                    double distanceX = getPosX() - entity.getPosX();
                    double distanceY = getPosY() - entity.getPosY();
                    double distanceZ = getPosZ() - entity.getPosZ();

                    if (distanceX > 0)
                        moveForward -= 0.3;
                    if (distanceX < 0)
                        moveForward += 0.3;
                    if (distanceY > 0)
                        moveVertical -= 0.3;
                    if (distanceY < 0)
                        moveVertical += 0.3;
                    if (distanceZ > 0)
                        moveStrafing -= 0.3;
                    if (distanceZ < 0)
                        moveStrafing += 0.3;
                }
            });
        }
    }

    /**
     * Called every tick to update the entity's position/logic and remove it if conditions are met.
     */
    @Override
    public void tick() {
        super.tick(); //Queues the tick method to run, code in tick() method won't run if removed.
        if (!world.isRemote) {
            if (getMaster() == null) { //Don't listen to your IDE, this can be null after a relog.
                remove();
                return; //Code will continue executing and crash if this is removed.
            }
            if (!getMaster().isAlive()) {
                MinecraftForge.EVENT_BUS.post(new StandEvent.MasterDeathEvent(getMaster(), this));
                remove();
            }
            if (getMaster().isSpectator()) remove();
            MinecraftForge.EVENT_BUS.post(new StandEvent.StandTickEvent(getMaster(), this)); //Fired after all death checks are passed to avoid confusion.

            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                standOn = props.getStandOn(); //If set to false, Stand will die next tick.
                if (!props.getStandOn()) {
                    MinecraftForge.EVENT_BUS.post(new StandEvent.StandUnsummonedEvent(getMaster(), this));
                    remove();
                }
            });
            dodgeAttacks();
        }
    }

    /**
     * Redirects attacks from the Stand to it's master.
     *
     * @param damageSource The {@link DamageSource} damaging the Stand.
     * @param damage       The amount of damage taken.
     * @return Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (!standOn || damageSource.getTrueSource() == getMaster() || damageSource == DamageSource.CACTUS || damageSource == DamageSource.FALL)
            return false; //Prevents Stands from taking damage they shouldn't, fall damage, cactus damage, etc.
        getMaster().attackEntityFrom(damageSource, damage * 0.5f);
        return false;
    }

    /**
     * Makes Stand's phase through non Stand entities.
     *
     * @param entityIn The {@link Entity} being collided with.
     */
    @Override
    public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof AbstractStandEntity || entityIn instanceof AbstractStandAttackEntity)
            super.applyEntityCollision(entityIn);
    }

    /**
     * Posts the {@link com.novarch.jojomod.events.custom.StandEvent.StandSummonedEvent} and sends a {@link SSyncStandMasterPacket} because I'm paranoid.
     */
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (MinecraftForge.EVENT_BUS.post(new StandEvent.StandSummonedEvent(getMaster(), this)))
            remove(); //Removes the Stand if the Stand summon event is cancelled.
        if (!world.isRemote)
            if (getMaster() != null)
                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncStandMasterPacket(getEntityId(), getMaster().getEntityId()));
    }

    /**
     * Fires the {@link com.novarch.jojomod.events.custom.StandEvent.StandRemovedEvent}, will cause major issues if super isn't called.
     */
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(getMaster(), this));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("MasterID", getMaster().getEntityId());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        setMaster((PlayerEntity) world.getEntityByID(compound.getInt("MasterID")));
    }

    /**
     * Prevents the Stand from despawning due to being far from players.
     *
     * @param distanceToClosestPlayer The Stand's distance to the nearest player.
     * @return Whether or not the Stand should despawn.
     */
    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    /**
     * Makes the Stand not render flames when it's on fire as it looks stupid.
     *
     * @return Whether the entity should render as on fire.
     */
    @Override
    public boolean canRenderOnFire() {
        return false;
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    /**
     * Writes data from the server to a {@link PacketBuffer}.
     *
     * @param buffer The {@link PacketBuffer} to write to.
     */
    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if (getMaster() != null)
            buffer.writeInt(getMaster().getEntityId());
    }

    /**
     * Reads the data written to the {@link PacketBuffer} by the server from the client, syncing that data to the client.
     *
     * @param additionalData The {@link PacketBuffer} to read from.
     */
    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        int id = additionalData.readInt();
        if (id != 0)
            setMaster((PlayerEntity) world.getEntityByID(id));
    }

    /**
     * Very important for custom entities, if not implemented the game will crash with a {@link NullPointerException}.
     */
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
