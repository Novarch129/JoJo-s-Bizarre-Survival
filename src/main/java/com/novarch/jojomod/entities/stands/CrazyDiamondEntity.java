package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.WeakHashMap;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrazyDiamondEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	private WeakHashMap<BlockPos, BlockState> repairBlocks = new WeakHashMap<>();

	public CrazyDiamondEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
		standID = Util.StandID.CRAZY_DIAMOND;
	}

	public CrazyDiamondEntity(World world) {
		super(EntityInit.CRAZY_DIAMOND.get(), world);
		spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
		standID = Util.StandID.CRAZY_DIAMOND;
	}

	public void putRepairBlock(BlockPos blockPos, BlockState state) {
		repairBlocks.put(blockPos, state);
	}

	public void repair() {
		if (getMaster() != null)
			Stand.getLazyOptional(getMaster()).ifPresent(props -> {
				if (repairBlocks.size() > 0) {
					world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.SPAWN_CRAZY_DIAMOND.get(), getSoundCategory(), 1.0f, 1.0f);
					props.setCooldown(100);
				}
				repairBlocks.forEach(world::setBlockState);
				repairBlocks.clear();
			});
	}

	@Override
	public void playSpawnSound() {
		world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), getSpawnSound(), getSoundCategory(), 2.0f, 1.0f);
	}

	@Override
	public void tick() {
		super.tick();
		fallDistance = 0.0f;
		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();
				if (props.getCooldown() > 0 && ability)
					props.subtractCooldown(1);
			});

			if (standOn) {
				followMaster();
				setRotationYawHead(player.rotationYaw);
				setRotation(player.rotationYaw, player.rotationPitch);

				if (!player.isAlive())
					remove();
				if (player.isSprinting()) {
					if (attackSwing(player))
						oratick++;
					if (oratick == 1)
						if (!world.isRemote) {
							world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.DORARUSH.get(), getSoundCategory(), 1.0f, 1.0f);
							orarush = true;
						}
				} else if (attackSwing(getMaster())) {
					if (!world.isRemote) {
						oratick++;
						if (oratick == 1) {
							world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
							AbstractStandPunchEntity.CrazyDiamond crazyDiamond = new AbstractStandPunchEntity.CrazyDiamond(world, this, player);
							crazyDiamond.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
							world.addEntity(crazyDiamond);
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
							AbstractStandPunchEntity.CrazyDiamond crazyDiamond1 = new AbstractStandPunchEntity.CrazyDiamond(world, this, player);
							crazyDiamond1.setRandomPositions();
							crazyDiamond1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
							world.addEntity(crazyDiamond1);
							AbstractStandPunchEntity.CrazyDiamond crazyDiamond2 = new AbstractStandPunchEntity.CrazyDiamond(world, this, player);
							crazyDiamond2.setRandomPositions();
							crazyDiamond2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
							world.addEntity(crazyDiamond2);
						}
					if (oratickr >= 100) {
						orarush = false;
						oratickr = 0;
					}
				}
			}
		}
	}
}
