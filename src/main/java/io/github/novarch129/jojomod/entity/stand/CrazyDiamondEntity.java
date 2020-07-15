package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.CrazyDiamondPunchEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.WeakHashMap;

@SuppressWarnings("ConstantConditions")
public class CrazyDiamondEntity extends AbstractStandEntity {
    private WeakHashMap<BlockPos, BlockState> repairBlocks = new WeakHashMap<>();

    public CrazyDiamondEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
    }

    public CrazyDiamondEntity(World world) {
        super(EntityInit.CRAZY_DIAMOND.get(), world);
        spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
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
        world.playSound(null, getMaster().getPosition(), getSpawnSound(), SoundCategory.NEUTRAL, 2, 1);
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.DORARUSH.get(), SoundCategory.NEUTRAL, 1, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                CrazyDiamondPunchEntity crazyDiamondPunchEntity = new CrazyDiamondPunchEntity(world, this, getMaster());
                crazyDiamondPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.9f, 0.15f);
                world.addEntity(crazyDiamondPunchEntity);
            }
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

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        CrazyDiamondPunchEntity crazyDiamond1 = new CrazyDiamondPunchEntity(world, this, player);
                        crazyDiamond1.setRandomPositions();
                        crazyDiamond1.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.2f);
                        world.addEntity(crazyDiamond1);
                        CrazyDiamondPunchEntity crazyDiamond2 = new CrazyDiamondPunchEntity(world, this, player);
                        crazyDiamond2.setRandomPositions();
                        crazyDiamond2.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.2f);
                        world.addEntity(crazyDiamond2);
                    }
                if (attackTicker >= 100) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
