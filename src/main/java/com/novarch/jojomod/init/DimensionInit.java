package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.world.dimension.d4c.end.D4CDimensionTypeEnd;
import com.novarch.jojomod.world.dimension.d4c.nether.D4CDimensionTypeNether;
import com.novarch.jojomod.world.dimension.d4c.overworld.D4CDimensionType;
import com.novarch.jojomod.world.dimension.madeInHeaven.MadeInHeavenDimensionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//TODO Overhaul in 1.16
public class DimensionInit {
    public static final DeferredRegister<ModDimension> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<ModDimension> D4C_DIMENSION = DIMENSIONS.register("d4c_dimension", D4CDimensionType::new);
    public static final RegistryObject<ModDimension> D4C_DIMENSION_NETHER = DIMENSIONS.register("d4c_dimension_nether", D4CDimensionTypeNether::new);
    public static final RegistryObject<ModDimension> D4C_DIMENSION_END = DIMENSIONS.register("d4c_dimension_end", D4CDimensionTypeEnd::new);
    public static final RegistryObject<ModDimension> MADE_IN_HEAVEN_DIMENSION = DIMENSIONS.register("made_in_heaven_dimension", MadeInHeavenDimensionType::new);

    public static final ResourceLocation D4C_DIMENSION_TYPE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c_dimension_overworld");
    public static final ResourceLocation D4C_DIMENSION_TYPE_NETHER = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c_dimension_nether");
    public static final ResourceLocation D4C_DIMENSION_TYPE_END = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c_dimension_end");
    public static final ResourceLocation MADE_IN_HEAVEN_DIMENSION_TYPE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "made_in_heaven_dimension");
}
