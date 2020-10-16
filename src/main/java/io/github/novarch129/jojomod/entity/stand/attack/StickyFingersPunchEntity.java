package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class StickyFingersPunchEntity extends AbstractStandAttackEntity {
    private boolean isZipPunch;

    public StickyFingersPunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public StickyFingersPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.STICKY_FINGERS_PUNCH.get(), worldIn, shooter, player);
    }

    public StickyFingersPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player, boolean isZipPunch) {
        super(EntityInit.STICKY_FINGERS_PUNCH.get(), worldIn, shooter, player);
        this.isZipPunch = isZipPunch;
    }


    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), isZipPunch ? 7 : (shootingStand.attackRush ? 1.2f : 2.5f));
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

    @Override
    protected int getRange() {
        return isZipPunch ? 5 : super.getRange();
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.STICKY_FINGERS_PUNCH;
    }
}
