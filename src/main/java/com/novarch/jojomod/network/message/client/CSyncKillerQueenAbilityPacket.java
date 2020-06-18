package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

@SuppressWarnings( "ConstantConditions")
public class CSyncKillerQueenAbilityPacket {
	private int action;

	public CSyncKillerQueenAbilityPacket(int action) {
		this.action = action;
	}

	public static void encode(CSyncKillerQueenAbilityPacket msg, PacketBuffer buffer) {
		buffer.writeInt(msg.action);
	}

	public static CSyncKillerQueenAbilityPacket decode(PacketBuffer buffer) {
		return new CSyncKillerQueenAbilityPacket(buffer.readInt());
	}

	public static void handle(CSyncKillerQueenAbilityPacket message, Supplier<Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				World world = player.world;
				if (world != null)
					if (!world.isRemote) {
						world.getServer().getWorld(player.dimension).getEntities()
								.filter(entity -> entity instanceof EntityKillerQueen)
								.filter(entity -> ((EntityKillerQueen) entity).getMaster().getEntityId() == player.getEntityId())
								.forEach(entity -> {
									if(message.action == 1)
										((EntityKillerQueen) entity).detonate();
									else
										((EntityKillerQueen) entity).toggleSheerHeartAttack();
								});
					}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
