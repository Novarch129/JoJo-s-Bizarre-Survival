package com.novarch.jojomod.ai.goals;

import com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack.EntitySheerHeartAttack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * Code taken from {@link net.minecraft.entity.ai.goal.CreeperSwellGoal} for more customization
 */
public class SheerHeartAttackSwellGoal extends Goal
{
    private final EntitySheerHeartAttack swellingSHA;
    private LivingEntity creeperAttackTarget;

    public SheerHeartAttackSwellGoal(EntitySheerHeartAttack entitySheerHeartAttackIn) {
        this.swellingSHA = entitySheerHeartAttackIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        LivingEntity livingentity = this.swellingSHA.getAttackTarget();
        return this.swellingSHA.getSheerHeartAttackState() > 0 || livingentity != null && this.swellingSHA.getDistanceSq(livingentity) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.swellingSHA.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingSHA.getAttackTarget();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.creeperAttackTarget = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.creeperAttackTarget == null) {
            this.swellingSHA.setSheerHeartAttackState(-1);
        } else if (this.swellingSHA.getDistanceSq(this.creeperAttackTarget) > 49.0D) {
            this.swellingSHA.setSheerHeartAttackState(-1);
        } else if (!this.swellingSHA.getEntitySenses().canSee(this.creeperAttackTarget)) {
            this.swellingSHA.setSheerHeartAttackState(-1);
        } else {
            this.swellingSHA.setSheerHeartAttackState(1);
        }
    }
}
