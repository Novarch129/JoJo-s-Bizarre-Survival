package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.EntityDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;

import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import com.novarch.jojomod.entities.stands.weatherReport.EntityWeatherReport;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Bus.MOD)
public class EntityInit
{
	private int ID = 0;

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, JojoBizarreSurvival.MOD_ID);
	
	public static final RegistryObject<EntityType<EntityKingCrimson>> KING_CRIMSON = ENTITY_TYPES
			.register("king_crimson",
					() -> EntityType.Builder.<EntityKingCrimson>create(EntityKingCrimson::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "king_crimson").toString()));
	
	public static final RegistryObject<EntityType<EntityStandPunch.kingCrimson>> KING_CRIMSON_PUNCH = ENTITY_TYPES
			.register("king_crimson_punch", () -> EntityType.Builder.<EntityStandPunch.kingCrimson>create(EntityStandPunch.kingCrimson::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "king_crimson_punch").toString()));

	public static final RegistryObject<EntityType<EntityDirtyDeedsDoneDirtCheap>> D4C = ENTITY_TYPES
			.register("d4c",
					() -> EntityType.Builder.<EntityDirtyDeedsDoneDirtCheap>create(EntityDirtyDeedsDoneDirtCheap::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.dirtyDeedsDoneDirtCheap>> D4C_PUNCH = ENTITY_TYPES
			.register("d4c_punch", () -> EntityType.Builder.<EntityStandPunch.dirtyDeedsDoneDirtCheap>create(EntityStandPunch.dirtyDeedsDoneDirtCheap::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "d4c_punch").toString()));

	public static final RegistryObject<EntityType<EntityGoldExperience>> GOLD_EXPERIENCE = ENTITY_TYPES
			.register("gold_experience",
					() -> EntityType.Builder.<EntityGoldExperience>create(EntityGoldExperience::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.goldExperience>> GOLD_EXPERIENCE_PUNCH = ENTITY_TYPES
			.register("gold_experience_punch", () -> EntityType.Builder.<EntityStandPunch.goldExperience>create(EntityStandPunch.goldExperience::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_punch").toString()));

	public static final RegistryObject<EntityType<EntityMadeInHeaven>> MADE_IN_HEAVEN = ENTITY_TYPES
			.register("made_in_heaven",
					() -> EntityType.Builder.<EntityMadeInHeaven>create(EntityMadeInHeaven::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "made_in_heaven").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.madeInHeaven>> MADE_IN_HEAVEN_PUNCH = ENTITY_TYPES
			.register("made_in_heaven_punch", () -> EntityType.Builder.<EntityStandPunch.madeInHeaven>create(EntityStandPunch.madeInHeaven::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "made_in_heaven_punch").toString()));

	public static final RegistryObject<EntityType<EntityGoldExperienceRequiem>> GOLD_EXPERIENCE_REQUIEM = ENTITY_TYPES
			.register("gold_experience_requiem",
					() -> EntityType.Builder.<EntityGoldExperienceRequiem>create(EntityGoldExperienceRequiem::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_requiem").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.goldExperienceRequiem>> GOLD_EXPERIENCE_REQUIEM_PUNCH = ENTITY_TYPES
			.register("gold_experience_requiem_punch", () -> EntityType.Builder.<EntityStandPunch.goldExperienceRequiem>create(EntityStandPunch.goldExperienceRequiem::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "gold_experience_requiem_punch").toString()));

	public static final RegistryObject<EntityType<EntityAerosmith>> AEROSMITH = ENTITY_TYPES
			.register("aerosmith",
					() -> EntityType.Builder.<EntityAerosmith>create(EntityAerosmith::new, EntityClassification.CREATURE)
							.size(0.8f, 0.8f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "aerosmith").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.aerosmith>> AEROSMITH_BULLET = ENTITY_TYPES
			.register("aerosmith_bullet", () -> EntityType.Builder.<EntityStandPunch.aerosmith>create(EntityStandPunch.aerosmith::new, EntityClassification.MISC)
					.size(0.1f, 0.1f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "aerosmith_bullet").toString()));

	public static final RegistryObject<EntityType<FakePlayerEntity>> FAKE_PLAYER = ENTITY_TYPES
			.register("fake_player_jojo",
					() -> EntityType.Builder.<FakePlayerEntity>create((p_i48576_1_, p_i48576_2_) -> new FakePlayerEntity(p_i48576_1_, p_i48576_2_, Minecraft.getInstance().player), EntityClassification.CREATURE)
							.size(1.0f, 2.0f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "fake_player_jojo").toString()));

	public static final RegistryObject<EntityType<EntityWeatherReport>> WEATHER_REPORT = ENTITY_TYPES
			.register("weather_report",
					() -> EntityType.Builder.<EntityWeatherReport>create(EntityWeatherReport::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "weather_report").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.weatherReport>> WEATHER_REPORT_PUNCH = ENTITY_TYPES
			.register("weather_report_punch", () -> EntityType.Builder.<EntityStandPunch.weatherReport>create(EntityStandPunch.weatherReport::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "weather_report_punch").toString()));

	public static final RegistryObject<EntityType<EntityKillerQueen>> KILLER_QUEEN = ENTITY_TYPES
			.register("killer_queen",
					() -> EntityType.Builder.<EntityKillerQueen>create(EntityKillerQueen::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "killer_queen").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.killerQueen>> KILLER_QUEEN_PUNCH = ENTITY_TYPES
			.register("killer_queen_punch",
					() -> EntityType.Builder.<EntityStandPunch.killerQueen>create(EntityStandPunch.killerQueen::new, EntityClassification.MISC)
							.size(0.2f, 0.2f)
							.build(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "killer_queen_punch").toString()));
}