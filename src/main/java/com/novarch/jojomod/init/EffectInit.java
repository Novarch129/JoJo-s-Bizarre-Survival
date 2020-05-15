package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.effects.CrimsonEffect;
import com.novarch.jojomod.effects.CrimsonEffectUser;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit
{
    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS,
            JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<Effect> CRIMSON_USER = EFFECTS.register("crimson_effect_user",
            () -> new CrimsonEffectUser(EffectType.NEUTRAL, 10819625, -4.0d)
                    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<Effect> CRIMSON = EFFECTS.register("crimson_effect",
            () -> new CrimsonEffect(EffectType.HARMFUL, 10819625)
                    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
}
