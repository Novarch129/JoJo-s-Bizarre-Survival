package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAbilityButton
{
    public int entityID;

    public SyncAbilityButton() {}

    public SyncAbilityButton(int entityId) { this.entityID = entityId; }

    public static void encode(SyncAbilityButton msg, PacketBuffer buffer)
    {
        buffer.writeInt(msg.entityID);
    }

    public static SyncAbilityButton decode(PacketBuffer buffer)
    {
        return new SyncAbilityButton(buffer.readInt());
    }

    public static void handle(SyncAbilityButton msg, Supplier<NetworkEvent.Context> supplier)
    {
        final NetworkEvent.Context ctx = supplier.get();
        if(ctx.getDirection().getReceptionSide().isServer())
        {
            ctx.enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.getSender();
                if(sender == null)
                    return;
                abilityToggle(sender);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void abilityToggle(PlayerEntity player)
    {
        if(player != null) {
            LazyOptional<IStand> power = player.getCapability(JojoProvider.STAND, null);
            IStand props = power.orElse(new IStandCapability());

            if (props != null) {
                props.setAbility(props.getAbility());
            }

            if (!props.getAbility()) {
                player.sendMessage(new TranslationTextComponent("Ability: OFF", new Object[0]));
            }

            if (props.getAbility()) {
                player.sendMessage(new TranslationTextComponent("Ability: ON", new Object[0]));

            }
        }
    }
}
