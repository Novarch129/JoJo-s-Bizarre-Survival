package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

/**
 * Controls Aerosmith's actions through keybinds,
 */
@SuppressWarnings("ConstantConditions")
public class CAerosmithControlPacket implements IMessage<CAerosmithControlPacket> {
    private Action action;
    private Direction direction;
    private boolean sprint;
    private float yaw, pitch;

    public CAerosmithControlPacket(float yaw, float pitch) {
        this.action = Action.ROTATE;
        this.yaw = yaw;
        this.pitch = pitch;
        this.direction = Direction.FORWARDS;
        this.sprint = false;
    }

    public CAerosmithControlPacket(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
        this.sprint = false;
        this.yaw = 0;
        this.pitch = 0;
    }

    public CAerosmithControlPacket(Action action, Direction direction, boolean sprint) {
        this.action = action;
        this.direction = direction;
        this.sprint = sprint;
        this.yaw = 0;
        this.pitch = 0;
    }

    public CAerosmithControlPacket(Action action, Direction direction, boolean sprint, float yaw, float pitch) {
        this.action = action;
        this.direction = direction;
        this.sprint = sprint;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public CAerosmithControlPacket() {
    }

    @Override
    public void handle(CAerosmithControlPacket msg, Supplier<Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                PlayerEntity player = ctx.get().getSender();
                assert player != null;
                World world = player.world;
                if (world != null)
                    if (!world.isRemote) {
                        world.getServer().getWorld(player.dimension).getEntities()
                                .filter(entity -> entity instanceof AerosmithEntity)
                                .filter(entity -> ((AerosmithEntity) entity).getMaster().getEntityId() == player.getEntityId())
                                .forEach(entity -> {
                                    float yaw = entity.rotationYaw;
                                    float pitch = entity.rotationPitch;
                                    double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
                                    double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
                                    double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * 1.0f);
                                    switch (msg.action) {
                                        case MOVE: {
                                            switch (msg.direction) {
                                                case FORWARDS: {
                                                    if (msg.sprint) {
                                                        entity.setVelocity(motionX, motionY, motionZ);
                                                        entity.setSprinting(true);
                                                    } else
                                                        entity.setVelocity(motionX * 0.5, entity.getMotion().getY(), motionZ * 0.5);
                                                    break;
                                                }
                                                case BACKWARDS: {
                                                    entity.setVelocity(-motionX * 0.6, entity.getMotion().getY(), -motionZ * 0.6);
                                                    break;
                                                }
                                                case RIGHT: {
                                                    entity.setVelocity(-motionZ * 0.5, entity.getMotion().getY(), motionX * 0.5);
                                                    break;
                                                }
                                                case LEFT: {
                                                    entity.setVelocity(motionZ * 0.5, entity.getMotion().getY(), -motionX * 0.5);
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
                                            break;
                                        }
                                        case ROTATE: {
                                            ((AerosmithEntity) entity).yaw = msg.yaw;
                                            ((AerosmithEntity) entity).pitch = msg.pitch;
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
    public void encode(CAerosmithControlPacket msg, PacketBuffer buffer) {
        buffer.writeEnumValue(msg.action);
        buffer.writeEnumValue(msg.direction);
        buffer.writeBoolean(msg.sprint);
        buffer.writeFloat(msg.yaw);
        buffer.writeFloat(msg.pitch);
    }

    @Override
    public CAerosmithControlPacket decode(PacketBuffer buffer) {
        return new CAerosmithControlPacket(
                buffer.readEnumValue(Action.class),
                buffer.readEnumValue(Direction.class),
                buffer.readBoolean(),
                buffer.readFloat(),
                buffer.readFloat()
        );
    }

    public enum Action {MOVE, ROTATE}

    public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, UP, DOWN}
}
