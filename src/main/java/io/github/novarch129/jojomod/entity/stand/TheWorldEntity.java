package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.ITimestop;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.Timestop;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.stand.attack.TheWorldPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ArrayBlockingQueue;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TheWorldEntity extends AbstractStandEntity {
    public static long dayTime = -1, gameTime = -1;
    /**
     * A list of every {@link TheWorldEntity} in the {@link World}, used to cancel events and unfreeze entities on logout.
     */
    private static ArrayBlockingQueue<TheWorldEntity> theWorldList = new ArrayBlockingQueue<>(1000000);
    public int timestopTick;
    public boolean shouldDamageBeCancelled;
    public boolean cooldown;
    private ArrayBlockingQueue<BlockPos> brokenBlocks = new ArrayBlockingQueue<>(100000);

    public TheWorldEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    public static ArrayBlockingQueue<TheWorldEntity> getTheWorldList() {
        return theWorldList;
    }

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (theWorldList.size() > 0) {
            theWorldList.forEach(theWorld -> {
                if (theWorld.ability && !theWorld.cooldown) {
                    if (dayTime != -1 && gameTime != -1) {
                        world.setDayTime(dayTime);
                        world.setGameTime(gameTime);
                    } else {
                        dayTime = world.getDayTime();
                        gameTime = world.getGameTime();
                    }
                }
            });
        } else if (theWorldList.size() <= 0 && StarPlatinumEntity.getStarPlatinumList().size() <= 0) {
            if (!world.isRemote) {
                world.getServer().getWorld(world.dimension.getType()).getEntities()
                        .filter(entity -> !(entity instanceof PlayerEntity))
                        .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props -> {
                            if (props.isEmpty())
                                return;
                            if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)) {
                                entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                entity.setNoGravity(false);
                            } else if (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)
                                entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                            if (entity instanceof MobEntity)
                                ((MobEntity) entity).setNoAI(false);
                            entity.velocityChanged = true;
                            if (props.getFallDistance() != 0)
                                entity.fallDistance = props.getFallDistance();
                            if (props.getDamage().size() > 0)
                                props.getDamage().forEach((source, amount) -> {
                                    DamageSource damageSource = DamageSource.GENERIC;
                                    String newSource = source.replaceAll("[0123456789]", "");
                                    switch (newSource) {
                                        case "inFire": {
                                            damageSource = DamageSource.IN_FIRE;
                                            break;
                                        }
                                        case "onFire": {
                                            damageSource = DamageSource.ON_FIRE;
                                            break;
                                        }
                                        case "lightningBolt": {
                                            damageSource = DamageSource.LIGHTNING_BOLT;
                                            break;
                                        }
                                        case "lava": {
                                            damageSource = DamageSource.LAVA;
                                            break;
                                        }
                                        case "hotFloor": {
                                            damageSource = DamageSource.HOT_FLOOR;
                                            break;
                                        }
                                        case "inWall": {
                                            damageSource = DamageSource.IN_WALL;
                                            break;
                                        }
                                        case "cramming": {
                                            damageSource = DamageSource.CRAMMING;
                                            break;
                                        }
                                        case "drown": {
                                            damageSource = DamageSource.DROWN;
                                            break;
                                        }
                                        case "starve": {
                                            damageSource = DamageSource.STARVE;
                                            break;
                                        }
                                        case "cactus": {
                                            damageSource = DamageSource.CACTUS;
                                            break;
                                        }
                                        case "fall": {
                                            damageSource = DamageSource.FALL;
                                            break;
                                        }
                                        case "flyIntoWall": {
                                            damageSource = DamageSource.FLY_INTO_WALL;
                                            break;
                                        }
                                        case "outOfWorld": {
                                            damageSource = DamageSource.OUT_OF_WORLD;
                                            break;
                                        }
                                        case "magic": {
                                            damageSource = DamageSource.MAGIC;
                                            break;
                                        }
                                        case "wither": {
                                            damageSource = DamageSource.WITHER;
                                            break;
                                        }
                                        case "anvil": {
                                            damageSource = DamageSource.ANVIL;
                                            break;
                                        }
                                        case "fallingBlock": {
                                            damageSource = DamageSource.FALLING_BLOCK;
                                            break;
                                        }
                                        case "dragonBreath": {
                                            damageSource = DamageSource.DRAGON_BREATH;
                                            break;
                                        }
                                        case "fireworks": {
                                            damageSource = DamageSource.FIREWORKS;
                                            break;
                                        }
                                        case "dryout": {
                                            damageSource = DamageSource.DRYOUT;
                                            break;
                                        }
                                        case "sweetBerryBush": {
                                            damageSource = DamageSource.SWEET_BERRY_BUSH;
                                            break;
                                        }
                                    }
                                    entity.attackEntityFrom(damageSource, amount);
                                    entity.hurtResistantTime = 0;
                                });
                            dayTime = -1;
                            gameTime = -1;
                            props.clear();
                        }));
            }
        }
    }

    @SubscribeEvent
    public static void fluidEvent(BlockEvent.FluidPlaceBlockEvent event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    event.setCanceled(true);
            });

    }

    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void blockPlaceEvent(BlockEvent.EntityPlaceEvent event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown) {
                    if (event.getEntity() == null)
                        event.setCanceled(true);
                    else {
                        if (event.getEntity().getUniqueID() != theWorldEntity.master.getUniqueID())
                            event.setCanceled(true);
                    }
                }
            });
    }

    @SubscribeEvent
    public static void pistonEvent(PistonEvent.Pre event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void playerInteract1(PlayerInteractEvent.EntityInteractSpecific event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void playerInteract2(PlayerInteractEvent.EntityInteract event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void playerInteract3(PlayerInteractEvent.RightClickBlock event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void playerInteract4(PlayerInteractEvent.RightClickItem event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void playerInteract5(PlayerInteractEvent.LeftClickBlock event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getPlayer().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void enderTeleport(EnderTeleportEvent event) {
        if (theWorldList.size() > 0)
            theWorldList.forEach(theWorldEntity -> {
                if (theWorldEntity.ability && !theWorldEntity.cooldown)
                    if (event.getEntity().getUniqueID() != theWorldEntity.master.getUniqueID())
                        event.setCanceled(true);
            });
    }

    public void teleport(double multiplier) {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            if (props.getCooldown() == 0) {
                Vec3d position = master.getLookVec().mul(7 * multiplier, 1, 7 * multiplier).add(master.getPositionVec());
                for (double i = position.getY() - 0.5; world.getBlockState(new BlockPos(position.getX(), i, position.getZ())).isSolid(); i++)
                    position = position.add(0, 0.5, 0);
                if (world.getBlockState(new BlockPos(position)).isSolid())
                    return;
                master.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());
                world.playSound(null, master.getPosition(), SoundInit.THE_WORLD_TELEPORT.get(), SoundCategory.HOSTILE, 1, 1);
                props.setCooldown(200);
            }
        });
    }

    public void dodgeAttacks() {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(stand -> {
            if (stand.getCooldown() == 0 && stand.getInvulnerableTicks() == 0)
                stand.setInvulnerableTicks(100);
        });
    }

    public void addBrokenBlocks(BlockPos pos) {
        brokenBlocks.add(pos);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_THE_WORLD.get();
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

    public ArrayBlockingQueue<BlockPos> getBrokenBlocks() {
        return brokenBlocks;
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props2 -> {
                ability = props2.getAbility();
                props2.setAbilityActive(ability && props2.getTimeLeft() > 780 && props2.getCooldown() == 0 && props2.getInvulnerableTicks() == 0);

                if (ability && props2.getTimeLeft() > 780 && props2.getInvulnerableTicks() == 0)
                    props2.setTimeLeft(props2.getTimeLeft() - 1);

                if (props2.getAbilityActive()) {
                    Timestop.getLazyOptional(master).ifPresent(ITimestop::clear);
                    timestopTick++;
                    shouldDamageBeCancelled = true;
                    master.setInvulnerable(true);
                    if (timestopTick == 1 && props2.getCooldown() <= 0)
                        world.playSound(null, getPosition(), SoundInit.STOP_TIME.get(), getSoundCategory(), 5, 1);
                    if (!theWorldList.contains(this))
                        theWorldList.add(this);

                    if (!world.isRemote) {
                        if (timestopTick == 1 || dayTime == -1 || gameTime == -1) {
                            dayTime = world.getDayTime();
                            gameTime = world.getGameTime();
                        }
                        world.getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity != this)
                                .filter(entity -> entity != master)
                                .filter(entity -> !(entity instanceof GoldExperienceRequiemEntity))
                                .forEach(entity -> {
                                    if (entity instanceof PlayerEntity) {
                                        Stand props = Stand.getCapabilityFromPlayer((PlayerEntity) entity);
                                        if (props.getStandID() == Util.StandID.GER)
                                            return;
                                        if (props.getStandID() == Util.StandID.THE_WORLD && props.getAbility() && props.getStandOn() && props.getCooldown() <= 0)
                                            return;
                                        if (props.getStandID() == Util.StandID.STAR_PLATINUM && props.getAbility() && props.getStandOn() && props.getCooldown() <= 0)
                                            return;
                                    }
                                    if (entity instanceof MobEntity) {
                                        if (((MobEntity) entity).getAttackTarget() == master || ((MobEntity) entity).getRevengeTarget() == master) {
                                            ((MobEntity) entity).setAttackTarget(null);
                                            ((MobEntity) entity).setRevengeTarget(null);
                                        }
                                        ((MobEntity) entity).setNoAI(true);
                                    }
                                    if (timestopTick == 1) {
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
                } else {
                    shouldDamageBeCancelled = false;
                    timestopTick = 0;
                    master.setInvulnerable(false);
                    theWorldList.remove(this);
                    brokenBlocks.forEach(pos -> {
                        world.getBlockState(pos).getBlock().harvestBlock(world, master, pos, world.getBlockState(pos), null, master.getActiveItemStack());
                        world.removeBlock(pos, false);
                    });
                    brokenBlocks.clear();
                    if (!world.isRemote) {
                        world.getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity != this)
                                .filter(entity -> entity != master)
                                .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props -> {
                                    if (props.isEmpty())
                                        return;
                                    if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)) {
                                        entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                        entity.setNoGravity(false);
                                    } else if (props.getMotionX() != 0 && props.getMotionY() != 0 && props.getMotionZ() != 0)
                                        entity.setMotion(props.getMotionX(), props.getMotionY(), props.getMotionZ());
                                    if (entity instanceof PlayerEntity)
                                        ((PlayerEntity) entity).removePotionEffect(Effects.SLOWNESS);
                                    if (entity instanceof MobEntity)
                                        ((MobEntity) entity).setNoAI(false);
                                    entity.velocityChanged = true;
                                    if (props.getFallDistance() != 0)
                                        entity.fallDistance = props.getFallDistance();
                                    if (props.getDamage().size() > 0)
                                        props.getDamage().forEach((source, amount) -> {
                                            DamageSource damageSource = DamageSource.GENERIC;
                                            String newSource = source.replaceAll("[0123456789]", "");
                                            switch (newSource) {
                                                case "inFire": {
                                                    damageSource = DamageSource.IN_FIRE;
                                                    break;
                                                }
                                                case "onFire": {
                                                    damageSource = DamageSource.ON_FIRE;
                                                    break;
                                                }
                                                case "lightningBolt": {
                                                    damageSource = DamageSource.LIGHTNING_BOLT;
                                                    break;
                                                }
                                                case "lava": {
                                                    damageSource = DamageSource.LAVA;
                                                    break;
                                                }
                                                case "hotFloor": {
                                                    damageSource = DamageSource.HOT_FLOOR;
                                                    break;
                                                }
                                                case "inWall": {
                                                    damageSource = DamageSource.IN_WALL;
                                                    break;
                                                }
                                                case "cramming": {
                                                    damageSource = DamageSource.CRAMMING;
                                                    break;
                                                }
                                                case "drown": {
                                                    damageSource = DamageSource.DROWN;
                                                    break;
                                                }
                                                case "starve": {
                                                    damageSource = DamageSource.STARVE;
                                                    break;
                                                }
                                                case "cactus": {
                                                    damageSource = DamageSource.CACTUS;
                                                    break;
                                                }
                                                case "fall": {
                                                    damageSource = DamageSource.FALL;
                                                    break;
                                                }
                                                case "flyIntoWall": {
                                                    damageSource = DamageSource.FLY_INTO_WALL;
                                                    break;
                                                }
                                                case "outOfWorld": {
                                                    damageSource = DamageSource.OUT_OF_WORLD;
                                                    break;
                                                }
                                                case "magic": {
                                                    damageSource = DamageSource.MAGIC;
                                                    break;
                                                }
                                                case "wither": {
                                                    damageSource = DamageSource.WITHER;
                                                    break;
                                                }
                                                case "anvil": {
                                                    damageSource = DamageSource.ANVIL;
                                                    break;
                                                }
                                                case "fallingBlock": {
                                                    damageSource = DamageSource.FALLING_BLOCK;
                                                    break;
                                                }
                                                case "dragonBreath": {
                                                    damageSource = DamageSource.DRAGON_BREATH;
                                                    break;
                                                }
                                                case "fireworks": {
                                                    damageSource = DamageSource.FIREWORKS;
                                                    break;
                                                }
                                                case "dryout": {
                                                    damageSource = DamageSource.DRYOUT;
                                                    break;
                                                }
                                                case "sweetBerryBush": {
                                                    damageSource = DamageSource.SWEET_BERRY_BUSH;
                                                    break;
                                                }
                                            }
                                            entity.attackEntityFrom(damageSource, amount);
                                            entity.hurtResistantTime = 0;
                                        });
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
                    world.playSound(null, getPosition(), SoundInit.RESUME_TIME.get(), getSoundCategory(), 5, 1);

                if (props2.getCooldown() == 1) {
                    props2.setTimeLeft(1000);
                    cooldown = false;
                }

                if (!ability) {
                    timestopTick = 0;
                    master.setInvulnerable(false);
                }
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        TheWorldPunchEntity theWorld1 = new TheWorldPunchEntity(world, this, master);
                        theWorld1.randomizePositions();
                        theWorld1.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.15f);
                        world.addEntity(theWorld1);
                        TheWorldPunchEntity theWorld2 = new TheWorldPunchEntity(world, this, master);
                        theWorld2.randomizePositions();
                        theWorld2.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.15f);
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
        master.setInvulnerable(false);
        shouldDamageBeCancelled = false;
        theWorldList.remove(this);
        brokenBlocks.forEach(pos -> {
            world.getBlockState(pos).getBlock().harvestBlock(world, master, pos, world.getBlockState(pos), null, master.getActiveItemStack());
            world.removeBlock(pos, false);
        });
        brokenBlocks.clear();
        dayTime = -1;
        gameTime = -1;
        if (!world.isRemote)
            world.getServer().getWorld(dimension).getEntities()
                    .filter(entity -> entity != this)
                    .forEach(entity ->
                            Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if (props2.isEmpty())
                                    return;
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                if (entity instanceof PlayerEntity)
                                    ((PlayerEntity) entity).removePotionEffect(Effects.SLOWNESS);
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                if (props2.getDamage().size() > 0)
                                    props2.getDamage().forEach((source, amount) -> {
                                        DamageSource damageSource = DamageSource.GENERIC;
                                        switch (source) {
                                            case "inFire": {
                                                damageSource = DamageSource.IN_FIRE;
                                                break;
                                            }
                                            case "onFire": {
                                                damageSource = DamageSource.ON_FIRE;
                                                break;
                                            }
                                            case "lightningBolt": {
                                                damageSource = DamageSource.LIGHTNING_BOLT;
                                                break;
                                            }
                                            case "lava": {
                                                damageSource = DamageSource.LAVA;
                                                break;
                                            }
                                            case "hotFloor": {
                                                damageSource = DamageSource.HOT_FLOOR;
                                                break;
                                            }
                                            case "inWall": {
                                                damageSource = DamageSource.IN_WALL;
                                                break;
                                            }
                                            case "cramming": {
                                                damageSource = DamageSource.CRAMMING;
                                                break;
                                            }
                                            case "drown": {
                                                damageSource = DamageSource.DROWN;
                                                break;
                                            }
                                            case "starve": {
                                                damageSource = DamageSource.STARVE;
                                                break;
                                            }
                                            case "cactus": {
                                                damageSource = DamageSource.CACTUS;
                                                break;
                                            }
                                            case "fall": {
                                                damageSource = DamageSource.FALL;
                                                break;
                                            }
                                            case "flyIntoWall": {
                                                damageSource = DamageSource.FLY_INTO_WALL;
                                                break;
                                            }
                                            case "outOfWorld": {
                                                damageSource = DamageSource.OUT_OF_WORLD;
                                                break;
                                            }
                                            case "magic": {
                                                damageSource = DamageSource.MAGIC;
                                                break;
                                            }
                                            case "wither": {
                                                damageSource = DamageSource.WITHER;
                                                break;
                                            }
                                            case "anvil": {
                                                damageSource = DamageSource.ANVIL;
                                                break;
                                            }
                                            case "fallingBlock": {
                                                damageSource = DamageSource.FALLING_BLOCK;
                                                break;
                                            }
                                            case "dragonBreath": {
                                                damageSource = DamageSource.DRAGON_BREATH;
                                                break;
                                            }
                                            case "fireworks": {
                                                damageSource = DamageSource.FIREWORKS;
                                                break;
                                            }
                                            case "dryout": {
                                                damageSource = DamageSource.DRYOUT;
                                                break;
                                            }
                                            case "sweetBerryBush": {
                                                damageSource = DamageSource.SWEET_BERRY_BUSH;
                                                break;
                                            }
                                        }
                                        entity.attackEntityFrom(damageSource, amount);
                                        entity.hurtResistantTime = 0;
                                    });
                                props2.clear();
                            }));
    }
}
