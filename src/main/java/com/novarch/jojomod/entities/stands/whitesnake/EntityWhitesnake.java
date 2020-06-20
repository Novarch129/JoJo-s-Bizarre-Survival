package com.novarch.jojomod.entities.stands.whitesnake;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityWhitesnake extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntityWhitesnake(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_WHITESNAKE.get();
		standID = Util.StandID.whitesnake;
	}

	public EntityWhitesnake(World world) {
		super(EntityInit.WHITESNAKE.get(), world);
		spawnSound = SoundInit.SPAWN_WHITESNAKE.get();
		standID = Util.StandID.whitesnake;
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

			if (!player.isAlive())
				remove();
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
						EntityStandPunch.whitesnake whitesnake = new EntityStandPunch.whitesnake(world, this, player);
						whitesnake.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.2f);
						world.addEntity(whitesnake);
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
						EntityStandPunch.whitesnake whitesnake1 = new EntityStandPunch.whitesnake(world, this, player);
						whitesnake1.setRandomPositions();
						whitesnake1.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.25F);
						world.addEntity(whitesnake1);
						EntityStandPunch.whitesnake whitesnake2 = new EntityStandPunch.whitesnake(world, this, player);
						whitesnake2.setRandomPositions();
						whitesnake2.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.25F);
						world.addEntity(whitesnake2);
					}
				if (oratickr >= 80) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}
}
