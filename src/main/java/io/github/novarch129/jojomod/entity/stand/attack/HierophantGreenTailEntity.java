package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.client.entity.model.HierophantGreenTailModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class HierophantGreenTailEntity extends AbstractStandAttackEntity {
    public HierophantGreenTailEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HierophantGreenTailEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.HIEROPHANT_GREEN_TAIL.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 3);
        if (entity instanceof LivingEntity) {
            if (((HierophantGreenEntity) shootingStand).possessedEntity instanceof MobEntity) {
                ((MobEntity) ((HierophantGreenEntity) shootingStand).possessedEntity).goalSelector.enableFlag(Goal.Flag.LOOK);
                ((MobEntity) ((HierophantGreenEntity) shootingStand).possessedEntity).goalSelector.enableFlag(Goal.Flag.MOVE);
            }
            ((HierophantGreenEntity) shootingStand).possessedEntity = (LivingEntity) entity;
        }
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3) {
            world.removeBlock(pos, false);
            if (world.rand.nextBoolean())
                state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
        }
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.HIEROPHANT_GREEN_TAIL;
    }

    @Override
    public <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel() {
        return (EntityModel<T>) new HierophantGreenTailModel();
    }

    @Override
    protected int getRange() {
        return 4; //Default value is 2, tail has much more range.
    }
}
