package io.github.novarch129.jojomod.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Function;

public class DimensionHopTeleporter extends Teleporter {
    public DimensionHopTeleporter(ServerWorld world) {
        super(world);
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        return repositionEntity.apply(false);
    }
}
