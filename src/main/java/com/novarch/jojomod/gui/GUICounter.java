package com.novarch.jojomod.gui;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraftforge.common.util.LazyOptional;

public class GUICounter extends AbstractGui
{
   public static final Minecraft mc = Minecraft.getInstance();

   public void render()
   {
       if(mc == null)
           return;
       LazyOptional<IStand> power = mc.player.getCapability(JojoProvider.STAND, null);
       IStand props = power.orElse(new IStandCapability());
       EntityStandBase stand = JojoLibs.getStand(props.getStandID(), mc.world);
       String text = "String";
       if(props.getStandID() == JojoLibs.StandID.madeInHeaven)
           text = String.valueOf(((EntityMadeInHeaven)stand).getHeaventickr());
       drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
   }
}
