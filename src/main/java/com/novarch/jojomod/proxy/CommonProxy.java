package com.novarch.jojomod.proxy;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.IStandStorage;
import com.novarch.jojomod.util.handlers.CapabilityHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy
{
	public static void registerCapabilities()
    {
    	CapabilityManager.INSTANCE.register(IStand.class, new IStandStorage(), IStandCapability::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
    }
}
