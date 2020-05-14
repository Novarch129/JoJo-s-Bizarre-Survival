package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.gui.StandGUI;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventRenderStandOverlay
{
    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        StandGUI standGui = new StandGUI();
        standGui.render();
    }
}
