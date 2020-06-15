package com.novarch.jojomod.capabilities.stand;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.network.message.server.SSyncStandCapabilityPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
import static com.novarch.jojomod.util.JojoLibs.StandID.cMoon;
import static com.novarch.jojomod.util.JojoLibs.StandID.madeInHeaven;

/**
 * The {@link Capability} used for storing the player's Stand ability.
 */
@ParametersAreNonnullByDefault
public class Stand implements IStand, ICapabilitySerializable<INBT> {
	private final PlayerEntity player;
	private int standID = 0;
	private int standAct = 0;
	private boolean standOn = false;
	private double cooldown = 0;
	private double timeleft = 1000;
	private String diavolo = "";
	private boolean ability = true;
	private int transformed = 0;
	private boolean noClip = false;

	@CapabilityInject(IStand.class)
	public static final Capability<IStand> STAND = Null();
	private LazyOptional<IStand> holder = LazyOptional.of(() -> new Stand(getPlayer()));

	public Stand(@Nonnull PlayerEntity player) {
		this.player = player;
	}

	@Override
	public PlayerEntity getPlayer() {
		return this.player;
	}

	@Override
	public int getStandID() {
		return this.standID;
	}

	@Override
	public void setStandID(int value) {
		this.standID = value;
		onDataUpdated();
	}

	@Override
	public int getAct() {
		return this.standAct;
	}

	@Override
	public void setAct(int value) {
		this.standAct = value;
		onDataUpdated();
	}

	@Override
	public void changeAct() {
		standAct++;
		if(standAct == getMaxAct()) {
			standAct = 0;
			setStandOn(false);
		}
		onDataUpdated();
	}

	@Override
	public boolean hasAct() {
		return getStandID() == madeInHeaven || getStandID() == cMoon;
	}

	@Override
	public int getMaxAct() {
		switch(standID) {
			case madeInHeaven:
				return 3;
			case cMoon:
				return 2;
		}
		return 0;
	}

	@Override
	public boolean getStandOn() {
		return this.standOn;
	}

	@Override
	public void setStandOn(boolean value) {
		this.standOn = value;
		onDataUpdated();
	}

	@Override
	public double getCooldown() {
		return this.cooldown;
	}

	@Override
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
		onDataUpdated();
	}

	@Override
	public void subtractCooldown(double subtraction) {
		this.cooldown -= subtraction;
		onDataUpdated();
	}

	@Override
	public double getTimeLeft() {
		return this.timeleft;
	}

	@Override
	public void setTimeLeft(double timeleft) {
		this.timeleft = timeleft;
		onDataUpdated();
	}

	@Override
	public void addTimeLeft(double addition) {
		this.timeleft += addition;
		onDataUpdated();
	}

	@Override
	public void subtractTimeLeft(double subtraction) {
		this.timeleft -= subtraction;
		onDataUpdated();
	}

	@Override
	public String getDiavolo() {
		return this.diavolo;
	}

	@Override
	public void setDiavolo(String truth) {
		this.diavolo = truth;
		onDataUpdated();
	}

	@Override
	public boolean getAbility() {
		return this.ability;
	}

	@Override
	public void setAbility(boolean value) {
		this.ability = value;
		onDataUpdated();
	}

	@Override
	public int getTransformed() {
		return this.transformed;
	}

	@Override
	public void setTransformed(int value) {
		this.transformed = value;
		onDataUpdated();
	}

	@Override
	public void addTransformed(int addition) {
		this.transformed += addition;
		onDataUpdated();
	}

	@Override
	public boolean getNoClip() {
		return noClip;
	}

	@Override
	public void setNoClip(boolean noClip) {
		this.noClip = noClip;
		onDataUpdated();
	}

	@Override
	public void putStandID(int standID) {
		this.standID = standID;
	}

	@Override
	public void putAct(int standAct) {
		this.standAct = standAct;
	}

	@Override
	public void putStandOn(boolean standOn) {
		this.standOn = standOn;
	}

	@Override
	public void putTimeLeft(double timeleft) {
		this.timeleft = timeleft;
	}

	@Override
	public void putCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public void putAbility(boolean ability) {
		this.ability = ability;
	}

	@Override
	public void putTransformed(int transformed) {
		this.transformed = transformed;
	}

	@Override
	public void putDiavolo(String truth) {
		this.diavolo = truth;
	}

	@Override
	public void putNoClip(boolean noClip) {
		this.noClip = noClip;
	}

	public void clone(IStand props) {
		setStandID(props.getStandID());
		setAct(props.getAct());
		setStandOn(false);
		setCooldown(props.getCooldown());
		setTimeLeft(props.getTimeLeft());
		setTransformed(props.getTransformed());
		setDiavolo(props.getDiavolo());
		setAbility(props.getAbility());
	}

	@Override
	public void removeStand() {
		setStandOn(false);
		setAct(0);
		setStandID(0);
		setCooldown(0);
		setTimeLeft(1000);
		setTransformed(0);
		setDiavolo("");
		setAbility(true);
		setNoClip(false);
	}

	/**
	 * Called to update the {@link Capability} to the client.
	 */
	@Override
	public void onDataUpdated() {
		if (!player.world.isRemote)
			JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new SSyncStandCapabilityPacket(this));
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		return capability == Stand.STAND ? holder.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return Stand.STAND.getStorage().writeNBT(Stand.STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		Stand.STAND.getStorage().readNBT(Stand.STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty.")), null, nbt);
	}

	public static IStand getCapabilityFromPlayer(PlayerEntity player) {
		return player.getCapability(STAND, null).orElse(new Stand(player));
	}

	public static LazyOptional<IStand> getLazyOptional(PlayerEntity player) {
		return player.getCapability(STAND, null);
	}

	public static void register() {
		CapabilityManager.INSTANCE.register(IStand.class, new Capability.IStorage<IStand>() {
			@Nonnull
			@Override
			public INBT writeNBT(Capability<IStand> capability, IStand instance, Direction side) {
				CompoundNBT props = new CompoundNBT();
				props.putInt("StandID", instance.getStandID());
				props.putInt("StandAct", instance.getAct());
				props.putBoolean("StandOn", instance.getStandOn());
				props.putDouble("Cooldown", instance.getCooldown());
				props.putDouble("Timeleft", instance.getTimeLeft());
				props.putBoolean("Ability", instance.getAbility());
				props.putInt("Transformed", instance.getTransformed());
				props.putString("Diavolo", instance.getDiavolo());
				props.putBoolean("NoClip", instance.getNoClip());
				return props;
			}

			@Override
			public void readNBT(Capability<IStand> capability, IStand instance, Direction side, INBT nbt) {
				CompoundNBT propertyData = (CompoundNBT) nbt;
				instance.putStandID(propertyData.getInt("StandID"));
				instance.putAct(propertyData.getInt("StandAct"));
				instance.putStandOn(propertyData.getBoolean("StandOn"));
				instance.putCooldown(propertyData.getDouble("Cooldown"));
				instance.putTimeLeft(propertyData.getDouble("Timeleft"));
				instance.putAbility(propertyData.getBoolean("Ability"));
				instance.putTransformed(propertyData.getInt("Transformed"));
				instance.putDiavolo(propertyData.getString("Diavolo"));
				instance.putNoClip(propertyData.getBoolean("NoClip"));
			}
		}, () -> new Stand(Null()));
	}
}
