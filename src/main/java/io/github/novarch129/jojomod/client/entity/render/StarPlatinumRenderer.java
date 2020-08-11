package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.StarPlatinumModel;
import io.github.novarch129.jojomod.entity.stand.StarPlatinumEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class StarPlatinumRenderer extends AbstractStandRenderer<StarPlatinumEntity, StarPlatinumModel> {
    public StarPlatinumRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StarPlatinumModel());
    }

    @Override
    public ResourceLocation getEntityTexture(StarPlatinumEntity entity) {
        return Util.ResourceLocations.STAR_PLATINUM;
    }
}

