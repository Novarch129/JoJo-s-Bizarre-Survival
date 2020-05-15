package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.effects.CrimsonEffect;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        JojoProvider.getLazy(player).ifPresent(props -> {
            if(!props.getStandOn() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(!props.getStandOn() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);


            if(!props.getStandOn())
            {
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);
                if(player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                    player.removePotionEffect(EffectInit.CRIMSON_USER.get());
                
                if(props.getStandID() == JojoLibs.StandID.kingCrimson)
                {
                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, 3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-7000.0, 3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, 3000.0 , -7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-7000.0, 3000.0 , -7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, -3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-7000.0, -3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, -3000.0 , -7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-7000.0, -3000.0 , -7000.0)), EntityPredicates.NOT_SPECTATING))
                    {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    }

                    for(PlayerEntity entity : player.world.getPlayers())
                    {
                        if(entity.isPotionActive(EffectInit.CRIMSON.get()))
                            entity.removePotionEffect(EffectInit.CRIMSON.get());
                    }
                }
            }
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
}
