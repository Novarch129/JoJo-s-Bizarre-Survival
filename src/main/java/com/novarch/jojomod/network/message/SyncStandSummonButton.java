package com.novarch.jojomod.network.message;

import java.util.List;

import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import com.novarch.jojomod.capabilities.stand.JojoProvider;

import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
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
			FakePlayerEntity fakePlayer = new FakePlayerEntity(serverPlayer.world, serverPlayer);
			fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
			World world = serverPlayer.world;
			if (!world.isRemote)
			{
				JojoProvider.getLazyOptional(serverPlayer).ifPresent(props -> {
					int tickDelay = 0;
					for (int k = 0; k < 20; k++) {
						tickDelay++;
						if (tickDelay == 20)
						{
							tickDelay = 0;
							if (!serverPlayer.isCrouching())
							{
								boolean standCheck = props.getStandOn();
								if (standCheck) {
									if (fakePlayer.isAlive())
										fakePlayer.remove();
									props.setStandOn(false);
									return;
								}
								SyncStandSummonButton.summonStand(serverPlayer, fakePlayer);
								return;
							}
							List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(serverPlayer, new AxisAlignedBB(serverPlayer.getPosX() - 1.0D, serverPlayer.getPosY() - 1.0D, serverPlayer.getPosZ() - 1.0D, serverPlayer.getPosX() + 1.0D, serverPlayer.getPosY() + 1.0D, serverPlayer.getPosZ() + 1.0D));
							if (!entityList.isEmpty()) {
								for (Entity entity : entityList) {
									if (entity instanceof EntityStandBase) {
										EntityStandBase stand = (EntityStandBase) entity;
										if (stand.getMaster() == serverPlayer)
											if (stand.getCanChangeAct()) {
												stand.changeAct();
												return;
											}
									}
								}
							}
						}
					}
				});
			}
		}
		
		  public static void summonStand(PlayerEntity player, FakePlayerEntity fakePlayer) {
			  JojoProvider.getLazyOptional(player).ifPresent(props -> {
				  int standID = props.getStandID();
				  EntityStandBase theStand = JojoLibs.getStand(standID, player.world);

				  if (theStand != null) {
					  if (!player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(1000.0, 1000.0, 1000.0), EntityPredicates.NOT_SPECTATING).contains(theStand)) {
						  int standAct = props.getAct();
						  if (props.getStandID() == JojoLibs.StandID.aerosmith && props.getAbility())
							  player.world.addEntity(fakePlayer);
						  props.setStandOn(true);
						  theStand.setLocationAndAngles(player.getPosX() + 0.1D, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
						  theStand.setMaster(player);
						  theStand.setMasterUUID(player.getUniqueID());
						  String name = props.getPlayerStandName();

						  if (!name.equals(""))
							  theStand.setCustomName((new StringTextComponent(name)));

						  player.world.addEntity(theStand);
						  theStand.spawnSound();
						  theStand.changeAct();
					  } else
						  theStand.remove();
				  }
			  });
		}
}
