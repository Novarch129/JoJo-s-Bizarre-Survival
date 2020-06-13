package com.novarch.jojomod.network.message.server;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class STimestopPacket
{
    private int[] entity;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public STimestopPacket() {}

    public STimestopPacket(int[] entity, double velocityX, double velocityY, double velocityZ)
    {
        this.entity = entity;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeVarIntArray(entity);
        buffer.writeDouble(velocityX);
        buffer.writeDouble(velocityY);
        buffer.writeDouble(velocityZ);
    }

    public static STimestopPacket decode(PacketBuffer buffer)
    {
        STimestopPacket msg = new STimestopPacket();
        msg.entity = buffer.readVarIntArray();
        msg.velocityX = buffer.readDouble();
        msg.velocityY = buffer.readDouble();
        msg.velocityZ = buffer.readDouble();
        return msg;
    }

    public static void handle(STimestopPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                for (int id : msg.entity) {
                    Entity entity = Minecraft.getInstance().world.getEntityByID(id);
                    if (entity != null)
                        entity.setVelocity(msg.velocityX, msg.velocityY, msg.velocityZ);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
