package com.novarch.jojomod.network.message;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.JojoProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class RequestSyncStandCapability
{
    private int entityId = 0;
    private INBT data;

    public RequestSyncStandCapability() {}

    public void encode(PacketBuffer buffer) {}

    public static RequestSyncStandCapability decode(PacketBuffer buffer)
    {
        RequestSyncStandCapability msg = new RequestSyncStandCapability();
        return msg;
    }

    public static void handle(RequestSyncStandCapability message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                assert player != null;
                IStand props = JojoProvider.get(player);
                assert props != null;
                StevesBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SyncStandCapability(props));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
