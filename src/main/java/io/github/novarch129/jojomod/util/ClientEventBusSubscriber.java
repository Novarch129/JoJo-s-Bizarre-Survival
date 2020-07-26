package io.github.novarch129.jojomod.util;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.render.*;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.KeyInit;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        KeyInit.register();

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON.get(), KingCrimsonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON_PUNCH.get(), KingCrimsonPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.D4C.get(), D4CRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.D4C_PUNCH.get(), D4CPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE.get(), GoldExperienceRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_PUNCH.get(), GoldExperiencePunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MADE_IN_HEAVEN.get(), MadeInHeavenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MADE_IN_HEAVEN_PUNCH.get(), MadeInHeavenPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), GoldExperienceRequiemRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_REQUIEM_PUNCH.get(), GoldExperienceRequiemPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.AEROSMITH.get(), AerosmithRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.AEROSMITH_BULLET.get(), AerosmithBulletRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WEATHER_REPORT.get(), WeatherReportRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WEATHER_REPORT_PUNCH.get(), WeatherReportPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KILLER_QUEEN.get(), KillerQueenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KILLER_QUEEN_PUNCH.get(), KillerQueenPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SHEER_HEART_ATTACK.get(), SheerHeartAttackRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRAZY_DIAMOND.get(), CrazyDiamondRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRAZY_DIAMOND_PUNCH.get(), CrazyDiamondPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PURPLE_HAZE.get(), PurpleHazeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PURPLE_HAZE_PUNCH.get(), PurpleHazePunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.EMPEROR_BULLET.get(), EmperorBulletRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WHITESNAKE.get(), WhitesnakeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WHITESNAKE_PUNCH.get(), WhitesnakePunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CMOON.get(), CMoonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CMOON_PUNCH.get(), CMoonPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_WORLD.get(), TheWorldRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_WORLD_PUNCH.get(), TheWorldPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.STAR_PLATINUM.get(), StarPlatinumRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.STAR_PLATINUM_PUNCH.get(), StarPlatinumPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SILVER_CHARIOT.get(), SilverChariotRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SILVER_CHARIOT_SWORD.get(), SilverChariotSwordRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MAGICIANS_RED.get(), MagiciansRedRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MAGICIANS_RED_FLAMES.get(), MagiciansRedFlameRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_HAND.get(), TheHandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_HAND_PUNCH.get(), TheHandPunchRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.STAND_ARROW.get(), StandArrowRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.HIEROPHANT_GREEN.get(), HierophantGreenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.EMERALD_SPLASH.get(), manager -> new SpriteRenderer<>(manager, event.getMinecraftSupplier().get().getItemRenderer())); //This renders as an emerald.
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.HIEROPHANT_GREEN_TAIL.get(), HierophantGreenTailRenderer::new);
    }
}
