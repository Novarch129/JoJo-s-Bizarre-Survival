package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.events.custom.AbilityEvent;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncAbilityPacket {
    public boolean ability;

    public CSyncAbilityPacket() {
    }

    public CSyncAbilityPacket(boolean value) {
        this.ability = value;
    }

    public static void encode(CSyncAbilityPacket msg, PacketBuffer buffer) {
        buffer.writeBoolean(msg.ability);
    }

    public static CSyncAbilityPacket decode(PacketBuffer buffer) {
        return new CSyncAbilityPacket(buffer.readBoolean());
    }

    public static void handle(CSyncAbilityPacket msg, Supplier<NetworkEvent.Context> supplier) {
        final NetworkEvent.Context ctx = supplier.get();
        if (ctx.getDirection().getReceptionSide().isServer()) {
            ctx.enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.getSender();
                if (sender == null)
                    return;
                abilityToggle(sender);
            });
        }
        ctx.setPacketHandled(true);
    }

    protected static void abilityToggle(PlayerEntity player) {
        if (player != null) {
            Stand.getLazyOptional(player).ifPresent(props -> {

                FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
                fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
                props.setAbility(!props.getAbility());

                if (props.getAbility())
                    MinecraftForge.EVENT_BUS.post(new AbilityEvent.AbilityActivated(player));

                if (!props.getAbility())
                    MinecraftForge.EVENT_BUS.post(new AbilityEvent.AbilityDeactivated(player));

                if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience)
                    player.sendMessage(new StringTextComponent("Mode: Normal"));

                if (props.getAbility() && props.getStandID() == JojoLibs.StandID.goldExperience)
                    player.sendMessage(new StringTextComponent("Mode: Lifegiver"));

                if (!props.getAbility() && props.getStandID() == JojoLibs.StandID.GER)
                    player.sendMessage(new StringTextComponent("Mode: Normal"));

                if (props.getAbility() && props.getStandID() == JojoLibs.StandID.GER)
                    player.sendMessage(new StringTextComponent("Mode: Gold Experience Requiem"));

                if (!props.getAbility() && props.getStandID() != JojoLibs.StandID.goldExperience && props.getStandID() != JojoLibs.StandID.GER && props.getStandID() != JojoLibs.StandID.killerQueen && props.getStandID() != JojoLibs.StandID.theEmperor) {
                    if (props.getStandID() != JojoLibs.StandID.madeInHeaven || (props.getStandID() == JojoLibs.StandID.madeInHeaven && props.getAct() != 0))
                        player.sendMessage(new StringTextComponent("Ability: OFF"));
                }

                if (props.getAbility() && props.getStandID() != JojoLibs.StandID.goldExperience && props.getStandID() != JojoLibs.StandID.GER && props.getStandID() != JojoLibs.StandID.killerQueen && props.getStandID() != JojoLibs.StandID.theEmperor) {
                    if (props.getStandID() != JojoLibs.StandID.madeInHeaven || (props.getStandID() == JojoLibs.StandID.madeInHeaven && props.getAct() != 0))
                        player.sendMessage(new StringTextComponent("Ability: ON"));
                    if (props.getStandID() == JojoLibs.StandID.aerosmith && props.getStandOn())
                        player.world.addEntity(fakePlayer);
                }
            });
        }
    }
}
