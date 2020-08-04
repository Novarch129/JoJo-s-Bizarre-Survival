package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
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
            } else if (!props.getAbilityActive()) {
                if (cooldown > 0)
                    props.subtractCooldown(0.5);

                if (cooldown == 0.5)
                    props.setTimeLeft(1000);

                if (timeLeft < 1000)
                    props.addTimeLeft(0.5);
            }

            if ((standID == Util.StandID.KING_CRIMSON) && (!standOn || !ability) && player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                player.removePotionEffect(EffectInit.CRIMSON_USER.get());
        });
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
        if (event.getPotionEffect().getPotion() == null) return;
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if (event.getPotionEffect().getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
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
            }
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
            if (props.getStandID() == Util.StandID.THE_WORLD) {
                if (props.getAbility() && props.getTimeLeft() > 780)
                    player.world.playSound(null, player.getPosition(), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5, 1);
                Entity theWorld = player.world.getEntityByID(props.getPlayerStand());
                if (theWorld instanceof TheWorldEntity)
                    TheWorldEntity.getTheWorldList().remove(theWorld);
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
                    player.world.playSound(null, player.getPosition(), SoundInit.RESUME_TIME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5, 1);
                Entity starPlatinum = player.world.getEntityByID(props.getPlayerStand());
                if (starPlatinum instanceof StarPlatinumEntity)
                    StarPlatinumEntity.getStarPlatinumList().remove(starPlatinum);
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
    public static void twentiethCenturyBoy(LivingDamageEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) entity).ifPresent(props -> {
                if (props.getStandID() == Util.StandID.TWENTIETH_CENTURY_BOY && props.getAbilityActive())
                    if (!entity.world.isRemote)
                        entity.getServer().getWorld(entity.dimension).getEntities()
                                .filter(entity1 -> entity1.getDistance(entity) <= 3)
                                .filter(entity1 ->  !entity1.equals(entity))
                                .forEach(entity1 -> {
                                    if (entity1 instanceof AbstractStandEntity && !((AbstractStandEntity) entity1).getMaster().equals(entity))
                                        return;
                                    entity1.attackEntityFrom(event.getSource(), event.getAmount() / 1.4f);
                                });
            });
    }
}
