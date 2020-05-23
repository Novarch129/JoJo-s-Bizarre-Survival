package com.novarch.jojomod.objects.items;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.util.JojoLibs;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemSummonWeatherReport extends Item
{
	public ItemSummonWeatherReport(Properties properties)
	{
		super(properties);
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
		return Rarity.create("weather_report", TextFormatting.WHITE);
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
			PlayerEntity player = (PlayerEntity) entity;
			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				if (props.getStandID() == 0)
				{
					if (!player.isCreative()) { stack.shrink(1); }

					props.setStandID(JojoLibs.StandID.weatherReport);
					props.setStandOn(true);
					final EntityStandBase theStand = JojoLibs.getStand(JojoLibs.StandID.weatherReport, world);
					if (theStand != null)
					{
						theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
						theStand.setMaster(player);
						theStand.setMastername(player.getDisplayName().toString());
						world.addEntity((Entity) theStand);
						theStand.spawnSound();
						theStand.setGiveItems();
					}
				} else { player.sendMessage((ITextComponent) new TranslationTextComponent("msg.jojomod.standalready.txt", new Object[0])); }
			});
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		final ItemStack stack = playerIn.getHeldItem(handIn);

		try
		{
            IStand props = JojoProvider.getCapabilityFromPlayer(playerIn);
        if (stack == null) 
        {        	
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.FAIL, stack);
        }

        if (props.getStandID() == 0) 
        {
            playerIn.setActiveHand(handIn);
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, stack);
        }
		} catch (ClassCastException cce) {playerIn.sendMessage(new StringTextComponent("Exception!" + playerIn.getCapability(JojoProvider.STAND)));}
			return (ActionResult<ItemStack>) new ActionResult(ActionResultType.PASS, stack);
	}
	
	@Override
	public int getUseDuration(final ItemStack itemStack) 
    {
        return 100;
    }
    
	@Override
	public UseAction getUseAction(final ItemStack itemStack) 
    {
        return UseAction.BOW;
    }

    @Override
	public int getItemEnchantability()
	{
		return 1;
	}
}
