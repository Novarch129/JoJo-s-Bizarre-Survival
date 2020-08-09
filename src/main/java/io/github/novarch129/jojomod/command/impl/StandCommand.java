package io.github.novarch129.jojomod.command.impl;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.github.novarch129.jojomod.capability.stand.IStand;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

/**
 * Loving the Brigadier command syntax, real simple.
 */
@SuppressWarnings("unused")
public class StandCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> literalCommandNode = dispatcher.register(Commands.literal("stand")
                .requires(source -> source.hasPermissionLevel(2))
                .then(Commands.literal("set")
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.literal("king_crimson")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.KING_CRIMSON)))
                                .then(Commands.literal("d4c")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.D4C)))
                                .then(Commands.literal("gold_experience")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.GOLD_EXPERIENCE)))
                                .then(Commands.literal("made_in_heaven")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.MADE_IN_HEAVEN)))
                                .then(Commands.literal("ger")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.GER)))
                                .then(Commands.literal("gold_experience_requiem")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.GER)))
                                .then(Commands.literal("aerosmith")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.AEROSMITH)))
                                .then(Commands.literal("weather_report")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.WEATHER_REPORT)))
                                .then(Commands.literal("killer_queen")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.KILLER_QUEEN)))
                                .then(Commands.literal("crazy_diamond")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.CRAZY_DIAMOND)))
                                .then(Commands.literal("purple_haze")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.PURPLE_HAZE)))
                                .then(Commands.literal("the_emperor")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.THE_EMPEROR)))
                                .then(Commands.literal("whitesnake")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.WHITESNAKE)))
                                .then(Commands.literal("c-moon")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.CMOON)))
                                .then(Commands.literal("the_world")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.THE_WORLD)))
                                .then(Commands.literal("star_platinum")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.STAR_PLATINUM)))
                                .then(Commands.literal("silver_chariot")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.SILVER_CHARIOT)))
                                .then(Commands.literal("magicians_red")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.MAGICIANS_RED)))
                                .then(Commands.literal("the_hand")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.THE_HAND)))
                                .then(Commands.literal("hierophant_green")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.HIEROPHANT_GREEN)))
                                .then(Commands.literal("green_day")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.GREEN_DAY)))
                        .then(Commands.literal("20th_century_boy")
                                        .executes(context -> setPlayerStandID(context.getSource(), EntityArgument.getPlayer(context, "target"), Util.StandID.TWENTIETH_CENTURY_BOY)))
                        ))
                .then(Commands.literal("remove")
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(context -> removePlayerStand(context.getSource(), EntityArgument.getPlayer(context, "target")))
                        ))
                .then(Commands.literal("evolve")
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(context -> evolvePlayerStand(context.getSource(), EntityArgument.getPlayer(context, "target")))
                        ))
        );
    }

    private static int setPlayerStandID(CommandSource source, PlayerEntity target, int standID) {
        IStand props = Stand.getCapabilityFromPlayer(target);
        if (props.getStandID() != standID) {
            props.setStandID(standID);
            props.setStandOn(false);
            source.sendFeedback(new StringTextComponent("Successfully set StandID for " + target.getDisplayName().getFormattedText() + "."), true);
        } else
            source.sendErrorMessage(new StringTextComponent(target.getDisplayName().getFormattedText() + " already has that Stand."));
        return props.getStandID();
    }

    private static int removePlayerStand(CommandSource source, PlayerEntity target) {
        IStand props = Stand.getCapabilityFromPlayer(target);
        if (props.getStandID() != 0) {
            props.removeStand();
            source.sendFeedback(new StringTextComponent("Successfully removed Stand from " + target.getDisplayName().getFormattedText() + "."), true);
        } else
            source.sendErrorMessage(new StringTextComponent(target.getDisplayName().getFormattedText() + " does not have a Stand."));
        return 1;
    }

    private static int evolvePlayerStand(CommandSource source, PlayerEntity target) {
        IStand props = Stand.getCapabilityFromPlayer(target);
        int standID = 0;
        switch (props.getStandID()) {
            default: {
                source.sendErrorMessage(new StringTextComponent(target.getDisplayName().getFormattedText() + "'s Stand cannot be evolved."));
                break;
            }
            case 0: {
                source.sendErrorMessage(new StringTextComponent(target.getDisplayName().getFormattedText() + " does not have a Stand."));
                break;
            }
            case Util.StandID.GOLD_EXPERIENCE: {
                standID = Util.StandID.GER;
                break;
            }
            case Util.StandID.WHITESNAKE: {
                standID = Util.StandID.CMOON;
                break;
            }
            case Util.StandID.CMOON: {
                standID = Util.StandID.MADE_IN_HEAVEN;
                break;
            }
        }
        if (standID != 0) {
            props.setStandOn(false);
            props.setStandID(standID);
            source.sendFeedback(new StringTextComponent("Successfully evolved " + target.getDisplayName().getFormattedText() + "'s Stand."), true);
        }
        return props.getStandID();
    }
}
