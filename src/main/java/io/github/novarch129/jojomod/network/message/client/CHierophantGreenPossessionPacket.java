package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CHierophantGreenPossessionPacket implements IMessage<CHierophantGreenPossessionPacket> {
    private Direction direction;
    private byte action;
    private float yaw, pitch;

    public CHierophantGreenPossessionPacket() {
    }

    public CHierophantGreenPossessionPacket(Direction direction, byte action, float yaw, float pitch) {
        this.direction = direction;
        this.action = action;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public CHierophantGreenPossessionPacket(byte action) {
        this.action = action;
        direction = Direction.FORWARDS;
        yaw = 0;
        pitch = 0;
    }

    public CHierophantGreenPossessionPacket(Direction direction) {
        this.direction = direction;
        action = 0;
        yaw = 0;
        pitch = 0;
    }

    public CHierophantGreenPossessionPacket(float yaw, float pitch) {
        direction = Direction.FORWARDS;
        action = 1;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void encode(CHierophantGreenPossessionPacket msg, PacketBuffer buffer) {
        buffer.writeEnumValue(msg.direction);
        buffer.writeByte(msg.action);
        buffer.writeFloat(msg.yaw);
        buffer.writeFloat(msg.pitch);
    }

    @Override
    public CHierophantGreenPossessionPacket decode(PacketBuffer buffer) {
        return new CHierophantGreenPossessionPacket(
                buffer.readEnumValue(Direction.class),
                buffer.readByte(),
                buffer.readFloat(),
                buffer.readFloat()
        );
    }

    @Override
    public void handle(CHierophantGreenPossessionPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                if (!sender.world.isRemote)
                    sender.getServerWorld().getEntities()
                            .filter(entity -> entity instanceof HierophantGreenEntity)
                            .filter(entity -> ((HierophantGreenEntity) entity).getMaster().getEntityId() == sender.getEntityId())
                            .forEach(entity -> {
                                if (((HierophantGreenEntity) entity).possessedEntity != null) {
                                    LivingEntity possessedEntity = ((HierophantGreenEntity) entity).possessedEntity;
                                    switch (msg.action) {
                                        default:
                                            break;
                                        case 0: {
                                            double motionX = (-MathHelper.sin(possessedEntity.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(possessedEntity.rotationPitch / 180 * (float) Math.PI));
                                            double motionZ = (MathHelper.cos(possessedEntity.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(possessedEntity.rotationPitch / 180 * (float) Math.PI));
                                            switch (msg.direction) {
                                                default:
                                                    break;
                                                case JUMP: {
                                                    if (possessedEntity.onGround)
                                                        possessedEntity.setMotion(
                                                                possessedEntity.getMotion().getX(),
                                                                0.4,
                                                                possessedEntity.getMotion().getZ()
                                                        );
                                                    break;
                                                }
                                                case CROUCH: {
                                                    possessedEntity.setSneaking(!possessedEntity.isSneaking());
                                                    break;
                                                }
                                                case FORWARDS: {
                                                    possessedEntity.setMotion(
                                                            motionX / 2.3,
                                                            entity.getMotion().getY(),
                                                            motionZ / 2.3
                                                    );
                                                    break;
                                                }
                                                case BACKWARDS: {
                                                    possessedEntity.setMotion(
                                                            -motionX / 2.3,
                                                            entity.getMotion().getY(),
                                                            -motionZ / 2.3
                                                    );
                                                    break;
                                                }
                                                case RIGHT: {
                                                    possessedEntity.setMotion(
                                                            -motionZ / 2.3,
                                                            entity.getMotion().getY(),
                                                            motionX / 2.3

                                                    );
                                                    break;
                                                }
                                                case LEFT: {
                                                    possessedEntity.setMotion(
                                                            motionZ / 2.3,
                                                            entity.getMotion().getY(),
                                                            -motionX / 2.3

                                                    );
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 1: {
                                            ((HierophantGreenEntity) entity).yaw = msg.yaw;
                                            ((HierophantGreenEntity) entity).pitch = msg.pitch;
                                            break;
                                        }
                                        case 2: {
                                            Minecraft.getInstance().setRenderViewEntity(possessedEntity);
                                        }
                                    }
                                }
                            });
            });
        }
        ctx.get().setPacketHandled(true);
    }

    public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, JUMP, CROUCH}
}
