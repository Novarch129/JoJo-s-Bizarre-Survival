package com.novarch.jojomod.network.message;

import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.capabilities.stand.JojoProvider;

import java.util.function.Supplier;

import com.novarch.jojomod.util.ValueTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

@SuppressWarnings("unused")
public class SyncStandSummonButton {
	public int entityID;

	public SyncStandSummonButton() {
	}

	public SyncStandSummonButton(int entityId) {
		this.entityID = entityId;
	}

	public static void encode(SyncStandSummonButton msg, PacketBuffer buffer) {
		buffer.writeInt(msg.entityID);
	}

	public static SyncStandSummonButton decode(PacketBuffer buffer) {
		return new SyncStandSummonButton(buffer.readInt());
	}

	public static void handle(SyncStandSummonButton msg, Supplier<NetworkEvent.Context> supplier) {
		final NetworkEvent.Context ctx = supplier.get();
		if (ctx.getDirection().getReceptionSide().isServer()) {
			ctx.enqueueWork(() ->
			{
				ServerPlayerEntity sender = ctx.getSender();
				if (sender == null)
					return;
				summonPlayerStand(msg, supplier, sender);
			});
		}
		ctx.setPacketHandled(true);
	}

	public static void summonPlayerStand(SyncStandSummonButton message, Supplier<NetworkEvent.Context> ctx, ServerPlayerEntity player) {
		FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
		fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
		World world = player.world;
		if (!world.isRemote) {
			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				int delay = 0;
				for (int i = 0; i < 20; i++) {
					delay++;
					if (delay == 20) {
						delay = 0;
						if (!player.isCrouching()) {
							if (props.getStandOn()) {
								if (fakePlayer.isAlive())
									fakePlayer.remove();
								if(!props.hasAct())
									props.setStandOn(false);
								else
									props.changeAct();
								return;
							}
							SyncStandSummonButton.summonStand(player, fakePlayer);
							return;
						}
					}
				}
			});
		}
	}

	public static void summonStand(PlayerEntity player, FakePlayerEntity fakePlayer) {
		JojoProvider.getLazyOptional(player).ifPresent(props -> {
			if (props.getStandID() != 0 && props.getStandID() != JojoLibs.StandID.theEmperor) {
				EntityStandBase stand = JojoLibs.getStand(props.getStandID(), player.world);

				if (stand != null) {
					if (!player.world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(1000.0, 1000.0, 1000.0), entity -> entity instanceof EntityStandBase).contains(stand)) {
						if (props.getStandID() == JojoLibs.StandID.aerosmith && props.getAbility())
							player.world.addEntity(fakePlayer);
						props.setStandOn(true);
						stand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
						stand.setMaster(player);
						stand.setMasterUUID(player.getUniqueID());
						player.world.addEntity(stand);
						stand.spawnSound();
					} else {
						if (!stand.hasAct())
							stand.remove();
						else
							stand.changeAct();
					}
				}
			} else if(props.getStandID() == JojoLibs.StandID.theEmperor) {
				ItemStack itemStack = new ItemStack(ItemInit.the_emperor.get());

				if(!player.inventory.hasItemStack(itemStack)) {
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
		});
	}
}
