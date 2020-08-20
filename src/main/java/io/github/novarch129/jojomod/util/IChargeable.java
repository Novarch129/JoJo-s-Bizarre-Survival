package io.github.novarch129.jojomod.util;

/**
 * Applied to {@link io.github.novarch129.jojomod.entity.stand.AbstractStandEntity}'s, indicates they can charge their attacks.
 */
public interface IChargeable {
    void chargeAttack(boolean isCharging);

    int getChargeTicks();

    void setChargeTicks(int chargeTicks);

    int getPrevChargeTicks();
}
