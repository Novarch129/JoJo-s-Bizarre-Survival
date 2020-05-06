package com.novarch.jojomod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.util.LazyOptional;

public class GUICounter
{
    public static void render()
    {
        Minecraft mc = Minecraft.getInstance();
        if(mc != null)
        {
            if (mc.player != null)
            {
                LazyOptional<IStand> power = mc.player.getCapability(JojoProvider.STAND);
                IStand props = power.orElse(new IStandCapability());
                EntityStandBase stand = JojoLibs.getStand(props.getStandID(), mc.world);
                if(stand != null)
                {
                    if(stand instanceof EntityMadeInHeaven)
                    {
                        RenderSystem.pushMatrix();
                        mc.fontRenderer.drawString(String.valueOf(((EntityMadeInHeaven) stand).getHeaventickr()), 4.0f, 4.0f, 1);
                        mc.fontRenderer.drawString("String", 4.0f, 4.0f, 1);
                        RenderSystem.popMatrix();
                    }
                }
            }
        }
    }
}
