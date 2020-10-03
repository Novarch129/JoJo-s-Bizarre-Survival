package io.github.novarch129.jojomod.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.github.novarch129.jojomod.util.Util.Null;

public class StandItemEffects implements ICapabilitySerializable<INBT> {
    @CapabilityInject(StandItemEffects.class)
    public static final Capability<StandItemEffects> STAND_ITEM_EFFECTS = Null();
    private LazyOptional<StandItemEffects> holder = LazyOptional.of(StandItemEffects::new);
    private boolean isErasable;

    public static LazyOptional<StandItemEffects> getLazyOptional(ItemStack stack) {
        return stack.getCapability(STAND_ITEM_EFFECTS);
    }

    public static StandItemEffects getCapabilityFromItem(ItemStack stack) {
        return stack.getCapability(STAND_ITEM_EFFECTS).orElse(new StandItemEffects());
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandItemEffects.class, new Capability.IStorage<StandItemEffects>() {
            @Override
            public INBT writeNBT(Capability<StandItemEffects> capability, StandItemEffects instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putBoolean("isErasable", instance.isErasable);
                return nbt;
            }

            @Override
            public void readNBT(Capability<StandItemEffects> capability, StandItemEffects instance, Direction side, INBT inbt) {
                CompoundNBT nbt = (CompoundNBT) inbt;
                instance.isErasable = nbt.getBoolean("isErasable");
            }
        }, StandItemEffects::new);
    }

    public boolean isErasable() {
        return isErasable;
    }

    public void setErasable(boolean erasable) {
        isErasable = erasable;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STAND_ITEM_EFFECTS ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return STAND_ITEM_EFFECTS.getStorage().writeNBT(STAND_ITEM_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        STAND_ITEM_EFFECTS.getStorage().readNBT(STAND_ITEM_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }
}
