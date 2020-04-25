package com.novarch.jojomod.util;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimson;
import com.novarch.jojomod.entities.stands.kingCrimson.RenderKingCrimsonPunch;
import com.novarch.jojomod.init.EntityInit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = JojoBlockyAdventure.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON.get(), RenderKingCrimson::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.KING_CRIMSON_PUNCH.get(), RenderKingCrimsonPunch::new);
	}
}
