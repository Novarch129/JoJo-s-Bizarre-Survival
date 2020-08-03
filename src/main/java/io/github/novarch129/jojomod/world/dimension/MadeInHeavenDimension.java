package io.github.novarch129.jojomod.world.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;

public class MadeInHeavenDimension extends OverworldDimension {
    public MadeInHeavenDimension(World worldIn, DimensionType type) {
        super(worldIn, type);
    }

    @Override
    public int getActualHeight() {
        return 512;
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
        return SleepResult.ALLOW;
    }
}
