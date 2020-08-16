package io.github.novarch129.jojomod.util;

public interface IChargeable {
    void chargeAttack(boolean isCharging);

    void setChargeTicks(int chargeTicks);

    int getChargeTicks();

    int getPrevChargeTicks();
}
