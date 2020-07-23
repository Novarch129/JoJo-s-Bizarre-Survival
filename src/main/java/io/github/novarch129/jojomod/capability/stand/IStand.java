package io.github.novarch129.jojomod.capability.stand;

import net.minecraft.entity.player.PlayerEntity;

public interface IStand {
    PlayerEntity getPlayer();

    int getStandID();

    void setStandID(int standID);

    int getPlayerStand();

    void setPlayerStand(int standEntityID);

    void putPlayerStand(int standEntityID);

    int getAct();

    void setAct(int standAct);

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

    void setAbility(boolean ability);

    int getTransformed();

    void setTransformed(int value);

    void addTransformed(int addition);

    boolean getNoClip();

    void setNoClip(boolean noClip);

    void putStandID(int standID);

    void putAct(int act);

    void putStandOn(boolean standOn);

    void putTimeLeft(double timeLeft);

    void putCooldown(double cooldown);

    void putAbility(boolean ability);

    void putTransformed(int transformed);

    void putDiavolo(String truth);

    void putNoClip(boolean noClip);

    boolean getAbilityActive();

    void setAbilityActive(boolean abilityActive);

    void putAbilityActive(boolean abilityActive);

    void clone(IStand props);

    void removeStand();

    void onDataUpdated();
}
