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
    public static List<PlayerEntity> d4cPassengerList = new ArrayList<PlayerEntity>();
    public static List<DimensionType> d4cDestinationList = new ArrayList<DimensionType>();

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        List<PlayerEntity> transported = new ArrayList<PlayerEntity>();
        List<DimensionType> used = new ArrayList<DimensionType>();
        if(event.phase == TickEvent.Phase.END)
        {
            if(d4cPassengerList.size() > 0 && d4cDestinationList.size() > 0)
            {
                for (PlayerEntity passenger : d4cPassengerList)
                {
                    for(DimensionType type : d4cDestinationList)
                    {
                        passenger.changeDimension(type, new DimensionHopTeleporter((ServerWorld) passenger.getEntityWorld(), passenger.getPosX(), passenger.getPosY(), passenger.getPosZ()));
                        transported.add(passenger);
                        used.add(type);
                    }
                }
            }

            if(transported.size() > 0 && used.size() > 0)
            {
                for (PlayerEntity transport : transported) {
                    if (d4cPassengerList.contains(transport))
                        d4cPassengerList.remove(transport);
                }
                for (DimensionType use : used) {
                    if (d4cDestinationList.contains(use))
                        d4cDestinationList.remove(use);
                }
                transported.removeAll(transported);
                used.removeAll(used);
            }
        }
    }
}
