package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.WhitesnakeModel;
import io.github.novarch129.jojomod.entity.stand.WhitesnakeEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WhitesnakeRenderer extends AbstractStandRenderer<WhitesnakeEntity, WhitesnakeModel> {
    public WhitesnakeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WhitesnakeModel());
    }

    @Override
    public ResourceLocation getEntityTexture(WhitesnakeEntity entity) {
        return Util.ResourceLocations.WHITESNAKE;
    }
}

