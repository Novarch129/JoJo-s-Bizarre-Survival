package com.novarch.jojomod.capabilities;

import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("unused")
public interface IStand
{
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

    void cloneSaveFunction(final IStand p0);
    
    void setCooldown(int new_cooldown);

    void addCooldown(int addition);

    void subtractCooldown(int subtraction);
    
    int getCooldown();

    void setTimeLeft(int new_time_left);

    int getTimeLeft();

    void addTimeLeft(int addition);

    void subtractTimeLeft(int subtraction);

    PlayerEntity getDiavolo();

    void setDiavolo(PlayerEntity truth);
}
