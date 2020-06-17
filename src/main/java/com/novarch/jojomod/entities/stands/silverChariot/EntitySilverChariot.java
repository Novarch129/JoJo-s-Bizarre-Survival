package com.novarch.jojomod.entities.stands.silverChariot;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntitySilverChariot extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	private boolean hasArmor = true;

	private int armorTick = 0;

	public EntitySilverChariot(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_SILVER_CHARIOT.get();
		standID = JojoLibs.StandID.silverChariot;
	}

	public EntitySilverChariot(World world) {
		super(EntityInit.SILVER_CHARIOT.get(), world);
		spawnSound = SoundInit.SPAWN_SILVER_CHARIOT.get();
		standID = JojoLibs.StandID.silverChariot;
	}

	public boolean isHasArmor() {
		return hasArmor;
	}

	/**
	 * Redirects attacks from Silver Chariot to it's master, doubling damage if it's armor is off.
	 *
	 * @param damageSource	The {@link DamageSource} damaging the Stand.
	 * @param damage	The amount of damage taken.
	 * @return	Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damage) {
		if (!standOn)
			return false;
		if (damageSource.getTrueSource() == getMaster())
			return false;
		if (damageSource == DamageSource.CACTUS)
			return false;
		getMaster().attackEntityFrom(damageSource, isHasArmor() ? damage * 2 : damage * 0.5f);
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());
			hasArmor = ability;

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

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
						EntityStandPunch.silverChariot silverChariot = new EntityStandPunch.silverChariot(world, this, player);
						silverChariot.shoot(player, player.rotationPitch, player.rotationYaw, isHasArmor() ? 10.0f : 4.0f, isHasArmor() ? Float.MIN_VALUE : 0.001f);
						world.addEntity(silverChariot);
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
						EntityStandPunch.silverChariot silverChariot1 = new EntityStandPunch.silverChariot(world, this, player);
						silverChariot1.setRandomPositions();
						silverChariot1.shoot(player, player.rotationPitch, player.rotationYaw, isHasArmor() ? 6.0f : 3.0f, isHasArmor() ? 0.0001f : 0.05f);
						world.addEntity(silverChariot1);
						EntityStandPunch.silverChariot silverChariot2 = new EntityStandPunch.silverChariot(world, this, player);
						silverChariot2.setRandomPositions();
						silverChariot2.shoot(player, player.rotationPitch, player.rotationYaw, isHasArmor() ? 6.0f : 3.0f, isHasArmor() ? 0.0001f : 0.05f);
						world.addEntity(silverChariot2);
					}
				if (oratickr >= 80) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}
}
