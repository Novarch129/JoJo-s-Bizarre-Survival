package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
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
 * @variable action     Defines the action Aerosmith will perform, the following are the possible values:
 * 1 Movement
 * 2 Rotation
 * 3 Set <code>Minecraft#renderViewEntity</code>
 * @variable direction    Defines the direction Aerosmith should go in if <code>action == Action#MOVEMENT</code>:
 * 1 Forwards
 * 2 Backwards
 * 3 Right
 * 4 Left
 * 5 Up
 * 6 Down
 * @variable sprint  Defines whether or not Aerosmith should sprint(if <code>Minecraft#gameSetting#keyBindSprint#isKeyDown</code>).
 * @variable yaw, pitch  Define Aerosmith's rotation relative to the mouse position(<code>Minecraft#mouseHelper#getMouseX/getMouseY</code>).
 */
@SuppressWarnings("ConstantConditions")
public class CAerosmithControlPacket implements IMessage<CAerosmithControlPacket> {
    private Action action;
    private Direction direction;
    private boolean sprint;
    private float yaw;
    private float pitch;

    public CAerosmithControlPacket(Action action) {
        this.action = action;
        this.direction = Direction.FORWARDS;
        this.sprint = false;
        this.yaw = 0f;
        this.pitch = 0f;
    }

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
        this.yaw = 0f;
        this.pitch = 0f;
    }

    public CAerosmithControlPacket(Action action, Direction direction, boolean sprint) {
        this.action = action;
        this.direction = direction;
        this.sprint = sprint;
        this.yaw = 0f;
        this.pitch = 0f;
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
                                        //Movement
                                        case MOVE: {
                                            switch (msg.direction) {
                                                //Forwards
                                                case FORWARDS: {
                                                    if (msg.sprint) {
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
                                        //Rotation
                                        case ROTATE: {
                                            ((AerosmithEntity) entity).yaw = msg.yaw;
                                            ((AerosmithEntity) entity).pitch = msg.pitch;
                                            break;
                                        }
                                        //Set RenderViewEntity
                                        case RENDER: {
                                            if (msg.direction == Direction.FORWARDS) {
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

    public enum Action {MOVE, ROTATE, RENDER}

    public enum Direction {FORWARDS, BACKWARDS, RIGHT, LEFT, UP, DOWN}
}
