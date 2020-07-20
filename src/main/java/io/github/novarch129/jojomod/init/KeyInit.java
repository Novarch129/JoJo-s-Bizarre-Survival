package io.github.novarch129.jojomod.init;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyInit {
    private static final String KEY_CATEGORY = "key.categories." + JojoBizarreSurvival.MOD_ID;
    public static final KeyBinding SPAWN_STAND = new KeyBinding("key.spawnstand.desc", GLFW.GLFW_KEY_V, KEY_CATEGORY);
    public static final KeyBinding TOGGLE_ABILITY = new KeyBinding("key.ability.desc", GLFW.GLFW_KEY_Z, KEY_CATEGORY);
    public static final KeyBinding ABILITY1 = new KeyBinding("key.ability2.desc", GLFW.GLFW_KEY_X, KEY_CATEGORY);
    public static final KeyBinding ABILITY2 = new KeyBinding("key.ability3.desc", GLFW.GLFW_KEY_P, KEY_CATEGORY);

    public static void register() {
        ClientRegistry.registerKeyBinding(SPAWN_STAND);
        ClientRegistry.registerKeyBinding(TOGGLE_ABILITY);
        ClientRegistry.registerKeyBinding(ABILITY1);
        ClientRegistry.registerKeyBinding(ABILITY2);
    }
}
