package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MagiciansRedEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public MagiciansRedEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_MAGICIANS_RED.get();
		standID = Util.StandID.MAGICIANS_RED;
	}

	public MagiciansRedEntity(World world) {
		super(EntityInit.MAGICIANS_RED.get(), world);
		spawnSound = SoundInit.SPAWN_MAGICIANS_RED.get();
		standID = Util.StandID.MAGICIANS_RED;
	}

	/**
	 * Prevents all fire damage from affecting Magician's Red.
	 *
	 * @param damageSource  The {@link DamageSource} damaging the Stand.
	 * @param damage    The amount of damage taken.
	 * @return	Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage) {
		if (!standOn || damageSource.getTrueSource() == getMaster() || damageSource == DamageSource.CACTUS || damageSource == DamageSource.ON_FIRE || damageSource == DamageSource.IN_FIRE)
			return false;
		getMaster().attackEntityFrom(damageSource, damage * 0.5f);
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());
			if(world.rand.nextInt(3) == 1) {
				world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX() + 0.2, getPosY(), getPosZ(), 0, 0.05, -0.001);
				world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX() - 0.2, getPosY(), getPosZ() + 0.1, 0, 0.075, 0);
				world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX(), getPosY() + 0.1, getPosZ() - 0.02, 0, 0.07, 0.005);
			}

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if(player.getFireTimer() > 0)
				player.extinguish();

			if (player.isSprinting()) {
				if (attackSwing(player))
					oratick++;
				if (oratick == 1) {
					world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.CROSSFIRE_HURRICANE_SPECIAL.get(), getSoundCategory(), 1.5f, 1.0f);
					if (!world.isRemote)
						orarush = true;
				}
			} else if (attackSwing(player)) {
				if (!world.isRemote) {
					oratick++;
					if (oratick == 1) {
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
						AbstractStandPunchEntity.MagiciansRed magiciansRed = new AbstractStandPunchEntity.MagiciansRed(world, this, player);
						magiciansRed.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.5f);
						magiciansRed.setExplosive(true);
						world.addEntity(magiciansRed);
						world.addParticle(ParticleTypes.LARGE_SMOKE, magiciansRed.getPosX(), magiciansRed.getPosY(), magiciansRed.getPosZ(), magiciansRed.getMotion().getX(), magiciansRed.getMotion().getY(), magiciansRed.getMotion().getZ());
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
						AbstractStandPunchEntity.MagiciansRed magiciansRed1 = new AbstractStandPunchEntity.MagiciansRed(world, this, player);
						magiciansRed1.setRandomPositions();
						magiciansRed1.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.6f);
						world.addEntity(magiciansRed1);
						AbstractStandPunchEntity.MagiciansRed magiciansRed2 = new AbstractStandPunchEntity.MagiciansRed(world, this, player);
						magiciansRed2.setRandomPositions();
						magiciansRed2.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.6f);
						world.addEntity(magiciansRed2);
					}
				if (oratickr >= 80) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}

	@Override
	public void setFire(int seconds) { }

	@Override
	public boolean canRenderOnFire() {
		return JojoBizarreSurvivalConfig.CLIENT.renderMagiciansRedFire.get();
	}
}
