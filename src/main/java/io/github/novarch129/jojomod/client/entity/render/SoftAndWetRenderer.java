package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.SoftAndWetModel;
import io.github.novarch129.jojomod.entity.stand.SoftAndWetEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SoftAndWetRenderer extends AbstractStandRenderer<SoftAndWetEntity, SoftAndWetModel> {
    public SoftAndWetRenderer(EntityRendererManager manager) {
        super(manager, new SoftAndWetModel());
    }

    @Override
    public ResourceLocation getEntityTexture(SoftAndWetEntity entity) {
        return Util.ResourceLocations.SOFT_AND_WET;
    }
}
