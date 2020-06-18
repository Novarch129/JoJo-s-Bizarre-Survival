package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncStandMasterPacket
{
    private int standID;
    private int masterID;

    public SSyncStandMasterPacket(int standID, int masterID)
    {
        this.standID = standID;
        this.masterID = masterID;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(standID);
        buffer.writeInt(masterID);
    }

    public static SSyncStandMasterPacket decode(PacketBuffer buffer)
    {
        return new SSyncStandMasterPacket(buffer.readInt(), buffer.readInt());
    }

    public static void handle(SSyncStandMasterPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Entity entity = Minecraft.getInstance().world.getEntityByID(message.standID);
                Entity master = Minecraft.getInstance().world.getEntityByID(message.masterID);
                if(entity != null)
                    if(entity instanceof EntityStandBase)
                        if(master != null)
                            ((EntityStandBase) entity).setMaster((PlayerEntity) master);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
