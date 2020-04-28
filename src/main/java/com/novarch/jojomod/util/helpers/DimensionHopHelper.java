package com.novarch.jojomod.util.helpers;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class DimensionHopHelper extends Teleporter
{
    private final ServerWorld world;
    private double x,y,z;
    public DimensionHopHelper(ServerWorld worldIn, double x, double y, double z)
    {
        super(worldIn);
        this.world = worldIn;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean placeInPortal(Entity entityIn, float rotationYaw) {
        this.world.getBlockState(new BlockPos((int)this.x, (int)this.y, (int)this.z));
        entityIn.setPosition(this.x, this.y, this.z);
        int i = MathHelper.floor(entityIn.getPosX());
        int j = MathHelper.floor(entityIn.getPosY()) - 1;
        int k = MathHelper.floor(entityIn.getPosZ());
        int l = 1;
        int i1 = 0;
        for (int j1 = -2; j1 <= 2; ++j1)
        {
            for (int k1 = -2; k1 <= 2; ++k1)
            {
                for (int l1 = -1; l1 < 3; ++l1)
                {
                    int i2 = i + k1 * 1 + j1 * 0;
                    int j2 = j + l1;
                    int k2 = k + k1 * 0 - j1 * 1;
                    boolean flag = l1 < 0;
                    this.world.setBlockState(new BlockPos(i2, j2, k2), flag ? Blocks.AIR.getDefaultState() : Blocks.AIR.getDefaultState());
                }
            }
        }
        entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
        entityIn.setMotion(0.0d, 0.0d, 0.0d);
        return true;
    }

    public static void teleportToDimension(PlayerEntity player, DimensionType dimension, double x, double y, double z)
    {
        DimensionType oldDimension = player.getEntityWorld().getDimension().getType();
        ServerPlayerEntity entityPlayerMP = (ServerPlayerEntity) player;
        MinecraftServer server = player.getEntityWorld().getServer();
        ServerWorld worldServer = server.getWorld(dimension);
        player.addExperienceLevel(0);

        if (worldServer == null || worldServer.getServer() == null)
        {
            throw new IllegalArgumentException("Dimension: "+dimension+" doesn't exist!");
        }
        entityPlayerMP.changeDimension(dimension);
        player.setPositionAndUpdate(x, y, z);
    }
}
