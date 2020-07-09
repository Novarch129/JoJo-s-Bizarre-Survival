package novarch.jojomod.world.dimension.d4c.end;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.EndDimension;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;

@MethodsReturnNonnullByDefault
public class D4CDimensionEnd extends EndDimension
{
    public D4CDimensionEnd(World worldIn, DimensionType typeIn)
    {
        super(worldIn, typeIn);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator()
    {
        return super.createChunkGenerator();
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid)
    {
        return super.findSpawn(chunkPosIn, checkValid);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid)
    {
        return super.findSpawn(posX, posZ, checkValid);
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return super.calculateCelestialAngle(worldTime, partialTicks);
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return super.isSurfaceWorld();
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks)
    {
        return super.getFogColor(celestialAngle, partialTicks);
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z)
    {
        return super.doesXZShowFog(x, z);
    }

    @Override
    public SleepResult canSleepAt(PlayerEntity player, BlockPos pos)
    {
        return SleepResult.BED_EXPLODES;
    }
}
