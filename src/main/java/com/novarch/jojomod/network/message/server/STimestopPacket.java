package com.novarch.jojomod.network.message.server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class STimestopPacket
{
    private UUID entity;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public STimestopPacket() {}

    public STimestopPacket(UUID entity, double velocityX, double velocityY, double velocityZ)
    {
        this.entity = entity;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUniqueId(entity);
        buffer.writeDouble(velocityX);
        buffer.writeDouble(velocityY);
        buffer.writeDouble(velocityZ);
    }

    public static STimestopPacket decode(PacketBuffer buffer)
    {
        STimestopPacket msg = new STimestopPacket();
        msg.entity = buffer.readUniqueId();
        msg.velocityX = buffer.readDouble();
        msg.velocityY = buffer.readDouble();
        msg.velocityZ = buffer.readDouble();
        return msg;
    }

    public static void handle(STimestopPacket msg, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Minecraft.getInstance().world.getAllEntities().forEach(entity1 -> {
                    if(entity1.getUniqueID() == msg.entity)
                        entity1.setVelocity(msg.velocityX, msg.velocityY, msg.velocityZ);
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
