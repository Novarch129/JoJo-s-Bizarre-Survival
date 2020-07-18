package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.client.gui.CarbonDioxideRadarGUI;
import io.github.novarch129.jojomod.client.gui.StandGUI;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.network.message.client.CAerosmithControlPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.StreamSupport;

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
            if (Minecraft.getInstance().world == null) return;
            if (props.getStandID() == Util.StandID.AEROSMITH && props.getStandOn() && props.getAbility())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof AerosmithEntity)
                        .filter(entity -> ((AerosmithEntity) entity).getMaster() != null)
                        .filter(entity -> ((AerosmithEntity) entity).getMaster().getEntityId() == player.getEntityId())
                        .forEach(entity -> {
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CAerosmithControlPacket(CAerosmithControlPacket.Action.RENDER));

                            float yaw = (float) Minecraft.getInstance().mouseHelper.getMouseX();
                            float pitch = (float) Minecraft.getInstance().mouseHelper.getMouseY();

                            if (pitch > 89.0f)
                                pitch = 89.0f;
                            else if (pitch < -89.0f)
                                pitch = -89.0f;

                            if (entity.rotationYaw != yaw && entity.rotationPitch != pitch)
                                JojoBizarreSurvival.INSTANCE.sendToServer(new CAerosmithControlPacket(yaw, pitch));
                        });
        });
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        StandGUI standGui = new StandGUI();
        standGui.render();

        CarbonDioxideRadarGUI carbonDioxideRadarGUI = new CarbonDioxideRadarGUI();
        carbonDioxideRadarGUI.renderRadar();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderCrimsonEffect(EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.3f);
        if (event.getInfo().getRenderViewEntity() instanceof LivingEntity)
            if (((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.OXYGEN_POISONING.get()))
                event.setCanceled(true);
        event.setDensity(5);
    }

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        World world = Minecraft.getInstance().world;
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (world == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getStandID() == Util.StandID.AEROSMITH && props.getStandOn() && props.getAbility()) {
                float partialTicks = event.getPartialTicks();
                double d0 = MathHelper.lerp(partialTicks, player.lastTickPosX, player.getPosX());
                double d1 = MathHelper.lerp(partialTicks, player.lastTickPosY, player.getPosY());
                double d2 = MathHelper.lerp(partialTicks, player.lastTickPosZ, player.getPosZ());
                float f = MathHelper.lerp(partialTicks, player.prevRotationYaw, player.rotationYaw);
                double v0 = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView().getX();
                double v1 = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView().getY();
                double v2 = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView().getZ();
                event.getMatrixStack().push();
                Minecraft.getInstance().getRenderManager().renderEntityStatic(
                        player,
                        d0 - v0,
                        d1 - v1,
                        d2 - v2,
                        f,
                        event.getPartialTicks(),
                        event.getMatrixStack(),
                        Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(),
                        Minecraft.getInstance().getRenderManager().getPackedLight(player, event.getPartialTicks())
                );
                event.getMatrixStack().pop();
            }
        });
//        if (event.getPhase() != EventPriority.NORMAL) return;
//        IRenderTypeBuffer.Impl buffer = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());
//        Util.renderBlockStatic(event.getMatrixStack(), buffer, world, Blocks.REDSTONE_BLOCK.getDefaultState(), new BlockPos(0, 128, 0), Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView());
//        buffer.finish();
    }
}
