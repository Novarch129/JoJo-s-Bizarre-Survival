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
    private int[] velocities;

    public STimestopPacket() {}

    public STimestopPacket(int[] entity, int[] velocities)
    {
        this.entity = entity;
        this.velocities = velocities;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeVarIntArray(entity);
        buffer.writeVarIntArray(velocities);
    }

    public static STimestopPacket decode(PacketBuffer buffer)
    {
        STimestopPacket msg = new STimestopPacket();
        msg.entity = buffer.readVarIntArray();
        msg.velocities = buffer.readVarIntArray();
        return msg;
    }

    public static void handle(STimestopPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                for(int i = 0; i < msg.entity.length; i++) {
                    Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entity[i]);
                    double x = msg.velocities[i*3] / 100.0;
                    double y = msg.velocities[i*3 + 1] / 100.0;
                    double z = msg.velocities[i*3 + 2] / 100.0;
                    if(entity != null)
                        entity.setVelocity(x, y, z);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
