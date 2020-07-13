package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.capability.timestop.ITimestop;
import io.github.novarch129.jojomod.capability.timestop.Timestop;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncTimestopCapabilityPacket implements IMessage<SSyncTimestopCapabilityPacket> {
    private INBT data;

    public SSyncTimestopCapabilityPacket() {
    }

    public SSyncTimestopCapabilityPacket(ITimestop props) {
        data = new CompoundNBT();
        data = Timestop.TIMESTOP.getStorage().writeNBT(Timestop.TIMESTOP, props, null);
    }

    @Override
    public SSyncTimestopCapabilityPacket decode(PacketBuffer buffer) {
        SSyncTimestopCapabilityPacket msg = new SSyncTimestopCapabilityPacket();
        msg.data = buffer.readCompoundTag();
        return msg;
    }

    @Override
    public void handle(SSyncTimestopCapabilityPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Minecraft.getInstance().world.getAllEntities().forEach(entity -> {
                    ITimestop props = Timestop.getCapabilityFromEntity(entity);
                    Timestop.TIMESTOP.getStorage().readNBT(Timestop.TIMESTOP, props, null, message.data);
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncTimestopCapabilityPacket message, PacketBuffer buffer) {
        buffer.writeCompoundTag((CompoundNBT) message.data);
    }
}
