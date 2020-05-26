package com.novarch.jojomod.util;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.fakePlayer.RenderFakePlayerEntity;
import com.novarch.jojomod.entities.stands.aerosmith.RenderAerosmith;
import com.novarch.jojomod.entities.stands.aerosmith.RenderAerosmithBullet;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.RenderDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.RenderDirtyDeedsDoneDirtCheapPunch;
import com.novarch.jojomod.entities.stands.goldExperience.RenderGoldExperience;
import com.novarch.jojomod.entities.stands.goldExperience.RenderGoldExperiencePunch;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.RenderGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.RenderGoldExperienceRequiemPunch;
import com.novarch.jojomod.entities.stands.killerQueen.RenderKillerQueen;
import com.novarch.jojomod.entities.stands.killerQueen.RenderKillerQueenPunch;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimson;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimsonPunch;
import com.novarch.jojomod.entities.stands.madeInHeaven.RenderMadeInHeaven;
import com.novarch.jojomod.entities.stands.madeInHeaven.RenderMadeInHeavenPunch;
import com.novarch.jojomod.entities.stands.weatherReport.RenderWeatherReport;
import com.novarch.jojomod.entities.stands.weatherReport.RenderWeatherReportPunch;
import com.novarch.jojomod.init.EntityInit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON.get(), RenderKingCrimson::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON_PUNCH.get(), RenderKingCrimsonPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.D4C.get(), RenderDirtyDeedsDoneDirtCheap::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.D4C_PUNCH.get(), RenderDirtyDeedsDoneDirtCheapPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE.get(), RenderGoldExperience::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_PUNCH.get(), RenderGoldExperiencePunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MADE_IN_HEAVEN.get(), RenderMadeInHeaven::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MADE_IN_HEAVEN_PUNCH.get(), RenderMadeInHeavenPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), RenderGoldExperienceRequiem::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.GOLD_EXPERIENCE_REQUIEM_PUNCH.get(), RenderGoldExperienceRequiemPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.AEROSMITH.get(), RenderAerosmith::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.AEROSMITH_BULLET.get(), RenderAerosmithBullet::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.FAKE_PLAYER.get(), RenderFakePlayerEntity::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.WEATHER_REPORT.get(), RenderWeatherReport::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.WEATHER_REPORT_PUNCH.get(), RenderWeatherReportPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KILLER_QUEEN.get(), RenderKillerQueen::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KILLER_QUEEN_PUNCH.get(), RenderKillerQueenPunch::new);

	}
}
