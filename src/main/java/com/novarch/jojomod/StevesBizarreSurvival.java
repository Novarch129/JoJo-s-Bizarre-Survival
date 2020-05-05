package com.novarch.jojomod;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.events.EventControlInputs;
import com.novarch.jojomod.gui.GUICounter;
import com.novarch.jojomod.init.DimensionInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.*;
import com.novarch.jojomod.proxy.CommonProxy;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Loader;

/**
 * @author Novarch
 */
@SuppressWarnings("unused")
@Mod("jojomod")
public class StevesBizarreSurvival
{
    public static final boolean debug = true;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "jojomod";
    public static StevesBizarreSurvival instance;
    private static final String PROTOCOL_VERSION = "1";
    public PlayerEntity playerEntity = Minecraft.getInstance().player;
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    public static final ResourceLocation D4C_DIMENSION_TYPE = new ResourceLocation(MOD_ID, "d4c_dimension_overworld");
    public static final ResourceLocation D4C_DIMENSION_TYPE_NETHER = new ResourceLocation(MOD_ID, "d4c_dimension_nether");
    public static final ResourceLocation D4C_DIMENSION_TYPE_END = new ResourceLocation(MOD_ID, "d4c_dimension_end");

    public StevesBizarreSurvival()
    {
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
           
        KeyHandler.addKeys();
        MinecraftForge.EVENT_BUS.register(EventControlInputs.class);
        ItemInit.ITEMS.register(modEventBus);
		EntityInit.ENTITY_TYPES.register(modEventBus);
		SoundInit.SOUNDS.register(modEventBus);
        DimensionInit.DIMENSIONS.register(modEventBus);
		
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
		INSTANCE.registerMessage(networkId++,
                SyncAbilityButton.class,
                SyncAbilityButton::encode,
                SyncAbilityButton::decode,
                SyncAbilityButton::handle);
        INSTANCE.registerMessage(networkId++,
                SyncAbility2Button.class,
                SyncAbility2Button::encode,
                SyncAbility2Button::decode,
                SyncAbility2Button::handle);
        INSTANCE.registerMessage(networkId++,
                SyncDimensionHop.class,
                SyncDimensionHop::encode,
                SyncDimensionHop::decode,
                SyncDimensionHop::handle);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
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
			return new ItemStack(ItemInit.stand_arrow.get());
		}
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        LazyOptional<IStand> power = event.player.getCapability(JojoProvider.STAND, null);
        IStand props = power.orElse(new IStandCapability());

        if(!props.getStandOn() && props.getCooldown() >= 0)
        {
            props.subtractCooldown(1);
        }

        if(props.getCooldown() <= 0)
        {

        }

        if(!props.getStandOn() && props.getTimeLeft() > 0)
        {
            props.subtractTimeLeft(1);
        }

        if(!props.getStandOn())
        {
            event.player.setInvulnerable(false);
            if(!event.player.isCreative() && !event.player.isSpectator())
                event.player.setGameType(GameType.SURVIVAL);
        }
    }

    @SubscribeEvent
    public void onPlayerDeath(PlayerEvent.PlayerRespawnEvent event)
    {
        LazyOptional<IStand> power = event.getPlayer().getCapability(JojoProvider.STAND, null);
        IStand props = power.orElse(new IStandCapability());
        props.cloneSaveFunction(props);
    }

    @SubscribeEvent
    public void onStandRemoved(EntityEvent event)
    {
        if(event.getEntity() instanceof EntityStandBase)
        {
            if(!event.getEntity().isAlive())
            {
                ((EntityStandBase) event.getEntity()).getMaster().clearActivePotions();
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event)
    {
        if(playerEntity != null)
        {
            LazyOptional<IStand> power = playerEntity.getCapability(JojoProvider.STAND);
            IStand props = power.orElse(new IStandCapability());
            GUICounter.render();
        }
    }

    //TODO Remove methodn below when D4C GUI is added
    @SubscribeEvent
    public void d4cDimensionHelper(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        if(event.getFrom() == DimensionType.byName(D4C_DIMENSION_TYPE))
        {
            if(event.getTo() == DimensionType.THE_NETHER)
            {
                INSTANCE.sendToServer(new SyncDimensionHop(DimensionType.byName(D4C_DIMENSION_TYPE_NETHER).getId()));
            }
        }
    }
}
