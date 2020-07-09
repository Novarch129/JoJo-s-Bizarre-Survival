package novarch.jojomod.world.dimension.d4c.nether;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class D4CDimensionTypeNether extends ModDimension
{
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return D4CDimensionNether::new;
    }
}
