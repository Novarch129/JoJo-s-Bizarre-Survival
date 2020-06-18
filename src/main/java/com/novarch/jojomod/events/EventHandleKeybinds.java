package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.network.message.client.*;
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
	 	if(Minecraft.getInstance().player != null) {
			if (KeyHandler.keys[0].isPressed())
				JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandSummonPacket());

			if (KeyHandler.keys[1].isPressed())
				JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAbilityPacket());

			if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
				JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncPlayerAttackPacket());

			Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
				if (props.getStandID() == JojoLibs.StandID.aerosmith) {
					if (props.getStandOn() && props.getAbility()) {
						if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 1, Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown()));
						if (Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 2));
						if (Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 3));
						if (Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 4));
						if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 5));
						if (Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(1, 6));
						if (KeyHandler.keys[2].isPressed())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithKeybindsPacket(2, 0));
					}
				} else if(props.getStandID() == JojoLibs.StandID.killerQueen) {
					if(props.getStandOn()) {
						if(KeyHandler.keys[2].isPressed())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncKillerQueenAbilityPacket(1));
						if(KeyHandler.keys[3].isPressed())
							JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncKillerQueenAbilityPacket(2));
					}
				}
			});
		}
	 }
}
