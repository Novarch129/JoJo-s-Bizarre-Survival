package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public interface IStandEffects {
    Entity getEntity();

    void onDataUpdated();

    boolean isCrimson();

    void setCrimson(boolean crimson);

    boolean isAging();

    void setAging(boolean aging);

    Vec3d getMotion();

    void setMotion(Vec3d motion);
}
