package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.PurpleHazePunchEntity;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
public class PurpleHazeEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public PurpleHazeEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
		standID = Util.StandID.PURPLE_HAZE;
	}

	public PurpleHazeEntity(World world) {
		super(EntityInit.PURPLE_HAZE.get(), world);
		spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
		standID = Util.StandID.PURPLE_HAZE;
	}

	public void burstCapsule() {
		if (!world.isRemote)
			world.getServer().getWorld(dimension).getEntities()
					.filter(entity -> entity instanceof LivingEntity)
					.filter(entity -> !(entity instanceof AbstractStandEntity))
					.filter(entity -> entity.getDistance(this) < 80)
					.forEach(entity -> ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 200, 2)));
	}

	@Override
	public void playSpawnSound() {
		world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), getSpawnSound(), getSoundCategory(), 2.0f, 1.0f);
	}

	@Override
	public void tick() {
		super.tick();
		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();

				if (props.getCooldown() > 0 && ability)
					props.subtractCooldown(1);
			});

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if (!player.isAlive())
				remove();
			if (player.isSprinting()) {
				if (attackSwing(player))
					oratick++;
				if (oratick == 1) {
					if (!world.isRemote)
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PURPLE_HAZE_RUSH.get(), getSoundCategory(), 4.5f, 1.0f);

					if (!world.isRemote)
						orarush = true;
				}
			} else if (attackSwing(getMaster())) {
				if (!world.isRemote) {
					oratick++;
					if (oratick == 1) {
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
						PurpleHazePunchEntity purpleHaze = new PurpleHazePunchEntity(world, this, player);
						purpleHaze.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						world.addEntity(purpleHaze);
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
						PurpleHazePunchEntity purpleHaze1 = new PurpleHazePunchEntity(world, this, player);
						purpleHaze1.setRandomPositions();
						purpleHaze1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						world.addEntity(purpleHaze1);
						PurpleHazePunchEntity purpleHaze2 = new PurpleHazePunchEntity(world, this, player);
						purpleHaze2.setRandomPositions();
						purpleHaze2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						world.addEntity(purpleHaze2);
					}
				if (oratickr >= 120) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}
}
