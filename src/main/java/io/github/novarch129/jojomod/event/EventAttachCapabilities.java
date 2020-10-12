package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventAttachCapabilities {
    public static final ResourceLocation STAND_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_capability");
    public static final ResourceLocation TIMESTOP_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "timestop_capability");
    public static final ResourceLocation STAND_EFFECTS_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_effects_capability");
    public static final ResourceLocation STAND_CHUNK_EFFECTS_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_chunk_effects_capability");
    public static final ResourceLocation STAND_PLAYER_EFFECTS_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_player_effects_capability");
    public static final ResourceLocation STAND_TILE_ENTITY_EFFECTS_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_tile_entity_effects_capability");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(STAND_CAPABILITY, new Stand((PlayerEntity) event.getObject()));
            event.addCapability(STAND_PLAYER_EFFECTS_CAPABILITY, new StandPlayerEffects((PlayerEntity) event.getObject()));
        }
        event.addCapability(TIMESTOP_CAPABILITY, new Timestop(event.getObject()));
        event.addCapability(STAND_EFFECTS_CAPABILITY, new StandEffects(event.getObject()));
    }

    @SubscribeEvent
    public static void attachChunkCapability(AttachCapabilitiesEvent<Chunk> event) {
        Chunk chunk = event.getObject();
        if (chunk == null) return;
        event.addCapability(STAND_CHUNK_EFFECTS_CAPABILITY, new StandChunkEffects(chunk.getWorld(), chunk.getPos()));
    }

    @SubscribeEvent
    public static void attachTileEntityCapability(AttachCapabilitiesEvent<TileEntity> event) {
        TileEntity tileEntity = event.getObject();
        if (tileEntity == null) return;
        event.addCapability(STAND_TILE_ENTITY_EFFECTS_CAPABILITY, new StandTileEntityEffects(tileEntity));
    }
}
