package io.github.novarch129.jojomod.world.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;

public class D4CDimension extends OverworldDimension {
    public D4CDimension(World worldIn, DimensionType type) {
        super(worldIn, type);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
        return SleepResult.ALLOW;
    }
}
