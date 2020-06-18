package com.novarch.jojomod.util;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.fakePlayer.RenderFakePlayerEntity;
import com.novarch.jojomod.entities.stands.aerosmith.RenderAerosmith;
import com.novarch.jojomod.entities.stands.aerosmith.RenderAerosmithBullet;
import com.novarch.jojomod.entities.stands.cMoon.RenderCMoon;
import com.novarch.jojomod.entities.stands.cMoon.RenderCMoonPunch;
import com.novarch.jojomod.entities.stands.crazyDiamond.RenderCrazyDiamond;
import com.novarch.jojomod.entities.stands.crazyDiamond.RenderCrazyDiamondPunch;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.RenderDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.RenderDirtyDeedsDoneDirtCheapPunch;
import com.novarch.jojomod.entities.stands.goldExperience.RenderGoldExperience;
import com.novarch.jojomod.entities.stands.goldExperience.RenderGoldExperiencePunch;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.RenderGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.RenderGoldExperienceRequiemPunch;
import com.novarch.jojomod.entities.stands.killerQueen.RenderKillerQueen;
import com.novarch.jojomod.entities.stands.killerQueen.RenderKillerQueenPunch;
import com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack.RenderSheerHeartAttack;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimson;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimsonPunch;
import com.novarch.jojomod.entities.stands.madeInHeaven.RenderMadeInHeaven;
import com.novarch.jojomod.entities.stands.madeInHeaven.RenderMadeInHeavenPunch;
import com.novarch.jojomod.entities.stands.magiciansRed.RenderMagiciansRed;
import com.novarch.jojomod.entities.stands.magiciansRed.RenderMagiciansRedFlames;
import com.novarch.jojomod.entities.stands.purpleHaze.RenderPurpleHaze;
import com.novarch.jojomod.entities.stands.purpleHaze.RenderPurpleHazePunch;
import com.novarch.jojomod.entities.stands.silverChariot.RenderSilverChariot;
import com.novarch.jojomod.entities.stands.silverChariot.RenderSilverChariotSword;
import com.novarch.jojomod.entities.stands.starPlatinum.RenderStarPlatinum;
import com.novarch.jojomod.entities.stands.starPlatinum.RenderStarPlatinumPunch;
import com.novarch.jojomod.entities.stands.theEmperor.RenderEmperorBullet;
import com.novarch.jojomod.entities.stands.theWorld.RenderTheWorld;
import com.novarch.jojomod.entities.stands.theWorld.RenderTheWorldPunch;
import com.novarch.jojomod.entities.stands.weatherReport.RenderWeatherReport;
import com.novarch.jojomod.entities.stands.weatherReport.RenderWeatherReportPunch;
import com.novarch.jojomod.entities.stands.whitesnake.RenderWhitesnake;
import com.novarch.jojomod.entities.stands.whitesnake.RenderWhitesnakePunch;
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

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.SHEER_HEART_ATTACK.get(), RenderSheerHeartAttack::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRAZY_DIAMOND.get(), RenderCrazyDiamond::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CRAZY_DIAMOND_PUNCH.get(), RenderCrazyDiamondPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PURPLE_HAZE.get(), RenderPurpleHaze::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.PURPLE_HAZE_PUNCH.get(), RenderPurpleHazePunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.EMPEROR_BULLET.get(), RenderEmperorBullet::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.WHITESNAKE.get(), RenderWhitesnake::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.WHITESNAKE_PUNCH.get(), RenderWhitesnakePunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CMOON.get(), RenderCMoon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.CMOON_PUNCH.get(), RenderCMoonPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_WORLD.get(), RenderTheWorld::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.THE_WORLD_PUNCH.get(), RenderTheWorldPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.STAR_PLATINUM.get(), RenderStarPlatinum::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.STAR_PLATINUM_PUNCH.get(), RenderStarPlatinumPunch::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.SILVER_CHARIOT.get(), RenderSilverChariot::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.SILVER_CHARIOT_SWORD.get(), RenderSilverChariotSword::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MAGICIANS_RED.get(), RenderMagiciansRed::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.MAGICIANS_RED_FLAMES.get(), RenderMagiciansRedFlames::new);
	}
}
