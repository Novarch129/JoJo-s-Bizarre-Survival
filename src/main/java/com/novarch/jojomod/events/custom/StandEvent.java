package com.novarch.jojomod.events.custom;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nullable;

/**
 * StandEvent is fired when an event involving {@link EntityStandBase} occurs.
 */
public class StandEvent extends PlayerEvent {
    private final EntityStandBase stand;

    public StandEvent(PlayerEntity player, EntityStandBase stand) {
        super(player);
        this.stand = stand;
    }

    @Override
    public PlayerEntity getPlayer() {
        return super.getPlayer();
    }

    public EntityStandBase getStand() {
        return stand;
    }

    /**
     * Fired when {@link com.novarch.jojomod.capabilities.stand.IStand} standOn is set to false.
     * This Event is fired from {@link EntityStandBase} right before it's removed from the world.
     * You should check if the {@link EntityStandBase} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class StandUnsummonedEvent extends StandEvent {
        public StandUnsummonedEvent(PlayerEntity player, @Nullable EntityStandBase stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link EntityStandBase} is added to the world.
     * This Event is fired from {@link EntityStandBase} when it's added to the world
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the Stand will not be summoned.
     * This Event does not have a result.
     */
    @Cancelable
    public static class StandSummonedEvent extends StandEvent {
        public StandSummonedEvent(PlayerEntity player, EntityStandBase stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link EntityStandBase}'s master dies.
     * This Event is fired from {@link EntityStandBase} right before it's removed from the world.
     * You should check if the {@link EntityStandBase} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class MasterDeathEvent extends StandEvent {
        public MasterDeathEvent(PlayerEntity player, @Nullable EntityStandBase stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link EntityStandBase} is removed from the {@link net.minecraft.world.World}.
     * You should check if the {@link EntityStandBase} provided in this event is <code>null</code> as it can be dead.
     * This Event does not have a result.
     */
    public static class StandRemovedEvent extends StandEvent {
        public StandRemovedEvent(PlayerEntity player, @Nullable EntityStandBase stand) {
            super(player, stand);
        }
    }

    /**
     * Fired when an {@link EntityStandBase} ticks, this Event will not be fired if the Stand's master is <code>null</code>.
     * This Event is fired from tick in {@link EntityStandBase}.
     * This Event does not have a result.
     */
    public static class StandTickEvent extends StandEvent {
        public StandTickEvent(PlayerEntity player, EntityStandBase stand) {
            super(player, stand);
        }
    }
}
