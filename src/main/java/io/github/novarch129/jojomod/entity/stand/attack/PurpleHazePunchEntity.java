package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.PurpleHazeEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;

public class PurpleHazePunchEntity extends AbstractStandAttackEntity {
    public PurpleHazePunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PurpleHazePunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.PURPLE_HAZE_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (!world.isRemote)
            Objects.requireNonNull(world.getServer()).getWorld(dimension).getEntities()
                    .filter(entity2 -> entity instanceof MobEntity)
                    .filter(entity2 -> entity.getDistance(this) < 25)
                    .forEach(entity2 -> ((MobEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 150, 1)));
        if (shootingStand.ability && entity instanceof LivingEntity)
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 600, 5));
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 0.85f);
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
            if (shootingStand.ability)
                ((PurpleHazeEntity) shootingStand).burstCapsule();
        }
    }
}
