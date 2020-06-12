package com.novarch.jojomod.objects.items;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;
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
    public ItemStandDisc(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (!player.world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            CompoundNBT nbt = stack.getTag() == null ? new CompoundNBT() : stack.getTag();
            IStand props = Stand.getCapabilityFromPlayer(player);
            if (props.getStandID() != 0 && props.getStandID() != JojoLibs.StandID.GER && nbt.getInt("StandID") == 0) {
                nbt.putInt("StandID", props.getStandID());
                props.removeStand();
                stack.setTag(nbt);
                return new ActionResult<>(ActionResultType.CONSUME, stack);
            } else if (props.getStandID() == 0 && nbt.getInt("StandID") != 0) {
                props.setStandID(nbt.getInt("StandID"));
                nbt.putInt("StandID", 0);
                return new ActionResult<>(ActionResultType.CONSUME, stack);
            }
            stack.setTag(nbt);
        }
        return new ActionResult<>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity livingEntityIn, LivingEntity attacker) {
        CompoundNBT nbt = stack.getTag() == null ? new CompoundNBT() : stack.getTag();
        if (livingEntityIn instanceof PlayerEntity) {
            Stand.getLazyOptional((PlayerEntity) livingEntityIn).ifPresent(props -> {
                if (attacker instanceof PlayerEntity) {
                    final int standID = Stand.getCapabilityFromPlayer(((PlayerEntity) attacker)).getStandID();
                    final int standAct = Stand.getCapabilityFromPlayer(((PlayerEntity) attacker)).getAct();
                    if(standID == JojoLibs.StandID.whitesnake || (standID == JojoLibs.StandID.madeInHeaven && standAct == 2)) {
                        if (props.getStandID() != 0 && nbt.getInt("StandID") == 0) {
                            nbt.putInt("StandID", props.getStandID());
                            props.removeStand();
                            stack.setTag(nbt);
                        } else if (props.getStandID() == 0 && props.getStandID() != JojoLibs.StandID.GER && nbt.getInt("StandID") != 0) {

                            props.setStandID(nbt.getInt("StandID"));
                            nbt.putInt("StandID", 0);
                        }
                        stack.setTag(nbt);
                    }
                }
            });
        }
        return true;
    }
}
