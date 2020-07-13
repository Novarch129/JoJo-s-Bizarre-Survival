package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.command.impl.StandCommand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/**
 * For some reason, this doesn't work in the main mod class, {@link JojoBizarreSurvival}.
 */
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID)
public class EventRegisterCommands {
    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event) {
        StandCommand.register(event.getCommandDispatcher());
    }
}
