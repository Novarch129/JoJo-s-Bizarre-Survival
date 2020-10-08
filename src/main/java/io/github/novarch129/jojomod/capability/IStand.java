package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.List;

public interface IStand {
    PlayerEntity getPlayer();

    int getStandID();

    void setStandID(int standID);

    int getPlayerStand();

    void setPlayerStand(int standEntityID);

    void putPlayerStand(int standEntityID);

    int getAct();

    void setAct(int standAct);

    void changeAct();

    boolean hasAct();

    int getMaxAct();

    boolean getStandOn();

    void setStandOn(boolean standOn);

    double getCooldown();

    void setCooldown(double cooldown);

    double getTimeLeft();

    void setTimeLeft(double timeLeft);

    String getDiavolo();

    void setDiavolo(String truth);

    boolean getAbility();

    void setAbility(boolean ability);

    int getTransformed();

    void setTransformed(int value);

    void addTransformed(int addition);

    boolean getNoClip();

    void setNoClip(boolean noClip);

    void putStandID(int standID);

    void putAct(int act);

    void putStandOn(boolean standOn);

    void putTimeLeft(double timeLeft);

    void putCooldown(double cooldown);

    void putAbility(boolean ability);

    void putTransformed(int transformed);

    void putDiavolo(String truth);

    void putNoClip(boolean noClip);

    boolean getAbilityActive();

    void setAbilityActive(boolean abilityActive);

    void putAbilityActive(boolean abilityActive);

    double getInvulnerableTicks();

    void setInvulnerableTicks(double invulnerableTicks);

    void putInvulnerableTicks(double invulnerableTicks);

    float getStandDamage();

    void setStandDamage(float standDamage);

    void putStandDamage(float standDamage);

    boolean isCharging();

    void setCharging(boolean charging);

    void putCharging(boolean charging);

    int getAbilityUseCount();

    void setAbilityUseCount(int abilityUseCount);

    void putAbilityUseCount(int abilityUseCount);

    BlockPos getBlockPos();

    void setBlockPos(BlockPos blockPos);

    void putBlockPos(BlockPos blockPos);

    List<ChunkPos> getAffectedChunkList();

    void addAffectedChunk(ChunkPos pos);

    void removeAffectedChunk(ChunkPos pos);

    void putAffectedChunkList(List<ChunkPos> list);

    void addExperiencePoints(int experiencePoints);

    int getExperiencePoints();

    void putExperiencePoints(int experiencePoints);

    long getGameTime();

    void setGameTime(long time);

    void putGameTime(long time);

    long getDayTime();

    void setDayTime(long time);

    void putDayTime(long time);

    int getAbilitiesUnlocked();

    void addAbilityUnlocked(int amount);

    void putAbilitiesUnlocked(int abilitiesUnlocked);

    double getPrevTimeLeft();

    void setPrevTimeLeft(double prevTimeLeft);

    void putPrevTimeLeft(double prevTimeLeft);

    void clone(IStand props);

    void removeStand();

    void onDataUpdated();
}
