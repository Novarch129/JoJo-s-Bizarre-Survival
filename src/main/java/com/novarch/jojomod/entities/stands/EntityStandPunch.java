package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public abstract class EntityStandPunch extends Entity implements IProjectile
{
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":king_crimson_punch") public static EntityType<kingCrimson> KING_CRIMSON;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":d4c_punch") public static EntityType<EntityStandPunch.dirtyDeedsDoneDirtCheap> D4C;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":gold_experience_punch") public static EntityType<EntityStandPunch.goldExperience> GOLD_EXPERIENCE;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":made_in_heaven_punch") public static EntityType<EntityStandPunch.madeInHeaven> MADE_IN_HEAVEN;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":gold_experience_requiem_punch") public static EntityType<EntityStandPunch.goldExperienceRequiem> GOLD_EXPERIENCE_REQUIEM;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":aerosmith_bullet") public static EntityType<EntityStandPunch.aerosmith> AEROSMITH;
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":weather_report_punch") public static EntityType<EntityStandPunch.aerosmith> WEATHER_REPORT;

public int xTile;

public int yTile;

public int zTile;

private Block inTile;

private int inData;

protected boolean inGround;

protected int timeInGround;

public PickupStatus pickupStatus;

public int arrowShake;

public Entity shootingEntity;

public EntityStandBase shootingStand;

public PlayerEntity standMaster;

private int ticksInGround;

private int ticksInAir;

private double damage;

@SuppressWarnings("unused")
private int knockbackStrength;

private int standID;

private int longTick = 3;

public boolean onFire = false;

public EntityStandPunch(EntityType<? extends Entity> type, World worldIn) {
  super(type, worldIn);
  this.xTile = -1;
  this.yTile = -1;
  this.zTile = -1;
  this.pickupStatus = PickupStatus.DISALLOWED;
  this.damage = 2.0D;
  if (worldIn.isRemote);
  setNoGravity(true);
}

public EntityStandPunch(EntityType<? extends Entity> type, World worldIn, double x, double y, double z) {
  this(type, worldIn);
  setPosition(x, y, z);
}

public EntityStandPunch(EntityType<? extends Entity> type, World worldIn, EntityStandBase shooter, PlayerEntity player) {
  this(type, worldIn, shooter.getPosX(), shooter.getPosY() + (shooter.getMaster()).getEyeHeight(), shooter.getPosZ());
  this.shootingEntity = (Entity)shooter;
  this.shootingStand = shooter;
  this.standID = shooter.standID;
  this.standMaster = player;
  this.longTick = shooter.longTick;
  movePunchInFront(shooter);
}

@OnlyIn(Dist.CLIENT)
public boolean isInRangeToRenderDist(double distance) {
  double d0 = getBoundingBox().getAverageEdgeLength() * 10.0D;
  if (Double.isNaN(d0))
    d0 = 1.0D; 
  d0 = d0 * 64.0D * getRenderDistanceWeight();
  return (distance < d0 * d0);
}

public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
  float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
  float f1 = -MathHelper.sin(pitch * 0.017453292F);
  float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
  shoot(f, f1, f2, velocity, inaccuracy);
  this.setMotion(this.getMotion().getX() + shooter.getMotion().getX(), this.getMotion().getY(), this.getMotion().getZ() + shooter.getMotion().getZ());
  if (!shooter.onGround)
	  this.setMotion(this.getMotion().getX(), this.getMotion().getY() + shooter.getMotion().getY(), this.getMotion().getZ());
}

@Override
public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
  float f = MathHelper.sqrt(x * x + y * y + z * z);
  x /= f;
  y /= f;
  z /= f;
  x += this.rand.nextGaussian() * 0.007499999832361937D;
  y += this.rand.nextGaussian() * 0.007499999832361937D;
  z += this.rand.nextGaussian() * 0.007499999832361937D;
  x *= velocity;
  y *= velocity;
  z *= velocity;
  this.setMotion(x, y, z);
  float f1 = MathHelper.sqrt(x * x + z * z);
  this.rotationYaw = (float)(MathHelper.atan2(x, z) * 57.29577951308232D);
  this.rotationPitch = (float)(MathHelper.atan2(y, f1) * 57.29577951308232D);
  this.prevRotationYaw = this.rotationYaw;
  this.prevRotationPitch = this.rotationPitch;
  this.ticksInGround = 0;
}

@OnlyIn(Dist.CLIENT)
@Override
public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
  setPosition(x, y, z);
  setRotation(yaw, pitch);
}

public void movePunchInFront(EntityStandBase base) {
  PlayerEntity playerEntity = base.getMaster();
  Vec3d vector = playerEntity.getLookVec();
  double x = this.getPosX() + vector.x * 0.5D;
  double y = this.getPosY() + vector.y * 0.4D * 0.5D;
  double z = this.getPosZ() + vector.z * 0.5D;
  setPosition(x, y, z);
}

public void setRandomPositions() {
  EntityStandBase entityStandBase = this.shootingStand;
  Vec3d vector = entityStandBase.getLookVec();
  double randp = this.rand.nextDouble() * 2.0D - 1.0D;
  double randp2 = this.rand.nextDouble() * 2.0D - 1.0D;
  double randp3 = this.rand.nextDouble() * 2.0D - 1.0D;
  double x = this.getPosX() + randp + vector.x * 1.5D;
  double y = this.getPosY() + randp2 + vector.y * 1.5D;
  double z = this.getPosZ() + randp3 + vector.z * 1.5D;
  setPosition(x, y, z);
  setRotation(entityStandBase.rotationYaw, entityStandBase.rotationPitch);
}

@OnlyIn(Dist.CLIENT)
@Override
public void setVelocity(double x, double y, double z) {
  this.setMotion(x, y, z);
  if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
    float f = MathHelper.sqrt(x * x + z * z);
    this.rotationPitch = (float)(MathHelper.atan2(y, f) * 57.29577951308232D);
    this.rotationYaw = (float)(MathHelper.atan2(x, z) * 57.29577951308232D);
    this.prevRotationPitch = this.rotationPitch;
    this.prevRotationYaw = this.rotationYaw;
    setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
    this.ticksInGround = 0;
  } 
}

@Override
public void tick() {
  super.tick();
  if(this.standMaster != null) {
    this.rotateTowards(standMaster.rotationYaw, standMaster.rotationPitch);
    this.setRotation(standMaster.rotationYaw, standMaster.rotationPitch);
  }
  if (this.shootingEntity == null && !this.world.isRemote)
    remove(); 
  baseTick();
  BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
  BlockState BlockState = this.world.getBlockState(blockpos);
  if (BlockState.getMaterial() != Material.AIR && !BlockState.getCollisionShape(this.world, blockpos).isEmpty()) {
    AxisAlignedBB axisalignedbb = BlockState.getCollisionShape(((IBlockReader)this.world), blockpos).getBoundingBox();
    if (axisalignedbb != null && axisalignedbb.offset(blockpos).contains(new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ())))
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
    BlockRayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.OUTLINE, FluidMode.ANY, this));
    EntityRayTraceResult entityRayTraceResult = null;
    vec3d1 = new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ());
    vec3d = new Vec3d(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z);
    if (raytraceresult != null)
      vec3d = new Vec3d(raytraceresult.getHitVec().x, raytraceresult.getHitVec().y, raytraceresult.getHitVec().z); 
    Entity entity = findEntityOnPath(vec3d1, vec3d);
    if (entity != null) {
      entityRayTraceResult = new EntityRayTraceResult(entity);
      if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof EntityStandPunch) {
        EntityStandPunch punch = (EntityStandPunch)entityRayTraceResult.getEntity();
        if (punch.shootingEntity == this.shootingEntity)
          raytraceresult = null; 
      } 
      if (raytraceresult != null && entityRayTraceResult.getEntity() instanceof PlayerEntity) {
        PlayerEntity PlayerEntity = (PlayerEntity)entityRayTraceResult.getEntity();
        if (PlayerEntity.isEntityEqual((Entity)this.standMaster)) {
          raytraceresult = null;
        } else {
          entityRayTraceResult = new EntityRayTraceResult((Entity)PlayerEntity);
        } 
      } 
      if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof EntityStandBase) {
        EntityStandBase stand = (EntityStandBase)entityRayTraceResult.getEntity();
        if (stand.isEntityEqual((Entity)this.shootingStand))
          entityRayTraceResult = null;
      } 
    } 
    if (entityRayTraceResult != null && !ForgeEventFactory.onProjectileImpact(this, entityRayTraceResult)) {
      onHit(entityRayTraceResult);
    }
    if(raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult))
    {
      onHit(raytraceresult);
    }
    //if (getIsCritical())
    this.world.addParticle(ParticleTypes.CRIT, this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z, -this.getMotion().x, -this.getMotion().y + 0.2D, -this.getMotion().z);
    this.setPosition(this.getPosX() + this.getMotion().getX(), this.getPosY() + this.getMotion().getY(), this.getPosZ() + this.getMotion().getZ());
    float f4 = MathHelper.sqrt(this.getMotion().x * this.getMotion().x + this.getMotion().z * this.getMotion().z);
    this.rotationYaw = (float)(MathHelper.atan2(this.getMotion().x, this.getMotion().z) * 57.29577951308232D);
    for (this.rotationPitch = (float)(MathHelper.atan2(this.getMotion().y, f4) * 57.29577951308232D); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
    while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
      this.prevRotationPitch += 360.0F; 
    while (this.rotationYaw - this.prevRotationYaw < -180.0F)
      this.prevRotationYaw -= 360.0F; 
    while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
      this.prevRotationYaw += 360.0F; 
    this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
    this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    float f1 = 0.99F;
    if (isInWater()) {
      for (int i = 0; i < 4; i++) {
        this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() - this.getMotion().x * 0.25D, this.getPosY() - this.getMotion().y * 0.25D, this.getPosZ() - this.getMotion().z * 0.25D, this.getMotion().x, this.getMotion().y, this.getMotion().z);
      } 
      f1 = 0.8F;
    } 
    if (isWet())
      extinguish(); 
    this.setMotion(this.getMotion().getX() * f1, this.getMotion().getY() * f1, this.getMotion().getZ() * f1);
    if (!hasNoGravity())
    	this.setMotion(this.getMotion().getX(), this.getMotion().getY() - 0.05000000074505806D, this.getMotion().getZ());
    setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
    doBlockCollisions();
  } 
}

protected void onHit(EntityRayTraceResult raytraceResultIn) {
  Entity entity = raytraceResultIn.getEntity();
  if (entity != null && entity != this.standMaster) {
    DamageSource damagesource;
    if (this.shootingStand == null) {
      damagesource = DamageSource.causeThrownDamage(this, this);
    } else if (this.standMaster == null) {
      damagesource = DamageSource.causeThrownDamage(this, (Entity)this.shootingStand);
    } else {
      damagesource = DamageSource.causeThrownDamage(this, (Entity)this.standMaster);
    } 
    if (isBurning() && !(entity instanceof net.minecraft.entity.monster.EndermanEntity))
      entity.setFire(5); 
    if (entity.attackEntityFrom(damagesource, 0.1F)) {
      if (entity instanceof LivingEntity) {
        LivingEntity LivingEntity = (LivingEntity)entity;
        if (!this.world.isRemote) {
          this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
          StandPunchEffects.getStandSpecific(raytraceResultIn, LivingEntity, this, true, this.standID, false);
        } 
        arrowHit(LivingEntity);
        if (this.standMaster != null && LivingEntity != this.standMaster && LivingEntity instanceof PlayerEntity && this.standMaster instanceof ServerPlayerEntity)
          ((ServerPlayerEntity)this.standMaster).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F)); 
      } 
      if (!(entity instanceof net.minecraft.entity.monster.EndermanEntity))
        remove(); 
    } else if (!this.world.isRemote && this.getMotion().x * this.getMotion().x + this.getMotion().y * this.getMotion().y + this.getMotion().z * this.getMotion().z < 0.0010000000474974513D) {
        remove(); 
    } 
  }
  }

protected void onHit(BlockRayTraceResult raytraceResultIn)
{
  BlockPos blockpos = raytraceResultIn.getPos();
  this.xTile = blockpos.getX();
  this.yTile = blockpos.getY();
  this.zTile = blockpos.getZ();
  BlockState blockState = this.world.getBlockState(blockpos);
  this.inTile = blockState.getBlock();
  this.inData = Block.getStateId(blockState);
  this.setMotion((float)(raytraceResultIn.getHitVec().x - this.getPosX()), (float)(raytraceResultIn.getHitVec().y - this.getPosY()), (float)(raytraceResultIn.getHitVec().z - this.getPosZ()));
  float f2 = MathHelper.sqrt(this.getMotion().x * this.getMotion().x + this.getMotion().y * this.getMotion().y + this.getMotion().z * this.getMotion().z);
  this.setPosition(this.getPosX() - this.getMotion().x / f2 * 0.05000000074505806D, this.getPosY() - this.getMotion().y / f2 * 0.05000000074505806D, this.getPosZ() - this.getMotion().z / f2 * 0.05000000074505806D);
  this.inGround = true;
  this.arrowShake = 7;
  StandPunchEffects.getStandSpecific(raytraceResultIn, null, this, false, this.standID, false);
  if (blockState.getMaterial() != Material.AIR)
  {
    this.inTile.onProjectileCollision(this.world, blockState, raytraceResultIn, this);
    remove();
  }
}

public Block getinTile() {
  return this.inTile;
}

public int getinData() {
  return this.inData;
}

public int getxTile() {
  return this.xTile;
}

public int getyTile() {
  return this.yTile;
}

public int getzTile() {
  return this.zTile;
}

@Override
public void move(MoverType type, Vec3d pos) {
  super.move(type, pos);
  if (this.inGround) {
    this.xTile = MathHelper.floor(this.getPosX());
    this.yTile = MathHelper.floor(this.getPosY());
    this.zTile = MathHelper.floor(this.getPosZ());
  } 
}

protected void arrowHit(LivingEntity living) {}

@Nullable
protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
  Entity entity = null;
  List<Entity> list = this.world.getEntitiesInAABBexcluding(this, getBoundingBox().expand(this.getMotion().x, this.getMotion().y, this.getMotion().z).grow(1.0D), JojoLibs.Predicates.STAND_PUNCH_TARGET);
  double d0 = 0.0D;
  for (Entity entity1 : list) {
    EntityStandPunch punch = null;
    if (entity1 instanceof EntityStandPunch)
      punch = (EntityStandPunch) entity1;
    if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || entity1 != this.shootingStand || entity1 != this.standMaster || (entity1 instanceof EntityStandPunch && punch != null && punch.shootingEntity != this.shootingEntity) || this.ticksInAir >= 6)) {
      AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow(0.30000001192092896D);
      RayTraceResult raytraceresult = new EntityRayTraceResult(entity1);
      if (raytraceresult != null) {
        double d1 = start.squareDistanceTo(raytraceresult.getHitVec());
        if (d1 < d0 || d0 == 0.0D) {
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
  compound.putShort("life", (short)this.ticksInGround);
  compound.putByte("inData", (byte)this.inData);
  compound.putByte("shake", (byte)this.arrowShake);
  compound.putByte("inGround", (byte)(this.inGround ? 1 : 0));
  compound.putByte("pickup", (byte)this.pickupStatus.ordinal());
  compound.putDouble("damage", this.damage);
  //compound.putBoolean("crit", getIsCritical());
}

@Override
public void readAdditional(CompoundNBT compound) {
  this.xTile = compound.getInt("xTile");
  this.yTile = compound.getInt("yTile");
  this.zTile = compound.getInt("zTile");
  this.ticksInGround = compound.getShort("life");
  this.inData = compound.getByte("inData") & 0xFF;
  this.arrowShake = compound.getByte("shake") & 0xFF;
  this.inGround = (compound.getByte("inGround") == 1);
  if (compound.contains("damage", 99))
    this.damage = compound.getDouble("damage"); 
  if (compound.contains("pickup", 99)) {
    this.pickupStatus = PickupStatus.getByOrdinal(compound.getByte("pickup"));
  } else if (compound.contains("player", 99)) {
    this.pickupStatus = compound.getBoolean("player") ? PickupStatus.ALLOWED : PickupStatus.DISALLOWED;
  } 
  //setIsCritical(compound.getBoolean("crit"));
}

@Override
public void onCollideWithPlayer(PlayerEntity entityIn) {
  if (!this.world.isRemote && this.inGround && this.arrowShake <= 0 && entityIn != this.standMaster)
    remove();
}

@Override
public void applyEntityCollision(Entity entityIn) {
  if (entityIn != this.shootingStand && entityIn != this.standMaster)
    super.applyEntityCollision(entityIn); 
}

@Override
protected boolean canTriggerWalking() {
  return false;
}

public void setDamage(double damageIn) {
  this.damage = damageIn;
}

public double getDamage() {
  return this.damage;
}

public void setKnockbackStrength(int knockbackStrengthIn) {
  this.knockbackStrength = knockbackStrengthIn;
}

@Override
public boolean canBeAttackedWithItem() {
  return false;
}

public enum PickupStatus
{
  DISALLOWED, ALLOWED, CREATIVE_ONLY;
  
  public static PickupStatus getByOrdinal(int ordinal)
  {
    if (ordinal < 0 || ordinal > (values()).length)
      ordinal = 0; 
    return values()[ordinal];
  }
}

 /*public static class crazyDiamond extends EntityStandPunch
 {
	    public crazyDiamond(World worldIn)
	    {
	      super(worldIn);
	    }
	    
	    public crazyDiamond(World worldIn, EntityStandBase shooter, PlayerEntity player)
	    {
	      super(worldIn, shooter, player);
	    }
 }*/
  public static class kingCrimson extends EntityStandPunch
  {
      public kingCrimson(World worldIn)
      {
        super(KING_CRIMSON, worldIn);
      }

      public kingCrimson(EntityType<? extends EntityStandPunch> type, World worldIn)
      {
        super(type, worldIn);
      }

      public kingCrimson(World worldIn, EntityStandBase shooter, PlayerEntity player)
      {
        super(KING_CRIMSON, worldIn, shooter, player);
      }

      @Override
      public IPacket<?> createSpawnPacket()
      {
        return NetworkHooks.getEntitySpawningPacket(this);
      }
  }
  	public static class dirtyDeedsDoneDirtCheap extends EntityStandPunch
  	{
      public dirtyDeedsDoneDirtCheap(World worldIn)
      {
        super(D4C, worldIn);
      }

      public dirtyDeedsDoneDirtCheap(EntityType<? extends EntityStandPunch> type, World worldIn)
      {
        super(type, worldIn);
      }

      public dirtyDeedsDoneDirtCheap(World worldIn, EntityStandBase shooter, PlayerEntity player)
      {
        super(D4C, worldIn, shooter, player);
      }

      @Override
      public IPacket<?> createSpawnPacket()
      {
        return NetworkHooks.getEntitySpawningPacket(this);
      }
  	}
  		
      public static class madeInHeaven extends EntityStandPunch
      {
        public madeInHeaven(World worldIn)
        {
          super(MADE_IN_HEAVEN, worldIn);
        }

        public madeInHeaven(EntityType<? extends EntityStandPunch> type, World worldIn)
        {
          super(type, worldIn);
        }

        public madeInHeaven(World worldIn, EntityStandBase shooter, PlayerEntity player)
        {
          super(MADE_IN_HEAVEN, worldIn, shooter, player);
        }

        @Override
        public IPacket<?> createSpawnPacket()
        {
          return NetworkHooks.getEntitySpawningPacket(this);
        }
  }
      /*public static class weatherReport extends EntityStandPunch
      {
          public weatherReport(World worldIn)
          {
            super(worldIn);
          }
          
          public weatherReport(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }*/
      public static class goldExperience extends EntityStandPunch 
      {
        public goldExperience(World worldIn)
        {
          super(GOLD_EXPERIENCE, worldIn);
        }

        public goldExperience(EntityType<? extends EntityStandPunch> type, World worldIn)
        {
          super(type, worldIn);
        }

        public goldExperience(World worldIn, EntityStandBase shooter, PlayerEntity player)
        {
          super(GOLD_EXPERIENCE, worldIn, shooter, player);
        }

        @Override
        public void tick()
        {
          super.tick();
          if(this.ticksExisted > 5)
            this.remove();
          if(this.getMotion().getX() == 0 || this.getMotion().getY() == 0 || this.getMotion().getZ() == 0)
          {
            this.remove();
          }
        }

        @Override
        public IPacket<?> createSpawnPacket()
        {
          return NetworkHooks.getEntitySpawningPacket(this);
        }
  }
      public static class goldExperienceRequiem extends EntityStandPunch
      {
        public goldExperienceRequiem(World worldIn)
        {
          super(GOLD_EXPERIENCE_REQUIEM, worldIn);
        }

        public goldExperienceRequiem(EntityType<? extends EntityStandPunch> type, World worldIn)
        {
          super(type, worldIn);
        }

        public goldExperienceRequiem(World worldIn, EntityStandBase shooter, PlayerEntity player)
        {
          super(GOLD_EXPERIENCE_REQUIEM, worldIn, shooter, player);
        }

        @Override
        public void tick()
        {
          super.tick();
          if(this.ticksExisted > 5)
            this.remove();
          if(this.getMotion().getX() == 0 || this.getMotion().getY() == 0 || this.getMotion().getZ() == 0)
          {
            this.remove();
          }
        }

        @Override
        public IPacket<?> createSpawnPacket()
        {
          return NetworkHooks.getEntitySpawningPacket(this);
        }
  }
      public static class aerosmith extends EntityStandPunch
      {
        boolean bomb = false;
        private Block inTile;
        private int inData;

        public aerosmith(World worldIn)
        {
          super(AEROSMITH, worldIn);
        }

        public aerosmith(World worldIn, EntityStandBase shooter, PlayerEntity player)
        {
          super(AEROSMITH, worldIn, shooter, player);
          this.bomb=false;
        }

        public aerosmith(World worldIn, EntityStandBase shooter, PlayerEntity player, boolean isBomb)
        {
          super(AEROSMITH, worldIn, shooter, player);
          this.bomb=isBomb;
        }

        public aerosmith(EntityType<aerosmith> aerosmithEntityType, World world)
        {
          super(aerosmithEntityType, world);
        }

        @Override
        protected void onHit(BlockRayTraceResult raytraceResultIn)
        {
          BlockPos blockpos = raytraceResultIn.getPos();
          this.xTile = blockpos.getX();
          this.yTile = blockpos.getY();
          this.zTile = blockpos.getZ();
          BlockState blockState = this.world.getBlockState(blockpos);
          this.inTile = blockState.getBlock();
          this.inData = Block.getStateId(blockState);
          this.setMotion((float)(raytraceResultIn.getHitVec().x - this.getPosX()), (float)(raytraceResultIn.getHitVec().y - this.getPosY()), (float)(raytraceResultIn.getHitVec().z - this.getPosZ()));
          float f2 = MathHelper.sqrt(this.getMotion().x * this.getMotion().x + this.getMotion().y * this.getMotion().y + this.getMotion().z * this.getMotion().z);
          this.setPosition(this.getPosX() - this.getMotion().x / f2 * 0.05000000074505806D, this.getPosY() - this.getMotion().y / f2 * 0.05000000074505806D, this.getPosZ() - this.getMotion().z / f2 * 0.05000000074505806D);
          this.inGround = true;
          this.arrowShake = 7;
          if(bomb) {
            TNTEntity tnt = new TNTEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), this.standMaster);
            tnt.setVelocity(this.getLookVec().getX(), this.getLookVec().getY(), this.getLookVec().getZ());
            this.world.addEntity(tnt);
          }
            else
            StandPunchEffects.getStandSpecific(raytraceResultIn, null, this, false, JojoLibs.StandID.aerosmith, false);
          if (blockState.getMaterial() != Material.AIR)
          {
            this.inTile.onProjectileCollision(this.world, blockState, raytraceResultIn, this);
            remove();
          }
        }
      }

  public static class weatherReport extends EntityStandPunch
  {
    public weatherReport(World worldIn)
    {
      super(WEATHER_REPORT, worldIn);
    }

    public weatherReport(World worldIn, EntityStandBase shooter, PlayerEntity player)
    {
      super(WEATHER_REPORT, worldIn, shooter, player);
    }

    public weatherReport(EntityType<weatherReport> weatherReportEntityType , World world)
    {
      super(weatherReportEntityType, world);
    }
  }
	@Override
	protected void registerData()
	{

	}

	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
