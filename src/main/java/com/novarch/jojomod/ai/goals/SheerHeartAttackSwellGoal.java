package com.novarch.jojomod.ai.goals;

import com.novarch.jojomod.entities.stands.SheerHeartAttackEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * Almost the same as {@link net.minecraft.entity.ai.goal.CreeperSwellGoal}, needed to make my own version to be able to use it for {@link SheerHeartAttackEntity}.
 */
public class SheerHeartAttackSwellGoal extends Goal {
    private final SheerHeartAttackEntity swellingSHA;
    private LivingEntity shaAttackTarget;

    public SheerHeartAttackSwellGoal(SheerHeartAttackEntity sheerHeartAttackEntityIn) {
        swellingSHA = sheerHeartAttackEntityIn;
        setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        LivingEntity livingentity = swellingSHA.getAttackTarget();
        return swellingSHA.getSheerHeartAttackState() > 0 || livingentity != null && swellingSHA.getDistanceSq(livingentity) < 9;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        swellingSHA.getNavigator().clearPath();
        shaAttackTarget = swellingSHA.getAttackTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        shaAttackTarget = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (shaAttackTarget == null)
            swellingSHA.setSheerHeartAttackState(-1);
        else if (swellingSHA.getDistanceSq(shaAttackTarget) > 49)
            swellingSHA.setSheerHeartAttackState(-1);
        else if (!swellingSHA.getEntitySenses().canSee(shaAttackTarget))
            swellingSHA.setSheerHeartAttackState(-1);
        else
            swellingSHA.setSheerHeartAttackState(1);
    }
}
