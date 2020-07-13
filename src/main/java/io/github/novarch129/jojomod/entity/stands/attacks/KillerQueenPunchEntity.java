package io.github.novarch129.jojomod.entity.stands.attacks;

import io.github.novarch129.jojomod.entity.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stands.KillerQueenEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class KillerQueenPunchEntity extends AbstractStandAttackEntity {
    public KillerQueenPunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public KillerQueenPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.KILLER_QUEEN_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 0.5f);
        if (entity instanceof LivingEntity)
            ((KillerQueenEntity) shootingStand).setBombEntity((LivingEntity) entity);
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
}
