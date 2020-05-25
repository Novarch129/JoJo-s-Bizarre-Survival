package com.novarch.jojomod.network.message;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAbility2Button
{
    public boolean ability;

    public SyncAbility2Button() {}

    public SyncAbility2Button(boolean value) { this.ability = value; }

    public static void encode(SyncAbility2Button msg, PacketBuffer buffer)
    {
        buffer.writeBoolean(msg.ability);
    }

    public static SyncAbility2Button decode(PacketBuffer buffer)
    {
        return new SyncAbility2Button(buffer.readBoolean());
    }

    public static void handle(SyncAbility2Button msg, Supplier<NetworkEvent.Context> supplier)
    {
        final NetworkEvent.Context ctx = supplier.get();
        if(ctx.getDirection().getReceptionSide().isServer())
        {
            ctx.enqueueWork(() -> {});
        }
        ctx.setPacketHandled(true);
    }
}
