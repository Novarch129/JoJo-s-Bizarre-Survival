package io.github.novarch129.jojomod.network.message.server;

import io.github.novarch129.jojomod.entity.stand.SilverChariotEntity;
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
    public void handle(SSyncSilverChariotArmorPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
            ctx.get().enqueueWork(() -> {
                if (Minecraft.getInstance().world == null) return;
                Entity entity = Minecraft.getInstance().world.getEntityByID(msg.standID);
                if (entity != null)
                    if (entity instanceof SilverChariotEntity)
                        ((SilverChariotEntity) entity).putHasArmor(msg.hasArmor);
            });
        }
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void encode(SSyncSilverChariotArmorPacket msg, PacketBuffer buffer) {
        buffer.writeInt(msg.standID);
        buffer.writeBoolean(msg.hasArmor);
    }
}
