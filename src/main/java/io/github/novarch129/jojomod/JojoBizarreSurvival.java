package io.github.novarch129.jojomod;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
import io.github.novarch129.jojomod.command.impl.StandCommand;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.init.*;
import io.github.novarch129.jojomod.network.message.PacketHandler;
import io.github.novarch129.jojomod.proxy.ClientProxy;
import io.github.novarch129.jojomod.proxy.IProxy;
import io.github.novarch129.jojomod.proxy.ServerProxy;
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
 * @since 1.15.2-1.0.0.0
 * <p>
 * The main {@link Mod} class, used mostly for registering objects.
 * Just a note, I mostly use these Javadocs to make snarky remarks and jokes.
 */
@Mod("jojomod")
public class JojoBizarreSurvival {
    public static final IProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MOD_ID = "jojomod";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public JojoBizarreSurvival() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        modBus.addListener(this::setup);
        forgeBus.addListener(this::onServerStarting); //FMLServerStartingEvent is fired on the Forge bus.

        EventInit.registerForgeBus(MinecraftForge.EVENT_BUS);
        ItemInit.ITEMS.register(modBus);
        EntityInit.ENTITY_TYPES.register(modBus);
        SoundInit.SOUNDS.register(modBus);
        DimensionInit.DIMENSIONS.register(modBus);
        EffectInit.EFFECTS.register(modBus);
        JojoBizarreSurvivalConfig.register(ModLoadingContext.get());
    }

    private void setup(FMLCommonSetupEvent event) {
        Stand.register();
        Timestop.register();
        PacketHandler.register();
    }

    private void onServerStarting(FMLServerStartingEvent event) {
        StandCommand.register(event.getCommandDispatcher());
    }

    @MethodsReturnNonnullByDefault
    public static class JojoItemGroup extends ItemGroup {
        public static final ItemGroup INSTANCE = new JojoItemGroup();

        private JojoItemGroup() {
            super(MOD_ID);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.STAND_ARROW.get());
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }
}
