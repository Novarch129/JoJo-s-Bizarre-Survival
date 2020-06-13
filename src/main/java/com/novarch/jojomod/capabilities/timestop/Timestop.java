package com.novarch.jojomod.capabilities.timestop;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.network.message.server.SSyncTimestopCapabilityPacket;
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
import javax.annotation.ParametersAreNonnullByDefault;

import static com.novarch.jojomod.util.JojoLibs.Null;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public class Timestop implements ITimestop, ICapabilitySerializable<INBT> {
    private Entity entity;
    private double posX = 0;
    private double posY = 0;
    private double posZ = 0;
    private double motionX = 0;
    private double motionY = 0;
    private double motionZ = 0;
    private float rotationYaw = 0;
    private float rotationPitch = 0;
    private float rotationYawHead = 0;
    private float fallDistance = 0;
    private int fuse = 0;
    private int fire = 0;

    @CapabilityInject(ITimestop.class)
    public static final Capability<ITimestop> TIMESTOP = Null();
    private LazyOptional<ITimestop> holder = LazyOptional.of(() -> new Timestop(entity));

    public Timestop(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public double getPosZ() {
        return posZ;
    }

    @Override
    public void setPosition(double posX, double posY, double posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        onDataUpdated();
    }

    @Override
    public double getMotionX() {
        return motionX;
    }

    @Override
    public double getMotionY() {
        return motionY;
    }

    @Override
    public double getMotionZ() {
        return motionZ;
    }

    @Override
    public void setMotion(double motionX, double motionY, double motionZ) {
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        onDataUpdated();
    }

    @Override
    public float getRotationYaw() {
        return rotationYaw;
    }

    @Override
    public float getRotationPitch() {
        return rotationPitch;
    }

    @Override
    public float getRotationYawHead() {
        return rotationYawHead;
    }

    @Override
    public void setRotation(float rotationYaw, float rotationPitch, float rotationYawHead) {
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.rotationYawHead = rotationYawHead;
        onDataUpdated();
    }

    @Override
    public float getFallDistance() {
        return fallDistance;
    }

    @Override
    public void setFallDistance(float fallDistance) {
        this.fallDistance = fallDistance;
        onDataUpdated();
    }

    @Override
    public int getFuse() {
        return fuse;
    }

    @Override
    public void setFuse(int fuse) {
        this.fuse = fuse;
        onDataUpdated();
    }

    @Override
    public int getFire() {
        return fire;
    }

    @Override
    public void setFire(int fire) {
        this.fire = fire;
        onDataUpdated();
    }

    @Override
    public void putPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void putPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public void putPosZ(double posZ) {
        this.posZ = posZ;
    }

    @Override
    public void putMotionX(double motionX) {
        this.motionX = motionX;
    }

    @Override
    public void putMotionY(double motionY) {
        this.motionY = motionY;
    }

    @Override
    public void putMotionZ(double motionZ) {
        this.motionZ = motionZ;
    }

    @Override
    public void putRotationYaw(float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }

    @Override
    public void putRotationPitch(float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }

    @Override
    public void putRotationYawHead(float rotationYawHead) {
        this.rotationYawHead = rotationYawHead;
    }

    @Override
    public void putFallDistance(float fallDistance) {
        this.fallDistance = fallDistance;
    }

    @Override
    public void putFuse(int fuse) {
        this.fuse = fuse;
    }

    @Override
    public void putFire(int fire) {
        this.fire = fire;
    }

    @Override
    public void onDataUpdated() {
        if(entity != null)
            if(!entity.world.isRemote)
                JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new SSyncTimestopCapabilityPacket(this));
    }

    @Override
    public void clear() {
        this.posX = 0;
        this.posY = 0;
        this.posZ = 0;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.rotationYaw = 0;
        this.rotationPitch = 0;
        this.rotationYawHead = 0;
        this.fallDistance = 0;
        this.fuse = 0;
        this.fire = 0;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        return capability == TIMESTOP ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return TIMESTOP.getStorage().writeNBT(TIMESTOP, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        TIMESTOP.getStorage().readNBT(TIMESTOP, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null, nbt);
    }

    public static ITimestop getCapabilityFromEntity(Entity entity) {
        return entity.getCapability(TIMESTOP, null).orElse(new Timestop(entity));
    }

    public static LazyOptional<ITimestop> getLazyOptional(Entity entity) {
        return entity.getCapability(TIMESTOP, null);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(ITimestop.class, new Capability.IStorage<ITimestop>() {
            @Override
            public INBT writeNBT(Capability<ITimestop> capability, ITimestop instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putDouble("posX", instance.getPosX());
                nbt.putDouble("posY", instance.getPosY());
                nbt.putDouble("posZ", instance.getPosZ());
                nbt.putDouble("motionX", instance.getMotionX());
                nbt.putDouble("motionY", instance.getMotionY());
                nbt.putDouble("motionZ", instance.getMotionZ());
                nbt.putFloat("rotationYaw", instance.getRotationYaw());
                nbt.putFloat("rotationPitch", instance.getRotationPitch());
                nbt.putFloat("rotationYawHead", instance.getRotationYawHead());
                nbt.putFloat("fallDistance", instance.getFallDistance());
                nbt.putInt("fuse", instance.getFuse());
                nbt.putInt("fire", instance.getFire());
                return nbt;
            }

            @Override
            public void readNBT(Capability<ITimestop> capability, ITimestop instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.putPosX(compoundNBT.getDouble("posX"));
                instance.putPosY(compoundNBT.getDouble("posY"));
                instance.putPosZ(compoundNBT.getDouble("posZ"));
                instance.putMotionX(compoundNBT.getDouble("motionX"));
                instance.putMotionY(compoundNBT.getDouble("motionY"));
                instance.putMotionZ(compoundNBT.getDouble("motionZ"));
                instance.putRotationYaw(compoundNBT.getFloat("rotationYaw"));
                instance.putRotationPitch(compoundNBT.getFloat("rotationPitch"));
                instance.putRotationYawHead(compoundNBT.getFloat("rotationYawHead"));
                instance.putFallDistance(compoundNBT.getInt("fallDistance"));
                instance.putFuse(compoundNBT.getInt("fuse"));
                instance.putFire(compoundNBT.getInt("fire"));
            }
        }, () -> new Timestop(Null()));
    }
}
