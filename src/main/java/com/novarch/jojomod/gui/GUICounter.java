package com.novarch.jojomod.gui;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.common.util.LazyOptional;

public class GUICounter extends AbstractGui
{
   public static final Minecraft mc = Minecraft.getInstance();

   public void render()
   {
       if (mc == null)
           return;
       LazyOptional<IStand> power = mc.player.getCapability(JojoProvider.STAND, null);
       IStand props = power.orElse(new IStandCapability());
       String text = "initial";
       text = Integer.toString(props.getTimeLeft());
       //mc.player.sendMessage(new StringTextComponent(text));
       drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
   }

    public void render(int value)
    {
        drawString(mc.fontRenderer, String.valueOf(value), 16, 16, 0xFFFFFF);
    }
}
