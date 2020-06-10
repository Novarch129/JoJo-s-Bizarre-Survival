package com.novarch.jojomod.events.custom;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

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
     * This event does not have a result.
     */
    public static class StandUnsummonedEvent extends PlayerEvent {
        public StandUnsummonedEvent(PlayerEntity player, EntityStandBase stand) {
            super(player);
        }
    }

    /**
     * Fired when an {@link EntityStandBase} is added to the world.
     * This Event is fired from {@link EntityStandBase} when it's added to the world
     * This Event is {@link Cancelable}.
     * If this Event is cancelled the Stand will not be summoned.
     * This event does not have a result.
     */
    @Cancelable
    public static class StandSummonedEvent extends PlayerEvent {
        public StandSummonedEvent(PlayerEntity player, EntityStandBase stand) {
            super(player);
        }
    }
}
