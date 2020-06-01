package com.novarch.jojomod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class ServerProxy implements IProxy
{
    @Override
    public PlayerEntity getPlayer()
    {
        return null;
    }

    @Override
    public World getWorld()
    {
        return null;
    }

    @Override
    public MinecraftServer getServer()
    {
        return ServerLifecycleHooks.getCurrentServer();
    }
}
