package com.novarch.jojomod.objects.items.stands;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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
public class ItemEmperor extends Item {
    private PlayerEntity master;

    public ItemEmperor(Properties properties) {
        super(properties);
    }

    public PlayerEntity getMaster() {
        return master;
    }

    public void setMaster(PlayerEntity master) {
        this.master = master;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if(!playerIn.world.isRemote) {
//            JojoProvider.getLazyOptional(playerIn).ifPresent(props -> {
//                if(props.getStandID() != JojoLibs.StandID.emperor)
//                    itemStack.shrink(1);
//                if(props.getCooldown() > 0) {
//                    EntityStandPunch.emperor bullet = new EntityStandPunch.emperor(playerIn.world);
//                    bullet.setPositionAndRotation(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), playerIn.rotationYaw, playerIn.rotationPitch);
//                    bullet.shoot(playerIn,  playerIn.rotationPitch, playerIn.rotationYaw, 5.0f, 0);
//                    playerIn.world.addEntity(bullet);
//                    props.setCooldown(50);
//                }
//            });
            ArrowEntity bullet = new ArrowEntity(playerIn.world, playerIn);
            bullet.setSilent(true);
            bullet.setInvisible(true);
            bullet.setPositionAndRotation(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), playerIn.rotationYaw, playerIn.rotationPitch);
            bullet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0, 10.0f, 0.001f);
            bullet.setDamage(bullet.getDamage() * playerIn.getHealth() / 2);
            playerIn.world.addEntity(bullet);
            return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
        }
        return new ActionResult<>(ActionResultType.PASS, itemStack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!isSelected)
            stack.shrink(1);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 200;
    }
}
