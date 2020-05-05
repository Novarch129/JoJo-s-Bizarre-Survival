package com.novarch.jojomod.network.message;

import com.novarch.jojomod.util.DimensionHopTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncDimensionHop
{
    public int type;

    public SyncDimensionHop(int type)
    {
        this.type = type;
    }

    public SyncDimensionHop() {}

    public static void encode(SyncDimensionHop msg, PacketBuffer buffer)
    {
        buffer.writeInt(msg.type);
    }

    public static SyncDimensionHop decode(PacketBuffer buffer)
    {
        return new SyncDimensionHop(buffer.readInt());
    }

    public static void handle(SyncDimensionHop msg, Supplier<NetworkEvent.Context> supplier)
    {
        final NetworkEvent.Context ctx = supplier.get();
        if(ctx.getDirection().getReceptionSide().isServer())
        {
            ctx.enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.getSender();
                if(sender == null)
                    return;
                dimensionHop(sender, msg.type);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void dimensionHop(PlayerEntity player, int type)
    {
        if(player != null)
        {
            player.changeDimension(DimensionType.getById(type), new DimensionHopTeleporter((ServerWorld) player.getEntityWorld(), player.getPosX(), player.getPosY(), player.getPosZ()));
        }
    }
}
