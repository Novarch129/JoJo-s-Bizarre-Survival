package io.github.novarch129.jojomod.item;

import io.github.novarch129.jojomod.capability.stand.IStand;
import io.github.novarch129.jojomod.capability.stand.Stand;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RemoveStandItem extends Item {
    public RemoveStandItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("On use removes the player's current Stand."));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (worldIn.isRemote) return ActionResult.resultPass(stack);
        IStand props = Stand.getCapabilityFromPlayer(playerIn);
        if (Stand.getLazyOptional(playerIn).isPresent()) {
            props.removeStand();
            return ActionResult.resultConsume(stack);
        }
        return ActionResult.resultFail(stack);
    }
}
