package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Deprecated //All the current structure code is bad
public class FeatureInit {
    public static final ResourceLocation JOJO_TEMPLE_LOCATION = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "jojo_temple");
    public static IStructurePieceType JOJO_TEMPLE_PIECE = null;

//    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":jojo_temple")
//    public static Structure<NoFeatureConfig> JOJO_TEMPLE = null;
}
