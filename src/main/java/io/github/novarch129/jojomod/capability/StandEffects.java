package io.github.novarch129.jojomod.capability;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.network.message.server.SSyncStandEffectsCapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.github.novarch129.jojomod.util.Util.Null;

public class StandEffects implements IStandEffects, ICapabilitySerializable<INBT> {
    @CapabilityInject(StandEffects.class)
    public static final Capability<StandEffects> STAND_EFFECTS = Null();
    private final Entity entity;
    private boolean aging;
    private boolean crimson;
    private LazyOptional<StandEffects> holder = LazyOptional.of(() -> new StandEffects(getEntity()));

    public StandEffects(Entity entity) {
        this.entity = entity;
    }

    public static LazyOptional<StandEffects> getLazyOptional(Entity entity) {
        return entity.getCapability(STAND_EFFECTS);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandEffects.class, new Capability.IStorage<StandEffects>() {
            @Override
            public INBT writeNBT(Capability<StandEffects> capability, StandEffects instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putBoolean("aging", instance.aging);
                nbt.putBoolean("crimson", instance.crimson);
                return nbt;
            }

            @Override
            public void readNBT(Capability<StandEffects> capability, StandEffects instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.aging = compoundNBT.getBoolean("aging");
                instance.crimson = compoundNBT.getBoolean("crimson");
            }
        }, () -> new StandEffects(Null()));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STAND_EFFECTS ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return STAND_EFFECTS.getStorage().writeNBT(STAND_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        STAND_EFFECTS.getStorage().readNBT(STAND_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean isAging() {
        return aging;
    }

    @Override
    public void setAging(boolean aging) {
        this.aging = aging;
        onDataUpdated();
    }

    @Override
    public boolean isCrimson() {
        return crimson;
    }

    @Override
    public void setCrimson(boolean crimson) {
        this.crimson = crimson;
        onDataUpdated();
    }

    @Override
    public void onDataUpdated() {
        if (!entity.world.isRemote)
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new SSyncStandEffectsCapabilityPacket(this));
    }
}
