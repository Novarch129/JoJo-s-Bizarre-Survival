package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.*;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.StarPlatinumEntity;
import io.github.novarch129.jojomod.entity.stand.TheWorldEntity;
import io.github.novarch129.jojomod.event.custom.StandAttackEvent;
import io.github.novarch129.jojomod.event.custom.StandEvent;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.item.StandDiscItem;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        Stand.getLazyOptional(player).ifPresent(stand -> {
            Random rand = player.world.rand;
            int standID = stand.getStandID();
            boolean standOn = stand.getStandOn();
            boolean ability = stand.getAbility();
            double cooldown = stand.getCooldown();
            double timeLeft = stand.getTimeLeft();
            double invulnerableTicks = stand.getInvulnerableTicks();

            if (!player.world.isRemote && stand.getGameTime() != -1 && stand.getGameTime() < player.world.getGameTime() - 24000) {
                player.world.setGameTime(stand.getGameTime());
                player.world.setDayTime(stand.getDayTime());
                stand.setGameTime(-1);
                stand.setDayTime(-1);
                player.setHealth(player.getMaxHealth());
                player.getServer().getWorld(player.dimension).getEntities().forEach(entity -> {
                    if (entity instanceof PlayerEntity)
                        StandPlayerEffects.getLazyOptional((PlayerEntity) entity).ifPresent(standPlayerEffects -> {
                            ((PlayerEntity) entity).inventory.clear();
                            ((PlayerEntity) entity).inventory.copyInventory(standPlayerEffects.getInventory());
                        });
                    StandEffects.getLazyOptional(entity).ifPresent(standEffects -> {
                        if (standEffects.getBitesTheDustPos() != BlockPos.ZERO) {
                            entity.setPositionAndUpdate(standEffects.getBitesTheDustPos().getX(), standEffects.getBitesTheDustPos().getY(), standEffects.getBitesTheDustPos().getZ());
                            standEffects.setBitesTheDustPos(BlockPos.ZERO);
                        }
                    });
                });
            }

            if (invulnerableTicks > 0) {
                stand.setInvulnerableTicks(stand.getInvulnerableTicks() - 0.5);
                for (int i = 0; i < 10; i++)
                    player.world.addOptionalParticle(
                            ParticleTypes.DRAGON_BREATH,
                            player.getPosX() + (player.world.rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()),
                            player.getPosY() + player.world.rand.nextDouble(),
                            player.getPosZ() + (player.world.rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()),
                            0, 0.3 + (rand.nextBoolean() ? 0.1 : -0.1), 0);
                if (invulnerableTicks == 0.5)
                    stand.setCooldown(140);
            }
            if (standID == Util.StandID.STICKY_FINGERS && stand.getAbilityActive())
                for (int i = 0; i < 10; i++)
                    player.world.addOptionalParticle(
                            ParticleTypes.DRAGON_BREATH,
                            player.getPosX() + (player.world.rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()),
                            player.getPosY() + player.world.rand.nextDouble(),
                            player.getPosZ() + (player.world.rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()),
                            0, 0.3 + (rand.nextBoolean() ? 0.1 : -0.1), 0);

            if (cooldown == 0.5 && stand.getStandID() != Util.StandID.MADE_IN_HEAVEN)
                stand.setTimeLeft(1000);

            if (standID == Util.StandID.GER)
                player.clearActivePotions();

            if (!standOn) {
                if (cooldown > 0)
                    stand.setCooldown(stand.getCooldown() - 0.5);

                if (timeLeft < 1000)
                    stand.setTimeLeft(stand.getTimeLeft() + 0.5);

                player.setInvulnerable(false);
            } else if (!stand.getAbilityActive()) {
                if (cooldown > 0)
                    stand.setCooldown(stand.getCooldown() - 0.5);

                if (cooldown == 0.5 && stand.getStandID() != Util.StandID.MADE_IN_HEAVEN)
                    stand.setTimeLeft(1000);

                if (timeLeft < 1000 && cooldown == 0)
                    stand.setTimeLeft(stand.getTimeLeft() + 0.5);
            }

            if (standID == Util.StandID.KING_CRIMSON && (!standOn || !ability || !stand.getAbilityActive()) && player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                player.removePotionEffect(EffectInit.CRIMSON_USER.get());
        });
    }

    @SubscribeEvent
    public static void xpEvent(PlayerXpEvent.XpChange event) {
        PlayerEntity player = event.getPlayer();
        if (player == null) return;
        Stand.getLazyOptional(player).ifPresent(stand -> {
            if (Util.StandID.EVOLUTION_STANDS.contains(stand.getStandID())) {
                stand.addExperiencePoints(event.getAmount());
                switch (stand.getExperiencePoints()) {
                    default:
                        break;
                    case 1000: {
                        switch (stand.getStandID()) {
                            default:
                                break;
                            case Util.StandID.ECHOES_ACT_1: {
                                stand.removeStand();
                                stand.setStandID(Util.StandID.ECHOES_ACT_2);
                                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Echoes\u00A7f has evolved to\u00A7e Act 2!"), true);
                                break;
                            }
                            case Util.StandID.TUSK_ACT_1: {
                                stand.removeStand();
                                stand.setStandID(Util.StandID.TUSK_ACT_2);
                                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Tusk\u00A7f has evolved to\u00A7e Act 2!"), true);
                                break;
                            }
                        }
                        break;
                    }
                    case 3000: {
                        switch (stand.getStandID()) {
                            default:
                                break;
                            case Util.StandID.WHITESNAKE: {
                                stand.removeStand();
                                stand.setStandID(Util.StandID.CMOON);
                                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Whitesnake\u00A7f has evolved into\u00A7e C-Moon!"), true);
                                break;
                            }
                            case Util.StandID.ECHOES_ACT_2: {
                                stand.removeStand();
                                stand.setStandID(Util.StandID.ECHOES_ACT_3);
                                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Echoes\u00A7f has evolved to\u00A7e Act 3!"), true);
                                break;
                            }
                            case Util.StandID.TUSK_ACT_2: {
                                stand.removeStand();
                                stand.setStandID(Util.StandID.TUSK_ACT_3);
                                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Tusk\u00A7f has evolved to\u00A7e Act 3!"), true);
                                break;
                            }
                        }
                        break;
                    }
                    case 5000: {
                        if (stand.getStandID() == Util.StandID.TUSK_ACT_3) {
                            stand.removeStand();
                            stand.setStandID(Util.StandID.TUSK_ACT_4);
                            player.sendStatusMessage(new StringTextComponent("Your\u00A7e Tusk\u00A7f has evolved to\u00A7e Act 4!"), true);
                        }
                        break;
                    }
                }
            }
        });
    }

    @SubscribeEvent
    public static void livingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
        LivingEntity entityLiving = event.getEntityLiving();
        if (player.world.isRemote) return;
        Stand.getLazyOptional(player).ifPresent(stand -> {
            if (stand.getGameTime() != -1) {
                event.setCanceled(true);
                entityLiving.setHealth(entityLiving.getMaxHealth());
                StandEffects.getLazyOptional(event.getEntityLiving()).ifPresent(standEffects -> standEffects.setTimeOfDeath(player.world.getGameTime()));
                player.world.setGameTime(stand.getGameTime());
                player.world.setDayTime(stand.getDayTime());
                stand.setGameTime(-1);
                stand.setDayTime(-1);
                player.setHealth(player.getMaxHealth());
                player.getServer().getWorld(player.dimension).getEntities().forEach(entity -> {
                    if (entity instanceof PlayerEntity)
                        StandPlayerEffects.getLazyOptional((PlayerEntity) entity).ifPresent(standPlayerEffects -> {
                            ((PlayerEntity) entity).inventory.clear();
                            ((PlayerEntity) entity).inventory.copyInventory(standPlayerEffects.getInventory());
                        });
                    StandEffects.getLazyOptional(entity).ifPresent(standEffects -> {
                        if (standEffects.getBitesTheDustPos() != BlockPos.ZERO) {
                            entity.setPositionAndUpdate(standEffects.getBitesTheDustPos().getX(), standEffects.getBitesTheDustPos().getY(), standEffects.getBitesTheDustPos().getZ());
                            standEffects.setBitesTheDustPos(BlockPos.ZERO);
                        }
                    });
                });
            }
        });
    }

    @SubscribeEvent
    public static void blockDestroyed(BlockEvent.BreakEvent event) {
        LivingEntity livingEntity = event.getPlayer();
        if (livingEntity == null) return;
        Chunk chunk = livingEntity.world.getChunkAt(livingEntity.getPosition());
        StandEffects.getLazyOptional(livingEntity).ifPresent(standEffects -> {
            if (standEffects.getBitesTheDustPos() != BlockPos.ZERO)
                standEffects.putDestroyedBlock(chunk.getPos(), event.getPos(), event.getState());
        });
    }

    @SubscribeEvent
    public static void effectAddedEvent(PotionEvent.PotionAddedEvent event) {
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(true);
    }

    @SubscribeEvent
    public static void effectRemovedEvent(PotionEvent.PotionRemoveEvent event) {
        if (event.getPotion() == EffectInit.CRIMSON.get()) event.getEntityLiving().setGlowing(false);
        if (event.getPotion() == Effects.GLOWING) event.getEntityLiving().setGlowing(false);
        if (event.getPotion() == EffectInit.OXYGEN_POISONING.get()) event.setCanceled(true);
        if (event.getPotion() == EffectInit.HAZE.get()) event.setCanceled(true);
        if (event.getPotion() == EffectInit.AGING.get()) event.setCanceled(true);
    }

    @SubscribeEvent
    public static void effectExpiredEvent(PotionEvent.PotionExpiryEvent event) {
        if (event.getPotionEffect().getPotion() == null) return;
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get()) event.getEntityLiving().setGlowing(false);
        if (event.getPotionEffect().getPotion() == Effects.GLOWING) event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent //This one still bugs me to this day, can't think of a way to automate it.
    public static void tooltipEvent(ItemTooltipEvent event) {
        if (!(event.getItemStack().getItem() instanceof StandDiscItem)) return;
        String standName = "";
        if (event.getItemStack().getTag() != null)
            switch (event.getItemStack().getTag().getInt("StandID")) {
                case Util.StandID.KING_CRIMSON: {
                    standName = "King Crimson";
                    break;
                }
                case Util.StandID.D4C: {
                    standName = "D4C";
                    break;
                }
                case Util.StandID.GOLD_EXPERIENCE: {
                    standName = "Gold Experience";
                    break;
                }
                case Util.StandID.MADE_IN_HEAVEN: {
                    standName = "Made in Heaven";
                    break;
                }
                case Util.StandID.GER: {
                    standName = "Gold Experience Requiem";
                    break;
                }
                case Util.StandID.AEROSMITH: {
                    standName = "Aerosmith";
                    break;
                }
                case Util.StandID.WEATHER_REPORT: {
                    standName = "Weather Report";
                    break;
                }
                case Util.StandID.KILLER_QUEEN: {
                    standName = "Killer Queen";
                    break;
                }
                case Util.StandID.CRAZY_DIAMOND: {
                    standName = "Crazy Diamond";
                    break;
                }
                case Util.StandID.PURPLE_HAZE: {
                    standName = "Purple Haze";
                    break;
                }
                case Util.StandID.THE_EMPEROR: {
                    standName = "The Emperor";
                    break;
                }
                case Util.StandID.WHITESNAKE: {
                    standName = "Whitesnake";
                    break;
                }
                case Util.StandID.CMOON: {
                    standName = "C-Moon";
                    break;
                }
                case Util.StandID.THE_WORLD: {
                    standName = "The World";
                    break;
                }
                case Util.StandID.STAR_PLATINUM: {
                    standName = "Star Platinum";
                    break;
                }
                case Util.StandID.SILVER_CHARIOT: {
                    standName = "Silver Chariot";
                    break;
                }
                case Util.StandID.MAGICIANS_RED: {
                    standName = "Magician's Red";
                    break;
                }
                case Util.StandID.THE_HAND: {
                    standName = "The Hand";
                    break;
                }
                case Util.StandID.HIEROPHANT_GREEN: {
                    standName = "Hierophant Green";
                    break;
                }
                case Util.StandID.GREEN_DAY: {
                    standName = "Green Day";
                    break;
                }
                case Util.StandID.TWENTIETH_CENTURY_BOY: {
                    standName = "20th Century Boy";
                    break;
                }
                case Util.StandID.THE_GRATEFUL_DEAD: {
                    standName = "The Grateful Dead";
                    break;
                }
                case Util.StandID.STICKY_FINGERS: {
                    standName = "Sticky Fingers";
                    break;
                }
                case Util.StandID.TUSK_ACT_1: {
                    standName = "Tusk (Act 1)";
                    break;
                }
                case Util.StandID.TUSK_ACT_2: {
                    standName = "Tusk (Act 2)";
                    break;
                }
                case Util.StandID.TUSK_ACT_3: {
                    standName = "Tusk (Act 3)";
                    break;
                }
                case Util.StandID.TUSK_ACT_4: {
                    standName = "Tusk (Act 4)";
                    break;
                }
                case Util.StandID.ECHOES_ACT_1: {
                    standName = "Echoes (Act 1)";
                    break;
                }
                case Util.StandID.ECHOES_ACT_2: {
                    standName = "Echoes (Act 2)";
                    break;
                }
                case Util.StandID.ECHOES_ACT_3: {
                    standName = "Echoes (Act 3)";
                    break;
                }
                case Util.StandID.BEACH_BOY: {
                    standName = "Beach Boy";
                    break;
                }
            }
        if (!standName.equals(""))
            event.getToolTip().add(new StringTextComponent(standName));
    }

    @SubscribeEvent
    public static void throwawayEvent(ItemTossEvent event) {
        if (event.getEntityItem().getItem().getItem() == ItemInit.THE_EMPEROR.get() || event.getEntityItem().getItem().getItem() == ItemInit.BEACH_BOY.get()) {
            event.setCanceled(true);
            Stand.getLazyOptional(event.getPlayer()).ifPresent(props -> props.setStandOn(false));
        }
        if (event.getEntityItem().getItem().getOrCreateTag().getBoolean("bomb"))
            StandEffects.getLazyOptional(event.getEntityItem()).ifPresent(props -> {
                props.setBomb(true);
                props.setStandUser(event.getPlayer().getUniqueID());
            });
    }

    @SubscribeEvent
    public static void standUnsummoned(StandEvent.StandUnsummonedEvent event) {
        PlayerEntity player = event.getPlayer();
        Stand.getLazyOptional(player).ifPresent(props -> {
            player.setInvulnerable(false);
            player.setNoGravity(false);
            if (!player.isCreative() && !player.isSpectator())
                player.setGameType(GameType.SURVIVAL);
            if (player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                player.removePotionEffect(EffectInit.CRIMSON_USER.get());
            if (props.getStandID() == Util.StandID.THE_WORLD) {
                if (props.getAbility() && props.getTimeLeft() > 780)
                    player.world.playSound(null, player.getPosition(), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5, 1);
                Entity theWorld = player.world.getEntityByID(props.getPlayerStand());
                if (theWorld instanceof TheWorldEntity) {
                    ((TheWorldEntity) theWorld).shouldDamageBeCancelled = false;
                    TheWorldEntity.getTheWorldList().remove(theWorld);
                    ((TheWorldEntity) theWorld).getBrokenBlocks().forEach(pos -> {
                        theWorld.world.getBlockState(pos).getBlock().harvestBlock(theWorld.world, player, pos, theWorld.world.getBlockState(pos), null, player.getActiveItemStack());
                        theWorld.world.removeBlock(pos, false);
                    });
                    ((TheWorldEntity) theWorld).getBrokenBlocks().clear();
                }
                TheWorldEntity.dayTime = -1;
                TheWorldEntity.gameTime = -1;

                if (!player.world.isRemote)
                    player.world.getServer().getWorld(player.dimension).getEntities()
                            .filter(entity -> entity != player)
                            .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                props2.getDamage().forEach((source, amount) -> {
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
                                props2.clear();
                            }));
            } else if (props.getStandID() == Util.StandID.STAR_PLATINUM) {
                if (props.getAbility() && props.getTimeLeft() > 900)
                    player.world.playSound(null, player.getPosition(), SoundInit.RESUME_TIME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5, 1);
                Entity starPlatinum = player.world.getEntityByID(props.getPlayerStand());
                if (starPlatinum instanceof StarPlatinumEntity) {
                    ((StarPlatinumEntity) starPlatinum).shouldDamageBeCancelled = false;
                    StarPlatinumEntity.getStarPlatinumList().remove(starPlatinum);
                    ((StarPlatinumEntity) starPlatinum).getBrokenBlocks().forEach(pos -> {
                        starPlatinum.world.getBlockState(pos).getBlock().harvestBlock(starPlatinum.world, player, pos, starPlatinum.world.getBlockState(pos), null, player.getActiveItemStack());
                        starPlatinum.world.removeBlock(pos, false);
                    });
                    ((StarPlatinumEntity) starPlatinum).getBrokenBlocks().clear();
                }
                StarPlatinumEntity.dayTime = -1;
                StarPlatinumEntity.gameTime = -1;
                if (!player.world.isRemote)
                    player.world.getServer().getWorld(player.dimension).getEntities()
                            .filter(entity -> entity != player)
                            .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                if (props2.getDamage().size() > 0)
                                    props2.getDamage().forEach((source, amount) -> {
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
                                props2.clear();
                            }));
            }
        });
    }

    @SubscribeEvent
    public static void standPunchEntityEvent(StandAttackEvent.EntityHit event) {
        if (!JojoBizarreSurvivalConfig.COMMON.standPunchDamage.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void standPunchBlockEvent(StandAttackEvent.BlockHit event) {
        if (!JojoBizarreSurvivalConfig.COMMON.standPunchBlockBreaking.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void cancelDamage(LivingAttackEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (TheWorldEntity.getTheWorldList().size() > 0)
            TheWorldEntity.getTheWorldList().forEach(theWorldEntity -> {
                if (theWorldEntity.shouldDamageBeCancelled) {
                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                        if (!props.getDamage().containsKey(event.getSource().getDamageType()))
                            props.getDamage().put(event.getSource().getDamageType(), event.getAmount());
                        else
                            for (int i = 0; i < 1000; i++) {
                                if (!props.getDamage().containsKey(event.getSource().getDamageType() + i)) {
                                    props.getDamage().put(event.getSource().getDamageType() + i, event.getAmount());
                                    break;
                                }
                            }
                    });
                    event.setCanceled(true);
                }
            });
        if (StarPlatinumEntity.getStarPlatinumList().size() > 0)
            StarPlatinumEntity.getStarPlatinumList().forEach(starPlatinumEntity -> {
                if (starPlatinumEntity.shouldDamageBeCancelled) {
                    Timestop.getLazyOptional(entity).ifPresent(props -> {
                        if (!props.getDamage().containsKey(event.getSource().getDamageType()))
                            props.getDamage().put(event.getSource().getDamageType(), event.getAmount());
                        else
                            for (int i = 0; i < 1000; i++) {
                                if (!props.getDamage().containsKey(event.getSource().getDamageType() + i)) {
                                    props.getDamage().put(event.getSource().getDamageType() + i, event.getAmount());
                                    break;
                                }
                            }
                    });
                    event.setCanceled(true);
                }
            });
        if (entity instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) entity).ifPresent(props -> {
                if (props.getStandID() == Util.StandID.TWENTIETH_CENTURY_BOY && props.getAbilityActive()) {
                    if (!entity.world.isRemote)
                        entity.getServer().getWorld(entity.dimension).getEntities()
                                .filter(entity1 -> entity1.getDistance(entity) <= 3)
                                .filter(entity1 -> !entity1.equals(entity))
                                .forEach(entity1 -> {
                                    if (entity1 instanceof AbstractStandEntity && !((AbstractStandEntity) entity1).getMaster().equals(entity))
                                        return;
                                    entity1.attackEntityFrom(event.getSource(), event.getAmount() / 1.4f);
                                });
                    event.setCanceled(true);
                } else if (props.getInvulnerableTicks() > 0) {
                    event.setCanceled(true);
                    Entity source = event.getSource().getTrueSource();
                    if (source != null) {
                        Vec3d pos = source.getLookVec().mul(-0.5, 1, -0.5).add(source.getPositionVec());
                        if (!entity.world.isRemote) {
                            entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                            entity.lookAt(EntityAnchorArgument.Type.FEET, source.getPositionVec());
                        }
                        switch (props.getStandID()) {
                            case Util.StandID.KING_CRIMSON: {
                                source.attackEntityFrom(DamageSource.OUT_OF_WORLD, 1);
                                entity.world.playSound(null, entity.getPosition(), SoundInit.SPAWN_KING_CRIMSON.get(), SoundCategory.VOICE, 1, 1);
                                break;
                            }
                            case Util.StandID.STAR_PLATINUM:
                            case Util.StandID.THE_WORLD: {
                                source.attackEntityFrom(DamageSource.OUT_OF_WORLD, 1);
                                entity.world.playSound(null, entity.getPosition(), SoundInit.THE_WORLD_TELEPORT.get(), SoundCategory.HOSTILE, 1, 1);
                                break;
                            }
                            case Util.StandID.MADE_IN_HEAVEN: {
                                source.attackEntityFrom(DamageSource.OUT_OF_WORLD, 5);
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }
            });
    }

    @SubscribeEvent
    public static void itemPickup(EntityItemPickupEvent event) {
        ItemEntity entity = event.getItem();
        if (entity == null) return;
        if (entity.world.isRemote) return;
        StandEffects.getLazyOptional(entity).ifPresent(props -> {
            if (props.isBomb()) {
                entity.world.createExplosion(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 2.3f, Explosion.Mode.DESTROY);
                PlayerEntity player = entity.world.getPlayerByUuid(props.getStandUser());
                if (player != null)
                    Stand.getLazyOptional(player).ifPresent(stand -> stand.setAbilityUseCount(0));
                entity.remove();
                event.setCanceled(true);
            }
        });
    }

    @SubscribeEvent
    public static void itemExpire(ItemExpireEvent event) {
        ItemEntity entity = event.getEntityItem();
        if (entity == null) return;
        if (entity.getItem().getOrCreateTag().getBoolean("bomb"))
            StandEffects.getLazyOptional(entity).ifPresent(props -> {
                PlayerEntity player = entity.world.getPlayerByUuid(props.getStandUser());
                Stand.getLazyOptional(player).ifPresent(stand -> stand.setAbilityUseCount(0));
            });
    }

    @SubscribeEvent
    public static void noClip(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) event.getEntityLiving()).ifPresent(props -> {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                if (!player.isSpectator())
                    player.noClip = props.getNoClip();
                else
                    player.noClip = true;
            });
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getWorld() instanceof World)) return;
        World world = (World) event.getWorld();
        if (world.isRemote) return;
        Chunk chunk = world.getChunkAt(event.getPos());
        StandChunkEffects.getLazyOptional(chunk).ifPresent(props -> {
            props.getBombs().forEach((uuid, blockPos) -> {
                if (blockPos.equals(event.getPos())) {
                    PlayerEntity player = world.getPlayerByUuid(uuid);
                    world.createExplosion(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 3, Explosion.Mode.DESTROY);
                    Stand.getLazyOptional(player).ifPresent(stand -> {
                        stand.setBlockPos(BlockPos.ZERO);
                        stand.setAbilityUseCount(0);
                    });
                    props.removeBombPos(player);
                }
            });
            props.getSoundEffects().forEach((uuid, list) -> {
                if (list.size() > 0) {
                    List<BlockPos> removalList = new ArrayList<>();
                    list.forEach(blockPos -> {
                        if (blockPos.equals(event.getPos())) {
                            PlayerEntity player = world.getPlayerByUuid(uuid);
                            Util.activateEchoesAbility(world, event.getPlayer(), event.getPos(), props, player, removalList);
                        }
                    });
                    list.removeAll(removalList);
                }
            });
        });
    }

    @SubscribeEvent
    public static void explosionEvent(ExplosionEvent.Detonate event) {
        World world = event.getWorld();
        if (world == null || world.isRemote) return;
        event.getAffectedBlocks().forEach(blockPos ->
                StandChunkEffects.getLazyOptional(world.getChunkAt(blockPos)).ifPresent(props -> {
                    props.getBombs().forEach((uuid, pos) -> {
                        if (pos.equals(blockPos)) {
                            PlayerEntity player = world.getPlayerByUuid(uuid);
                            Stand.getLazyOptional(player).ifPresent(stand -> {
                                stand.setBlockPos(BlockPos.ZERO);
                                stand.setAbilityUseCount(0);
                            });
                            props.removeBombPos(player);
                        }
                    });
                    props.getSoundEffects().forEach((uuid, list) -> {
                        if (list.size() > 0) {
                            List<BlockPos> removalList = new ArrayList<>();
                            list.forEach(pos -> {
                                if (pos.equals(blockPos)) {
                                    PlayerEntity player = world.getPlayerByUuid(uuid);
                                    Stand.getLazyOptional(player).ifPresent(stand -> stand.setAbilityUseCount(stand.getAbilityUseCount() - 1));
                                    removalList.add(pos);
                                }
                            });
                            list.removeAll(removalList);
                        }
                    });
                }));
    }

    @SubscribeEvent
    public static void playerEatEvent(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (player.world.isRemote) return;
        if (event.getItem().getItem() instanceof HoneyBottleItem)
            Stand.getLazyOptional(player).ifPresent(props -> {
                if ((props.getStandID() == Util.StandID.TUSK_ACT_2 || props.getStandID() == Util.StandID.TUSK_ACT_3 || props.getStandID() == Util.StandID.TUSK_ACT_4) && props.getCooldown() > 0)
                    props.setCooldown(props.getCooldown() > 40 ? props.getCooldown() - 40 : 1);
            });
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof AbstractStandEntity) return;
        StandEffects.getLazyOptional(entity).ifPresent(props -> {
            if (props.getSoundEffect() != 0) {
                if (entity.world.rand.nextInt(40) == 1) {
                    SoundEvent soundEvent = SoundEvents.ENTITY_GENERIC_EXPLODE;
                    switch (props.getSoundEffect()) {
                        case 1: {
                            soundEvent = SoundEvents.BLOCK_ANVIL_BREAK;
                            break;
                        }
                        case 2: {
                            soundEvent = SoundEvents.BLOCK_BELL_USE;
                            break;
                        }
                        case 3: {
                            soundEvent = SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST;
                            break;
                        }
                        case 4: {
                            soundEvent = SoundEvents.ENTITY_GENERIC_BURN;
                            break;
                        }
                        case 6: {
                            soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
                            break;
                        }
                        case 7: {
                            soundEvent = SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON;
                            break;
                        }
                    }
                    if (props.getSoundEffect() != 2 && props.getSoundEffect() % 2 == 0) {
                        for (int i = 0; i < 2; i++) {
                            entity.world.playSound(null, entity.getPosition(), soundEvent, SoundCategory.BLOCKS, 1, 1);
                            entity.attackEntityFrom(DamageSource.DROWN, 2);
                        }
                    } else {
                        entity.world.playSound(null, entity.getPosition(), soundEvent, SoundCategory.BLOCKS, 1, 1);
                        entity.attackEntityFrom(DamageSource.DROWN, 2);
                    }
                }
            }
            if (props.isRotating()) {
                entity.rotationYaw += 30;
                if (entity.rotationYaw > 180)
                    entity.rotationYaw = -180;
                entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, 2);
            }
            if (props.isThreeFreeze()) {
                PlayerEntity playerEntity = entity.world.getPlayerByUuid(props.getStandUser());
                if (playerEntity == null || !playerEntity.isAlive()) return;
                float distance = entity.getDistance(playerEntity);
                if (distance < 2)
                    entity.setMotion(0, -1000, 0);
                else if (distance < 3)
                    entity.setMotion(0, -100, 0);
                else if (distance < 6)
                    entity.setMotion(0, -50, 0);
                else if (distance < 10)
                    entity.setMotion(0, -10, 0);
                else if (distance < 15)
                    entity.setMotion(0, -5, 0);
                else
                    return;
                entity.velocityChanged = true;
            }
            if (props.getTimeNearFlames() > 0) {
                entity.setFireTimer((int) (props.getTimeNearFlames() * 2));
                props.setTimeNearFlames(props.getTimeNearFlames() - 0.25);
                if (entity.world.rand.nextInt(6) == 1)
                    entity.attackEntityFrom(DamageSource.IN_FIRE, 2);
            }
            if (props.getTimeOfDeath() != -1 && props.getTimeOfDeath() <= entity.world.getGameTime()) {
                Explosion explosion = new Explosion(entity.world, null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 4, true, Explosion.Mode.NONE);
                ((MobEntity) entity).spawnExplosionParticle();
                explosion.doExplosionB(true);
                entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1, 1);
                entity.remove();
            }
        });
        StandChunkEffects.getLazyOptional(entity.world.getChunkAt(entity.getPosition())).ifPresent(props ->
                props.getSoundEffects().forEach((uuid, list) -> {
                    if (list.size() > 0) {
                        List<BlockPos> removalList = new ArrayList<>();
                        list.forEach(blockPos -> {
                            if (blockPos.equals(entity.getPosition().add(0, -1, 0)))
                                Util.activateEchoesAbility(entity.world, entity, blockPos, props, entity.world.getPlayerByUuid(uuid), removalList);
                        });
                        list.removeAll(removalList);
                    }
                }));
    }
}