package io.github.novarch129.jojomod.entity.stands;

import io.github.novarch129.jojomod.capability.timestop.ITimestop;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
import io.github.novarch129.jojomod.entity.stands.attacks.TheWorldPunchEntity;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.IStand;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.TNTMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TheWorldEntity extends AbstractStandEntity {
    public static long dayTime = -1, gameTime = -1;
    public static TheWorldEntity theWorld;
    public int timestopTick;
    public boolean cooldown ;

    public TheWorldEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_THE_WORLD.get();
    }

    public TheWorldEntity(World world) {
        super(EntityInit.THE_WORLD.get(), world);
        spawnSound = SoundInit.SPAWN_THE_WORLD.get();
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (theWorld != null) {
            if (theWorld.ability && !theWorld.cooldown) {
                if (dayTime != -1 && gameTime != -1) {
                    world.setDayTime(dayTime);
                    world.setGameTime(gameTime);
                } else {
                    dayTime = world.getDayTime();
                    gameTime = world.getGameTime();
                }
            }
        } else if (theWorld == null && StarPlatinumEntity.starPlatinum == null) {
            if (!world.isRemote) {
                Objects.requireNonNull(world.getServer(), "Null MinecraftServer on line 66 of EventTheWorldStopTime.").getWorld(world.dimension.getType()).getEntities()
                        .filter(entity -> !(entity instanceof PlayerEntity))
                        .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props -> {
                            if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)) {
                                entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                entity.setNoGravity(false);
                            } else {
                                if (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)
                                    entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                            }
                            if (entity instanceof MobEntity)
                                ((MobEntity) entity).setNoAI(false);
                            entity.velocityChanged = true;
                            if (props.getFallDistance() != 0)
                                entity.fallDistance = props.getFallDistance();
                            dayTime = -1;
                            gameTime = -1;
                            props.clear();
                        }));
            }
        }
    }

    @SubscribeEvent
    public static void fluidEvent(BlockEvent.FluidPlaceBlockEvent event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getEntity() != null)
                    if (event.getEntity().getUniqueID() != theWorld.getMaster().getUniqueID())
                        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void pistonEvent(PistonEvent.Pre event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract1(PlayerInteractEvent.EntityInteractSpecific event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract2(PlayerInteractEvent.EntityInteract event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract3(PlayerInteractEvent.RightClickBlock event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract4(PlayerInteractEvent.RightClickItem event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void playerInteract5(PlayerInteractEvent.LeftClickBlock event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getPlayer().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public static void enderTeleport(EnderTeleportEvent event) {
        if (theWorld != null)
            if (theWorld.ability && !theWorld.cooldown)
                if (event.getEntity().getUniqueID() != theWorld.getMaster().getUniqueID())
                    event.setCanceled(true);
    }

    @Override
    public void playSpawnSound() {
        Stand.getLazyOptional(getMaster()).ifPresent(props -> {
            if (!props.getAbility())
                world.playSound(null, getMaster().getPosition(), SoundInit.SPAWN_THE_WORLD.get(), SoundCategory.NEUTRAL, 5, 1);
        });
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.MUDARUSH.get(), SoundCategory.NEUTRAL, 5, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                TheWorldPunchEntity theWorldPunchEntity = new TheWorldPunchEntity(world, this, getMaster());
                theWorldPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 3, 0.1f);
                world.addEntity(theWorldPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        fallDistance = 0.0f;

        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props2 -> {
                ability = props2.getAbility();

                if (ability && props2.getTimeLeft() > 780) {
                    props2.subtractTimeLeft(1);
                    Timestop.getLazyOptional(player).ifPresent(ITimestop::clear);
                    timestopTick++;
                    player.setInvulnerable(true);
                    if (timestopTick == 1 && props2.getCooldown() <= 0)
                        world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.STOP_TIME.get(), getSoundCategory(), 5.0f, 1.0f);
                    theWorld = this;

                    if (!world.isRemote) {
                        if (timestopTick == 1 || dayTime == -1 || gameTime == -1) {
                            dayTime = world.getDayTime();
                            gameTime = world.getGameTime();
                        }
                        world.getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity != this)
                                .filter(entity -> entity != player)
                                .filter(entity -> !(entity instanceof GoldExperienceRequiemEntity))
                                .forEach(entity -> {
                                    if (entity instanceof PlayerEntity) {
                                        IStand props = Stand.getCapabilityFromPlayer((PlayerEntity) entity);
                                        if (props.getStandID() == Util.StandID.GER)
                                            return;
                                        if (props.getStandID() == Util.StandID.THE_WORLD && props.getAbility() && props.getStandOn() && props.getCooldown() <= 0)
                                            return;
                                        if (props.getStandID() == Util.StandID.STAR_PLATINUM && props.getAbility() && props.getStandOn() && props.getCooldown() <= 0)
                                            return;
                                    }
                                    if (entity instanceof MobEntity) {
                                        if (((MobEntity) entity).getAttackTarget() == player || ((MobEntity) entity).getRevengeTarget() == player) {
                                            ((MobEntity) entity).setAttackTarget(null);
                                            ((MobEntity) entity).setRevengeTarget(null);
                                        }
                                        ((MobEntity) entity).setNoAI(true);
                                    }
                                    if (this.timestopTick == 1) {
                                        Timestop.getLazyOptional(entity).ifPresent(props -> {
                                            props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                            props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                            props.setRotation(entity.rotationYaw, entity.rotationPitch, entity.getRotationYawHead());
                                            props.setFallDistance(entity.fallDistance);
                                            props.setFire(entity.getFireTimer());
                                            if (entity instanceof TNTEntity)
                                                props.setFuse(((TNTEntity) entity).getFuse());
                                            if (entity instanceof TNTMinecartEntity)
                                                props.setFuse(((TNTMinecartEntity) entity).minecartTNTFuse);
                                            if (entity instanceof ItemEntity)
                                                props.setAge(((ItemEntity) entity).age);
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
                                                if (entity instanceof PlayerEntity)
                                                    ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 255, false, false));
                                                entity.setMotion(0, 0, 0);
                                                entity.fallDistance = props.getFallDistance();
                                                entity.setFireTimer(props.getFire());
                                                if (entity instanceof TNTEntity)
                                                    ((TNTEntity) entity).setFuse(props.getFuse());
                                                if (entity instanceof TNTMinecartEntity)
                                                    ((TNTMinecartEntity) entity).minecartTNTFuse = props.getFuse();
                                                if (entity instanceof ItemEntity)
                                                    ((ItemEntity) entity).age = props.getAge();
                                                entity.velocityChanged = true;
                                            } else {
                                                props.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                                                props.setMotion(entity.getMotion().getX(), entity.getMotion().getY(), entity.getMotion().getZ());
                                                props.setRotation(entity.rotationYaw, entity.rotationPitch, entity.getRotationYawHead());
                                                props.setFallDistance(entity.fallDistance);
                                                props.setFire(entity.getFireTimer());
                                                if (entity instanceof TNTEntity)
                                                    props.setFuse(((TNTEntity) entity).getFuse());
                                                if (entity instanceof TNTMinecartEntity)
                                                    props.setFuse(((TNTMinecartEntity) entity).minecartTNTFuse);
                                                if (entity instanceof ItemEntity)
                                                    props.setAge(((ItemEntity) entity).age);
                                            }
                                        });
                                    }
                                });
                    }
                } else if (!ability || props2.getTimeLeft() <= 780) {
                    timestopTick = 0;
                    player.setInvulnerable(false);
                    theWorld = null;
                    if (!this.world.isRemote) {
                        this.world.getServer().getWorld(this.dimension).getEntities()
                                .filter(entity -> entity != this)
                                .filter(entity -> entity != player)
                                .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props -> {
                                    if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)) {
                                        entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                        entity.setNoGravity(false);
                                    } else {
                                        if (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)
                                            entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                    }
                                    if (entity instanceof PlayerEntity)
                                        ((PlayerEntity) entity).removePotionEffect(Effects.SLOWNESS);
                                    if (entity instanceof MobEntity)
                                        ((MobEntity) entity).setNoAI(false);
                                    entity.velocityChanged = true;
                                    if (props.getFallDistance() != 0)
                                        entity.fallDistance = props.getFallDistance();
                                    dayTime = -1;
                                    gameTime = -1;
                                    props.clear();
                                }));
                    }
                }

                if (JojoBizarreSurvivalConfig.COMMON.infiniteTimestop.get())
                    props2.setTimeLeft(1000);

                if (props2.getTimeLeft() == 781) {
                    props2.setCooldown(201);
                    cooldown = true;
                }

                if (props2.getTimeLeft() == 831)
                    world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.RESUME_TIME.get(), getSoundCategory(), 5.0f, 1.0f);

                if (props2.getCooldown() > 0)
                    props2.subtractCooldown(1);

                if (props2.getCooldown() == 1) {
                    props2.setTimeLeft(1000);
                    cooldown = false;
                }

                if (!ability && props2.getTimeLeft() < 1000)
                    props2.addTimeLeft(1);

                if (!ability) {
                    timestopTick = 0;
                    player.setInvulnerable(false);
                }
            });

            followMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        TheWorldPunchEntity theWorld1 = new TheWorldPunchEntity(world, this, player);
                        theWorld1.setRandomPositions();
                        theWorld1.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.15f);
                        world.addEntity(theWorld1);
                        TheWorldPunchEntity theWorld2 = new TheWorldPunchEntity(world, this, player);
                        theWorld2.setRandomPositions();
                        theWorld2.shoot(player, player.rotationPitch, player.rotationYaw, 2.5f, 0.15f);
                        world.addEntity(theWorld2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        ability = false;
        theWorld = null;
        dayTime = -1;
        gameTime = -1;
        if (!this.world.isRemote)
            this.world.getServer().getWorld(this.dimension).getEntities()
                    .filter(entity -> entity != this)
                    .forEach(entity ->
                            Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else {
                                    if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                }
                                if (entity instanceof PlayerEntity)
                                    ((PlayerEntity) entity).removePotionEffect(Effects.SLOWNESS);
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                props2.clear();
                            }));
    }
}