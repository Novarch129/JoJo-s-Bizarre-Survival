package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.MadeInHeavenModel;
import io.github.novarch129.jojomod.entity.stand.MadeInHeavenEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MadeInHeavenRenderer extends AbstractStandRenderer<MadeInHeavenEntity, MadeInHeavenModel> {
    public MadeInHeavenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MadeInHeavenModel());
    }

    @Override
    public ResourceLocation getEntityTexture(MadeInHeavenEntity entity) {
        return Util.ResourceLocations.MADE_IN_HEAVEN;
    }
}

