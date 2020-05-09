package com.novarch.jojomod.proxy;

import net.minecraft.entity.player.PlayerEntity;
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
