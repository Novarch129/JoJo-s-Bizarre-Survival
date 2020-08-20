package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TuskAct1Model;
import io.github.novarch129.jojomod.entity.stand.TuskAct1Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TuskAct1Renderer extends AbstractStandRenderer<TuskAct1Entity, TuskAct1Model> {
    public TuskAct1Renderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TuskAct1Model());
    }

    @Override
    public ResourceLocation getEntityTexture(TuskAct1Entity entity) {
        return Util.ResourceLocations.TUSK_ACT_1;
    }
}

