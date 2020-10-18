package io.github.novarch129.jojomod.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.StandChunkEffects;
import io.github.novarch129.jojomod.client.gui.CarbonDioxideRadarGUI;
import io.github.novarch129.jojomod.client.gui.StandGUI;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.entity.stand.StickyFingersEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.item.StandDiscItem;
import io.github.novarch129.jojomod.network.message.client.CAerosmithRotationPacket;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
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
                        .forEach(entity -> {
                            Minecraft.getInstance().setRenderViewEntity(entity);
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CAerosmithRotationPacket(entity.getEntityId(), entity.rotationYaw, entity.rotationPitch, ((AerosmithEntity) entity).rotationYawHead));
                        });
            if (stand.getStandID() == Util.StandID.STICKY_FINGERS && stand.getStandOn())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof StickyFingersEntity)
                        .filter(entity -> ((StickyFingersEntity) entity).getMaster() != null)
                        .filter(entity -> ((StickyFingersEntity) entity).getMaster().equals(player))
                        .forEach(entity -> {
                            if (((StickyFingersEntity) entity).disguiseEntity != null) {
                                Minecraft.getInstance().setRenderViewEntity(((StickyFingersEntity) entity).disguiseEntity);
                                Minecraft.getInstance().gameSettings.thirdPersonView = 1;
                            } else {
                                Minecraft.getInstance().setRenderViewEntity(player);
                                Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                            }
                        });
            if (stand.getStandID() == Util.StandID.HIEROPHANT_GREEN && stand.getStandOn() && stand.getAbility())
                StreamSupport.stream(Minecraft.getInstance().world.getAllEntities().spliterator(), false)
                        .filter(entity -> entity instanceof HierophantGreenEntity)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster() != null)
                        .filter(entity -> ((HierophantGreenEntity) entity).getMaster().equals(player))
                        .forEach(entity -> {
                            if (((HierophantGreenEntity) entity).possessedEntity != null) {
                                Minecraft.getInstance().setRenderViewEntity(((HierophantGreenEntity) entity).possessedEntity);
                                Minecraft.getInstance().gameSettings.thirdPersonView = 1;
                            } else {
                                Minecraft.getInstance().setRenderViewEntity(player);
                                Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                            }
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
            if (((LivingEntity) event.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.OXYGEN_POISONING.get()))
                event.setCanceled(true);
        event.setDensity(5);
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
