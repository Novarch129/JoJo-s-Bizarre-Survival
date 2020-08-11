package io.github.novarch129.jojomod.client.gui;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

@SuppressWarnings("unused")
public class StandGUI extends AbstractGui {
    public static final Minecraft mc = Minecraft.getInstance();

    public void renderTimeValue(int ticks) {
        int minutes = ticks / 1200;
        int seconds = (ticks / 20) - (minutes * 60);
        String text = String.valueOf(minutes);
        if (seconds < 10)
            text += ":0" + seconds;
        else
            text += ":" + seconds;
        renderText(text);
    }

    public void renderTimeLeft(int ticks) {
        int seconds = ticks / 20;
        String text = String.valueOf(seconds);
        text += " seconds left.";
        renderText(text);
    }

    public void renderCooldown(int ticks) {
        int seconds = ticks / 20;
        String text = "Cooldown: ";
        text += seconds;
        renderText(text);
    }

    protected void renderString(String text) {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void renderText(String text) {
        drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
    }

    public void render() {
        assert mc.player != null;
        Stand.getLazyOptional(mc.player).ifPresent(props -> {
            int standID = props.getStandID();
            int timeLeft = (int) props.getTimeLeft();
            int cooldown = (int) props.getCooldown();
            int transformed = props.getTransformed();
            double invulnerableTicks = props.getInvulnerableTicks();
            if (props.getStandOn()) {
                switch (standID) {
                    case (Util.StandID.MADE_IN_HEAVEN): {
                        if (props.getAct() == 0) {
                            if (timeLeft > 0 && cooldown <= 0)
                                renderTimeValue(timeLeft);
                            else
                                renderText("\"Heaven\" has begun!");
                        }
                        break;
                    }
                    case (Util.StandID.THE_GRATEFUL_DEAD):
                    case (Util.StandID.TWENTIETH_CENTURY_BOY):
                    case (Util.StandID.SILVER_CHARIOT):
                    case (Util.StandID.KING_CRIMSON): {
                        if (timeLeft > 801 && cooldown == 0)
                            renderTimeLeft(timeLeft - 800);
                        break;
                    }
                    case (Util.StandID.THE_WORLD): {
                        if (timeLeft > 780 && cooldown == 0)
                            renderTimeLeft(timeLeft - 780);
                        break;
                    }
                    case (Util.StandID.STAR_PLATINUM): {
                        if (timeLeft > 900 && cooldown == 0)
                            renderTimeLeft(timeLeft - 900);
                        break;
                    }
                }
            }
            switch (standID) {
                default: {
                    if (standID != Util.StandID.MADE_IN_HEAVEN)
                        if (cooldown > 0)
                            renderCooldown(cooldown);
                    break;
                }
                case (Util.StandID.GOLD_EXPERIENCE): {
                    if (cooldown > 0 && transformed > 0)
                        renderCooldown(cooldown);
                    break;
                }
                case (Util.StandID.GER): {
                    if (cooldown > 0 && transformed > 1)
                        renderCooldown(cooldown);
                    break;
                }
                case (Util.StandID.KING_CRIMSON): {
                    if (cooldown > 0)
                        renderCooldown(cooldown);
                    if (invulnerableTicks > 0 && !props.getStandOn())
                        renderText("Invulnerable ticks: " + invulnerableTicks);
                    break;
                }
            }
        });
    }
}
