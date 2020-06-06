package com.novarch.jojomod.objects.items;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemStandDisc extends Item {
    private int standID;

    public ItemStandDisc(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!player.world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            CompoundNBT nbt = stack.getTag() == null ? new CompoundNBT() : stack.getTag();
            IStand props = JojoProvider.getCapabilityFromPlayer(player);
            if (props.getStandID() != 0 && standID == 0) {
                nbt.putInt("StandID", props.getStandID());
                props.removeStand();
                return new ActionResult<>(ActionResultType.CONSUME, stack);
            } else if (props.getStandID() == 0 && standID != 0) {
                props.setStandID(standID);
                nbt.putInt("StandID", 0);
                return new ActionResult<>(ActionResultType.CONSUME, stack);
            }
            stack.setTag(nbt);
        }
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(isSelected)
            if(stack.getTag() != null)
                standID = stack.getTag().getInt("StandID");
    }
}
