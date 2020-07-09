package io.github.novarch129.jojomod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CrimsonEffectUser extends Effect {
    protected final double bonusPerLevel;

    public CrimsonEffectUser(EffectType typeIn, int liquidColorIn, double bonusPerLevel) {
        super(typeIn, liquidColorIn);
        this.bonusPerLevel = bonusPerLevel;
    }

    @Override
    public void performEffect(LivingEntity entityIn, int amplifier) {
        super.performEffect(entityIn, amplifier);
    }

    @Override
    public double getAttributeModifierAmount(int p_111183_1_, AttributeModifier p_111183_2_) {
        return bonusPerLevel * (double) (p_111183_1_ + 1);
    }
}
