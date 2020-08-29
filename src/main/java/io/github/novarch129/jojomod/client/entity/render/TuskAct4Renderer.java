package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TuskAct4Model;
import io.github.novarch129.jojomod.entity.stand.TuskAct4Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TuskAct4Renderer extends AbstractStandRenderer<TuskAct4Entity, TuskAct4Model> {
    public TuskAct4Renderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TuskAct4Model());
    }

    @Override
    public ResourceLocation getEntityTexture(TuskAct4Entity entity) {
        return Util.ResourceLocations.TUSK_ACT_4;
    }
}

