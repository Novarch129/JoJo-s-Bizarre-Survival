package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.events.custom.AbilityEvent;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncAbilityPacket {

    public static void encode(CSyncAbilityPacket msg, PacketBuffer buffer) { }

    public static CSyncAbilityPacket decode(PacketBuffer buffer) {
        return new CSyncAbilityPacket();
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
                int standID = props.getStandID();
                int act = props.getAct();
                boolean standOn = props.getStandOn();

                props.setAbility(!props.getAbility());

                if (props.getAbility()) {
                    switch (standID) {
                        case Util.StandID.theHand:
                        case Util.StandID.magiciansRed:
                        case Util.StandID.killerQueen:
                        case Util.StandID.theEmperor:
                            break;
                        case Util.StandID.goldExperience: {
                            player.sendMessage(new StringTextComponent("Mode: Lifegiver"));
                            break;
                        }
                        case Util.StandID.GER: {
                            player.sendMessage(new StringTextComponent("Mode: Gold Experience Requiem"));
                            break;
                        }
                        case Util.StandID.aerosmith: {
                            player.sendMessage(new StringTextComponent("Ability: ON"));
                            if (standOn)
                                player.world.addEntity(fakePlayer);
                        }
                        default: {
                            if (standID != Util.StandID.madeInHeaven || act != 0)
                                player.sendMessage(new StringTextComponent("Ability: ON"));
                        }
                    }
                } else {
                    switch (standID) {
                        case Util.StandID.theHand:
                        case Util.StandID.magiciansRed:
                        case Util.StandID.theEmperor:
                        case Util.StandID.killerQueen:
                            break;
                        case Util.StandID.goldExperience:
                        case Util.StandID.GER: {
                            player.sendMessage(new StringTextComponent("Mode: Normal"));
                            break;
                        }
                        default: {
                            if (standID != Util.StandID.madeInHeaven || act != 0)
                                player.sendMessage(new StringTextComponent("Ability: OFF"));
                            if (props.getStandID() == Util.StandID.theWorld && props.getStandOn() && props.getTimeLeft() > 780 && props.getCooldown() <= 0)
                                player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);

                            if (props.getStandID() == Util.StandID.starPlatinum && props.getStandOn() && props.getTimeLeft() > 900 && props.getCooldown() <= 0)
                                player.world.playSound(null, new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()), SoundInit.TIME_RESUME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5.0f, 1.0f);
                        }
                    }
                }

                if (props.getAbility())
                    MinecraftForge.EVENT_BUS.post(new AbilityEvent.AbilityActivated(player));

                if (!props.getAbility())
                    MinecraftForge.EVENT_BUS.post(new AbilityEvent.AbilityDeactivated(player));
            });
        }
    }
}
