package novarch.jojomod.init;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.effects.CrimsonEffect;
import novarch.jojomod.effects.CrimsonEffectUser;
import novarch.jojomod.effects.HazeEffect;
import novarch.jojomod.effects.OxygenPoisoningEffect;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<Effect> CRIMSON_USER = EFFECTS.register("crimson_effect_user",
            () -> new CrimsonEffectUser(EffectType.NEUTRAL, 10819625, -4.0d)
                    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "63d19b01-3d33-4b4c-bbee-a48eb417cfde", 0.0d, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<Effect> CRIMSON = EFFECTS.register("crimson_effect",
            () -> new CrimsonEffect(EffectType.HARMFUL, 10819625)
                    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "a55e2313-560e-4881-95a7-0ee8df22fa04", -0.15f, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<Effect> OXYGEN_POISONING = EFFECTS.register("oxygen_poisoning",
            () -> new OxygenPoisoningEffect(EffectType.HARMFUL, 7009526)
                    .addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "81d95dec-554d-4199-adbc-a339d2a671d3", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<Effect> HAZE = EFFECTS.register("haze",
            () -> new HazeEffect(EffectType.HARMFUL, 9250166)
                    .addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "36feea01-c301-4eeb-b085-42d38ed2d742", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "36feea01-c301-4eeb-b085-42d38ed2d742", -0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL));
}
