package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class CSyncAerosmithPacket {
	private int action;
	private int direction;
	private boolean sprint;
	private float yaw;
	private float pitch;

	public CSyncAerosmithPacket(int action) {
		this.action = action;
		this.direction = 0;
		this.sprint = false;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(float yaw, float pitch) {
		this.action = 3;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = 0;
		this.sprint = false;
	}

	public CSyncAerosmithPacket(int action, int direction) {
		this.action = action;
		this.direction = direction;
		this.sprint = false;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(int action, int direction, boolean sprint) {
		this.action = action;
		this.direction = direction;
		this.sprint = sprint;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(int action, int direction, boolean sprint, float yaw, float pitch) {
		this.action = action;
		this.direction = direction;
		this.sprint = sprint;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public static void encode(CSyncAerosmithPacket msg, PacketBuffer buffer) {
		buffer.writeInt(msg.action);
		buffer.writeInt(msg.direction);
		buffer.writeBoolean(msg.sprint);
		buffer.writeFloat(msg.yaw);
		buffer.writeFloat(msg.pitch);
	}

	public static CSyncAerosmithPacket decode(PacketBuffer buffer) {
		return new CSyncAerosmithPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readBoolean(),
				buffer.readFloat(),
				buffer.readFloat()
		);
	}

	public static void handle(CSyncAerosmithPacket message, Supplier<Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				World world = player.world;
				if (world != null)
					if (!world.isRemote) {
						world.getServer().getWorld(player.dimension).getEntities()
								.filter(entity -> entity instanceof EntityAerosmith)
								.filter(entity -> ((EntityAerosmith) entity).getMaster().getEntityId() == player.getEntityId())
								.forEach(entity -> {
									float yaw = entity.rotationYaw;
									float pitch = entity.rotationPitch;
									double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
									double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
									double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * 1.0f);
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
													break;
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
													entity.addVelocity(0, 0.5, 0);
													break;
												}
												//Down
												case 6: {
													((EntityAerosmith) entity).shouldFall = true;
													entity.addVelocity(0, -0.3, 0);
													break;
												}
												default:
													break;
											}
											break;
										}
										//Bomb
										case 2: {
											Stand.getLazyOptional(player).ifPresent(props -> {
												if (props.getCooldown() <= 0) {
													TNTEntity tnt = new TNTEntity(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), player);
													tnt.setVelocity(entity.getLookVec().getX(), entity.getLookVec().getY(), entity.getLookVec().getZ());
													entity.world.addEntity(tnt);
													props.setCooldown(200);
												}
											});
											break;
										}
										//Rotation
										case 3: {
											entity.rotationYaw = message.yaw;
											entity.rotationPitch = message.pitch;
											entity.setRotationYawHead(message.yaw);
											break;
										}
										//Set RenderViewEntity
										case 4: {
											if(message.direction == 0) {
												Minecraft.getInstance().setRenderViewEntity(entity);
												Minecraft.getInstance().gameSettings.thirdPersonView = 1;
											} else {
												Minecraft.getInstance().setRenderViewEntity(player);
												Minecraft.getInstance().gameSettings.thirdPersonView = 0;
											}
											break;
										}
										default:
											break;
									}
								});
					}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
