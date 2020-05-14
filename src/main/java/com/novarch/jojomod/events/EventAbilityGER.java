package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventAbilityGER
{
    @SubscribeEvent
    public static void cancelDamage(LivingHurtEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            IStand props = JojoProvider.get(player);
            if(props.getStandID() == JojoLibs.StandID.GER)
            {
                event.setCanceled(true);
                if(event.getSource().getTrueSource() instanceof PlayerEntity)
                    event.getSource().getTrueSource().world.playSound(null, event.getSource().getTrueSource().getPosX(), event.getSource().getTrueSource().getPosY(), event.getSource().getTrueSource().getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                else if(event.getSource().getTrueSource() instanceof MobEntity)
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                else if(event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension != DimensionType.THE_END) {
                    player.setPositionAndUpdate(player.getPosX(), JojoLibs.getHighestBlock(player.world, player.getPosition()) + 1, player.getPosZ());
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }
                else if(event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension == DimensionType.THE_END)
                {
                    if(JojoLibs.getNearestBlockEnd(player.world, player.getPosition()) != null)
                        player.setPositionAndUpdate(JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getX(), JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getY() + 1, JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getZ());
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }
            }
        }
    }

    @SubscribeEvent
    public static void stopExplosion(ExplosionEvent.Start event)
    {
        PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
        if(player != null)
        {
            IStand props = JojoProvider.get(player);
            if (event.getExplosion().getPosition().distanceTo(player.getPositionVec()) < 10.0f)
            {
                if (props.getStandID() == JojoLibs.StandID.GER)
                    event.setCanceled(true);
            }
        }
    }
}
