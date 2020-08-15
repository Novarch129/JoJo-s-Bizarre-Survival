package io.github.novarch129.jojomod.network.message;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.network.message.client.*;
import io.github.novarch129.jojomod.network.message.server.*;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.Optional;

public class PacketHandler {
    static int networkId = 0;

    public static void register() {
        registerPacket(CStandSummonPacket.class, new CStandSummonPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(CStandAttackPacket.class, new CStandAttackPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(CToggleAbilityPacket.class, new CToggleAbilityPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(CAerosmithMovePacket.class, new CAerosmithMovePacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(CSyncStandAbilitiesPacket.class, new CSyncStandAbilitiesPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(SSyncStandCapabilityPacket.class, new SSyncStandCapabilityPacket(), NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(SSyncTimestopCapabilityPacket.class, new SSyncTimestopCapabilityPacket(), NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(SSyncStandMasterPacket.class, new SSyncStandMasterPacket(), NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(SSyncSilverChariotArmorPacket.class, new SSyncSilverChariotArmorPacket(), NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(CHierophantGreenPossessionPacket.class, new CHierophantGreenPossessionPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(SSyncStandEffectsCapabilityPacket.class, new SSyncStandEffectsCapabilityPacket(), NetworkDirection.PLAY_TO_CLIENT);
        registerPacket(CKingCrimsonChargePunchPacket.class, new CKingCrimsonChargePunchPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(CSwitchStandActPacket.class, new CSwitchStandActPacket(), NetworkDirection.PLAY_TO_SERVER);
        registerPacket(SSyncStickyFingersDisguisePacket.class, new SSyncStickyFingersDisguisePacket(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <MSG> void registerPacket(Class<MSG> clazz, IMessage<MSG> message) {
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++, clazz, message::encode, message::decode, message::handle);
    }

    public static <MSG> void registerPacket(Class<MSG> clazz, IMessage<MSG> message, NetworkDirection direction) { //Includes a NetworkDirection parameter.
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++, clazz, message::encode, message::decode, message::handle, Optional.of(direction));
    }
}
