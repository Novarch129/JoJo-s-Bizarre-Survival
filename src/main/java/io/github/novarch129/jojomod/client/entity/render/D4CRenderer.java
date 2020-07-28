package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.D4CModel;
import io.github.novarch129.jojomod.entity.stand.D4CEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class D4CRenderer extends MobRenderer<D4CEntity, D4CModel> {
    public D4CRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new D4CModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(D4CEntity entity) {
        return Util.ResourceLocations.D4C;
    }
}

