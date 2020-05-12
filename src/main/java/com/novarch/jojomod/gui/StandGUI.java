package com.novarch.jojomod.gui;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class StandGUI extends AbstractGui
{
   public static final Minecraft mc = Minecraft.getInstance();

    public void renderTimeValue(int ticks)
    {
        if(ticks > 0)
        {
            String text = "";
            int minutes = ticks / 1200;
            int seconds = (ticks / 20) - (minutes * 60);
            text = String.valueOf(minutes);
            if (seconds < 10)
                text += ":0" + seconds;
            else
                text += ":" + seconds;
            drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
        } else { drawString(mc.fontRenderer, "\"Heaven\" has begun!", 4, 4, 0xFFFFFF); }
    }

    public void render()
    {
        JojoProvider.getLazy(Minecraft.getInstance().player).ifPresent(props ->  {
            int value = props.getTimeLeft();
            if (props.getStandOn() && props.getStandID() == JojoLibs.StandID.madeInHeaven) {
                renderTimeValue(value);
            }
        });
    }

    public void renderText(String text)
    {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }
}
