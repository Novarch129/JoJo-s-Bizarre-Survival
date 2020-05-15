package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
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
            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(!props.getStandOn())
            {
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);
                if(player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                    player.removePotionEffect(EffectInit.CRIMSON_USER.get());
                
                if(props.getStandID() == JojoLibs.StandID.kingCrimson)
                {
                    /*for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, 3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
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
                    }*/
                }
                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 1000)
                    props.addTimeLeft(0.5);
            }

            else if(props.getStandOn() && !props.getAbility())
            {
                if(props.getStandID() == JojoLibs.StandID.kingCrimson)
                {
                    /*for (Entity entity : player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(7000.0, 3000.0 , 7000.0)), EntityPredicates.NOT_SPECTATING))
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
                    }*/

                    for(PlayerEntity entity : player.world.getPlayers())
                    {
                        if(entity.isPotionActive(EffectInit.CRIMSON.get()))
                            entity.removePotionEffect(EffectInit.CRIMSON.get());
                    }
                }
                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 100)
                    props.addTimeLeft(0.5);
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

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event)
    {
        if(Minecraft.getInstance()==null)
            return;
        if(Minecraft.getInstance().player==null)
            return;

        PlayerEntity player = Minecraft.getInstance().player;
        ClientWorld world = Minecraft.getInstance().world;

        JojoProvider.getLazy(player).ifPresent(props -> {
            if(!props.getStandOn()) {
                Minecraft.getInstance().setRenderViewEntity(player);
                if(props.getStandID() == JojoLibs.StandID.kingCrimson) {
                    world.getAllEntities().forEach(entity -> {
                        if(entity instanceof MobEntity)
                            if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                    });
                }
            }
            else {
                if(props.getStandID() == JojoLibs.StandID.kingCrimson) {
                    if(!props.getAbility()) {
                        world.getAllEntities().forEach(entity -> {
                            if(entity instanceof MobEntity)
                                if(((MobEntity) entity).isPotionActive(EffectInit.CRIMSON.get()))
                                    ((MobEntity) entity).removePotionEffect(EffectInit.CRIMSON.get());
                        });
                    }
                }
            }
        });
    }
}
