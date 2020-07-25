package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Used to controls Hierophant Green's victims through keybinds, allows 4 directional movement, jumping and crouching.
 *
 * @author Novarch129
 * @since 12:58 AM, 7/25/2020.
 */
public class CHierophantGreenPossessionPacket implements IMessage<CHierophantGreenPossessionPacket> {
    private Direction direction;
    private byte action; //2 enums would be too much, using a byte is probably more efficient.
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
                                            Vec3d motion = Util.getEntityForwardsMotion(possessedEntity);
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
                                                            motion.getX() / 2.5,
                                                            entity.getMotion().getY(),
                                                            motion.getZ() / 2.5
                                                    );
                                                    break;
                                                }
                                                case BACKWARDS: {
                                                    possessedEntity.setMotion(
                                                            -motion.getX() / 2.5,
                                                            entity.getMotion().getY(),
                                                            -motion.getZ() / 2.5
                                                    );
                                                    break;
                                                }
                                                case RIGHT: {
                                                    possessedEntity.setMotion(
                                                            -motion.getZ() / 2.5,
                                                            entity.getMotion().getY(),
                                                            motion.getX() / 2.5

                                                    );
                                                    break;
                                                }
                                                case LEFT: {
                                                    possessedEntity.setMotion(
                                                            motion.getZ() / 2.5,
                                                            entity.getMotion().getY(),
                                                            -motion.getX() / 2.5

                                                    );
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 1: { //Mob will always fight back a bit when you're doing this.
                                            ((HierophantGreenEntity) entity).yaw = msg.yaw;
                                            ((HierophantGreenEntity) entity).pitch = msg.pitch;
                                            break;
                                        }
                                        case 2: { //Called here so I don't have to sync HierophantGreenEntity#possesedEntity to the Client.
                                            Minecraft.getInstance().setRenderViewEntity(possessedEntity);
                                            break;
                                        }
                                    }
                                }
                            });
            });
        }
        ctx.get().setPacketHandled(true);
    }

    public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, JUMP, CROUCH} //Improves readability, could be replaced with ints, like most enums.
}
