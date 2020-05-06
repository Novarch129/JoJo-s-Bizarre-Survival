package com.novarch.jojomod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class IStandStorage implements IStorage<IStand>
{
	public INBT writeNBT(Capability<IStand> capability, IStand instance, Direction side)
	{
        CompoundNBT props = new CompoundNBT();
        props.putInt("standID", instance.getStandID());
        props.putInt("StandAct", instance.getStandAct());
        props.putBoolean("StandOn", instance.getStandOn());
        props.putBoolean("PowerOn", instance.getPowerSpawned());
        props.putInt("PowerID", instance.getJojoPower());
        props.putInt("Cooldown", instance.getCooldown());
        props.putInt("Timeleft", instance.getTimeLeft());
        props.putBoolean("Ability", instance.getAbility());
        props.putString("Diavolo", instance.getDiavolo());
        return (INBT)props;
      }

      public void readNBT(Capability<IStand> capability, IStand instance, Direction side, INBT nbt)
      {
        CompoundNBT propertyData = (CompoundNBT)nbt;
        instance.setStandID(propertyData.getInt("standID"));
        instance.setStandAct(propertyData.getInt("StandAct"));
        instance.setStandOn(propertyData.getBoolean("StandOn"));
        instance.setPowerSpawned(propertyData.getBoolean("PowerOn"));
        instance.setJojoPower(propertyData.getInt("PowerID"));
        propertyData.getInt("Cooldown");
        propertyData.getInt("Timeleft");
        propertyData.getBoolean("Ability");
        instance.setDiavolo(propertyData.getString("Diavolo"));
      }
}
