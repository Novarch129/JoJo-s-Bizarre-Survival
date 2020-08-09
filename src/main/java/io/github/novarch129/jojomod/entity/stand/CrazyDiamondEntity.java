package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.CrazyDiamondPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.WeakHashMap;

@SuppressWarnings("ConstantConditions")
public class CrazyDiamondEntity extends AbstractStandEntity {
    private WeakHashMap<BlockPos, BlockState> repairBlocks = new WeakHashMap<>();

    public CrazyDiamondEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_CRAZY_DIAMOND.get();
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
                CrazyDiamondPunchEntity crazyDiamondPunchEntity = new CrazyDiamondPunchEntity(world, this, master);
                crazyDiamondPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.9f, 0.15f);
                world.addEntity(crazyDiamondPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                if (props.getCooldown() > 0 && ability)
                    props.subtractCooldown(1);
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        CrazyDiamondPunchEntity crazyDiamond1 = new CrazyDiamondPunchEntity(world, this, master);
                        crazyDiamond1.randomizePositions();
                        crazyDiamond1.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.2f);
                        world.addEntity(crazyDiamond1);
                        CrazyDiamondPunchEntity crazyDiamond2 = new CrazyDiamondPunchEntity(world, this, master);
                        crazyDiamond2.randomizePositions();
                        crazyDiamond2.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.2f);
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
