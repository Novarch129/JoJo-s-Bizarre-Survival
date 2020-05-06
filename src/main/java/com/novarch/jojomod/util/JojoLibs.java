package com.novarch.jojomod.util;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.EntityDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;

import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnchantmentNameParts;
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
        if (heldStack != null && !player.isCreative())
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

        public static int goldExperience = 3;

        public static int madeInHeaven = 4;

        public static int GER = 5;
    }

    public static int numberOfStands = 5;

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
            case 3:
                return (EntityStandBase)new EntityGoldExperience(world);
            case 4:
                return (EntityStandBase)new EntityMadeInHeaven(world);
            case 5:
                return (EntityStandBase)new EntityGoldExperienceRequiem(world);
        }
        return null;
    }
}
