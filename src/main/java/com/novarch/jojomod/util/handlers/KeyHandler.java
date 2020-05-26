package com.novarch.jojomod.util.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyHandler
{
	private static final String[] desc = new String[] { "spawnstand" , "ability", "ability2", "aerosmith_move_forward", "aerosmith_move_right", "aerosmith_move_left", "aerosmith_move_back", "aerosmith_move_up", "aerosmith_move_down", "aerosmith_rotate", "ability3"};
	  
	private static final int[] keyValues = new int[] { GLFW.GLFW_KEY_V, GLFW.GLFW_KEY_Z, GLFW.GLFW_KEY_X, GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_RIGHT, GLFW.GLFW_KEY_LEFT, GLFW.GLFW_KEY_DOWN, GLFW.GLFW_KEY_RIGHT_SHIFT, GLFW.GLFW_KEY_RIGHT_CONTROL, GLFW.GLFW_KEY_RIGHT_ALT, GLFW.GLFW_KEY_P};
	  
	public static final KeyBinding[] keys = new KeyBinding[desc.length];

	public static void addKeys()
	{
		for (int i = 0; i < desc.length; i++)
	    {
	    	String descStr = "key." + desc[i] + ".desc";
	    	keys[i] = new KeyBinding(descStr, keyValues[i], "key.categories.JoJo's Bizarre Survival");
	    	ClientRegistry.registerKeyBinding(keys[i]);
	    }
	}
}
