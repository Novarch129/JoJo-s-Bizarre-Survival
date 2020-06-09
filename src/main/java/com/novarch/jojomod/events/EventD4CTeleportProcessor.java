package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.util.DimensionHopTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventD4CTeleportProcessor {
    public static WeakHashMap<PlayerEntity, DimensionType> d4cPassengers = new WeakHashMap<>();
    public static List<PlayerEntity> madeInHeaven = new ArrayList<>();

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

        madeInHeaven.forEach(passenger -> {
            Random rand = passenger.world.rand;
            passenger.changeDimension(
                    Objects.requireNonNull(DimensionType.byName(JojoBizarreSurvival.MADE_IN_HEAVEN_DIMENSION_TYPE)),
                    new DimensionHopTeleporter(
                            (ServerWorld) passenger.getEntityWorld(),
                            passenger.getPosX(),
                            passenger.getPosY(),
                            passenger.getPosZ()
                    )
            );
            passenger.setPositionAndUpdate(
                    rand.nextBoolean() ? passenger.getPosX() + rand.nextInt(100000) : passenger.getPosX() - rand.nextInt(100000),
                    passenger.getPosY() + rand.nextInt(10),
                    rand.nextBoolean() ? passenger.getPosZ() + rand.nextInt(100000) : passenger.getPosZ() - rand.nextInt(100000)
            );
        });
        madeInHeaven.clear();
    }
}
