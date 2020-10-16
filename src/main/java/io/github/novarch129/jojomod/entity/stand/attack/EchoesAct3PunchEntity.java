package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.StandEffects;
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

public class EchoesAct3PunchEntity extends AbstractStandAttackEntity {
    public EchoesAct3PunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EchoesAct3PunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.ECHOES_ACT_3_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), shootingStand.attackRush ? 1.5f : 2);
        StandEffects.getLazyOptional(entity).ifPresent(props -> {
            props.setThreeFreeze(shootingStand.ability);
            props.setStandUser(standMaster.getUniqueID());
        });
        StandEffects.getLazyOptional(standMaster).ifPresent(props -> props.setThreeFreeze(false));
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
    protected int getRange() {
        return 2;
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.ECHOES_ACT_3_PUNCH;
    }
}
