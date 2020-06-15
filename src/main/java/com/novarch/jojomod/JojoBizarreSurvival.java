package com.novarch.jojomod;

import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.init.*;
import com.novarch.jojomod.proxy.ClientProxy;
import com.novarch.jojomod.proxy.IProxy;
import com.novarch.jojomod.proxy.ServerProxy;
import com.novarch.jojomod.util.handlers.KeyHandler;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * @author Novarch
 *
 * The main {@link Mod} {@link Class}, used mostly for registering objects.
 */
@Mod("jojomod")
public class JojoBizarreSurvival
{
    public static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MOD_ID = "jojomod";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public JojoBizarreSurvival()
    {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	eventBus.addListener(this::setup);
        eventBus.addListener(this::onServerStarting);

        KeyHandler.addKeys();
        EventInit.register(MinecraftForge.EVENT_BUS);

        ItemInit.ITEMS.register(eventBus);
		EntityInit.ENTITY_TYPES.register(eventBus);
		SoundInit.SOUNDS.register(eventBus);
        DimensionInit.DIMENSIONS.register(eventBus);
        EffectInit.EFFECTS.register(eventBus);
        JojoBizarreSurvivalConfig.register(ModLoadingContext.get());
        PacketInit.register();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityInit.register();
    }

    public void onServerStarting(FMLServerStartingEvent event)
    {

    }

    @MethodsReturnNonnullByDefault
    public static class JojoItemGroup extends ItemGroup 
    {
		public static final ItemGroup instance = new JojoItemGroup(ItemGroup.GROUPS.length, "jojotab");

		private JojoItemGroup(int index, String label)
		{
			super(index, label);
		}

		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ItemInit.stand_arrow.get());
		}
    }
}
