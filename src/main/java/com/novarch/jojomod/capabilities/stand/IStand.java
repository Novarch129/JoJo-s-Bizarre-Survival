package com.novarch.jojomod.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;

public interface IStand {
    PlayerEntity getPlayer();
    int getStandID();
    void setStandID(int standID);
    int getAct();
    void setAct(int act);
    void changeAct();
    boolean hasAct();
    int getMaxAct();
    boolean getStandOn();
    void setStandOn(boolean standOn);
    double getCooldown();
    void setCooldown(double cooldown);
    void subtractCooldown(double subtraction);
    double getTimeLeft();
    void setTimeLeft(double timeLeft);
    void addTimeLeft(double addition);
    void subtractTimeLeft(double subtraction);
    String getDiavolo();
    void setDiavolo(String truth);
    boolean getAbility();
    void setAbility(boolean value);
    int getTransformed();
    void setTransformed(int value);
    void addTransformed(int addition);
    boolean getNoClip();
    void setNoClip(boolean noClip);
    void putStandID(final int standID);
    void putAct(final int act);
    void putStandOn(final boolean standOn);
    void putTimeLeft(final double timeleft);
    void putCooldown(final double cooldown);
    void putAbility(final boolean ability);
    void putTransformed(final int transformed);
    void putDiavolo(final String truth);
    void putNoClip(boolean noClip);
    void clone(IStand props);
    void removeStand();
    void onDataUpdated();
}
