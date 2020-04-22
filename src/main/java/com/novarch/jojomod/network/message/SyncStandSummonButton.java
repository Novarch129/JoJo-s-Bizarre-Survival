package com.novarch.jojomod.network.message;

import java.util.List;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.capabilities.stand.IStand;
import com.novarch.jojomod.util.capabilities.stand.IStandCapability;
import com.novarch.jojomod.util.capabilities.stand.JojoProvider;

import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

@SuppressWarnings("unused")
public class SyncStandSummonButton
{
	public int entityID;
	
	public SyncStandSummonButton() {}
	
	public SyncStandSummonButton(int entityId) { this.entityID = entityId; }

	public static void encode(SyncStandSummonButton msg, PacketBuffer buffer)
	{
		buffer.writeInt(msg.entityID);
	}

	public static SyncStandSummonButton decode(PacketBuffer buffer)
	{
		return new SyncStandSummonButton(buffer.readInt());
	}

	public static void handle(SyncStandSummonButton msg, Supplier<NetworkEvent.Context> supplier)
	{
		final NetworkEvent.Context ctx = supplier.get();
		if(ctx.getDirection().getReceptionSide().isServer())
		{
		ctx.enqueueWork(() ->
		{
			ServerPlayerEntity sender = ctx.getSender();
			if(sender == null)
				return;
			work(msg, supplier, sender);
		});
		}
		ctx.setPacketHandled(true);
	 }
		  
		public static void work(SyncStandSummonButton message, Supplier<NetworkEvent.Context> ctx, ServerPlayerEntity serverPlayer)
		{
			ServerPlayerEntity player = serverPlayer;
		      World world = player.world;
		      if (!world.isRemote)
		        if (player != null) {
		          LazyOptional<IStand> power = player.getCapability(JojoProvider.STAND, null);
		          IStand props = power.orElse(new IStandCapability());
		          if (props != null) {
		            int tickDelay = 0;
		            for (int k = 0; k < 20; k++) {
		              tickDelay++;
		              if (tickDelay == 20) {
		                tickDelay = 0;
		                if (!player.isCrouching()) {
		                  boolean standCheck = props.getStandOn();
		                  if (standCheck) {
		                    props.setStandOn(false);
		                    return;
		                  } 
		                  SyncStandSummonButton.summonStand((PlayerEntity)player);
		                  return;
		                } 
		                List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity((Entity)player, new AxisAlignedBB(player.getPosX() - 1.0D, player.getPosY() - 1.0D, player.getPosZ() - 1.0D, player.getPosX() + 1.0D, player.getPosY() + 1.0D, player.getPosZ() + 1.0D));
		                if (entityList != null && !entityList.isEmpty())
		                  for (int i = 0; i < entityList.size(); i++) {
		                    if (entityList.get(i) instanceof EntityStandBase) {
		                      EntityStandBase stand = (EntityStandBase) entityList.get(i);
		                      if (stand.getMaster() == player)
		                        if (stand.getCanChangeAct()) {
		                          stand.changeAct();
		                          return;
		                        }  
		                    } 
		                  }  
		              } 
		            } 
		          } 
		        }  
		      return;
		}
		
		  public static void summonStand(PlayerEntity player)
		  {
			  LazyOptional<IStand> power = player.getCapability(JojoProvider.STAND, null);
			  IStand props = power.orElse(new IStandCapability());
			  int standID = props.getStandID();
			  EntityStandBase theStand = JojoLibs.getStand(standID, player.world);
			  
			  if (theStand != null)
			  {
				  int standAct = props.getStandAct();
				  props.setStandOn(true);
				  theStand.setLocationAndAngles(player.getPosX() + 0.1D, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
				  theStand.setMaster(player.getCommandSource().getName());
				  theStand.setMastername(player.getDisplayName().toString());
				  String name = props.getPlayerStandName();
				  
				  if (!name.equals(""))
					  theStand.setCustomName(((ITextComponent)new TranslationTextComponent(name, null, new Object[0]))); 
				  
				  player.world.addEntity((Entity)theStand);
				  theStand.spawnSound();
				  theStand.setGiveItems();
				  theStand.changeAct();
		      return;
		    } 
		}
}
