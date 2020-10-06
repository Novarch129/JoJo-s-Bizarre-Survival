package io.github.novarch129.jojomod.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
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

public class StandTileEntityEffects implements ICapabilitySerializable<INBT> {
    @CapabilityInject(StandTileEntityEffects.class)
    public static Capability<StandTileEntityEffects> STAND_TILE_ENTITY_EFFECTS = Null();
    private final TileEntity tileEntity;
    private LazyOptional<StandTileEntityEffects> holder = LazyOptional.of(() -> new StandTileEntityEffects(getTileEntity()));
    private NonNullList<ItemStack> chestInventory = NonNullList.withSize(27, ItemStack.EMPTY);
    private NonNullList<ItemStack> furnaceInventory = NonNullList.withSize(3, ItemStack.EMPTY);
    private NonNullList<ItemStack> brewingInventory = NonNullList.withSize(5, ItemStack.EMPTY);
    private NonNullList<ItemStack> barrelInventory = NonNullList.withSize(27, ItemStack.EMPTY);
    private NonNullList<ItemStack> dispenserInventory = NonNullList.withSize(9, ItemStack.EMPTY);
    private NonNullList<ItemStack> hopperInventory = NonNullList.withSize(5, ItemStack.EMPTY);
    private NonNullList<ItemStack> shulkerBoxInventory = NonNullList.withSize(27, ItemStack.EMPTY);

    public StandTileEntityEffects(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    public static LazyOptional<StandTileEntityEffects> getLazyOptional(TileEntity tileEntity) {
        return tileEntity.getCapability(STAND_TILE_ENTITY_EFFECTS);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandTileEntityEffects.class, new Capability.IStorage<StandTileEntityEffects>() {
            @Override
            public INBT writeNBT(Capability<StandTileEntityEffects> capability, StandTileEntityEffects instance, Direction side) {
                CompoundNBT compoundNBT = new CompoundNBT();
                ListNBT chestInventory = new ListNBT();
                for (int i = 0; i < instance.chestInventory.size(); i++)
                    if (!instance.chestInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.chestInventory.get(i).write(compoundnbt);
                        chestInventory.add(compoundnbt);
                    }
                compoundNBT.put("chestInventory", chestInventory);
                ListNBT furnaceInventory = new ListNBT();
                for (int i = 0; i < instance.furnaceInventory.size(); i++)
                    if (!instance.furnaceInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.furnaceInventory.get(i).write(compoundnbt);
                        furnaceInventory.add(compoundnbt);
                    }
                compoundNBT.put("furnaceInventory", furnaceInventory);
                ListNBT brewingInventory = new ListNBT();
                for (int i = 0; i < instance.brewingInventory.size(); i++)
                    if (!instance.brewingInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.brewingInventory.get(i).write(compoundnbt);
                        brewingInventory.add(compoundnbt);
                    }
                compoundNBT.put("brewingInventory", brewingInventory);
                ListNBT barrelInventory = new ListNBT();
                for (int i = 0; i < instance.barrelInventory.size(); i++)
                    if (!instance.barrelInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.barrelInventory.get(i).write(compoundnbt);
                        barrelInventory.add(compoundnbt);
                    }
                compoundNBT.put("barrelInventory", barrelInventory);
                ListNBT dispenserInventory = new ListNBT();
                for (int i = 0; i < instance.dispenserInventory.size(); i++)
                    if (!instance.dispenserInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.dispenserInventory.get(i).write(compoundnbt);
                        dispenserInventory.add(compoundnbt);
                    }
                compoundNBT.put("dispenserInventory", dispenserInventory);
                ListNBT hopperInventory = new ListNBT();
                for (int i = 0; i < instance.hopperInventory.size(); i++)
                    if (!instance.hopperInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.hopperInventory.get(i).write(compoundnbt);
                        hopperInventory.add(compoundnbt);
                    }
                compoundNBT.put("hopperInventory", hopperInventory);
                ListNBT shulkerBoxInventory = new ListNBT();
                for (int i = 0; i < instance.shulkerBoxInventory.size(); i++)
                    if (!instance.shulkerBoxInventory.get(i).isEmpty()) {
                        CompoundNBT compoundnbt = new CompoundNBT();
                        compoundnbt.putByte("Slot", (byte) i);
                        instance.shulkerBoxInventory.get(i).write(compoundnbt);
                        shulkerBoxInventory.add(compoundnbt);
                    }
                compoundNBT.put("shulkerBoxInventory", shulkerBoxInventory);
                return compoundNBT;
            }

            @Override
            public void readNBT(Capability<StandTileEntityEffects> capability, StandTileEntityEffects instance, Direction side, INBT nbt) {
                ListNBT listNBT = ((CompoundNBT) nbt).getList("chestInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < listNBT.size(); ++i) {
                    CompoundNBT compoundnbt = listNBT.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.chestInventory.size())
                            instance.chestInventory.set(j, itemstack);
                }
                ListNBT furnaceInventory = ((CompoundNBT) nbt).getList("furnaceInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < furnaceInventory.size(); ++i) {
                    CompoundNBT compoundnbt = furnaceInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.furnaceInventory.size())
                            instance.furnaceInventory.set(j, itemstack);
                }
                ListNBT brewingInventory = ((CompoundNBT) nbt).getList("brewingInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < brewingInventory.size(); ++i) {
                    CompoundNBT compoundnbt = brewingInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.brewingInventory.size())
                            instance.brewingInventory.set(j, itemstack);
                }
                ListNBT barrelInventory = ((CompoundNBT) nbt).getList("barrelInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < barrelInventory.size(); ++i) {
                    CompoundNBT compoundnbt = barrelInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.barrelInventory.size())
                            instance.barrelInventory.set(j, itemstack);
                }
                ListNBT dispenserInventory = ((CompoundNBT) nbt).getList("dispenserInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < dispenserInventory.size(); ++i) {
                    CompoundNBT compoundnbt = dispenserInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.dispenserInventory.size())
                            instance.dispenserInventory.set(j, itemstack);
                }
                ListNBT hopperInventory = ((CompoundNBT) nbt).getList("hopperInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < hopperInventory.size(); ++i) {
                    CompoundNBT compoundnbt = hopperInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.hopperInventory.size())
                            instance.hopperInventory.set(j, itemstack);
                }
                ListNBT shulkerBoxInventory = ((CompoundNBT) nbt).getList("shulkerBoxInventory", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < shulkerBoxInventory.size(); ++i) {
                    CompoundNBT compoundnbt = shulkerBoxInventory.getCompound(i);
                    int j = compoundnbt.getByte("Slot") & 255;
                    ItemStack itemstack = ItemStack.read(compoundnbt);
                    if (!itemstack.isEmpty())
                        if (j < instance.shulkerBoxInventory.size())
                            instance.shulkerBoxInventory.set(j, itemstack);
                }
            }
        }, () -> new StandTileEntityEffects(Null()));
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public NonNullList<ItemStack> getChestInventory() {
        return chestInventory;
    }

    public NonNullList<ItemStack> getFurnaceInventory() {
        return furnaceInventory;
    }

    public NonNullList<ItemStack> getBrewingInventory() {
        return brewingInventory;
    }

    public NonNullList<ItemStack> getBarrelInventory() {
        return barrelInventory;
    }

    public NonNullList<ItemStack> getDispenserInventory() {
        return dispenserInventory;
    }

    public NonNullList<ItemStack> getHopperInventory() {
        return hopperInventory;
    }

    public NonNullList<ItemStack> getShulkerBoxInventory() {
        return shulkerBoxInventory;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STAND_TILE_ENTITY_EFFECTS ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return STAND_TILE_ENTITY_EFFECTS.getStorage().writeNBT(STAND_TILE_ENTITY_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        STAND_TILE_ENTITY_EFFECTS.getStorage().readNBT(STAND_TILE_ENTITY_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }
}
