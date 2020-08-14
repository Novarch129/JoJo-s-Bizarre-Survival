package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.SilverChariotModel;
import io.github.novarch129.jojomod.entity.stand.SilverChariotEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SilverChariotRenderer extends AbstractStandRenderer<SilverChariotEntity, SilverChariotModel> {
    public SilverChariotRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SilverChariotModel());
    }

    @Override
    public ResourceLocation getEntityTexture(SilverChariotEntity entity) {
        return Util.ResourceLocations.SILVER_CHARIOT;
    }
}

