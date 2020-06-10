package com.novarch.jojomod.events.custom;

import com.novarch.jojomod.entities.stands.EntityStandPunch;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * Fired before the effects of an {@link EntityStandPunch} take place.
 * This Event is fired from {@link com.novarch.jojomod.entities.stands.StandPunchEffects}.
 * This Event is {@link Cancelable}.
 * If this event is cancelled the effects of the Stand punch will be canceled (block breaking, damaging entities).
 * This Event does not have a result.
 */
@Cancelable
public class StandPunchEvent extends Event {
    private final EntityStandPunch punch;
    private final RayTraceResult rayTraceResult;
    private final LivingEntity target;
    private final boolean isEntity;

    public StandPunchEvent(EntityStandPunch punch, RayTraceResult rayTraceResult, LivingEntity target, boolean isEntity) {
        this.punch = punch;
        this.rayTraceResult = rayTraceResult;
        this.target = target;
        this.isEntity = isEntity;
    }

    public EntityStandPunch getPunch() {
        return punch;
    }

    public RayTraceResult getRayTraceResult() {
        return rayTraceResult;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public boolean isEntity() {
        return isEntity;
    }

    /**
     * Fired before an {@link EntityStandPunch} effects a {@link LivingEntity}.
     * This Event is fired from each Stand's punch effect individually in {@link com.novarch.jojomod.entities.stands.StandPunchEffects}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled no damage or effect will be applied to the target, but knockback will still be applied.
     * This Event does not have a result.
     */
    @Cancelable
    public static class EntityHit extends StandPunchEvent {
        public EntityHit(EntityStandPunch punch, RayTraceResult rayTraceResult, LivingEntity target) {
            super(punch, rayTraceResult, target, true);
        }
    }

    /**
     * Fired before an {@link EntityStandPunch} effects a {@link net.minecraft.block.Block}.
     * This Event is fired from each Stand's punch effect individually in {@link com.novarch.jojomod.entities.stands.StandPunchEffects}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the block will not be destroyed.
     * This Event does not have a result.
     */
    @Cancelable
    public static class BlockHit extends StandPunchEvent {
        public BlockHit(EntityStandPunch punch, RayTraceResult rayTraceResult, LivingEntity target) {
            super(punch, rayTraceResult, target, false);
        }
    }
}
