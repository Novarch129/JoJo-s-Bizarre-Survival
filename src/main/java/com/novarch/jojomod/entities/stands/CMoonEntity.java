package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
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
public class CMoonEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public CMoonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = Util.StandID.CMOON;
	}

	public CMoonEntity(World world) {
		super(EntityInit.CMOON.get(), world);
		this.spawnSound = SoundInit.SPAWN_CMOON.get();
		this.standID = Util.StandID.CMOON;
	}

	@Override
	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();
				if((props.getStandID() == Util.StandID.CMOON && props.getAct() == 1) || (props.getStandID() == Util.StandID.MADE_IN_HEAVEN && props.getAct() == 2)) {
					player.setNoGravity(false);
					remove();
					WhitesnakeEntity whitesnake = new WhitesnakeEntity(world);
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
						AbstractStandPunchEntity.CMoon cMoon = new AbstractStandPunchEntity.CMoon(this.world, this, player);
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
						AbstractStandPunchEntity.CMoon cMoon1 = new AbstractStandPunchEntity.CMoon(this.world, this, player);
						cMoon1.setRandomPositions();
						cMoon1.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
						this.world.addEntity(cMoon1);
						AbstractStandPunchEntity.CMoon cMoon2 = new AbstractStandPunchEntity.CMoon(this.world, this, player);
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
