package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.GoldExperienceRequiemModel;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceRequiemEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoldExperienceRequiemRenderer extends MobRenderer<GoldExperienceRequiemEntity, GoldExperienceRequiemModel> {
    public GoldExperienceRequiemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldExperienceRequiemModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(GoldExperienceRequiemEntity entity) {
        return Util.ResourceLocations.GER;
    }
}

