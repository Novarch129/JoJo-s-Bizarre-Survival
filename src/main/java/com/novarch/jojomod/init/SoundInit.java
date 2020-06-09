package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit
{
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, JojoBizarreSurvival.MOD_ID);

	public static final RegistryObject<SoundEvent> CANZONI_PREFERITE = SOUNDS.register("canzoni_preferite", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "canzoni_preferite")));

	public static final RegistryObject<SoundEvent> PUNCH_MISS = SOUNDS.register("punch_miss", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "punch_miss")));
	public static final RegistryObject<SoundEvent> SPAWN_KING_CRIMSON = SOUNDS.register("spawn_king_crimson", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_king_crimson")));
	public static final RegistryObject<SoundEvent> SPAWN_D4C = SOUNDS.register("spawn_d4c", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_d4c")));
	public static final RegistryObject<SoundEvent> SPAWN_GOLD_EXPERIENCE = SOUNDS.register("spawn_gold_experience", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_gold_experience")));
	public static final RegistryObject<SoundEvent> MUDAGIORNO = SOUNDS.register("mudagiorno", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "mudagiorno")));
	public static final RegistryObject<SoundEvent> TRANSMUTE = SOUNDS.register("transmute", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "transmute")));
	public static final RegistryObject<SoundEvent> SPAWN_MADE_IN_HEAVEN = SOUNDS.register("spawn_made_in_heaven", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_made_in_heaven")));
	public static final RegistryObject<SoundEvent> SPAWN_GER = SOUNDS.register("spawn_ger", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_ger")));
	public static final RegistryObject<SoundEvent> SPAWN_AEROSMITH = SOUNDS.register("spawn_aerosmith", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_aerosmith")));
	public static final RegistryObject<SoundEvent> VOLARUSH = SOUNDS.register("volarush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "volarush")));
	public static final RegistryObject<SoundEvent> SPAWN_WEATHER_REPORT = SOUNDS.register("spawn_weather_report", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_weather_report")));
	public static final RegistryObject<SoundEvent> SPAWN_KILLER_QUEEN = SOUNDS.register("spawn_killer_queen", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_killer_queen")));
	public static final RegistryObject<SoundEvent> LOOK_HERE = SOUNDS.register("look_here", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "look_here")));
	public static final RegistryObject<SoundEvent> SPAWN_CRAZY_DIAMOND = SOUNDS.register("spawn_crazy_diamond", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_crazy_diamond")));
	public static final RegistryObject<SoundEvent> DORARUSH = SOUNDS.register("dorarush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "dorarush")));
	public static final RegistryObject<SoundEvent> SPAWN_PURPLE_HAZE = SOUNDS.register("spawn_purple_haze", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_purple_haze")));
	public static final RegistryObject<SoundEvent> PURPLE_HAZE_RUSH = SOUNDS.register("purple_haze_rush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "purple_haze_rush")));
	public static final RegistryObject<SoundEvent> SPAWN_THE_EMPEROR = SOUNDS.register("spawn_the_emperor", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_the_emperor")));
	public static final RegistryObject<SoundEvent> SPAWN_WHITESNAKE = SOUNDS.register("spawn_whitesnake", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_whitesnake")));
	public static final RegistryObject<SoundEvent> SPAWN_CMOON = SOUNDS.register("spawn_cmoon", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_cmoon")));
}
