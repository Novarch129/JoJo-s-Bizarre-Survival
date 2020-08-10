package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.capability.ITimestop;
import io.github.novarch129.jojomod.capability.Timestop;
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

    private SSyncTimestopCapabilityPacket(CompoundNBT compoundNBT) {
        data = compoundNBT;
    }

    public SSyncTimestopCapabilityPacket(ITimestop props) {
        data = Timestop.TIMESTOP.getStorage().writeNBT(Timestop.TIMESTOP, props, null);
    }

    @Override
    public SSyncTimestopCapabilityPacket decode(PacketBuffer buffer) {
        return new SSyncTimestopCapabilityPacket(buffer.readCompoundTag());
    }

    @Override
    public void handle(SSyncTimestopCapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().world == null) return;
                Minecraft.getInstance().world.getAllEntities().forEach(entity -> Timestop.getLazyOptional(entity).ifPresent(props ->
                        Timestop.TIMESTOP.getStorage().readNBT(Timestop.TIMESTOP, props, null, msg.data)
                ));
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncTimestopCapabilityPacket msg, PacketBuffer buffer) {
        buffer.writeCompoundTag((CompoundNBT) msg.data);
    }
}
