package io.github.novarch129.jojomod.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyInit {
    public static final KeyBinding SPAWN_STAND = new KeyBinding("key.spawnstand.desc", GLFW.GLFW_KEY_V, "key.categories.jojobizarresurvival");
    public static final KeyBinding TOGGLE_ABILITY = new KeyBinding("key.ability.desc", GLFW.GLFW_KEY_Z, "key.categories.jojobizarresurvival");
    public static final KeyBinding ABILITY1 = new KeyBinding("key.ability2.desc", GLFW.GLFW_KEY_X, "key.categories.jojobizarresurvival");
    public static final KeyBinding ABILITY2 = new KeyBinding("key.ability3.desc", GLFW.GLFW_KEY_P, "key.categories.jojobizarresurvival");

    public static void register() {
        ClientRegistry.registerKeyBinding(SPAWN_STAND);
        ClientRegistry.registerKeyBinding(TOGGLE_ABILITY);
        ClientRegistry.registerKeyBinding(ABILITY1);
        ClientRegistry.registerKeyBinding(ABILITY2);
    }
}
