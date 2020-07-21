package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CHierophantGreenPossessionPacket implements IMessage<CHierophantGreenPossessionPacket> {
    private Direction direction;

    public CHierophantGreenPossessionPacket() {
    }

    public CHierophantGreenPossessionPacket(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void encode(CHierophantGreenPossessionPacket msg, PacketBuffer buffer) {
        buffer.writeEnumValue(msg.direction);
    }

    @Override
    public CHierophantGreenPossessionPacket decode(PacketBuffer buffer) {
        return new CHierophantGreenPossessionPacket(buffer.readEnumValue(Direction.class));
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
                                    switch (msg.direction) {
                                        default:
                                            break;
                                        case JUMP: {
                                            possessedEntity.setMotion(
                                                    possessedEntity.getMotion().getX(),
                                                    (possessedEntity.getMotion().getY() + 2) * 10,
                                                    possessedEntity.getMotion().getZ()
                                            );
                                            break;
                                        }
                                        case CROUCH: {
                                            possessedEntity.setSneaking(true);
                                            break;
                                        }
                                        case FORWARDS: {
                                            possessedEntity.moveForward += 5;
                                            break;
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
