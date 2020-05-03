package com.novarch.jojomod.objects.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCanzoniPreferite extends MusicDiscItem
{
    public ItemCanzoniPreferite(int comparatorValueIn, SoundEvent soundIn, Properties builder)
    {
        super(comparatorValueIn, soundIn, builder);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.JUKEBOX && !blockstate.get(JukeboxBlock.HAS_RECORD)) {
            ItemStack itemstack = context.getItem();
            if (!world.isRemote) {
                ((JukeboxBlock)Blocks.JUKEBOX).insertRecord(world, blockpos, blockstate, itemstack);
                world.playEvent((PlayerEntity)null, 1010, blockpos, Item.getIdFromItem(this));
                itemstack.shrink(1);
                PlayerEntity playerentity = context.getPlayer();
                if (playerentity != null) {
                    playerentity.addStat(Stats.PLAY_RECORD);
                }
            }

            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(this.getRecordDescription().applyTextStyle(TextFormatting.GRAY));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ITextComponent getRecordDescription() {
        return new TranslationTextComponent(this.getTranslationKey() + ".desc");
    }


    @Override
    public SoundEvent getSound()
    {
        return super.getSound();
    }
}
