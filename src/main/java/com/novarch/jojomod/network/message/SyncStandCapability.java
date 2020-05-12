package com.novarch.jojomod.network.message;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncStandCapability
{
    private INBT data;

    public SyncStandCapability() {}

    public SyncStandCapability(IStand props)
    {
        this.data = new CompoundNBT();
        this.data = JojoProvider.STAND.getStorage().writeNBT(JojoProvider.STAND, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeCompoundTag((CompoundNBT) data);
    }

    public static SyncStandCapability decode(PacketBuffer buffer)
    {
        SyncStandCapability msg = new SyncStandCapability();
        msg.data = buffer.readCompoundTag();
        return msg;
    }

    public static void handle(SyncStandCapability message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
                assert player != null;
                player.sendMessage(new StringTextComponent("Running!"));
                IStand props = JojoProvider.get(player);
                assert props != null;
                JojoProvider.STAND.getStorage().readNBT(JojoProvider.STAND, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
