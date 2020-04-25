package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;

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
			
}