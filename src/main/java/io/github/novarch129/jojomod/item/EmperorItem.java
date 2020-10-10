package io.github.novarch129.jojomod.item;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.EmperorBulletEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EmperorItem extends Item {
    public EmperorItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!playerIn.world.isRemote) {
            Stand stand = Stand.getCapabilityFromPlayer(playerIn);
            if (stand.getStandID() != Util.StandID.THE_EMPEROR)
                itemStack.shrink(1);
            if (stand.getCooldown() <= 0) {
                EmperorBulletEntity bullet = new EmperorBulletEntity(playerIn, worldIn);
                bullet.setSilent(true);
                bullet.setPositionAndRotation(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), playerIn.rotationYaw, playerIn.rotationPitch);
                bullet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1, Float.MIN_VALUE);
                playerIn.world.addEntity(bullet);
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
