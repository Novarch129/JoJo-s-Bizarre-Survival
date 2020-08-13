package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.KingCrimsonEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CKingCrimsonChargePunchPacket implements IMessage<CKingCrimsonChargePunchPacket> {
    private boolean isAttackKeyDown;

    public CKingCrimsonChargePunchPacket(boolean isAttackKeyDown) {
        this.isAttackKeyDown = isAttackKeyDown;
    }

    public CKingCrimsonChargePunchPacket() {
    }

    @Override
    public void encode(CKingCrimsonChargePunchPacket message, PacketBuffer buffer) {
        buffer.writeBoolean(message.isAttackKeyDown);
    }

    @Override
    public CKingCrimsonChargePunchPacket decode(PacketBuffer buffer) {
        return new CKingCrimsonChargePunchPacket(buffer.readBoolean());
    }

    @Override
    public void handle(CKingCrimsonChargePunchPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                if (!sender.world.isRemote)
                    sender.getServerWorld().getEntities()
                            .filter(entity -> entity instanceof KingCrimsonEntity)
                            .filter(entity -> ((KingCrimsonEntity) entity).getMaster().equals(sender))
                            .forEach(entity -> ((KingCrimsonEntity) entity).chargePunch(message.isAttackKeyDown));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
