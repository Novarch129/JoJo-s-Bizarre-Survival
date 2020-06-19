package com.novarch.jojomod.util;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.init.FeatureInit;
import com.novarch.jojomod.world.structure.temple.JojoTemple;
import com.novarch.jojomod.world.structure.temple.JojoTemplePiece;
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
