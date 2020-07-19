package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.WhitesnakeModel;
import io.github.novarch129.jojomod.entity.stand.WhitesnakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WhitesnakeRenderer extends MobRenderer<WhitesnakeEntity, WhitesnakeModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

    public WhitesnakeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WhitesnakeModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(WhitesnakeEntity entity) {
        return TEXTURE;
    }
}

