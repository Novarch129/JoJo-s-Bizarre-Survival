package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.CameraModel;
import io.github.novarch129.jojomod.entity.CameraEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class CameraRenderer extends LivingRenderer<CameraEntity, CameraModel> {
    public CameraRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CameraModel(), 0);
    }

    @Override
    public ResourceLocation getEntityTexture(CameraEntity entity) {
        return Util.ResourceLocations.CMOON;
    }
}

