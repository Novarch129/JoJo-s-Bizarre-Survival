package io.github.novarch129.jojomod.effect;

import io.github.novarch129.jojomod.capability.StandEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class AgingEffect extends Effect {
    public AgingEffect(EffectType typeIn, int potionColorIn) {
        super(typeIn, potionColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
        StandEffects.getLazyOptional(livingEntity).ifPresent(props -> {
            if (!props.isAging())
                livingEntity.removePotionEffect(this);
        });
    }
}
