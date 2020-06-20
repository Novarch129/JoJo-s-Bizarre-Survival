package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.silverChariot.EntitySilverChariot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncMagiciansRedFirePacket
{
    private int standID;
    private boolean isExplosive;

    public SSyncMagiciansRedFirePacket(int standID, boolean isExplosive) {
        this.standID = standID;
        this.isExplosive = isExplosive;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(standID);
        buffer.writeBoolean(isExplosive);
    }

    public static SSyncMagiciansRedFirePacket decode(PacketBuffer buffer)
    {
        return new SSyncMagiciansRedFirePacket(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(SSyncMagiciansRedFirePacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Entity entity = Minecraft.getInstance().world.getEntityByID(message.standID);
                if(entity != null)
                    if(entity instanceof EntityStandPunch.magiciansRed)
                        ((EntityStandPunch.magiciansRed)entity).putExplosive(message.isExplosive);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
