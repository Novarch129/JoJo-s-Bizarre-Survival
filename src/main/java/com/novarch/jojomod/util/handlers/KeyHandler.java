package com.novarch.jojomod.util.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyHandler
{
    /*private static final String[] desc;
    private static final int[] keyValues;
    public static final KeyBinding[] keys;
    public static final int STANDSPAWN = 0;

    public static void registerKeys()
    {
        for (int i = 0; i < KeyHandler.desc.length; ++i)
        {
            final String descStr = "key." + KeyHandler.desc[i] + ".desc";
            ClientRegistry.registerKeyBinding(KeyHandler.keys[i] = new KeyBinding(descStr, KeyHandler.keyValues[i], "key.categories.JojoMod"));
        }
    }

    static
    {
        desc = new String[] { "spawnStand" };
        keyValues = new int[] { 47 };
        keys = new KeyBinding[KeyHandler.desc.length];
    }*/
	private static final String[] desc = new String[] { "spawnStand" , "ability"};
	  
	  private static final int[] keyValues = new int[] { 47, 48 };
	  
	  public static final KeyBinding[] keys = new KeyBinding[desc.length];
	  
	  public static final int STANDSPAWN = 0;
	  
	  public static void addKeys() {
	    for (int i = 0; i < desc.length; i++) {
	      String descStr = "key." + desc[i] + ".desc";
	      keys[i] = new KeyBinding(descStr, keyValues[i], "key.categories.JojoMod");
	      ClientRegistry.registerKeyBinding(keys[i]);
	    } 
	  }
}
