package com.novarch.jojomod.entities.stands;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

@SuppressWarnings({"unused", "ConstantConditions"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class EntityStandBase extends MobEntity {
    private PlayerEntity master;
    protected boolean standOn;
    private int act;
    public boolean orarush;
    protected SoundEvent spawnSound;
    public int longTick;
    public int standID;
    boolean jumpCheck;
    boolean attack;
    public UUID masterUUID;
    public boolean ability;

    public EntityStandBase(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.master = null;
        this.standOn = true;
        this.orarush = false;
        this.spawnSound = null;
        this.longTick = 2;
        this.ability = true;
    }

    public SoundEvent getSpawnSound() {
        return this.spawnSound;
    }

    public void setMaster(PlayerEntity playerEntity) {
        this.master = playerEntity;
    }

    public int getStandID() {
        return this.standID;
    }

    public boolean attackSwing(PlayerEntity player) {
        return (player.isSwingInProgress && getAttack()) && !setAttack(false);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean setAttack(boolean attack) {
        return this.attack = attack;
    }

    public boolean getAttack() {
        return this.attack;
    }

    public PlayerEntity getMaster() {
        return this.master;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    public void setMasterUUID(final UUID masterUUID) {
        if (!this.world.isRemote)
            this.masterUUID = masterUUID;
    }

    public UUID getMasterUUID() {
        return masterUUID;
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

            MinecraftForge.EVENT_BUS.post(new StandEvent.StandTickEvent(getMaster(), this));
            dodgeAttacks();

            if (!getMaster().isAlive()) {
                MinecraftForge.EVENT_BUS.post(new StandEvent.MasterDeathEvent(getMaster(), this));
                remove();
            }

            if(getMaster().isSpectator())
                remove();

            if (getAir() < 20)
                setAir(60);

            clearActivePotions();
            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                standOn = props.getStandOn();

                if (!props.getStandOn()) {
                    MinecraftForge.EVENT_BUS.post(new StandEvent.StandUnsummonedEvent(getMaster(), this));
                    remove();
                }
            });
        }
    }

    public void spawnSound() {
        world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), getSpawnSound(), getSoundCategory(), 1.0f, 1.0f);
    }

    protected void followMaster() {
        final PlayerEntity entity = getMaster();
        final double distance = entity.getDistance(this);
        final double minimum = 0.5;
        final double maximum = 3.0;

        if (distance < minimum)
            moveStand(distance, entity);

        else if (distance > minimum) {
            if (distance < maximum && distance > minimum + 0.3)
                moveStand(distance, entity);

            else if (distance > maximum && !world.isRemote)
                setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
        }
    }

    public void moveStand(final double distance, final Entity entity) {
        final double distanceX = getPosX() - entity.getPosX();
        final double distanceY = getPosY() - entity.getPosY();
        final double distanceZ = getPosZ() - entity.getPosZ();
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

    @Override
    public boolean attackEntityFrom(final DamageSource damageSource, final float damage) {
        if (!standOn)
            return false;
        if (damageSource.getTrueSource() == getMaster())
            return false;
        if (damageSource == DamageSource.CACTUS)
            return false;
        getMaster().attackEntityFrom(damageSource, damage * 0.5f);
        return false;
    }

    public boolean hasAct() {
        return false;
    }

    public void changeAct() {
        act++;
    }

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
    public void applyEntityCollision(Entity entityIn) {
        if(entityIn instanceof EntityStandBase || entityIn instanceof EntityStandPunch)
            super.applyEntityCollision(entityIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(MinecraftForge.EVENT_BUS.post(new StandEvent.StandSummonedEvent(getMaster(), this))) remove();
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

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }
}
