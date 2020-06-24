package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.events.custom.StandEvent;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

@SuppressWarnings({"unused", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class EntityStandBase extends MobEntity implements IEntityAdditionalSpawnData {
    private PlayerEntity master;
    protected boolean standOn = true;
    private int act;
    public boolean orarush;
    protected SoundEvent spawnSound;
    public int longTick = 2;
    public int standID;
    boolean jumpCheck;
    boolean attack;
    public UUID masterUUID;
    public boolean ability;

    public EntityStandBase(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * @return  Returns a {@link SoundEvent}, the Stand's spawn sound.
     */
    public SoundEvent getSpawnSound() {
        return this.spawnSound;
    }

    /**
     * Sets the Stand's master, should never be <code>null</code> as it would most likely crash the game.
     *
     * @param master  The {@link PlayerEntity} that will be set as the Stand's master.
     */
    public void setMaster(@Nonnull PlayerEntity master) {
        this.master = master;
    }

    public int getStandID() {
        return this.standID;
    }

    /**
     * Used to check if the Stand's master has swung their hand, used to summon {@link EntityStandPunch}.
     *
     * @param player    The player who's swing is checked.
     * @return  Returns whether or not the swing was successful.
     */
    public boolean attackSwing(PlayerEntity player) {
        return (player.isSwingInProgress && getAttack()) && !setAttack(false);
    }

    public boolean setAttack(boolean attack) {
        return this.attack = attack;
    }

    public boolean getAttack() {
        return this.attack;
    }

    public PlayerEntity getMaster() {
        return master;
    }

    public void setMasterUUID(UUID masterUUID) {
        this.masterUUID = masterUUID;
    }

    public UUID getMasterUUID() {
        return masterUUID;
    }

    /**
     *  Plays the Stand's spawn sound.
     */
    public void playSpawnSound() {
        world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), getSpawnSound(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
    }

    /**
     *  Makes the Stand follow it's master, follow speed is based on distance from it's master.
     */
    protected void followMaster() {
        PlayerEntity player = getMaster();
        double distance = player.getDistance(this);
        double minimum = 0.5;
        double maximum = 3.0;

        if (distance < minimum)
            moveStand(distance, player);

        else if (distance > minimum) {
            if (distance < maximum && distance > minimum + 0.3)
                moveStand(distance, player);

            else if (distance > maximum && !world.isRemote)
                setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
        }
    }

    /**
     *  Used in followMaster() to shorten code, moves the Stand based on the,
     *
     *  @param distance The Stand's distance from it's master.
     *  @param player   The Stand's master, used for some distance calculations.
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
     * @return  Returns whether the Stand has acts, used to determine if it should be removed from the {@link World}.
     */
    public boolean hasAct() {
        return false;
    }

    /**
     * Makes the Stand dodge oncoming attacks, such as TNT, arrows and falling blocks.
     */
    private void dodgeAttacks() {
        if (this.world != null) {
            if (!world.isRemote)
                world.getServer().getWorld(dimension).getEntities().forEach(entity1 -> {
                    Entity entity;
                    Entity playerEntity = null;

                    if (entity1 != null)
                        playerEntity = entity1;

                    final double distance = playerEntity.getDistance(getMaster());
                    final double distance2 = Math.PI * 2 * 2 * 2;

                    entity = playerEntity;

                    if (!world.isRemote && (entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity) && distance <= distance2) {
                        final double distanceX = getPosX() - entity.getPosX();
                        final double distanceY = getPosY() - entity.getPosY();
                        final double distanceZ = getPosZ() - entity.getPosZ();

                        if (distanceX > 0.0)
                            moveForward += -0.3;
                        if (distanceX < 0.0)
                            moveForward += 0.3;
                        if (distanceY > 0.0)
                            moveVertical += -0.3;
                        if (distanceY < 0.0)
                            moveVertical += 0.3;
                        if (distanceZ > 0.0)
                            moveStrafing += -0.3;
                        if (distanceZ < 0.0)
                            moveStrafing += 0.3;
                    }
                });
        }
    }

    @Override
    public void tick() {
        super.tick();
        fallDistance = 0.0f;
        if (!world.isRemote) {
            if (getMaster() == null) {
                remove();
                return;
            }
            if (!getMaster().isAlive()) {
                MinecraftForge.EVENT_BUS.post(new StandEvent.MasterDeathEvent(getMaster(), this));
                remove();
            }
            if (getMaster().isSpectator()) remove();
            MinecraftForge.EVENT_BUS.post(new StandEvent.StandTickEvent(getMaster(), this));

            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                standOn = props.getStandOn();
                if (!props.getStandOn()) {
                    MinecraftForge.EVENT_BUS.post(new StandEvent.StandUnsummonedEvent(getMaster(), this));
                    remove();
                }
            });
            dodgeAttacks();
            if (getAir() < 20)
                setAir(60);
        }
    }

    /**
     * Redirects attacks from the Stand to it's master.
     *
     * @param damageSource  The {@link DamageSource} damaging the Stand.
     * @param damage    The amount of damage taken.
     * @return  Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (!standOn)
            return false;
        if (damageSource.getTrueSource() == getMaster())
            return false;
        if (damageSource == DamageSource.CACTUS)
            return false;
        getMaster().attackEntityFrom(damageSource, damage * 0.5f);
        return false;
    }

    /**
     * Makes Stand's phase through non Stand entities.
     *
     * @param entityIn  The {@link Entity} being collided with.
     */
    @Override
    public void applyEntityCollision(Entity entityIn) {
        if(entityIn instanceof EntityStandBase || entityIn instanceof EntityStandPunch)
            super.applyEntityCollision(entityIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(MinecraftForge.EVENT_BUS.post(new StandEvent.StandSummonedEvent(getMaster(), this))) remove();
//        if(!world.isRemote)
//            if(getMaster() != null)
//                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncStandMasterPacket(getEntityId(), getMaster().getEntityId()));
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(getMaster(), this));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putUniqueId("MasterUUID", getMasterUUID());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setMasterUUID(compound.getUniqueId("MasterUUID"));
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    /**
     * Makes the Stand not render flames when it's on fire as it looks stupid.
     *
     * @return  Whether the entity should render as on fire.
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

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if(getMaster() != null)
            buffer.writeInt(getMaster().getEntityId());
        else
            LogManager.getLogger().debug("write null master");
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        int id = additionalData.readInt();
        if(id != 0)
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
