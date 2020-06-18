package com.novarch.jojomod.entities.stands.magiciansRed;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityMagiciansRed extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntityMagiciansRed(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_STAR_PLATINUM.get();
		standID = JojoLibs.StandID.magiciansRed;
	}

	public EntityMagiciansRed(World world) {
		super(EntityInit.MAGICIANS_RED.get(), world);
		spawnSound = SoundInit.SPAWN_STAR_PLATINUM.get();
		standID = JojoLibs.StandID.magiciansRed;
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

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if(player.getFireTimer() > 0)
				player.extinguish();

			if (player.isSprinting()) {
				if (attackSwing(player))
					oratick++;
				if (oratick == 1) {
					if (!world.isRemote)
						orarush = true;
				}
			} else if (attackSwing(player)) {
				if (!world.isRemote) {
					oratick++;
					if (oratick == 1) {
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						EntityStandPunch.magiciansRed magiciansRed = new EntityStandPunch.magiciansRed(world, this, player);
						magiciansRed.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.5f);
						world.addEntity(magiciansRed);
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
						EntityStandPunch.magiciansRed magiciansRed1 = new EntityStandPunch.magiciansRed(world, this, player);
						magiciansRed1.setRandomPositions();
						magiciansRed1.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.6f);
						world.addEntity(magiciansRed1);
						EntityStandPunch.magiciansRed magiciansRed2 = new EntityStandPunch.magiciansRed(world, this, player);
						magiciansRed2.setRandomPositions();
						magiciansRed2.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.6f);
						world.addEntity(magiciansRed2);
						world.addParticle(new BasicParticleType(true), magiciansRed1.getPosX(), magiciansRed1.getPosY(), magiciansRed1.getPosZ(), 3d, 0d, 3d);
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
}
