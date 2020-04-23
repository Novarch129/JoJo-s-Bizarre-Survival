package com.novarch.jojomod.entities.stands.kingCrimson;

import com.google.common.collect.Iterables;
import com.novarch.jojomod.JojoMod;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;

import com.novarch.jojomod.util.capabilities.stand.IStand;
import com.novarch.jojomod.util.capabilities.stand.IStandCapability;
import com.novarch.jojomod.util.capabilities.stand.JojoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityKingCrimson extends EntityStandBase
{
	@ObjectHolder(JojoMod.MOD_ID + ":king_crimson") public static EntityType<EntityKingCrimson> TYPE;
	
	  private int oratick = 0;
	  
	  private int oratickr = 0;
	  
	  private int changetick = 0;
	  
	  private boolean timeSkipped = true;
	  
	  PlayerEntity player = getMaster();

	  int timeleft = 200;
	  
	  protected boolean canDespawn() 
	  {
	    return false;
	  }
	  
	  protected boolean isAIEnabled() 
	  {
	    return true;
	  }
	  
	  public void readEntityFromNBT(CompoundNBT nbttagcompound) 
	  {
	    super.readAdditional(nbttagcompound);
	  }
	  
	  public void writeEntityToNBT(CompoundNBT nbttagcompound) 
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
	    this.spawnSound = SoundInit.SPAWN_KC;
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.kingCrimson;
	}
	
	public EntityKingCrimson(World world) 
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_KC;
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.kingCrimson;
	}
	//TODO add old code
	
	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F; 
	    if (getMaster() != null)
	    {
	      PlayerEntity player = getMaster();
	      LazyOptional<IStand> power = this.getMaster().getCapability(JojoProvider.STAND, null);
	      IStand props = power.orElse(new IStandCapability());

	      player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
	      if (player.isCrouching()) 
	      {
	        if (playerJump(player) || player.isAirBorne) 
	        {
	          this.changetick++;
	          if (this.changetick == 1) 
	          {
	            this.timeSkipped = !this.timeSkipped;
	            if(this.timeSkipped)
	            {   
	            	player.setInvisible(true);
	            	player.sendMessage((ITextComponent)new TranslationTextComponent("Time Skip : ON", new Object[0]));
	            }
	            else
	            {
	            	player.setInvisible(false);
	            	player.sendMessage((ITextComponent)new TranslationTextComponent("Time Skip : OFF", new Object[0]));
	            }
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
	        
	        //King Crimson's Ability
			  //TODO Fix cooldown bugs
	        if(this.timeSkipped)
	        {
				if(JojoMod.debug) {player.sendMessage((ITextComponent)new TranslationTextComponent("Time skipped" + String.valueOf(props.getCooldown()), new Object[0]));}

	        	if(this.timeleft==200) {player.sendMessage((ITextComponent)new TranslationTextComponent("Time Skip : ON", new Object[0]));}
	        if(this.timeleft >= 0)
	        {
	        this.timeleft--;
	        if(Minecraft.getInstance().world.getAllEntities() != null && Iterables.size(Minecraft.getInstance().world.getAllEntities()) > 0)
	        {
	        for (Entity entity : Minecraft.getInstance().world.getAllEntities())
			{
	    	if(entity != null && entity instanceof EntityKingCrimson == false)
	    	{
	    			if (entity == this.getMaster() || entity.getName() == this.getMaster().getName()) {
						player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 150, 255));
						player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 50, 0));
						player.addPotionEffect(new EffectInstance(Effects.SPEED, 50, 0));
						player.setSprinting(true);
					}
	    	
	    			if(entity instanceof MobEntity && entity instanceof EntityKingCrimson==false && entity instanceof EntityStandPunch.kingCrimson==false/*|| entity instanceof AnimalEntity || entity instanceof AgeableEntity || entity instanceof AmbientEntity || entity instanceof WaterMobEntity || entity instanceof MonsterEntity || entity instanceof Entity || entity instanceof LivingEntity || entity instanceof ZombieEntity || entity instanceof CreeperEntity || entity instanceof SkeletonEntity*/&& entity instanceof PlayerEntity == false && entity instanceof ItemEntity == false)
					{
	    				((MobEntity)entity).setAttackTarget((LivingEntity)null);
	    				((MobEntity)entity).setRevengeTarget((LivingEntity)null);
	    				((MobEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 2));
	    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.GLOWING, 40, 255));
	    				((MobEntity)entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 40, 255));
	    				((MobEntity)entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 255));
	    			}
	    			
	    			/*if(entity instanceof PlayerEntity && entity != this.getMaster() && entity.getCustomName().equals("Giorno Giovanna")==false)
	    			{
	    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 2));
	    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.GLOWING, 40, 255));
	    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 40, 255));
	    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 255));
	    			}*/
	    			
	    			if(entity instanceof ArrowEntity)
	    			{
	    				((ArrowEntity)entity).setMotion(-1, -1, -1);
	    				((ArrowEntity)entity).dimension = DimensionType.THE_NETHER;
	    			}
	    			
	    	}
	    	}
	        }
	        }
	        else
	        {
	        	this.timeSkipped = false;
	        }
	        }

	        if(!timeSkipped)
			{
				props.setCooldown(200);
				if(props.getCooldown()==200) {player.sendMessage((ITextComponent)new TranslationTextComponent("Time Skip : OFF", new Object[0])); player.clearActivePotions();}

				player.sendMessage((ITextComponent)new TranslationTextComponent(String.valueOf(props.getCooldown()), new Object[0]));

				if(props.getCooldown() > 0)
				{
					props.subtractCooldown(1);
				}

				if(props.getCooldown() <= 0)
				{
					player.sendMessage((ITextComponent)new TranslationTextComponent("YES YES YES", new Object[0]));
					this.timeleft = 200;
					this.timeSkipped = true;
				}
			}

	        //Orarush food check       
	        if (!player.isAlive())
	          setDead(); 
	        if (player instanceof PlayerEntity) 
	        {
	          if (player.isSprinting()) 
	          {
	            if (attackSwing(player))
	              if (player.getFoodStats().getFoodLevel() > 6) 
	              {
	                this.oratick++;
	                if (this.oratick == 1) 
	                {
	                	if(!this.world.isRemote)
	                		this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.LAST_ORA_KC, getSoundCategory(), 6.0F, 1.0F);
	                  
	                	if (!player.isCreative())
	                    player.getFoodStats().addStats(0, 0.0F); 
	                  if (!this.world.isRemote)
	                    this.orarush = true; 
	                } 
	              } 
	              else 
	              {
	                hungerMessage();
	              }  
	          } 
	          else if (attackSwing(getMaster())) 
	          {
	            if (!this.world.isRemote) 
	            {
	              this.oratick++;
	              if (this.oratick == 1) 
	              {
	                this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.KNIFE_SWING_MISS1, getSoundCategory(), 1.0F, 0.8F / (this.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
	                EntityStandPunch.kingCrimson kingCrimson = new EntityStandPunch.kingCrimson(this.world, this, player);
	                //kingCrimson.setIsCritical(true);
	                kingCrimson.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
	                this.world.addEntity((Entity)kingCrimson);
	              } 
	            } 
	          } 
	          if (player.swingProgressInt == 0)
	            this.oratick = 0; 
	          if (this.orarush) 
	          {
	            player.setSprinting(false);
	            this.oratickr++;
	            if (this.oratickr >= 10)
	              if (!this.world.isRemote) 
	              {
	                player.setSprinting(false);
	                EntityStandPunch.kingCrimson kingCrimson1 = new EntityStandPunch.kingCrimson(this.world, this, player);
	                //kingCrimson1.setIsCritical(true);
	                kingCrimson1.setRandomPositions();
	                kingCrimson1.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
	                this.world.addEntity((Entity)kingCrimson1);
	                EntityStandPunch.kingCrimson kingCrimson2 = new EntityStandPunch.kingCrimson(this.world, this, player);
	               //kingCrimson2.setIsCritical(true);
	                kingCrimson2.setRandomPositions();
	                kingCrimson2.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
	                this.world.addEntity((Entity)kingCrimson2);
	              }  
	            if (this.oratickr >= 80) 
	            {
	              this.orarush = false;
	              this.oratickr = 0;
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
}
