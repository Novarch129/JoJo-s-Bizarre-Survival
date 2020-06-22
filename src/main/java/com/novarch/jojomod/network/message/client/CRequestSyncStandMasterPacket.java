package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.network.message.server.SSyncStandMasterPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;

import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class CRequestSyncStandMasterPacket {
    private int standID;

    public CRequestSyncStandMasterPacket(int standID) {
        this.standID = standID;
    }

    public static void encode(CRequestSyncStandMasterPacket msg, PacketBuffer buffer) {
        buffer.writeInt(msg.standID);
    }

    public static CRequestSyncStandMasterPacket decode(PacketBuffer buffer) {
        return new CRequestSyncStandMasterPacket(buffer.readInt());
    }

    public static void handle(CRequestSyncStandMasterPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if(ctx.get().getSender() == null) return;
                if(ctx.get().getSender().world == null) return;
                Entity stand = ctx.get().getSender().world.getEntityByID(msg.standID);
                if(stand != null && !stand.world.isRemote) {
                    LogManager.getLogger().debug("run");
                    PlayerEntity master = ((EntityStandBase)stand).getMaster();
                    if(master != null)
                        JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> stand), new SSyncStandMasterPacket(stand.getEntityId(), master.getEntityId()));
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
