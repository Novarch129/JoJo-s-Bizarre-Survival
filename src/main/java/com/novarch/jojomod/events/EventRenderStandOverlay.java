package com.novarch.jojomod.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.gui.CarbonDioxideRadarGUI;
import com.novarch.jojomod.gui.StandGUI;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void renderCrimsonEffect(EntityViewRenderEvent.FogDensity fogEvent)
    {
        fogEvent.setDensity(0.3f);

        if(fogEvent.getInfo().getRenderViewEntity() instanceof LivingEntity)
            if(((LivingEntity) fogEvent.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get()) || ((LivingEntity) fogEvent.getInfo().getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get()))
                if(fogEvent.isCancelable())
                    fogEvent.setCanceled(true);
                fogEvent.setDensity(5f);
    }

    @SubscribeEvent
    public static void renderCrimsonEffect2(EntityViewRenderEvent.FogColors fogEvent)
    {
        if(fogEvent.isCancelable())
            fogEvent.setCanceled(true);

        ActiveRenderInfo activeRenderInfoIn = fogEvent.getInfo();
        float partialTicks = (float) fogEvent.getRenderPartialTicks();
        ClientWorld worldIn = Minecraft.getInstance().world;
        int renderDistanceChunks = Minecraft.getInstance().gameSettings.renderDistanceChunks;
        float bossColorModifier = 1;
        float red, green, blue;
        int lastWaterFogColor = -1;
        int waterFogColor = -1;
        long waterFogUpdateTime = -1L;
        IFluidState ifluidstate = activeRenderInfoIn.getFluidState();
        if (ifluidstate.isTagged(FluidTags.WATER)) {
            long i = Util.milliTime();
            int j = worldIn.getBiome(new BlockPos(activeRenderInfoIn.getProjectedView())).getWaterFogColor();
            if (waterFogUpdateTime < 0L) {
                lastWaterFogColor = j;
                waterFogColor = j;
                waterFogUpdateTime = i;
            }

            int k = lastWaterFogColor >> 16 & 255;
            int l = lastWaterFogColor >> 8 & 255;
            int i1 = lastWaterFogColor & 255;
            int j1 = waterFogColor >> 16 & 255;
            int k1 = waterFogColor >> 8 & 255;
            int l1 = waterFogColor & 255;
            float f = MathHelper.clamp((float)(i - waterFogUpdateTime) / 5000.0F, 0.0F, 1.0F);
            float f1 = MathHelper.lerp(f, (float)j1, (float)k);
            float f2 = MathHelper.lerp(f, (float)k1, (float)l);
            float f3 = MathHelper.lerp(f, (float)l1, (float)i1);
            red = f1 / 255.0F;
            green = f2 / 255.0F;
            blue = f3 / 255.0F;
            if (lastWaterFogColor != j) {
                lastWaterFogColor = j;
                waterFogColor = MathHelper.floor(f1) << 16 | MathHelper.floor(f2) << 8 | MathHelper.floor(f3);
                waterFogUpdateTime = i;
            }
        } else if (ifluidstate.isTagged(FluidTags.LAVA)) {
            red = 0.6F;
            green = 0.1F;
            blue = 0.0F;
            waterFogUpdateTime = -1L;
        } else {
            float f4 = 0.25F + 0.75F * (float)renderDistanceChunks / 32.0F;
            f4 = 1.0F - (float)Math.pow((double)f4, 0.25D);
            Vec3d vec3d = worldIn.getSkyColor(activeRenderInfoIn.getBlockPos(), partialTicks);
            float f5 = (float)vec3d.x;
            float f8 = (float)vec3d.y;
            float f11 = (float)vec3d.z;
            Vec3d vec3d1 = worldIn.getFogColor(partialTicks);
            red = (float)vec3d1.x;
            green = (float)vec3d1.y;
            blue = (float)vec3d1.z;
            if (renderDistanceChunks >= 4) {
                float f12 = MathHelper.sin(worldIn.getCelestialAngleRadians(partialTicks)) > 0.0F ? -1.0F : 1.0F;
                Vector3f vector3f = new Vector3f(f12, 0.0F, 0.0F);
                float f16 = activeRenderInfoIn.getViewVector().dot(vector3f);
                if (f16 < 0.0F) {
                    f16 = 0.0F;
                }

                if (f16 > 0.0F) {
                    float[] afloat = worldIn.dimension.calcSunriseSunsetColors(worldIn.getCelestialAngle(partialTicks), partialTicks);
                    if (afloat != null) {
                        f16 = f16 * afloat[3];
                        red = red * (1.0F - f16) + afloat[0] * f16;
                        green = green * (1.0F - f16) + afloat[1] * f16;
                        blue = blue * (1.0F - f16) + afloat[2] * f16;
                    }
                }
            }

            red += (f5 - red) * f4;
            green += (f8 - green) * f4;
            blue += (f11 - blue) * f4;
            float f13 = worldIn.getRainStrength(partialTicks);
            if (f13 > 0.0F) {
                float f14 = 1.0F - f13 * 0.5F;
                float f17 = 1.0F - f13 * 0.4F;
                red *= f14;
                green *= f14;
                blue *= f17;
            }

            float f15 = worldIn.getThunderStrength(partialTicks);
            if (f15 > 0.0F) {
                float f18 = 1.0F - f15 * 0.5F;
                red *= f18;
                green *= f18;
                blue *= f18;
            }

            waterFogUpdateTime = -1L;
        }

        double d0 = activeRenderInfoIn.getProjectedView().y * worldIn.dimension.getVoidFogYFactor();
        if (activeRenderInfoIn.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).isPotionActive(Effects.BLINDNESS)) {
            int i2 = ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).getActivePotionEffect(Effects.BLINDNESS).getDuration();
            if (i2 < 20) {
                d0 *= (double)(1.0F - (float)i2 / 20.0F);
            } else {
                d0 = 0.0D;
            }
        }

        if (activeRenderInfoIn.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON_USER.get())) {
            int i2 = ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).getActivePotionEffect(EffectInit.CRIMSON_USER.get()).getDuration();
            if (i2 < 20) {
                d0 *= (double)(1.0F - (float)i2 / 20.0F);
            } else {
                d0 = 0.0D;
            }
        }

        if (activeRenderInfoIn.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).isPotionActive(EffectInit.CRIMSON.get())) {
            int i2 = ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).getActivePotionEffect(EffectInit.CRIMSON.get()).getDuration();
            if (i2 < 20) {
                d0 *= (double)(1.0F - (float)i2 / 20.0F);
            } else {
                d0 = 0.0D;
            }
        }

        if (d0 < 1.0D) {
            if (d0 < 0.0D) {
                d0 = 0.0D;
            }

            d0 = d0 * d0;
            red = (float)((double)red * d0);
            green = (float)((double)green * d0);
            blue = (float)((double)blue * d0);
        }

        if (bossColorModifier > 0.0F) {
            red = red * (1.0F - bossColorModifier) + red * 0.7F * bossColorModifier;
            green = green * (1.0F - bossColorModifier) + green * 0.6F * bossColorModifier;
            blue = blue * (1.0F - bossColorModifier) + blue * 0.6F * bossColorModifier;
        }

        if (ifluidstate.isTagged(FluidTags.WATER)) {
            float f6 = 0.0F;
            if (activeRenderInfoIn.getRenderViewEntity() instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientplayerentity = (ClientPlayerEntity)activeRenderInfoIn.getRenderViewEntity();
                f6 = clientplayerentity.getWaterBrightness();
            }

            float f9 = Math.min(1.0F / red, Math.min(1.0F / green, 1.0F / blue));
            // Forge: fix MC-4647 and MC-10480
            if (Float.isInfinite(f9)) f9 = Math.nextAfter(f9, 0.0);
            red = red * (1.0F - f6) + red * f9 * f6;
            green = green * (1.0F - f6) + green * f9 * f6;
            blue = blue * (1.0F - f6) + blue * f9 * f6;
        } else if (activeRenderInfoIn.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity)activeRenderInfoIn.getRenderViewEntity()).isPotionActive(Effects.NIGHT_VISION)) {
            float f7 = GameRenderer.getNightVisionBrightness((LivingEntity)activeRenderInfoIn.getRenderViewEntity(), partialTicks);
            float f10 = Math.min(1.0F / red, Math.min(1.0F / green, 1.0F / blue));
            // Forge: fix MC-4647 and MC-10480
            if (Float.isInfinite(f10)) f10 = Math.nextAfter(f10, 0.0);
            red = red * (1.0F - f7) + red * f10 * f7;
            green = green * (1.0F - f7) + green * f10 * f7;
            blue = blue * (1.0F - f7) + blue * f10 * f7;
        }

        EntityViewRenderEvent.FogColors event = new net.minecraftforge.client.event.EntityViewRenderEvent.FogColors(activeRenderInfoIn, partialTicks, red, green, blue);
        //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);

        red = event.getRed();
        green = event.getGreen();
        blue = event.getBlue();

        RenderSystem.clearColor(red, green, blue, 0.0F);
    }
}
