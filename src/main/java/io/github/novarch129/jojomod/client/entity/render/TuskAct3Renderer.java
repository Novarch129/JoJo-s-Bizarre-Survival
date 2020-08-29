package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TuskAct3Model;
import io.github.novarch129.jojomod.entity.stand.TuskAct3Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TuskAct3Renderer extends AbstractStandRenderer<TuskAct3Entity, TuskAct3Model> {
    public TuskAct3Renderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TuskAct3Model());
    }

    @Override
    public ResourceLocation getEntityTexture(TuskAct3Entity entity) {
        return Util.ResourceLocations.TUSK_ACT_3;
    }
}

