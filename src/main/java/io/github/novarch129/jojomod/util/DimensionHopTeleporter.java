package io.github.novarch129.jojomod.util;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Function;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DimensionHopTeleporter extends Teleporter {
    public DimensionHopTeleporter(ServerWorld world) {
        super(world);
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return false;
    }

    @Override
    public boolean placeInPortal(Entity p_222268_1_, float p_222268_2_) {
        return false;
    }

    @Nullable
    @Override
    public BlockPattern.PortalInfo placeInExistingPortal(BlockPos p_222272_1_, Vec3d p_222272_2_, Direction directionIn, double p_222272_4_, double p_222272_6_, boolean p_222272_8_) {
        return null;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        return repositionEntity.apply(false);
    }
}
