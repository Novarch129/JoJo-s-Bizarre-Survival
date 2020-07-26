package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.event.custom.StandAttackEvent;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class is a mess, I can barely read it.
 */
@SuppressWarnings("ALL")
public abstract class AbstractStandAttackEntity extends Entity implements IProjectile, IEntityAdditionalSpawnData {
    public int xTile, yTile, zTile, arrowShake, ticksInAir;
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
        movePunchInFront(shooter);
    }

    protected abstract void onEntityHit(EntityRayTraceResult result);

    protected abstract void onBlockHit(BlockRayTraceResult result);

    /**
     * Defines the range of a Stand attack, {@link Override} to change, default is 2.
     */
    protected int getRange() {
        return 2;
    }

    private void setXYZ(BlockPos blockPos) {
        xTile = blockPos.getX();
        yTile = blockPos.getY();
        zTile = blockPos.getZ();
    }

    public boolean isInRangeToRenderDist(double distance) {
        double d0 = getBoundingBox().getAverageEdgeLength() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }
        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    public void shoot(Entity shooter, float pitch, float yaw, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        float f1 = -MathHelper.sin(pitch * ((float) Math.PI / 180F));
        float f2 = MathHelper.cos(yaw * ((float) Math.PI / 180F)) * MathHelper.cos(pitch * ((float) Math.PI / 180F));
        shoot(f, f1, f2, velocity, inaccuracy);
        setMotion(getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy).scale(velocity);
        setMotion(vec3d);
        float f = MathHelper.sqrt(horizontalMag(vec3d));
        rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
        rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180F / (float) Math.PI));
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
    }

    public void setRandomPositions() {
        Vec3d lookVec = shootingStand.getLookVec();
        double rand1 = rand.nextDouble() * 2 - 1;
        double rand2 = rand.nextDouble() * 2 - 1;
        double rand3 = rand.nextDouble() * 2 - 1;
        double posX = getPosX() + rand1 + lookVec.getX() * 1.5;
        double posY = getPosY() + rand2 + lookVec.getY() * 1.5;
        double posZ = getPosZ() + rand3 + lookVec.getZ() * 1.5;
        setPosition(posX, posY, posZ);
        setRotation(shootingStand.rotationYaw, shootingStand.rotationPitch);
    }

    @Override
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        setPosition(x, y, z);
        setRotation(yaw, pitch);
    }

    public void movePunchInFront(AbstractStandEntity base) {
        PlayerEntity master = base.getMaster();
        Vec3d vector = master.getLookVec();
        double x = getPosX() + vector.x * 0.5;
        double y = getPosY() + vector.y * 0.4 * 0.5;
        double z = getPosZ() + vector.z * 0.5;
        setPosition(x, y, z);
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        setMotion(x, y, z);
        if (prevRotationPitch == 0 && prevRotationYaw == 0) {
            float f = MathHelper.sqrt(x * x + z * z);
            rotationPitch = (float) (MathHelper.atan2(y, f) * (double) (180 / (float) Math.PI));
            rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180 / (float) Math.PI));
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (standMaster != null) {
            rotateTowards(standMaster.rotationYaw, standMaster.rotationPitch);
            setRotation(standMaster.rotationYaw, standMaster.rotationPitch);
        }
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
            Vec3d vec3d = new Vec3d(getPosX() + getMotion().x, getPosY() + getMotion().y, getPosZ() + getMotion().z);
            BlockRayTraceResult raytraceresult = world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
            EntityRayTraceResult entityRayTraceResult = null;
            vec3d1 = new Vec3d(getPosX(), getPosY(), getPosZ());
            Entity entity = findEntityOnPath(vec3d1);
            if (entity != null) {
                entityRayTraceResult = new EntityRayTraceResult(entity);
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
            rotationYaw = (float) (MathHelper.atan2(getMotion().x, getMotion().z) * 57.29577951308232);
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
                    world.addParticle(ParticleTypes.BUBBLE, getPosX() - getMotion().x * 0.25, getPosY() - getMotion().y * 0.25d, getPosZ() - getMotion().z * 0.25d, getMotion().x, getMotion().y, getMotion().z);
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
                            world.addParticle(ParticleTypes.EXPLOSION, getPosX(), getPosY(), getPosZ(), 1.0d, 0.0d, 0.0d);
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
                } else if (!world.isRemote && getMotion().x * getMotion().x + getMotion().y * getMotion().y + getMotion().z * getMotion().z < 0.0010000000474974513)
                    remove();
            }
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos pos = ((BlockRayTraceResult) result).getPos();
            BlockState state = world.getBlockState(pos);
            setXYZ(pos);
            setMotion((float) (result.getHitVec().x - getPosX()), (float) (result.getHitVec().y - getPosY()), (float) (result.getHitVec().z - getPosZ()));
            float f2 = MathHelper.sqrt(getMotion().getX() * getMotion().getX() + getMotion().getY() * getMotion().getY() + getMotion().getZ() * getMotion().getZ());
            setPosition(getPosX() - getMotion().getX() / f2 * 0.05000000074505806d, getPosY() - getMotion().getY() / f2 * 0.05000000074505806, getPosZ() - getMotion().getZ() / f2 * 0.05000000074505806d);
            inGround = true;
            arrowShake = 7;
            if (!world.isRemote) {
                if (MinecraftForge.EVENT_BUS.post(new StandAttackEvent.BlockHit(this, result, null))) return;
                if (MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(world, pos, state, standMaster)))
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
    protected Entity findEntityOnPath(Vec3d start) {
        Entity entity = null;
        List<Entity> list = world.getEntitiesInAABBexcluding(this, getBoundingBox().expand(getMotion().getX(), getMotion().getY(), getMotion().getZ()).grow(1), Util.Predicates.STAND_PUNCH_TARGET);
        double d0 = 0;
        for (Entity entity1 : list) {
            AbstractStandAttackEntity punch = null;
            if (entity1 instanceof AbstractStandAttackEntity)
                punch = (AbstractStandAttackEntity) entity1;
            if (entity1.canBeCollidedWith() && (entity1 != shootingEntity || entity1 != shootingStand || entity1 != standMaster || (entity1 instanceof AbstractStandAttackEntity && punch != null && punch.shootingEntity != shootingEntity) || ticksInAir >= getRange())) {
                RayTraceResult raytraceresult = new EntityRayTraceResult(entity1);
                if (raytraceresult != null) {
                    double d1 = start.squareDistanceTo(raytraceresult.getHitVec());
                    if (d1 < d0 || d0 == 0) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
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
    protected void registerData() {
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

}