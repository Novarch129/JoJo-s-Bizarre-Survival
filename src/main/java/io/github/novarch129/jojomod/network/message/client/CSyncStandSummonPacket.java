package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capabilities.stand.Stand;
import io.github.novarch129.jojomod.entities.FakePlayerEntity;
import io.github.novarch129.jojomod.entities.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.events.custom.StandEvent;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncStandSummonPacket {
	public int entityID;

	public CSyncStandSummonPacket() {
	}

	public CSyncStandSummonPacket(int entityId) {
		this.entityID = entityId;
	}

	public static void encode(CSyncStandSummonPacket msg, PacketBuffer buffer) {
		buffer.writeInt(msg.entityID);
	}

	public static CSyncStandSummonPacket decode(PacketBuffer buffer) {
		return new CSyncStandSummonPacket(buffer.readInt());
	}

	public static void handle(CSyncStandSummonPacket msg, Supplier<NetworkEvent.Context> supplier) {
		final NetworkEvent.Context ctx = supplier.get();
		if (ctx.getDirection().getReceptionSide().isServer()) {
			ctx.enqueueWork(() ->
			{
				ServerPlayerEntity sender = ctx.getSender();
				if (sender == null)
					return;
				summonStand(msg, supplier, sender);
			});
		}
		ctx.setPacketHandled(true);
	}

	private static void summonStand(CSyncStandSummonPacket message, Supplier<NetworkEvent.Context> ctx, ServerPlayerEntity player) {
		FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
		fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
		World world = player.world;
		if (!world.isRemote) {
			Stand.getLazyOptional(player).ifPresent(props -> {
				int delay = 0;
				for (int i = 0; i < 20; i++) {
					delay++;
					if (delay == 20) {
						delay = 0;
						if (!player.isCrouching()) {
							if (props.getStandOn()) {
								if (fakePlayer.isAlive())
									fakePlayer.remove();
								if (!props.hasAct())
									props.setStandOn(false);
								else
									props.changeAct();
								return;
							}
							if (!player.isSpectator())
								if (props.getStandID() != 0 && props.getStandID() != Util.StandID.THE_EMPEROR) {
									AbstractStandEntity stand = Util.getStand(props.getStandID(), player.world);
									if (stand != null) {
										if (!player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(1000.0, 1000.0, 1000.0), entity -> entity instanceof AbstractStandEntity).contains(stand)) {
											if (props.getStandID() == Util.StandID.AEROSMITH && props.getAbility())
												player.world.addEntity(fakePlayer);
											props.setStandOn(true);
											stand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
											stand.setMaster(player);
											if(!player.world.isRemote)
												JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new SSyncStandMasterPacket(stand.getEntityId(), player.getEntityId()));
											CompoundNBT nbt = new CompoundNBT();
											nbt.putInt("MasterID", player.getEntityId());
											stand.writeAdditional(nbt);
											player.world.addEntity(stand);
											stand.playSpawnSound();
										} else {
											MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(player, stand));
											stand.remove();
										}
									}
								} else if (props.getStandID() == Util.StandID.THE_EMPEROR) {
									ItemStack itemStack = new ItemStack(ItemInit.THE_EMPEROR.get());

									if (!player.inventory.hasItemStack(itemStack)) {
										if (player.inventory.getStackInSlot(player.inventory.getBestHotbarSlot()).isEmpty()) {
											player.inventory.currentItem = player.inventory.getBestHotbarSlot();
											player.inventory.add(player.inventory.getBestHotbarSlot(), itemStack);
											player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.SPAWN_THE_EMPEROR.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
											props.setStandOn(true);
										} else
											player.sendMessage(new StringTextComponent("Your hotbar is full!"));
									} else {
										itemStack.shrink(1);
										props.setStandOn(false);
									}
								}
							return;
						}
					}
				}
			});
		}
	}
}
