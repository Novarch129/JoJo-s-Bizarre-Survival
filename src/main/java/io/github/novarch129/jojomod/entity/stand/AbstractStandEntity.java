package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import io.github.novarch129.jojomod.event.custom.StandEvent;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings({"unused", "ConstantConditions"})
public abstract class AbstractStandEntity extends MobEntity implements IEntityAdditionalSpawnData {
    private static final DataParameter<Optional<UUID>> MASTER_UNIQUE_ID = EntityDataManager.createKey(AbstractStandEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    public boolean ability, attackRush;
    public int attackTick, attackTicker;
    protected PlayerEntity master;

    public AbstractStandEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * @return Returns the Stand's spawn sound.
     */
    public abstract SoundEvent getSpawnSound();

    /**
     * Initiates the Stand's default attack, usually a punch ({@link AbstractStandAttackEntity}).
     *
     * @param special Whether the attack is a special attack or not, usually if it's a rush attack.
     */
    public abstract void attack(boolean special);

    /**
     * @return The Stand's current {@link AbstractStandEntity#master}, also makes sure it isn't <code>null</code>.
     */
    public PlayerEntity getMaster() { //Don't listen to your IDE, this can be null during a relog.
        return master == null ? master = world.getPlayerByUuid(getMasterUUID()) : master;
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
     * Plays the Stand's {@link AbstractStandEntity#getSpawnSound()}.
     */
    public void playSpawnSound() {
        world.playSound(null, master.getPosition(), getSpawnSound(), SoundCategory.NEUTRAL, 1, 1);
    }

    /**
     * Makes the Stand follow it's {@link AbstractStandEntity#master}, follow speed is based on distance from it's master.
     */
    protected void followMaster() {
        double distance = master.getDistance(this);
        double minimum = 0.5; //The Stand's minimum and maximum distance from it's master.
        double maximum = 3;

        if (distance < minimum)
            moveStand();
        else if (distance > minimum)
            if (distance < maximum && distance > minimum + 0.3)
                moveStand();
            else if (distance > maximum && !world.isRemote)
                setPosition(master.getPosX(), master.getPosY(), master.getPosZ());
    }

    /**
     * Used in followMaster() to shorten code, moves the Stand based on it's distance from it's master.
     */
    protected void moveStand() {
        double distanceFromMaster = master.getDistance(this);
        Vec3d distance = getPositionVec().subtract(master.getPositionVec());
        float speed = distanceFromMaster < 0.5 ? -0.1f : (float) distanceFromMaster / 45; //The speed at which the Stand should move towards it's master
        if (distance.getX() > 0)
            moveForward -= speed;
        else if (distance.getX() < 0)
            moveForward += speed;
        if (distance.getY() > 0)
            moveVertical -= speed;
        else if (distance.getY() < 0)
            moveVertical += speed;
        if (distance.getZ() > 0)
            moveStrafing -= speed;
        else if (distance.getZ() < 0)
            moveStrafing += speed;
    }

    /**
     * Makes the Stand dodge oncoming attacks, such as TNT, arrows and falling blocks.
     */
    private void dodgeAttacks() {
        if (world.isRemote) return;
        world.getServer().getWorld(dimension).getEntities()
                .filter(entity -> entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity || entity instanceof ProjectileItemEntity)
                .filter(entity -> entity.getDistance(master) <= Math.PI * 8)
                .forEach(entity -> {
                    double distanceX = getPosX() - entity.getPosX();
                    double distanceY = getPosY() - entity.getPosY();
                    double distanceZ = getPosZ() - entity.getPosZ();
                    if (distanceX > 0)
                        moveForward -= 0.3;
                    else if (distanceX < 0)
                        moveForward += 0.3;
                    if (distanceY > 0)
                        moveVertical -= 0.3;
                    else if (distanceY < 0)
                        moveVertical += 0.3;
                    if (distanceZ > 0)
                        moveStrafing -= 0.3;
                    else if (distanceZ < 0)
                        moveStrafing += 0.3;
                });
    }

    /**
     * Called every tick to update the entity's position/logic and remove it if conditions are met.
     */
    @Override
    public void tick() {
        super.tick(); //Queues the tick method to run, code in tick() method won't run if removed.
        fallDistance = 0; //Mutes that god forsaken fall sound, not even overriding the playFallSound method helps without this.
        if (!world.isRemote && getMaster() != null) { //Calls getMaster to set the master to a @Nonnull value.
            if (!master.isAlive()) {
                MinecraftForge.EVENT_BUS.post(new StandEvent.MasterDeathEvent(master, this));
                remove();
            }
            if (master.isSpectator()) remove();
            MinecraftForge.EVENT_BUS.post(new StandEvent.StandTickEvent(master, this)); //Fired after all death checks are passed to avoid confusion.

            Stand.getLazyOptional(master).ifPresent(props -> {
                if (!props.getStandOn()) {
                    MinecraftForge.EVENT_BUS.post(new StandEvent.StandUnsummonedEvent(master, this));
                    remove();
                }
            });
            StandEffects.getLazyOptional(this).ifPresent(props -> {
                if (props.isThreeFreeze()) {
                    master.setMotion(0, getMotion().getY(), 0);
                    master.velocityChanged = true;
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
        if (MinecraftForge.EVENT_BUS.post(new StandEvent.StandSummonedEvent(master, this)))
            remove(); //Removes the Stand if the Stand summon event is cancelled.
        if (!world.isRemote && master != null) {
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncStandMasterPacket(getEntityId(), master.getEntityId()));
            Stand.getLazyOptional(master).ifPresent(props -> props.setPlayerStand(getEntityId())); //Sets the Stand's Entity#getEntityID to the player's capability.
        }
        playSpawnSound();
    }

    /**
     * Fires the {@link StandEvent.StandRemovedEvent}, will cause major issues if super isn't called.
     */
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(master, this));
        if (master != null)
            Stand.getLazyOptional(master).ifPresent(props -> props.setPlayerStand(0)); //Resets the IStand#getPlayerStand for easier null checks.
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

    @Override
    protected AxisAlignedBB getBoundingBox(Pose pose) {
        return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
    }

    @Override
    protected void collideWithEntity(Entity entity) {
        if (entity instanceof AbstractStandEntity || entity instanceof AbstractStandAttackEntity)
            applyEntityCollision(entity);
    }

    /**
     * Mutes the annoying fall sound.
     */
    @Override
    protected void playFallSound() {
    }

    /**
     * Writes data from the server to a {@link PacketBuffer}.
     *
     * @param buffer The {@link PacketBuffer} to write to.
     */
    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeInt(getMaster() == null ? -1 : master.getEntityId());
    }

    /**
     * Reads the data written to the {@link PacketBuffer} by the server from the client, syncing that data to the client.
     *
     * @param additionalData The {@link PacketBuffer} to read from.
     */
    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        Entity entity = world.getEntityByID(additionalData.readInt());
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
        else
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(getServer(), compoundNBT.getString("MasterUUID"));
        if (!s.isEmpty())
            setMasterUUID(UUID.fromString(s)); //Got most of this code from AbstractHorseEntity, though I improved it a bit.
    }

    /**
     * Very important for custom entities, if not implemented the game can crash with a {@link NullPointerException} and/or the entity won't render.
     */
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
