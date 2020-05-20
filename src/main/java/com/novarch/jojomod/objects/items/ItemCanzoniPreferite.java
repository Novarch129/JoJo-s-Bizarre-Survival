package com.novarch.jojomod.objects.items;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemCanzoniPreferite extends MusicDiscItem
{
    public ItemCanzoniPreferite(int p_i230071_1_, Supplier<SoundEvent> p_i230071_2_, Properties p_i230071_3_)
    {
        super(p_i230071_1_, p_i230071_2_, p_i230071_3_);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        return super.onItemUse(context);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(this.getRecordDescription().applyTextStyle(TextFormatting.GRAY));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
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
