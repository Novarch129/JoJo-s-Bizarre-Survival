package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAbility2Button
{
    public boolean ability;

    public SyncAbility2Button() {}

    public SyncAbility2Button(boolean value) { this.ability = value; }

    public static void encode(SyncAbility2Button msg, PacketBuffer buffer)
    {
        buffer.writeBoolean(msg.ability);
    }

    public static SyncAbility2Button decode(PacketBuffer buffer)
    {
        return new SyncAbility2Button(buffer.readBoolean());
    }

    public static void handle(SyncAbility2Button msg, Supplier<NetworkEvent.Context> supplier)
    {
        final NetworkEvent.Context ctx = supplier.get();
        if(ctx.getDirection().getReceptionSide().isServer())
        {
            ctx.enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.getSender();
                if(sender == null)
                    return;
                abilityToggle((PlayerEntity) sender);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void abilityToggle(PlayerEntity player)
    {
        if(player != null)
        {
            player.changeDimension(DimensionType.THE_NETHER);
        }
    }
}
