package com.novarch.jojomod.objects.items;

import java.util.List;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.capabilities.stand.JojoProvider;
import com.novarch.jojomod.util.capabilities.stand.IStand;
import com.novarch.jojomod.util.capabilities.stand.IStandCapability;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtensibleEnum;
import net.minecraftforge.common.util.LazyOptional;

@SuppressWarnings("unused")
public class ItemStandArrow extends Item
{
	private boolean hasStand;
	private int standID;
		
	public ItemStandArrow(Properties properties) 
    {
		super(properties);
		this.hasStand = true;
		this.standID = 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public Item asItem()
	{
		return super.asItem();
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
	{
		if (enchantment.isAllowedOnBooks() && stack.hasEffect())
		{
			return true;
		}
		return false;
	}

	@Override
	public Rarity getRarity(ItemStack stack)
	{
		return Rarity.create("jojo", TextFormatting.YELLOW);
	}

	@Override
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft)
	{
		if (!world.isRemote && entity instanceof PlayerEntity) 
        {
            final PlayerEntity player = (PlayerEntity)entity;
            LazyOptional<IStand> stand = player.getCapability(JojoProvider.STAND, null);
            IStand props = stand.orElse(new IStandCapability());
            this.hasStand = true;													 
            if (props != null && props.getStandID() != 0) 
            {
                this.hasStand = false;
            }
            if (this.hasStand) 
            {
                int randomstand = 0;
                if (this.standID == 0)
                {
                    while (randomstand == 0) 
                    {
                        randomstand = ItemStandArrow.random.nextInt(JojoLibs.numberOfStands);
                    }
                }
                else 
                {
                    randomstand = this.standID;
                }
                if (randomstand != 0) 
                {
                    if (!player.isCreative()) 
                    {
                        stack.shrink(1);
                    }
                    props.setStandID(randomstand);
                    props.setStandOn(true);
                    final EntityStandBase theStand = JojoLibs.getStand(randomstand, world);
                    if (theStand != null) {
                        theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
                        theStand.setMaster(player.getCommandSource().getName());
                        theStand.setMastername(player.getDisplayName().toString());
                        world.addEntity((Entity)theStand);
                        theStand.spawnSound();
                        theStand.setGiveItems();
                    }
                }
            }
            else 
            {
                player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.standalready.txt", new Object[0]));
            }
	}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		final ItemStack stack = playerIn.getHeldItem(handIn);
		try {
			LazyOptional<IStand> stand = playerIn.getCapability(JojoProvider.STAND, null);
            IStand props = stand.orElse(new IStandCapability());
        if (stack == null) 
        {        	
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.FAIL, (Object)stack);
        }
        if (props.getStandID() == 0) 
        {
        	playerIn.sendMessage((ITextComponent)new TranslationTextComponent("Success " + String.valueOf(props.getStandID()), new Object[0]));
            playerIn.setActiveHand(handIn);
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, (Object)stack);
        }
        playerIn.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.standalready.txt", new Object[0]));
		} catch (ClassCastException cce) {playerIn.sendMessage((ITextComponent)new TranslationTextComponent("Exception!" + playerIn.getCapability(JojoProvider.STAND, null), new Object[0]));}
        return (ActionResult<ItemStack>) new ActionResult(ActionResultType.PASS, (Object)stack);
	}	
	
	public enum ModRarity implements IExtensibleEnum
	{
		JOJO(TextFormatting.YELLOW);

		public final TextFormatting color;

		private ModRarity(TextFormatting format)
		{
			this.color = format;
		}

		public static ModRarity create(String name, TextFormatting format)
		{
			throw new IllegalStateException("Enum not extended");
		}
	}
	
	@Override
	public int getUseDuration(final ItemStack itemStack) 
    {
        return 7200;
    }
    
	@Override
	public UseAction getUseAction(final ItemStack itemStack) 
    {
        return UseAction.BOW;
    }
	
	public int getItemEnchantability()
	{
		return 1;
	}
}
