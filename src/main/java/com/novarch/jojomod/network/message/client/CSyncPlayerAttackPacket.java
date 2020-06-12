package com.novarch.jojomod.network.message.client;

import java.util.List;
import java.util.function.Supplier;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.Stand;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CSyncPlayerAttackPacket
{

	public static void encode(CSyncPlayerAttackPacket msg, PacketBuffer buffer)
	{
	}

	public static CSyncPlayerAttackPacket decode(PacketBuffer buffer)
	{
		return new CSyncPlayerAttackPacket();
	}

	public static void handle(CSyncPlayerAttackPacket message, Supplier<Context> ctx) {
		ServerPlayerEntity player = (ctx.get().getSender());
	      World world = player.world;
	      if (!world.isRemote)
	        if (player != null) {
	          LazyOptional<IStand> power = player.getCapability(Stand.STAND, null);
	          IStand props = power.orElse(new Stand(player));
	          if (props != null)
	            if (props.getStandOn()) {
	              List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity((Entity)player, new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D));
	              if (entityList != null && !entityList.isEmpty())
	                for (int i = 0; i < entityList.size(); i++) {
	                  if (entityList.get(i) instanceof EntityStandBase) {
	                    EntityStandBase stand = (EntityStandBase) entityList.get(i);
	                    if (stand.getMaster() == player) {
	                      stand.setAttackTrue(true);
	                      return;
	                    } 
	                  } 
	                }  
	            }  
	        }  
	      return;
	    }
		
	}
