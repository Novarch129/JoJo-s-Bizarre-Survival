package com.novarch.jojomod.util;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.EntityDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.Event;

public class JojoLibs
{
    public static void removeHeldItem(PlayerEntity player)
    {
        ItemStack heldStack = player.getActiveItemStack();
        if (heldStack != null && !player.isCreative());
        destroyItemInSlot((IInventory)player.inventory, heldStack, player.inventory.currentItem);
    }

    public static void destroyItemInSlot(IInventory iInventory, ItemStack stack, int slot)
    {
        if (iInventory instanceof PlayerInventory)
        {
            PlayerInventory playerInventory = (PlayerInventory)iInventory;
            playerInventory.deleteStack(stack);
            MinecraftForge.EVENT_BUS.post((Event)new PlayerDestroyItemEvent(playerInventory.player, stack, Hand.MAIN_HAND));
        }
    }

    public static class StandID
    {
        public static int nullStand = 0;

        public static int kingCrimson = 1;

        public static int dirtyDeedsDoneDirtCheap = 2;

        public static int madeInHeaven = 3;

        public static int weatherReport = 4;

        public static int goldExperience = 5;

        public static int GER = 6;

        public static int theWorld = 7;

        public static int killerQueen = 8;
    }

    public static int numberOfStands = 2;

    public static EntityStandBase getStand(int standID, World world)
    {
        switch (standID)
        {
            case 0:
                return null;
            case 1:
                return (EntityStandBase)new EntityKingCrimson(world);
            case 2:
                return (EntityStandBase)new EntityDirtyDeedsDoneDirtCheap(world);
        }
        return null;
    }

    public static boolean enderCore = false;

    public static void setEnderCore(boolean value) {
        enderCore = value;
    }

        public static boolean getEnderCore() {
            return enderCore;
    }

    public static Vec3d getPlayerPosition(PlayerEntity player, float par2Float)
    {
        if (par2Float == 1.0F)
            return new Vec3d(player.getPosX(), player.getPosY() + 0.1D, player.getPosZ());
        double d0 = player.prevPosX + (player.getPosX() - player.prevPosX) * par2Float;
        double d1 = 1.0D + player.prevPosY + (player.getPosY() - player.prevPosY) * par2Float;
        double d2 = player.prevPosZ + (player.getPosZ() - player.prevPosZ) * par2Float;
        return new Vec3d(d0, d1, d2);
    }

    public static Vec3d getPlayerLook(PlayerEntity player, float par2Float)
    {
        if (par2Float == 1.0F) {
            float f7 = MathHelper.cos(-player.rotationYaw * 0.017453292F - 3.1415927F);
            float f8 = MathHelper.sin(-player.rotationYaw * 0.017453292F - 3.1415927F);
            float f9 = -MathHelper.cos(-player.rotationPitch * 0.017453292F);
            float f10 = MathHelper.sin(-player.rotationPitch * 0.017453292F);
            return new Vec3d((f8 * f9), f10, (f7 * f9));
        }
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * par2Float;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * par2Float;
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        return new Vec3d((f4 * f5), f6, (f3 * f5));
    }
}
