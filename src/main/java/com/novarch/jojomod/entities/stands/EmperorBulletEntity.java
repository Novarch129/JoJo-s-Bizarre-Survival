package com.novarch.jojomod.entities.stands;

import com.google.common.collect.Lists;
import com.novarch.jojomod.init.EntityInit;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@SuppressWarnings("ALL")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EmperorBulletEntity extends Entity implements IProjectile {
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<Optional<UUID>> field_212362_a = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    private static final DataParameter<Byte> PIERCE_LEVEL = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.BYTE);
    @Nullable
    private BlockState inBlockState;
    protected boolean inGround;
    protected int timeInGround;
    public EmperorBulletEntity.PickupStatus pickupStatus = EmperorBulletEntity.PickupStatus.DISALLOWED;
    public int arrowShake;
    public UUID shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 5.0;
    private int knockbackStrength;
    private IntOpenHashSet piercedEntities;
    private List<Entity> hitEntities;


    public EmperorBulletEntity(double x, double y, double z, World worldIn) {
        this(EntityInit.EMPEROR_BULLET.get(), worldIn);
        this.setPosition(x, y, z);
    }

    public EmperorBulletEntity(LivingEntity shooter, World worldIn) {
        this(shooter.getPosX(), shooter.getPosYEye() - 0.1F, shooter.getPosZ(), worldIn);
        this.setShooter(shooter);
        if (shooter instanceof PlayerEntity) {
            this.pickupStatus = EmperorBulletEntity.PickupStatus.ALLOWED;
        }
    }

    public EmperorBulletEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            onEntityHit((EntityRayTraceResult)raytraceResultIn);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceResultIn;
            BlockState blockstate = world.getBlockState(blockraytraceresult.getPos());
            inBlockState = blockstate;
            Vec3d vec3d = blockraytraceresult.getHitVec().subtract(getPosX(), getPosY(), getPosZ());
            setMotion(vec3d);
            Vec3d vec3d1 = vec3d.normalize().scale(0.05F);
            setRawPosition(getPosX() - vec3d1.x, getPosY() - vec3d1.y, getPosZ() - vec3d1.z);
            inGround = true;
            arrowShake = 7;
            setIsCritical(false);
            setPierceLevel((byte)0);
            setShotFromCrossbow(false);
            clearEntities();
            blockstate.onProjectileCollision(world, blockstate, blockraytraceresult, this);
            if(world.isRemote)
                blockstate.getBlock().addDestroyEffects(blockstate, world, blockraytraceresult.getPos(), Minecraft.getInstance().particles);
        }
    }

    private void clearEntities() {
        if (hitEntities != null) {
            hitEntities.clear();
        }
        if (piercedEntities != null) {
            piercedEntities.clear();
        }
    }


    public double getDamage() {
        return world.getPlayerByUuid(shootingEntity) == null ? damage : Objects.requireNonNull(world.getPlayerByUuid(shootingEntity), "Null entity on getDamage in EmperorBullet").getHealth() / 2.5;
    }

    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    protected void registerData() {
        this.dataManager.register(CRITICAL, (byte)0);
        this.dataManager.register(field_212362_a, Optional.empty());
        this.dataManager.register(PIERCE_LEVEL, (byte)0);
    }

    public void shoot(Entity shooter, float pitch, float yaw, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
        float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, velocity, inaccuracy);
        this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * 0.0075F * inaccuracy, this.rand.nextGaussian() * 0.0075F * inaccuracy, this.rand.nextGaussian() * 0.0075F * inaccuracy).scale(velocity);
        this.setMotion(vec3d);
        float f = MathHelper.sqrt(horizontalMag(vec3d));
        this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (180F / (float)Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, f) * (180F / (float)Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.ticksInGround = 0;
    }

    /**
     * Sets a target for the client to interpolate towards over the next few ticks
     */
    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.setMotion(x, y, z);
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(x * x + z * z);
            this.rotationPitch = (float)(MathHelper.atan2(y, f) * (180F / (float)Math.PI));
            this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180F / (float)Math.PI));
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        boolean flag = this.getNoClip();
        Vec3d vec3d = this.getMotion();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (180F / (float)Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, f) * (180F / (float)Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }

        BlockPos blockpos = new BlockPos(this);
        BlockState blockstate = this.world.getBlockState(blockpos);
        if (!blockstate.isAir(this.world, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3d vec3d1 = this.getPositionVec();

                for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
                    if (axisalignedbb.offset(blockpos).contains(vec3d1)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.isWet()) {
            this.extinguish();
        }

        if (this.inGround && !flag) {
            if (this.inBlockState != blockstate && this.world.hasNoCollisions(this.getBoundingBox().grow(0.06D))) {
                this.inGround = false;
                this.setMotion(vec3d.mul((this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F)));
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            } else if (!this.world.isRemote) {
                this.func_225516_i_();
            }

            ++this.timeInGround;
        } else {
            this.timeInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d2 = this.getPositionVec();
            Vec3d vec3d3 = vec3d2.add(vec3d);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d2, vec3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
                vec3d3 = raytraceresult.getHitVec();
            }

            while(isAlive()) {
                EntityRayTraceResult entityraytraceresult = this.rayTraceEntities(vec3d2, vec3d3);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }

                if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                    assert raytraceresult instanceof EntityRayTraceResult;
                    Entity entity = ((EntityRayTraceResult)raytraceresult).getEntity();
                    Entity entity1 = this.getShooter();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canAttackPlayer((PlayerEntity)entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }

                if (raytraceresult != null && raytraceresult.getType() != RayTraceResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                    this.onHit(raytraceresult);
                    this.isAirBorne = true;
                }

                if (entityraytraceresult == null || this.getPierceLevel() <= 0) {
                    break;
                }

                raytraceresult = null;
            }

            vec3d = this.getMotion();
            double d3 = vec3d.x;
            double d4 = vec3d.y;
            double d0 = vec3d.z;
            if (this.getIsCritical()) {
                for(int i = 0; i < 4; ++i) {
                    this.world.addParticle(ParticleTypes.CRIT, this.getPosX() + d3 * i / 4.0D, this.getPosY() + d4 * i / 4.0D, this.getPosZ() + d0 * i / 4.0D, -d3, -d4 + 0.2D, -d0);
                }
            }

            double d5 = this.getPosX() + d3;
            double d1 = this.getPosY() + d4;
            double d2 = this.getPosZ() + d0;
            if (flag) {
                this.rotationYaw = (float)(MathHelper.atan2(-d3, -d0) * (180F / (float)Math.PI));
            } else {
                this.rotationYaw = (float)(MathHelper.atan2(d3, d0) * (180F / (float)Math.PI));
            }

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
            this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
            float f2 = 0.99F;
            if (this.isInWater()) {
                for(int j = 0; j < 4; ++j) {
                    this.world.addParticle(ParticleTypes.BUBBLE, d5 - d3 * 0.25D, d1 - d4 * 0.25D, d2 - d0 * 0.25D, d3, d4, d0);
                }

                f2 = this.getWaterDrag();
            }

            this.setMotion(vec3d.scale(f2));
            if (!this.hasNoGravity() && !flag) {
                Vec3d vec3d4 = this.getMotion();
                this.setMotion(vec3d4.x, vec3d4.y - 0.05F, vec3d4.z);
            }

            this.setPosition(d5, d1, d2);
            this.doBlockCollisions();
        }
    }

    protected void func_225516_i_() {
        ++this.ticksInGround;
        if (this.ticksInGround >= 1200) {
            this.remove();
        }

    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        Entity entity = p_213868_1_.getEntity();
        float f = (float)this.getMotion().length();
        int i = MathHelper.ceil(Math.max(f * this.damage, 0.0D));
        if (this.getPierceLevel() > 0) {
            if (this.piercedEntities == null) {
                this.piercedEntities = new IntOpenHashSet(5);
            }

            if (this.hitEntities == null) {
                this.hitEntities = Lists.newArrayListWithCapacity(5);
            }

            if (this.piercedEntities.size() >= this.getPierceLevel() + 1) {
                this.remove();
                return;
            }

            this.piercedEntities.add(entity.getEntityId());
        }

        if (this.getIsCritical()) {
            i += this.rand.nextInt(i / 2 + 2);
        }

        Entity entity1 = this.getShooter();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.causeIndirectMagicDamage(this, this);
        } else {
            damagesource = DamageSource.causeIndirectMagicDamage(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastAttackedEntity(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTimer();
        if (this.isBurning() && !flag) {
            entity.setFire(5);
        }

        if (entity.attackEntityFrom(damagesource, (float)i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.world.isRemote && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() + 1);
                }

                if (this.knockbackStrength > 0) {
                    Vec3d vec3d = this.getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(this.knockbackStrength * 0.6D);
                    if (vec3d.lengthSquared() > 0.0D) {
                        livingentity.addVelocity(vec3d.x, 0.1D, vec3d.z);
                    }
                }

                if (!this.world.isRemote && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity)entity1, livingentity);
                }
                
                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity)entity1).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));
                }

                if (!entity.isAlive() && this.hitEntities != null) {
                    this.hitEntities.add(livingentity);
                }

                if (!this.world.isRemote && entity1 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entity1;
                    if (this.hitEntities != null && this.getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, this.hitEntities, this.hitEntities.size());
                    } else if (!entity.isAlive() && this.getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(entity), 0);
                    }
                }
            }

            if (this.getPierceLevel() <= 0) {
                this.remove();
            }
        } else {
            entity.setFireTimer(j);
            this.setMotion(this.getMotion().scale(-0.1D));
            this.rotationYaw += 180.0F;
            this.prevRotationYaw += 180.0F;
            this.ticksInAir = 0;
            if (!this.world.isRemote && this.getMotion().lengthSquared() < 1.0E-7D) {

                this.remove();
            }
        }

    }

    /**
     * Gets the EntityRayTraceResult representing the entity hit
     */
    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) {
        return ProjectileHelper.rayTraceEntities(this.world, this, startVec, endVec, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (p_213871_1_) -> !p_213871_1_.isSpectator() && p_213871_1_.isAlive() && p_213871_1_.canBeCollidedWith() && (p_213871_1_ != this.getShooter() || this.ticksInAir >= 5) && (this.piercedEntities == null || !this.piercedEntities.contains(p_213871_1_.getEntityId())));
    }

    public void writeAdditional(CompoundNBT compound) {
        compound.putShort("life", (short)this.ticksInGround);
        if (this.inBlockState != null) {
            compound.put("inBlockState", NBTUtil.writeBlockState(this.inBlockState));
        }

        compound.putByte("shake", (byte)this.arrowShake);
        compound.putBoolean("inGround", this.inGround);
        compound.putByte("pickup", (byte)this.pickupStatus.ordinal());
        compound.putDouble("damage", this.damage);
        compound.putBoolean("crit", this.getIsCritical());
        compound.putByte("PierceLevel", this.getPierceLevel());
        if (this.shootingEntity != null) {
            compound.putUniqueId("OwnerUUID", this.shootingEntity);
        }

        compound.putBoolean("ShotFromCrossbow", this.getShotFromCrossbow());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        this.ticksInGround = compound.getShort("life");
        if (compound.contains("inBlockState", 10)) {
            this.inBlockState = NBTUtil.readBlockState(compound.getCompound("inBlockState"));
        }

        this.arrowShake = compound.getByte("shake") & 255;
        this.inGround = compound.getBoolean("inGround");
        if (compound.contains("damage", 99)) {
            this.damage = compound.getDouble("damage");
        }

        if (compound.contains("pickup", 99)) {
            this.pickupStatus = EmperorBulletEntity.PickupStatus.getByOrdinal(compound.getByte("pickup"));
        } else if (compound.contains("player", 99)) {
            this.pickupStatus = compound.getBoolean("player") ? EmperorBulletEntity.PickupStatus.ALLOWED : EmperorBulletEntity.PickupStatus.DISALLOWED;
        }

        this.setIsCritical(compound.getBoolean("crit"));
        this.setPierceLevel(compound.getByte("PierceLevel"));
        if (compound.hasUniqueId("OwnerUUID")) {
            this.shootingEntity = compound.getUniqueId("OwnerUUID");
        }

        this.setShotFromCrossbow(compound.getBoolean("ShotFromCrossbow"));
    }

    public void setShooter(@Nullable Entity entityIn) {
        this.shootingEntity = entityIn == null ? null : entityIn.getUniqueID();
        if (entityIn instanceof PlayerEntity) {
            this.pickupStatus = ((PlayerEntity)entityIn).abilities.isCreativeMode ? EmperorBulletEntity.PickupStatus.CREATIVE_ONLY : EmperorBulletEntity.PickupStatus.ALLOWED;
        }

    }

    @Nullable
    public Entity getShooter() {
        return this.shootingEntity != null && this.world instanceof ServerWorld ? ((ServerWorld)this.world).getEntityByUuid(this.shootingEntity) : null;
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!this.world.isRemote && (this.inGround || this.getNoClip()) && this.arrowShake <= 0) {
            boolean flag = this.pickupStatus == EmperorBulletEntity.PickupStatus.ALLOWED || this.pickupStatus == EmperorBulletEntity.PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || this.getNoClip() && this.getShooter().getUniqueID() == entityIn.getUniqueID();

            if (flag) {
                entityIn.onItemPickup(this, 1);
                this.remove();
            }

        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    /**
     * Returns true if it's possible to attack this entity with an item.
     */
    public boolean canBeAttackedWithItem() {
        return false;
    }

    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.0F;
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public void setIsCritical(boolean critical) {
        this.setArrowFlag(1, critical);
    }

    public void setPierceLevel(byte level) {
        this.dataManager.set(PIERCE_LEVEL, level);
    }

    private void setArrowFlag(int p_203049_1_, boolean p_203049_2_) {
        byte b0 = this.dataManager.get(CRITICAL);
        if (p_203049_2_) {
            this.dataManager.set(CRITICAL, (byte)(b0 | p_203049_1_));
        } else {
            this.dataManager.set(CRITICAL, (byte)(b0 & ~p_203049_1_));
        }

    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public boolean getIsCritical() {
        byte b0 = this.dataManager.get(CRITICAL);
        return (b0 & 1) != 0;
    }

    /**
     * Whether the arrow was shot from a crossbow.
     */
    public boolean getShotFromCrossbow() {
        byte b0 = this.dataManager.get(CRITICAL);
        return (b0 & 4) != 0;
    }

    public byte getPierceLevel() {
        return this.dataManager.get(PIERCE_LEVEL);
    }

    protected float getWaterDrag() {
        return 0.6F;
    }

    /**
     * Whether the arrow can noClip
     */
    public boolean getNoClip() {
        if (!this.world.isRemote) {
            return this.noClip;
        } else {
            return (this.dataManager.get(CRITICAL) & 2) != 0;
        }
    }

    /**
     * Sets data about if this arrow entity was shot from a crossbow
     */
    public void setShotFromCrossbow(boolean fromCrossbow) {
        this.setArrowFlag(4, fromCrossbow);
    }

    public IPacket<?> createSpawnPacket() {
        Entity entity = this.getShooter();
        return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getEntityId());
    }

    public enum PickupStatus {
        DISALLOWED,
        ALLOWED,
        CREATIVE_ONLY;

        public static PickupStatus getByOrdinal(int ordinal) {
            if (ordinal < 0 || ordinal > values().length) {
                ordinal = 0;
            }

            return values()[ordinal];
        }
    }
}
