package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.Stand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandCapabilityPacket
{
    private INBT data;

    private SSyncStandCapabilityPacket() {}

    public SSyncStandCapabilityPacket(IStand props)
    {
        this.data = new CompoundNBT();
        this.data = Stand.STAND.getStorage().writeNBT(Stand.STAND, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeCompoundTag((CompoundNBT) data);
    }

    public static SSyncStandCapabilityPacket decode(PacketBuffer buffer)
    {
        SSyncStandCapabilityPacket msg = new SSyncStandCapabilityPacket();
        msg.data = buffer.readCompoundTag();
        return msg;
    }

    public static void handle(SSyncStandCapabilityPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
                assert player != null;
                Stand.getLazyOptional(player).ifPresent(props ->
                        Stand.STAND.getStorage().readNBT(Stand.STAND, props, null, message.data));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
