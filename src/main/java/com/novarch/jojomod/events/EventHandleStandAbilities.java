package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.entities.stands.starPlatinum.EntityStarPlatinum;
import com.novarch.jojomod.entities.stands.theWorld.EntityTheWorld;
import com.novarch.jojomod.events.custom.AbilityEvent;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.events.custom.StandPunchEvent;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.client.CSyncAerosmithPacket;
import com.novarch.jojomod.objects.items.ItemStandDisc;
import com.novarch.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities {
    public static List<Entity> removalQueue = new ArrayList<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;

        if (!player.isPotionActive(Effects.GLOWING) && !player.isPotionActive(EffectInit.CRIMSON.get()))
            player.setGlowing(false);

        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if (props.getStandID() == Util.StandID.GER)
                player.clearActivePotions();

            if (!props.getStandOn()) {
                if (props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if (props.getTimeLeft() < 1000)
                    props.addTimeLeft(0.5);

                player.setInvulnerable(false);
            } else if (props.getStandOn() && !props.getAbility()) {
                if (props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if (props.getTimeLeft() < 1000)
                    props.addTimeLeft(0.5);
            }
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
        if (event.getPotionEffect().getPotion() == null)
            return;
        if (event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if (event.getPotionEffect().getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null)
            return;

        PlayerEntity player = Minecraft.getInstance().player;

        Stand.getLazyOptional(player).ifPresent(props -> {
            if (!props.getStandOn() || !props.getAbility())
                if (!player.isSpectator())
                    Minecraft.getInstance().setRenderViewEntity(player);
        });
        assert Minecraft.getInstance().world != null;
        Minecraft.getInstance().world.getAllEntities().forEach(entity -> {
            if (entity instanceof EntityAerosmith)
                if (((EntityAerosmith) entity).getMaster() != null)
                    if (player.getEntityId() == ((EntityAerosmith) entity).getMaster().getEntityId()) {
                        float yaw = (float) Minecraft.getInstance().mouseHelper.getMouseX();
                        float pitch = (float) Minecraft.getInstance().mouseHelper.getMouseY();

                        if (pitch > 89.0f)
                            pitch = 89.0f;
                        else if (pitch < -89.0f)
                            pitch = -89.0f;

                        if (entity.rotationYaw != yaw && entity.rotationPitch != pitch)
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(yaw, pitch));
                    }
        });
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            removalQueue.forEach(Entity::remove);
            removalQueue.clear();
        }
    }

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event) {
        String standName = "";
        if (event.getItemStack().getTag() != null)
            switch (event.getItemStack().getTag().getInt("StandID")) {
                case Util.StandID.kingCrimson: {
                    standName = "King Crimson";
                    break;
                }
                case Util.StandID.dirtyDeedsDoneDirtCheap: {
                    standName = "D4C";
                    break;
                }
                case Util.StandID.goldExperience: {
                    standName = "Gold Experience";
                    break;
                }
                case Util.StandID.madeInHeaven: {
                    standName = "Made in Heaven";
                    break;
                }
                case Util.StandID.GER: {
                    standName = "Gold Experience Requiem";
                    break;
                }
                case Util.StandID.aerosmith: {
                    standName = "Aerosmith";
                    break;
                }
                case Util.StandID.weatherReport: {
                    standName = "Weather Report";
                    break;
                }
                case Util.StandID.killerQueen: {
                    standName = "Killer Queen";
                    break;
                }
                case Util.StandID.crazyDiamond: {
                    standName = "Crazy Diamond";
                    break;
                }
                case Util.StandID.purpleHaze: {
                    standName = "Purple Haze";
                    break;
                }
                case Util.StandID.theEmperor: {
                    standName = "The Emperor";
                    break;
                }
                case Util.StandID.whitesnake: {
                    standName = "Whitesnake";
                    break;
                }
                case Util.StandID.cMoon: {
                    standName = "C-Moon";
                    break;
                }
                case Util.StandID.theWorld: {
                    standName = "The World";
                    break;
                }
                case Util.StandID.starPlatinum: {
                    standName = "Star Platinum";
                    break;
                }
                case Util.StandID.silverChariot: {
                    standName = "Silver Chariot";
                    break;
                }
                case Util.StandID.magiciansRed: {
                    standName = "Magician's Red";
                    break;
                }
            }
        if (event.getItemStack().getItem() instanceof ItemStandDisc)
            if (!standName.equals(""))
                event.getToolTip().add(new StringTextComponent(standName));
    }

    @SubscribeEvent
    public static void throwawayEvent(ItemTossEvent event) {
        if (event.getEntityItem().getItem().getItem() == ItemInit.the_emperor.get()) {
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
                if (props.getStandID() == Util.StandID.aerosmith)
                    if (!(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity)) {
                        Minecraft.getInstance().setRenderViewEntity(player);
                        Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                    }
            }
            if (props.getStandID() == Util.StandID.theWorld) {
                if (props.getAbility() && props.getTimeLeft() > 780)
                    player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);
                EntityTheWorld.theWorld = null;
                EntityTheWorld.dayTime = -1;
                EntityTheWorld.gameTime = -1;
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
            } else if (props.getStandID() == Util.StandID.starPlatinum) {
                if (props.getAbility() && props.getTimeLeft() > 900)
                    player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.TIME_RESUME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);
                EntityStarPlatinum.starPlatinum = null;
                EntityStarPlatinum.dayTime = -1;
                EntityStarPlatinum.gameTime = -1;
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
            if (props.getStandID() == Util.StandID.aerosmith)
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

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event) {
        Stand.getLazyOptional(event.getPlayer()).ifPresent(props -> {
            if (props.getStandID() == Util.StandID.aerosmith && props.getStandOn() && props.getAbility())
                event.setCanceled(true);
        });
    }
}
