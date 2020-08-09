package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.client.entity.model.DefaultStandAttackModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class WhitesnakePunchEntity extends AbstractStandAttackEntity {
    public WhitesnakePunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public WhitesnakePunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.WHITESNAKE_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 0.8f);
        entity.hurtResistantTime = 0;
        if (shootingStand.ability)
            if (entity instanceof PlayerEntity)
                Stand.getLazyOptional((PlayerEntity) entity).ifPresent(props -> {
                    if (props.getStandID() != 0 && props.getStandID() != Util.StandID.GER && ((PlayerEntity) entity).getHealth() <= ((PlayerEntity) entity).getMaxHealth() / 2) {
                        ItemStack itemStack = new ItemStack(ItemInit.STAND_DISC.get());
                        CompoundNBT nbt = itemStack.getTag() == null ? new CompoundNBT() : itemStack.getTag();
                        if (standMaster.inventory.getStackInSlot(standMaster.inventory.getBestHotbarSlot()).isEmpty()) {
                            standMaster.inventory.currentItem = standMaster.inventory.getBestHotbarSlot();
                            nbt.putInt("StandID", props.getStandID());
                            props.removeStand();
                            itemStack.setTag(nbt);
                            standMaster.inventory.add(standMaster.inventory.getBestHotbarSlot(), itemStack);
                        }
                    }
                });
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
        return Util.ResourceLocations.WHITESNAKE_PUNCH;
    }

    @Override
    public <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel() {
        return new DefaultStandAttackModel<>();
    }
}
