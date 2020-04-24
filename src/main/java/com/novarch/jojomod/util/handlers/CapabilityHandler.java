package com.novarch.jojomod.util.handlers;

import com.novarch.jojomod.JojoMod;

import com.novarch.jojomod.capabilities.JojoProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CapabilityHandler
{
    public final ResourceLocation JOJO_CAP = new ResourceLocation(JojoMod.MOD_ID, "player-capabilities");
    
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) 
    {
      if (!((Entity)event.getObject() instanceof PlayerEntity))
        return; 
      event.addCapability(this.JOJO_CAP, (ICapabilityProvider)new JojoProvider());
    }
}
