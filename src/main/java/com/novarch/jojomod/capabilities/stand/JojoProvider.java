package com.novarch.jojomod.capabilities.stand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class JojoProvider implements ICapabilitySerializable<INBT> {
	private PlayerEntity player;

	public JojoProvider(PlayerEntity player) {
		this.player = player;
	}

	@CapabilityInject(IStand.class)
	public static final Capability<IStand> STAND = Null();
	private LazyOptional<IStand> holder = LazyOptional.of(() -> new StandCapability(player));

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, Direction side) {
		return capability == STAND ? holder.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return STAND.getStorage().writeNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		STAND.getStorage().readNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null, nbt);
	}

	public static IStand getCapabilityFromPlayer(PlayerEntity player) {
		return player.getCapability(STAND, null).orElse(new StandCapability(player));
	}

	public static LazyOptional<IStand> getLazyOptional(PlayerEntity player) {
		return player.getCapability(STAND, null);
	}

	/**
	 * Used to suppress warnings saying that the STAND {@link Capability} is {@code null}
	 * Based on diesieben07's solution <a href="http://www.minecraftforge.net/forum/topic/60980-solved-disable-%E2%80%9Cconstant-conditions-exceptions%E2%80%9D-inspection-for-field-in-intellij-idea/?do=findCommentcomment=285024">here</a>.
	 *
	 * @return null
	 */
	@Nonnull
	@SuppressWarnings({"ConstantConditions"})
	private static <T> T Null() {
		return null;
	}
}
