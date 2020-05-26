package com.novarch.jojomod.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface IStand
{
    PlayerEntity getPlayer();

    void setStandID(final int p0);

    void setAct(final int act);

    void setStandOn(final boolean p0);

    void setPlayerStandName(final String p0);

    int getStandID();

    int getAct();

    boolean getStandOn();

    String getPlayerStandName();

    void removeStand();

    void clone(final IStand p0);
    
    void setCooldown(double ncooldown);

    void addCooldown(double addition);

    void subtractCooldown(double subtraction);
    
    double getCooldown();

    void setTimeLeft(double timeLeft);

    double getTimeLeft();

    void addTimeLeft(double addition);

    void subtractTimeLeft(double subtraction);

    String getDiavolo();

    void setDiavolo(String truth);

    boolean getAbility();

    void setAbility(boolean value);

    int getTransformed();

    void setTransformed(int value);

    void subtractTransformed(int subtraction);

    void addTransformed(int addition);

    void putStandID(final int standID);

    void putAct(final int act);

    void putStandOn(final boolean standOn);

    void putTimeLeft(final double timeleft);

    void putCooldown(final double cooldown);

    void putAbility(final boolean ability);

    void putTransformed(final int transformed);

    void putDiavolo(final String truth);
}
