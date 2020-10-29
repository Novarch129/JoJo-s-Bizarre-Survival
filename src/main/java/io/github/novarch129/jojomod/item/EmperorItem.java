package io.github.novarch129.jojomod.item;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EmperorItem extends Item {
    public EmperorItem(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!playerIn.world.isRemote) {
            Stand stand = Stand.getCapabilityFromPlayer(playerIn);
            if (stand.getStandID() != Util.StandID.THE_EMPEROR)
                itemStack.shrink(1);
            if (stand.getCooldown() <= 0) {
                Vec3d vec3d = playerIn.getPositionVec().add(0, playerIn.getEyeHeight(), 0);
                double range = 100;
                Vec3d vec3d1 = playerIn.getLook(1);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * range, vec3d1.y * range, vec3d1.z * range);
                AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(range)).grow(1, 1, 1);
                EntityRayTraceResult entityRayTraceResult =
                        Util.rayTraceEntities(
                                playerIn,
                                vec3d,
                                vec3d2,
                                axisalignedbb,
                                Util.Predicates.STAND_PUNCH_TARGET.and((predicateEntity) -> !predicateEntity.equals(playerIn)),
                                10000);
                if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) entityRayTraceResult.getEntity();
                    entity.knockBack(playerIn, 5, playerIn.getPosX() - entity.getPosX(), playerIn.getPosZ() - entity.getPosZ());
                    entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 20);

                }
                if (!playerIn.isCreative() && !playerIn.isSpectator())
                    playerIn.getFoodStats().addStats(2, 0);
                stand.setCooldown(100);
                return ActionResult.resultSuccess(itemStack);
            } else
                return ActionResult.resultFail(itemStack);
        }
        return ActionResult.resultPass(itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) entityIn).ifPresent(props -> {
                props.setAbility(false);
                if (!props.getStandOn() || props.getStandID() != Util.StandID.THE_EMPEROR)
                    stack.shrink(1);
            });
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 100;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }
}
