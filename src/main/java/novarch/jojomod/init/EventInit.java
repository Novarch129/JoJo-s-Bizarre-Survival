package novarch.jojomod.init;

import novarch.jojomod.entities.stands.StarPlatinumEntity;
import novarch.jojomod.entities.stands.TheWorldEntity;
import com.novarch.jojomod.events.*;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.eventbus.api.IEventBus;
import novarch.jojomod.events.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
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
