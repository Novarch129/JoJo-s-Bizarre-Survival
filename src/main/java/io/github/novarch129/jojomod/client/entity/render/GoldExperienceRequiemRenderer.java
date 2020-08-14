package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.GoldExperienceRequiemModel;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceRequiemEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GoldExperienceRequiemRenderer extends AbstractStandRenderer<GoldExperienceRequiemEntity, GoldExperienceRequiemModel> {
    public GoldExperienceRequiemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldExperienceRequiemModel());
    }

    @Override
    public ResourceLocation getEntityTexture(GoldExperienceRequiemEntity entity) {
        return Util.ResourceLocations.GER;
    }
}

