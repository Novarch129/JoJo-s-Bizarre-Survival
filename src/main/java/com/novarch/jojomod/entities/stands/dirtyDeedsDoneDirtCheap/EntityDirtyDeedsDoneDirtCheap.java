package com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.events.EventD4CTeleportProcessor;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.DimensionHopTeleporter;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityDirtyDeedsDoneDirtCheap extends EntityStandBase
{
	  @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":d4c") public static EntityType<EntityDirtyDeedsDoneDirtCheap> TYPE;

	  private int oratick = 0;

	  private int oratickr = 0;

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

	public EntityDirtyDeedsDoneDirtCheap(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_D4C.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.dirtyDeedsDoneDirtCheap;
	}

	public EntityDirtyDeedsDoneDirtCheap(World world)
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_D4C.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.dirtyDeedsDoneDirtCheap;
	}

	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F;
	    if (getMaster() != null)
	    {
	    	PlayerEntity player = getMaster();
	    	LazyOptional<IStand> power = this.getMaster().getCapability(JojoProvider.STAND, null);
	      	IStand props = power.orElse(new StandCapability(player));

			//Cooldown handler
			if (props.getCooldown() > 0)
				props.subtractCooldown(1);

	      	player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));

			if(player.world.getBlockState(new BlockPos(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ() - 1)).getMaterial() != Material.AIR && player.world.getBlockState(new BlockPos(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ() + 1)).getMaterial() != Material.AIR)
			{
				if (player.isCrouching() || player.isAirBorne)
				{
					if (props.getAbility() && props.getCooldown() <= 0)
					{
						if (player.world.getDimension().getType() == DimensionType.OVERWORLD)
						{
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.OVERWORLD);
						} else if (player.world.getDimension().getType() == DimensionType.THE_NETHER) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_NETHER);
						} else if (player.world.getDimension().getType() == DimensionType.THE_END) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_END);
						}

						this.world.getServer().getWorld(this.dimension).getEntities().forEach(entity -> {
							if (!(entity instanceof PlayerEntity))
								transportEntity(entity);
						});

						for (PlayerEntity playerEntity : this.world.getPlayers()) {
							if (playerEntity.getDistance(player) < 2.0f)
								transportPlayer(playerEntity);
						}

						player.getFoodStats().addStats(-3, 0.0f);
						props.setStandOn(false);
						props.setCooldown(200);
						this.remove();
					}
				}
			} else if(player.world.getBlockState(new BlockPos(player.getPosition().getX() - 1, player.getPosition().getY(), player.getPosition().getZ())).getMaterial() != Material.AIR && player.world.getBlockState(new BlockPos(player.getPosition().getX() + 1, player.getPosition().getY(), player.getPosition().getZ())).getMaterial() != Material.AIR) {
				if (player.isCrouching() || player.isAirBorne)
				{
					if (props.getAbility() && props.getCooldown() <= 0)
					{
						if (player.world.getDimension().getType() == DimensionType.OVERWORLD)
						{
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.OVERWORLD);
						} else if (player.world.getDimension().getType() == DimensionType.THE_NETHER) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_NETHER);
						} else if (player.world.getDimension().getType() == DimensionType.THE_END) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END));
						} else if (player.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END)) {
							EventD4CTeleportProcessor.d4cPassengerList.add(player);
							EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_END);
						}

						this.world.getServer().getWorld(this.dimension).getEntities().forEach(entity -> {
							if (!(entity instanceof PlayerEntity))
								transportEntity(entity);
						});

						for (PlayerEntity playerEntity : this.world.getPlayers()) {
							if (playerEntity.getDistance(player) < 2.0f)
								transportPlayer(playerEntity);
						}

						player.getFoodStats().addStats(-3, 0.0f);
						props.setStandOn(false);
						props.setCooldown(200);
						this.remove();
					}
				}
			}
	      if (this.standOn)
	      {
	        followMaster();
	        setRotationYawHead(player.rotationYaw);
	        setRotation(player.rotationYaw, player.rotationPitch);

	        //Orarush food check
	        if (!player.isAlive())
	          remove();
	        if (player instanceof PlayerEntity) {
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
								if (!player.isCreative())
									player.getFoodStats().addStats(-1, 0.0F);
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
							EntityStandPunch.dirtyDeedsDoneDirtCheap dirtyDeedsDoneDirtCheap = new EntityStandPunch.dirtyDeedsDoneDirtCheap(this.world, this, player);
							dirtyDeedsDoneDirtCheap.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) dirtyDeedsDoneDirtCheap);
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
							EntityStandPunch.dirtyDeedsDoneDirtCheap dirtyDeedsDoneDirtCheap1 = new EntityStandPunch.dirtyDeedsDoneDirtCheap(this.world, this, player);
							dirtyDeedsDoneDirtCheap1.setRandomPositions();
							dirtyDeedsDoneDirtCheap1.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) dirtyDeedsDoneDirtCheap1);
							EntityStandPunch.dirtyDeedsDoneDirtCheap dirtyDeedsDoneDirtCheap2 = new EntityStandPunch.dirtyDeedsDoneDirtCheap(this.world, this, player);
							dirtyDeedsDoneDirtCheap2.setRandomPositions();
							dirtyDeedsDoneDirtCheap2.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) dirtyDeedsDoneDirtCheap2);
						}
					if (this.oratickr >= 80) {
						this.orarush = false;
						this.oratickr = 0;
					}
				}
	        }
	      }
	    }
	  }

	  private void changeDimension(DimensionType type, Entity entity)
	  {
		  entity.changeDimension(type, new DimensionHopTeleporter((ServerWorld) entity.getEntityWorld(), entity.getPosX(), entity.getPosY(), entity.getPosZ()));
	  }

	  private void transportEntity(Entity entity)
	  {
		  if(!(entity instanceof EntityStandBase) && !(entity instanceof PlayerEntity))
		  {
			  if(entity.getDistance(this) < 2.0f)
			  {
				  if(entity.world.getDimension().getType() == DimensionType.OVERWORLD)
					  changeDimension(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE), entity);
				  else if(entity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE))
					  changeDimension(DimensionType.OVERWORLD, entity);
				  else if(entity.world.getDimension().getType() == DimensionType.THE_NETHER)
					  changeDimension(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER), entity);
				  else if(entity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER))
					  changeDimension(DimensionType.THE_NETHER, entity);
				  else if(entity.world.getDimension().getType() == DimensionType.THE_END)
					  changeDimension(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END), entity);
				  else if(entity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END))
					  changeDimension(DimensionType.THE_END, entity);
			  }
		  }
	  }

	private void transportPlayer(PlayerEntity playerEntity)
	{
		if(playerEntity.world.getDimension().getType() == DimensionType.OVERWORLD) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE));
		}
		else if(playerEntity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE)) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.OVERWORLD);
		}
		else if(playerEntity.world.getDimension().getType() == DimensionType.THE_NETHER) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER));
		}
		else if(playerEntity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_NETHER)) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_NETHER);
		}
		else if(playerEntity.world.getDimension().getType() == DimensionType.THE_END) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END));
		}
		else if(playerEntity.world.getDimension().getType() == DimensionType.byName(JojoBizarreSurvival.D4C_DIMENSION_TYPE_END)) {
			EventD4CTeleportProcessor.d4cPassengerList.add(playerEntity);
			EventD4CTeleportProcessor.d4cDestinationList.add(DimensionType.THE_END);
		}
	}

	  @Override
	  public boolean isEntityInsideOpaqueBlock()
	  {
	  	return false;
	  }

	@Override
	public boolean canBeCollidedWith()
	{
		return super.canBeCollidedWith();
	}
}
