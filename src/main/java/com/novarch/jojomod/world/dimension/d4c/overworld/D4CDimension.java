package com.novarch.jojomod.world.dimension.d4c.overworld;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

import javax.annotation.Nullable;

public class D4CDimension extends Dimension
{
    public D4CDimension(World worldIn, DimensionType type_)
    {
        super(worldIn, type_, 0.0f);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator()
    {
        return new OverworldChunkGenerator(world, new OverworldBiomeProvider(new OverworldBiomeProviderSettings(world.getWorldInfo())), new OverworldGenSettings());
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid)
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
        int j = 6000;
        float f1 = (j + partialTicks) / 24000.0f - 0.25f;
        if (f1 < 0.0f) {
            f1 += 1.0f;
        }

        if (f1 > 1.0f) {
            f1 -= 1.0f;
        }

        float f2 = f1;
        f1 = 1.0f - (float) ((Math.cos(f1 * Math.PI) + 1.0d) / 2.0d);
        f1 = f2 + (f1 - f2) / 3.0f;
        return f1;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks)
    {
        return Vec3d.ZERO;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }

    @Override
    public int getActualHeight()
    {
        return 256;
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos)
    {
        return SleepResult.ALLOW;
    }
}
