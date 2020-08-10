package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.event.custom.AbilityEvent;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CToggleAbilityPacket implements IMessage<CToggleAbilityPacket> {
    @Override
    public void encode(CToggleAbilityPacket msg, PacketBuffer buffer) {
    }

    @Override
    public CToggleAbilityPacket decode(PacketBuffer buffer) {
        return new CToggleAbilityPacket();
    }

    @Override
    public void handle(CToggleAbilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                Stand.getLazyOptional(sender).ifPresent(props -> {
                    int standID = props.getStandID();
                    int act = props.getAct();

                    props.setAbility(!props.getAbility());

                    if (props.getAbility()) {
                        switch (standID) {
                            case Util.StandID.THE_HAND:
                            case Util.StandID.MAGICIANS_RED:
                            case Util.StandID.KILLER_QUEEN:
                            case Util.StandID.THE_EMPEROR:
                                break;
                            case Util.StandID.GOLD_EXPERIENCE: {
                                sender.sendMessage(new StringTextComponent("Mode: Lifegiver"), ChatType.GAME_INFO);
                                break;
                            }
                            case Util.StandID.GER: {
                                sender.sendMessage(new StringTextComponent("Mode: Truth"), ChatType.GAME_INFO);
                                break;
                            }
                            default: {
                                if (standID != Util.StandID.MADE_IN_HEAVEN || act != 0)
                                    sender.sendMessage(new StringTextComponent("Ability: ON"), ChatType.GAME_INFO);
                                break;
                            }
                        }
                    } else {
                        switch (standID) {
                            case Util.StandID.THE_HAND:
                            case Util.StandID.MAGICIANS_RED:
                            case Util.StandID.THE_EMPEROR:
                            case Util.StandID.KILLER_QUEEN:
                                break;
                            case Util.StandID.GOLD_EXPERIENCE:
                            case Util.StandID.GER: {
                                sender.sendMessage(new StringTextComponent("Mode: Normal"), ChatType.GAME_INFO);
                                break;
                            }
                            default: {
                                if (standID != Util.StandID.MADE_IN_HEAVEN || act != 0)
                                    sender.sendMessage(new StringTextComponent("Ability: OFF"), ChatType.GAME_INFO);
                                if (props.getStandID() == Util.StandID.THE_WORLD && props.getStandOn() && props.getTimeLeft() > 780 && props.getCooldown() <= 0)
                                    sender.world.playSound(null, sender.getPosition(), SoundInit.RESUME_TIME.get(), SoundCategory.NEUTRAL, 5, 1);

                                else if (props.getStandID() == Util.StandID.STAR_PLATINUM && props.getStandOn() && props.getTimeLeft() > 900 && props.getCooldown() <= 0)
                                    sender.world.playSound(null, sender.getPosition(), SoundInit.RESUME_TIME_STAR_PLATINUM.get(), SoundCategory.NEUTRAL, 5, 1);
                                break;
                            }
                        }
                    }
                    MinecraftForge.EVENT_BUS.post(props.getAbility() ? new AbilityEvent.AbilityActivated(sender) : new AbilityEvent.AbilityDeactivated(sender));
                });
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
