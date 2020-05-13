package com.novarch.jojomod.entities.stands.goldExperience;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityGoldExperience extends EntityStandBase
{
	  @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":gold_experience") public static EntityType<EntityGoldExperience> TYPE;

	  private int oratick = 0;

	  private int oratickr = 0;
	  
	  private int changetick = 0;

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

	public EntityGoldExperience(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_GE.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.goldExperience;
	}

	public EntityGoldExperience(World world)
	{
		super(TYPE, world);
	    this.spawnSound = SoundInit.SPAWN_GE.get();
	    setCatchPassive();
	    this.standID = JojoLibs.StandID.goldExperience;
	}
	
	public void tick()
	{
		super.tick();
		this.fallDistance = 0.0F;

	    if (getMaster() != null) {
			PlayerEntity player = getMaster();
			IStand props = JojoProvider.get(player);
			this.life = props.getAbility();

			//Cooldown handler
			if (props.getTransformed() > 0) {
				props.subtractCooldown(1);
			}
			if (props.getCooldown() <= 0) {
				props.setTransformed(0);
				props.setCooldown(80);
			}

			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 2));
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
							EntityStandPunch.goldExperience goldExperience = new EntityStandPunch.goldExperience(this.world, this, player);
							goldExperience.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperience);
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
							EntityStandPunch.goldExperience goldExperience1 = new EntityStandPunch.goldExperience(this.world, this, player);
							goldExperience1.setRandomPositions();
							goldExperience1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperience1);
							EntityStandPunch.goldExperience goldExperience2 = new EntityStandPunch.goldExperience(this.world, this, player);
							goldExperience2.setRandomPositions();
							goldExperience2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(goldExperience2);
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
