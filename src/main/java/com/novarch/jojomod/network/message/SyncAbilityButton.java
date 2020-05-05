package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAbilityButton
{
    public boolean ability;

    public SyncAbilityButton() {}

    public SyncAbilityButton(boolean value) { this.ability = value; }

    public static void encode(SyncAbilityButton msg, PacketBuffer buffer)
    {
        buffer.writeBoolean(msg.ability);
    }

    public static SyncAbilityButton decode(PacketBuffer buffer)
    {
        return new SyncAbilityButton(buffer.readBoolean());
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
                abilityToggle((PlayerEntity) sender);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void abilityToggle(PlayerEntity player)
    {
        if(player != null) {
            LazyOptional<IStand> power = player.getCapability(JojoProvider.STAND, null);
            IStand props = power.orElse(new IStandCapability());

            if (props != null)
            {
                props.setAbility(!props.getAbility());
            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience) {
                player.sendMessage((ITextComponent)new TranslationTextComponent("Mode: Normal", new Object[0]));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience) {
                player.sendMessage((ITextComponent)new TranslationTextComponent("Mode: Lifegiver", new Object[0]));
            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.GER) {
                player.sendMessage((ITextComponent)new TranslationTextComponent("Mode: Normal", new Object[0]));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.GER) {
                player.sendMessage((ITextComponent)new TranslationTextComponent("Mode: Gold Experience Requiem", new Object[0]));
            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.kingCrimson) {
                player.sendMessage(new TranslationTextComponent("Ability: OFF", new Object[0]));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.kingCrimson) {
                player.sendMessage(new TranslationTextComponent("Ability: ON", new Object[0]));
            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.madeInHeaven) {
                player.sendMessage(new TranslationTextComponent("Ability: OFF", new Object[0]));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.madeInHeaven) {
                player.sendMessage(new TranslationTextComponent("Ability: ON", new Object[0]));

            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.dirtyDeedsDoneDirtCheap) {
                player.sendMessage(new TranslationTextComponent("Ability: OFF", new Object[0]));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.dirtyDeedsDoneDirtCheap) {
                player.sendMessage(new TranslationTextComponent("Ability: ON", new Object[0]));

            }
        }
    }
}
