package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.client.entity.model.TheGratefulDeadPunchModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class TheGratefulDeadPunchEntity extends AbstractStandAttackEntity {
    public TheGratefulDeadPunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public TheGratefulDeadPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.THE_GRATEFUL_DEAD_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), shootingStand.attackRush ? 0.5f : 1);
        if (entity instanceof LivingEntity && shootingStand.ability)
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.AGING.get(), 100, 2));
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 4) {
            world.removeBlock(pos, false);
            if (world.rand.nextBoolean())
                state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
        }
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.THE_GRATEFUL_DEAD_PUNCH;
    }

    @Override
    public <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel() {
        return Util.cast(new TheGratefulDeadPunchModel());
    }
}
