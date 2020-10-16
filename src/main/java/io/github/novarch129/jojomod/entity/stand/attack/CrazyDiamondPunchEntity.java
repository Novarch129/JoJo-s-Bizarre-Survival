package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class CrazyDiamondPunchEntity extends AbstractStandAttackEntity {
    public CrazyDiamondPunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public CrazyDiamondPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.CRAZY_DIAMOND_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (!(entity instanceof LivingEntity)) return;
        if (shootingStand.ability)
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 2));
        else
            entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 2.5f);
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3) {
            if (shootingStand.ability) {
                if (state.getMaterial() != Material.AIR && state.getMaterial() != Material.WATER && state.getMaterial() != Material.LAVA)
                    Stand.getLazyOptional(standMaster).ifPresent(stand -> stand.putCrazyDiamondBlock(world.getChunkAt(pos).getPos(), pos, state));
            } else if (world.rand.nextBoolean())
                state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
            world.removeBlock(pos, false);

        }
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.CRAZY_DIAMOND_PUNCH;
    }
}
