package com.novarch.jojomod.ai.goals;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;

public class StandFollowMasterGoal extends Goal {
    private final EntityStandBase stand;
    private final PathNavigator pathNavigator;
    private PlayerEntity master;
    private int timeToRecalcPath;

    public StandFollowMasterGoal(EntityStandBase stand, PathNavigator pathNavigator) {
        this.stand = stand;
        this.pathNavigator = pathNavigator;
        this.master = stand.getMaster();
    }

    @Override
    public boolean shouldExecute() {
        if(master == null)
            return false;
        return !master.isSpectator();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !pathNavigator.noPath();
    }

    @Override
    public void resetTask() {
        pathNavigator.clearPath();
    }

    /*@Override
    public void tick() {
        super.tick();
        stand.getLookController().setLookPositionWithEntity(master, 10.0f, stand.getVerticalFaceSpeed());
    }*/

    public void tick() {
        if(master == null)
            return;
        stand.getLookController().setLookPositionWithEntity(master, 10.0F, (float)stand.getVerticalFaceSpeed());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!stand.getLeashed() && !stand.isPassenger()) {
                if (stand.getDistanceSq(master) >= 144.0D) {
                    this.func_226330_g_();
                } else {
                    pathNavigator.tryMoveToEntityLiving(master, 10);
                }

            }
        }
    }

    private void func_226330_g_() {
        BlockPos lvt_1_1_ = new BlockPos(master);

        for(int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
            int lvt_3_1_ = this.func_226327_a_(-3, 3);
            int lvt_4_1_ = this.func_226327_a_(-1, 1);
            int lvt_5_1_ = this.func_226327_a_(-3, 3);
            boolean lvt_6_1_ = this.func_226328_a_(lvt_1_1_.getX() + lvt_3_1_, lvt_1_1_.getY() + lvt_4_1_, lvt_1_1_.getZ() + lvt_5_1_);
            if (lvt_6_1_) {
                return;
            }
        }

    }

    private boolean func_226328_a_(int p_226328_1_, int p_226328_2_, int p_226328_3_) {
        if (Math.abs(p_226328_1_ - master.getPosX()) < 2.0D && Math.abs(p_226328_3_ - master.getPosZ()) < 2.0D) {
            return false;
        } else if (!this.func_226329_a_(new BlockPos(p_226328_1_, p_226328_2_, p_226328_3_))) {
            return false;
        } else {
            stand.setLocationAndAngles(((float)p_226328_1_ + 0.5F), p_226328_2_, ((float)p_226328_3_ + 0.5F), stand.rotationYaw, stand.rotationPitch);
            pathNavigator.clearPath();
            return true;
        }
    }

    private boolean func_226329_a_(BlockPos p_226329_1_) {
        PathNodeType lvt_2_1_ = WalkNodeProcessor.func_227480_b_(stand.world, p_226329_1_.getX(), p_226329_1_.getY(), p_226329_1_.getZ());
        if (lvt_2_1_ != PathNodeType.WALKABLE) {
            return false;
        } else {
            BlockPos lvt_4_1_ = p_226329_1_.subtract(new BlockPos(stand));
            return stand.world.hasNoCollisions(stand, stand.getBoundingBox().offset(lvt_4_1_));
        }
    }

    private int func_226327_a_(int p_226327_1_, int p_226327_2_) {
        return stand.getRNG().nextInt(p_226327_2_ - p_226327_1_ + 1) + p_226327_1_;
    }
}
