package com.novarch.jojomod.events.custom;

import com.novarch.jojomod.entities.stands.EntityStandBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Fired when {@link com.novarch.jojomod.capabilities.stand.IStand} standOn is set to false.
 * This event is fired from {@link EntityStandBase} right before it's removed from the world.
 */
public class StandUnsummonedEvent extends PlayerEvent {
    private final EntityStandBase stand;

    public StandUnsummonedEvent(PlayerEntity player, EntityStandBase stand) {
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
}
