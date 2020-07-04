package com.novarch.jojomod.events.custom;

import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Fired before the effects of an {@link AbstractStandPunchEntity} take place.
 * This Event is fired from {@link com.novarch.jojomod.entities.stands.AbstractStandPunchEntity} and it's children.
 * Note that the {@link AbstractStandPunchEntity}'s target is {@link Nullable} and will always be null if isEntity is false.
 * This Event is {@link Cancelable}.
 * If this event is cancelled the effects of the Stand punch will be canceled (block breaking, damaging entities).
 * This Event does not have a result.
 */
@SuppressWarnings("unused")
@Cancelable
public class StandPunchEvent extends Event {
    private final AbstractStandPunchEntity punch;
    private final RayTraceResult rayTraceResult;
    @Nullable private final LivingEntity target;
    private final RayTraceResult.Type type;

    public StandPunchEvent(AbstractStandPunchEntity punch, RayTraceResult rayTraceResult, @Nullable LivingEntity target, RayTraceResult.Type type) {
        this.punch = punch;
        this.rayTraceResult = rayTraceResult;
        this.target = target;
        this.type = type;
    }

    public AbstractStandPunchEntity getPunch() {
        return punch;
    }

    public RayTraceResult getRayTraceResult() {
        return rayTraceResult;
    }

    @Nullable
    public LivingEntity getTarget() {
        return target;
    }

    public RayTraceResult.Type getType() {
        return type;
    }

    /**
     * Fired before an {@link AbstractStandPunchEntity} effects a {@link LivingEntity}.
     * This Event is fired from each Stand's punch effect individually in {@link com.novarch.jojomod.entities.stands.AbstractStandPunchEntity}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled no damage or effect will be applied to the target, but knockback will still be applied.
     * This Event does not have a result.
     */
    @Cancelable
    public static class EntityHit extends StandPunchEvent {
        public EntityHit(AbstractStandPunchEntity punch, RayTraceResult rayTraceResult, @Nonnull LivingEntity target) {
            super(punch, rayTraceResult, target, RayTraceResult.Type.ENTITY);
        }
    }

    /**
     * Fired before an {@link AbstractStandPunchEntity} effects a {@link net.minecraft.block.Block}.
     * This Event is fired from each Stand's punch effect individually in {@link com.novarch.jojomod.entities.stands.AbstractStandPunchEntity}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the block will not be destroyed.
     * This Event does not have a result.
     */
    @Cancelable
    public static class BlockHit extends StandPunchEvent {
        public BlockHit(AbstractStandPunchEntity punch, RayTraceResult rayTraceResult, @Nullable LivingEntity target) {
            super(punch, rayTraceResult, target, RayTraceResult.Type.BLOCK);
        }
    }
}
