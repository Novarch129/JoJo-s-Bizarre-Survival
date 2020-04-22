package com.novarch.jojomod.util.helpers;

import org.lwjgl.glfw.GLFW;

import com.novarch.jojomod.util.handlers.KeyHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KeyboardHelper
{
	private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	@OnlyIn(Dist.CLIENT)
	public static boolean isStandSpawnDown() 
	{
		return InputMappings.isKeyDown(WINDOW, KeyHandler.STANDSPAWN) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_V);
	}
}
