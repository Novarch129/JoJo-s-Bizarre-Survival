package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TheHandModel;
import io.github.novarch129.jojomod.entity.stand.TheHandEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TheHandRenderer extends AbstractStandRenderer<TheHandEntity, TheHandModel> {
    public TheHandRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TheHandModel());
    }

    @Override
    public ResourceLocation getEntityTexture(TheHandEntity entity) {
        return Util.ResourceLocations.THE_HAND;
    }
}

