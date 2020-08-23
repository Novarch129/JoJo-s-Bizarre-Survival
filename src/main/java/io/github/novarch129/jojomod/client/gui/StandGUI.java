package io.github.novarch129.jojomod.client.gui;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

@SuppressWarnings("unused")
public class StandGUI extends AbstractGui {
    public static final Minecraft mc = Minecraft.getInstance();

    public void renderTimeValue(int ticks) {
        int seconds = (ticks / 20) - (ticks / 1200 * 60);
        renderString((ticks / 1200) + (seconds < 10 ? ":0" : ":") + seconds);
    }

    public void renderTimeLeft(int ticks) {
        renderString(ticks / 20 + " seconds left.");
    }

    public void renderCooldown(int ticks) {
        renderString("Cooldown: " + ticks / 20);
    }

    protected void renderString(String text, int... position) {
        if (position.length < 2)
            drawString(mc.fontRenderer, text, 4, 4, 0xFFFFFF);
        else if (position.length == 2)
            drawString(mc.fontRenderer, text, position[0], position[1], 0xFFFFFF);
    }

    public void render() {
        assert mc.player != null;
        Stand.getLazyOptional(mc.player).ifPresent(props -> {
            int standID = props.getStandID();
            int act = props.getAct();
            int timeLeft = (int) props.getTimeLeft();
            int cooldown = (int) props.getCooldown();
            int transformed = props.getTransformed();
            double invulnerableTicks = props.getInvulnerableTicks();
            boolean charging = props.isCharging();
            float damage = props.getStandDamage();
            int abilityUseCount = props.getAbilityUseCount();
            if (props.getStandOn()) {
                switch (standID) {
                    case Util.StandID.MADE_IN_HEAVEN: {
                        if (props.getAct() == 0) {
                            if (timeLeft > 0 && cooldown <= 0)
                                renderTimeValue(timeLeft);
                            else
                                renderString("\"Heaven\" has begun!");
                        }
                        break;
                    }
                    case Util.StandID.TWENTIETH_CENTURY_BOY:
                    case Util.StandID.SILVER_CHARIOT: {
                        if (timeLeft > 801 && cooldown == 0)
                            renderTimeLeft(timeLeft - 800);
                        break;
                    }
                    case Util.StandID.KING_CRIMSON: {
                        if (timeLeft > 801 && cooldown == 0 && invulnerableTicks == 0)
                            renderTimeLeft(timeLeft - 800);
                        if (charging && damage > 3.5f)
                            renderString("Damage: " + damage + (damage == 13 ? " MAX DAMAGE" : ""), 4, (cooldown > 0 || invulnerableTicks > 0 || timeLeft > 801 ? 16 : 4));
                        break;
                    }
                    case Util.StandID.THE_WORLD: {
                        if (timeLeft > 780 && cooldown == 0 && invulnerableTicks == 0)
                            renderTimeLeft(timeLeft - 780);
                        break;
                    }
                    case Util.StandID.STAR_PLATINUM: {
                        if (timeLeft > 900 && cooldown == 0 && invulnerableTicks == 0)
                            renderTimeLeft(timeLeft - 900);
                        break;
                    }
                    case Util.StandID.STICKY_FINGERS:
                    case Util.StandID.THE_GRATEFUL_DEAD: {
                        if (timeLeft > 601 && cooldown == 0)
                            renderTimeLeft(timeLeft - 600);
                        break;
                    }
                    case Util.StandID.TUSK_ACT_2:
                    case Util.StandID.TUSK_ACT_1: {
                        renderString("Nails left: " + (10 - abilityUseCount));
                        if (charging && damage > 4.5f && cooldown == 0)
                            renderString("Damage: " + damage + (damage == (standID == Util.StandID.TUSK_ACT_1 ? 15 : 26) ? " MAX DAMAGE" : ""), 4, 16);
                        break;
                    }
                    case Util.StandID.TUSK_ACT_3: {
                        renderString("Nails left: " + (10 - abilityUseCount));
                        if (charging && damage > 7 && cooldown == 0)
                            renderString("Damage: " + damage + (damage == (act == 2 ? 15 : 26) ? " MAX DAMAGE" : ""), 4, act == 1 && props.getAbilityActive() ? 32 : 16);
                        if (props.getAbilityActive() && act == 0 && cooldown == 0)
                            renderString((timeLeft - 800) / 20 + " seconds left.", 4, 16);
                    }
                    case Util.StandID.TUSK_ACT_4: {
                        renderString("Nails left: " + (10 - abilityUseCount));
                        int maxDamage = 0;
                        switch (act) {
                            case 4:
                            case 0: {
                                maxDamage = 55;
                                break;
                            }
                            case 1:
                            case 2: {
                                maxDamage = 26;
                                break;
                            }
                            case 3: {
                                maxDamage = 15;
                                break;
                            }
                            default:
                                break;
                        }
                        if (charging && damage > 7 && cooldown == 0)
                            renderString("Damage: " + damage + (damage == maxDamage ? " MAX DAMAGE" : ""), 4, act == 1 && props.getAbilityActive() ? 32 : 16);
                        if (props.getAbilityActive() && act == 1 && cooldown == 0)
                            renderString((timeLeft - 800) / 20 + " seconds left.", 4, 16);
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
                case Util.StandID.GOLD_EXPERIENCE: {
                    if (cooldown > 0 && transformed > 0)
                        renderCooldown(cooldown);
                    break;
                }
                case Util.StandID.GER: {
                    if (cooldown > 0 && transformed > 1)
                        renderCooldown(cooldown);
                    break;
                }
                case Util.StandID.STAR_PLATINUM:
                case Util.StandID.THE_WORLD:
                case Util.StandID.KING_CRIMSON: {
                    if (cooldown > 0)
                        renderString("Cooldown: " + cooldown / 20, 4, (invulnerableTicks > 0 ? 16 : 4));
                    if (invulnerableTicks > 0)
                        renderString("Invulnerable ticks: " + (int) (invulnerableTicks / 20));
                    break;
                }
                case Util.StandID.TUSK_ACT_4:
                case Util.StandID.TUSK_ACT_3:
                case Util.StandID.TUSK_ACT_2:
                case Util.StandID.TUSK_ACT_1: {
                    if (cooldown > 0)
                        renderString("Cooldown: " + cooldown / 20, 4, props.getStandOn() ? 16 : 4);
                    break;
                }
            }
        });
    }
}
