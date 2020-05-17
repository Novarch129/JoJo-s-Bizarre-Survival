package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities
{
    public static Entity renderViewEntity = null;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        JojoProvider.getLazy(player).ifPresent(props -> {
            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(!props.getStandOn())
            {
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);

                if(player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                    player.removePotionEffect(EffectInit.CRIMSON_USER.get());

                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 1000)
                    props.addTimeLeft(0.5);
            }

            else if(props.getStandOn() && !props.getAbility())
            {
                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 100)
                    props.addTimeLeft(0.5);
            }

            if(!player.world.isRemote)
                player.world.getServer().getWorld(player.dimension).getEntities().forEach(entity -> {
                    if(entity instanceof EntityAerosmith)
                        if(((EntityAerosmith) entity).getMaster() == player)
                            renderViewEntity = entity;
                });
        });
    }

    @SubscribeEvent
    public static void crimsonEffectAddedHandler(PotionEvent.PotionAddedEvent event)
    {
        if(event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(true);
    }

    @SubscribeEvent
    public static void crimsonEffectRemovedHandler(PotionEvent.PotionRemoveEvent event)
    {
        if(event.getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if(event.getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event)
    {
        if(Minecraft.getInstance().player==null)
            return;

        PlayerEntity player = Minecraft.getInstance().player;

        JojoProvider.getLazy(player).ifPresent(props -> {
            if(!props.getStandOn()) {
                if(!player.isSpectator())
                    Minecraft.getInstance().setRenderViewEntity(player);
            } else {
                if(renderViewEntity != null)
                    if(renderViewEntity.isAlive())
                        if(props.getAbility()) {
                            Minecraft.getInstance().setRenderViewEntity(renderViewEntity);
                            if(Minecraft.getInstance().gameSettings.thirdPersonView != 1)
                                Minecraft.getInstance().gameSettings.thirdPersonView=1;
                        }
                    else
                        Minecraft.getInstance().setRenderViewEntity(player);
            }
        });
    }

    @SubscribeEvent
    public static void aerosmithFlyEvent(LivingFallEvent event)
    {
        if(event.getEntityLiving() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            JojoProvider.getLazy(player).ifPresent(props -> {
                if(props.getStandID() == JojoLibs.StandID.GER)
                    if(props.getAbility())
                        if(event.isCancelable())
                            event.setCanceled(true);
            });
        }

        if(event.getEntityLiving() instanceof EntityAerosmith)
            event.setCanceled(true);
    }
}
