package com.novarch.jojomod.network.message;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
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
                abilityToggle(sender);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void abilityToggle(PlayerEntity player)
    {
        if(player != null) {
            JojoProvider.getLazyOptional(player).ifPresent(props -> {

                FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
                fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
                props.setAbility(!props.getAbility());

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience) {
                player.sendMessage(new StringTextComponent("Mode: Normal"));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience) {
                player.sendMessage(new StringTextComponent("Mode: Lifegiver"));
            }

            if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.GER) {
                player.sendMessage(new StringTextComponent("Mode: Normal"));
            }

            if (props.getAbility() && props.getStandID() == JojoLibs.StandID.GER) {
                player.sendMessage(new StringTextComponent("Mode: Gold Experience Requiem"));
            }

            if (!props.getAbility() && props.getStandID() != JojoLibs.StandID.goldExperience && props.getStandID() != JojoLibs.StandID.GER && props.getStandID() != JojoLibs.StandID.killerQueen) {
                player.sendMessage(new StringTextComponent("Ability: OFF"));
                if(props.getStandID() == JojoLibs.StandID.aerosmith)
                    Minecraft.getInstance().gameSettings.thirdPersonView = 0;
            }

            if (props.getAbility() && props.getStandID() != JojoLibs.StandID.goldExperience && props.getStandID() != JojoLibs.StandID.GER && props.getStandID() != JojoLibs.StandID.killerQueen && props.getStandID() != JojoLibs.StandID.madeInHeaven) {
                player.sendMessage(new StringTextComponent("Ability: ON"));
                if (props.getStandID() == JojoLibs.StandID.aerosmith && props.getStandOn())
                    player.world.addEntity(fakePlayer);
            }
            });
        }
    }
}
