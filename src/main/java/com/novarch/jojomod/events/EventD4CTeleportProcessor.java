package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.util.DimensionHopTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventD4CTeleportProcessor
{
    public static List<PlayerEntity> d4cPassengerList = new ArrayList<>();
    public static List<DimensionType> d4cDestinationList = new ArrayList<>();

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        List<PlayerEntity> transported = new ArrayList<>();
        List<DimensionType> used = new ArrayList<>();
        if(event.phase == TickEvent.Phase.END)
        {
            d4cPassengerList.forEach(passenger -> {
                DimensionType type = d4cDestinationList.get(d4cPassengerList.indexOf(passenger));
                passenger.changeDimension(type, new DimensionHopTeleporter((ServerWorld) passenger.getEntityWorld(), passenger.getPosX(), passenger.getPosY(), passenger.getPosZ()));
                transported.add(passenger);
                used.add(type);
            });
            d4cPassengerList.removeAll(transported);
            d4cDestinationList.removeAll(used);
            transported.clear();
            used.clear();
        }
    }
}
