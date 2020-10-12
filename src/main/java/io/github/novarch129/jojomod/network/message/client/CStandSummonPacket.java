package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CStandSummonPacket implements IMessage<CStandSummonPacket> {
    @Override
    public void encode(CStandSummonPacket message, PacketBuffer buffer) {
    }

    @Override
    public CStandSummonPacket decode(PacketBuffer buffer) {
        return new CStandSummonPacket();
    }

    @Override
    public void handle(CStandSummonPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null || sender.isSpectator()) return;
                if (!sender.world.isRemote) {
                    Stand.getLazyOptional(sender).ifPresent(stand -> {
                        stand.setStandOn(!stand.getStandOn());
                        if (stand.getStandOn()) {
                            if (stand.getStandID() != 0 && !Util.StandID.ITEM_STANDS.contains(stand.getStandID())) {
                                AbstractStandEntity standEntity = Util.StandID.getStandByID(stand.getStandID(), sender.world);
                                if (Collections.frequency(sender.getServerWorld().getEntities().collect(Collectors.toList()), standEntity) > 0)
                                    return;
                                Vec3d position = sender.getLookVec().mul(0.5, 1, 0.5).add(sender.getPositionVec()).add(0, 0.5, 0);
                                standEntity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), sender.rotationYaw, sender.rotationPitch);
                                standEntity.setMaster(sender);
                                standEntity.setMasterUUID(sender.getUniqueID());
                                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> sender), new SSyncStandMasterPacket(standEntity.getEntityId(), sender.getEntityId()));
                                sender.world.addEntity(standEntity);
                            } else if (Util.StandID.ITEM_STANDS.contains(stand.getStandID()))
                                Util.StandID.summonItemStand(sender);
                        } else
                            stand.setAct(0);
                    });
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
