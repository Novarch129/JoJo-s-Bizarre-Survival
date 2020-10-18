package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CAerosmithRotationPacket implements IMessage<CAerosmithRotationPacket> {
    private int entityID;
    private float yaw, pitch, yawHead;

    public CAerosmithRotationPacket(int entityID, float yaw, float pitch, float yawHead) {
        this.entityID = entityID;
        this.yaw = yaw;
        this.pitch = pitch;
        this.yawHead = yawHead;
    }

    public CAerosmithRotationPacket() {
    }

    @Override
    public void encode(CAerosmithRotationPacket message, PacketBuffer buffer) {
        buffer.writeInt(message.entityID);
        buffer.writeFloat(message.yaw);
        buffer.writeFloat(message.pitch);
        buffer.writeFloat(message.yawHead);
    }

    @Override
    public CAerosmithRotationPacket decode(PacketBuffer buffer) {
        return new CAerosmithRotationPacket(buffer.readInt(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    @Override
    public void handle(CAerosmithRotationPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                Entity entity = sender.world.getEntityByID(message.entityID);
                if (entity instanceof AerosmithEntity) {
                    entity.rotationYaw = message.yaw;
                    entity.rotationPitch = message.pitch;
                    ((AerosmithEntity) entity).rotationYawHead = message.yawHead;
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
