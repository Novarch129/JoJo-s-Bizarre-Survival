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

/**
 * Controls Aerosmith's actions through keybinds,
 *
 * @variable action	 Defines the action Aerosmith will perform, the following are the possible values:
 * 1 Movement
 * 2 Bomb
 * 3 Rotation
 * 4 Set to renderViewEntity
 * 5 Attack
 *
 * @variable direction	Defines the direction Aerosmith should go in if <code>action == Action#MOVEMENT</code>:
 * 1 Forwards
 * 2 Backwards
 * 3 Right
 * 4 Left
 * 5 Up
 * 6 Down
 *
 * @variable sprint  Defines whether or not Aerosmith should sprint(if <code>Minecraft#gameSetting#keyBindSprint#isKeyDown</code>).
 *
 * @variable yaw, pitch  Define Aerosmith's rotation relative to the mouse position(<code>Minecarft#mouseHelper#getMouseX/getMouseY</code>).
 */
@SuppressWarnings("ConstantConditions")
public class CSyncAerosmithPacket {
	public enum Action {MOVE, BOMB, ROTATE, RENDER, ATTACK};
	public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, UP, DOWN};
	private Action action;
	private Direction direction;
	private boolean sprint;
	private float yaw;
	private float pitch;

	public CSyncAerosmithPacket(Action action) {
		this.action = action;
		this.direction = Direction.FORWARDS;
		this.sprint = false;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(float yaw, float pitch) {
		this.action = Action.ROTATE;
		this.yaw = yaw;
		this.pitch = pitch;
		this.direction = Direction.FORWARDS;
		this.sprint = false;
	}

	public CSyncAerosmithPacket(Action action, Direction direction) {
		this.action = action;
		this.direction = direction;
		this.sprint = false;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(Action action, Direction direction, boolean sprint) {
		this.action = action;
		this.direction = direction;
		this.sprint = sprint;
		this.yaw = 0f;
		this.pitch = 0f;
	}

	public CSyncAerosmithPacket(Action action, Direction direction, boolean sprint, float yaw, float pitch) {
		this.action = action;
		this.direction = direction;
		this.sprint = sprint;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public static void encode(CSyncAerosmithPacket msg, PacketBuffer buffer) {
		buffer.writeEnumValue(msg.action);
		buffer.writeEnumValue(msg.direction);
		buffer.writeBoolean(msg.sprint);
		buffer.writeFloat(msg.yaw);
		buffer.writeFloat(msg.pitch);
	}

	public static CSyncAerosmithPacket decode(PacketBuffer buffer) {
		return new CSyncAerosmithPacket(
				buffer.readEnumValue(Action.class),
				buffer.readEnumValue(Direction.class),
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
										case MOVE: {
											switch (message.direction) {
												//Forwards
												case FORWARDS: {
													if (message.sprint) {
														entity.setVelocity(motionX, motionY, motionZ);
														entity.setSprinting(true);
													} else
														entity.setVelocity(motionX * 0.5, entity.getMotion().getY(), motionZ * 0.5);
													break;
												}
												//Backwards
												case BACKWARDS: {
													entity.setVelocity(-motionX * 0.6, entity.getMotion().getY(), -motionZ * 0.6);
													break;
												}
												//Right
												case RIGHT: {
													entity.setVelocity(-motionZ * 0.5, entity.getMotion().getY(), motionX * 0.5);
													break;
												}
												//Left
												case LEFT: {
													entity.setVelocity(motionZ * 0.5, entity.getMotion().getY(), -motionX * 0.5);
													break;
												}
												//Up
												case UP: {
													entity.addVelocity(0, 0.5, 0);
													break;
												}
												//Down
												case DOWN: {
													entity.addVelocity(0, -0.3, 0);
													break;
												}
												default:
													break;
											}
											break;
										}
										//Bomb
										case BOMB: {
											Stand.getLazyOptional(player).ifPresent(props -> {
												if (props.getCooldown() <= 0) {
													TNTEntity tnt = new TNTEntity(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), player);
													tnt.setVelocity(entity.getLookVec().getX(), entity.getLookVec().getY(), entity.getLookVec().getZ());
													if(!entity.world.isRemote)
														entity.world.addEntity(tnt);
													props.setCooldown(200);
												}
											});
											break;
										}
										//Rotation
										case ROTATE: {
											((EntityAerosmith) entity).yaw = message.yaw;
											((EntityAerosmith) entity).pitch = message.pitch;
											break;
										}
										//Set RenderViewEntity
										case RENDER: {
											if(message.direction == Direction.FORWARDS) {
												Minecraft.getInstance().setRenderViewEntity(entity);
												Minecraft.getInstance().gameSettings.thirdPersonView = 1;
											} else {
												Minecraft.getInstance().setRenderViewEntity(player);
												Minecraft.getInstance().gameSettings.thirdPersonView = 0;
											}
											break;
										}
										//Attack
										case ATTACK: {
											if(message.direction == Direction.FORWARDS)
												((EntityAerosmith) entity).swingProgressInt = 1;
											else
												((EntityAerosmith) entity).swingProgressInt = 0;
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
