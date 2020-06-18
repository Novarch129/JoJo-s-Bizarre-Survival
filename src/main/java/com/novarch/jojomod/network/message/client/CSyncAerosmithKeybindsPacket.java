package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import org.apache.logging.log4j.LogManager;

import java.util.function.Supplier;

@SuppressWarnings({"unused", "ConstantConditions"})
public class CSyncAerosmithKeybindsPacket {
	private int action;
	private int direction;
	private boolean sprint;
	private double yaw;
	private double pitch;


	public CSyncAerosmithKeybindsPacket(int action, int direction, boolean sprint, double yaw, double pitch) {
		this.action = action;
		this.direction = direction;
		this.sprint = sprint;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public static void encode(CSyncAerosmithKeybindsPacket msg, PacketBuffer buffer) {
		buffer.writeInt(msg.action);
		buffer.writeInt(msg.direction);
		buffer.writeBoolean(msg.sprint);
		buffer.writeDouble(msg.yaw);
		buffer.writeDouble(msg.pitch);
	}

	public static CSyncAerosmithKeybindsPacket decode(PacketBuffer buffer) {
		return new CSyncAerosmithKeybindsPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readBoolean(),
				buffer.readDouble(),
				buffer.readDouble()
		);
	}

	public static void handle(CSyncAerosmithKeybindsPacket message, Supplier<Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				World world = player.world;
				if (world != null)
					if (!world.isRemote) {
						LogManager.getLogger().debug("server world");
						world.getServer().getWorld(player.dimension).getEntities()
								.filter(entity -> entity instanceof EntityAerosmith)
								.forEach(entity -> {
									float yaw = (float) message.yaw;
									float pitch = (float) message.pitch;

									if (pitch > 89.0f)
										pitch = 89.0f;
									else if (pitch < -89.0f)
										pitch = -89.0f;

									entity.rotationYaw = yaw;
									entity.rotationPitch = pitch;

									float f = 1.0f;
									double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
									double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
									double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * f);
									switch (message.action) {
										//Movement
										case 1: {
											switch (message.direction) {
												//Forwards
												case 1: {
													if (message.sprint)
														entity.setVelocity(motionX, motionY, motionZ);
													else
														entity.setVelocity(motionX * 0.5, entity.getMotion().getY(), motionZ * 0.5);
												}
												//Backwards
												case 2: {
													entity.setVelocity(-motionX * 0.6, entity.getMotion().getY(), -motionZ * 0.6);
													break;
												}
												//Right
												case 3: {
													entity.setVelocity(-motionZ * 0.5, entity.getMotion().getY(), motionX * 0.5);
													break;
												}
												//Left
												case 4: {
													entity.setVelocity(motionZ * 0.5, entity.getMotion().getY(), -motionX * 0.5);
													break;
												}
												//Up
												case 5: {
													entity.addVelocity(0, 0.35, 0);
												}
												//Down
												case 6: {
													((EntityAerosmith) entity).shouldFall = true;
													entity.addVelocity(0, -0.2, 0);
												}
												default:
													break;
											}
										}
										//Bomb
										case 2: {
											Stand.getLazyOptional(player).ifPresent(props -> {
												if (props.getAbility()) {
													if (props.getCooldown() > 0)
														props.subtractCooldown(1);
												}
												if (props.getCooldown() <= 0) {
													if (KeyHandler.keys[2].isPressed()) {
														TNTEntity tnt = new TNTEntity(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), player);
														tnt.setVelocity(entity.getLookVec().getX(), entity.getLookVec().getY(), entity.getLookVec().getZ());
														entity.world.addEntity(tnt);
														props.setCooldown(200);
													}
												}
											});
										}
									}
								});
					}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
