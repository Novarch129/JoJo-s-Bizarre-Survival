package io.github.novarch129.jojomod.entity.stand.attack;

import com.google.common.collect.Lists;
import io.github.novarch129.jojomod.init.EntityInit;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
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

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("ALL")
public class EmperorBulletEntity extends Entity implements IProjectile {
    protected static final DataParameter<Optional<UUID>> field_212362_a = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID); //Obfuscated
    private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> PIERCE_LEVEL = EntityDataManager.createKey(EmperorBulletEntity.class, DataSerializers.BYTE);
    public EmperorBulletEntity.PickupStatus pickupStatus = EmperorBulletEntity.PickupStatus.DISALLOWED;
    public int arrowShake;
    public UUID shootingEntity;
    protected boolean inGround;
    protected int timeInGround;
    @Nullable
    private BlockState inBlockState;
    private int ticksInGround;
    private int ticksInAir;
    private double damage = 5.0;
    private int knockbackStrength;
    private IntOpenHashSet piercedEntities;
    private List<Entity> hitEntities;


    public EmperorBulletEntity(double x, double y, double z, World worldIn) {
        this(EntityInit.EMPEROR_BULLET.get(), worldIn);
        setPosition(x, y, z);
    }

    public EmperorBulletEntity(LivingEntity shooter, World worldIn) {
        this(shooter.getPosX(), shooter.getPosYEye() - 0.1F, shooter.getPosZ(), worldIn);
        setShooter(shooter);
        if (shooter instanceof PlayerEntity) {
            pickupStatus = EmperorBulletEntity.PickupStatus.ALLOWED;
        }
    }

    public EmperorBulletEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
            onEntityHit((EntityRayTraceResult) raytraceResultIn);
        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceResultIn;
            BlockState blockstate = world.getBlockState(blockraytraceresult.getPos());
            inBlockState = blockstate;
            Vec3d vec3d = blockraytraceresult.getHitVec().subtract(getPosX(), getPosY(), getPosZ());
            setMotion(vec3d);
            Vec3d vec3d1 = vec3d.normalize().scale(0.05F);
            setRawPosition(getPosX() - vec3d1.x, getPosY() - vec3d1.y, getPosZ() - vec3d1.z);
            inGround = true;
            arrowShake = 7;
            setIsCritical(false);
            setPierceLevel((byte) 0);
            setShotFromCrossbow(false);
            clearEntities();
            blockstate.onProjectileCollision(world, blockstate, blockraytraceresult, this);
            if (world.isRemote)
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

    public boolean isInRangeToRenderDist(double distance) {
        double d0 = getBoundingBox().getAverageEdgeLength() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    protected void registerData() {
        dataManager.register(CRITICAL, (byte) 0);
        dataManager.register(field_212362_a, Optional.empty());
        dataManager.register(PIERCE_LEVEL, (byte) 0);
    }

    public void shoot(Entity shooter, float pitch, float yaw, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
        float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        shoot(f, f1, f2, velocity, inaccuracy);
        setMotion(getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(rand.nextGaussian() * 0.0075F * inaccuracy, rand.nextGaussian() * 0.0075F * inaccuracy, rand.nextGaussian() * 0.0075F * inaccuracy).scale(velocity);
        setMotion(vec3d);
        float f = MathHelper.sqrt(horizontalMag(vec3d));
        rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (180F / (float) Math.PI));
        rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (180F / (float) Math.PI));
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
        ticksInGround = 0;
    }

    /**
     * Sets a target for the client to interpolate towards over the next few ticks
     */
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        setPosition(x, y, z);
        setRotation(yaw, pitch);
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    public void setVelocity(double x, double y, double z) {
        setMotion(x, y, z);
        if (prevRotationPitch == 0 && prevRotationYaw == 0) {
            float f = MathHelper.sqrt(x * x + z * z);
            rotationPitch = (float) (MathHelper.atan2(y, f) * (180F / (float) Math.PI));
            rotationYaw = (float) (MathHelper.atan2(x, z) * (180F / (float) Math.PI));
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
            ticksInGround = 0;
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        boolean flag = getNoClip();
        Vec3d vec3d = getMotion();
        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (180F / (float) Math.PI));
            rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (180F / (float) Math.PI));
            prevRotationYaw = rotationYaw;
            prevRotationPitch = rotationPitch;
        }

        BlockPos blockpos = new BlockPos(this);
        BlockState blockstate = world.getBlockState(blockpos);
        if (!blockstate.isAir(world, blockpos) && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(world, blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3d vec3d1 = getPositionVec();

                for (AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
                    if (axisalignedbb.offset(blockpos).contains(vec3d1)) {
                        inGround = true;
                        break;
                    }
                }
            }
        }

        if (arrowShake > 0) {
            --arrowShake;
        }

        if (isWet()) {
            extinguish();
        }

        if (inGround && !flag) {
            if (inBlockState != blockstate && world.hasNoCollisions(getBoundingBox().grow(0.06D))) {
                inGround = false;
                setMotion(vec3d.mul((rand.nextFloat() * 0.2F), (rand.nextFloat() * 0.2F), (rand.nextFloat() * 0.2F)));
                ticksInGround = 0;
                ticksInAir = 0;
            } else if (!world.isRemote) {
                func_225516_i_();
            }

            ++timeInGround;
        } else {
            timeInGround = 0;
            ++ticksInAir;
            Vec3d vec3d2 = getPositionVec();
            Vec3d vec3d3 = vec3d2.add(vec3d);
            RayTraceResult raytraceresult = world.rayTraceBlocks(new RayTraceContext(vec3d2, vec3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
                vec3d3 = raytraceresult.getHitVec();
            }

            while (isAlive()) {
                EntityRayTraceResult entityraytraceresult = rayTraceEntities(vec3d2, vec3d3);
                if (entityraytraceresult != null) {
                    raytraceresult = entityraytraceresult;
                }

                if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                    assert raytraceresult instanceof EntityRayTraceResult;
                    Entity entity = ((EntityRayTraceResult) raytraceresult).getEntity();
                    Entity entity1 = getShooter();
                    if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity) entity1).canAttackPlayer((PlayerEntity) entity)) {
                        raytraceresult = null;
                        entityraytraceresult = null;
                    }
                }

                if (raytraceresult != null && raytraceresult.getType() != RayTraceResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                    onHit(raytraceresult);
                    isAirBorne = true;
                }

                if (entityraytraceresult == null || getPierceLevel() <= 0) {
                    break;
                }

                raytraceresult = null;
            }

            vec3d = getMotion();
            double d3 = vec3d.x;
            double d4 = vec3d.y;
            double d0 = vec3d.z;
            if (getIsCritical()) {
                for (int i = 0; i < 4; ++i) {
                    world.addParticle(ParticleTypes.CRIT, getPosX() + d3 * i / 4.0D, getPosY() + d4 * i / 4.0D, getPosZ() + d0 * i / 4.0D, -d3, -d4 + 0.2D, -d0);
                }
            }

            double d5 = getPosX() + d3;
            double d1 = getPosY() + d4;
            double d2 = getPosZ() + d0;
            if (flag) {
                rotationYaw = (float) (MathHelper.atan2(-d3, -d0) * (180F / (float) Math.PI));
            } else {
                rotationYaw = (float) (MathHelper.atan2(d3, d0) * (180F / (float) Math.PI));
            }

            while (rotationPitch - prevRotationPitch >= 180.0F) {
                prevRotationPitch += 360.0F;
            }

            while (rotationYaw - prevRotationYaw < -180.0F) {
                prevRotationYaw -= 360.0F;
            }

            while (rotationYaw - prevRotationYaw >= 180.0F) {
                prevRotationYaw += 360.0F;
            }

            rotationPitch = MathHelper.lerp(0.2F, prevRotationPitch, rotationPitch);
            rotationYaw = MathHelper.lerp(0.2F, prevRotationYaw, rotationYaw);
            float f2 = 0.99F;
            if (isInWater()) {
                for (int j = 0; j < 4; ++j) {
                    world.addParticle(ParticleTypes.BUBBLE, d5 - d3 * 0.25D, d1 - d4 * 0.25D, d2 - d0 * 0.25D, d3, d4, d0);
                }

                f2 = getWaterDrag();
            }

            setMotion(vec3d.scale(f2));
            if (!hasNoGravity() && !flag) {
                Vec3d vec3d4 = getMotion();
                setMotion(vec3d4.x, vec3d4.y - 0.05F, vec3d4.z);
            }

            setPosition(d5, d1, d2);
            doBlockCollisions();
        }
    }

    protected void func_225516_i_() {
        ++ticksInGround;
        if (ticksInGround >= 1200) {
            remove();
        }

    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
        Entity entity = p_213868_1_.getEntity();
        float f = (float) getMotion().length();
        int i = MathHelper.ceil(Math.max(f * damage, 0.0D));
        if (getPierceLevel() > 0) {
            if (piercedEntities == null) {
                piercedEntities = new IntOpenHashSet(5);
            }

            if (hitEntities == null) {
                hitEntities = Lists.newArrayListWithCapacity(5);
            }

            if (piercedEntities.size() >= getPierceLevel() + 1) {
                remove();
                return;
            }

            piercedEntities.add(entity.getEntityId());
        }

        if (getIsCritical()) {
            i += rand.nextInt(i / 2 + 2);
        }

        Entity entity1 = getShooter();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.causeIndirectMagicDamage(this, this);
        } else {
            damagesource = DamageSource.causeIndirectMagicDamage(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity) entity1).setLastAttackedEntity(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTimer();
        if (isBurning() && !flag) {
            entity.setFire(5);
        }

        if (entity.attackEntityFrom(damagesource, (float) i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity;
                if (!world.isRemote && getPierceLevel() <= 0) {
                    livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() + 1);
                }

                if (knockbackStrength > 0) {
                    Vec3d vec3d = getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale(knockbackStrength * 0.6D);
                    if (vec3d.lengthSquared() > 0.0D) {
                        livingentity.addVelocity(vec3d.x, 0.1D, vec3d.z);
                    }
                }

                if (!world.isRemote && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity) entity1, livingentity);
                }

                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity1).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));
                }

                if (!entity.isAlive() && hitEntities != null) {
                    hitEntities.add(livingentity);
                }

                if (!world.isRemote && entity1 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity1;
                    if (hitEntities != null && getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, hitEntities, hitEntities.size());
                    } else if (!entity.isAlive() && getShotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayerentity, Arrays.asList(entity), 0);
                    }
                }
            }

            if (getPierceLevel() <= 0) {
                remove();
            }
        } else {
            entity.setFireTimer(j);
            setMotion(getMotion().scale(-0.1D));
            rotationYaw += 180.0F;
            prevRotationYaw += 180.0F;
            ticksInAir = 0;
            if (!world.isRemote && getMotion().lengthSquared() < 1.0E-7D) {

                remove();
            }
        }

    }

    /**
     * Gets the EntityRayTraceResult representing the entity hit
     */
    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) {
        return ProjectileHelper.rayTraceEntities(world, this, startVec, endVec, getBoundingBox().expand(getMotion()).grow(1.0D), (p_213871_1_) -> !p_213871_1_.isSpectator() && p_213871_1_.isAlive() && p_213871_1_.canBeCollidedWith() && (p_213871_1_ != getShooter() || ticksInAir >= 5) && (piercedEntities == null || !piercedEntities.contains(p_213871_1_.getEntityId())));
    }

    public void writeAdditional(CompoundNBT compound) {
        compound.putShort("life", (short) ticksInGround);
        if (inBlockState != null) {
            compound.put("inBlockState", NBTUtil.writeBlockState(inBlockState));
        }

        compound.putByte("shake", (byte) arrowShake);
        compound.putBoolean("inGround", inGround);
        compound.putByte("pickup", (byte) pickupStatus.ordinal());
        compound.putDouble("damage", damage);
        compound.putBoolean("crit", getIsCritical());
        compound.putByte("PierceLevel", getPierceLevel());
        if (shootingEntity != null) {
            compound.putUniqueId("OwnerUUID", shootingEntity);
        }

        compound.putBoolean("ShotFromCrossbow", getShotFromCrossbow());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        ticksInGround = compound.getShort("life");
        if (compound.contains("inBlockState", 10)) {
            inBlockState = NBTUtil.readBlockState(compound.getCompound("inBlockState"));
        }

        arrowShake = compound.getByte("shake") & 255;
        inGround = compound.getBoolean("inGround");
        if (compound.contains("damage", 99)) {
            damage = compound.getDouble("damage");
        }

        if (compound.contains("pickup", 99)) {
            pickupStatus = EmperorBulletEntity.PickupStatus.getByOrdinal(compound.getByte("pickup"));
        } else if (compound.contains("player", 99)) {
            pickupStatus = compound.getBoolean("player") ? EmperorBulletEntity.PickupStatus.ALLOWED : EmperorBulletEntity.PickupStatus.DISALLOWED;
        }

        setIsCritical(compound.getBoolean("crit"));
        setPierceLevel(compound.getByte("PierceLevel"));
        if (compound.hasUniqueId("OwnerUUID")) {
            shootingEntity = compound.getUniqueId("OwnerUUID");
        }

        setShotFromCrossbow(compound.getBoolean("ShotFromCrossbow"));
    }

    @Nullable
    public Entity getShooter() {
        return shootingEntity != null && world instanceof ServerWorld ? ((ServerWorld) world).getEntityByUuid(shootingEntity) : null;
    }

    public void setShooter(@Nullable Entity entityIn) {
        shootingEntity = entityIn == null ? null : entityIn.getUniqueID();
        if (entityIn instanceof PlayerEntity) {
            pickupStatus = ((PlayerEntity) entityIn).abilities.isCreativeMode ? EmperorBulletEntity.PickupStatus.CREATIVE_ONLY : EmperorBulletEntity.PickupStatus.ALLOWED;
        }

    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!world.isRemote && (inGround || getNoClip()) && arrowShake <= 0) {
            boolean flag = pickupStatus == EmperorBulletEntity.PickupStatus.ALLOWED || pickupStatus == EmperorBulletEntity.PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || getNoClip() && getShooter().getUniqueID() == entityIn.getUniqueID();

            if (flag) {
                entityIn.onItemPickup(this, 1);
                remove();
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
        return 0.0f;
    }

    private void setArrowFlag(int p_203049_1_, boolean p_203049_2_) {
        byte b0 = dataManager.get(CRITICAL);
        if (p_203049_2_) {
            dataManager.set(CRITICAL, (byte) (b0 | p_203049_1_));
        } else {
            dataManager.set(CRITICAL, (byte) (b0 & ~p_203049_1_));
        }

    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public boolean getIsCritical() {
        byte b0 = dataManager.get(CRITICAL);
        return (b0 & 1) != 0;
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public void setIsCritical(boolean critical) {
        setArrowFlag(1, critical);
    }

    /**
     * Whether the arrow was shot from a crossbow.
     */
    public boolean getShotFromCrossbow() {
        byte b0 = dataManager.get(CRITICAL);
        return (b0 & 4) != 0;
    }

    /**
     * Sets data about if this arrow entity was shot from a crossbow
     */
    public void setShotFromCrossbow(boolean fromCrossbow) {
        setArrowFlag(4, fromCrossbow);
    }

    public byte getPierceLevel() {
        return dataManager.get(PIERCE_LEVEL);
    }

    public void setPierceLevel(byte level) {
        dataManager.set(PIERCE_LEVEL, level);
    }

    protected float getWaterDrag() {
        return 0.6f;
    }

    /**
     * Whether the arrow can noClip
     */
    public boolean getNoClip() {
        if (!world.isRemote) {
            return noClip;
        } else {
            return (dataManager.get(CRITICAL) & 2) != 0;
        }
    }

    public IPacket<?> createSpawnPacket() {
        Entity entity = getShooter();
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
