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
	public static final RegistryObject<SoundEvent> PUNCH_MISS = SOUNDS.register("knife_swing_miss1", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "knife_swing_miss1")));
	public static final RegistryObject<SoundEvent> DORARUSH = SOUNDS.register("dorarush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "dorarush")));
	public static final RegistryObject<SoundEvent> LAST_ORA = SOUNDS.register("last_ora", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "last_ora")));
	public static final RegistryObject<SoundEvent> LAST_ORA_KC = SOUNDS.register("last_ora_kc", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "last_ora_kc")));
	public static final RegistryObject<SoundEvent> ORA = SOUNDS.register("ora", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "ora")));
	public static final RegistryObject<SoundEvent> ORARUSH = SOUNDS.register("orarush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "orarush")));
	public static final RegistryObject<SoundEvent> MUDARUSH = SOUNDS.register("mudarush", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "mudarush")));
	public static final RegistryObject<SoundEvent> STOPTIME = SOUNDS.register("stoptime", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stoptime")));
	public static final RegistryObject<SoundEvent> MUDAGIORNO = SOUNDS.register("mudagiorno", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "mudagiorno")));
	public static final RegistryObject<SoundEvent> TRANSMUTE = SOUNDS.register("transmute", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "transmute")));
	public static final RegistryObject<SoundEvent> SPAWN_CD = SOUNDS.register("spawn_cd", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_cd")));
	public static final RegistryObject<SoundEvent> SPAWN_KC = SOUNDS.register("spawn_kc", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_kc")));
	public static final RegistryObject<SoundEvent> SPAWN_D4C = SOUNDS.register("spawn_d4c", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_d4c")));
	public static final RegistryObject<SoundEvent> SPAWN_MIH = SOUNDS.register("spawn_mih", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_mih")));
	public static final RegistryObject<SoundEvent> SPAWN_WR = SOUNDS.register("spawn_wr", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_wr")));
	public static final RegistryObject<SoundEvent> SPAWN_GE = SOUNDS.register("spawn_ge", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_ge")));
	public static final RegistryObject<SoundEvent> SPAWN_GER = SOUNDS.register("spawn_ger", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_ger")));
	public static final RegistryObject<SoundEvent> SPAWN_TW = SOUNDS.register("spawn_tw", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_tw")));
	public static final RegistryObject<SoundEvent> SPAWN_KQ = SOUNDS.register("spawn_kq", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_kq")));
	public static final RegistryObject<SoundEvent> SPAWN_SHA = SOUNDS.register("spawn_sha", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_sha")));
	public static final RegistryObject<SoundEvent> LOOK_HERE = SOUNDS.register("look_here", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "look_here")));
	public static final RegistryObject<SoundEvent> SPAWN_SF = SOUNDS.register("spawn_sf", () -> new SoundEvent(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "spawn_sf")));
}
