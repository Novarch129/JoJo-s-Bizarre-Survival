package com.novarch.jojomod.entities.stands.goldExperienceRequiem;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.StandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityGoldExperienceRequiem extends EntityStandBase
{
	  @ObjectHolder(StevesBizarreSurvival.MOD_ID + ":gold_experience_requiem") public static EntityType<EntityGoldExperienceRequiem> TYPE;

	  private int oratick = 0;

	  private int oratickr = 0;

	  private String truthname = "You will never reach the truth.";

	  @Override
	  public boolean canDespawn(double distanceToClosestPlayer) { return false; }

	  @Override
	  public boolean isAIDisabled()
	  {
	    return false;
	  }

	  @Override
	  public void readAdditional(CompoundNBT nbttagcompound)
	  {
	    super.readAdditional(nbttagcompound);
	  }

	  @Override
	  public void writeAdditional(CompoundNBT nbttagcompound)
	  {
	    super.writeAdditional(nbttagcompound);
	  }

	  @Override
	  public IPacket<?> createSpawnPacket()
	{
		return super.createSpawnPacket();
	}

	public EntityGoldExperienceRequiem(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_GER.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.GER;
	}

	public EntityGoldExperienceRequiem(World world)
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_GER.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.GER;
	}
	
	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F;

	    if (getMaster() != null)
	    {
			PlayerEntity player = getMaster();
			LazyOptional<IStand> power = this.getMaster().getCapability(JojoProvider.STAND, null);
			IStand props = power.orElse(new StandCapability());
			this.ger = props.getAbility();

			//Cooldown handler
			if (props.getTransformed() > 1)
			{
				props.subtractCooldown(1);
			}
			if (props.getCooldown() <= 0) {
				props.setTransformed(0);
				props.setCooldown(220);
			}

			//player.setInvulnerable(true);
			//player.setHealth(20.0f);
			player.getFoodStats().addStats(20, 20.0f);

			//Gold Experience Requiem's ability
			if(this.ger)
			{
				if(player.getLastAttackedEntity() != null)
				{
					if(player.getLastAttackedEntity() instanceof PlayerEntity)
					{
						props.setDiavolo(player.getLastAttackedEntity().getDisplayName().toString());
					}
				}
				/*for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(4000.0, 2000.0 , 4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity instanceof MobEntity)
						{
							if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
							{
								((MobEntity) entity).setAttackTarget((LivingEntity) entity);
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-4000.0, 2000.0 , -4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-4000.0, 2000.0 , 4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(4000.0, 2000.0 , -4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(4000.0, -2000.0 , 4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-4000.0, -2000.0 , -4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-4000.0, -2000.0 , 4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				for (Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(4000.0, -2000.0 , -4000.0)), EntityPredicates.NOT_SPECTATING))
				{
					LazyOptional<IStand> pwr = entity.getCapability(JojoProvider.STAND);
					IStand prs = pwr.orElse(new StandCapability());
					if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
					{
						if(entity != null && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity)
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget((LivingEntity) entity);
								}
							}
						}
					}
				}*/
				for(PlayerEntity playerEntity : this.world.getPlayers())
				{
					if(playerEntity != null)
					{
						if(playerEntity != this.getMaster())
						{
							if(playerEntity.getLastAttackedEntity() == this.getMaster())
							{
								props.setDiavolo(playerEntity.getDisplayName().toString());
								/*if(playerEntity.isAlive())
								{
									CreeperEntity truth = new CreeperEntity(EntityType.CREEPER, playerEntity.world);
									truth.setCustomName(new TranslationTextComponent("You will never reach the truth.", new Object[0]));
									truth.setPosition(playerEntity.getPosX(), playerEntity.getPosY(), player.getPosZ());
									truth.setAttackTarget(playerEntity);
									playerEntity.world.addEntity(truth);
								}*/
							}
						}
					}
				}

				if(props.getDiavolo() != null && !props.getDiavolo().equals(""))
				{
					for(PlayerEntity playerEntity : this.world.getPlayers())
					{
						if(playerEntity != null)
						{
							if(playerEntity != this.getMaster())
							{
								if(playerEntity.getDisplayName().toString().equals(props.getDiavolo()))
								{
									if(playerEntity.isAlive())
									{
										CreeperEntity truth = new CreeperEntity(EntityType.CREEPER, playerEntity.world);
										truth.setCustomName(new TranslationTextComponent(truthname, new Object[0]));
										truth.setPosition(playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ());
										truth.setAttackTarget(playerEntity);
										truth.setDropChance(EquipmentSlotType.MAINHAND, 0.0f);
										playerEntity.world.addEntity(truth);
										truth.setAttackTarget(playerEntity);
									}
								}
							}
						}
					}
				}
			}

			try {
				setHealth(1000.0F);
			} catch (ClassCastException classCastException) {
			}
			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			//Orarush food check
			if (!player.isAlive())
				setDead();
			if (player instanceof PlayerEntity) {
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
								if (!this.world.isRemote)
									this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.MUDAGIORNO.get(), getSoundCategory(), 1.0F, 1.0F);


								if (!player.isCreative())
									player.getFoodStats().addStats(0, 0.0F);
								if (!this.world.isRemote)
									this.orarush = true;
							}
						} else {
							hungerMessage();
						}
				} else if (attackSwing(getMaster())) {
					if (!this.world.isRemote) {
						this.oratick++;
						if (this.oratick == 1) {
							this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (this.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
							EntityStandPunch.goldExperienceRequiem goldExperienceRequiem = new EntityStandPunch.goldExperienceRequiem(this.world, this, player);
							goldExperienceRequiem.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperienceRequiem);
						}
					}
				}
				if (player.swingProgressInt == 0)
					this.oratick = 0;
				if (this.orarush) {
					player.setSprinting(false);
					this.oratickr++;
					if (this.oratickr >= 10)
						if (!this.world.isRemote) {
							player.setSprinting(false);
							EntityStandPunch.goldExperienceRequiem goldExperienceRequiem1 = new EntityStandPunch.goldExperienceRequiem(this.world, this, player);
							goldExperienceRequiem1.setRandomPositions();
							goldExperienceRequiem1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperienceRequiem1);
							EntityStandPunch.goldExperienceRequiem goldExperienceRequiem2 = new EntityStandPunch.goldExperienceRequiem(this.world, this, player);
							goldExperienceRequiem2.setRandomPositions();
							goldExperienceRequiem2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperienceRequiem2);
						}
					if (this.oratickr >= 110) {
						this.orarush = false;
						this.oratickr = 0;
					}
				}
			}
		}
	    }

	  public boolean isEntityInsideOpaqueBlock()
	  {
	  	return false;
	  }

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}
}
