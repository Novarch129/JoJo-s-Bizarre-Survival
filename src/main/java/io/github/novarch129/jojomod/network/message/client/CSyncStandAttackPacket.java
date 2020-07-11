package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncStandAttackPacket implements IMessage<CSyncStandAttackPacket> {
    @Override
    public void encode(CSyncStandAttackPacket msg, PacketBuffer buffer) {
    }

    @Override
    public CSyncStandAttackPacket decode(PacketBuffer buffer) {
        return new CSyncStandAttackPacket();
    }

    @Override
    public void handle(CSyncStandAttackPacket message, Supplier<Context> ctx) {
        ServerPlayerEntity player = (ctx.get().getSender());
        if (player != null) {
            World world = player.world;
            Stand.getLazyOptional(player).ifPresent(props -> {
                if (props.getStandOn() && !world.isRemote && player.isSwingInProgress) //Don't want this firing continuously, punches come out way too fast.
                    Objects.requireNonNull(world.getServer()).getWorld(player.dimension).getEntities()
                            .filter(entity -> entity instanceof AbstractStandEntity)
                            .filter(entity -> ((AbstractStandEntity) entity).getMaster().getEntityId() == player.getEntityId())
                            .forEach(entity -> ((AbstractStandEntity) entity).attack(player.isSprinting()));
            });
        }
    }

}
