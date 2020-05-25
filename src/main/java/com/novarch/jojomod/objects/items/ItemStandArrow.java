package com.novarch.jojomod.objects.items;

import java.util.List;

import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.capabilities.stand.IStand;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public final class ItemStandArrow extends Item
{ // TODO add animation when obtaining GER
	final int standID;
	final String tooltip;

	public ItemStandArrow(Properties properties, int standID, String tooltip)
    {
		super(properties);
		this.standID = standID;
		this.tooltip = tooltip;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(!this.tooltip.equals(""))
			tooltip.add(new StringTextComponent(this.tooltip));
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
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft)
	{
		PlayerEntity player = (PlayerEntity)entity;
		JojoProvider.getLazyOptional(player).ifPresent(props -> {
			final int random = world.rand.nextInt(JojoLibs.numberOfStands);
			int newStandID;
			if(standID == 0)
				newStandID = JojoLibs.StandID.stands[random];
			else
				newStandID = standID;
			if (props.getStandID() == 0)
			{
				if (!player.isCreative())
				{
					stack.shrink(1);
				}
				if(standID == 0)
					props.setStandID(newStandID);
				else
					props.setStandID(standID);
				if(!player.isCreative())
					player.attackEntityFrom(DamageSource.MAGIC, 5.0f);
				props.setStandOn(true);
				final EntityStandBase theStand = JojoLibs.getStand(newStandID, world);
				if (theStand != null) {
					theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
					theStand.setMaster(player);
					theStand.setMastername(player.getDisplayName().toString());
					if(this.standID == JojoLibs.StandID.aerosmith)
					{
						FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
						fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
						world.addEntity(fakePlayer);
					}
					world.addEntity(theStand);
					theStand.spawnSound();
					theStand.setGiveItems();
				}
			}
			else if(props.getStandID() == JojoLibs.StandID.goldExperience && this.standID == 0)
			{
				props.removeStand();
				for(Entity entity1 : world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(400.0, 200.0 , 400.0)), EntityPredicates.NOT_SPECTATING))
				{
					if(entity1 instanceof EntityGoldExperience)
					{
						if(((EntityGoldExperience) entity1).getMaster() == player)
						{
							((EntityGoldExperience) entity1).setTransforming(true);
						}
					}
				}
				for(Entity entity1 : world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-400.0, -200.0 , -400.0)), EntityPredicates.NOT_SPECTATING))
				{
					if(entity1 instanceof EntityGoldExperience)
					{
						if(((EntityGoldExperience) entity1).getMaster() == player)
						{
							((EntityGoldExperience) entity1).setTransforming(true);
						}
					}
				}
				for(Entity entity1 : world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(-400.0, 200.0 , 400.0)), EntityPredicates.NOT_SPECTATING))
				{
					if(entity1 instanceof EntityGoldExperience)
					{
						if(((EntityGoldExperience) entity1).getMaster() == player)
						{
							((EntityGoldExperience) entity1).setTransforming(true);
						}
					}
				}
				for(Entity entity1 : world.getEntitiesInAABBexcluding(player, player.getBoundingBox().expand(new Vec3d(400.0, -200.0 , -400.0)), EntityPredicates.NOT_SPECTATING))
				{
					if(entity1 instanceof EntityGoldExperience)
					{
						if(((EntityGoldExperience) entity1).getMaster() == player)
						{
							((EntityGoldExperience) entity1).setTransforming(true);
						}
					}
				}
				props.setStandID(JojoLibs.StandID.GER);
				props.setStandOn(true);
				final EntityStandBase theStand = JojoLibs.getStand(JojoLibs.StandID.GER, world);
				if (theStand != null) {
					theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
					theStand.setMaster(player);
					theStand.setMastername(player.getDisplayName().toString());
					world.addEntity((Entity) theStand);
					theStand.spawnSound();
					theStand.setGiveItems();
				}
			}
			else
			{
				player.sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.standalready.txt", new Object[0]));
			}
		});
	}

	/*@Override
	public ActionResult onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		final ItemStack stack = playerIn.getHeldItem(handIn);
		try {
            IStand props = JojoProvider.getCapabilityFromPlayer(playerIn);
        if (props.getStandID() == 0 || props.getStandID() == JojoLibs.StandID.goldExperience)
        {
            playerIn.setActiveHand(handIn);
            return (ActionResult<ItemStack>)new ActionResult(ActionResultType.SUCCESS, stack);
        }
        playerIn.sendMessage(new TranslationTextComponent("msg.jojomod.standalready.txt", new Object[0]));
		} catch (ClassCastException cce) {playerIn.sendMessage(new StringTextComponent("Exception!" + playerIn.getCapability(JojoProvider.STAND, null)));}
        return new ActionResult(ActionResultType.PASS, stack);
	}*/
	
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
