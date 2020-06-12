package com.novarch.jojomod.network.message.server;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class STimestopPacket
{
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public STimestopPacket() {}

    public STimestopPacket(double velocityX, double velocityY, double velocityZ)
    {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeDouble(velocityX);
        buffer.writeDouble(velocityY);
        buffer.writeDouble(velocityZ);
    }

    public static STimestopPacket decode(PacketBuffer buffer)
    {
        STimestopPacket msg = new STimestopPacket();
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
                    Minecraft.getInstance().world.getAllEntities().forEach(entity -> {
                        entity.setVelocity(msg.velocityX, msg.velocityY, msg.velocityZ);
                    }));
        }
        ctx.get().setPacketHandled(true);
    }
}
