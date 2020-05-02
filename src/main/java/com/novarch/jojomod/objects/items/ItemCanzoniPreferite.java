package com.novarch.jojomod.objects.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

public class ItemCanzoniPreferite extends MusicDiscItem
{
    public ItemCanzoniPreferite(int comparatorValueIn, SoundEvent soundIn, Properties builder)
    {
        super(comparatorValueIn, soundIn, builder);
        this.setRegistryName("canzoni_preferite");
    }

    @Override
    public SoundEvent getSound()
    {
        return super.getSound();
    }
}
