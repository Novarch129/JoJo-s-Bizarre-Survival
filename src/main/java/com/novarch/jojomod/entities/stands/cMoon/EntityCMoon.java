package com.novarch.jojomod.entities.stands.cMoon;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.whitesnake.EntityWhitesnake;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityCMoon extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntityCMoon(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = Util.StandID.cMoon;
	}

	public EntityCMoon(World world) {
		super(EntityInit.CMOON.get(), world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = Util.StandID.cMoon;
	}

	@Override
	public boolean hasAct() {
		return true;
	}

	@Override
	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();
				if((props.getStandID() == Util.StandID.cMoon && props.getAct() == 1) || (props.getStandID() == Util.StandID.madeInHeaven && props.getAct() == 2)) {
					player.setNoGravity(false);
					remove();
					EntityWhitesnake whitesnake = new EntityWhitesnake(world);
					whitesnake.setLocationAndAngles(getMaster().getPosX() + 0.1, getMaster().getPosY(), getMaster().getPosZ(), getMaster().rotationYaw, getMaster().rotationPitch);
					whitesnake.setMaster(getMaster());
					whitesnake.setMasterUUID(getMaster().getUniqueID());
					world.addEntity(whitesnake);
					whitesnake.playSpawnSound();
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
						EntityStandPunch.CMoon cMoon = new EntityStandPunch.CMoon(this.world, this, player);
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
						EntityStandPunch.CMoon cMoon1 = new EntityStandPunch.CMoon(this.world, this, player);
						cMoon1.setRandomPositions();
						cMoon1.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
						this.world.addEntity(cMoon1);
						EntityStandPunch.CMoon cMoon2 = new EntityStandPunch.CMoon(this.world, this, player);
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
}
