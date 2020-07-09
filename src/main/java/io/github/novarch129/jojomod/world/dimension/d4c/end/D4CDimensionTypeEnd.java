package io.github.novarch129.jojomod.world.dimension.d4c.end;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class D4CDimensionTypeEnd extends ModDimension
{
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return D4CDimensionEnd::new;
    }
}
