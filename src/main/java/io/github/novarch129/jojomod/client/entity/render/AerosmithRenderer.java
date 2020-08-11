package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.AerosmithModel;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class AerosmithRenderer extends AbstractStandRenderer<AerosmithEntity, AerosmithModel> {
    public AerosmithRenderer(EntityRendererManager manager) {
        super(manager, new AerosmithModel());
    }

    @Override
    public ResourceLocation getEntityTexture(AerosmithEntity entity) {
        return Util.ResourceLocations.AEROSMITH;
    }
}
