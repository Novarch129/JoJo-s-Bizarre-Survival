package com.novarch.jojomod.entities.stands.madeInHeaven;

import com.mojang.authlib.GameProfile;
import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.gui.GUICounter;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityMadeInHeaven extends EntityStandBase
{
	  @ObjectHolder(StevesBizarreSurvival.MOD_ID + ":made_in_heaven") public static EntityType<EntityMadeInHeaven> TYPE;

	  private int oratick = 0;

	  private int oratickr = 0;

	  private int heaventickr = 3600;

	  public int getHeaventickr()
	  {
	  	return this.heaventickr;
	  }

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

	public EntityMadeInHeaven(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_MIH.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.madeInHeaven;
	}

	public EntityMadeInHeaven(World world)
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_MIH.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.madeInHeaven;
	}
	
	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F;

	    if (getMaster() != null) {
			PlayerEntity player = getMaster();
			LazyOptional<IStand> power = this.getMaster().getCapability(JojoProvider.STAND, null);
			IStand props = power.orElse(new IStandCapability());
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 19));
			player.setHealth(20.0f);
			player.getFoodStats().addStats(20, 20.0f);

			if(this.getMaster().isCrouching()&& StevesBizarreSurvival.debug)
			{
				heaventickr-=100;
				player.sendMessage(new TranslationTextComponent(String.valueOf(heaventickr), new Object[0]));
			}

			//Made In Heaven's Ability
			if(this.ability)
			{
				if(heaventickr > 0)
					heaventickr--;

				if(heaventickr < 1200)
				{
					this.world.setDayTime(this.world.getDayTime() + 50);
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 39));
					LightningBoltEntity lightning = new LightningBoltEntity(this.world, this.getPosX() + rand.nextInt(50), this.getPosY(), this.getPosZ() + rand.nextInt(50), false);
					lightning.setSilent(true);
					if(!this.world.isRemote)
						((ServerWorld)this.world).addLightningBolt(lightning);
					this.world.addEntity(lightning);
					this.world.getGameRules().write().putInt("randomTickSpeed", 100);
				}

				if(heaventickr < 800)
				{
					this.world.setDayTime(this.world.getDayTime() + 80);
					this.world.setRainStrength(10.0f);
					this.world.getWorldInfo().setRaining(true);
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 99));
				}

				if(heaventickr < 400)
				{
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 255));
					player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 40, 2));
					this.world.createExplosion(this, this.getPosX() + rand.nextInt(100), this.getPosY() - this.fallDistance, this.getPosZ() + rand.nextInt(100), 4.0f, Explosion.Mode.DESTROY);
					this.world.setDayTime(this.world.getDayTime() + 500);
				}

				if(heaventickr <= 0)
				{
					for(PlayerEntity entity : this.world.getPlayers())
					{
						LazyOptional<IStand> pwr = ((PlayerEntity) entity).getCapability(JojoProvider.STAND);
						IStand prps = pwr.orElse(new IStandCapability());
						if(entity instanceof PlayerEntity && prps.getStandID() != JojoLibs.StandID.GER)
						{
							((PlayerEntity) entity).inventory.clear();
							//TODO teleport player to custom dimension
							((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 99));
							((PlayerEntity) entity).fallDistance = 0;
							//TODO set player spawn dimension to new dimension
							prps.setStandRemoved();
							((PlayerEntity) entity).setInvulnerable(false);
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
							EntityStandPunch.madeInHeaven madeInHeaven = new EntityStandPunch.madeInHeaven(this.world, this, player);
							madeInHeaven.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) madeInHeaven);
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
							EntityStandPunch.madeInHeaven madeInHeaven1 = new EntityStandPunch.madeInHeaven(this.world, this, player);
							madeInHeaven1.setRandomPositions();
							madeInHeaven1.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.15F);
							this.world.addEntity((Entity) madeInHeaven1);
							EntityStandPunch.madeInHeaven madeInHeaven2 = new EntityStandPunch.madeInHeaven(this.world, this, player);
							madeInHeaven2.setRandomPositions();
							madeInHeaven2.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.15F);
							this.world.addEntity((Entity) madeInHeaven2);
						}
					if (this.oratickr >= 80) {
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
		return super.canBeCollidedWith();
	}
}
