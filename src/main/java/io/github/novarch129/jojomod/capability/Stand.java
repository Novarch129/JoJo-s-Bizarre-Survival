package io.github.novarch129.jojomod.capability;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.network.message.server.SSyncStandCapabilityPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static io.github.novarch129.jojomod.util.Util.Null;
import static io.github.novarch129.jojomod.util.Util.StandID.*;

/**
 * The {@link Capability} used for storing the player's Stand ability.
 */
public class Stand implements ICapabilitySerializable<INBT> {
    @CapabilityInject(Stand.class)
    public static final Capability<Stand> STAND = Null(); //Null method suppresses warnings
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
    private boolean ability = JojoBizarreSurvivalConfig.COMMON.abilityImmediatelyActive.get();
    private boolean abilityActive;
    private int transformed;
    private boolean noClip;
    private double invulnerableTicks;
    private float standDamage;
    private boolean charging;
    private int abilityUseCount;
    private BlockPos blockPos = BlockPos.ZERO;
    private List<ChunkPos> affectedChunkList = new ArrayList<>();
    private int experiencePoints;
    private long gameTime = -1;
    private long dayTime = -1;
    private int abilitiesUnlocked;
    private double prevTimeLeft = 1000;
    private LazyOptional<Stand> holder = LazyOptional.of(() -> new Stand(getPlayer()));

    public Stand(@Nonnull PlayerEntity player) {
        this.player = player;
    }

    public static Stand getCapabilityFromPlayer(PlayerEntity player) {
        return player.getCapability(STAND).orElse(new Stand(player));
    }

    public static LazyOptional<Stand> getLazyOptional(PlayerEntity player) {
        return player.getCapability(STAND);
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(Stand.class, new Capability.IStorage<Stand>() {
            @Nonnull
            @Override
            public INBT writeNBT(Capability<Stand> capability, Stand props, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putInt("standID", props.getStandID());
                nbt.putInt("standAct", props.getAct());
                nbt.putBoolean("standOn", props.getStandOn());
                nbt.putDouble("cooldown", props.getCooldown());
                nbt.putDouble("timeLeft", props.getTimeLeft());
                nbt.putBoolean("ability", props.getAbility());
                nbt.putInt("transformed", props.getTransformed());
                nbt.putString("diavolo", props.getDiavolo());
                nbt.putBoolean("noClip", props.getNoClip());
                nbt.putInt("standEntityID", props.getPlayerStand());
                nbt.putBoolean("abilityActive", props.getAbilityActive());
                nbt.putDouble("invulnerableTicks", props.getInvulnerableTicks());
                nbt.putFloat("standDamage", props.getStandDamage());
                nbt.putBoolean("charging", props.isCharging());
                nbt.putInt("abilityUseCount", props.getAbilityUseCount());
                nbt.putDouble("blockPosX", props.getBlockPos().getX());
                nbt.putDouble("blockPosY", props.getBlockPos().getY());
                nbt.putDouble("blockPosZ", props.getBlockPos().getZ());
                nbt.putInt("experiencePoints", props.getExperiencePoints());
                nbt.putLong("gameTime", props.getGameTime());
                nbt.putLong("dayTime", props.getDayTime());
                nbt.putInt("abilitiesUnlocked", props.getAbilitiesUnlocked());
                nbt.putDouble("prevTimeLeft", props.getPrevTimeLeft());
                ListNBT listNBT = new ListNBT();
                props.getAffectedChunkList().forEach(pos -> {
                    CompoundNBT compoundNBT = new CompoundNBT();
                    compoundNBT.putInt("chunkX", pos.x);
                    compoundNBT.putInt("chunkZ", pos.z);
                    listNBT.add(compoundNBT);
                });
                nbt.put("affectedChunkList", listNBT);
                return nbt;
            }

            @Override
            public void readNBT(Capability<Stand> capability, Stand props, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                props.standID = compoundNBT.getInt("standID");
                props.standAct = compoundNBT.getInt("standAct");
                props.standOn = compoundNBT.getBoolean("standOn");
                props.cooldown = compoundNBT.getDouble("cooldown");
                props.timeLeft = compoundNBT.getDouble("timeLeft");
                props.ability = compoundNBT.getBoolean("ability");
                props.transformed = compoundNBT.getInt("transformed");
                props.diavolo = compoundNBT.getString("diavolo");
                props.noClip = compoundNBT.getBoolean("noClip");
                props.standEntityID = compoundNBT.getInt("standEntityID");
                props.abilityActive = compoundNBT.getBoolean("abilityActive");
                props.invulnerableTicks = compoundNBT.getDouble("invulnerableTicks");
                props.standDamage = compoundNBT.getFloat("standDamage");
                props.charging = compoundNBT.getBoolean("charging");
                props.abilityUseCount = compoundNBT.getInt("abilityUseCount");
                props.blockPos = new BlockPos(compoundNBT.getDouble("blockPosX"), compoundNBT.getDouble("blockPosY"), compoundNBT.getDouble("blockPosZ"));
                props.experiencePoints = compoundNBT.getInt("experiencePoints");
                props.gameTime = compoundNBT.getLong("gameTime");
                props.dayTime = compoundNBT.getLong("dayTime");
                props.abilityUseCount = compoundNBT.getInt("abilitiesUnlocked");
                props.prevTimeLeft = compoundNBT.getDouble("prevTimeLeft");
                compoundNBT.getList("affectedChunkList", Constants.NBT.TAG_COMPOUND).forEach(inbt -> {
                    if (inbt instanceof CompoundNBT && ((CompoundNBT) inbt).contains("chunkX"))
                        props.affectedChunkList.add(new ChunkPos(((CompoundNBT) inbt).getInt("chunkX"), ((CompoundNBT) inbt).getInt("chunkZ")));
                });
            }
        }, () -> new Stand(Null()));
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public int getStandID() {
        return standID;
    }

    public void setStandID(int value) {
        this.standID = value;
        onDataUpdated();
    }

    public int getPlayerStand() {
        return standEntityID;
    }

    public void setPlayerStand(int standEntityID) {
        this.standEntityID = standEntityID;
        onDataUpdated();
    }

    public int getAct() {
        return standAct;
    }

    public void setAct(int standAct) {
        this.standAct = standAct;
        onDataUpdated();
    }

    public void changeAct() {
        standAct++;
        if (standAct == getMaxAct())
            standAct = 0;
        onDataUpdated();
    }

    public boolean hasAct() {
        return Util.StandID.STANDS_WITH_ACTS.contains(getStandID());
    }

    public int getMaxAct() {
        switch (standID) {
            case TUSK_ACT_4:
                return 4;
            case BEACH_BOY:
            case ECHOES_ACT_3:
            case TUSK_ACT_3:
            case MADE_IN_HEAVEN:
                return 3;
            case ECHOES_ACT_2:
            case TUSK_ACT_2:
            case CMOON:
                return 2;
        }
        return 0;
    }

    public boolean getStandOn() {
        return this.standOn;
    }

    public void setStandOn(boolean value) {
        this.standOn = value;
        onDataUpdated();
    }

    public double getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
        onDataUpdated();
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(double timeLeft) {
        this.prevTimeLeft = this.timeLeft;
        this.timeLeft = timeLeft;
        onDataUpdated();
    }

    public String getDiavolo() {
        return diavolo;
    }

    public void setDiavolo(String truth) {
        this.diavolo = truth;
        onDataUpdated();
    }

    public boolean getAbility() {
        return ability;
    }

    public void setAbility(boolean ability) {
        this.ability = ability;
        onDataUpdated();
    }

    public int getTransformed() {
        return transformed;
    }

    public void setTransformed(int value) {
        this.transformed = value;
        onDataUpdated();
    }

    public void addTransformed(int addition) {
        this.transformed += addition;
        onDataUpdated();
    }

    public boolean getNoClip() {
        return noClip;
    }

    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
        onDataUpdated();
    }

    public boolean getAbilityActive() {
        return abilityActive;
    }

    public void setAbilityActive(boolean abilityActive) {
        this.abilityActive = abilityActive;
        onDataUpdated();
    }

    public double getInvulnerableTicks() {
        return invulnerableTicks;
    }

    public void setInvulnerableTicks(double invulnerableTicks) {
        this.invulnerableTicks = invulnerableTicks;
        onDataUpdated();
    }

    public float getStandDamage() {
        return standDamage;
    }

    public void setStandDamage(float standDamage) {
        this.standDamage = standDamage;
        onDataUpdated();
    }

    public boolean isCharging() {
        return charging;
    }

    public void setCharging(boolean charging) {
        this.charging = charging;
        onDataUpdated();
    }

    public int getAbilityUseCount() {
        return abilityUseCount;
    }

    public void setAbilityUseCount(int abilityUseCount) {
        this.abilityUseCount = abilityUseCount;
        onDataUpdated();
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
        onDataUpdated();
    }

    public List<ChunkPos> getAffectedChunkList() {
        return affectedChunkList;
    }

    public void addAffectedChunk(ChunkPos pos) {
        affectedChunkList.add(pos);
        onDataUpdated();
    }

    public void removeAffectedChunk(ChunkPos pos) {
        affectedChunkList.remove(pos);
        onDataUpdated();
    }

    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
        onDataUpdated();
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public long getGameTime() {
        return gameTime;
    }

    public void setGameTime(long time) {
        this.gameTime = time;
        onDataUpdated();
    }

    public long getDayTime() {
        return dayTime;
    }

    public void setDayTime(long time) {
        this.dayTime = time;
        onDataUpdated();
    }

    public int getAbilitiesUnlocked() {
        return abilitiesUnlocked;
    }

    public void addAbilityUnlocked(int amount) {
        abilitiesUnlocked += amount;
        onDataUpdated();
    }

    public double getPrevTimeLeft() {
        return prevTimeLeft;
    }

    public void setPrevTimeLeft(double prevTimeLeft) {
        this.prevTimeLeft = prevTimeLeft;
        onDataUpdated();
    }

    public void clone(Stand stand) {
        standID = stand.getStandID();
        standAct = stand.getAct();
        standOn = stand.getStandOn();
        cooldown = stand.getCooldown();
        timeLeft = stand.getTimeLeft();
        transformed = stand.getTransformed();
        diavolo = stand.getDiavolo();
        ability = stand.getAbility();
        standEntityID = stand.getPlayerStand();
        abilityActive = stand.getAbilityActive();
        invulnerableTicks = stand.getInvulnerableTicks();
        standDamage = stand.getStandDamage();
        charging = stand.isCharging();
        abilityUseCount = stand.getAbilityUseCount();
        affectedChunkList = stand.getAffectedChunkList();
        experiencePoints = stand.getExperiencePoints();
        prevTimeLeft = stand.getTimeLeft();
        onDataUpdated();
    }

    public void removeStand() {
        standOn = false;
        standAct = 0;
        standID = 0;
        cooldown = 0;
        timeLeft = 1000;
        transformed = 0;
        diavolo = "";
        ability = false;
        noClip = false;
        standEntityID = 0;
        abilityActive = false;
        invulnerableTicks = 0;
        standDamage = 0;
        charging = false;
        abilityUseCount = 0;
        affectedChunkList = new ArrayList<>();
        experiencePoints = 0;
        prevTimeLeft = 0;
        onDataUpdated();
    }

    /**
     * Called to update the {@link Capability} to the client.
     */
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
