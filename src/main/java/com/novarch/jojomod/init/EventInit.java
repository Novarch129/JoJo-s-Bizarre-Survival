package com.novarch.jojomod.init;

import com.novarch.jojomod.entities.stands.StarPlatinumEntity;
import com.novarch.jojomod.entities.stands.TheWorldEntity;
import com.novarch.jojomod.events.*;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventInit
{
    public static void register(IEventBus bus)
    {
        bus.register(EventAttachCapabilities.class);
        bus.register(EventHandleKeybinds.class);
        bus.register(EventD4CTeleportProcessor.class);
        bus.register(EventSyncCapability.class);
        bus.register(EventAbilityGER.class);
        bus.register(EventHandleStandAbilities.class);
        bus.register(TheWorldEntity.class);
        bus.register(StarPlatinumEntity.class);
    }
}
