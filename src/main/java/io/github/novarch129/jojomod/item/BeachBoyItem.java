package io.github.novarch129.jojomod.item;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.BeachBoyBobberEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BeachBoyItem extends FishingRodItem {
    public BeachBoyItem(Properties builder) {
        super(builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        Stand.getLazyOptional(playerIn).ifPresent(stand -> {
            if (playerIn.fishingBobber != null) {
                if (!worldIn.isRemote) {
                    int i = playerIn.fishingBobber.handleHookRetraction(itemstack);
                    itemstack.damageItem(i, playerIn, (playerEntity) ->
                            playerEntity.sendBreakAnimation(handIn));
                }
                worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1, 0.4f / (random.nextFloat() * 0.4f + 0.8f));
            } else {
                worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (random.nextFloat() * 0.4f + 0.8f));
                if (!worldIn.isRemote) {
                    FishingBobberEntity fishingBobber = new BeachBoyBobberEntity(playerIn, worldIn);
                    worldIn.addEntity(fishingBobber);
                }
                playerIn.addStat(Stats.ITEM_USED.get(this));
            }
        });
        return ActionResult.resultSuccess(itemstack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) entityIn).ifPresent(stand -> {
                stand.setAbility(false);
                if (!stand.getStandOn() || stand.getStandID() != Util.StandID.BEACH_BOY)
                    stack.shrink(1);
            });
    }
}
