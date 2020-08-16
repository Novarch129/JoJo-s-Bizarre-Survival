package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.IChargeable;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CChargeAttackPacket implements IMessage<CChargeAttackPacket> {
    private boolean isAttackKeyDown;

    public CChargeAttackPacket(boolean isAttackKeyDown) {
        this.isAttackKeyDown = isAttackKeyDown;
    }

    public CChargeAttackPacket() {
    }

    @Override
    public void encode(CChargeAttackPacket message, PacketBuffer buffer) {
        buffer.writeBoolean(message.isAttackKeyDown);
    }

    @Override
    public CChargeAttackPacket decode(PacketBuffer buffer) {
        return new CChargeAttackPacket(buffer.readBoolean());
    }

    @Override
    public void handle(CChargeAttackPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                if (!sender.world.isRemote)
                    sender.getServerWorld().getEntities()
                            .filter(entity -> entity instanceof AbstractStandEntity && entity instanceof IChargeable)
                            .filter(entity -> ((AbstractStandEntity) entity).getMaster().equals(sender))
                            .forEach(entity -> ((IChargeable) entity).chargeAttack(message.isAttackKeyDown));
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
