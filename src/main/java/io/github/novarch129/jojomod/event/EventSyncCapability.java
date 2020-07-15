package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.FakePlayerEntity;
import io.github.novarch129.jojomod.entity.stand.SheerHeartAttackEntity;
import io.github.novarch129.jojomod.entity.stand.TheWorldEntity;
import io.github.novarch129.jojomod.network.message.server.SSyncStandCapabilityPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

import static io.github.novarch129.jojomod.util.Util.StandID.THE_WORLD;

/**
 * Syncs the {@link Stand} capability to the client, for use in GUIs, {@link Timestop} is not synced because it's info is disposable and useless to the client.
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventSyncCapability {
    @SubscribeEvent
    public static void saveStand(PlayerEvent.Clone event) {
        if (!event.isWasDeath() || JojoBizarreSurvivalConfig.COMMON.saveStandOnDeath.get()) {
            Stand.getLazyOptional(event.getOriginal()).ifPresent(originalProps -> {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                Stand.getLazyOptional(player).ifPresent(newProps -> newProps.clone(originalProps));
            });
        }
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props)));
    }

    @SubscribeEvent
    public static void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        player.setInvulnerable(false);
        Stand.getLazyOptional(player).ifPresent(props -> {
            props.putAct(0); //Prevents some stupidly obvious issues with the wrong Stand being summoned, IStand#putAct used because line 123 syncs to the client anyway.
            if (!player.world.isRemote) {
                player.getServerWorld().getEntities()
                        .filter(entity -> entity instanceof FakePlayerEntity)
                        .filter(entity -> ((FakePlayerEntity) entity).getParent() == player)
                        .forEach(Entity::remove);
                player.getServerWorld().getEntities()
                        .filter(entity -> entity instanceof SheerHeartAttackEntity)
                        .filter(entity -> ((SheerHeartAttackEntity) entity).getMaster() == player)
                        .forEach(Entity::remove);
                if (props.getStandID() == THE_WORLD) {
                    player.getServerWorld().getEntities()
                            .forEach(entity -> {
                                Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                    if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                        entity.setNoGravity(false);
                                    } else {
                                        if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                            entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    }
                                    if (entity instanceof MobEntity)
                                        ((MobEntity) entity).setNoAI(false);
                                    entity.velocityChanged = true;
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.fallDistance = props2.getFallDistance();
                                    entity.setInvulnerable(false);
                                    props2.clear();

                                });
                                if (entity instanceof TheWorldEntity)
                                    if (entity == TheWorldEntity.theWorld)
                                        TheWorldEntity.theWorld = null;
                            });
                } else if (props.getStandID() == Util.StandID.STAR_PLATINUM) {
                    player.getServerWorld().getEntities()
                            .forEach(entity -> {
                                Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                    if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                        entity.setNoGravity(false);
                                    } else {
                                        if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                            entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    }
                                    if (entity instanceof MobEntity)
                                        ((MobEntity) entity).setNoAI(false);
                                    entity.velocityChanged = true;
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.fallDistance = props2.getFallDistance();
                                    entity.setInvulnerable(false);
                                    props2.clear();

                                });
                                if (entity instanceof TheWorldEntity)
                                    if (entity == TheWorldEntity.theWorld)
                                        TheWorldEntity.theWorld = null;
                            });
                }
                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SSyncStandCapabilityPacket(props));
            }
        });
    }
}
