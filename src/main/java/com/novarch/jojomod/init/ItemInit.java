package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBlockyAdventure;
import com.novarch.jojomod.JojoBlockyAdventure.JojoItemGroup;
import com.novarch.jojomod.objects.items.ItemStandArrow;
import com.novarch.jojomod.objects.items.ItemSummonKingCrimson;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class ItemInit
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JojoBlockyAdventure.MOD_ID);

	public static final RegistryObject<Item> stand_arrow =ITEMS.register("stand_arrow", () -> new ItemStandArrow(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));
	public static final RegistryObject<Item> summon_kc =ITEMS.register("summon_kc", () -> new ItemSummonKingCrimson(new Item.Properties().maxStackSize(1).group(JojoItemGroup.instance)));

}
