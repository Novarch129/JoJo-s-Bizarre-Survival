package com.novarch.jojomod.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class JojoProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(IStand.class)
    public static final Capability<IStand> STAND = null;
    IStand instance = STAND.getDefaultInstance();

    public boolean hasCapability(Capability<?> capability, Direction side)
	{
		return capability == STAND;
	}

	@Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
	{
		return STAND.orEmpty(capability, LazyOptional.of(() -> instance));
    }

	@Override
	public INBT serializeNBT()
	{
		return STAND.getStorage().writeNBT(STAND, instance, null);
	}

	@Override
	public void deserializeNBT(INBT nbt)
	{
		STAND.getStorage().readNBT(STAND, instance,null, nbt);
	}

	public static IStand get(PlayerEntity player)
	{
		return player.getCapability(STAND, null).orElse(new StandCapability(player));
	}

	public static LazyOptional<IStand> getLazy(PlayerEntity player)
	{
		return player.getCapability(STAND, null);
	}
}
