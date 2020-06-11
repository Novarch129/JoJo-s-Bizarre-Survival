package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.network.message.*;

public class PacketInit {
    public static void register() {
        int networkId = 0;
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++,
                SyncStandSummonButton.class,
                SyncStandSummonButton::encode,
                SyncStandSummonButton::decode,
                SyncStandSummonButton::handle);
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++,
                SyncPlayerAttackMessage.class,
                SyncPlayerAttackMessage::encode,
                SyncPlayerAttackMessage::decode,
                SyncPlayerAttackMessage::handle);
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++,
                SyncAbilityButton.class,
                SyncAbilityButton::encode,
                SyncAbilityButton::decode,
                SyncAbilityButton::handle);
        JojoBizarreSurvival.INSTANCE.registerMessage(networkId++,
                SyncStandCapability.class,
                SyncStandCapability::encode,
                SyncStandCapability::decode,
                SyncStandCapability::handle);
    }
}
