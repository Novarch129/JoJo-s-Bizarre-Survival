package io.github.novarch129.jojomod.entity.stands;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stands.attacks.AbstractStandAttackEntity;
import io.github.novarch129.jojomod.event.custom.StandEvent;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unused", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class AbstractStandEntity extends MobEntity implements IEntityAdditionalSpawnData {
    private static final DataParameter<Optional<UUID>> MASTER_UNIQUE_ID = EntityDataManager.createKey(AbstractStandEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public boolean ability, attackRush, standOn;
    public int attackTick, attackTicker;
    protected SoundEvent spawnSound;
    private PlayerEntity master;

    public AbstractStandEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * @return Returns a the Stand's {@link AbstractStandEntity#spawnSound}.
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
     * @return The Stand's current {@link AbstractStandEntity#master}.
     */
    public PlayerEntity getMaster() {
        return master;
    }

    /**
     * Sets the Stand's master, should never be <code>null</code> as it would most likely <b>crash</b> the game.
     *
     * @param master The {@link PlayerEntity} that will be set as the Stand's {@link AbstractStandEntity#master}.
     */
    public void setMaster(@Nonnull PlayerEntity master) {
        this.master = master;
    }

    /**
     * @return The {@link UUID} of the Stand's master from it's {@link EntityDataManager}.
     */
    public UUID getMasterUUID() {
        return dataManager.get(MASTER_UNIQUE_ID).orElse(null);
    }

    /**
     * Sets the Stand's master's {@link UUID} to the {@link EntityDataManager}.
     *
     * @param masterUUID The {@link UUID} that will be set as the Stand's {@link AbstractStandEntity#MASTER_UNIQUE_ID}
     */
    public void setMasterUUID(@Nullable UUID masterUUID) {
        dataManager.set(MASTER_UNIQUE_ID, Optional.ofNullable(masterUUID));
    }

    /**
     * Plays the Stand's {@link AbstractStandEntity#spawnSound}.
     */
    public void playSpawnSound() {
        world.playSound(null, getMaster().getPosition(), spawnSound, SoundCategory.NEUTRAL, 1, 1);
    }

    /**
     * Makes the Stand follow it's {@link AbstractStandEntity#master}, follow speed is based on distance from it's master.
     */
    protected void followMaster() {
        double distance = master.getDistance(this);
        double minimum = 0.5; //The Stand's minimum and maximum distance from it's master.
        double maximum = 3;

        if (distance < minimum)
            moveStand(distance);
        else if (distance > minimum)
            if (distance < maximum && distance > minimum + 0.3)
                moveStand(distance);
            else if (distance > maximum && !world.isRemote)
                setPosition(master.getPosX(), master.getPosY(), master.getPosZ());
    }

    /**
     * Used in followMaster() to shorten code, moves the Stand based on the,
     *
     * @param distance The Stand's distance from it's master.
     */
    public void moveStand(double distance) {
        double distanceX = getPosX() - master.getPosX();
        double distanceY = getPosY() - master.getPosY();
        double distanceZ = getPosZ() - master.getPosZ();
        float speed = (float) (distance / 45); //The speed at which the Stand should move towards it's master

        if (distance < 0.5)
            speed = -0.1f;
        if (distanceX > 0)
            moveForward -= speed;
        if (distanceX < 0)
            moveForward += speed;
        if (distanceY > 0)
            moveVertical -= speed;
        if (distanceY < 0)
            moveVertical += speed;
        if (distanceZ > 0)
            moveStrafing -= speed;
        if (distanceZ < 0)
            moveStrafing += speed;
    }

    /**
     * Makes the Stand dodge oncoming attacks, such as TNT, arrows and falling blocks.
     */
    private void dodgeAttacks() {
        if (!world.isRemote) {
            world.getServer().getWorld(dimension).getEntities().forEach(entity -> {
                double distance = entity.getDistance(getMaster());

                if ((entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity || entity instanceof ProjectileItemEntity) && distance <= Math.PI * 8) {
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
                dataManager.get(MASTER_UNIQUE_ID).ifPresent(uuid -> setMaster(world.getPlayerByUuid(uuid)));
                return; //Code will continue executing and may crash if this is removed.
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
        if (master == null || damageSource.getTrueSource() == master || damageSource == DamageSource.CACTUS || damageSource == DamageSource.FALL)
            return false; //Prevents Stands from taking damage they shouldn't, fall damage, cactus damage, etc.
        master.attackEntityFrom(damageSource, damage * 0.5f);
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
     * Posts the {@link StandEvent.StandSummonedEvent} and sends a {@link SSyncStandMasterPacket} because I'm paranoid.
     */
    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (MinecraftForge.EVENT_BUS.post(new StandEvent.StandSummonedEvent(getMaster(), this)))
            remove(); //Removes the Stand if the Stand summon event is cancelled.
        if (!world.isRemote && getMaster() != null)
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncStandMasterPacket(getEntityId(), getMaster().getEntityId()));
    }

    /**
     * Fires the {@link StandEvent.StandRemovedEvent}, will cause major issues if super isn't called.
     */
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(getMaster(), this));
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
        int entityID = additionalData.readInt();
        Entity entity = world.getEntityByID(entityID);
        if (entity instanceof PlayerEntity)
            setMaster((PlayerEntity) entity);
    }

    /**
     * Registers the Stand's master's UUID to the {@link EntityDataManager}.
     */
    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(MASTER_UNIQUE_ID, Optional.empty());
    }

    /**
     * Writes the Stand's master's UUID to the {@link CompoundNBT} for future use.
     */
    @Override
    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        if (getMasterUUID() != null)
            compoundNBT.putString("MasterUUID", getMasterUUID().toString());
    }

    /**
     * Sets the Stand's master to the one written to the {@link CompoundNBT}.
     */
    @Override
    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        String s;
        if (compoundNBT.contains("MasterUUID", 8))
            s = compoundNBT.getString("MasterUUID");
        else {
            String s1 = compoundNBT.getString("MasterUUID");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(getServer(), s1);
        }
        if (!s.isEmpty())
            setMasterUUID(UUID.fromString(s));
    }

    /**
     * Very important for custom entities, if not implemented the game will crash with a {@link NullPointerException}.
     */
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
