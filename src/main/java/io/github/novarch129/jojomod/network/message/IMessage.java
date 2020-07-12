package io.github.novarch129.jojomod.network.message;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Mostly just used so I don't screw up a method in my packets, also vital to {@link PacketHandler#registerPacket(Class, IMessage)}.
 */
public interface IMessage<P> {
    void encode(P message, PacketBuffer buffer);

    P decode(PacketBuffer buffer);

    void handle(P message, Supplier<NetworkEvent.Context> ctx);
}
