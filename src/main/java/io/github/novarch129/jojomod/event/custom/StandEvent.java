package io.github.novarch129.jojomod.event.custom;

import io.github.novarch129.jojomod.capability.stand.IStand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nullable;

/**
 * StandEvent is fired when an event involving {@link AbstractStandEntity} occurs.
 */
public class StandEvent extends PlayerEvent {
    private final AbstractStandEntity stand;

    public StandEvent(PlayerEntity player, AbstractStandEntity stand) {
        super(player);
        this.stand = stand;
    }

    @Override
    public PlayerEntity getPlayer() {
        return super.getPlayer();
    }

    public AbstractStandEntity getStand() {
        return stand;
    }

    /**
     * Fired when {@link IStand} standOn is set to false.
     * This Event is fired from {@link AbstractStandEntity} right before it's removed from the world.
     * You should check if the {@link AbstractStandEntity} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class StandUnsummonedEvent extends StandEvent {
        public StandUnsummonedEvent(PlayerEntity player, @Nullable AbstractStandEntity stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link AbstractStandEntity} is added to the world.
     * This Event is fired from {@link AbstractStandEntity} when it's added to the world
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the Stand will not be summoned.
     * This Event does not have a result.
     */
    @Cancelable
    public static class StandSummonedEvent extends StandEvent {
        public StandSummonedEvent(PlayerEntity player, AbstractStandEntity stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link AbstractStandEntity}'s master dies.
     * This Event is fired from {@link AbstractStandEntity} right before it's removed from the world.
     * You should check if the {@link AbstractStandEntity} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class MasterDeathEvent extends StandEvent {
        public MasterDeathEvent(PlayerEntity player, @Nullable AbstractStandEntity stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link AbstractStandEntity} is removed from the {@link net.minecraft.world.World}.
     * You should check if the {@link AbstractStandEntity} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class StandRemovedEvent extends StandEvent {
        public StandRemovedEvent(PlayerEntity player, @Nullable AbstractStandEntity stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link AbstractStandEntity} ticks, this Event will not be fired if the Stand's master is <code>null</code>.
     * This Event is fired from tick in {@link AbstractStandEntity}.
     * This Event does not have a result.
     */
    public static class StandTickEvent extends StandEvent {
        public StandTickEvent(PlayerEntity player, AbstractStandEntity stand) {
            super(player, stand);
        }
    }
}
