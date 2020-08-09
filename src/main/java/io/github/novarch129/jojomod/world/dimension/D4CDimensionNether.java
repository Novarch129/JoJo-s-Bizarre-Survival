package io.github.novarch129.jojomod.world.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.NetherDimension;

public class D4CDimensionNether extends NetherDimension {
    public D4CDimensionNether(World worldIn, DimensionType type) {
        super(worldIn, type);
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return new Vec3d(0.2f, 0.03f, 0.03f);
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
        return SleepResult.BED_EXPLODES;
    }
}
