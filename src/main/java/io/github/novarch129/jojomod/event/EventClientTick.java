package io.github.novarch129.jojomod.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.StandChunkEffects;
import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.client.gui.CarbonDioxideRadarGUI;
import io.github.novarch129.jojomod.client.gui.StandGUI;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.entity.stand.KingCrimsonEntity;
import io.github.novarch129.jojomod.entity.stand.StickyFingersEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.network.message.client.CHierophantGreenPossessionPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.Tags;
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
        ClientPlayerEntity player = Minecraft.getInstance().player;
        Stand.getLazyOptional(player).ifPresent(stand -> {
            player.setInvisible(stand.getStandID() == Util.StandID.KING_CRIMSON && stand.getStandOn() && stand.getAbility() && stand.getTimeLeft() > 800 && stand.getCooldown() == 0);
            if (Minecraft.getInstance().world == null) return;
            if (stand.getStandID() == Util.StandID.AEROSMITH && stand.getStandOn() && stand.getAbility())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof AerosmithEntity)
                        .filter(entity -> ((AerosmithEntity) entity).getMaster() != null)
                        .filter(entity -> ((AerosmithEntity) entity).getMaster().equals(player))
                        .forEach(Minecraft.getInstance()::setRenderViewEntity);
            if (stand.getStandID() == Util.StandID.HIEROPHANT_GREEN && stand.getStandOn() && stand.getAbility())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof HierophantGreenEntity)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster() != null)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster().equals(player))
                        .forEach(entity -> {
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CHierophantGreenPossessionPacket((byte) 2));
                            float yaw = (float) Minecraft.getInstance().mouseHelper.getMouseX();
                            float pitch = (float) Minecraft.getInstance().mouseHelper.getMouseY();
                            if (pitch > 89)
                                pitch = 89;
                            else if (pitch < -89)
                                pitch = -89;
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CHierophantGreenPossessionPacket(yaw, pitch));
                        });
            if (!player.isSpectator() && !stand.getStandOn())
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

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getStandOn())
                switch (props.getStandID()) {
                    default:
                        break;
                    case Util.StandID.AEROSMITH: {
                        if (props.getAbility())
                            event.setCanceled(true);
                        break;
                    }
                    case Util.StandID.HIEROPHANT_GREEN: {
                        if (props.getAbilityActive())
                            event.setCanceled(true);
                        break;
                    }
                    case Util.StandID.STICKY_FINGERS: {
                        StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                                .filter(entity -> entity instanceof StickyFingersEntity)
                                .filter(entity -> ((StickyFingersEntity) entity).getMaster() != null)
                                .filter(entity -> ((StickyFingersEntity) entity).getMaster().equals(player))
                                .forEach(entity -> event.setCanceled(((StickyFingersEntity) entity).disguiseEntity != null));
                        break;
                    }
                }
        });
    }

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event) {
        Stand.getLazyOptional(event.getPlayer()).ifPresent(stand -> {
            if (stand.getStandOn())
                switch (stand.getStandID()) {
                    case Util.StandID.STICKY_FINGERS: {
                        StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                                .filter(entity -> entity instanceof StickyFingersEntity)
                                .filter(entity -> ((StickyFingersEntity) entity).getMaster() != null)
                                .filter(entity -> ((StickyFingersEntity) entity).getMaster().equals(event.getPlayer()))
                                .forEach(entity -> event.setCanceled(((StickyFingersEntity) entity).disguiseEntity != null));
                        break;
                    }
                    case Util.StandID.TUSK_ACT_3: {
                        event.setCanceled(stand.getAbilityActive());
                        break;
                    }
                    case Util.StandID.KING_CRIMSON: {
                        event.setCanceled(stand.getAbility() && stand.getTimeLeft() > 800 && stand.getCooldown() == 0 && stand.getInvulnerableTicks() == 0);
                        break;
                    }
                    default:
                        break;
                }
        });
    }

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        ClientWorld world = Minecraft.getInstance().world;
        Vec3d projectedView = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
        MatrixStack matrixStack = event.getMatrixStack();
        ClientPlayerEntity player = Minecraft.getInstance().player;
        float partialTicks = event.getPartialTicks();
        if (world == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if ((props.getStandID() == Util.StandID.AEROSMITH && props.getStandOn() && props.getAbility()) ||
                    (props.getStandID() == Util.StandID.HIEROPHANT_GREEN && props.getStandOn() && props.getAbility() && props.getAbilityActive())) {
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
            if (props.getStandID() == Util.StandID.KING_CRIMSON && props.getStandOn() && props.getAbility() && props.getAbilityActive()) {
                StreamSupport.stream(world.getAllEntities().spliterator(), false)
                        .filter(entity -> !entity.equals(player))
                        .filter(entity -> !(entity instanceof KingCrimsonEntity))
                        .filter(entity -> entity instanceof LivingEntity)
                        .filter(entity -> StandEffects.getCapabilityFromEntity(entity).isCrimson())
                        .forEach(entity -> {
                            double posX = MathHelper.lerp(partialTicks, entity.lastTickPosX, entity.getPosX());
                            double posY = MathHelper.lerp(partialTicks, entity.lastTickPosY, entity.getPosY());
                            double posZ = MathHelper.lerp(partialTicks, entity.lastTickPosZ, entity.getPosZ());
                            float yaw = MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw);
                            matrixStack.push();
                            Minecraft.getInstance().getRenderManager().renderEntityStatic(
                                    entity,
                                    posX - projectedView.getX(),
                                    posY - projectedView.getY(),
                                    posZ - projectedView.getZ(),
                                    yaw,
                                    partialTicks,
                                    matrixStack,
                                    Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(),
                                    Minecraft.getInstance().getRenderManager().getPackedLight(entity, partialTicks)
                            );
                            matrixStack.pop();
                        });
            }
            if (event.getPhase() != EventPriority.NORMAL || player == null) return;
            //Code below is *very* experimental, not final in any way.
            if (JojoBizarreSurvivalConfig.CLIENT.kingCrimsonOreRendering.get() && props.getStandID() == Util.StandID.KING_CRIMSON && props.getStandOn()) {
                BlockPos.getAllInBox(player.getPosition().add(10, 10, 10), player.getPosition().add(-10, -10, -10))
                        .filter(blockPos -> player.world.getBlockState(blockPos).getBlock().getTags().contains(Tags.Blocks.ORES.getId()))
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
            if (props.getStandID() == Util.StandID.ECHOES_ACT_2 || props.getStandID() == Util.StandID.ECHOES_ACT_3) {
                BlockPos.getAllInBox(player.getPosition().add(10, 10, 10), player.getPosition().add(-10, -10, -10))
                        .filter(blockPos -> StandChunkEffects.getCapabilityFromChunk(world.getChunkAt(blockPos)).getSoundEffects().get(player.getUniqueID()) != null)
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
            if (props.getStandID() == Util.StandID.KILLER_QUEEN) {
                BlockPos.getAllInBox(player.getPosition().add(10, 10, 10), player.getPosition().add(-10, -10, -10))
                        .filter(blockPos -> StandChunkEffects.getCapabilityFromChunk(world.getChunkAt(blockPos)).getBombs().containsKey(player.getUniqueID()))
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
