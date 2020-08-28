package io.github.novarch129.jojomod.capability;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.network.message.server.SSyncStandEffectsCapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

import static io.github.novarch129.jojomod.util.Util.Null;

public class StandEffects implements ICapabilitySerializable<INBT> {
    @CapabilityInject(StandEffects.class)
    public static final Capability<StandEffects> STAND_EFFECTS = Null();
    private final Entity entity;
    private boolean crimson;
    private boolean aging;
    private Vec3d motion = Vec3d.ZERO;
    private boolean bomb;
    private UUID standUser = UUID.fromString("c9362041-f5e8-447c-80a8-9db27a2646bb");
    private boolean rotating;
    private byte soundEffect;
    private boolean threeFreeze;
    private LazyOptional<StandEffects> holder = LazyOptional.of(() -> new StandEffects(getEntity()));

    public StandEffects(Entity entity) {
        this.entity = entity;
    }

    public static LazyOptional<StandEffects> getLazyOptional(Entity entity) {
        return entity.getCapability(STAND_EFFECTS);
    }

    public static StandEffects getCapabilityFromEntity(Entity entity) {
        return entity.getCapability(STAND_EFFECTS).orElse(new StandEffects(entity));
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandEffects.class, new Capability.IStorage<StandEffects>() {
            @Override
            public INBT writeNBT(Capability<StandEffects> capability, StandEffects instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putBoolean("crimson", instance.crimson);
                nbt.putBoolean("aging", instance.aging);
                nbt.putDouble("motionX", instance.motion.getX());
                nbt.putDouble("motionY", instance.motion.getY());
                nbt.putDouble("motionZ", instance.motion.getZ());
                nbt.putBoolean("bomb", instance.bomb);
                nbt.putUniqueId("standUser", instance.standUser);
                nbt.putBoolean("rotating", instance.rotating);
                nbt.putByte("soundEffect", instance.soundEffect);
                nbt.putBoolean("threeFreeze", instance.threeFreeze);
                return nbt;
            }

            @Override
            public void readNBT(Capability<StandEffects> capability, StandEffects instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.crimson = compoundNBT.getBoolean("crimson");
                instance.aging = compoundNBT.getBoolean("aging");
                instance.motion = new Vec3d(compoundNBT.getDouble("motionX"), compoundNBT.getDouble("motionY"), compoundNBT.getDouble("motionZ"));
                instance.bomb = compoundNBT.getBoolean("bomb");
                instance.standUser = compoundNBT.getUniqueId("standUser");
                instance.rotating = compoundNBT.getBoolean("rotating");
                instance.soundEffect = compoundNBT.getByte("soundEffect");
                instance.threeFreeze = compoundNBT.getBoolean("threeFreeze");
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

    public Entity getEntity() {
        return entity;
    }

    public boolean isAging() {
        return aging;
    }

    public void setAging(boolean aging) {
        this.aging = aging;
        onDataUpdated();
    }

    public Vec3d getMotion() {
        return motion;
    }

    public void setMotion(Vec3d motion) {
        this.motion = motion;
        onDataUpdated();
    }

    public boolean isCrimson() {
        return crimson;
    }

    public void setCrimson(boolean crimson) {
        this.crimson = crimson;
        onDataUpdated();
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public UUID getStandUser() {
        return standUser;
    }

    public void setStandUser(UUID standUser) {
        this.standUser = standUser;
        onDataUpdated();
    }

    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
        onDataUpdated();
    }

    public byte getSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(byte soundEffect) {
        this.soundEffect = soundEffect;
        onDataUpdated();
    }

    public boolean isThreeFreeze() {
        return threeFreeze;
    }

    public void setThreeFreeze(boolean threeFreeze) {
        this.threeFreeze = threeFreeze;
        onDataUpdated();
    }

    public void onDataUpdated() {
        if (!entity.world.isRemote)
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new SSyncStandEffectsCapabilityPacket(this));
    }
}
