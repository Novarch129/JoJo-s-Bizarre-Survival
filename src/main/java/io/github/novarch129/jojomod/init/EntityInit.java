package io.github.novarch129.jojomod.init;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.entity.KingCrimsonAfterimageEntity;
import io.github.novarch129.jojomod.entity.StandArrowEntity;
import io.github.novarch129.jojomod.entity.stand.*;
import io.github.novarch129.jojomod.entity.stand.attack.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, JojoBizarreSurvival.MOD_ID);

    public static final RegistryObject<EntityType<KingCrimsonEntity>> KING_CRIMSON = ENTITY_TYPES
            .register("king_crimson",
                    () -> EntityType.Builder.create(KingCrimsonEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "king_crimson").toString()));

    public static final RegistryObject<EntityType<KingCrimsonPunchEntity>> KING_CRIMSON_PUNCH = ENTITY_TYPES
            .register("king_crimson_punch", () -> EntityType.Builder.<KingCrimsonPunchEntity>create(KingCrimsonPunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "king_crimson_punch").toString()));

    public static final RegistryObject<EntityType<D4CEntity>> D4C = ENTITY_TYPES
            .register("d4c",
                    () -> EntityType.Builder.create(D4CEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c").toString()));

    public static final RegistryObject<EntityType<D4CPunchEntity>> D4C_PUNCH = ENTITY_TYPES
            .register("d4c_punch", () -> EntityType.Builder.<D4CPunchEntity>create(D4CPunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c_punch").toString()));

    public static final RegistryObject<EntityType<GoldExperienceEntity>> GOLD_EXPERIENCE = ENTITY_TYPES
            .register("gold_experience",
                    () -> EntityType.Builder.create(GoldExperienceEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience").toString()));

    public static final RegistryObject<EntityType<GoldExperiencePunchEntity>> GOLD_EXPERIENCE_PUNCH = ENTITY_TYPES
            .register("gold_experience_punch", () -> EntityType.Builder.<GoldExperiencePunchEntity>create(GoldExperiencePunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_punch").toString()));

    public static final RegistryObject<EntityType<MadeInHeavenEntity>> MADE_IN_HEAVEN = ENTITY_TYPES
            .register("made_in_heaven",
                    () -> EntityType.Builder.create(MadeInHeavenEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "made_in_heaven").toString()));

    public static final RegistryObject<EntityType<MadeInHeavenPunchEntity>> MADE_IN_HEAVEN_PUNCH = ENTITY_TYPES
            .register("made_in_heaven_punch", () -> EntityType.Builder.<MadeInHeavenPunchEntity>create(MadeInHeavenPunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "made_in_heaven_punch").toString()));

    public static final RegistryObject<EntityType<GoldExperienceRequiemEntity>> GOLD_EXPERIENCE_REQUIEM = ENTITY_TYPES
            .register("gold_experience_requiem",
                    () -> EntityType.Builder.create(GoldExperienceRequiemEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_requiem").toString()));

    public static final RegistryObject<EntityType<GoldExperienceRequiemPunchEntity>> GOLD_EXPERIENCE_REQUIEM_PUNCH = ENTITY_TYPES
            .register("gold_experience_requiem_punch", () -> EntityType.Builder.<GoldExperienceRequiemPunchEntity>create(GoldExperienceRequiemPunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_requiem_punch").toString()));

    public static final RegistryObject<EntityType<AerosmithEntity>> AEROSMITH = ENTITY_TYPES
            .register("aerosmith",
                    () -> EntityType.Builder.create(AerosmithEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.8f, 0.8f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "aerosmith").toString()));

    public static final RegistryObject<EntityType<AerosmithBulletEntity>> AEROSMITH_BULLET = ENTITY_TYPES
            .register("aerosmith_bullet", () -> EntityType.Builder.<AerosmithBulletEntity>create(AerosmithBulletEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.1f, 0.1f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "aerosmith_bullet").toString()));

    public static final RegistryObject<EntityType<WeatherReportEntity>> WEATHER_REPORT = ENTITY_TYPES
            .register("weather_report",
                    () -> EntityType.Builder.create(WeatherReportEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "weather_report").toString()));

    public static final RegistryObject<EntityType<WeatherReportPunchEntity>> WEATHER_REPORT_PUNCH = ENTITY_TYPES
            .register("weather_report_punch", () -> EntityType.Builder.<WeatherReportPunchEntity>create(WeatherReportPunchEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.2f, 0.2f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "weather_report_punch").toString()));

    public static final RegistryObject<EntityType<KillerQueenEntity>> KILLER_QUEEN = ENTITY_TYPES
            .register("killer_queen",
                    () -> EntityType.Builder.create(KillerQueenEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "killer_queen").toString()));

    public static final RegistryObject<EntityType<KillerQueenPunchEntity>> KILLER_QUEEN_PUNCH = ENTITY_TYPES
            .register("killer_queen_punch",
                    () -> EntityType.Builder.<KillerQueenPunchEntity>create(KillerQueenPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "killer_queen_punch").toString()));

    public static final RegistryObject<EntityType<SheerHeartAttackEntity>> SHEER_HEART_ATTACK = ENTITY_TYPES
            .register("sheer_heart_attack",
                    () -> EntityType.Builder.<SheerHeartAttackEntity>create(SheerHeartAttackEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.6f, 0.6f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "sheer_heart_attack").toString()));

    public static final RegistryObject<EntityType<CrazyDiamondEntity>> CRAZY_DIAMOND = ENTITY_TYPES
            .register("crazy_diamond",
                    () -> EntityType.Builder.create(CrazyDiamondEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "crazy_diamond").toString()));

    public static final RegistryObject<EntityType<CrazyDiamondPunchEntity>> CRAZY_DIAMOND_PUNCH = ENTITY_TYPES
            .register("crazy_diamond_punch",
                    () -> EntityType.Builder.<CrazyDiamondPunchEntity>create(CrazyDiamondPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "crazy_diamond_punch").toString()));

    public static final RegistryObject<EntityType<PurpleHazeEntity>> PURPLE_HAZE = ENTITY_TYPES
            .register("purple_haze",
                    () -> EntityType.Builder.create(PurpleHazeEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "purple_haze").toString()));

    public static final RegistryObject<EntityType<PurpleHazePunchEntity>> PURPLE_HAZE_PUNCH = ENTITY_TYPES
            .register("purple_haze_punch",
                    () -> EntityType.Builder.<PurpleHazePunchEntity>create(PurpleHazePunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "purple_haze_punch").toString()));

    public static final RegistryObject<EntityType<EmperorBulletEntity>> EMPEROR_BULLET = ENTITY_TYPES
            .register("emperor_bullet", () -> EntityType.Builder.<EmperorBulletEntity>create(EmperorBulletEntity::new, EntityClassification.MISC)
                    .disableSummoning().size(0.5f, 0.5f)
                    .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "emperor_bullet").toString()));

    public static final RegistryObject<EntityType<WhitesnakeEntity>> WHITESNAKE = ENTITY_TYPES
            .register("whitesnake",
                    () -> EntityType.Builder.create(WhitesnakeEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "whitesnake").toString()));

    public static final RegistryObject<EntityType<WhitesnakePunchEntity>> WHITESNAKE_PUNCH = ENTITY_TYPES
            .register("whitesnake_punch",
                    () -> EntityType.Builder.<WhitesnakePunchEntity>create(WhitesnakePunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "whitesnake_punch").toString()));

    public static final RegistryObject<EntityType<CMoonEntity>> CMOON = ENTITY_TYPES
            .register("cmoon",
                    () -> EntityType.Builder.create(CMoonEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "cmoon").toString()));

    public static final RegistryObject<EntityType<CMoonPunchEntity>> CMOON_PUNCH = ENTITY_TYPES
            .register("cmoon_punch",
                    () -> EntityType.Builder.<CMoonPunchEntity>create(CMoonPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "cmoon_punch").toString()));

    public static final RegistryObject<EntityType<TheWorldEntity>> THE_WORLD = ENTITY_TYPES
            .register("the_world",
                    () -> EntityType.Builder.create(TheWorldEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_world").toString()));

    public static final RegistryObject<EntityType<TheWorldPunchEntity>> THE_WORLD_PUNCH = ENTITY_TYPES
            .register("the_world_punch",
                    () -> EntityType.Builder.<TheWorldPunchEntity>create(TheWorldPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_world_punch").toString()));

    public static final RegistryObject<EntityType<StarPlatinumEntity>> STAR_PLATINUM = ENTITY_TYPES
            .register("star_platinum",
                    () -> EntityType.Builder.create(StarPlatinumEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "star_platinum").toString()));

    public static final RegistryObject<EntityType<StarPlatinumPunchEntity>> STAR_PLATINUM_PUNCH = ENTITY_TYPES
            .register("star_platinum_punch",
                    () -> EntityType.Builder.<StarPlatinumPunchEntity>create(StarPlatinumPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "star_platinum_punch").toString()));

    public static final RegistryObject<EntityType<SilverChariotEntity>> SILVER_CHARIOT = ENTITY_TYPES
            .register("silver_chariot",
                    () -> EntityType.Builder.create(SilverChariotEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "silver_chariot").toString()));

    public static final RegistryObject<EntityType<SilverChariotSwordEntity>> SILVER_CHARIOT_SWORD = ENTITY_TYPES
            .register("silver_chariot_sword",
                    () -> EntityType.Builder.<SilverChariotSwordEntity>create(SilverChariotSwordEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.3f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "silver_chariot_sword").toString()));

    public static final RegistryObject<EntityType<MagiciansRedEntity>> MAGICIANS_RED = ENTITY_TYPES
            .register("magicians_red",
                    () -> EntityType.Builder.create(MagiciansRedEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "magicians_red").toString()));

    public static final RegistryObject<EntityType<MagiciansRedFlameEntity>> MAGICIANS_RED_FLAMES = ENTITY_TYPES
            .register("magicians_red_flames",
                    () -> EntityType.Builder.<MagiciansRedFlameEntity>create(MagiciansRedFlameEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "magicians_red_flames").toString()));

    public static final RegistryObject<EntityType<TheHandEntity>> THE_HAND = ENTITY_TYPES
            .register("the_hand",
                    () -> EntityType.Builder.create(TheHandEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_hand").toString()));

    public static final RegistryObject<EntityType<TheHandPunchEntity>> THE_HAND_PUNCH = ENTITY_TYPES
            .register("the_hand_punch",
                    () -> EntityType.Builder.<TheHandPunchEntity>create(TheHandPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_hand_punch").toString()));

    public static final RegistryObject<EntityType<StandArrowEntity>> STAND_ARROW = ENTITY_TYPES
            .register("stand_arrow",
                    () -> EntityType.Builder.<StandArrowEntity>create(StandArrowEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.5f, 0.5f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_arrow").toString()));

    public static final RegistryObject<EntityType<HierophantGreenEntity>> HIEROPHANT_GREEN = ENTITY_TYPES
            .register("hierophant_green",
                    () -> EntityType.Builder.create(HierophantGreenEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "hierophant_green").toString()));

    public static final RegistryObject<EntityType<EmeraldSplashEntity>> EMERALD_SPLASH = ENTITY_TYPES
            .register("emerald_splash",
                    () -> EntityType.Builder.<EmeraldSplashEntity>create(EmeraldSplashEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "emerald_splash").toString()));

    public static final RegistryObject<EntityType<HierophantGreenTailEntity>> HIEROPHANT_GREEN_TAIL = ENTITY_TYPES
            .register("hierophant_green_tail",
                    () -> EntityType.Builder.<HierophantGreenTailEntity>create(HierophantGreenTailEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.4f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "hierophant_green_tail").toString()));

    public static final RegistryObject<EntityType<GreenDayEntity>> GREEN_DAY = ENTITY_TYPES
            .register("green_day",
                    () -> EntityType.Builder.create(GreenDayEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "green_day").toString()));

    public static final RegistryObject<EntityType<GreenDayPunchEntity>> GREEN_DAY_PUNCH = ENTITY_TYPES
            .register("green_day_punch",
                    () -> EntityType.Builder.<GreenDayPunchEntity>create(GreenDayPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "green_day_punch").toString()));

    public static final RegistryObject<EntityType<TwentiethCenturyBoyEntity>> TWENTIETH_CENTURY_BOY = ENTITY_TYPES
            .register("twentieth_century_boy",
                    () -> EntityType.Builder.create(TwentiethCenturyBoyEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "twentieth_century_boy").toString()));

    public static final RegistryObject<EntityType<TheGratefulDeadEntity>> THE_GRATEFUL_DEAD = ENTITY_TYPES
            .register("the_grateful_dead",
                    () -> EntityType.Builder.create(TheGratefulDeadEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_grateful_dead").toString()));

    public static final RegistryObject<EntityType<TheGratefulDeadPunchEntity>> THE_GRATEFUL_DEAD_PUNCH = ENTITY_TYPES
            .register("the_grateful_dead_punch",
                    () -> EntityType.Builder.<TheGratefulDeadPunchEntity>create(TheGratefulDeadPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "the_grateful_dead_punch").toString()));

    public static final RegistryObject<EntityType<StickyFingersEntity>> STICKY_FINGERS = ENTITY_TYPES
            .register("sticky_fingers",
                    () -> EntityType.Builder.create(StickyFingersEntity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.2f, 2.7f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "sticky_fingers").toString()));

    public static final RegistryObject<EntityType<StickyFingersPunchEntity>> STICKY_FINGERS_PUNCH = ENTITY_TYPES
            .register("sticky_fingers_punch",
                    () -> EntityType.Builder.<StickyFingersPunchEntity>create(StickyFingersPunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "sticky_fingers_punch").toString()));

    public static final RegistryObject<EntityType<NailBulletEntity>> NAIL_BULLET = ENTITY_TYPES
            .register("nail_bullet",
                    () -> EntityType.Builder.<NailBulletEntity>create(NailBulletEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.1f, 0.1f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "nail_bullet").toString()));

    public static final RegistryObject<EntityType<TuskAct1Entity>> TUSK_ACT_1 = ENTITY_TYPES
            .register("tusk_act_1",
                    () -> EntityType.Builder.create(TuskAct1Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.9f, 1.6f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "tusk_act_1").toString()));

    public static final RegistryObject<EntityType<TuskAct2Entity>> TUSK_ACT_2 = ENTITY_TYPES
            .register("tusk_act_2",
                    () -> EntityType.Builder.create(TuskAct2Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.9f, 1.85f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "tusk_act_2").toString()));

    public static final RegistryObject<EntityType<TuskAct3Entity>> TUSK_ACT_3 = ENTITY_TYPES
            .register("tusk_act_3",
                    () -> EntityType.Builder.create(TuskAct3Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(0.9f, 1.9f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "tusk_act_3").toString()));

    public static final RegistryObject<EntityType<TuskAct4Entity>> TUSK_ACT_4 = ENTITY_TYPES
            .register("tusk_act_4",
                    () -> EntityType.Builder.create(TuskAct4Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.5f, 2.4f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "tusk_act_4").toString()));

    public static final RegistryObject<EntityType<TuskAct4PunchEntity>> TUSK_ACT_4_PUNCH = ENTITY_TYPES
            .register("tusk_act_4_punch",
                    () -> EntityType.Builder.<TuskAct4PunchEntity>create(TuskAct4PunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "tusk_act_4_punch").toString()));

    public static final RegistryObject<EntityType<EchoesAct1Entity>> ECHOES_ACT_1 = ENTITY_TYPES
            .register("echoes_act_1",
                    () -> EntityType.Builder.create(EchoesAct1Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().size(1.3f, 2.5f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "echoes_act_1").toString()));

    public static final RegistryObject<EntityType<EchoesSoundEntity>> ECHOES_SOUND = ENTITY_TYPES
            .register("echoes_sound",
                    () -> EntityType.Builder.<EchoesSoundEntity>create(EchoesSoundEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.3f, 0.3f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "echoes_sound").toString()));

    public static final RegistryObject<EntityType<EchoesAct2Entity>> ECHOES_ACT_2 = ENTITY_TYPES
            .register("echoes_act_2",
                    () -> EntityType.Builder.create(EchoesAct2Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().disableSummoning().size(1.3f, 1.9f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "echoes_act_2").toString()));

    public static final RegistryObject<EntityType<EchoesAct3Entity>> ECHOES_ACT_3 = ENTITY_TYPES
            .register("echoes_act_3",
                    () -> EntityType.Builder.create(EchoesAct3Entity::new, EntityClassification.CREATURE)
                            .disableSummoning().disableSummoning().size(0.9f, 1.8f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "echoes_act_3").toString()));

    public static final RegistryObject<EntityType<EchoesAct3PunchEntity>> ECHOES_ACT_3_PUNCH = ENTITY_TYPES
            .register("echoes_act_3_punch",
                    () -> EntityType.Builder.<EchoesAct3PunchEntity>create(EchoesAct3PunchEntity::new, EntityClassification.MISC)
                            .disableSummoning().size(0.2f, 0.2f)
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "echoes_act_3_punch").toString()));

    public static final RegistryObject<EntityType<KingCrimsonAfterimageEntity>> KING_CRIMSON_AFTERIMAGE = ENTITY_TYPES
            .register("king_crimson_afterimage",
                    () -> EntityType.Builder.<KingCrimsonAfterimageEntity>create(KingCrimsonAfterimageEntity::new, EntityClassification.MISC)
                            .disableSummoning()
                            .build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "king_crimson_afterimage").toString()));
}