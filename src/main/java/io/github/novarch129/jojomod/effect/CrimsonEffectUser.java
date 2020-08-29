package io.github.novarch129.jojomod.effect;

import io.github.novarch129.jojomod.capability.Stand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CrimsonEffectUser extends Effect {
    protected final double bonusPerLevel;

    public CrimsonEffectUser(EffectType typeIn, int liquidColorIn, double bonusPerLevel) {
        super(typeIn, liquidColorIn);
        this.bonusPerLevel = bonusPerLevel;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityIn, int amplifier) {
        if (entityIn instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) entityIn).ifPresent(props -> {
                if (!props.getStandOn() || !props.getAbilityActive())
                    entityIn.removePotionEffect(this);
            });
    }

    @Override
    public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
        return bonusPerLevel * (double) (amplifier + 1);
    }
}
