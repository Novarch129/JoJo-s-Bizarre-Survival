package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.entity.stand.StickyFingersEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStickyFingersDisguisePacket implements IMessage<SSyncStickyFingersDisguisePacket> {
    private int standID;
    private int entityID;

    public SSyncStickyFingersDisguisePacket() {
    }

    public SSyncStickyFingersDisguisePacket(int standID, int entityID) {
        this.standID = standID;
        this.entityID = entityID;
    }

    @Override
    public void encode(SSyncStickyFingersDisguisePacket message, PacketBuffer buffer) {
        buffer.writeInt(message.standID);
        buffer.writeInt(message.entityID);
    }

    @Override
    public SSyncStickyFingersDisguisePacket decode(PacketBuffer buffer) {
        return new SSyncStickyFingersDisguisePacket(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void handle(SSyncStickyFingersDisguisePacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                ClientWorld world = Minecraft.getInstance().world;
                if (world == null) return;
                Entity stand = world.getEntityByID(message.standID);
                if (message.entityID != 100) {
                    Entity entity = world.getEntityByID(message.entityID);
                    if (stand instanceof StickyFingersEntity && entity instanceof LivingEntity)
                        ((StickyFingersEntity) stand).disguiseEntity = (LivingEntity) entity;
                } else if (stand instanceof StickyFingersEntity)
                    ((StickyFingersEntity) stand).disguiseEntity = null;
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
