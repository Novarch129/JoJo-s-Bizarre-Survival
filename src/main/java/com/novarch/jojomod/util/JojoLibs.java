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
        public static final int nullStand = 0;

        public static final int kingCrimson = 1;

        public static final int dirtyDeedsDoneDirtCheap = 2;

        public static final int goldExperience = 3;

        public static final int madeInHeaven = 4;

        public static final int GER = 5;
    }

    public static int numberOfStands = 5;

    public static EntityStandBase getStand(int standID, World world)
    {
        switch (standID)
        {
            case StandID.nullStand:
                return null;
            case StandID.kingCrimson:
                return new EntityKingCrimson(world);
            case StandID.dirtyDeedsDoneDirtCheap:
                return new EntityDirtyDeedsDoneDirtCheap(world);
            case StandID.goldExperience:
                return new EntityGoldExperience(world);
            case StandID.madeInHeaven:
                return new EntityMadeInHeaven(world);
            case StandID.GER:
                return new EntityGoldExperienceRequiem(world);
        }
        return null;
    }
}
