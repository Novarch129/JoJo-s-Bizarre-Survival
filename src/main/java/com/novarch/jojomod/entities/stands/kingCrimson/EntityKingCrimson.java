package com.novarch.jojomod.entities.stands.kingCrimson;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityKingCrimson extends EntityStandBase
{
	  @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":king_crimson") public static EntityType<EntityKingCrimson> TYPE;
	
	  private int oratick = 0;
	  
	  private int oratickr = 0;
	  
	  private int changetick = 0;
	  
	  private boolean timeSkipped;

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

	public EntityKingCrimson(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_KC.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.kingCrimson;
	}
	
	public EntityKingCrimson(World world) 
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_KC.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.kingCrimson;
	}
	
	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F; 
	    if (getMaster() != null)
	    {
	    	//Minecraft.getInstance().setRenderViewEntity(this); //TODO Remember this!
	    	PlayerEntity player = getMaster();
	    	LazyOptional<IStand> power = this.getMaster().getCapability(JojoProvider.STAND, null);
	    	IStand props = power.orElse(new StandCapability(player));
	    	this.timeSkipped = !(props.getCooldown() > 0);

	      player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
	      if (this.standOn)
	      {
	        try 
	        {
	          setHealth(1000.0F);
	        } 
	        catch (ClassCastException classCastException) {}
	        followMaster();
	        setRotationYawHead(player.rotationYaw);
	        setRotation(player.rotationYaw, player.rotationPitch);
	        
	        //King Crimson's Ability
	        if(this.timeSkipped && props.getAbility() && props.getStandOn())
				{
	        	if(props.getTimeLeft()==0) {player.sendMessage(new TranslationTextComponent("Time Skip : ON", new Object[0]));}
	        	if(props.getTimeLeft() > 800)
	        	{
	        		this.getMaster().setInvulnerable(true);
	        		player.addPotionEffect(new EffectInstance(EffectInit.CRIMSON_USER.get(), 10000, 255));
	        		if(!player.isCreative() && !player.isSpectator())
						player.setGameType(GameType.ADVENTURE);
	        		props.subtractTimeLeft(1);
	        		this.world.getServer().getWorld(this.dimension).getEntities().forEach(entity -> {
	        			IStand prs = entity.getCapability(JojoProvider.STAND).orElse(new StandCapability(player));
						if(entity != null && !(entity instanceof EntityKingCrimson) && !(entity instanceof EntityStandPunch.kingCrimson) && !(entity instanceof ItemEntity) && entity.isAlive())
						{
							if(entity instanceof MobEntity && !(entity instanceof PlayerEntity) && !(entity instanceof EntityGoldExperienceRequiem) && !(entity instanceof EntityStandPunch.goldExperienceRequiem))
							{
								if(((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster())
								{
									((MobEntity) entity).setAttackTarget(null);
									((MobEntity) entity).setRevengeTarget(null);
								}
								((MobEntity)entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
							}

							if(entity instanceof PlayerEntity && entity != this.getMaster() && prs.getStandID() != JojoLibs.StandID.GER)
							{
								if(prs.getStandID() == JojoLibs.StandID.kingCrimson && prs.getStandOn() && prs.getAbility() && prs.getTimeLeft() > 800)
									return;
								((PlayerEntity)entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
							}
						}
					});
				}

	        	else
	        	{
	        		if(!this.getMaster().isCreative() && !this.getMaster().isSpectator())
						this.getMaster().setGameType(GameType.SURVIVAL);
	        		this.getMaster().setInvulnerable(false);
	        		this.timeSkipped = false;
	        		player.removePotionEffect(EffectInit.CRIMSON_USER.get());
					props.setCooldown(200);
	        	}
	        }

	        if(!timeSkipped)
			{
				if(props.getCooldown()==200) { player.sendMessage(new TranslationTextComponent("Time Skip : OFF", new Object[0])); }
				if(props.getCooldown() > 0)
				{
					props.subtractCooldown(1);
				}

				if(props.getCooldown() <= 0)
				{
					props.setTimeLeft(1000);
					this.timeSkipped = true;
				}
			}

	        if(!props.getAbility()) {
				if (player.isPotionActive(EffectInit.CRIMSON_USER.get()))
					player.removePotionEffect(EffectInit.CRIMSON_USER.get());
				if(props.getTimeLeft() < 1000)
					props.addTimeLeft(1);
			}

	        //Orarush food check       
	        if (!player.isAlive())
	          setDead(); 
	        if (player instanceof PlayerEntity) {
	        	if(!this.timeSkipped || !props.getAbility()) {
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
								if (!this.world.isRemote)
									this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.LAST_ORA_KC.get(), getSoundCategory(), 6.0F, 1.0F);

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
							EntityStandPunch.kingCrimson kingCrimson = new EntityStandPunch.kingCrimson(this.world, this, player);
							kingCrimson.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) kingCrimson);
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
							EntityStandPunch.kingCrimson kingCrimson1 = new EntityStandPunch.kingCrimson(this.world, this, player);
							kingCrimson1.setRandomPositions();
							kingCrimson1.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) kingCrimson1);
							EntityStandPunch.kingCrimson kingCrimson2 = new EntityStandPunch.kingCrimson(this.world, this, player);
							kingCrimson2.setRandomPositions();
							kingCrimson2.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) kingCrimson2);
							/*for(Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-1000.0, -1000.0 , 1000.0)), EntityPredicates.NOT_SPECTATING))
							{
								if(entity instanceof EntityStandPunch.kingCrimson)
									if(((EntityStandPunch.kingCrimson) entity).standMaster == this.getMaster())
										((EntityStandPunch.kingCrimson) entity).setPositionAndRotation(entity.getPosX(), entity.getPosY(), entity.getPosZ(), this.getMaster().rotationYaw, this.getMaster().rotationPitch);
							}*/
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
	  }


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
