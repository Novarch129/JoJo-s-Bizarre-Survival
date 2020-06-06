package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.util.DimensionHopTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventD4CTeleportProcessor {
    public static HashMap<PlayerEntity, DimensionType> d4cPassengers = new HashMap<>();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        d4cPassengers.forEach((passenger, type) ->
                passenger.changeDimension(
                        type,
                        new DimensionHopTeleporter(
                                (ServerWorld) passenger.getEntityWorld(),
                                passenger.getPosX(),
                                passenger.getPosY(),
                                passenger.getPosZ()
                        )
                )
        );
        d4cPassengers.clear();
    }
}
