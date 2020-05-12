package com.novarch.jojomod.util.handlers;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler
{
    public final ResourceLocation JOJO_CAP = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "player-capabilities");
    
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) 
    {
      if (!(event.getObject() instanceof PlayerEntity))
        return;
      final StandCapability stand = new StandCapability((PlayerEntity) event.getObject());
      if(!event.getObject().world.isRemote)
          event.addCapability(this.JOJO_CAP, new JojoProvider());//new SerializableCapabilityProvider<>(JojoProvider.STAND, null, stand));
    }
}
