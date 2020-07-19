package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.StarPlatinumModel;
import io.github.novarch129.jojomod.entity.stand.StarPlatinumEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StarPlatinumRenderer extends MobRenderer<StarPlatinumEntity, StarPlatinumModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum.png");

    public StarPlatinumRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StarPlatinumModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(StarPlatinumEntity entity) {
        return TEXTURE;
    }
}

