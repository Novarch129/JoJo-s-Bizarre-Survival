package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.init.KeyInit;
import io.github.novarch129.jojomod.network.message.client.*;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import static io.github.novarch129.jojomod.network.message.client.CSyncAerosmithPacket.Action.MOVE;
import static io.github.novarch129.jojomod.network.message.client.CSyncAerosmithPacket.Direction.*;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class EventHandleKeybinds {
    @SubscribeEvent
    public static void onInput(TickEvent.ClientTickEvent event) { //It's recommended to use ClientTickEvent instead of any of the input events.
        if (event.phase != TickEvent.Phase.END || !Minecraft.getInstance().isGameFocused()) return;

        if (Minecraft.getInstance().player != null) {
            if (KeyInit.SPAWN_STAND.isPressed())
                JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandSummonPacket());

            if (KeyInit.TOGGLE_ABILITY.isPressed())
                JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAbilityPacket());

            if (Minecraft.getInstance().gameSettings.keyBindAttack.isKeyDown())
                JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandAttackPacket());

            Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
                if (props.getStandOn()) {
                    if (KeyInit.ABILITY1.isPressed())
                        JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandAbilitiesPacket(1));
                    if (KeyInit.ABILITY2.isPressed())
                        JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncStandAbilitiesPacket(2));
                }

                if (props.getStandID() == Util.StandID.AEROSMITH) {
                    if (props.getStandOn() && props.getAbility()) {
                        if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, FORWARDS, Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown()));
                        if (Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, BACKWARDS));
                        if (Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, RIGHT));
                        if (Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, LEFT));
                        if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, UP));
                        if (Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
                            JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(MOVE, DOWN));
                    }
                }
            });
        }
    }
}
