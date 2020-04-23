package com.novarch.jojomod;

import com.novarch.jojomod.util.capabilities.stand.IStand;
import com.novarch.jojomod.util.capabilities.stand.IStandCapability;
import com.novarch.jojomod.util.capabilities.stand.JojoProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Loader;

import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.SyncPlayerAttackMessage;
import com.novarch.jojomod.network.message.SyncStandSummonButton;
import com.novarch.jojomod.proxy.CommonProxy;
import com.novarch.jojomod.events.EventControlInputs;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@SuppressWarnings("unused")
@Mod("jojomod")
public class JojoMod
{
    public static final boolean debug = true;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "jojomod";
    public static JojoMod instance;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public JojoMod()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
           
        KeyHandler.addKeys();
        MinecraftForge.EVENT_BUS.register(EventControlInputs.class);
		EntityInit.ENTITY_TYPES.register(modEventBus);
		
	    if (Loader.isClassAvailable("endercore"))
			   JojoLibs.setEnderCore(true); 
		
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
        int networkId = 0;
		INSTANCE.registerMessage(networkId++,
				SyncStandSummonButton.class,
				SyncStandSummonButton::encode,
				SyncStandSummonButton::decode,
				SyncStandSummonButton::handle);
		INSTANCE.registerMessage(networkId++,
				SyncPlayerAttackMessage.class,
				SyncPlayerAttackMessage::encode,
				SyncPlayerAttackMessage::decode,
				SyncPlayerAttackMessage::handle);
    }
    
    @SubscribeEvent
    public static void registerSounds(final RegistryEvent.Register<SoundEvent> event)
    {
    	SoundInit.registerSounds();
    }
    
    private void setup(final FMLCommonSetupEvent event)
    {
    	//PacketHandler.init();
    	CommonProxy.registerCapabilities();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    	
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        
    }
    
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
			return new ItemStack(ItemInit.stand_arrow);
		}
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        LazyOptional<IStand> power = event.player.getCapability(JojoProvider.STAND, null);
        IStand props = power.orElse(new IStandCapability());

        if(props.getStandOn() && props.getCooldown() >= 0)
        {
            props.subtractCooldown(1);
        }

        if(!props.getStandOn() && props.getCooldown() >= 0)
        {
            props.subtractCooldown(1);
        }
    }
}
