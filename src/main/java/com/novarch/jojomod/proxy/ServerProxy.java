package com.novarch.jojomod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

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
}
