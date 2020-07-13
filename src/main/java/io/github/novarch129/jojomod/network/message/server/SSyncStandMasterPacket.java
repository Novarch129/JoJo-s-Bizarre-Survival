package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.entity.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandMasterPacket implements IMessage<SSyncStandMasterPacket> {
    private int standID;
    private int masterID;

    public SSyncStandMasterPacket() {
    }

    public SSyncStandMasterPacket(int standID, int masterID) {
        this.standID = standID;
        this.masterID = masterID;
    }

    @Override
    public void encode(SSyncStandMasterPacket msg, PacketBuffer buffer) {
        buffer.writeInt(msg.standID);
        buffer.writeInt(msg.masterID);
    }

    @Override
    public SSyncStandMasterPacket decode(PacketBuffer buffer) {
        return new SSyncStandMasterPacket(buffer.readInt(), buffer.readInt());
    }

    @Override
    public void handle(SSyncStandMasterPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().world == null) return;
                Entity stand = Minecraft.getInstance().world.getEntityByID(msg.standID);
                Entity master = Minecraft.getInstance().world.getEntityByID(msg.masterID);
                if (stand != null && master != null && stand.world.isRemote)
                    ((AbstractStandEntity) stand).setMaster((PlayerEntity) master);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
