package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.network.message.client.CSyncAbilityPacket;
import com.novarch.jojomod.network.message.client.CSyncPlayerAttackPacket;
import com.novarch.jojomod.network.message.client.CSyncStandSummonPacket;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class EventControlInputs
{
	 @SubscribeEvent
	 public static void onInput(InputEvent event)
	 {
		 if (KeyHandler.keys[0].isPressed())
		 {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandSummonPacket());
		 }

		 if(KeyHandler.keys[1].isPressed())
		 {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAbilityPacket());
		 }
	    
		 if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
		 {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncPlayerAttackPacket());
		 }
	 }
}
