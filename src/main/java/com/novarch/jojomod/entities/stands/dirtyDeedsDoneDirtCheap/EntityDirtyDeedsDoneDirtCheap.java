package com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.helpers.DimensionHopHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityDirtyDeedsDoneDirtCheap extends EntityStandBase
{
	  @ObjectHolder(JojoBlockyAdventure.MOD_ID + ":d4c") public static EntityType<EntityDirtyDeedsDoneDirtCheap> TYPE;
	
	  private int oratick = 0;
	  
	  private int oratickr = 0;
	  
	  private int changetick = 0;
	  
	  private boolean d4c = true;
	  
	  PlayerEntity player = getMaster();

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
	      IStand props = power.orElse(new IStandCapability());
	      this.d4c = props.getCooldown() <= 0;

	      player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
	      if (player.isCrouching()) 
	      {
	        if (playerJump(player) || player.isAirBorne) 
	        {
	          this.changetick++;
	          if (this.changetick == 1) 
	          {
	            this.d4c = !this.d4c;
				  if(player.dimension == DimensionType.OVERWORLD)
				  {
					  /*player.getFoodStats().addStats(-2, 0.0F);
					  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
					  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
					  DimensionHopHelper.teleportToDimension(player, DimensionType.THE_NETHER, player.getPosX(), player.getPosY(), player.getPosZ());
					  player.sendMessage((ITextComponent) new TranslationTextComponent("msg.jojomod.d4c.txt", new Object[0]));*/
					  player.changeDimension(DimensionType.THE_NETHER);
				  }
					  /*case -1:
						  player.getFoodStats().addStats(-2, 0.0F);
						  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
						  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
						  DimensionHopHelper.teleportToDimension(player, 3, player.getPosX(), this.world.getHeight((int) player.getPosX(), (int) player.getPosZ()), player.getPosZ());
						  player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.d4c.txt", new Object[0]));
						  break;
					  case 2:
						  player.getFoodStats().addStats(-2, 0.0F);
						  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
						  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
						  DimensionHopHelper.teleportToDimension(player, 0, player.posX, this.world.getHeight((int)player.posX, (int)player.posZ) + 20, player.posZ);
						  player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.overworld.txt", new Object[0]));
						  break;
					  case 3:
						  player.getFoodStats().addStats(-2, 0.0F);
						  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
						  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
						  DimensionHopHelper.teleportToDimension(player, -1, player.posX, player.posY, player.posZ);
						  player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.overworld.txt", new Object[0]));
						  break;
					  case 1:
						  player.getFoodStats().addStats(-2, 0.0F);
						  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
						  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
						  DimensionHopHelper.teleportToDimension(player, 4, player.posX, player.posY + 60, player.posZ);
						  player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.overworld.txt", new Object[0]));
						  break;
					  case 4:
						  player.getFoodStats().addStats(-2, 0.0F);
						  player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 200));
						  player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 200));
						  DimensionHopHelper.teleportToDimension(player, 1, player.posX, player.posY + 60, player.posZ);
						  player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.overworld.txt", new Object[0]));
						  break;*/
	          }
	        } 
	      } else 
	      {
	        this.changetick = 0;
	      } 
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

	        //Orarush food check       
	        if (!player.isAlive())
	          setDead(); 
	        if (player instanceof PlayerEntity) {
	        	if(!this.d4c) {
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
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
