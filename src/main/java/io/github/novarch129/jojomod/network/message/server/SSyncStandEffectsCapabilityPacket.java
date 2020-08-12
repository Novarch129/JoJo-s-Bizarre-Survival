package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandEffectsCapabilityPacket implements IMessage<SSyncStandEffectsCapabilityPacket> {
    private INBT data;

    public SSyncStandEffectsCapabilityPacket() {
    }

    private SSyncStandEffectsCapabilityPacket(CompoundNBT compoundNBT) {
        data = compoundNBT;
    }

    public SSyncStandEffectsCapabilityPacket(StandEffects props) {
        data = StandEffects.STAND_EFFECTS.getStorage().writeNBT(StandEffects.STAND_EFFECTS, props, null);
    }

    @Override
    public SSyncStandEffectsCapabilityPacket decode(PacketBuffer buffer) {
        return new SSyncStandEffectsCapabilityPacket(buffer.readCompoundTag());
    }

    @Override
    public void handle(SSyncStandEffectsCapabilityPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().world == null) return;
                Minecraft.getInstance().world.getAllEntities().forEach(entity -> StandEffects.getLazyOptional(entity).ifPresent(props ->
                        StandEffects.STAND_EFFECTS.getStorage().readNBT(StandEffects.STAND_EFFECTS, props, null, message.data)
                ));
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncStandEffectsCapabilityPacket message, PacketBuffer buffer) {
        buffer.writeCompoundTag((CompoundNBT) message.data);
    }
}
