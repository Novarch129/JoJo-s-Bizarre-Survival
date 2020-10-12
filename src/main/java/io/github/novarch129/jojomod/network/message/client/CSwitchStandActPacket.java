package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSwitchStandActPacket implements IMessage<CSwitchStandActPacket> {
    @Override
    public void encode(CSwitchStandActPacket message, PacketBuffer buffer) {
    }

    @Override
    public CSwitchStandActPacket decode(PacketBuffer buffer) {
        return new CSwitchStandActPacket();
    }

    @Override
    public void handle(CSwitchStandActPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                Stand.getLazyOptional(sender).ifPresent(stand -> {
                    if (stand.getStandOn()) {
                        stand.changeAct();
                        Util.setupActSwitch(sender, stand.getStandID(), stand.getAct());
                    }
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
