package io.github.novarch129.jojomod.item;

import io.github.novarch129.jojomod.capability.IStand;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.StandArrowEntity;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceEntity;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceRequiemEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class StandArrowItem extends ArrowItem {
    final int standID;
    final String tooltip;

    public StandArrowItem(Properties properties, int standID, String tooltip) {
        super(properties);
        this.standID = standID;
        this.tooltip = tooltip;
    }

    @SuppressWarnings("ConstantConditions")
    @Override //TODO add an animation when obtaining GER
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) entity;
        Stand.getLazyOptional(player).ifPresent(props -> {
            final int random = world.rand.nextInt(Util.StandID.STANDS.length);
            int newStandID;
            if (standID == 0)
                newStandID = Util.StandID.STANDS[random];
            else
                newStandID = standID;
            if (props.getStandID() == 0) {
                if (!player.isCreative())
                    stack.shrink(1);
                props.setStandID(newStandID);
                props.setStandOn(true);
                final AbstractStandEntity stand = Util.getStandByID(newStandID, world);
                if (stand != null) { //Can be null if Stand is The Emperor
                    stand.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
                    stand.setMasterUUID(player.getUniqueID());
                    stand.setMaster(player);
                    world.addEntity(stand);
                }
            } else if (props.getStandID() == Util.StandID.GOLD_EXPERIENCE && this.standID == 0) {
                props.removeStand();
                if (!world.isRemote) {
                    Objects.requireNonNull(world.getServer()).getWorld(player.dimension).getEntities()
                            .filter(entity1 -> entity1 instanceof GoldExperienceEntity)
                            .filter(entity1 -> ((GoldExperienceEntity) entity1).getMaster() == player)
                            .forEach(Entity::remove);
                }
                props.setStandID(Util.StandID.GER);
                props.setStandOn(true);
                GoldExperienceRequiemEntity goldExperienceRequiem = new GoldExperienceRequiemEntity(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), world);
                goldExperienceRequiem.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
                goldExperienceRequiem.setMasterUUID(player.getUniqueID());
                goldExperienceRequiem.setMaster(player);
                world.addEntity(goldExperienceRequiem);
            }
        });
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        IStand props = Stand.getCapabilityFromPlayer(playerIn);
        if (!Stand.getLazyOptional(playerIn).isPresent()) return ActionResult.resultFail(stack);
        if (props.getStandID() == 0 || props.getStandID() == Util.StandID.GOLD_EXPERIENCE) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(stack);
        } else if (props.getStandID() != 0 && props.getStandID() != Util.StandID.GOLD_EXPERIENCE) {
            playerIn.sendMessage(new StringTextComponent("You already have a Stand!"));
            return ActionResult.resultFail(stack);
        }
        return ActionResult.resultPass(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (!this.tooltip.equals(""))
            tooltip.add(new StringTextComponent(this.tooltip));
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.isAllowedOnBooks();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 1;
    }

    @Override
    public UseAction getUseAction(final ItemStack itemStack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(final ItemStack itemStack) {
        return 7200;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity shooter) {
        return false;
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity entity) {
        return new StandArrowEntity(world, entity);
    }
}
