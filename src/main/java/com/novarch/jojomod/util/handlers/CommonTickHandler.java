package com.novarch.jojomod.util.handlers;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.capabilities.stand.IStand;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID)
public class CommonTickHandler 
{
	  private int tickSlow = 0;
	  
	  @SubscribeEvent
	  public void itemTick(TickEvent.PlayerTickEvent event) 
	  {
	    if (!event.player.world.isRemote)
	      if (this.tickSlow == 20) {
	        IStand props = (IStand)event.player.getCapability(JojoProvider.STAND, null);
	        PlayerInventory playerInv = event.player.inventory;
	        if (props != null) 
	        {
	          if (props.getJojoPower() != 0)
	              props.setPowerSpawned(false);
	            }  
	          if (playerInv != null) {
	            for (int i = 0; i < playerInv.mainInventory.size(); i++) 
	            {
	              ItemStack stack = playerInv.getStackInSlot(i);
	              if (stack != null)
	                  JojoLibs.destroyItemInSlot((IInventory)playerInv, stack, i);  
	            } 
	            this.tickSlow = 0;
	          }
	  		else
	        this.tickSlow++;
	  }
	 }
	}