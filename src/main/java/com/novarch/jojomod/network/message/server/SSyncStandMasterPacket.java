package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.entities.stands.AbstractStandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandMasterPacket {
    private int standID;
    private int masterID;

    public SSyncStandMasterPacket(int standID, int masterID) {
        this.standID = standID;
        this.masterID = masterID;
    }

    public static void encode(SSyncStandMasterPacket msg, PacketBuffer buffer) {
        buffer.writeInt(msg.standID);
        buffer.writeInt(msg.masterID);
    }

    public static SSyncStandMasterPacket decode(PacketBuffer buffer) {
        return new SSyncStandMasterPacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(SSyncStandMasterPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if(Minecraft.getInstance().world == null) return;
                Entity stand = Minecraft.getInstance().world.getEntityByID(msg.standID);
                Entity master = Minecraft.getInstance().world.getEntityByID(msg.masterID);
                if(stand != null && master != null && stand.world.isRemote)
                    ((AbstractStandEntity) stand).setMaster((PlayerEntity) master);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
