package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.GreenDayModel;
import io.github.novarch129.jojomod.entity.stand.GreenDayEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class GreenDayRenderer extends AbstractStandRenderer<GreenDayEntity, GreenDayModel> {
    public GreenDayRenderer(EntityRendererManager manager) {
        super(manager, new GreenDayModel());
    }

    @Override
    public ResourceLocation getEntityTexture(GreenDayEntity entity) {
        return Util.ResourceLocations.GREEN_DAY;
    }
}
