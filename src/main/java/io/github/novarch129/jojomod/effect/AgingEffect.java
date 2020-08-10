package io.github.novarch129.jojomod.effect;

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
}
