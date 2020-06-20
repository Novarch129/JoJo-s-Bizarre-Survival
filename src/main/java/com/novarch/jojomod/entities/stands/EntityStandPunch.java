package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.network.message.server.SSyncMagiciansRedFirePacket;
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
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@SuppressWarnings({"ConstantConditions", "unused", "UnusedAssignment"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class EntityStandPunch extends Entity implements IProjectile, IEntityAdditionalSpawnData {
  public int xTile;

  public int yTile;

  public int zTile;

  private Block inTile;

  private int inData;

  protected boolean inGround;

  protected int timeInGround;

  public int arrowShake;

  public Entity shootingEntity;

  public EntityStandBase shootingStand;

  public PlayerEntity standMaster;

  private int ticksInGround;

  private int ticksInAir;

  private double damage;

  private int standID;

  private int longTick = 3;

  public EntityStandPunch(EntityType<? extends Entity> type, World worldIn) {
    super(type, worldIn);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.damage = 2.0d;
    if (worldIn.isRemote)
      setNoGravity(true);
  }

  public EntityStandPunch(EntityType<? extends Entity> type, World worldIn, double x, double y, double z) {
    this(type, worldIn);
    setPosition(x, y, z);
  }

  public EntityStandPunch(EntityType<? extends Entity> type, World worldIn, EntityStandBase shooter, PlayerEntity player) {
    this(type, worldIn, shooter.getPosX(), shooter.getPosY() + (shooter.getMaster()).getEyeHeight(), shooter.getPosZ());
    this.shootingEntity = shooter;
    this.shootingStand = shooter;
    this.standID = shooter.standID;
    this.standMaster = player;
    this.longTick = shooter.longTick;
    movePunchInFront(shooter);
  }

  private void setXYZ(BlockPos blockPos) {
    xTile = blockPos.getX();
    yTile = blockPos.getY();
    zTile = blockPos.getZ();
  }

  public boolean isInRangeToRenderDist(double distance) {
    double d0 = this.getBoundingBox().getAverageEdgeLength() * 10.0D;
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
    this.shoot(f, f1, f2, velocity, inaccuracy);
    this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
  }

  @Override
  public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
    Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.rand.nextGaussian() * (double) 0.0075F * (double) inaccuracy).scale(velocity);
    this.setMotion(vec3d);
    float f = MathHelper.sqrt(horizontalMag(vec3d));
    this.rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
    this.rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180F / (float) Math.PI));
    this.prevRotationYaw = this.rotationYaw;
    this.prevRotationPitch = this.rotationPitch;
    this.ticksInGround = 0;
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

  public void movePunchInFront(EntityStandBase base) {
    PlayerEntity playerEntity = base.getMaster();
    Vec3d vector = playerEntity.getLookVec();
    double x = this.getPosX() + vector.x * 0.5d;
    double y = this.getPosY() + vector.y * 0.4d * 0.5d;
    double z = this.getPosZ() + vector.z * 0.5d;
    setPosition(x, y, z);
  }

  @Override
  public void setVelocity(double x, double y, double z) {
    this.setMotion(x, y, z);
    if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
      float f = MathHelper.sqrt(x * x + z * z);
      this.rotationPitch = (float) (MathHelper.atan2(y, f) * (double) (180F / (float) Math.PI));
      this.rotationYaw = (float) (MathHelper.atan2(x, z) * (double) (180F / (float) Math.PI));
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
      this.ticksInGround = 0;
    }
  }

  @Override
  public void tick() {
    super.tick();
    if (this.standMaster != null) {
      this.rotateTowards(standMaster.rotationYaw, standMaster.rotationPitch);
      this.setRotation(standMaster.rotationYaw, standMaster.rotationPitch);
    }
    if (this.shootingEntity == null && !this.world.isRemote)
      remove();
    baseTick();
    BlockPos blockPos = new BlockPos(this.xTile, this.yTile, this.zTile);
    BlockState blockState = this.world.getBlockState(blockPos);
    if (blockState.getMaterial() != Material.AIR && !blockState.getCollisionShape(this.world, blockPos).isEmpty()) {
      AxisAlignedBB axisalignedbb = blockState.getCollisionShape(this.world, blockPos).getBoundingBox();
      if (axisalignedbb.offset(blockPos).contains(new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ())))
        this.inGround = true;
    }
    if (this.arrowShake > 0)
      this.arrowShake--;
    if (!this.inGround) {
      this.timeInGround = 0;
      this.ticksInAir++;
      if (this.ticksInAir >= this.longTick)
        if (!this.world.isRemote)
          remove();
      Vec3d vec3d1 = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
      Vec3d vec3d = new Vec3d(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z);
      BlockRayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, this));
      EntityRayTraceResult entityRayTraceResult = null;
      vec3d1 = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
      vec3d = new Vec3d(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z);
      if (raytraceresult != null)
        vec3d = new Vec3d(raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z);
      Entity entity = findEntityOnPath(vec3d1);
      if (entity != null) {
        entityRayTraceResult = new EntityRayTraceResult(entity);
        if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof EntityStandPunch) {
          EntityStandPunch punch = (EntityStandPunch) entityRayTraceResult.getEntity();
          if (punch.shootingEntity == this.shootingEntity)
            raytraceresult = null;
        }
        if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof PlayerEntity) {
          PlayerEntity playerEntity = (PlayerEntity) entityRayTraceResult.getEntity();
          if (playerEntity.isEntityEqual(this.standMaster)) {
            raytraceresult = null;
          } else {
            entityRayTraceResult = new EntityRayTraceResult(playerEntity);
          }
        }
        if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof EntityStandBase) {
          EntityStandBase stand = (EntityStandBase) entityRayTraceResult.getEntity();
          if (stand.isEntityEqual(this.shootingStand))
            entityRayTraceResult = null;
        }
      }
      if (entityRayTraceResult != null && !ForgeEventFactory.onProjectileImpact(this, entityRayTraceResult)) {
        onHit(entityRayTraceResult);
      }
      if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
        onHit(raytraceresult);
      }
      this.setPosition(this.getPosX() + this.getMotion().getX(), this.getPosY() + this.getMotion().getY(), this.getPosZ() + this.getMotion().getZ());
      this.rotationYaw = (float) (MathHelper.atan2(this.getMotion().x, this.getMotion().z) * 57.29577951308232d);
      while (this.rotationPitch - this.prevRotationPitch >= 180.0f)
        this.prevRotationPitch += 360.0f;
      while (this.rotationYaw - this.prevRotationYaw < -180.0f)
        this.prevRotationYaw -= 360.0f;
      while (this.rotationYaw - this.prevRotationYaw >= 180.0f)
        this.prevRotationYaw += 360.0f;
      this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2f;
      this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2f;
      float f1 = 0.99f;
      if (isInWater()) {
        for (int i = 0; i < 4; i++) {
          this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - this.getMotion().x * 0.25d, this.getPosY() - this.getMotion().y * 0.25d, this.getPosZ() - this.getMotion().z * 0.25d, this.getMotion().x, this.getMotion().y, this.getMotion().z);
        }
        f1 = 0.8f;
      }
      if (isWet())
        extinguish();
      this.setMotion(this.getMotion().getX() * f1, this.getMotion().getY() * f1, this.getMotion().getZ() * f1);
      if (!hasNoGravity())
        this.setMotion(this.getMotion().getX(), this.getMotion().getY() - 0.05000000074505806d, this.getMotion().getZ());
      setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
      doBlockCollisions();
    }
  }

  private void onHit(RayTraceResult result) {
    if (result.getType() == RayTraceResult.Type.ENTITY) {
      Entity entity = ((EntityRayTraceResult) result).getEntity();
      if (entity != this.standMaster && entity != this) {
        DamageSource damagesource;
        if (this.shootingStand == null) {
          damagesource = DamageSource.causeThrownDamage(this, this);
        } else if (this.standMaster == null) {
          damagesource = DamageSource.causeThrownDamage(this, this.shootingStand);
        } else {
          damagesource = DamageSource.causeThrownDamage(this, this.standMaster);
        }
        if (isBurning() && !(entity instanceof net.minecraft.entity.monster.EndermanEntity))
          entity.setFire(5);
        if (entity.attackEntityFrom(damagesource, 0.1f)) {
          if (entity instanceof LivingEntity) {
            LivingEntity LivingEntity = (LivingEntity) entity;
            if (!this.world.isRemote) {
              this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0d, 0.0d, 0.0d);
              StandPunchEffects.getStandSpecific(result, LivingEntity, this, true, this.standID);
            }
            if (this.standMaster != null && LivingEntity != this.standMaster && LivingEntity instanceof PlayerEntity && this.standMaster instanceof ServerPlayerEntity)
              ((ServerPlayerEntity) this.standMaster).connection.sendPacket(new SChangeGameStatePacket(6, 0.0f));
          }
          if (!(entity instanceof net.minecraft.entity.monster.EndermanEntity))
            remove();
        } else if (!this.world.isRemote && this.getMotion().x * this.getMotion().x + this.getMotion().y * this.getMotion().y + this.getMotion().z * this.getMotion().z < 0.0010000000474974513d) {
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
      setPosition(this.getPosX() - getMotion().getX() / f2 * 0.05000000074505806d, getPosY() - getMotion().getY() / f2 * 0.05000000074505806d, getPosZ() - getMotion().getZ() / f2 * 0.05000000074505806d);
      inGround = true;
      arrowShake = 7;
      StandPunchEffects.getStandSpecific(result, null, this, false, standID);
      if (state.getMaterial() != Material.AIR) {
        inTile.onProjectileCollision(world, state, (BlockRayTraceResult) result, this);
        remove();
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
    List<Entity> list = this.world.getEntitiesInAABBexcluding(this, getBoundingBox().expand(this.getMotion().x, this.getMotion().y, this.getMotion().z).grow(1.0d), Util.Predicates.STAND_PUNCH_TARGET);
    double d0 = 0.0d;
    for (Entity entity1 : list) {
      EntityStandPunch punch = null;
      if (entity1 instanceof EntityStandPunch)
        punch = (EntityStandPunch) entity1;
      if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || entity1 != this.shootingStand || entity1 != this.standMaster || (entity1 instanceof EntityStandPunch && punch != null && punch.shootingEntity != this.shootingEntity) || this.ticksInAir >= 6)) {
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
    compound.putInt("xTile", this.xTile);
    compound.putInt("yTile", this.yTile);
    compound.putInt("zTile", this.zTile);
    compound.putShort("life", (short) this.ticksInGround);
    compound.putByte("inData", (byte) this.inData);
    compound.putByte("shake", (byte) this.arrowShake);
    compound.putByte("inGround", (byte) (this.inGround ? 1 : 0));
    compound.putDouble("damage", this.damage);
    if(standMaster != null) {
      compound.putInt("standMaster", standMaster.getEntityId());
      LOGGER.debug("written standmaster");
    }
  }

  @Override
  public void readAdditional(CompoundNBT compound) {
    xTile = compound.getInt("xTile");
    yTile = compound.getInt("yTile");
    zTile = compound.getInt("zTile");
    ticksInGround = compound.getShort("life");
    inData = compound.getByte("inData") & 0xFF;
    arrowShake = compound.getByte("shake") & 0xFF;
    inGround = (compound.getByte("inGround") == 1);
    if(compound.contains("damage", 99))
      damage = compound.getDouble("damage");
    if(compound.contains("standMaster"))
      if(world.isRemote)
        standMaster = (PlayerEntity) world.getEntityByID(compound.getInt("standMaster"));
  }

  @Override
  public void onCollideWithPlayer(PlayerEntity entityIn) {
    if (!world.isRemote && this.inGround && arrowShake <= 0 && entityIn != standMaster)
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
    shootingStand = (EntityStandBase) world.getEntityByID(additionalData.readInt());
    standMaster = (PlayerEntity) world.getEntityByID(additionalData.readInt());
  }

  public static class KingCrimson extends EntityStandPunch {
    public KingCrimson(World worldIn) {
      super(EntityInit.KING_CRIMSON_PUNCH.get(), worldIn);
    }

    public KingCrimson(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public KingCrimson(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.KING_CRIMSON_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class DirtyDeedsDoneDirtCheap extends EntityStandPunch {
    public DirtyDeedsDoneDirtCheap(World worldIn) {
      super(EntityInit.D4C_PUNCH.get(), worldIn);
    }

    public DirtyDeedsDoneDirtCheap(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public DirtyDeedsDoneDirtCheap(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.D4C_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class MadeInHeaven extends EntityStandPunch {
    public MadeInHeaven(World worldIn) {
      super(EntityInit.MADE_IN_HEAVEN_PUNCH.get(), worldIn);
    }

    public MadeInHeaven(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public MadeInHeaven(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.MADE_IN_HEAVEN_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class GoldExperience extends EntityStandPunch {
    public GoldExperience(World worldIn) {
      super(EntityInit.GOLD_EXPERIENCE_PUNCH.get(), worldIn);
    }

    public GoldExperience(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public GoldExperience(World worldIn, EntityStandBase shooter, PlayerEntity player) {
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

  public static class GoldExperienceRequiem extends EntityStandPunch {
    public GoldExperienceRequiem(World worldIn) {
      super(EntityInit.GOLD_EXPERIENCE_REQUIEM_PUNCH.get(), worldIn);
    }

    public GoldExperienceRequiem(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public GoldExperienceRequiem(World worldIn, EntityStandBase shooter, PlayerEntity player) {
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

  public static class Aerosmith extends EntityStandPunch {
    public Aerosmith(World worldIn) {
      super(EntityInit.AEROSMITH_BULLET.get(), worldIn);
    }

    public Aerosmith(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.AEROSMITH_BULLET.get(), worldIn, shooter, player);
    }

    public Aerosmith(EntityType<Aerosmith> aerosmithEntityType, World world) {
      super(aerosmithEntityType, world);
    }
  }

  public static class WeatherReport extends EntityStandPunch {
    private boolean lightning;

    public boolean isLightning() {
      return lightning;
    }

    public void setLightning(boolean lightning) {
      this.lightning = lightning;
    }

    public WeatherReport(World worldIn) {
      super(EntityInit.WEATHER_REPORT_PUNCH.get(), worldIn);
    }

    public WeatherReport(World worldIn, EntityStandBase shooter, PlayerEntity player) {
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

  public static class KillerQueen extends EntityStandPunch {
    public KillerQueen(World worldIn) {
      super(EntityInit.KILLER_QUEEN_PUNCH.get(), worldIn);
    }

    public KillerQueen(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public KillerQueen(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.KILLER_QUEEN_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class CrazyDiamond extends EntityStandPunch {
    public CrazyDiamond(World worldIn) {
      super(EntityInit.CRAZY_DIAMOND_PUNCH.get(), worldIn);
    }

    public CrazyDiamond(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public CrazyDiamond(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.CRAZY_DIAMOND_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class PurpleHaze extends EntityStandPunch {
    public PurpleHaze(World worldIn) {
      super(EntityInit.PURPLE_HAZE_PUNCH.get(), worldIn);
    }

    public PurpleHaze(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public PurpleHaze(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.PURPLE_HAZE_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class Whitesnake extends EntityStandPunch {
    public Whitesnake(World worldIn) {
      super(EntityInit.WHITESNAKE_PUNCH.get(), worldIn);
    }

    public Whitesnake(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public Whitesnake(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.WHITESNAKE_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class CMoon extends EntityStandPunch {
    public CMoon(World worldIn) {
      super(EntityInit.CMOON_PUNCH.get(), worldIn);
    }

    public CMoon(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public CMoon(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.CMOON_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class TheWorld extends EntityStandPunch {
    public TheWorld(World worldIn) {
      super(EntityInit.THE_WORLD_PUNCH.get(), worldIn);
    }

    public TheWorld(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public TheWorld(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.THE_WORLD_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class StarPlatinum extends EntityStandPunch {
    public StarPlatinum(World worldIn) {
      super(EntityInit.STAR_PLATINUM_PUNCH.get(), worldIn);
    }

    public StarPlatinum(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public StarPlatinum(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.STAR_PLATINUM_PUNCH.get(), worldIn, shooter, player);
    }
  }

  public static class SilverChariot extends EntityStandPunch {
    public SilverChariot(World worldIn) {
      super(EntityInit.SILVER_CHARIOT_SWORD.get(), worldIn);
    }

    public SilverChariot(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public SilverChariot(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.SILVER_CHARIOT_SWORD.get(), worldIn, shooter, player);
    }
  }

  public static class MagiciansRed extends EntityStandPunch {
    private boolean explosive;

    public void setExplosive(boolean explosive) {
      this.explosive = explosive;
      if(!world.isRemote)
        JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncMagiciansRedFirePacket(getEntityId(), isExplosive()));
    }

    public void putExplosive(boolean explosive) {
      this.explosive = explosive;
    }

    public boolean isExplosive() {
      return explosive;
    }

    public MagiciansRed(World worldIn) {
      super(EntityInit.MAGICIANS_RED_FLAMES.get(), worldIn);
    }

    public MagiciansRed(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public MagiciansRed(World worldIn, EntityStandBase shooter, PlayerEntity player) {
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

  public static class TheHand extends EntityStandPunch {
    public TheHand(World worldIn) {
      super(EntityInit.THE_HAND_PUNCH.get(), worldIn);
    }

    public TheHand(EntityType<? extends EntityStandPunch> type, World worldIn) {
      super(type, worldIn);
    }

    public TheHand(World worldIn, EntityStandBase shooter, PlayerEntity player) {
      super(EntityInit.THE_HAND_PUNCH.get(), worldIn, shooter, player);
    }
  }
}