package com.novarch.jojomod.gui;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class StandGUI extends AbstractGui
{
   public static final Minecraft mc = Minecraft.getInstance();

   public void render()
   {
       if (mc == null)
           return;
       IStand props = JojoProvider.get(mc.player);
       String text = "initial";
       text = Integer.toString(props.getTimeLeft());
       drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
   }

    public void renderMadeInHeaven(int value)
    {
        if(value > 0)
        {
            String text = "";
            int minutes = value / 1200;
            int seconds = (value / 20) - (minutes * 60);
            text = String.valueOf(minutes);
            if (seconds < 10)
                text += ":0" + seconds;
            else
                text += ":" + seconds;
            drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
        } else { drawString(mc.fontRenderer, "\"Heaven\" has begun!", 4, 4, 0xFFFFFF); }
    }

    public void renderMadeInHeaven()
    {
        JojoProvider.getLazy(Minecraft.getInstance().player).ifPresent(props ->  {
            int value = props.getTimeLeft();
            if (props.getStandOn() && props.getStandID() == JojoLibs.StandID.madeInHeaven) {
                if (value > 0) {
                    String text = "";
                    int minutes = value / 1200;
                    int seconds = (value / 20) - (minutes * 60);
                    text = String.valueOf(minutes);
                    if (seconds < 10)
                        text += ":0" + seconds;
                    else
                        text += ":" + seconds;
                    drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
                } else {
                    drawString(mc.fontRenderer, "\"Heaven\" has begun!", 4, 4, 0xFFFFFF);
                }
            }
        });
    }

    public void renderText(String text)
    {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }
}
