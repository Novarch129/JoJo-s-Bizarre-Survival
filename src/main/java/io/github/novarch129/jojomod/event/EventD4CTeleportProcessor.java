package io.github.novarch129.jojomod.event;

import com.google.common.collect.Maps;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.init.DimensionInit;
import io.github.novarch129.jojomod.util.DimensionHopTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.*;

@EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.FORGE)
public class EventD4CTeleportProcessor {
    public static Map<PlayerEntity, DimensionType> d4cPassengers = Maps.newHashMap();
    public static List<PlayerEntity> madeInHeaven = new ArrayList<>();

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (d4cPassengers.size() > 0) {
            d4cPassengers.forEach((passenger, type) -> {
                if (!passenger.world.isRemote)
                    passenger.changeDimension(
                            type,
                            new DimensionHopTeleporter((ServerWorld) passenger.getEntityWorld())
                    );
            });
            d4cPassengers = Maps.newHashMap();
        }

        if (madeInHeaven.size() > 0) {
            madeInHeaven.forEach(passenger -> {
                Random rand = passenger.world.rand;
                if (!passenger.world.isRemote) {
                    passenger.changeDimension(
                            Objects.requireNonNull(DimensionType.byName(DimensionInit.MADE_IN_HEAVEN_DIMENSION_TYPE)),
                            new DimensionHopTeleporter((ServerWorld) passenger.getEntityWorld())
                    );
                    passenger.setPositionAndUpdate(
                            rand.nextBoolean() ? passenger.getPosX() + rand.nextInt(100000) : passenger.getPosX() - rand.nextInt(100000),
                            passenger.getPosY() + rand.nextInt(10),
                            rand.nextBoolean() ? passenger.getPosZ() + rand.nextInt(100000) : passenger.getPosZ() - rand.nextInt(100000)
                    );
                }
            });
            madeInHeaven.clear();
        }
    }
}
