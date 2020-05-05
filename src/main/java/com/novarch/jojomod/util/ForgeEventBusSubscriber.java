package com.novarch.jojomod.util;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.init.DimensionInit;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = StevesBizarreSurvival.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber
{
    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event)
    {
        if (DimensionType.byName(StevesBizarreSurvival.D4C_DIMENSION_TYPE) == null)
        {
            DimensionManager.registerDimension(StevesBizarreSurvival.D4C_DIMENSION_TYPE, DimensionInit.D4C_DIMENSION.get(), null, true);
        }
    }
}
