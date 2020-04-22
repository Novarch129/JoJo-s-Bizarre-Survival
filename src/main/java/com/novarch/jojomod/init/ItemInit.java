package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoMod;
import com.novarch.jojomod.JojoMod.JojoItemGroup;
import com.novarch.jojomod.objects.items.ItemStandArrow;
import com.novarch.jojomod.objects.items.ItemSummonKingCrimson;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = JojoMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(JojoMod.MOD_ID)
public class ItemInit
{
	public static final Item stand_arrow = null;
	public static final Item summon_kc = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			(new ItemStandArrow(new Item.Properties()
				.maxStackSize(1)
					.group(JojoItemGroup.instance))
						.setRegistryName("stand_arrow")),
		
			(new ItemSummonKingCrimson(new Item.Properties()
				.maxStackSize(1)
					.group(JojoItemGroup.instance))
						.setRegistryName("summon_kc"))
		);
	}	
}
