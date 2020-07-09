package io.github.novarch129.jojomod.util;

import io.github.novarch129.jojomod.init.FeatureInit;
import io.github.novarch129.jojomod.world.structure.temple.JojoTemple;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.world.structure.temple.JojoTemplePiece;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber {
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        FeatureInit.JOJO_TEMPLE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, FeatureInit.JOJO_TEMPLE_LOCATION, JojoTemplePiece.Piece::new);
        event.getRegistry().register(new JojoTemple(NoFeatureConfig::deserialize).setRegistryName(FeatureInit.JOJO_TEMPLE_LOCATION));
    }
}
