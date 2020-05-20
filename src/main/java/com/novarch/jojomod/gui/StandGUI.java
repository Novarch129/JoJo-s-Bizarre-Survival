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
        String text = "";
        int minutes = ticks / 1200;
        int seconds = (ticks / 20) - (minutes * 60);
        text = String.valueOf(minutes);
        if (seconds < 10)
            text += ":0" + seconds;
        else
            text += ":" + seconds;
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderTimeLeft(int ticks)
    {
        String text = ";";
        int seconds = ticks / 20;
        text = String.valueOf(seconds);
        text += " seconds left.";
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderCooldown(int ticks)
    {
        String text = ";";
        int seconds = ticks / 20;
        text = "Cooldown: ";
        text += seconds;
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void render()
    {
        JojoProvider.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            int timeLeft = (int) props.getTimeLeft();
            int cooldown = (int) props.getCooldown();
            int transformed = props.getTransformed();
            if (props.getStandOn())
            {
                //Made in Heaven
                if (props.getStandID() == JojoLibs.StandID.madeInHeaven)
                {
                    if (timeLeft > 0 && cooldown <= 0)
                        renderTimeValue(timeLeft);
                    else
                        drawString(mc.fontRenderer, "\"Heaven\" has begun!", 4, 4, 0xFFFFFF);
                }

                //King Crimson
                else if(props.getStandID() == JojoLibs.StandID.kingCrimson)
                {
                    if(timeLeft > 800 && cooldown == 0)
                        renderTimeLeft(timeLeft - 800);
                }
            }

            //King Crimson
            if(props.getStandID() == JojoLibs.StandID.kingCrimson)
            {
                if(cooldown > 0)
                    renderCooldown(cooldown);
            }

            //Gold Experience
            else if(props.getStandID() == JojoLibs.StandID.goldExperience)
            {
                if(cooldown > 0 && transformed > 0)
                    renderCooldown(cooldown);
            }

            //Gold Experience Requiem
            else if(props.getStandID() == JojoLibs.StandID.GER)
            {
                if(cooldown > 0 && transformed > 1)
                    renderCooldown(cooldown);
            }

            //Dirty Deeds Done Dirt Cheap
            else if(props.getStandID() == JojoLibs.StandID.dirtyDeedsDoneDirtCheap)
            {
                if(cooldown > 0)
                    renderCooldown(cooldown);
            }
        });
    }

    public void renderText(String text)
    {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }
}
