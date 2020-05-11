package com.novarch.jojomod.network.message;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.capabilities.stand.StandCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
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

    public SyncStandCapability(INBT data)
    {
        this.data = data;
    }

    public static void encode(SyncStandCapability msg, PacketBuffer buffer)
    {
        buffer.writeCompoundTag((CompoundNBT) msg.data);
    }

    public static SyncStandCapability decode(PacketBuffer buffer)
    {
        return new SyncStandCapability(buffer.readCompoundTag());
    }

    public static void handle(SyncStandCapability message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                final Optional<PlayerEntity> optionalPlayer = Optional.ofNullable(ctx.get().getSender());
                optionalPlayer.ifPresent(player -> {
                    player.getCapability(JojoProvider.STAND).ifPresent(props -> {
                        if(!(props instanceof StandCapability))
                            return;

                        JojoProvider.STAND.getStorage().readNBT(JojoProvider.STAND, props, null, message.data);
                    });});
                /*PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
                assert player != null;
                IStand props = JojoProvider.get(player);
                player.sendMessage(new StringTextComponent(String.valueOf(props.getTimeLeft())));
                assert props != null;
                JojoProvider.STAND.getStorage().readNBT(JojoProvider.STAND, props, null, message.data);*/
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
