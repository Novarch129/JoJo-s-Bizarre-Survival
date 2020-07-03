package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
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
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@SuppressWarnings({"ConstantConditions", "unused", "UnusedAssignment"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class AbstractStandPunchEntity extends Entity implements IProjectile, IEntityAdditionalSpawnData {
  public int xTile;

  public int yTile;

  public int zTile;

  private Block inTile;

  private int inData;

  protected boolean inGround;

  protected int timeInGround;

  public int arrowShake;

  public Entity shootingEntity;

  public AbstractStandEntity shootingStand;

  public PlayerEntity standMaster;

  private int ticksInGround;

  private int ticksInAir;

  private int standID;

  private int longTick = 3;

  public AbstractStandPunchEntity(EntityType<? extends Entity> type, World worldIn) {
    super(type, worldIn);
    xTile = -1;
    yTile = -1;
    zTile = -1;
    if (worldIn.isRemote)
      setNoGravity(true);
  }

  public AbstractStandPunchEntity(EntityType<? extends Entity> type, World worldIn, double x, double y, double z) {
    this(type, worldIn);
    setPosition(x, y, z);
  }

  public AbstractStandPunchEntity(EntityType<? extends Entity> type, World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
    this(type, worldIn, shooter.getPosX(), shooter.getPosY() + (shooter.getMaster()).getEyeHeight(), shooter.getPosZ());
    shootingEntity = shooter;
    shootingStand = shooter;
    standID = shooter.standID;
    standMaster = player;
    longTick = 2;
    movePunchInFront(shooter);
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
    ticksInGround = 0;
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
    PlayerEntity playerEntity = base.getMaster();
    Vec3d vector = playerEntity.getLookVec();
    double x = getPosX() + vector.x * 0.5d;
    double y = getPosY() + vector.y * 0.4d * 0.5d;
    double z = getPosZ() + vector.z * 0.5d;
    setPosition(x, y, z);
  }

  @Override
  public void setVelocity(double x, double y, double z) {
    setMotion(x, y, z);
    if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt(x * x + z * z);
      rotationPitch = (float) (MathHelper.atan2(y, f) * (double) (180F / (float) Math.PI));
      rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
      prevRotationPitch = rotationPitch;
      prevRotationYaw = rotationYaw;
      setLocationAndAngles(getPosX(), getPosY(), getPosZ(), rotationYaw, rotationPitch);
      ticksInGround = 0;
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
    baseTick();
    BlockPos blockPos = new BlockPos(xTile, yTile, zTile);
    BlockState blockState = world.getBlockState(blockPos);
    if (blockState.getMaterial() != Material.AIR && !blockState.getCollisionShape(world, blockPos).isEmpty()) {
      AxisAlignedBB axisalignedbb = blockState.getCollisionShape(world, blockPos).getBoundingBox();
      if (axisalignedbb.offset(blockPos).contains(new Vec3d(getPosX(), getPosY(), getPosZ())))
        inGround = true;
    }
    if (arrowShake > 0)
      arrowShake--;
    if (!inGround) {
      timeInGround = 0;
      ticksInAir++;
      if (ticksInAir >= longTick)
        if (!world.isRemote)
          remove();
      Vec3d vec3d1 = new Vec3d(getPosX(), getPosY(), getPosZ());
      Vec3d vec3d = new Vec3d(getPosX() + getMotion().x, getPosY() + getMotion().y, getPosZ() + getMotion().z);
      BlockRayTraceResult raytraceresult = world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
      EntityRayTraceResult entityRayTraceResult = null;
      vec3d1 = new Vec3d(getPosX(), getPosY(), getPosZ());
      vec3d = new Vec3d(getPosX() + getMotion().x, getPosY() + getMotion().y, getPosZ() + getMotion().z);
      if (raytraceresult != null)
        vec3d = new Vec3d(raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
      Entity entity = findEntityOnPath(vec3d1);
      if (entity != null) {
        entityRayTraceResult = new EntityRayTraceResult(entity);
        if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof AbstractStandPunchEntity) {
          AbstractStandPunchEntity punch = (AbstractStandPunchEntity) entityRayTraceResult.getEntity();
          if (punch.shootingEntity == shootingEntity)
            raytraceresult = null;
        }
        if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof PlayerEntity) {
          PlayerEntity playerEntity = (PlayerEntity) entityRayTraceResult.getEntity();
          if (playerEntity.isEntityEqual(standMaster)) {
            raytraceresult = null;
          } else {
            entityRayTraceResult = new EntityRayTraceResult(playerEntity);
          }
        }
        if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof AbstractStandEntity) {
          AbstractStandEntity stand = (AbstractStandEntity) entityRayTraceResult.getEntity();
          if (stand.isEntityEqual(shootingStand))
            entityRayTraceResult = null;
        }
      }
      if (entityRayTraceResult != null && !ForgeEventFactory.onProjectileImpact(this, entityRayTraceResult)) {
        onHit(entityRayTraceResult);
      }
      if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
        onHit(raytraceresult);
      }
      setPosition(getPosX() + getMotion().getX(), getPosY() + getMotion().getY(), getPosZ() + getMotion().getZ());
      rotationYaw = (float) (MathHelper.atan2(getMotion().x, getMotion().z) * 57.29577951308232d);
      while (rotationPitch - prevRotationPitch >= 180.0f)
        prevRotationPitch += 360.0f;
      while (rotationYaw - prevRotationYaw < -180.0f)
        prevRotationYaw -= 360.0f;
      while (rotationYaw - prevRotationYaw >= 180.0f)
        prevRotationYaw += 360.0f;
      rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;
      rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;
      float f1 = 0.99f;
      if (isInWater()) {
        for (int i = 0; i < 4; i++) {
          world.addParticle(ParticleTypes.BUBBLE, getPosX() - getMotion().x * 0.25d, getPosY() - getMotion().y * 0.25d, getPosZ() - getMotion().z * 0.25d, getMotion().x, getMotion().y, getMotion().z);
        }
        f1 = 0.8f;
      }
      if (isWet())
        extinguish();
      setMotion(getMotion().getX() * f1, getMotion().getY() * f1, getMotion().getZ() * f1);
      if (!hasNoGravity())
        setMotion(getMotion().getX(), getMotion().getY() - 0.05000000074505806d, getMotion().getZ());
      setPosition(getPosX(), getPosY(), getPosZ());
      doBlockCollisions();
    }
  }

  private void onHit(RayTraceResult result) {
    if (result.getType() == RayTraceResult.Type.ENTITY) {
      Entity entity = ((EntityRayTraceResult) result).getEntity();
      if (entity != standMaster && entity != this) {
        DamageSource damagesource;
        if (shootingStand == null) {
          damagesource = DamageSource.causeThrownDamage(this, this);
        } else if (standMaster == null) {
          damagesource = DamageSource.causeThrownDamage(this, shootingStand);
        } else {
          damagesource = DamageSource.causeThrownDamage(this, standMaster);
        }
        if (isBurning() && !(entity instanceof net.minecraft.entity.monster.EndermanEntity))
          entity.setFire(5);
        if (entity.attackEntityFrom(damagesource, 0.1f)) {
          if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            if (!world.isRemote) {
              world.addParticle(ParticleTypes.EXPLOSION, getPosX(), getPosY(), getPosZ(), 1.0d, 0.0d, 0.0d);
              if(!world.isRemote)
                StandPunchEffects.getStandSpecific(result, livingEntity, this, true, standID);
            }
            if (standMaster != null && livingEntity != standMaster && livingEntity instanceof PlayerEntity && standMaster instanceof ServerPlayerEntity)
              ((ServerPlayerEntity) standMaster).connection.sendPacket(new SChangeGameStatePacket(6, 0.0f));
          }
          if (!(entity instanceof net.minecraft.entity.monster.EndermanEntity))
            remove();
        } else if (!world.isRemote && getMotion().x * getMotion().x + getMotion().y * getMotion().y + getMotion().z * getMotion().z < 0.0010000000474974513d) {
          remove();
        }
      }
    } else if (result.getType() == RayTraceResult.Type.BLOCK) {
      BlockPos pos = ((BlockRayTraceResult) result).getPos();
      BlockState state = world.getBlockState(pos);
      setXYZ(pos);
      inTile = state.getBlock();
      setMotion((float) (result.getHitVec().x - getPosX()), (float) (result.getHitVec().y - getPosY()), (float) (result.getHitVec().z - getPosZ()));
      float f2 = MathHelper.sqrt(getMotion().getX() * getMotion().getX() + getMotion().getY() * getMotion().getY() + getMotion().getZ() * getMotion().getZ());
      setPosition(getPosX() - getMotion().getX() / f2 * 0.05000000074505806d, getPosY() - getMotion().getY() / f2 * 0.05000000074505806d, getPosZ() - getMotion().getZ() / f2 * 0.05000000074505806d);
      inGround = true;
      arrowShake = 7;
      if (!world.isRemote) {
        StandPunchEffects.getStandSpecific(result, null, this, false, standID);
        if (state.getMaterial() != Material.AIR) {
          inTile.onProjectileCollision(world, state, (BlockRayTraceResult) result, this);
          remove();
        }
      }
    }
  }

  public Block getInTile() {
    return inTile;
  }

  public int getXTile() {
    return xTile;
  }

  public int getYTile() {
    return yTile;
  }

  public int getZTile() {
    return zTile;
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
    List<Entity> list = world.getEntitiesInAABBexcluding(this, getBoundingBox().expand(getMotion().x, getMotion().y, getMotion().z).grow(1.0d), Util.Predicates.STAND_PUNCH_TARGET);
    double d0 = 0.0d;
    for (Entity entity1 : list) {
      AbstractStandPunchEntity punch = null;
      if (entity1 instanceof AbstractStandPunchEntity)
        punch = (AbstractStandPunchEntity) entity1;
      if (entity1.canBeCollidedWith() && (entity1 != shootingEntity || entity1 != shootingStand || entity1 != standMaster || (entity1 instanceof AbstractStandPunchEntity && punch != null && punch.shootingEntity != shootingEntity) || ticksInAir >= 6)) {
        RayTraceResult raytraceresult = new EntityRayTraceResult(entity1);
        if (raytraceresult != null) {
          double d1 = start.squareDistanceTo(raytraceresult.getHitVec());
          if (d1 < d0 || d0 == 0.0d) {
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
    if (!world.isRemote && inGround && arrowShake <= 0 && entityIn != standMaster)
      remove();
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
    buffer.writeInt(shootingStand.getEntityId());
    buffer.writeInt(standMaster.getEntityId());
  }

  @Override
  public void readSpawnData(PacketBuffer additionalData) {
    shootingStand = (AbstractStandEntity) world.getEntityByID(additionalData.readInt());
    standMaster = (PlayerEntity) world.getEntityByID(additionalData.readInt());
  }

  public static class KingCrimson extends AbstractStandPunchEntity {
    public KingCrimson(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public KingCrimson(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.KING_CRIMSON_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class DirtyDeedsDoneDirtCheap extends AbstractStandPunchEntity {
    public DirtyDeedsDoneDirtCheap(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public DirtyDeedsDoneDirtCheap(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.D4C_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class MadeInHeaven extends AbstractStandPunchEntity {
    public MadeInHeaven(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public MadeInHeaven(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.MADE_IN_HEAVEN_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class GoldExperience extends AbstractStandPunchEntity {
    public GoldExperience(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public GoldExperience(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.GOLD_EXPERIENCE_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    public void tick() {
      super.tick();
      if (ticksExisted > 5)
        remove();
      if (getMotion().getX() == 0 || getMotion().getY() == 0 || getMotion().getZ() == 0)
        remove();
    }
  }

  public static class GoldExperienceRequiem extends AbstractStandPunchEntity {
    public GoldExperienceRequiem(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public GoldExperienceRequiem(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.GOLD_EXPERIENCE_REQUIEM_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    public void tick() {
      super.tick();
      if (ticksExisted > 5)
        remove();
      if (getMotion().getX() == 0 || getMotion().getY() == 0 || getMotion().getZ() == 0)
        remove();
    }
  }

  public static class Aerosmith extends AbstractStandPunchEntity {
    public Aerosmith(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.AEROSMITH_BULLET.get(), worldIn, shooter, player);
    }

    public Aerosmith(EntityType<Aerosmith> aerosmithEntityType, World world) {
      super(aerosmithEntityType, world);
    }
  }

  public static class WeatherReport extends AbstractStandPunchEntity {
    private boolean lightning;

    public boolean isLightning() {
      return lightning;
    }

    public void setLightning(boolean lightning) {
      this.lightning = lightning;
    }

    public WeatherReport(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.WEATHER_REPORT_PUNCH.get(), worldIn, shooter, player);
    }

    public WeatherReport(EntityType<WeatherReport> weatherReportEntityType, World world) {
      super(weatherReportEntityType, world);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
      super.writeSpawnData(buffer);
      buffer.writeBoolean(lightning);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
      super.readSpawnData(additionalData);
      lightning = additionalData.readBoolean();
    }
  }

  public static class KillerQueen extends AbstractStandPunchEntity {
    public KillerQueen(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public KillerQueen(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.KILLER_QUEEN_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class CrazyDiamond extends AbstractStandPunchEntity {
    public CrazyDiamond(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public CrazyDiamond(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.CRAZY_DIAMOND_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class PurpleHaze extends AbstractStandPunchEntity {
    public PurpleHaze(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public PurpleHaze(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.PURPLE_HAZE_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class Whitesnake extends AbstractStandPunchEntity {
    public Whitesnake(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public Whitesnake(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.WHITESNAKE_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class CMoon extends AbstractStandPunchEntity {
    public CMoon(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public CMoon(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.CMOON_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class TheWorld extends AbstractStandPunchEntity {
    public TheWorld(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public TheWorld(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.THE_WORLD_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class StarPlatinum extends AbstractStandPunchEntity {
    public StarPlatinum(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public StarPlatinum(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.STAR_PLATINUM_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class SilverChariot extends AbstractStandPunchEntity {
    public SilverChariot(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public SilverChariot(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.SILVER_CHARIOT_SWORD.get(), worldIn, shooter, player);
    }
  }

  public static class MagiciansRed extends AbstractStandPunchEntity {
    private boolean explosive;

    public void setExplosive(boolean explosive) {
      this.explosive = explosive;
    }

    public boolean isExplosive() {
      return explosive;
    }

    public MagiciansRed(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public MagiciansRed(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.MAGICIANS_RED_FLAMES.get(), worldIn, shooter, player);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
      super.writeSpawnData(buffer);
      buffer.writeBoolean(isExplosive());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
      super.readSpawnData(additionalData);
      setExplosive(additionalData.readBoolean());
    }
  }

  public static class TheHand extends AbstractStandPunchEntity {
    public TheHand(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
      super(type, worldIn);
    }

    public TheHand(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
      super(EntityInit.THE_HAND_PUNCH.get(), worldIn, shooter, player);
    }
  }
}