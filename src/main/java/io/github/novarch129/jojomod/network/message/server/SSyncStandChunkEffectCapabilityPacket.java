package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.capability.StandChunkEffects;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandChunkEffectCapabilityPacket implements IMessage<SSyncStandChunkEffectCapabilityPacket> {
    private INBT data;
    private ChunkPos pos;

    public SSyncStandChunkEffectCapabilityPacket() {
    }

    private SSyncStandChunkEffectCapabilityPacket(CompoundNBT compoundNBT, int x, int z) {
        this.data = compoundNBT;
        this.pos = new ChunkPos(x, z);
    }

    public SSyncStandChunkEffectCapabilityPacket(StandChunkEffects props) {
        this.data = StandChunkEffects.STAND_CHUNK_EFFECTS.getStorage().writeNBT(StandChunkEffects.STAND_CHUNK_EFFECTS, props, null);
        this.pos = props.getChunk();
    }

    @Override
    public void encode(SSyncStandChunkEffectCapabilityPacket message, PacketBuffer buffer) {
        buffer.writeCompoundTag((CompoundNBT) message.data);
        buffer.writeInt(message.pos.x);
        buffer.writeInt(message.pos.z);
    }

    @Override
    public SSyncStandChunkEffectCapabilityPacket decode(PacketBuffer buffer) {
        return new SSyncStandChunkEffectCapabilityPacket(buffer.readCompoundTag(), buffer.readInt(), buffer.readInt());
    }

    @Override
    public void handle(SSyncStandChunkEffectCapabilityPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().world == null) return;
                StandChunkEffects.getLazyOptional(Minecraft.getInstance().world.getChunk(message.pos.x, message.pos.z)).ifPresent(props ->
                        StandChunkEffects.STAND_CHUNK_EFFECTS.getStorage().readNBT(StandChunkEffects.STAND_CHUNK_EFFECTS, props, null, message.data));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
