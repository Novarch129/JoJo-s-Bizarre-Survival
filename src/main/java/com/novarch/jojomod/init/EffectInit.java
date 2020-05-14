package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.effects.CrimsonEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit
{
    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS,
            JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<Effect> CRIMSON = EFFECTS.register("crimson_effect",
            () -> new CrimsonEffect(EffectType.NEUTRAL, 37848743, -4.0d));
}
