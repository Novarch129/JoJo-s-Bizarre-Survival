package com.novarch.jojomod.init;

import com.novarch.jojomod.JojoBlockyAdventure;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = JojoBlockyAdventure.MOD_ID, bus = Bus.MOD)
@ObjectHolder(JojoBlockyAdventure.MOD_ID)
public class SoundInit 
{
	public static SoundEvent DORARUSH;
	  
	public static SoundEvent KNIFE_STAB;
	  
	public static SoundEvent KNIFE_SWING_MISS1;
	  
	public static SoundEvent KNIFE_SWING_MISS2;
	  
	public static SoundEvent LAST_ORA;
	
	public static SoundEvent LAST_ORA_KC;
	
	public static SoundEvent ORA;
	  
	public static SoundEvent ORARUSH;
	
	public static SoundEvent MUDARUSH;
	
	public static SoundEvent STOPTIME;
	
	public static SoundEvent MUDAGIORNO;
	
	public static SoundEvent TRANSMUTE;

	public static SoundEvent SPAWN_CD;

	public static SoundEvent SPAWN_KC;
	  
	public static SoundEvent SPAWN_D4C;
	
	public static SoundEvent SPAWN_MIH;
	
	public static SoundEvent SPAWN_WR;
	
	public static SoundEvent SPAWN_GE;
	
	public static SoundEvent SPAWN_GER;
	
	public static SoundEvent SPAWN_TW;
	
	public static void registerSounds()
	{
		DORARUSH = registerSound("dorarush");
		
		KNIFE_STAB = registerSound("knife_stab");
		
		KNIFE_SWING_MISS1 = registerSound("knife_swing_miss1");
		
		KNIFE_SWING_MISS2 = registerSound("knife_swing_miss2");
		
		LAST_ORA = registerSound("last_ora");
		
		LAST_ORA_KC = registerSound("last_ora_kc");
		
		ORA = registerSound("ora");
		
		ORARUSH = registerSound("orarush");
		
		MUDARUSH = registerSound("mudarush");
		
		STOPTIME = registerSound("stoptime");
		
		MUDAGIORNO = registerSound("mudagiorno");
		
		TRANSMUTE = registerSound("transmute");
		
		SPAWN_CD = registerSound("spawn_cd");
		
		SPAWN_KC = registerSound("spawn_kc");
		
		SPAWN_D4C = registerSound("spawn_d4c");
		
		SPAWN_MIH = registerSound("spawn_mih");
		
		SPAWN_WR = registerSound("spawn_wr");
		
		SPAWN_GE = registerSound("spawn_ge");
		
		SPAWN_GER = registerSound("spawn_ger");
		
		SPAWN_TW = registerSound("spawn_tw");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(JojoBlockyAdventure.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
