package novarch.jojomod.util;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.init.DimensionInit;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
    @SubscribeEvent
    public static void registerDimensions(final RegisterDimensionsEvent event) {
        if (DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE) == null)
            DimensionManager.registerDimension(DimensionInit.D4C_DIMENSION_TYPE, DimensionInit.D4C_DIMENSION.get(), null, true);

        if (DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_NETHER) == null)
            DimensionManager.registerDimension(DimensionInit.D4C_DIMENSION_TYPE_NETHER, DimensionInit.D4C_DIMENSION_NETHER.get(), null, false);

        if (DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_END) == null)
            DimensionManager.registerDimension(DimensionInit.D4C_DIMENSION_TYPE_END, DimensionInit.D4C_DIMENSION_END.get(), null, false);

        if (DimensionType.byName(DimensionInit.MADE_IN_HEAVEN_DIMENSION_TYPE) == null)
            DimensionManager.registerDimension(DimensionInit.MADE_IN_HEAVEN_DIMENSION_TYPE, DimensionInit.MADE_IN_HEAVEN_DIMENSION.get(), null, true);
    }
}
