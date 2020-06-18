package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.network.message.client.CSyncAbilityPacket;
import com.novarch.jojomod.network.message.client.CSyncAerosmithKeybindsPacket;
import com.novarch.jojomod.network.message.client.CSyncPlayerAttackPacket;
import com.novarch.jojomod.network.message.client.CSyncStandSummonPacket;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class EventHandleKeybinds {
	 @SubscribeEvent
	 public static void onInput(InputEvent event) {
		 if (KeyHandler.keys[0].isPressed()) {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandSummonPacket());
		 }

		 if(KeyHandler.keys[1].isPressed()) {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAbilityPacket());
		 }
	    
		 if(Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown()) {
			 if (Minecraft.getInstance().player != null)
				 JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncPlayerAttackPacket());
		 }

		 if(Minecraft.getInstance().player != null)
		 	Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
		 	if(props.getStandID() == JojoLibs.StandID.aerosmith)
		 		if(props.getStandOn() && props.getAbility()) {
		 			if(Minecraft.getInstance().mouseHelper.getMouseX() == 0 || Minecraft.getInstance().mouseHelper.getMouseY() == 0)
		 				return;
					if(Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 1, Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown(), Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 2, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 3, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 4, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 5, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 6, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
					if(KeyHandler.keys[2].isPressed())
						JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(2, 0, false, Minecraft.getInstance().mouseHelper.getMouseX(), Minecraft.getInstance().mouseHelper.getMouseY()));
		 		}
		 });
	 }
}
