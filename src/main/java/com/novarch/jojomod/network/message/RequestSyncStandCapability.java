package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestSyncStandCapability
{
    private INBT data;

    public RequestSyncStandCapability() {}

    public RequestSyncStandCapability(IStand props)
    {
        this.data = new CompoundNBT();
        this.data = JojoProvider.STAND.getStorage().writeNBT(JojoProvider.STAND, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeCompoundTag((CompoundNBT) data);
    }

    public static RequestSyncStandCapability decode(PacketBuffer buffer)
    {
        RequestSyncStandCapability msg = new RequestSyncStandCapability();
        msg.data = buffer.readCompoundTag();
        return msg;
    }

    public static void handle(RequestSyncStandCapability message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IStand props = JojoProvider.get(player);

                JojoProvider.STAND.getStorage().readNBT(JojoProvider.STAND, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
