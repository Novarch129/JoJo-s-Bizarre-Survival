package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.EntityDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;

import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = JojoBlockyAdventure.MOD_ID, bus = Bus.MOD)
public class EntityInit
{
	private int ID = 0;
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, JojoBlockyAdventure.MOD_ID);
	
	public static final RegistryObject<EntityType<EntityKingCrimson>> KING_CRIMSON = ENTITY_TYPES
			.register("king_crimson",
					() -> EntityType.Builder.<EntityKingCrimson>create(EntityKingCrimson::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "king_crimson").toString()));
	
	public static final RegistryObject<EntityType<EntityStandPunch.kingCrimson>> KING_CRIMSON_PUNCH = ENTITY_TYPES
			.register("king_crimson_punch", () -> EntityType.Builder.<EntityStandPunch.kingCrimson>create(EntityStandPunch.kingCrimson::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "king_crimson_punch").toString()));

	public static final RegistryObject<EntityType<EntityDirtyDeedsDoneDirtCheap>> D4C = ENTITY_TYPES
			.register("d4c",
					() -> EntityType.Builder.<EntityDirtyDeedsDoneDirtCheap>create(EntityDirtyDeedsDoneDirtCheap::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "d4c").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.dirtyDeedsDoneDirtCheap>> D4C_PUNCH = ENTITY_TYPES
			.register("d4c_punch", () -> EntityType.Builder.<EntityStandPunch.dirtyDeedsDoneDirtCheap>create(EntityStandPunch.dirtyDeedsDoneDirtCheap::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "d4c_punch").toString()));

	public static final RegistryObject<EntityType<EntityGoldExperience>> GOLD_EXPERIENCE = ENTITY_TYPES
			.register("gold_experience",
					() -> EntityType.Builder.<EntityGoldExperience>create(EntityGoldExperience::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "gold_experience").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.goldExperience>> GOLD_EXPERIENCE_PUNCH = ENTITY_TYPES
			.register("gold_experience_punch", () -> EntityType.Builder.<EntityStandPunch.goldExperience>create(EntityStandPunch.goldExperience::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "gold_experience_punch").toString()));

	public static final RegistryObject<EntityType<EntityMadeInHeaven>> MADE_IN_HEAVEN = ENTITY_TYPES
			.register("made_in_heaven",
					() -> EntityType.Builder.<EntityMadeInHeaven>create(EntityMadeInHeaven::new, EntityClassification.CREATURE)
							.size(1.2f, 2.7f)
							.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "made_in_heaven").toString()));

	public static final RegistryObject<EntityType<EntityStandPunch.madeInHeaven>> MADE_IN_HEAVEN_PUNCH = ENTITY_TYPES
			.register("made_in_heaven_punch", () -> EntityType.Builder.<EntityStandPunch.madeInHeaven>create(EntityStandPunch.madeInHeaven::new, EntityClassification.MISC)
					.size(0.2f, 0.2f)
					.build(new ResourceLocation(JojoBlockyAdventure.MOD_ID, "made_in_heaven_punch").toString()));
}