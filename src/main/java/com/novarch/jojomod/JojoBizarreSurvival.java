package com.novarch.jojomod;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.events.EventControlInputs;
import com.novarch.jojomod.gui.StandGUI;
import com.novarch.jojomod.init.*;
import com.novarch.jojomod.network.message.*;
import com.novarch.jojomod.proxy.ClientProxy;
import com.novarch.jojomod.proxy.IProxy;
import com.novarch.jojomod.proxy.ServerProxy;
import com.novarch.jojomod.util.DimensionHopTeleporter;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.GameType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Novarch
 */
@SuppressWarnings("unused")
@Mod("jojomod")
public class JojoBizarreSurvival
{
    public static final boolean debug = true;
    public static List<PlayerEntity> d4cPassengerList = new ArrayList<PlayerEntity>();
    public static List<DimensionType> d4cDestinationList = new ArrayList<DimensionType>();
    public static final IProxy PROXY = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "jojomod";
    public static JojoBizarreSurvival instance;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    public static final ResourceLocation D4C_DIMENSION_TYPE = new ResourceLocation(MOD_ID, "d4c_dimension_overworld");
    public static final ResourceLocation D4C_DIMENSION_TYPE_NETHER = new ResourceLocation(MOD_ID, "d4c_dimension_nether");
    public static final ResourceLocation D4C_DIMENSION_TYPE_END = new ResourceLocation(MOD_ID, "d4c_dimension_end");

    public JojoBizarreSurvival()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

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
        INSTANCE.registerMessage(networkId++,
                SyncStandCapability.class,
                SyncStandCapability::encode,
                SyncStandCapability::decode,
                SyncStandCapability::handle);
        INSTANCE.registerMessage(networkId++,
                SyncDimensionHop.class,
                SyncDimensionHop::encode,
                SyncDimensionHop::decode,
                SyncDimensionHop::handle);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityInit.register();
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
        PlayerEntity player = event.player;
        JojoProvider.getLazy(player).ifPresent(props -> {
            if(!props.getStandOn() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(!props.getStandOn() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);


            if(!props.getStandOn())
            {
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);
            }
        });
    }

    @SubscribeEvent
    public void saveStand(PlayerEvent.Clone event)
    {
        if(!event.isWasDeath()) {
            JojoProvider.getLazy(event.getOriginal()).ifPresent(originalProps -> {
                ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
                JojoProvider.getLazy(player).ifPresent(newProps -> {
                    newProps.clone(originalProps);
                });
            });
        }
    }

    @SubscribeEvent
    public void playerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazy(player).ifPresent(props -> {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        StandGUI standGui = new StandGUI();
        standGui.render();
    }

    @SubscribeEvent
    public void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazy(player).ifPresent(props -> {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = event.getPlayer();
            IStand props = JojoProvider.get(player);
            props.putStandOn(false);
            if(!player.world.isRemote)
                INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
        }
    }

    @SubscribeEvent
    public void playerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        JojoProvider.getLazy(player).ifPresent(props -> {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncStandCapability(props));
        });
    }

    @SubscribeEvent
    public void cancelDamage(LivingHurtEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            IStand props = JojoProvider.get(player);
            if(props.getStandID() == JojoLibs.StandID.GER)
            {
                event.setCanceled(true);
                if(event.getSource().getTrueSource() instanceof PlayerEntity)
                    event.getSource().getTrueSource().world.playSound(null, event.getSource().getTrueSource().getPosX(), event.getSource().getTrueSource().getPosY(), event.getSource().getTrueSource().getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                else if(event.getSource().getTrueSource() instanceof MobEntity)
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                else if(event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension != DimensionType.THE_END) {
                    player.setPositionAndUpdate(player.getPosX(), JojoLibs.getHighestBlock(player.world, player.getPosition()) + 1, player.getPosZ());
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }
                else if(event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension == DimensionType.THE_END) {
                    if(JojoLibs.getNearestBlockEnd(player.world, player.getPosition()) != null)
                        player.setPositionAndUpdate(JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getX(), JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getY() + 1, JojoLibs.getNearestBlockEnd(player.world, player.getPosition()).getZ());
                    player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundInit.SPAWN_GER.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }
            }
        }
    }

    @SubscribeEvent
    public void stopExplosion(ExplosionEvent.Start event)
    {
        PlayerEntity player = PROXY.getPlayer();
        if(player != null) {
            IStand props = JojoProvider.get(player);
            if (event.getExplosion().getPosition().distanceTo(player.getPositionVec()) < 10.0f) {
                if (props.getStandID() == JojoLibs.StandID.GER)
                    event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void serverTick(TickEvent.ServerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            if(d4cPassengerList.size() > 0 && d4cDestinationList.size() > 0)
            {
                for (ListIterator<PlayerEntity> it = d4cPassengerList.listIterator(); it.hasNext(); ) {
                    PlayerEntity passenger = it.next();
                    for(ListIterator<DimensionType> destinations = d4cDestinationList.listIterator(); destinations.hasNext();)
                    {
                        DimensionType type = destinations.next();
                        passenger.changeDimension(type, new DimensionHopTeleporter((ServerWorld) passenger.getEntityWorld(), passenger.getPosX(), passenger.getPosY(), passenger.getPosZ()));
                        d4cPassengerList.remove(passenger);
                        d4cDestinationList.remove(type);
                    }
                }
            }
        }
    }
}
