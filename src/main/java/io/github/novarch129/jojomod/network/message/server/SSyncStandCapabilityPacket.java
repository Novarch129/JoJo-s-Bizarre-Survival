package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.IStand;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandCapabilityPacket implements IMessage<SSyncStandCapabilityPacket> {
    private INBT data;

    public SSyncStandCapabilityPacket() {
    }

    public SSyncStandCapabilityPacket(IStand props) {
        this.data = new CompoundNBT();
        this.data = Stand.STAND.getStorage().writeNBT(Stand.STAND, props, null);
    }

    @Override
    public SSyncStandCapabilityPacket decode(PacketBuffer buffer) {
        SSyncStandCapabilityPacket msg = new SSyncStandCapabilityPacket();
        msg.data = buffer.readCompoundTag();
        return msg;
    }

    @Override
    public void handle(SSyncStandCapabilityPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
                if (player == null) return;
                Stand.getLazyOptional(player).ifPresent(props ->
                        Stand.STAND.getStorage().readNBT(Stand.STAND, props, null, message.data));
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncStandCapabilityPacket message, PacketBuffer buffer) {
        buffer.writeCompoundTag((CompoundNBT) message.data);
    }
}
