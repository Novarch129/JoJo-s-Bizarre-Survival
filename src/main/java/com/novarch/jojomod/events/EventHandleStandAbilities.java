package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.AerosmithEntity;
import com.novarch.jojomod.entities.stands.StarPlatinumEntity;
import com.novarch.jojomod.entities.stands.TheWorldEntity;
import com.novarch.jojomod.events.custom.AbilityEvent;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.events.custom.StandPunchEvent;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.item.StandDiscItem;
import com.novarch.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities {
    public static List<Entity> removalQueue = new ArrayList<>();
    public static List<PlayerEntity> teleportQueue = new ArrayList<>();
    public static List<Entity> entityList;
    public static AerosmithEntity playerStand;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;

        if (!player.isPotionActive(Effects.GLOWING) && !player.isPotionActive(EffectInit.CRIMSON.get()))
            player.setGlowing(false);

        if (!player.world.isRemote) {
            player.world.getServer().getWorld(player.dimension).getEntities().forEach(entity -> {
                if (entity instanceof AerosmithEntity)
                    if (((AerosmithEntity) entity).getMaster() == player)
                        playerStand = (AerosmithEntity) entity;
            });
            if (playerStand != null)
                entityList = player.world.getServer().getWorld(player.dimension)
                        .getEntities()
                        .filter(entity -> entity != player)
                        .filter(entity -> entity != playerStand)
                        .filter(entity -> !(entity instanceof FakePlayerEntity))
                        .filter(Util.Predicates.BREATHS)
                        .filter(entity -> entity.getDistance(playerStand) < 16)
                        .collect(Collectors.toList());
        }

        Stand.getLazyOptional(player).ifPresent(props -> {
            int standID = props.getStandID();
            boolean standOn = props.getStandOn();
            boolean ability = props.getAbility();
            double cooldown = props.getCooldown();
            double timeLeft = props.getTimeLeft();

            if (cooldown == 0.5)
                props.setTimeLeft(1000);

            if (standID == Util.StandID.GER)
                player.clearActivePotions();

            if (!standOn) {
                if (cooldown > 0)
                    props.subtractCooldown(0.5);

                if (timeLeft < 1000)
                    props.addTimeLeft(0.5);

                player.setInvulnerable(false);
            } else if (standOn && !ability) {
                if (cooldown > 0)
                    props.subtractCooldown(0.5);

                if (timeLeft < 1000)
                    props.addTimeLeft(0.5);
            }

            if ((standID == Util.StandID.KING_CRIMSON) && (!standOn || !ability) && player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                player.removePotionEffect(EffectInit.CRIMSON_USER.get());
        });
    }

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingUpdateEvent event) {
        if (!event.getEntityLiving().isPotionActive(EffectInit.CRIMSON.get()) || !event.getEntityLiving().isPotionActive(Effects.GLOWING))
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
    public static void effectAddedEvent(PotionEvent.PotionAddedEvent event) {
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(true);
    }

    @SubscribeEvent
    public static void effectRemovedEvent(PotionEvent.PotionRemoveEvent event) {
        if (event.getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if (event.getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
        if (event.getPotion() == EffectInit.OXYGEN_POISONING.get())
            event.setCanceled(true);
        if (event.getPotion() == EffectInit.HAZE.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void effectExpiredEvent(PotionEvent.PotionExpiryEvent event) {
        if (event.getPotionEffect().getPotion() == null)
            return;
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if (event.getPotionEffect().getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (removalQueue.size() > 0) {
                removalQueue.forEach(Entity::remove);
                removalQueue.clear();
            }
            if (teleportQueue.size() > 0) {
                teleportQueue.forEach(playerEntity -> {
                    int distance = 5;
                    float f1 = MathHelper.cos(-playerEntity.rotationYaw * 0.017453292f - (float) Math.PI); //0.017453292f is approximately Math.PI/180.
                    float f2 = MathHelper.sin(-playerEntity.rotationYaw * 0.017453292f - (float) Math.PI);
                    float f3 = -MathHelper.cos(-playerEntity.rotationPitch * 0.017453292f);
                    float f4 = MathHelper.sin(-playerEntity.rotationPitch * 0.017453292f);
                    playerEntity.move(MoverType.PLAYER, new Vec3d(distance * f2 * f3, distance * f4, distance * f1 * f3));
                });
                teleportQueue.clear();
            }
        }
    }

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event) {
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
            }
        if (event.getItemStack().getItem() instanceof StandDiscItem)
            if (!standName.equals(""))
                event.getToolTip().add(new StringTextComponent(standName));
    }

    @SubscribeEvent
    public static void throwawayEvent(ItemTossEvent event) {
        if (event.getEntityItem().getItem().getItem() == ItemInit.THE_EMPEROR.get()) {
            event.setCanceled(true);
            Stand.getLazyOptional(event.getPlayer()).ifPresent(props -> props.setStandOn(false));
        }
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
            if (!player.world.isRemote) {
                player.world.getServer().getWorld(player.dimension).getEntities()
                        .filter(entity -> entity instanceof FakePlayerEntity)
                        .filter(entity -> ((FakePlayerEntity) entity).getParent() == player)
                        .forEach(Entity::remove);
            }
            if (player.world.isRemote) {
                if (props.getStandID() == Util.StandID.AEROSMITH)
                    if (!(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity)) {
                        Minecraft.getInstance().setRenderViewEntity(player);
                        Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                    }
            }
            if (props.getStandID() == Util.StandID.THE_WORLD) {
                if (props.getAbility() && props.getTimeLeft() > 780)
                    player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);
                TheWorldEntity.theWorld = null;
                TheWorldEntity.dayTime = -1;
                TheWorldEntity.gameTime = -1;
                if (!player.world.isRemote)
                    player.world.getServer().getWorld(player.dimension).getEntities()
                            .filter(entity -> entity != player)
                            .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else {
                                    if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                }
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                props2.clear();
                            }));
            } else if (props.getStandID() == Util.StandID.STAR_PLATINUM) {
                if (props.getAbility() && props.getTimeLeft() > 900)
                    player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.TIME_RESUME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);
                StarPlatinumEntity.starPlatinum = null;
                StarPlatinumEntity.dayTime = -1;
                StarPlatinumEntity.gameTime = -1;
                if (!player.world.isRemote)
                    player.world.getServer().getWorld(player.dimension).getEntities()
                            .filter(entity -> entity != player)
                            .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                } else {
                                    if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                }
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.velocityChanged = true;
                                entity.fallDistance = props2.getFallDistance();
                                entity.setInvulnerable(false);
                                props2.clear();
                            }));
            }
        });
    }

    @SubscribeEvent
    public static void abilityOff(AbilityEvent.AbilityDeactivated event) {
        PlayerEntity player = event.getPlayer();
        assert player != null;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getStandID() == Util.StandID.AEROSMITH)
                if (!(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity)) {
                    Minecraft.getInstance().setRenderViewEntity(player);
                    Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                }
        });
    }

    @SubscribeEvent
    public static void standPunchEntityEvent(StandPunchEvent.EntityHit event) {
        if (!JojoBizarreSurvivalConfig.COMMON.standPunchDamage.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void standPunchBlockEvent(StandPunchEvent.BlockHit event) {
        if (!JojoBizarreSurvivalConfig.COMMON.standPunchBlockBreaking.get())
            event.setCanceled(true);
    }
}
