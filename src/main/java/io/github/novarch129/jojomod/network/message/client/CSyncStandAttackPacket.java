package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capabilities.stand.Stand;
import io.github.novarch129.jojomod.entities.stands.AbstractStandEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncStandAttackPacket {
    public static void encode(CSyncStandAttackPacket msg, PacketBuffer buffer) {
    }

    public static CSyncStandAttackPacket decode(PacketBuffer buffer) {
        return new CSyncStandAttackPacket();
    }

    public static void handle(CSyncStandAttackPacket message, Supplier<Context> ctx) {
        ServerPlayerEntity player = (ctx.get().getSender());
        if (player != null) {
            World world = player.world;
            Stand.getLazyOptional(player).ifPresent(props -> {
                if (props.getStandOn() && !world.isRemote)
                    Objects.requireNonNull(world.getServer()).getWorld(player.dimension).getEntities()
                            .filter(entity -> entity instanceof AbstractStandEntity)
                            .filter(entity -> ((AbstractStandEntity) entity).getMaster().getEntityId() == player.getEntityId())
                            .forEach(entity -> ((AbstractStandEntity) entity).attack(player.isSprinting()));
            });
        }
    }

}
