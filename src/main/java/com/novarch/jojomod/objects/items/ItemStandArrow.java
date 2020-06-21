package com.novarch.jojomod.objects.items;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.fakePlayer.FakePlayerEntity;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemStandArrow extends Item
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		if(!this.tooltip.equals(""))
			tooltip.add(new StringTextComponent(this.tooltip));
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
	{
		return enchantment.isAllowedOnBooks() && stack.hasEffect();
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
		Stand.getLazyOptional(player).ifPresent(props -> {
			final int random = world.rand.nextInt(Util.StandID.numberOfStands);
			int newStandID;
			if(standID == 0)
				newStandID = Util.StandID.stands[random];
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
				props.setStandOn(true);
				final EntityStandBase theStand = Util.getStand(newStandID, world);
				if (theStand != null) {
					theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
					theStand.setMaster(player);
					theStand.setMasterUUID(player.getUniqueID());
					if(this.standID == Util.StandID.aerosmith)
					{
						FakePlayerEntity fakePlayer = new FakePlayerEntity(player.world, player);
						fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
						world.addEntity(fakePlayer);
					}
					world.addEntity(theStand);
					theStand.playSpawnSound();
				}
			}
			else if(props.getStandID() == Util.StandID.goldExperience && this.standID == 0)
			{
				props.removeStand();
				if(!world.isRemote) {
					Objects.requireNonNull(world.getServer()).getWorld(player.dimension).getEntities()
							.filter(entity1 -> entity1 instanceof EntityGoldExperience)
							.filter(entity1 -> ((EntityGoldExperience) entity1).getMaster() == player)
							.forEach(Entity::remove);
				}

				props.setStandID(Util.StandID.GER);
				props.setStandOn(true);
				final EntityStandBase theStand = Util.getStand(Util.StandID.GER, world);
				if (theStand != null) {
					theStand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
					theStand.setMaster(player);
					theStand.setMasterUUID(player.getUniqueID());
					world.addEntity(theStand);
					theStand.playSpawnSound();
				}
			}
		});
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		final ItemStack stack = playerIn.getHeldItem(handIn);
            IStand props = Stand.getCapabilityFromPlayer(playerIn);
        	if (props.getStandID() == 0 || props.getStandID() == Util.StandID.goldExperience) {
        		playerIn.setActiveHand(handIn);
        		return new ActionResult<>(ActionResultType.SUCCESS, stack);
        	} else
				playerIn.sendMessage(new StringTextComponent("You already have a Stand!"));
        	return new ActionResult<>(ActionResultType.PASS, stack);
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
