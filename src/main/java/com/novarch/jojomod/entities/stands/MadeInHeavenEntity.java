package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.events.EventD4CTeleportProcessor;
import com.novarch.jojomod.init.DimensionInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MadeInHeavenEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public int heavenTick = 3600;

	public MadeInHeavenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_MADE_IN_HEAVEN.get();
		standID = Util.StandID.MADE_IN_HEAVEN;
	}

	public MadeInHeavenEntity(World world) {
		super(EntityInit.MADE_IN_HEAVEN.get(), world);
		spawnSound = SoundInit.SPAWN_MADE_IN_HEAVEN.get();
		standID = Util.StandID.MADE_IN_HEAVEN;
	}

	@Override
	public void tick() {
		super.tick();
		fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> {
				props.setTimeLeft(heavenTick - 1200);
				if (props.getAct() == 1) {
					remove();
					CMoonEntity cMoon = new CMoonEntity(world);
					cMoon.setLocationAndAngles(getMaster().getPosX() + 0.1, getMaster().getPosY(), getMaster().getPosZ(), getMaster().rotationYaw, getMaster().rotationPitch);
					cMoon.setMaster(getMaster());
					cMoon.setMasterUUID(getMaster().getUniqueID());
					world.addEntity(cMoon);
					cMoon.playSpawnSound();
				}
			});
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 19));
			player.setHealth(20.0f);
			player.getFoodStats().addStats(20, 20.0f);

			if(player.isCrouching() && JojoBizarreSurvivalConfig.COMMON.madeInHeavenAbilityAccelerating.get())
				heavenTick -=200;

			//Made In Heaven's Ability
			if (heavenTick > 0)
				heavenTick--;

			if (heavenTick == 1200)
				player.sendMessage(new StringTextComponent("\"Heaven\" has begun!"));

			if (heavenTick < 1200) {
				world.setDayTime(world.getDayTime() + 50);
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 39));
				LightningBoltEntity lightning = new LightningBoltEntity(world, getPosX() + rand.nextInt(50), getPosY(), getPosZ() + rand.nextInt(50), false);
				lightning.setSilent(true);
				if (!world.isRemote)
					((ServerWorld) world).addLightningBolt(lightning);
				world.addEntity(lightning);
				world.getGameRules().write().putInt(GameRules.RANDOM_TICK_SPEED.getName(), world.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED) + 5);
			}

			if (heavenTick < 800) {
				world.setDayTime(world.getDayTime() + 80);
				world.setRainStrength(10.0f);
				world.getWorldInfo().setRaining(true);
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 99));
			}

			if (heavenTick < 400) {
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 255));
				player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 40, 2));
				world.createExplosion(this, getPosX() + rand.nextInt(100), getPosY() - fallDistance, getPosZ() + rand.nextInt(100), 4.0f, Explosion.Mode.DESTROY);
				world.setDayTime(world.getDayTime() + 500);
			}

			if (heavenTick <= 0) {
				world.getPlayers().forEach(entity -> Stand.getLazyOptional(entity).ifPresent(prps -> {
					if (prps.getStandID() != Util.StandID.GER) {
						entity.inventory.clear();
						entity.getInventoryEnderChest().clear();
						EventD4CTeleportProcessor.madeInHeaven.add(entity);
						entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 99));
						entity.fallDistance = 0;
						entity.setSpawnDimenion(DimensionType.byName(DimensionInit.MADE_IN_HEAVEN_DIMENSION_TYPE));
						prps.removeStand();
						entity.setInvulnerable(false);
					}
				}));
			}

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if (!player.isAlive())
				remove();
			if (player.isSprinting()) {
				if (attackSwing(player))
					oratick++;
				if (oratick == 1) {
					if (!player.isCreative())
						player.getFoodStats().addStats(0, 0.0F);
					if (!world.isRemote)
						orarush = true;
				}
			} else if (attackSwing(getMaster())) {
				if (!world.isRemote) {
					oratick++;
					if (oratick == 1) {
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						AbstractStandPunchEntity.MadeInHeaven madeInHeaven = new AbstractStandPunchEntity.MadeInHeaven(world, this, player);
						madeInHeaven.shoot(player, player.rotationPitch, player.rotationYaw, 6.0f, 0.0001f);
						world.addEntity(madeInHeaven);
					}
				}
			}
			if (player.swingProgressInt == 0)
				oratick = 0;
			if (orarush) {
				player.setSprinting(false);
				oratickr++;
				if (oratickr >= 10)
					if (!world.isRemote) {
						player.setSprinting(false);
						AbstractStandPunchEntity.MadeInHeaven madeInHeaven1 = new AbstractStandPunchEntity.MadeInHeaven(world, this, player);
						madeInHeaven1.setRandomPositions();
						madeInHeaven1.shoot(player, player.rotationPitch, player.rotationYaw, 4.0f, 0.1f);
						world.addEntity(madeInHeaven1);
						AbstractStandPunchEntity.MadeInHeaven madeInHeaven2 = new AbstractStandPunchEntity.MadeInHeaven(world, this, player);
						madeInHeaven2.setRandomPositions();
						madeInHeaven2.shoot(player, player.rotationPitch, player.rotationYaw, 4.0f, 0.1f);
						world.addEntity(madeInHeaven2);
					}
				if (oratickr >= 80) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}
}
