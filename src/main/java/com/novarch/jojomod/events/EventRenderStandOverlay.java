package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.gui.overlay.CarbonDioxideRadarGUI;
import com.novarch.jojomod.gui.overlay.StandGUI;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventRenderStandOverlay
{
    public static List<Entity> entityList = null;
    public static EntityAerosmith playerStand = null;

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity playerEntity = event.player;

        if(!playerEntity.world.isRemote)
            playerEntity.world.getServer().getWorld(playerEntity.dimension).getEntities().forEach(entity -> {
                if(entity instanceof EntityAerosmith)
                    if(((EntityAerosmith) entity).getMaster() == playerEntity)
                        playerStand = (EntityAerosmith) entity;
            });

        if(!playerEntity.world.isRemote)
            if(playerStand!=null)
                entityList = playerEntity.world.getServer().getWorld(playerEntity.dimension).getEntities().filter(entity -> entity!=playerEntity).filter(entity -> entity!=playerStand).filter(JojoLibs.Predicates.BREATHS).filter(entity -> entity.getDistance(playerStand) < 16).collect(Collectors.toList());
    }

    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        StandGUI standGui = new StandGUI();
        standGui.render();

        CarbonDioxideRadarGUI carbonDioxideRadarGUI = new CarbonDioxideRadarGUI();
        if(entityList!=null && playerStand != null)
            carbonDioxideRadarGUI.renderRadar(entityList, playerStand);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderCrimsonEffect(EntityViewRenderEvent.FogDensity fogEvent)
    {
        fogEvent.setDensity(0.3f);

        if(fogEvent.getInfo().getRenderViewEntity() instanceof LivingEntity)
            if(((LivingEntity) fogEvent.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get()) || ((LivingEntity) fogEvent.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get()) || ((LivingEntity) fogEvent.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.OXYGEN_POISONING.get()))
                if(fogEvent.isCancelable())
                    fogEvent.setCanceled(true);
                fogEvent.setDensity(5f);
    }
}
