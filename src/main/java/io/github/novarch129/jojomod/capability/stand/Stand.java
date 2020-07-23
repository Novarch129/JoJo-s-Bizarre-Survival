package io.github.novarch129.jojomod.capability.stand;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.network.message.server.SSyncStandCapabilityPacket;
import net.minecraft.entity.Entity;
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

import static io.github.novarch129.jojomod.util.Util.Null;
import static io.github.novarch129.jojomod.util.Util.StandID.CMOON;
import static io.github.novarch129.jojomod.util.Util.StandID.MADE_IN_HEAVEN;

/**
 * The {@link Capability} used for storing the player's Stand ability.
 */
@ParametersAreNonnullByDefault
public class Stand implements IStand, ICapabilitySerializable<INBT> {
    @CapabilityInject(IStand.class)
    public static final Capability<IStand> STAND = Null(); //Null method suppresses warnings
    private final PlayerEntity player;
    private int standID;
    /**
     * The {@link Entity#getEntityId()} of the player's Stand, can be used in conjunction with a {@link net.minecraft.world.World} to get the entity.
     */
    private int standEntityID;
    private int standAct;
    private boolean standOn;
    private double cooldown;
    private double timeLeft = 1000;
    private String diavolo = "";
    private boolean ability = true;
    private boolean abilityActive;
    private int transformed;
    private boolean noClip;
    private LazyOptional<IStand> holder = LazyOptional.of(() -> new Stand(getPlayer()));

    public Stand(@Nonnull PlayerEntity player) {
        this.player = player;
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
                props.putDouble("TimeLeft", instance.getTimeLeft());
                props.putBoolean("Ability", instance.getAbility());
                props.putInt("Transformed", instance.getTransformed());
                props.putString("Diavolo", instance.getDiavolo());
                props.putBoolean("NoClip", instance.getNoClip());
                props.putInt("StandEntityID", instance.getPlayerStand());
                props.putBoolean("AbilityActive", instance.getAbilityActive());
                return props;
            }

            @Override
            public void readNBT(Capability<IStand> capability, IStand instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                instance.putStandID(compoundNBT.getInt("StandID"));
                instance.putAct(compoundNBT.getInt("StandAct"));
                instance.putStandOn(compoundNBT.getBoolean("StandOn"));
                instance.putCooldown(compoundNBT.getDouble("Cooldown"));
                instance.putTimeLeft(compoundNBT.getDouble("TimeLeft"));
                instance.putAbility(compoundNBT.getBoolean("Ability"));
                instance.putTransformed(compoundNBT.getInt("Transformed"));
                instance.putDiavolo(compoundNBT.getString("Diavolo"));
                instance.putNoClip(compoundNBT.getBoolean("NoClip"));
                instance.putPlayerStand(compoundNBT.getInt("StandEntityID"));
                instance.putAbilityActive(compoundNBT.getBoolean("AbilityActive"));
            }
        }, () -> new Stand(Null()));
    }

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public int getStandID() {
        return standID;
    }

    @Override
    public void setStandID(int value) {
        this.standID = value;
        onDataUpdated();
    }

    @Override
    public int getPlayerStand() {
        return standEntityID;
    }

    @Override
    public void setPlayerStand(int standEntityID) {
        this.standEntityID = standEntityID;
        onDataUpdated();
    }

    @Override
    public void putPlayerStand(int standEntityID) {
        this.standEntityID = standEntityID;
    }

    @Override
    public int getAct() {
        return standAct;
    }

    @Override
    public void setAct(int standAct) {
        this.standAct = standAct;
        onDataUpdated();
    }

    @Override
    public void changeAct() {
        standAct++;
        if (standAct == getMaxAct()) {
            standAct = 0;
            setStandOn(false);
        }
        onDataUpdated();
    }

    @Override
    public boolean hasAct() {
        return getStandID() == MADE_IN_HEAVEN || getStandID() == CMOON;
    }

    @Override
    public int getMaxAct() {
        switch (standID) {
            case MADE_IN_HEAVEN:
                return 3;
            case CMOON:
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
        return timeLeft;
    }

    @Override
    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
        onDataUpdated();
    }

    @Override
    public void addTimeLeft(double addition) {
        this.timeLeft += addition;
        onDataUpdated();
    }

    @Override
    public void subtractTimeLeft(double subtraction) {
        this.timeLeft -= subtraction;
        onDataUpdated();
    }

    @Override
    public String getDiavolo() {
        return diavolo;
    }

    @Override
    public void setDiavolo(String truth) {
        this.diavolo = truth;
        onDataUpdated();
    }

    @Override
    public boolean getAbility() {
        return ability;
    }

    @Override
    public void setAbility(boolean ability) {
        this.ability = ability;
        onDataUpdated();
    }

    @Override
    public int getTransformed() {
        return transformed;
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
    public void putTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
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

    @Override
    public boolean getAbilityActive() {
        return abilityActive;
    }

    @Override
    public void setAbilityActive(boolean abilityActive) {
        this.abilityActive = abilityActive;
        onDataUpdated();
    }

    @Override
    public void putAbilityActive(boolean abilityActive) {
        this.abilityActive = abilityActive;
    }

    public void clone(IStand props) {
        putStandID(props.getStandID());
        putAct(props.getAct());
        putStandOn(props.getStandOn());
        putCooldown(props.getCooldown());
        putTimeLeft(props.getTimeLeft());
        putTransformed(props.getTransformed());
        putDiavolo(props.getDiavolo());
        putAbility(props.getAbility());
        putPlayerStand(props.getPlayerStand());
        putAbilityActive(props.getAbilityActive());
        onDataUpdated();
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
        setPlayerStand(0);
        setAbilityActive(false);
        onDataUpdated();
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
        return capability == STAND ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return Stand.STAND.getStorage().writeNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        Stand.STAND.getStorage().readNBT(STAND, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }
}
