package io.github.novarch129.jojomod.init;

import io.github.novarch129.jojomod.entity.stand.StarPlatinumEntity;
import io.github.novarch129.jojomod.entity.stand.TheWorldEntity;
import io.github.novarch129.jojomod.event.*;
import net.minecraftforge.eventbus.api.IEventBus;

import javax.annotation.Nonnull;

public class EventInit {
    public static void register(@Nonnull IEventBus bus) {
        bus.register(EventAttachCapabilities.class);
        bus.register(EventHandleKeybinds.class);
        bus.register(EventD4CTeleportProcessor.class);
        bus.register(EventSyncCapability.class);
        bus.register(EventAbilityGER.class);
        bus.register(EventHandleStandAbilities.class);
        bus.register(TheWorldEntity.class);
        bus.register(StarPlatinumEntity.class);
        bus.register(EventClientTick.class);
    }
}
