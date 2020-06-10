package com.novarch.jojomod.entities.stands.cMoon;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.whitesnake.EntityWhitesnake;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityCMoon extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public void readAdditional(CompoundNBT compoundNBT) {
		super.readAdditional(compoundNBT);
	}

	@Override
	public void writeAdditional(CompoundNBT compoundNBT) {
		super.writeAdditional(compoundNBT);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return super.createSpawnPacket();
	}

	public EntityCMoon(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = JojoLibs.StandID.cMoon;
	}

	public EntityCMoon(World world) {
		super(EntityInit.CMOON.get(), world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = JojoLibs.StandID.cMoon;
	}

	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();
				if((props.getStandID() == JojoLibs.StandID.cMoon && props.getAct() == 1) || (props.getStandID() == JojoLibs.StandID.madeInHeaven && props.getAct() == 2)) {
					remove();
					EntityWhitesnake whitesnake = new EntityWhitesnake(world);
					whitesnake.setLocationAndAngles(getMaster().getPosX() + 0.1, getMaster().getPosY(), getMaster().getPosZ(), getMaster().rotationYaw, getMaster().rotationPitch);
					whitesnake.setMaster(getMaster());
					whitesnake.setMasterUUID(getMaster().getUniqueID());
					world.addEntity(whitesnake);
					whitesnake.spawnSound();
				}
			});

			if(ability) {
				player.setNoGravity(true);
				player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 100, 1));
			} else {
				player.setNoGravity(false);
				player.removePotionEffect(Effects.LEVITATION);
				player.stopFallFlying();
				player.fallDistance = 0;
			}

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if (!player.isAlive())
				remove();
			if (player.isSprinting()) {
				if (attackSwing(player))
					this.oratick++;
				if (this.oratick == 1) {
					if (!player.isCreative())
						player.getFoodStats().addStats(0, 0.0F);
					if (!this.world.isRemote)
						this.orarush = true;
				}
			} else if (attackSwing(getMaster())) {
				if (!this.world.isRemote) {
					this.oratick++;
					if (this.oratick == 1) {
						this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (this.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						EntityStandPunch.cMoon cMoon = new EntityStandPunch.cMoon(this.world, this, player);
						cMoon.shoot(player, player.rotationPitch, player.rotationYaw, 3.0f, 0.15f);
						this.world.addEntity(cMoon);
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
						EntityStandPunch.cMoon cMoon1 = new EntityStandPunch.cMoon(this.world, this, player);
						cMoon1.setRandomPositions();
						cMoon1.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
						this.world.addEntity(cMoon1);
						EntityStandPunch.cMoon cMoon2 = new EntityStandPunch.cMoon(this.world, this, player);
						cMoon2.setRandomPositions();
						cMoon2.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
						this.world.addEntity(cMoon2);
					}
				if (this.oratickr >= 80) {
					this.orarush = false;
					this.oratickr = 0;
				}
			}
		}
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return super.canBeCollidedWith();
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(entityIn instanceof EntityStandBase || entityIn instanceof EntityStandPunch)
			super.applyEntityCollision(entityIn);
	}

	@Override
	public boolean hasAct() {
		return true;
	}
}
