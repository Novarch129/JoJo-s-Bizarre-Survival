package io.github.novarch129.jojomod.util;

/**
 * Applied to an {@link io.github.novarch129.jojomod.entity.stand.AbstractStandEntity}, indicates that it can charge it's attacks.
 */
public interface IChargeable {
    void chargeAttack(boolean isCharging);

    int getChargeTicks();

    void setChargeTicks(int chargeTicks);

    int getPrevChargeTicks();
}
