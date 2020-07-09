package novarch.jojomod.events;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.capabilities.stand.Stand;
import novarch.jojomod.client.gui.overlay.CarbonDioxideRadarGUI;
import novarch.jojomod.client.gui.overlay.StandGUI;
import novarch.jojomod.entities.stands.AerosmithEntity;
import novarch.jojomod.init.EffectInit;
import novarch.jojomod.network.message.client.CSyncAerosmithPacket;
import novarch.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static novarch.jojomod.events.EventHandleStandAbilities.entityList;
import static novarch.jojomod.events.EventHandleStandAbilities.playerStand;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventClientTick {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getInstance().player == null) return;
        PlayerEntity player = Minecraft.getInstance().player;

        Stand.getLazyOptional(player).ifPresent(props -> {
            if (!props.getStandOn() || !props.getAbility())
                if (!player.isSpectator())
                    Minecraft.getInstance().setRenderViewEntity(player);
        });
        assert Minecraft.getInstance().world != null;
        Minecraft.getInstance().world.getAllEntities().forEach(entity -> {
            if (entity instanceof AerosmithEntity)
                if (((AerosmithEntity) entity).getMaster() != null)
                    if (player.getEntityId() == ((AerosmithEntity) entity).getMaster().getEntityId()) {
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
    public static void renderPlayer(RenderPlayerEvent.Pre event) {
        Stand.getLazyOptional(event.getPlayer()).ifPresent(props -> {
            if (props.getStandID() == Util.StandID.AEROSMITH && props.getStandOn() && props.getAbility())
                event.setCanceled(true);
        });
    }

    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        StandGUI standGui = new StandGUI();
        standGui.render();

        CarbonDioxideRadarGUI carbonDioxideRadarGUI = new CarbonDioxideRadarGUI();
        if (entityList != null && playerStand != null)
            carbonDioxideRadarGUI.renderRadar(entityList, playerStand);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderCrimsonEffect(EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.3f);
        if (event.getInfo().getRenderViewEntity() instanceof LivingEntity)
            if (((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.OXYGEN_POISONING.get()))
                event.setCanceled(true);
        event.setDensity(5f);
    }
}
