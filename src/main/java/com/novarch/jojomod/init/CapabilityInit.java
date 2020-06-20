package com.novarch.jojomod.init;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import com.novarch.jojomod.events.EventAttachCapabilities;
import net.minecraftforge.common.MinecraftForge;

public class CapabilityInit
{
    public static void register() {
        Stand.register();
        Timestop.register();
    }
}
