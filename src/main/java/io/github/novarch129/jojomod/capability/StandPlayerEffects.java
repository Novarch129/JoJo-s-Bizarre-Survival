package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.github.novarch129.jojomod.util.Util.Null;

public class StandPlayerEffects implements ICapabilitySerializable<INBT> {
    @CapabilityInject(StandPlayerEffects.class)
    public static final Capability<StandPlayerEffects> STAND_PLAYER_EFFECTS = Null();
    private final PlayerEntity player;
    private final NonNullList<ItemStack> mainInventory = NonNullList.withSize(36, ItemStack.EMPTY);
    private final NonNullList<ItemStack> armorInventory = NonNullList.withSize(4, ItemStack.EMPTY);
    private final NonNullList<ItemStack> offHandInventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private final NonNullList<ItemStack> enderChestInventory = NonNullList.withSize(27, ItemStack.EMPTY);
    private LazyOptional<StandPlayerEffects> holder = LazyOptional.of(() -> new StandPlayerEffects(getPlayer()));

    public StandPlayerEffects(PlayerEntity player) {
        this.player = player;
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandPlayerEffects.class, new Capability.IStorage<StandPlayerEffects>() {
            @Override
            public INBT writeNBT(Capability<StandPlayerEffects> capability, StandPlayerEffects instance, Direction side) {
                CompoundNBT compoundNBT = new CompoundNBT();
                ListNBT inventory = new ListNBT();
                ListNBT enderChestInventory = new ListNBT();
                for (int i = 0; i < instance.mainInventory.size(); i++)
                    if (!instance.mainInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.mainInventory.get(i).write(compoundnbt);
                        inventory.add(compoundnbt);
                    }
                for (int j = 0; j < instance.armorInventory.size(); j++)
                    if (!instance.armorInventory.get(j).isEmpty()) {
                        CompoundNBT compoundnbt1 = new CompoundNBT();
                        compoundnbt1.putByte("Slot", (byte) (j + 100));
                        instance.armorInventory.get(j).write(compoundnbt1);
                        inventory.add(compoundnbt1);
                    }
                for (int k = 0; k < instance.offHandInventory.size(); k++)
                    if (!instance.offHandInventory.get(k).isEmpty()) {
                        CompoundNBT compoundnbt2 = new CompoundNBT();
                        compoundnbt2.putByte("Slot", (byte) (k + 150));
                        instance.offHandInventory.get(k).write(compoundnbt2);
                        inventory.add(compoundnbt2);
                    }
                for (int l = 0; l < instance.enderChestInventory.size(); l++)
                    if (!instance.enderChestInventory.get(l).isEmpty()) {
                        CompoundNBT compoundnbt3 = new CompoundNBT();
                        compoundnbt3.putByte("Slot", (byte) l);
                        instance.enderChestInventory.get(l).write(compoundnbt3);
                        enderChestInventory.add(compoundnbt3);
                    }
                compoundNBT.put("inventory", inventory);
                compoundNBT.put("enderChestInventory", enderChestInventory);
                return compoundNBT;
            }

            @SuppressWarnings("ConstantConditions")
            @Override
            public void readNBT(Capability<StandPlayerEffects> capability, StandPlayerEffects instance, Direction side, INBT nbt) {
                ListNBT inventory = ((CompoundNBT) nbt).getList("inventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < inventory.size(); i++) {
                    CompoundNBT compoundnbt = inventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j >= 0 && j < instance.mainInventory.size())
                            instance.mainInventory.set(j, itemstack);
                        else if (j >= 100 && j < instance.armorInventory.size() + 100)
                            instance.armorInventory.set(j - 100, itemstack);
                        else if (j >= 150 && j < instance.offHandInventory.size() + 150)
                            instance.offHandInventory.set(j - 150, itemstack);
                }
                ListNBT enderChestInventory = ((CompoundNBT) nbt).getList("enderChestInventory", Constants.NBT.TAG_COMPOUND);
                for (int k = 0; k < enderChestInventory.size(); k++) {
                    CompoundNBT compoundnbt = enderChestInventory.getCompound(k);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        instance.enderChestInventory.set(j, itemstack);
                }
            }
        }, () -> new StandPlayerEffects(Null()));
    }

    public static LazyOptional<StandPlayerEffects> getLazyOptional(PlayerEntity player) {
        return player.getCapability(STAND_PLAYER_EFFECTS);
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public NonNullList<ItemStack> getMainInventory() {
        return mainInventory;
    }

    public NonNullList<ItemStack> getArmorInventory() {
        return armorInventory;
    }

    public NonNullList<ItemStack> getOffHandInventory() {
        return offHandInventory;
    }

    public NonNullList<ItemStack> getEnderChestInventory() {
        return enderChestInventory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STAND_PLAYER_EFFECTS ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return STAND_PLAYER_EFFECTS.getStorage().writeNBT(STAND_PLAYER_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        STAND_PLAYER_EFFECTS.getStorage().readNBT(STAND_PLAYER_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }
}
