package com.novarch.jojomod.entities.stands;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.novarch.jojomod.JojoMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

public class EntityStandPunch extends Entity implements IProjectile
{
    @ObjectHolder(JojoMod.MOD_ID + ":king_crimson_punch") public static EntityType<EntityStandPunch.kingCrimson> KING_CRIMSON;
	/*private static final Predicate<Entity> PUNCH_TARGETS = Predicates.and(new Predicate[] { (Predicate) EntityPredicates.NOT_SPECTATING, (Predicate )EntityPredicates.IS_ALIVE, new Predicate<Entity>() {
		@Override
		public boolean apply(Entity input)
		{
			return input.canBeCollidedWith();
		}
	}   });*/
	private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(EntityStandPunch.class, DataSerializers.BYTE);

private int xTile;

private int yTile;

private int zTile;

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

protected void entityInit() {
  this.dataManager.register(CRITICAL, Byte.valueOf((byte)0));
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
  PlayerEntity PlayerEntity = base.getMaster();
  Vec3d vector = PlayerEntity.getLookVec();
  double x = this.getPosX() + vector.x * 0.5D;
  double y = this.getPosY() + vector.y * 0.4D * 0.5D;
  double z = this.getPosZ() + vector.z * 0.5D;
  setPosition(x, y, z);
}

public void setRandomPositions() {
  EntityStandBase EntityStandBase = this.shootingStand;
  Vec3d vector = EntityStandBase.getLookVec();
  double randp = this.rand.nextDouble() * 2.0D - 1.0D;
  double randp2 = this.rand.nextDouble() * 2.0D - 1.0D;
  double randp3 = this.rand.nextDouble() * 2.0D - 1.0D;
  double x = this.getPosX() + randp + vector.x * 1.5D;
  double y = this.getPosY() + randp2 + vector.y * 1.5D;
  double z = this.getPosZ() + randp3 + vector.z * 1.5D;
  setPosition(x, y, z);
  setRotation(((LivingEntity)EntityStandBase).rotationYaw, ((LivingEntity)EntityStandBase).rotationPitch);
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
  if (this.shootingEntity == null && !this.world.isRemote)
    remove(); 
  baseTick();
  BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
  BlockState BlockState = this.world.getBlockState(blockpos);
  if (BlockState.getMaterial() != Material.AIR) {
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
    RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d, BlockMode.COLLIDER, FluidMode.ANY, this));
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

protected void onHit(RayTraceResult raytraceResultIn) {
  Entity entity = ((EntityRayTraceResult)raytraceResultIn).getEntity();
  if (entity != null) {
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
          StandPunchEffects.getStandSpecific(raytraceResultIn, LivingEntity, this, true, this.standID);
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
  } else {
    BlockPos blockpos = ((BlockRayTraceResult)raytraceResultIn).getPos();
    this.xTile = blockpos.getX();
    this.yTile = blockpos.getY();
    this.zTile = blockpos.getZ();
    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0D, 0.0D, 0.0D);
    BlockState BlockState = this.world.getBlockState(blockpos);
    this.inTile = BlockState.getBlock();
    this.inData = Block.getStateId(BlockState);
    this.setMotion((float)(raytraceResultIn.getHitVec().x - this.getPosX()), (float)(raytraceResultIn.getHitVec().y - this.getPosY()), (float)(raytraceResultIn.getHitVec().z - this.getPosZ()));
    float f2 = MathHelper.sqrt(this.getMotion().x * this.getMotion().x + this.getMotion().y * this.getMotion().y + this.getMotion().z * this.getMotion().z);
    this.setPosition(this.getPosX() - this.getMotion().x / f2 * 0.05000000074505806D, this.getPosY() - this.getMotion().y / f2 * 0.05000000074505806D, this.getPosZ() - this.getMotion().z / f2 * 0.05000000074505806D); 
    this.inGround = true;
    this.arrowShake = 7;
    //setIsCritical(false);
    StandPunchEffects.getStandSpecific(raytraceResultIn, null, this, false, this.standID);
    if (BlockState.getMaterial() != Material.AIR) {
      this.inTile.onEntityCollision(BlockState, world, blockpos, (Entity)this);
      remove();
    } 
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
  List<Entity> list = this.world.getEntitiesInAABBexcluding(this, getBoundingBox().expand(this.getMotion().x, this.getMotion().y, this.getMotion().z).grow(1.0D), EntityPredicates.NOT_SPECTATING);
  double d0 = 0.0D;
  for (int i = 0; i < list.size(); i++) {
    Entity entity1 = list.get(i);
    EntityStandPunch punch = null;
    if (entity1 instanceof EntityStandPunch)
      punch = (EntityStandPunch)entity1; 
    if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || entity1 != this.shootingStand || entity1 != this.standMaster || (entity1 instanceof EntityStandPunch && punch != null && punch.shootingEntity != this.shootingEntity) || this.ticksInAir >= 6)) {
      AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow(0.30000001192092896D);
      RayTraceResult raytraceresult =  new EntityRayTraceResult(entity1); // TODO fix
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


    public void setIsCritical(boolean critical)
    {
        byte b0 = (Byte) this.dataManager.get(CRITICAL);
        if (critical)
        {
           this.dataManager.set(CRITICAL, Byte.valueOf((byte)(b0 | 0x1)));
        } else
        {
          this.dataManager.set(CRITICAL, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
        }
    }

        public boolean getIsCritical()
        {
          byte b0 = ((Byte)this.dataManager.get(CRITICAL)).byteValue();
          return ((b0 & 0x1) != 0);
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
        return super.createSpawnPacket();
      }
  }
  	/*public static class dirtyDeedsDoneDirtCheap extends EntityStandPunch
  	{
  		public dirtyDeedsDoneDirtCheap(World worldIn)
  		{
  			super(worldIn);
  		}
      
  		public dirtyDeedsDoneDirtCheap(World worldIn, EntityStandBase shooter, PlayerEntity player)
  		{
  			super(worldIn, shooter, player);
  		}
  	}
  		
      public static class madeInHeaven extends EntityStandPunch 
      {
          public madeInHeaven(World worldIn)
          {
            super(worldIn);
          }
          
          public madeInHeaven(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class weatherReport extends EntityStandPunch 
      {
          public weatherReport(World worldIn)
          {
            super(worldIn);
          }
          
          public weatherReport(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class goldExperience extends EntityStandPunch 
      {
          public goldExperience(World worldIn)
          {
            super(worldIn);
          }
          
          public goldExperience(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class goldExperienceRequiem extends EntityStandPunch 
      {
          public goldExperienceRequiem(World worldIn)
          {
            super(worldIn);
          }
          
          public goldExperienceRequiem(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class theWorld extends EntityStandPunch 
      {
          public theWorld(World worldIn)
          {
            super(worldIn);
          }
          
          public theWorld(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class killerQueen extends EntityStandPunch 
      {
          public killerQueen(World worldIn)
          {
            super(worldIn);
          }
          
          public killerQueen(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class killerQueen8 extends EntityStandPunch 
      {
          public killerQueen8(World worldIn)
          {
            super(worldIn);
          }
          
          public killerQueen8(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }
      public static class stickyFingers extends EntityStandPunch 
      {
          public stickyFingers(World worldIn)
          {
            super(worldIn);
          }
          
          public stickyFingers(World worldIn, EntityStandBase shooter, PlayerEntity player) 
          {
            super(worldIn, shooter, player);
          }
  }*/
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
