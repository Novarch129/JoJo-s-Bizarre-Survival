package com.novarch.jojomod.events;


import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.network.message.SyncAbility2Button;
import com.novarch.jojomod.network.message.SyncAbilityButton;
import com.novarch.jojomod.network.message.SyncPlayerAttackMessage;
import com.novarch.jojomod.network.message.SyncStandSummonButton;
import com.novarch.jojomod.util.handlers.KeyHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = StevesBizarreSurvival.MOD_ID, bus = Bus.FORGE)
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
				 StevesBizarreSurvival.INSTANCE.sendToServer(new SyncStandSummonButton());
			 }
		 }

		 if(KeyHandler.keys[1].isPressed())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 StevesBizarreSurvival.INSTANCE.sendToServer(new SyncAbilityButton());
			 }
		 }

		 if(KeyHandler.keys[2].isPressed())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 //StevesBizarreSurvival.INSTANCE.sendToServer(new SyncAbility2Button());
				 entityPlayerSP.setMotion(entityPlayerSP.getMotion().getX() * 5.0f, entityPlayerSP.getMotion().getY() * 5.0f, entityPlayerSP.getMotion().getZ() * 5.0f);
			 }
		 }
	    
		 if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
		 {
			 ClientPlayerEntity entityPlayerSP = (Minecraft.getInstance()).player;
			 if (entityPlayerSP != null)
			 {
				 StevesBizarreSurvival.INSTANCE.sendToServer(new SyncPlayerAttackMessage());
			 }
		 }
	 }
}
