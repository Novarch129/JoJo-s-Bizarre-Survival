package com.novarch.jojomod.events;


import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.network.message.SyncAbilityButton;
import com.novarch.jojomod.network.message.SyncPlayerAttackMessage;
import com.novarch.jojomod.network.message.SyncStandSummonButton;
import com.novarch.jojomod.util.handlers.KeyHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = JojoBlockyAdventure.MOD_ID, bus = Bus.FORGE)
public class EventControlInputs
{
	 @SubscribeEvent
	 public static void onInput(InputEvent event)
	 {
		 if (KeyHandler.keys[0].isPressed())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 JojoBlockyAdventure.INSTANCE.sendToServer(new SyncStandSummonButton());
			 }
		 }

		 if(KeyHandler.keys[1].isPressed())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 JojoBlockyAdventure.INSTANCE.sendToServer(new SyncAbilityButton()); //TODO Toggle for ability
			 }
		 }
	    
		 if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 JojoBlockyAdventure.INSTANCE.sendToServer(new SyncPlayerAttackMessage());
			 }
		 }
	 }
}
