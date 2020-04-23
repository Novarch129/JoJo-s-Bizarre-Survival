package com.novarch.jojomod.events;


import com.novarch.jojomod.JojoMod;
import com.novarch.jojomod.network.message.SyncPlayerAttackMessage;
import com.novarch.jojomod.network.message.SyncStandSummonButton;
import com.novarch.jojomod.util.handlers.KeyHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = JojoMod.MOD_ID, bus = Bus.FORGE)
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
				 JojoMod.INSTANCE.sendToServer(new SyncStandSummonButton()); 
			 }
		 }

		 if(KeyHandler.keys[1].isPressed())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 JojoMod.INSTANCE.sendToServer(new SyncStandSummonButton()); //TODO Toggle for ability
			 }
		 }
	    
		 if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 JojoMod.INSTANCE.sendToServer(new SyncPlayerAttackMessage());
			 }
		 }
	 }
}
