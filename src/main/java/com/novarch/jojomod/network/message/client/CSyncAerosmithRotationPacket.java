package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

@SuppressWarnings("ALL")
public class CSyncAerosmithRotationPacket {
	private double yaw;
	private double pitch;

	public CSyncAerosmithRotationPacket(double yaw, double pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public static void encode(CSyncAerosmithRotationPacket msg, PacketBuffer buffer) {
		buffer.writeDouble(msg.yaw);
		buffer.writeDouble(msg.pitch);
	}

	public static CSyncAerosmithRotationPacket decode(PacketBuffer buffer) {
		return new CSyncAerosmithRotationPacket(buffer.readDouble(), buffer.readDouble());
	}

	public static void handle(CSyncAerosmithRotationPacket message, Supplier<Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				World world = player.world;
				if (world != null)
					if (!world.isRemote) {
						world.getServer().getWorld(player.dimension).getEntities()
								.filter(entity -> entity instanceof EntityAerosmith)
								.forEach(entity -> {
									if(player.getEntityId() == ((EntityAerosmith) entity).getMaster().getEntityId()) {
										entity.rotationYaw = (float) message.yaw;
										entity.rotationPitch = (float) message.pitch;
									}
								});
					}
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
