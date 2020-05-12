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
    
    void setCooldown(int new_cooldown);

    void addCooldown(int addition);

    void subtractCooldown(int subtraction);
    
    int getCooldown();

    void setTimeLeft(int new_time_left);

    int getTimeLeft();

    void addTimeLeft(int addition);

    void subtractTimeLeft(int subtraction);

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

    void putTimeLeft(final int timeleft);

    void putCooldown(final int cooldown);

    void putAbility(final boolean ability);

    void putDiavolo(final String truth);
}
