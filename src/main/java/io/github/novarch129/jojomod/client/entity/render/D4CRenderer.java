package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.D4CModel;
import io.github.novarch129.jojomod.entity.stand.D4CEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class D4CRenderer extends MobRenderer<D4CEntity, D4CModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c.png");

    public D4CRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new D4CModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(D4CEntity entity) {
        return TEXTURE;
    }
}

