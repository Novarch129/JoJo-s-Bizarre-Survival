package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.entities.stands.theWorld.EntityTheWorld;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.init.SoundInit;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventStopTime {
    static EntityTheWorld theWorld = null;
    static long dayTime = 0;
    static long gameTime = 0;

    @SubscribeEvent
    public static void stopTime(StandEvent.StandTickEvent event) {
        PlayerEntity player = event.getPlayer();
        if (event.getStand() instanceof EntityTheWorld) {
            theWorld = (EntityTheWorld) event.getStand();
            if (theWorld.ability) {
                if (!theWorld.world.isRemote) {
                    if (theWorld.timestopTick == 1) {
                        dayTime = theWorld.world.getDayTime();
                        gameTime = theWorld.world.getGameTime();
                    } else {
                        theWorld.world.setDayTime(dayTime);
                        theWorld.world.setGameTime(gameTime);
                    }
                    theWorld.world.getServer().getWorld(theWorld.dimension).getEntities()
                            .filter(entity -> entity != theWorld)
                            .filter(entity -> entity != player)
                            .forEach(entity -> {
                                if (theWorld.timestopTick == 1) {
                                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                                        props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                        props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                        props.setRotation(entity.rotationYaw, entity.rotationPitch);
                                        if(entity instanceof TNTEntity)
                                            props.setFuse(((TNTEntity) entity).getFuse());
                                    });
                                } else {
                                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                                        if (props.getPosX() != 0 && props.getPosY() != 0 && props.getPosZ() != 0) {
                                            entity.setPosition(props.getPosX(), props.getPosY(), props.getPosZ());
                                            entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                            entity.rotationYaw = props.getRotationYaw();
                                            entity.rotationPitch = props.getRotationPitch();
                                            if(entity instanceof TNTEntity)
                                                ((TNTEntity) entity).setFuse(props.getFuse());
                                        } else {
                                            props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                            props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                            props.setRotation(entity.rotationYaw, entity.rotationPitch);
                                            if(entity instanceof TNTEntity)
                                                props.setFuse(((TNTEntity) entity).getFuse());
                                        }
                                    });
                                }
                            });
                }
            }
        }
    }

    @SubscribeEvent
    public static void fluidEvent(BlockEvent.FluidPlaceBlockEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void entityPlaceEvent(EntityJoinWorldEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                if(!(event.getEntity() instanceof PlayerEntity))
                    event.setCanceled(true);
    }

//    @SubscribeEvent
//    public static void soundPlaceEvent(PlaySoundEvent event) {
//        if(theWorld != null)
//            if(theWorld.ability)
//                if(event.getSound().getSoundLocation() == SoundInit.STOP_TIME.get().getName())
//                    event.setCanceled(true);
//    }
}
