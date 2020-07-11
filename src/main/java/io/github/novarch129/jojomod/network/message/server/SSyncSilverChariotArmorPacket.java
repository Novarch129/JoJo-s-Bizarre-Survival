package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.entity.stands.SilverChariotEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncSilverChariotArmorPacket implements IMessage<SSyncSilverChariotArmorPacket> {
    private int standID;
    private boolean hasArmor;

    public SSyncSilverChariotArmorPacket() {
    }

    public SSyncSilverChariotArmorPacket(int standID, boolean hasArmor) {
        this.standID = standID;
        this.hasArmor = hasArmor;
    }

    @Override
    public SSyncSilverChariotArmorPacket decode(PacketBuffer buffer) {
        return new SSyncSilverChariotArmorPacket(buffer.readInt(), buffer.readBoolean());
    }

    @Override
    public void handle(SSyncSilverChariotArmorPacket message, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() ->
            {
                assert Minecraft.getInstance().world != null;
                Entity entity = Minecraft.getInstance().world.getEntityByID(message.standID);
                if (entity != null)
                    if (entity instanceof SilverChariotEntity)
                        ((SilverChariotEntity) entity).putHasArmor(message.hasArmor);
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncSilverChariotArmorPacket message, PacketBuffer buffer) {
        buffer.writeInt(message.standID);
        buffer.writeBoolean(message.hasArmor);
    }
}
