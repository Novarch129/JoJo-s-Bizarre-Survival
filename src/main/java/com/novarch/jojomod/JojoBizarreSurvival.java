package com.novarch.jojomod;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.capabilities.stand.StandCapabailityStorage;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.events.EventControlInputs;
import com.novarch.jojomod.gui.StandGUI;
import com.novarch.jojomod.init.DimensionInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.*;
import com.novarch.jojomod.proxy.ClientProxy;
import com.novarch.jojomod.proxy.IProxy;
import com.novarch.jojomod.proxy.ServerProxy;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.CapabilityHandler;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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

import javax.annotation.Nullable;

/**
 * @author Novarch
 */
@SuppressWarnings("unused")
@Mod("jojomod")
public class JojoBizarreSurvival
{
    public static final boolean debug = true;
    public static final IProxy PROXY = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "jojomod";
    public static JojoBizarreSurvival instance;
    private static final String PROTOCOL_VERSION = "1";
    private IStand ability = null;
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
                RequestSyncStandCapability.class,
                RequestSyncStandCapability::encode,
                RequestSyncStandCapability::decode,
                RequestSyncStandCapability::handle);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IStand.class, new StandCapabailityStorage(), () -> new StandCapability(null));
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
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
            if(player instanceof ServerPlayerEntity && !player.world.isRemote && player.isAlive())
            {
                INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
                player.sendMessage(new StringTextComponent(String.valueOf(props.getTimeLeft())));
                this.ability = props;
            }

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
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);
            }
        });
    }

    /*@SubscribeEvent
    public void onPlayerDeath(PlayerEvent.PlayerRespawnEvent event)
    {
        IStand props = event.getPlayer().getCapability(JojoProvider.STAND).orElse(new StandCapability());
        props = this.ability;
        if(!event.getPlayer().world.isRemote)
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new SyncStandCapability(props));
    }*/

    @SubscribeEvent
    public void saveStand(PlayerEvent.Clone event)
    {
        if(event.isWasDeath())
        {
            IStand oldProps = JojoProvider.get(event.getOriginal());
            IStand newProps = JojoProvider.get(event.getPlayer());
            oldProps.cloneSaveFunction(newProps);
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()), new SyncStandCapability(newProps));
        }
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        StandGUI standGui = new StandGUI();
        assert Minecraft.getInstance().player != null;
        IStand props = JojoProvider.get(Minecraft.getInstance().player); // TODO Fix
        if(props!=null)
            if(props.getStandOn() && props.getStandID() == JojoLibs.StandID.madeInHeaven)
                standGui.renderMadeInHeaven(props.getTimeLeft());
       /* if(!Minecraft.getInstance().isSingleplayer()) {
            if (props != null)
                if (props.getStandOn() && props.getStandID() == JojoLibs.StandID.madeInHeaven) {
                    standGui.renderText("Made in Heaven's counter currently doesn't work in multiplayer.");
                }
        }
        else
        {
            if(this.ability!=null)
            {
                if(ability.getStandOn() && ability.getStandID() == JojoLibs.StandID.madeInHeaven)
                    standGui.renderMadeInHeaven(this.ability.getTimeLeft());
            }
        }*/
    }

    @SubscribeEvent
    public void playerJoinWorld(EntityJoinWorldEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            IStand props = JojoProvider.get(player);
            if(!player.world.isRemote)
            {
                INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
            }
        }
    }

    @SubscribeEvent
    public void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if(event.getEntity() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            IStand props = JojoProvider.get(player);
            props.setStandOn(false);
            if(!player.world.isRemote)
            {
                INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
            }
        }
    }

    //TODO Remove method below when D4C GUI is added
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
}
