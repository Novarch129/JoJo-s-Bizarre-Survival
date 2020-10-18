package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncHierophantGreenPacket implements IMessage<SSyncHierophantGreenPacket> {
    private int standID;
    private int entityID;

    public SSyncHierophantGreenPacket() {
    }

    public SSyncHierophantGreenPacket(int standID, int entityID) {
        this.standID = standID;
        this.entityID = entityID;
    }

    @Override
    public void encode(SSyncHierophantGreenPacket message, PacketBuffer buffer) {
        buffer.writeInt(message.standID);
        buffer.writeInt(message.entityID);
    }

    @Override
    public SSyncHierophantGreenPacket decode(PacketBuffer buffer) {
        return new SSyncHierophantGreenPacket(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void handle(SSyncHierophantGreenPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                ClientWorld world = Minecraft.getInstance().world;
                if (world == null) return;
                Entity stand = world.getEntityByID(message.standID);
                if (message.entityID != -100) {
                    Entity entity = world.getEntityByID(message.entityID);
                    if (stand instanceof HierophantGreenEntity && entity instanceof LivingEntity)
                        ((HierophantGreenEntity) stand).possessedEntity = (LivingEntity) entity;
                } else if (stand instanceof HierophantGreenEntity)
                    ((HierophantGreenEntity) stand).possessedEntity = null;
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
