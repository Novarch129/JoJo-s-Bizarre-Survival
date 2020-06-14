package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.theWorld.EntityTheWorld;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventStopTime {
    public static EntityTheWorld theWorld;
    public static long dayTime = -1;
    public static long gameTime = -1;

    @SubscribeEvent
    public static void stopTime(StandEvent.StandTickEvent event) {
        PlayerEntity player = event.getPlayer();
        if (event.getStand() instanceof EntityTheWorld) {
            theWorld = (EntityTheWorld) event.getStand();
            if (theWorld.ability && theWorld.isAlive()) {
                if (!theWorld.world.isRemote) {
                    if (theWorld.timestopTick == 1 || dayTime == -1 || gameTime == -1) {
                        dayTime = theWorld.world.getDayTime();
                        gameTime = theWorld.world.getGameTime();
                    }
                    theWorld.world.getServer().getWorld(theWorld.dimension).getEntities()
                            .filter(entity -> entity != theWorld)
                            .filter(entity -> entity != player)
                            .filter(entity -> !(entity instanceof EntityGoldExperienceRequiem))
                            .forEach(entity -> {
                                if(entity instanceof PlayerEntity) {
                                    IStand props = Stand.getCapabilityFromPlayer((PlayerEntity) entity);
                                    if (props.getStandID() == JojoLibs.StandID.GER)
                                        return;
                                    if(props.getStandID() == JojoLibs.StandID.theWorld && props.getAbility() && props.getStandOn() && props.getCooldown() <= 0)
                                        return;
                                }
                                if(entity instanceof MobEntity) {
                                    if (((MobEntity) entity).getAttackTarget() == player || ((MobEntity) entity).getRevengeTarget() == player) {
                                        ((MobEntity) entity).setAttackTarget(null);
                                        ((MobEntity) entity).setRevengeTarget(null);
                                    }
                                    ((MobEntity) entity).setNoAI(true);
                                }
                                if (theWorld.timestopTick == 1) {
                                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                                        props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                        props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                        props.setRotation(entity.rotationYaw, entity.rotationPitch, entity.getRotationYawHead());
                                        props.setFallDistance(entity.fallDistance);
                                        props.setFire(entity.getFireTimer());
                                        if(entity instanceof TNTEntity)
                                            props.setFuse(((TNTEntity) entity).getFuse());
                                    });
                                } else {
                                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                                        if (props.getPosX() != 0 && props.getPosY() != 0 && props.getPosZ() != 0) {
                                            entity.setPosition(props.getPosX(), props.getPosY(), props.getPosZ());
                                            if ((entity instanceof IProjectile) || (entity instanceof ItemEntity) || (entity instanceof DamagingProjectileEntity))
                                                entity.setNoGravity(true);
                                            else {
                                                entity.rotationYaw = props.getRotationYaw();
                                                entity.rotationPitch = props.getRotationPitch();
                                                entity.setRotationYawHead(props.getRotationYawHead());
                                            }
                                            entity.setMotion(0, 0, 0);

                                            entity.fallDistance = props.getFallDistance();
                                            entity.setFireTimer(props.getFire());
                                            if (entity instanceof TNTEntity)
                                                ((TNTEntity) entity).setFuse(props.getFuse());
                                            entity.velocityChanged = true;
//                                            if(!entity.world.isRemote) {
//                                                int[] entities = entity.world.getServer().getWorld(entity.dimension).getEntities().mapToInt(Entity::getEntityId).toArray();
//                                                int[] velocities = new int[entities.length * 3];
//                                                entity.world.getServer().getWorld(entity.dimension).getEntities()
//                                                        .filter(entity1 -> entity1 != theWorld && entity1 != player)
//                                                        .map(Entity::getMotion)
//                                                        .forEach(i -> {
//                                                            for(int i1 = 0; i1 < velocities.length; i1+=3) {
//                                                                velocities[i1] = (int) (i.getX() * 100);
//                                                                velocities[i1+1] = (int) (i.getY() * 100);
//                                                                velocities[i1+2] = (int) (i.getZ() * 100);
//                                                            }
//                                                        });
//                                                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.DIMENSION.with(() -> entity.dimension), new STimestopPacket(entities, velocities));
//                                            }
                                        } else {
                                            props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                            props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                            props.setRotation(entity.rotationYaw, entity.rotationPitch, entity.getRotationYawHead());
                                            props.setFallDistance(entity.fallDistance);
                                            props.setFire(entity.getFireTimer());
                                            if(entity instanceof TNTEntity)
                                                props.setFuse(((TNTEntity) entity).getFuse());
                                        }
                                    });
                                }
                            });
                }
            } else {
                if(!theWorld.world.isRemote) {
                    theWorld.world.getServer().getWorld(theWorld.dimension).getEntities()
                            .filter(entity -> entity != theWorld)
                            .filter(entity -> entity != player)
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
    }

    @SubscribeEvent
    public static void fluidEvent(BlockEvent.FluidPlaceBlockEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                if(event.getPlayer() != theWorld.getMaster())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if(theWorld != null)
            if(theWorld.ability)
                if(event.getEntity() != theWorld.getMaster())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if(theWorld != null) {
            if (theWorld.ability)
                if (dayTime != -1 && gameTime != -1) {
                    world.setDayTime(dayTime);
                    world.setGameTime(gameTime);
                } else {
                    dayTime = world.getDayTime();
                    gameTime = world.getGameTime();
                }
        }
    }

    @SubscribeEvent
    public static void standRemoved(StandEvent.StandTickEvent.StandRemovedEvent event) {
        if(event.getStand() instanceof EntityTheWorld)
            if(!event.getStand().world.isRemote)
                event.getStand().world.getServer().getWorld(event.getStand().dimension).getEntities()
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

    @SubscribeEvent
    public static void pistonEvent(PistonEvent.Pre event) {
        if(theWorld!=null)
            if(theWorld.ability)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract1(PlayerInteractEvent.EntityInteractSpecific event) {
        if(theWorld!=null)
            if(theWorld.ability)
                if(event.getPlayer() != theWorld.getMaster())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract2(PlayerInteractEvent.EntityInteract event) {
        if(theWorld!=null)
            if(theWorld.ability)
                if(event.getPlayer() != theWorld.getMaster())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract3(PlayerInteractEvent.RightClickBlock event) {
        if(theWorld!=null)
            if(theWorld.ability)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract4(PlayerInteractEvent.RightClickItem event) {
        if(theWorld!=null)
            if(theWorld.ability)
                if(event.getPlayer() != theWorld.getMaster())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract5(PlayerInteractEvent.LeftClickBlock event) {
        if(theWorld!=null)
            if(theWorld.ability)
                if(event.getPlayer() != theWorld.getMaster())
                    event.setCanceled(true);
    }
}
