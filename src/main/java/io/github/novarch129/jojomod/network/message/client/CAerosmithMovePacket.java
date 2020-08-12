package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

/**
 * Controls Aerosmith's actions through keybinds,
 */
@SuppressWarnings("ConstantConditions")
public class CAerosmithMovePacket implements IMessage<CAerosmithMovePacket> {
    private Direction direction;
    private boolean sprint;
    private float yaw, pitch;

    public CAerosmithMovePacket(Direction direction) {
        this.direction = direction;
        this.sprint = false;
        this.yaw = 0;
        this.pitch = 0;
    }

    public CAerosmithMovePacket(Direction direction, boolean sprint) {
        this.direction = direction;
        this.sprint = sprint;
        this.yaw = 0;
        this.pitch = 0;
    }

    public CAerosmithMovePacket(Direction direction, boolean sprint, float yaw, float pitch) {
        this.direction = direction;
        this.sprint = sprint;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public CAerosmithMovePacket() {
    }

    @Override
    public void handle(CAerosmithMovePacket message, Supplier<Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                PlayerEntity sender = ctx.get().getSender();
                assert sender != null;
                World world = sender.world;
                if (world != null)
                    if (!world.isRemote) {
                        world.getServer().getWorld(sender.dimension).getEntities()
                                .filter(entity -> entity instanceof AerosmithEntity)
                                .filter(entity -> ((AerosmithEntity) entity).getMaster().equals(sender))
                                .forEach(entity -> {
                                    Vec3d motion = Util.getEntityForwardsMotion(entity);
                                    switch (message.direction) {
                                        case FORWARDS: {
                                            if (message.sprint) {
                                                entity.setVelocity(motion.getX(), motion.getY(), motion.getZ());
                                                entity.setSprinting(true);
                                            } else
                                                entity.setVelocity(motion.getX() * 0.5, entity.getMotion().getY(), motion.getZ() * 0.5);
                                            break;
                                        }
                                        case BACKWARDS: {
                                            entity.setVelocity(-motion.getX() * 0.6, entity.getMotion().getY(), -motion.getZ() * 0.6);
                                            break;
                                        }
                                        case RIGHT: {
                                            entity.setVelocity(-motion.getZ() * 0.5, entity.getMotion().getY(), motion.getX() * 0.5);
                                            break;
                                        }
                                        case LEFT: {
                                            entity.setVelocity(motion.getZ() * 0.5, entity.getMotion().getY(), -motion.getX() * 0.5);
                                            break;
                                        }
                                        case UP: {
                                            entity.addVelocity(0, 0.5, 0);
                                            break;
                                        }
                                        case DOWN: {
                                            entity.addVelocity(0, -0.3, 0);
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

    @Override
    public void encode(CAerosmithMovePacket message, PacketBuffer buffer) {
        buffer.writeEnumValue(message.direction);
        buffer.writeBoolean(message.sprint);
        buffer.writeFloat(message.yaw);
        buffer.writeFloat(message.pitch);
    }

    @Override
    public CAerosmithMovePacket decode(PacketBuffer buffer) {
        return new CAerosmithMovePacket(
                buffer.readEnumValue(Direction.class),
                buffer.readBoolean(),
                buffer.readFloat(),
                buffer.readFloat()
        );
    }

    public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, UP, DOWN}
}
