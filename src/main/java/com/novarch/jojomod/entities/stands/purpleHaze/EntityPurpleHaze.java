package com.novarch.jojomod.entities.stands.purpleHaze;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityPurpleHaze extends EntityStandBase
{
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
	  public void readAdditional(CompoundNBT compoundNBT)
	  {
	    super.readAdditional(compoundNBT);
	  }

	  @Override
	  public void writeAdditional(CompoundNBT compoundNBT)
	  {
	    super.writeAdditional(compoundNBT);
	  }

	  @Override
	  public IPacket<?> createSpawnPacket()
	{
		return super.createSpawnPacket();
	}

	@Override
	public void spawnSound()
	{
		this.world.playSound(null, new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), this.getSpawnSound(), this.getSoundCategory(), 2.0f, 1.0f);
	}

	public EntityPurpleHaze(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
	    this.standID = JojoLibs.StandID.purpleHaze;
	}

	public EntityPurpleHaze(World world)
	{
		super(EntityInit.PURPLE_HAZE.get(), world);
	    this.spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
	    this.standID = JojoLibs.StandID.purpleHaze;
	}
	
	public void tick() {
		super.tick();
		this.fallDistance = 0.0f;
		if (getMaster() != null)
		{
			PlayerEntity player = getMaster();

			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				this.ability = props.getAbility();

				if(props.getCooldown() > 0 && ability)
					props.subtractCooldown(1);
			});

			if (this.standOn)
			{
				followMaster();
				setRotationYawHead(player.rotationYaw);
				setRotation(player.rotationYaw, player.rotationPitch);

				//Orarush food check
				if (!player.isAlive())
					remove();
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
								if(!world.isRemote)
									world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PURPLE_HAZE_RUSH.get(), getSoundCategory(), 1.0f, 1.0f);

								if (!this.world.isRemote)
									this.orarush = true;
							}
						} else
							hungerMessage();
				} else if (attackSwing(getMaster())) {
					if (!this.world.isRemote) {
						this.oratick++;
						if (this.oratick == 1) {
							this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (this.rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
							EntityStandPunch.purpleHaze purpleHaze = new EntityStandPunch.purpleHaze(this.world, this, player);
							purpleHaze.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(purpleHaze);
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
							EntityStandPunch.purpleHaze purpleHaze1 = new EntityStandPunch.purpleHaze(this.world, this, player);
							purpleHaze1.setRandomPositions();
							purpleHaze1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(purpleHaze1);
							EntityStandPunch.purpleHaze purpleHaze2 = new EntityStandPunch.purpleHaze(this.world, this, player);
							purpleHaze2.setRandomPositions();
							purpleHaze2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(purpleHaze2);
						}
					if (this.oratickr >= 120) {
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
