package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.JojoBizarreSurvival.JojoItemGroup;
import com.novarch.jojomod.objects.items.*;

import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("unused")
public class ItemInit
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JojoBizarreSurvival.MOD_ID);

	public static final RegistryObject<Item> stand_arrow = ITEMS.register("stand_arrow", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("arrow", TextFormatting.YELLOW)).group(JojoItemGroup.instance), 0, "On use, grants the user the power of a STAND."));
	public static final RegistryObject<Item> summon_king_crimson = ITEMS.register("summon_king_crimson", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("king_crimson", TextFormatting.DARK_RED)).group(JojoItemGroup.instance), JojoLibs.StandID.kingCrimson, "Skips 10 seconds of time, allowing the user to see others movements."));
	public static final RegistryObject<Item> summon_d4c = ITEMS.register("summon_d4c", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("d4c", TextFormatting.AQUA)).group(JojoItemGroup.instance), JojoLibs.StandID.dirtyDeedsDoneDirtCheap, "Allows the user to hop to parallel worlds when between two objects."));
	public static final RegistryObject<Item> summon_gold_experience = ITEMS.register("summon_gold_experience", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("gold_experience", TextFormatting.GOLD)).group(JojoItemGroup.instance), JojoLibs.StandID.goldExperience, "Has the ability to turn objects into living creatures."));
	public static final RegistryObject<Item> summon_made_in_heaven = ITEMS.register("summon_made_in_heaven", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("made_in_heaven", TextFormatting.GREEN)).group(JojoItemGroup.instance), JojoLibs.StandID.madeInHeaven, "Possess the ability to accelerate the passage of time."));
	public static final RegistryObject<Item> summon_aerosmith = ITEMS.register("summon_aerosmith", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("aerosmith", TextFormatting.LIGHT_PURPLE)).group(JojoItemGroup.instance), JojoLibs.StandID.aerosmith, "Remote controlled Stand, allows the user to detect entities by their breathing."));
	public static final RegistryObject<Item> summon_weather_report = ITEMS.register("summon_weather_report", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("weather_report", TextFormatting.WHITE)).group(JojoItemGroup.instance), JojoLibs.StandID.weatherReport, "Possess the ability to control the weather, is there something more to this ability?"));
	public static final RegistryObject<Item> summon_killer_queen = ITEMS.register("summon_killer_queen", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("killer_queen", TextFormatting.DARK_PURPLE)).group(JojoItemGroup.instance), JojoLibs.StandID.killerQueen, "Can turn anything it touches into a bomb.\n\nControls: \n"+JojoLibs.KeyCodes.ability1+": Activate 1st bomb.\n"+JojoLibs.KeyCodes.ability2+": Summon Sheer Heart Attack."));
	public static final RegistryObject<Item> summon_crazy_diamond = ITEMS.register("summon_crazy_diamond", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).rarity(Rarity.create("crazy_diamond", TextFormatting.BLUE)).group(JojoItemGroup.instance), JojoLibs.StandID.crazyDiamond, "Has the ability to revert objects to a previous state."));

	public static final RegistryObject<Item> remove_stand = ITEMS.register("remove_stand", () -> new ItemRemoveStand(new Item.Properties().maxStackSize(1).rarity(Rarity.create("stand_disc", TextFormatting.GRAY)).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> canzoni_preferite = ITEMS.register("canzoni_preferite", () -> new ItemCanzoniPreferite(1, SoundInit.CANZONI_PREFERITE, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(JojoItemGroup.instance)));
}