package com.novarch.jojomod.entities.stands.madeInHeaven;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ObjectHolder;

public class EntityMadeInHeaven extends EntityStandBase
{
	  @ObjectHolder(JojoBlockyAdventure.MOD_ID + ":made_in_heaven") public static EntityType<EntityMadeInHeaven> TYPE;

	  private int oratick = 0;

	  private int oratickr = 0;
	  
	  private int changetick = 0;

	  private boolean life = true;

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
			this.life = props.getAbility();
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 19));
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
							madeInHeaven1.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) madeInHeaven1);
							EntityStandPunch.madeInHeaven madeInHeaven2 = new EntityStandPunch.madeInHeaven(this.world, this, player);
							madeInHeaven2.setRandomPositions();
							madeInHeaven2.shoot((Entity) player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity((Entity) madeInHeaven2);
							/*for(Entity entity : this.world.getEntitiesInAABBexcluding(this.getMaster(), this.getMaster().getBoundingBox().expand(new Vec3d(-1000.0, -1000.0 , 1000.0)), EntityPredicates.NOT_SPECTATING))
							{
								if(entity instanceof EntityStandPunch.madeInHeaven)
									if(((EntityStandPunch.madeInHeaven) entity).standMaster == this.getMaster())
										((EntityStandPunch.madeInHeaven) entity).setPositionAndRotation(entity.getPosX(), entity.getPosY(), entity.getPosZ(), this.getMaster().rotationYaw, this.getMaster().rotationPitch);
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
