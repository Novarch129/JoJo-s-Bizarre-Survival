package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.Entity;

public interface IStandEffects {
    Entity getEntity();

    void onDataUpdated();

    boolean isAging();

    void setAging(boolean aging);

    boolean isCrimson();

    void setCrimson(boolean crimson);
}
