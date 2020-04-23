package com.novarch.jojomod.util.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
public interface IStand
{
    void setStandID(final int p0);

    void setStandAct(final int p0);

    void setStandOn(final boolean p0);

    void setJojoPower(final int p0);

    void setPowerSpawned(final boolean p0);

    void setPlayerStandName(final String p0);

    int getStandID();

    int getStandAct();

    boolean getStandOn();

    int getJojoPower();

    boolean getPowerSpawned();

    String getPlayerStandName();

    void setStandRemoved();

    void cloneSaveFunction(final IStand p0);
    
    void setCooldown(int new_cooldown);

    void addCooldown(int addition);

    void subtractCooldown(int subtraction);
    
    int getCooldown();

    PlayerEntity getDiavolo();

    void setDiavolo(PlayerEntity truth);

    class Storage implements Capability.IStorage<IStand>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IStand> capability, IStand instance, Direction side)
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("standID", instance.getStandID());
            nbt.putInt("standAct", instance.getStandAct());
            nbt.putBoolean("standOn", instance.getStandOn());
            nbt.putBoolean("powerOn", instance.getPowerSpawned());
            nbt.putInt("powerID", instance.getJojoPower());
            nbt.putInt("cooldown", instance.getCooldown());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IStand> capability, IStand instance, Direction side, INBT nbt)
        {
            CompoundNBT propertyData = new CompoundNBT();
            instance.setStandID(propertyData.getInt("standID"));
            instance.setStandAct(propertyData.getInt("standAct"));
            instance.setStandOn(propertyData.getBoolean("standOn"));
            instance.setPowerSpawned(propertyData.getBoolean("powerOn"));
            instance.setJojoPower(propertyData.getInt("powerID"));
            propertyData.getInt("cooldown");
        }
    }
}
