package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.event.custom.StandAttackEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

/**
 * This class is a mess, I can barely read it, especially the {@link #tick()} method..
 */
@SuppressWarnings("ALL")
public abstract class AbstractStandAttackEntity extends Entity implements IProjectile, IEntityAdditionalSpawnData {
    public double xTile, yTile, zTile, arrowShake, ticksInAir;
    public Entity shootingEntity;
    public AbstractStandEntity shootingStand;
    public PlayerEntity standMaster;
    protected boolean inGround;

    public AbstractStandAttackEntity(EntityType<? extends Entity> type, World worldIn) {
        super(type, worldIn);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        setNoGravity(true);
    }

    public AbstractStandAttackEntity(EntityType<? extends Entity> type, World worldIn, double x, double y, double z) {
        this(type, worldIn);
        setPosition(x, y, z);
    }

    public AbstractStandAttackEntity(EntityType<? extends Entity> type, World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        this(type, worldIn, shooter.getPosX(), shooter.getPosY() + (shooter.getMaster()).getEyeHeight(), shooter.getPosZ());
        shootingEntity = shooter;
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
    }

    protected abstract void onEntityHit(EntityRayTraceResult result);

    protected abstract void onBlockHit(BlockRayTraceResult result);

    public abstract ResourceLocation getEntityTexture();

    public abstract <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel();

    /**
     * Defines the range of a Stand attack, {@link Override} to change, default is 2.
     */
    protected int getRange() {
        return 2;
    }

    private BlockPos getBlockPosFromNBT() {
        return new BlockPos(xTile, yTile, zTile);
    }

    public void shoot(Entity shooter, float pitch, float yaw, float velocity, float inaccuracy) {
        float x = -MathHelper.sin(yaw * ((float) Math.PI / 180)) * MathHelper.cos(pitch * ((float) Math.PI / 180));
        float y = -MathHelper.sin(pitch * ((float) Math.PI / 180));
        float z = MathHelper.cos(yaw * ((float) Math.PI / 180)) * MathHelper.cos(pitch * ((float) Math.PI / 180));
        shoot(x, y, z, velocity, inaccuracy);
        setMotion(getMotion().add(shooter.getMotion().getX(), shooter.onGround ? 0 : shooter.getMotion().getY(), shooter.getMotion().getZ()));
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(rand.nextGaussian() * (double) 0.0075 * (double) inaccuracy, rand.nextGaussian() * (double) 0.0075 * (double) inaccuracy, rand.nextGaussian() * (double) 0.0075 * (double) inaccuracy).scale(velocity);
        setMotion(vec3d);
        float f = MathHelper.sqrt(horizontalMag(vec3d));
        rotationYaw = (float) (MathHelper.atan2(vec3d.getX(), vec3d.getZ()) * (double) (180 / (float) Math.PI));
        rotationPitch = (float) (MathHelper.atan2(vec3d.getY(), f) * (double) (180 / (float) Math.PI));
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
    }

    public void randomizePositions() {
        if (shootingStand == null) return;
        Vec3d random = new Vec3d(rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1, rand.nextDouble() * 2 - 1);
        Vec3d position = getPositionVec().add(random).add(shootingStand.getLookVec().mul(1.5, 1.5, 1.5));
        setPosition(position.getX(), position.getY(), position.getZ());
        setRotation(shootingStand.rotationYaw, shootingStand.rotationPitch);
    }

    public void movePunchInFrontOfStand(AbstractStandEntity stand) {
        if (stand.getMaster() == null) return;
        Vec3d position = getPositionVec().add(stand.getMaster().getLookVec().mul(0.5, 0.2, 0.5));
        setPosition(position.getX(), position.getY(), position.getZ());
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        setMotion(x, y, z);
        if (prevRotationPitch == 0 && prevRotationYaw == 0) {
            rotationPitch = (float) (MathHelper.atan2(y, (double) MathHelper.sqrt(x * x + z * z)) * (double) (180 / (float) Math.PI));
            rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180 / (float) Math.PI));
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
            inGround = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (standMaster != null)
            setRotation(standMaster.rotationYaw, standMaster.rotationPitch);
        if (shootingEntity == null && !world.isRemote)
            remove();
        BlockPos blockPos = new BlockPos(xTile, yTile, zTile);
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getMaterial() != Material.AIR && !blockState.getCollisionShape(world, blockPos).isEmpty())
            if (blockState.getCollisionShape(world, blockPos).getBoundingBox().offset(blockPos).contains(new Vec3d(getPosX(), getPosY(), getPosZ())))
                inGround = true;
        if (arrowShake > 0)
            arrowShake--;
        if (!inGround) {
            ticksInAir++;
            if (ticksInAir >= getRange())
                if (!world.isRemote)
                    remove();
            Vec3d vec3d1 = new Vec3d(getPosX(), getPosY(), getPosZ());
            Vec3d vec3d = new Vec3d(getPosX() + getMotion().getX(), getPosY() + getMotion().getY(), getPosZ() + getMotion().getZ());
            BlockRayTraceResult raytraceresult = world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
            EntityRayTraceResult entityRayTraceResult = null;
            vec3d1 = getPositionVec();
            EntityRayTraceResult result = rayTraceEntities(vec3d1, vec3d1.add(getMotion()));
            if (result != null) {
                entityRayTraceResult = result;
                if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof AbstractStandAttackEntity) {
                    AbstractStandAttackEntity punch = (AbstractStandAttackEntity) entityRayTraceResult.getEntity();
                    if (punch.shootingEntity == shootingEntity)
                        raytraceresult = null;
                }
                if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity) entityRayTraceResult.getEntity();
                    if (playerEntity.isEntityEqual(standMaster))
                        raytraceresult = null;
                    else
                        entityRayTraceResult = new EntityRayTraceResult(playerEntity);
                }
                if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof AbstractStandEntity)
                    if (entityRayTraceResult.getEntity() == shootingStand)
                        entityRayTraceResult = null;
            }
            if (entityRayTraceResult != null && !ForgeEventFactory.onProjectileImpact(this, entityRayTraceResult))
                onHit(entityRayTraceResult);
            if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
                onHit(raytraceresult);
            setPosition(getPosX() + getMotion().getX(), getPosY() + getMotion().getY(), getPosZ() + getMotion().getZ());
            rotationYaw = (float) (MathHelper.atan2(getMotion().getX(), getMotion().getZ()) * 57.29577951308232);
            while (rotationPitch - prevRotationPitch >= 180)
                prevRotationPitch += 360;
            while (rotationYaw - prevRotationYaw < -180)
                prevRotationYaw -= 360;
            while (rotationYaw - prevRotationYaw >= 180)
                prevRotationYaw += 360;
            rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;
            rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;
            float f1 = 0.99f;
            if (isInWater()) {
                for (int i = 0; i < 4; i++)
                    world.addParticle(ParticleTypes.BUBBLE, getPosX() - getMotion().getX() * 0.25, getPosY() - getMotion().getY() * 0.25d, getPosZ() - getMotion().getZ() * 0.25d, getMotion().getX(), getMotion().getY(), getMotion().getZ());
                f1 = 0.8f;
            }
            if (isWet())
                extinguish();
            setMotion(getMotion().getX() * f1, getMotion().getY() * f1, getMotion().getZ() * f1);
            if (!hasNoGravity())
                setMotion(getMotion().getX(), getMotion().getY() - 0.05000000074505806, getMotion().getZ());
            setPosition(getPosX(), getPosY(), getPosZ());
            doBlockCollisions();
        }
    }

    private void onHit(RayTraceResult result) {
        if (result.getType() != RayTraceResult.Type.MISS)
            if (MinecraftForge.EVENT_BUS.post(new StandAttackEvent(
                    this,
                    result,
                    result.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult) result).getEntity() : null,
                    result.getType()
            ))) return;
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            if (entity != standMaster && entity != this) {
                DamageSource damagesource;
                if (shootingStand == null)
                    damagesource = DamageSource.causeThrownDamage(this, this);
                else if (standMaster == null)
                    damagesource = DamageSource.causeThrownDamage(this, shootingStand);
                else
                    damagesource = DamageSource.causeThrownDamage(this, standMaster);
                if (isBurning() && !(entity instanceof EndermanEntity))
                    entity.setFire(5);
                if (entity.attackEntityFrom(damagesource, 0.1f)) {
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        if (!world.isRemote) {
                            world.addParticle(ParticleTypes.EXPLOSION, getPosX(), getPosY(), getPosZ(), 1, 0, 0);
                            if (!world.isRemote) {
                                if (MinecraftForge.EVENT_BUS.post(new StandAttackEvent.EntityHit(this, result, entity)))
                                    return;
                                onEntityHit((EntityRayTraceResult) result);
                            }
                        }
                        if (standMaster != null && livingEntity != standMaster && livingEntity instanceof PlayerEntity && standMaster instanceof ServerPlayerEntity)
                            ((ServerPlayerEntity) standMaster).connection.sendPacket(new SChangeGameStatePacket(6, 0));
                    }
                    if (!(entity instanceof EndermanEntity))
                        remove();
                } else if (!world.isRemote && getMotion().getX() * getMotion().getX() + getMotion().getY() * getMotion().getY() + getMotion().getZ() * getMotion().getZ() < 0.0010000000474974513)
                    remove();
            }
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockRayTraceResult) result).getPos();
            BlockState state = world.getBlockState(blockPos);
            xTile = blockPos.getX();
            yTile = blockPos.getY();
            zTile = blockPos.getZ();
            setMotion((float) (result.getHitVec().getX() - getPosX()), (float) (result.getHitVec().getY() - getPosY()), (float) (result.getHitVec().getZ() - getPosZ()));
            float f2 = MathHelper.sqrt(getMotion().getX() * getMotion().getX() + getMotion().getY() * getMotion().getY() + getMotion().getZ() * getMotion().getZ());
            setPosition(getPosX() - getMotion().getX() / f2 * 0.05000000074505806, getPosY() - getMotion().getY() / f2 * 0.05000000074505806, getPosZ() - getMotion().getZ() / f2 * 0.05000000074505806);
            inGround = true;
            arrowShake = 7;
            if (!world.isRemote) {
                if (MinecraftForge.EVENT_BUS.post(new StandAttackEvent.BlockHit(this, result, null))) return;
                if (MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(world, blockPos, state, standMaster)))
                    return;
                onBlockHit((BlockRayTraceResult) result);
                if (state.getMaterial() != Material.AIR) {
                    state.getBlock().onProjectileCollision(world, state, (BlockRayTraceResult) result, this);
                    remove();
                }
            }
        }
    }

    @Override
    public void move(MoverType type, Vec3d pos) {
        super.move(type, pos);
        if (inGround) {
            xTile = MathHelper.floor(getPosX());
            yTile = MathHelper.floor(getPosY());
            zTile = MathHelper.floor(getPosZ());
        }
    }

    @Nullable
    protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) {
        return ProjectileHelper.rayTraceEntities(world, this, startVec, endVec, getBoundingBox().expand(getMotion()).grow(1), (entity) ->
                !entity.isSpectator() && entity.isAlive() && entity.canBeCollidedWith() && (entity != shootingStand || ticksInAir >= getRange()) && entity.getEntityId() != standMaster.getEntityId());
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity entityIn) {
        if (!world.isRemote && inGround && arrowShake <= 0 && entityIn != standMaster) remove();
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
        if (entityIn != shootingStand && entityIn != standMaster)
            super.applyEntityCollision(entityIn);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeAttackedWithItem() {
        return false;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeInt(shootingStand == null ? -1 : shootingStand.getEntityId());
        buffer.writeInt(standMaster == null ? -1 : standMaster.getEntityId());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        Entity stand = world.getEntityByID(additionalData.readInt());
        Entity master = world.getEntityByID(additionalData.readInt());
        if (stand instanceof AbstractStandEntity)
            shootingStand = (AbstractStandEntity) stand;
        if (master instanceof PlayerEntity)
            standMaster = (PlayerEntity) master;
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        xTile = compound.getDouble("xTile");
        yTile = compound.getDouble("yTile");
        zTile = compound.getDouble("zTile");
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putDouble("xTile", xTile);
        compound.putDouble("yTile", yTile);
        compound.putDouble("zTile", zTile);
    }

    @Override
    protected void registerData() {
    }
}