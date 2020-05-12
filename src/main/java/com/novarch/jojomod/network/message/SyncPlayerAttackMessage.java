package com.novarch.jojomod.network.message;

import java.util.List;
import java.util.function.Supplier;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.capabilities.stand.JojoProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SyncPlayerAttackMessage
{

	public static void encode(SyncPlayerAttackMessage msg, PacketBuffer buffer)
	{
	}

	public static SyncPlayerAttackMessage decode(PacketBuffer buffer)
	{
		return new SyncPlayerAttackMessage();
	}

	public static void handle(SyncPlayerAttackMessage message, Supplier<Context> ctx) {
		ServerPlayerEntity player = (ctx.get().getSender());
	      World world = player.world;
	      if (!world.isRemote)
	        if (player != null) {
	          LazyOptional<IStand> power = player.getCapability(JojoProvider.STAND, null);
	          IStand props = power.orElse(new StandCapability(player));
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
