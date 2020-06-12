package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack.EntitySheerHeartAttack;
import com.novarch.jojomod.network.message.server.SSyncStandCapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventSyncCapability
{
    @SubscribeEvent
    public static void saveStand(PlayerEvent.Clone event)
    {
        if(!event.isWasDeath() || JojoBizarreSurvivalConfig.COMMON.saveStandOnDeath.get())
        {
            Stand.getLazyOptional(event.getOriginal()).ifPresent(originalProps -> {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                Stand.getLazyOptional(player).ifPresent(newProps -> newProps.clone(originalProps));
            });
        }
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        player.setInvulnerable(false);
        Stand.getLazyOptional(player).ifPresent(props -> {
            props.putStandOn(false);
            if (!player.world.isRemote)
            {
                player.getServerWorld().getEntities()
                        .filter(entity -> entity instanceof FakePlayerEntity)
                        .filter(entity -> ((FakePlayerEntity) entity).getParent() == player)
                        .forEach(Entity::remove);
                player.getServerWorld().getEntities()
                        .filter(entity -> entity instanceof EntitySheerHeartAttack)
                        .filter(entity -> ((EntitySheerHeartAttack) entity).getMaster() == player)
                        .forEach(Entity::remove);
                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props));
            }
        });
    }
}
