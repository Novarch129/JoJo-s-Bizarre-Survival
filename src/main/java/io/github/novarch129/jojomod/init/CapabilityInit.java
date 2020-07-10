package io.github.novarch129.jojomod.init;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
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
