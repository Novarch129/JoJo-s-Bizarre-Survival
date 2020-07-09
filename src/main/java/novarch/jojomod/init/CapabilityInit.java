package novarch.jojomod.init;

import novarch.jojomod.capabilities.stand.Stand;
import novarch.jojomod.capabilities.timestop.Timestop;
import mcp.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 100% the shortest class in this entire mod.
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CapabilityInit {
    public static void register() {
        Stand.register();
        Timestop.register();
    }
}
