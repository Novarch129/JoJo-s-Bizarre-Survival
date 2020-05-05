package com.novarch.jojomod.world.dimension.d4c.nether;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;

import javax.annotation.Nullable;

public class D4CDimensionNether extends NetherDimension
{
    private static final Vec3d fogColor = new Vec3d((double)0.2F, (double)0.03F, (double)0.03F);
    public D4CDimensionNether(World worldIn, DimensionType type)
    {
        super(worldIn, type);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator()
    {
        return super.createChunkGenerator();
    }

    @Nullable
    @Override public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid)
    {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid)
    {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5f;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks)
    {
        return fogColor;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos)
    {
        return SleepResult.BED_EXPLODES;
    }
}
