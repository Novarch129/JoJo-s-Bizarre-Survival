package com.novarch.jojomod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class OxygenPoisoningEffect extends Effect
{
    public OxygenPoisoningEffect(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) { return true; }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        for(int i = 100; i > entityLivingBaseIn.getHealth(); i--)
        {
            if(entityLivingBaseIn instanceof PlayerEntity)
                if(((PlayerEntity) entityLivingBaseIn).isCreative() | entityLivingBaseIn.isSpectator())
                    return;
            entityLivingBaseIn.attackEntityFrom(DamageSource.DROWN, 2.0f);
        }

        if(entityLivingBaseIn instanceof MobEntity)
            ((MobEntity) entityLivingBaseIn).spawnExplosionParticle();

        if(entityLivingBaseIn instanceof PlayerEntity)
            ((PlayerEntity) entityLivingBaseIn).spawnSweepParticles();
    }
}
