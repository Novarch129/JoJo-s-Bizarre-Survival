package com.novarch.jojomod.gui;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.JojoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class GUICounter extends AbstractGui
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

    public void render(int value)
    {
        drawString(mc.fontRenderer, String.valueOf(value), 4, 4, 0xFFFFFF);
    }
}
