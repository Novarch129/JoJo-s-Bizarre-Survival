package com.novarch.jojomod.effects;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class CrimsonEffect extends Effect
{
    public CrimsonEffect(EffectType typeIn, int potionColorIn)
    {
        super(typeIn, potionColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        if(Minecraft.getInstance().player==null)
            return;
        Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            if(!props.getStandOn()) {
                if(props.getStandID() == Util.StandID.KING_CRIMSON)
                    entityLivingBaseIn.removePotionEffect(this);
            } else {
                if(!props.getAbility() || !(props.getTimeLeft() > 800))
                    if(props.getStandID() == Util.StandID.KING_CRIMSON)
                        entityLivingBaseIn.removePotionEffect(this);
            }
        });
    }
}
