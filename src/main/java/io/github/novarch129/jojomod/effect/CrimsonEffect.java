package io.github.novarch129.jojomod.effect;

import io.github.novarch129.jojomod.capability.StandEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CrimsonEffect extends Effect {
    public CrimsonEffect(EffectType typeIn, int potionColorIn) {
        super(typeIn, potionColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        StandEffects.getLazyOptional(entityLivingBaseIn).ifPresent(props -> {
            if (!props.isCrimson())
                entityLivingBaseIn.removePotionEffect(this);
        });
    }
}
