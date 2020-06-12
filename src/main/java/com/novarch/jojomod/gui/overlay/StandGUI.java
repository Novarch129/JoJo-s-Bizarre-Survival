package com.novarch.jojomod.gui.overlay;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("unused")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class StandGUI extends AbstractGui
{
   public static final Minecraft mc = Minecraft.getInstance();

    public void renderTimeValue(int ticks)
    {
        int minutes = ticks / 1200;
        int seconds = (ticks / 20) - (minutes * 60);
        String text = String.valueOf(minutes);
        if (seconds < 10)
            text += ":0" + seconds;
        else
            text += ":" + seconds;
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderTimeLeft(int ticks)
    {
        int seconds = ticks / 20;
        String text = String.valueOf(seconds);
        text += " seconds left.";
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderCooldown(int ticks)
    {
        int seconds = ticks / 20;
        String text = "Cooldown: ";
        text += seconds;
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderText(String text)
    {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void render()
    {
        assert Minecraft.getInstance().player != null;
        Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            int timeLeft = (int) props.getTimeLeft();
            int cooldown = (int) props.getCooldown();
            int transformed = props.getTransformed();
            if (props.getStandOn())
            {
                //Made in Heaven
                if (props.getStandID() == JojoLibs.StandID.madeInHeaven && props.getAct() == 0)
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
            if(props.getStandID() != JojoLibs.StandID.madeInHeaven && props.getStandID() != JojoLibs.StandID.goldExperience && props.getStandID() != JojoLibs.StandID.GER) {
                if(cooldown > 0)
                    renderCooldown(cooldown);
            } else if(props.getStandID() == JojoLibs.StandID.goldExperience) {
                if(cooldown > 0 && transformed > 0)
                    renderCooldown(cooldown);
            } else if(props.getStandID() == JojoLibs.StandID.GER) {
                if(cooldown > 0 && transformed > 1)
                    renderCooldown(cooldown);
            }
        });
    }
}
