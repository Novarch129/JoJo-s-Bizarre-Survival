package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.capabilities.timestop.Timestop;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class STimestopPacket
{
    private int[] entity;

    public STimestopPacket() {}

    public STimestopPacket(int[] entity)
    {
        this.entity = entity;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeVarIntArray(entity);
    }

    public static STimestopPacket decode(PacketBuffer buffer)
    {
        STimestopPacket msg = new STimestopPacket();
        msg.entity = buffer.readVarIntArray();
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
                        Timestop.getLazyOptional(entity).ifPresent(props -> entity.setVelocity(props.getMotionX(), props.getMotionY(), props.getMotionZ()));
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
