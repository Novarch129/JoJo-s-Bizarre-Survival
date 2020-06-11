package com.novarch.jojomod.objects.items;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
public class ItemCanzoniPreferite extends MusicDiscItem
{
    public ItemCanzoniPreferite(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder)
    {
        super(comparatorValue, soundSupplier, builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(this.getRecordDescription().applyTextStyle(TextFormatting.GRAY));
    }

    @Override
    public ITextComponent getRecordDescription()
    {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }

    @Override
    public SoundEvent getSound()
    {
        return super.getSound();
    }
}
