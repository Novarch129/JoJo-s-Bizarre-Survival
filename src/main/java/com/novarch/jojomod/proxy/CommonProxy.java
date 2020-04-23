package com.novarch.jojomod.proxy;

import com.novarch.jojomod.util.capabilities.stand.IStand;
import com.novarch.jojomod.util.capabilities.stand.IStandCapability;
import com.novarch.jojomod.util.capabilities.stand.IStandStorage;
import com.novarch.jojomod.util.handlers.CapabilityHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy
{
	public static void registerCapabilities()
    {
    	CapabilityManager.INSTANCE.register(IStand.class, new IStand.Storage(), IStandCapability::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
}
