package io.github.novarch129.jojomod.item;

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
        if (!(entity instanceof PlayerEntity || world.isRemote)) return;
        PlayerEntity player = (PlayerEntity) entity;
        Stand.getLazyOptional(player).ifPresent(stand -> {
            final int random = world.rand.nextInt(Util.StandID.STANDS.length);
            int newStandID = standID == 0 ? Util.StandID.STANDS[random] : standID;
            if (stand.getStandID() == 0) {
                if (!player.isCreative())
                    stack.shrink(1);
                stand.setStandID(newStandID);
                stand.setStandOn(true);
                final AbstractStandEntity standEntity = Util.StandID.getStandByID(newStandID, world);
                if (standEntity != null) { //Can be null if Stand is The Emperor
                    standEntity.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
                    standEntity.setMasterUUID(player.getUniqueID());
                    standEntity.setMaster(player);
                    world.addEntity(standEntity);
                }
            } else if (stand.getStandID() == Util.StandID.GOLD_EXPERIENCE && stand.getAbilitiesUnlocked() == 1 && this.standID == 0) {
                stand.removeStand();
                if (!world.isRemote) {
                    Objects.requireNonNull(world.getServer()).getWorld(player.dimension).getEntities()
                            .filter(entity1 -> entity1 instanceof GoldExperienceEntity)
                            .filter(entity1 -> ((GoldExperienceEntity) entity1).getMaster() == player)
                            .forEach(Entity::remove);
                }
                stand.setStandID(Util.StandID.GER);
                stand.setStandOn(true);
                GoldExperienceRequiemEntity goldExperienceRequiem = new GoldExperienceRequiemEntity(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), world);
                goldExperienceRequiem.setLocationAndAngles(player.getPosX() + 0.1, player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
                goldExperienceRequiem.setMasterUUID(player.getUniqueID());
                goldExperienceRequiem.setMaster(player);
                world.addEntity(goldExperienceRequiem);
            } else if (stand.getStandID() == Util.StandID.KILLER_QUEEN && stand.getAbilitiesUnlocked() == 1 && this.standID == 0) {
                stand.addAbilityUnlocked(1);
                player.sendStatusMessage(new StringTextComponent("Your\u00A7e Killer Queen\u00A7f has obtained\u00A7e Bites the Dust!"), true);
            }
        });
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        Stand stand = Stand.getCapabilityFromPlayer(playerIn);
        if (!Stand.getLazyOptional(playerIn).isPresent()) return ActionResult.resultFail(stack);
        if (stand.getStandID() == 0 || ((stand.getStandID() == Util.StandID.GOLD_EXPERIENCE || stand.getStandID() == Util.StandID.KILLER_QUEEN) && stand.getAbilitiesUnlocked() == 1)) {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultSuccess(stack);
        } else if (stand.getStandID() != 0 && ((stand.getStandID() != Util.StandID.GOLD_EXPERIENCE || stand.getStandID() != Util.StandID.KILLER_QUEEN) && stand.getAbilitiesUnlocked() != 1)) {
            playerIn.sendMessage(new StringTextComponent("You already have a Stand!"));
            return ActionResult.resultFail(stack);
        }
        return ActionResult.resultPass(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (standID != 0) {
            if (!this.tooltip.equals("")) {
                String displayText = this.tooltip
                        .replaceAll("ABILITY1", Util.KeyCodes.ABILITY_1)
                        .replaceAll("ABILITY2", Util.KeyCodes.ABILITY_2)
                        .replaceAll("ABILITY3", Util.KeyCodes.ABILITY_3)
                        .replaceAll("ABILITYTOGGLE", Util.KeyCodes.ABILITY_TOGGLE)
                        .replaceAll("SWITCHACT", Util.KeyCodes.SWITCH_ACT);
                tooltip.add(new StringTextComponent(Util.isClientHoldingShift() ? displayText : "Hold\u00A7e Shift\u00A7f for more information!"));
            }
        } else
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
