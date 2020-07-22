package io.github.novarch129.jojomod.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.client.gui.CarbonDioxideRadarGUI;
import io.github.novarch129.jojomod.client.gui.StandGUI;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.network.message.client.CAerosmithControlPacket;
import io.github.novarch129.jojomod.network.message.client.CHierophantGreenPossessionPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
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
                            Minecraft.getInstance().setRenderViewEntity(entity);

                            float yaw = (float) Minecraft.getInstance().mouseHelper.getMouseX();
                            float pitch = (float) Minecraft.getInstance().mouseHelper.getMouseY();

                            if (pitch > 89.0f)
                                pitch = 89.0f;
                            else if (pitch < -89.0f)
                                pitch = -89.0f;

                            if (entity.rotationYaw != yaw && entity.rotationPitch != pitch)
                                JojoBizarreSurvival.INSTANCE.sendToServer(new CAerosmithControlPacket(yaw, pitch));
                        });
            if (props.getStandID() == Util.StandID.HIEROPHANT_GREEN && props.getStandOn() && props.getAbility())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof HierophantGreenEntity)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster() != null)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster().getEntityId() == player.getEntityId())
                        .forEach(entity -> {
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CHierophantGreenPossessionPacket((byte) 2));
                            if (((HierophantGreenEntity) entity).possessedEntity != null) {
                                Minecraft.getInstance().setRenderViewEntity(((HierophantGreenEntity) entity).possessedEntity);

                                float yaw = (float) Minecraft.getInstance().mouseHelper.getMouseX();
                                float pitch = (float) Minecraft.getInstance().mouseHelper.getMouseY();

                                if (pitch > 89.0f)
                                    pitch = 89.0f;
                                else if (pitch < -89.0f)
                                    pitch = -89.0f;

                                JojoBizarreSurvival.INSTANCE.sendToServer(new CHierophantGreenPossessionPacket(yaw, pitch));
                            }
                        });
            if (!player.isSpectator() && (!props.getStandOn() || !props.getAbility()))
                if (Minecraft.getInstance().renderViewEntity != player)
                    Minecraft.getInstance().setRenderViewEntity(player);
        });
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        new StandGUI().render();
        new CarbonDioxideRadarGUI().renderRadar();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderCrimsonEffect(EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.3f);
        if (event.getInfo().getRenderViewEntity() instanceof LivingEntity)
            if (((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get()) || ((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.OXYGEN_POISONING.get()))
                event.setCanceled(true);
        event.setDensity(5);
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @SubscribeEvent
    public static void renderHand(RenderHandEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getStandOn() && props.getAbility())
                switch (props.getStandID()) {
                    default:
                        break;
                    case Util.StandID.AEROSMITH: {
                        event.setCanceled(true);
                        break;
                    }
                }
        });
    }

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        World world = Minecraft.getInstance().world;
        Vec3d projectedView = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
        MatrixStack matrixStack = event.getMatrixStack();
        ClientPlayerEntity player = Minecraft.getInstance().player;
        float partialTicks = event.getPartialTicks();
        if (world == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if ((props.getStandID() == Util.StandID.AEROSMITH || props.getStandID() == Util.StandID.HIEROPHANT_GREEN) && props.getStandOn() && props.getAbility()) {
                double posX = MathHelper.lerp(partialTicks, player.lastTickPosX, player.getPosX());
                double posY = MathHelper.lerp(partialTicks, player.lastTickPosY, player.getPosY());
                double posZ = MathHelper.lerp(partialTicks, player.lastTickPosZ, player.getPosZ());
                float yaw = MathHelper.lerp(partialTicks, player.prevRotationYaw, player.rotationYaw);
                matrixStack.push();
                Minecraft.getInstance().getRenderManager().renderEntityStatic(
                        player,
                        posX - projectedView.getX(),
                        posY - projectedView.getY(),
                        posZ - projectedView.getZ(),
                        yaw,
                        partialTicks,
                        matrixStack,
                        Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(),
                        Minecraft.getInstance().getRenderManager().getPackedLight(player, partialTicks)
                );
                matrixStack.pop();
            }
            if (event.getPhase() != EventPriority.NORMAL || player == null) return;
            //Code below is *very* experimental, not final in any way.
            if (props.getStandID() == Util.StandID.KING_CRIMSON && props.getStandOn()) {
                BlockPos.getAllInBox(player.getPosition(), player.getPosition().add(50, -100, 50))
                        .filter(blockPos -> player.world.getBlockState(blockPos).getBlock() instanceof OreBlock)
                        .forEach(blockPos ->
                                Util.renderBlockStatic(
                                        matrixStack,
                                        IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer()),
                                        world,
                                        Blocks.DIAMOND_ORE.getDefaultState(),
                                        blockPos,
                                        projectedView,
                                        false,
                                        RenderType.getOutline(new ResourceLocation("minecraft", "textures/block/diamond_ore.png"))
                                ));
                BlockPos.getAllInBox(player.getPosition(), player.getPosition().add(-50, -100, 50))
                        .filter(blockPos -> player.world.getBlockState(blockPos).getBlock() instanceof OreBlock)
                        .forEach(blockPos ->
                                Util.renderBlockStatic(
                                        matrixStack,
                                        IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer()),
                                        world,
                                        Blocks.DIAMOND_ORE.getDefaultState(),
                                        blockPos,
                                        projectedView,
                                        false,
                                        RenderType.getOutline(new ResourceLocation("minecraft", "textures/block/diamond_ore.png"))
                                ));
                BlockPos.getAllInBox(player.getPosition(), player.getPosition().add(-50, -100, 50))
                        .filter(blockPos -> player.world.getBlockState(blockPos).getBlock() instanceof OreBlock)
                        .forEach(blockPos ->
                                Util.renderBlockStatic(
                                        matrixStack,
                                        IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer()),
                                        world,
                                        Blocks.DIAMOND_ORE.getDefaultState(),
                                        blockPos,
                                        projectedView,
                                        false,
                                        RenderType.getOutline(new ResourceLocation("minecraft", "textures/block/diamond_ore.png"))
                                ));
                BlockPos.getAllInBox(player.getPosition(), player.getPosition().add(-50, -100, -50))
                        .filter(blockPos -> player.world.getBlockState(blockPos).getBlock() instanceof OreBlock)
                        .forEach(blockPos ->
                                Util.renderBlockStatic(
                                        matrixStack,
                                        IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer()),
                                        world,
                                        Blocks.DIAMOND_ORE.getDefaultState(),
                                        blockPos,
                                        projectedView,
                                        false,
                                        RenderType.getOutline(new ResourceLocation("minecraft", "textures/block/diamond_ore.png"))
                                ));
            }
        });
    }
}
