package com.novarch.jojomod.network.message.server;

import com.novarch.jojomod.entities.stands.silverChariot.EntitySilverChariot;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncSilverChariotArmorPacket
{
    private boolean hasArmor;

    public SSyncSilverChariotArmorPacket(boolean hasArmor)
    {
        this.hasArmor = hasArmor;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeBoolean(hasArmor);
    }

    public static SSyncSilverChariotArmorPacket decode(PacketBuffer buffer)
    {
        return new SSyncSilverChariotArmorPacket(buffer.readBoolean());
    }

    public static void handle(SSyncSilverChariotArmorPacket message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Minecraft.getInstance().world.getAllEntities().forEach(entity -> {
                    if(entity instanceof EntitySilverChariot)
                        ((EntitySilverChariot) entity).putHasArmor(message.hasArmor);
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
