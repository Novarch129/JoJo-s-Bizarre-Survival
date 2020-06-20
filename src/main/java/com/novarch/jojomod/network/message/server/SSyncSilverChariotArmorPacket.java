package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.entities.stands.silverChariot.EntitySilverChariot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncSilverChariotArmorPacket
{
    private int standID;
    private boolean hasArmor;

    public SSyncSilverChariotArmorPacket(int standID, boolean hasArmor) {
        this.standID = standID;
        this.hasArmor = hasArmor;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(standID);
        buffer.writeBoolean(hasArmor);
    }

    public static SSyncSilverChariotArmorPacket decode(PacketBuffer buffer)
    {
        return new SSyncSilverChariotArmorPacket(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(SSyncSilverChariotArmorPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Entity entity = Minecraft.getInstance().world.getEntityByID(message.standID);
                if(entity != null)
                    if(entity instanceof EntitySilverChariot)
                        ((EntitySilverChariot)entity).putHasArmor(message.hasArmor);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
