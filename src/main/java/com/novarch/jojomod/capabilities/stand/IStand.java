package com.novarch.jojomod.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("unused")
public interface IStand
{
    PlayerEntity getPlayer();

    void setStandID(final int p0);

    void setStandAct(final int p0);

    void setStandOn(final boolean p0);

    void setJojoPower(final int p0);

    void setPowerSpawned(final boolean p0);

    void setPlayerStandName(final String p0);

    int getStandID();

    int getStandAct();

    boolean getStandOn();

    int getJojoPower();

    boolean getPowerSpawned();

    String getPlayerStandName();

    void setStandRemoved();

    void clone(final IStand p0);
    
    void setCooldown(double new_cooldown);

    void addCooldown(double addition);

    void subtractCooldown(double subtraction);
    
    double getCooldown();

    void setTimeLeft(double new_time_left);

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

    void putStandAct(final int standAct);

    void putStandOn(final boolean standOn);

    void putTimeLeft(final double timeleft);

    void putCooldown(final double cooldown);

    void putAbility(final boolean ability);

    void putTransformed(final int transformed);

    void putDiavolo(final String truth);
}
