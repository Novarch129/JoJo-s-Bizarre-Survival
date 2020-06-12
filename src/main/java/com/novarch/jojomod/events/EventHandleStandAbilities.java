package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.events.custom.AbilityEvent;
import com.novarch.jojomod.events.custom.StandEvent;
import com.novarch.jojomod.events.custom.StandPunchEvent;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.objects.items.ItemStandDisc;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleStandAbilities
{
    public static Entity renderViewEntity = null;
    public static List<Entity> removalQueue = new ArrayList<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;

        if(!player.isPotionActive(Effects.GLOWING) && !player.isPotionActive(EffectInit.CRIMSON.get()))
            player.setGlowing(false);

        Stand.getLazyOptional(player).ifPresent(props -> {
            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(props.getStandID() == JojoLibs.StandID.GER)
                player.clearActivePotions();

            if(!props.getStandOn())
            {
                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 1000)
                    props.addTimeLeft(0.5);

                player.setInvulnerable(false);
            }

            else if(props.getStandOn() && !props.getAbility())
            {
                if(props.getCooldown() > 0)
                    props.subtractCooldown(0.5);

                if(props.getTimeLeft() < 100)
                    props.addTimeLeft(0.5);
            }

            if(!player.world.isRemote)
                player.world.getServer().getWorld(player.dimension).getEntities().forEach(entity -> {
                    if(entity instanceof EntityAerosmith)
                        if(((EntityAerosmith) entity).getMaster() == player)
                            renderViewEntity = entity;
                });
        });
    }

    @SubscribeEvent
    public static void effectAddedEvent(PotionEvent.PotionAddedEvent event)
    {
        if(event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(true);
    }

    @SubscribeEvent
    public static void effectRemovedEvent(PotionEvent.PotionRemoveEvent event)
    {
        if(event.getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if(event.getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
        if(event.getPotion() == EffectInit.OXYGEN_POISONING.get())
            event.setCanceled(true);
        if(event.getPotion() == EffectInit.HAZE.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void effectExpiredEvent(PotionEvent.PotionExpiryEvent event)
    {
        if(event.getPotionEffect().getPotion() == null)
            return;

        if(event.getPotionEffect().getPotion() == EffectInit.CRIMSON.get())
            event.getEntityLiving().setGlowing(false);
        if(event.getPotionEffect().getPotion() == Effects.GLOWING)
            event.getEntityLiving().setGlowing(false);
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null)
            return;

        PlayerEntity player = Minecraft.getInstance().player;

        Stand.getLazyOptional(player).ifPresent(props -> {
            if (!props.getStandOn()) {
                if (!player.isSpectator())
                    Minecraft.getInstance().setRenderViewEntity(player);
            } else {
                if (renderViewEntity != null)
                    if (renderViewEntity.isAlive())
                        if (props.getAbility()) {
                            Minecraft.getInstance().setRenderViewEntity(renderViewEntity);
                            if (Minecraft.getInstance().gameSettings.thirdPersonView != 1)
                                Minecraft.getInstance().gameSettings.thirdPersonView = 1;
                        }
            }
        });
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END) {
           removalQueue.forEach(Entity::remove);
           removalQueue.clear();
        }
    }

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event)
    {
        String standName = "";
        if(event.getItemStack().getTag() != null)
            switch(event.getItemStack().getTag().getInt("StandID")) {
                case JojoLibs.StandID.kingCrimson: {
                    standName = "King Crimson";
                    break;
                }
                case JojoLibs.StandID.dirtyDeedsDoneDirtCheap: {
                    standName = "D4C";
                    break;
                }
                case JojoLibs.StandID.goldExperience: {
                    standName = "Gold Experience";
                    break;
                }
                case JojoLibs.StandID.madeInHeaven: {
                    standName = "Made in Heaven";
                    break;
                }
                case JojoLibs.StandID.GER: {
                    standName = "Gold Experience Requiem";
                    break;
                }
                case JojoLibs.StandID.aerosmith: {
                    standName = "Aerosmith";
                    break;
                }
                case JojoLibs.StandID.weatherReport: {
                    standName = "Weather Report";
                    break;
                }
                case JojoLibs.StandID.killerQueen: {
                    standName = "Killer Queen";
                    break;
                }
                case JojoLibs.StandID.crazyDiamond: {
                    standName = "Crazy Diamond";
                    break;
                }
                case JojoLibs.StandID.purpleHaze: {
                    standName = "Purple Haze";
                    break;
                }
                case JojoLibs.StandID.theEmperor: {
                    standName = "The Emperor";
                    break;
                }
                case JojoLibs.StandID.whitesnake: {
                    standName = "Whitesnake";
                    break;
                }
                case JojoLibs.StandID.cMoon: {
                    standName = "C-Moon";
                    break;
                }
                case JojoLibs.StandID.theWorld: {
                    standName = "The World";
                    break;
                }
            }
        if(event.getItemStack().getItem() instanceof ItemStandDisc)
            if(!standName.equals(""))
                event.getToolTip().add(new StringTextComponent(standName));
    }

    @SubscribeEvent
    public static void throwawayEvent(ItemTossEvent event)
    {
        if(event.getEntityItem().getItem().getItem() == ItemInit.the_emperor.get()) {
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
            if(!player.isCreative() && !player.isSpectator())
                player.setGameType(GameType.SURVIVAL);
            if(player.isPotionActive(EffectInit.CRIMSON_USER.get()))
                player.removePotionEffect(EffectInit.CRIMSON_USER.get());
            if(!player.world.isRemote) {
                player.world.getServer().getWorld(player.dimension).getEntities()
                        .filter(entity -> entity instanceof FakePlayerEntity)
                        .filter(entity -> ((FakePlayerEntity) entity).getParent() == player)
                        .forEach(Entity::remove);
            }
            if(player.world.isRemote) {
                if(props.getStandID() == JojoLibs.StandID.aerosmith)
                    if(!(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity)) {
                        Minecraft.getInstance().setRenderViewEntity(player);
                        Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                }
            }

            if(props.getStandID() == JojoLibs.StandID.theWorld) {
                if(event.getStand() == EventStopTime.theWorld)
                    EventStopTime.theWorld=null;
                if (!player.world.isRemote)
                    player.world.getServer().getWorld(player.dimension).getEntities()
                            .filter(entity -> entity != player)
                            .forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props2 -> {
                                if ((entity instanceof IProjectile || entity instanceof ItemEntity || entity instanceof DamagingProjectileEntity) && (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)) {
                                    entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                    entity.setNoGravity(false);
                                    entity.velocityChanged = true;
                                } else {
                                    if (props2.getMotionX() != 0 && props2.getMotionY() != 0 && props2.getMotionZ() != 0)
                                        entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                }
                                if (entity instanceof MobEntity)
                                    ((MobEntity) entity).setNoAI(false);
                                entity.setMotion(props2.getMotionX(), props2.getMotionY(), props2.getMotionZ());
                                entity.fallDistance = props2.getFallDistance();
                                props2.clear();
                            }));
            }
        });
    }

    @SubscribeEvent
    public static void abilityOff(AbilityEvent.AbilityDeactivated event) {
        PlayerEntity player = Minecraft.getInstance().player;
        assert player != null;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if(props.getStandID() == JojoLibs.StandID.aerosmith)
                if(!(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity)) {
                    Minecraft.getInstance().setRenderViewEntity(player);
                    Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                }
        });
    }

    @SubscribeEvent
    public static void standPunchEntityEvent(StandPunchEvent.EntityHit event) {
        if(!JojoBizarreSurvivalConfig.COMMON.standPunchDamage.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void standPunchBlockEvent(StandPunchEvent.BlockHit event) {
        if(!JojoBizarreSurvivalConfig.COMMON.standPunchBlockBreaking.get())
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void renderPlayer(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        EntityRendererManager manager = mc.getRenderManager();
        if(player != null) {
            Stand.getLazyOptional(player).ifPresent(props -> {
                if(props.getStandID() == JojoLibs.StandID.aerosmith &&
                        props.getStandOn() &&
                        props.getAbility()) {
                    float partialTicks = event.getPartialTicks();
                    assert mc.player != null;
                    double x = mc.player.getPosX();
                    double y = mc.player.getPosX();
                    double z = mc.player.getPosX();
                    float yaw = mc.player.rotationYaw;
                    manager.renderEntityStatic(
                            player,
                            x, y, z,
                            yaw, partialTicks,
                            event.getMatrixStack(),
                            mc.getRenderTypeBuffers().getBufferSource(),
                            manager.getPackedLight(player, partialTicks)
                    );
                }
            });
        }
    }
}
