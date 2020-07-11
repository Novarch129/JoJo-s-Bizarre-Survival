package io.github.novarch129.jojomod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public interface IProxy {
    PlayerEntity getPlayer();

    World getWorld();

    MinecraftServer getServer();
}
