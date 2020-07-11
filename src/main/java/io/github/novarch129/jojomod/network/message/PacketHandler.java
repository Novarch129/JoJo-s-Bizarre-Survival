package io.github.novarch129.jojomod.network.message;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.network.message.client.*;
import io.github.novarch129.jojomod.network.message.server.SSyncSilverChariotArmorPacket;
import io.github.novarch129.jojomod.network.message.server.SSyncStandCapabilityPacket;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import io.github.novarch129.jojomod.network.message.server.SSyncTimestopCapabilityPacket;

@SuppressWarnings("unused")
public class PacketHandler {
    static int networkId = 0;

    public static void register() {
        registerPacket(CSyncStandSummonPacket.class, new CSyncStandSummonPacket());
        registerPacket(CSyncStandAttackPacket.class, new CSyncStandAttackPacket());
        registerPacket(CSyncAbilityPacket.class, new CSyncAbilityPacket());
        registerPacket(CSyncAerosmithPacket.class, new CSyncAerosmithPacket());
        registerPacket(CSyncStandAbilitiesPacket.class, new CSyncStandAbilitiesPacket());
        registerPacket(SSyncStandCapabilityPacket.class, new SSyncStandCapabilityPacket());
        registerPacket(SSyncTimestopCapabilityPacket.class, new SSyncTimestopCapabilityPacket());
        registerPacket(SSyncStandMasterPacket.class, new SSyncStandMasterPacket());
        registerPacket(SSyncSilverChariotArmorPacket.class, new SSyncSilverChariotArmorPacket());
    }

    private static <P> void registerPacket(Class<P> clazz, IMessage<P> message) {
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++, clazz, message::encode, message::decode, message::handle);
    }
}
