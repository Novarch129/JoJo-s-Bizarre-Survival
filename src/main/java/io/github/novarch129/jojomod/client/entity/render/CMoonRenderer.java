package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.CMoonModel;
import io.github.novarch129.jojomod.entity.stand.CMoonEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CMoonRenderer extends AbstractStandRenderer<CMoonEntity, CMoonModel> {
    public CMoonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CMoonModel());
    }

    @Override
    public ResourceLocation getEntityTexture(CMoonEntity entity) {
        return Util.ResourceLocations.CMOON;
    }
}

