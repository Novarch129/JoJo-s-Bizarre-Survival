package com.novarch.jojomod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class JojoBizarreSurvivalConfig
{
    public static class Common
    {
        public final ForgeConfigSpec.BooleanValue saveStandOnDeath;
        public final ForgeConfigSpec.BooleanValue sheerHeartAttackDeathLoop;
        public final ForgeConfigSpec.BooleanValue madeInHeavenAbilityAccelerating;
        public final ForgeConfigSpec.BooleanValue standPunchDamage;
        public final ForgeConfigSpec.BooleanValue standPunchBlockBreaking;
        public final ForgeConfigSpec.BooleanValue infiniteTimestop;

        Common(final ForgeConfigSpec.Builder builder)
        {
            builder.push("common");

            saveStandOnDeath = builder
                    .comment("Toggle save Stand on death")
                    .define("saveStandOnDeath", false);

            sheerHeartAttackDeathLoop = builder
                    .comment("Toggle Sheer Heart Attack attacking himself repeatedly.")
                    .define("sheerHeartAttackDeathLoop", false);

            madeInHeavenAbilityAccelerating = builder
                    .comment("Toggle being able to accelerate Made in Heaven's ability with shift")
                    .define("madeInHeavenAbilityAccelerating", false);

            standPunchDamage = builder
                    .comment("Toggle Stand punches damaging entities.")
                    .define("standPunchDamage", true);

            standPunchBlockBreaking = builder
                    .comment("Toggle Stand punches breaking blocks, like the MobGriefing gamerule.")
                    .define("standPunchBlockBreaking", true);

            infiniteTimestop = builder
                    .comment("Toggle The World's timestop no having a time limit.")
                    .define("infiniteTimestop", false);

            builder.pop();
        }
    }

    private static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static void register(final ModLoadingContext context)
    {
        context.registerConfig(ModConfig.Type.COMMON, commonSpec);
    }
}
