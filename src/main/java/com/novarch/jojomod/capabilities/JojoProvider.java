package com.novarch.jojomod.capabilities;

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
    private LazyOptional<IStand> holder = LazyOptional.of(StandCapability::new);

    public boolean hasCapability(Capability<?> capability, Direction side)
	{
		return capability == STAND;
	}

	@Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
	{
		return capability == STAND ? holder.cast() : LazyOptional.empty();
    }

	@Override
	public INBT serializeNBT()
	{
		return STAND.getStorage().writeNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")),null);
	}

	@Override
	public void deserializeNBT(INBT nbt)
	{
		STAND.getStorage().readNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")),null, nbt);
	}

	public static IStand get(PlayerEntity player)
	{
		return player.getCapability(STAND, null).orElse(new StandCapability());
	}
}
