package com.novarch.jojomod.init;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.StevesBizarreSurvival.JojoItemGroup;
import com.novarch.jojomod.objects.items.*;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, StevesBizarreSurvival.MOD_ID);

	public static final RegistryObject<Item> stand_arrow = ITEMS.register("stand_arrow", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> summon_kc = ITEMS.register("summon_kc", () -> new ItemSummonKingCrimson(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> summon_d4c = ITEMS.register("summon_d4c", () -> new ItemSummonD4C(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> summon_ge = ITEMS.register("summon_ge", () -> new ItemSummonGoldExperience(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> summon_mih = ITEMS.register("summon_mih", () -> new ItemSummonMadeInHeaven(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	//public static final RegistryObject<Item> canzoni_preferite = ITEMS.register("canzoni_preferite", () -> new ItemCanzoniPreferite(10, SoundInit.CANZONI_PREFERITE.get(), new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
}
