package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.entities.stands.theWorld.EntityTheWorld;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventTheWorldStopTime {
    public static EntityTheWorld theWorld;
    public static long dayTime = -1;
    public static long gameTime = -1;

    @SubscribeEvent
    public static void fluidEvent(BlockEvent.FluidPlaceBlockEvent event) {
        if(theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if(theWorld != null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if(theWorld != null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getEntity() != null)
                    if(event.getEntity().getUniqueID() != theWorld.getMaster().getUniqueID())
                        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if(theWorld != null) {
            if (theWorld.ability && !theWorld.cooldown) {
                if (dayTime != -1 && gameTime != -1) {
                    world.setDayTime(dayTime);
                    world.setGameTime(gameTime);
                } else {
                    dayTime = world.getDayTime();
                    gameTime = world.getGameTime();
                }
            }
        } else {
            if(!world.isRemote) {
                Objects.requireNonNull(world.getServer(), "Null MinecraftServer on line 66 of EventTheWorldStopTime.").getWorld(world.dimension.getType()).getEntities()
                        .filter(entity -> !(entity instanceof PlayerEntity))
                        .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props -> {
                            if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)) {
                                entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                entity.setNoGravity(false);
                            } else {
                                if(props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)
                                    entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                            }
                            if(entity instanceof MobEntity)
                                ((MobEntity) entity).setNoAI(false);
                            entity.velocityChanged = true;
                            if(props.getFallDistance() != 0)
                                entity.fallDistance = props.getFallDistance();
                            dayTime = -1;
                            gameTime = -1;
                            props.clear();
                        }));
            }
        }
    }

    @SubscribeEvent
    public static void pistonEvent(PistonEvent.Pre event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract1(PlayerInteractEvent.EntityInteractSpecific event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract2(PlayerInteractEvent.EntityInteract event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract3(PlayerInteractEvent.RightClickBlock event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract4(PlayerInteractEvent.RightClickItem event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract5(PlayerInteractEvent.LeftClickBlock event) {
        if(theWorld!=null)
            if(theWorld.ability && !theWorld.cooldown)
                if(event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }
}
