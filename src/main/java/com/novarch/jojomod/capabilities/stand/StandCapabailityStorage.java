package com.novarch.jojomod.capabilities.stand;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class StandCapabailityStorage implements IStorage<IStand>
{
    @Nullable
    @Override
	public INBT writeNBT(Capability<IStand> capability, IStand instance, Direction side)
	{
        CompoundNBT props = new CompoundNBT();
        props.putInt("standID", instance.getStandID());
        props.putInt("StandAct", instance.getStandAct());
        props.putBoolean("StandOn", instance.getStandOn());
        props.putInt("Cooldown", instance.getCooldown());
        props.putInt("Timeleft", instance.getTimeLeft());
        props.putBoolean("Ability", instance.getAbility());
        props.putString("Diavolo", instance.getDiavolo());
        return (INBT)props;
      }

      @Override
      public void readNBT(Capability<IStand> capability, IStand instance, Direction side, INBT nbt)
      {
        CompoundNBT propertyData = (CompoundNBT)nbt;
        instance.putStandID(propertyData.getInt("standID"));
        instance.putStandAct(propertyData.getInt("StandAct"));
        instance.putStandOn(propertyData.getBoolean("StandOn"));
        propertyData.getInt("Cooldown");
        propertyData.getInt("Timeleft");
        propertyData.getBoolean("Ability");
        instance.putDiavolo(propertyData.getString("Diavolo"));
      }
}
