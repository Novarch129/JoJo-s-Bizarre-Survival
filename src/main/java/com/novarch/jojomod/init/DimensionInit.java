package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.world.dimension.d4c.end.D4CDimensionTypeEnd;
import com.novarch.jojomod.world.dimension.d4c.nether.D4CDimensionTypeNether;
import com.novarch.jojomod.world.dimension.d4c.overworld.D4CDimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit
{
    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<ModDimension> D4C_DIMENSION = DIMENSIONS.register("d4c_dimension", D4CDimensionType::new);
    public static final RegistryObject<ModDimension> D4C_DIMENSION_NETHER = DIMENSIONS.register("d4c_dimension_nether", D4CDimensionTypeNether::new);
    public static final RegistryObject<ModDimension> D4C_DIMENSION_END = DIMENSIONS.register("d4c_dimension_end", D4CDimensionTypeEnd::new);
}
