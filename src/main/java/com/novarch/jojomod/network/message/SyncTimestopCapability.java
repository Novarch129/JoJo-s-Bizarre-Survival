package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.timestop.ITimestop;
import com.novarch.jojomod.capabilities.timestop.Timestop;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SyncTimestopCapability
{
    private INBT data;
    private UUID uuid;

    public SyncTimestopCapability() {}

    public SyncTimestopCapability(ITimestop props, UUID uuid)
    {
        this.data = new CompoundNBT();
        this.data = Timestop.CAPABILITY.getStorage().writeNBT(Timestop.CAPABILITY, props, null);
        this.uuid = uuid;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeCompoundTag((CompoundNBT) data);
        buffer.writeUniqueId(uuid);
    }

    public static SyncTimestopCapability decode(PacketBuffer buffer)
    {
        SyncTimestopCapability msg = new SyncTimestopCapability();
        msg.data = buffer.readCompoundTag();
        msg.uuid = buffer.readUniqueId();
        return msg;
    }

    public static void handle(SyncTimestopCapability message, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
        {
            ctx.get().enqueueWork(() ->
            {
                Entity entity = null;
                for(Entity entity1 : Minecraft.getInstance().world.getAllEntities()) {
                    if(entity1.getUniqueID() == message.uuid)
                        entity = entity1;
                }
                assert entity != null;
                ITimestop props = Timestop.getCapabilityFromEntity(entity);
                assert props != null;
                Timestop.CAPABILITY.getStorage().readNBT(Timestop.CAPABILITY, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
