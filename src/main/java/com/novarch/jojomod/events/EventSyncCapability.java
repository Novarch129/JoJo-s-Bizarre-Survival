package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.network.message.SyncStandCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventSyncCapability
{
    @SubscribeEvent
    public static void saveStand(PlayerEvent.Clone event)
    {
        if(!event.isWasDeath())
        {
            JojoProvider.getLazyOptional(event.getOriginal()).ifPresent(originalProps -> {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                JojoProvider.getLazyOptional(player).ifPresent(newProps -> {
                    newProps.clone(originalProps);
                });
            });
        }
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazyOptional(player).ifPresent(props -> {
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazyOptional(player).ifPresent(props -> {
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazyOptional(player).ifPresent(props -> {
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public static void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazyOptional(player).ifPresent(props -> {
            props.putStandOn(false);
            if(!player.world.isRemote)
                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
        });

    }
}
