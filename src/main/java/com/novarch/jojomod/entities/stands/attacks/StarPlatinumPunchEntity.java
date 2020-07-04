package com.novarch.jojomod.entities.stands.attacks;

import com.novarch.jojomod.entities.stands.AbstractStandEntity;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import com.novarch.jojomod.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class StarPlatinumPunchEntity extends AbstractStandPunchEntity {
    public StarPlatinumPunchEntity(EntityType<? extends AbstractStandPunchEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public StarPlatinumPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.STAR_PLATINUM_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof DolphinEntity)
            if (!standMaster.isCreative() && !standMaster.isSpectator())
                standMaster.attackEntityFrom(DamageSource.causeMobDamage((DolphinEntity) entity), 5);
        if (standMaster.isPotionActive(Effects.DOLPHINS_GRACE))
            entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), shootingStand.orarush ? 2f : 3.5f);
        else
            entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), shootingStand.orarush ? 1.4f : 2.9f);
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 4) {
            world.removeBlock(pos, false);
            state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
        }
    }
}
