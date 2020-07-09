package novarch.jojomod.events.custom;

import novarch.jojomod.entities.stands.attacks.AbstractStandAttackEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Fired before the effects of an {@link AbstractStandAttackEntity} take place.
 * This Event is fired from {@link AbstractStandAttackEntity} and it's children.
 * Note that the {@link AbstractStandAttackEntity}'s target is {@link Nullable} and will always be null if isEntity is false.
 * This Event is {@link Cancelable}.
 * If this event is cancelled the effects of the Stand punch will be canceled (block breaking, damaging entities).
 * This Event does not have a result.
 */
@SuppressWarnings("unused")
@Cancelable
public class StandPunchEvent extends Event {
    private final AbstractStandAttackEntity punch;
    private final RayTraceResult rayTraceResult;
    @Nullable private final Entity target;
    private final RayTraceResult.Type type;

    public StandPunchEvent(AbstractStandAttackEntity punch, RayTraceResult rayTraceResult, @Nullable Entity target, RayTraceResult.Type type) {
        this.punch = punch;
        this.rayTraceResult = rayTraceResult;
        this.target = target;
        this.type = type;
    }

    public AbstractStandAttackEntity getPunch() {
        return punch;
    }

    public RayTraceResult getRayTraceResult() {
        return rayTraceResult;
    }

    @Nullable
    public Entity getTarget() {
        return target;
    }

    public RayTraceResult.Type getType() {
        return type;
    }

    /**
     * Fired before an {@link AbstractStandAttackEntity} effects a {@link LivingEntity}.
     * This Event is fired from each Stand's punch effect individually in {@link AbstractStandAttackEntity}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled no damage or effect will be applied to the target, but knockback will still be applied.
     * This Event does not have a result.
     */
    @Cancelable
    public static class EntityHit extends StandPunchEvent {
        public EntityHit(AbstractStandAttackEntity punch, RayTraceResult rayTraceResult, @Nonnull Entity target) {
            super(punch, rayTraceResult, target, RayTraceResult.Type.ENTITY);
        }
    }

    /**
     * Fired before an {@link AbstractStandAttackEntity} effects a {@link net.minecraft.block.Block}.
     * This Event is fired from each Stand's punch effect individually in {@link AbstractStandAttackEntity}.
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the block will not be destroyed.
     * This Event does not have a result.
     */
    @Cancelable
    public static class BlockHit extends StandPunchEvent {
        public BlockHit(AbstractStandAttackEntity punch, RayTraceResult rayTraceResult, @Nullable Entity target) {
            super(punch, rayTraceResult, target, RayTraceResult.Type.BLOCK);
        }
    }
}
