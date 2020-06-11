package com.novarch.jojomod.util.handlers;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler
{
    public final ResourceLocation JOJO_CAP = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "player-capabilities");
    public final ResourceLocation TIMESTOP_CAP = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "entity-capabilities");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() == null)
            return;
        if(event.getObject() instanceof PlayerEntity) {
            final PlayerEntity player = (PlayerEntity) event.getObject();
            event.addCapability(this.JOJO_CAP, new JojoProvider(player));
        }
       event.addCapability(this.TIMESTOP_CAP, new Timestop(event.getObject()));
    }
}
