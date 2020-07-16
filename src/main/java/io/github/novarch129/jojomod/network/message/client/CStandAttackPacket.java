package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.Objects;
import java.util.function.Supplier;

public class CStandAttackPacket implements IMessage<CStandAttackPacket> {
    @Override
    public void encode(CStandAttackPacket msg, PacketBuffer buffer) {
    }

    @Override
    public CStandAttackPacket decode(PacketBuffer buffer) {
        return new CStandAttackPacket();
    }

    @Override
    public void handle(CStandAttackPacket msg, Supplier<Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                World world = sender.world;
                Stand.getLazyOptional(sender).ifPresent(props -> {
                    if (props.getStandOn() && !world.isRemote && sender.isSwingInProgress) //Don't want this firing continuously, punches come out way too fast.
                        Objects.requireNonNull(world.getServer()).getWorld(sender.dimension).getEntities()
                                .filter(entity -> entity instanceof AbstractStandEntity)
                                .filter(entity -> ((AbstractStandEntity) entity).getMaster().getEntityId() == sender.getEntityId())
                                .forEach(entity -> ((AbstractStandEntity) entity).attack(sender.isSprinting()));
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
