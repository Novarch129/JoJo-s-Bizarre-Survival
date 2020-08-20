package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TuskAct2Model;
import io.github.novarch129.jojomod.entity.stand.TuskAct2Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class TuskAct2Renderer extends AbstractStandRenderer<TuskAct2Entity, TuskAct2Model> {
    public TuskAct2Renderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TuskAct2Model());
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(TuskAct2Entity entity) {
        return Util.ResourceLocations.TUSK_ACT_2;
    }
}

