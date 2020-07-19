package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.KingCrimsonModel;
import io.github.novarch129.jojomod.entity.stand.KingCrimsonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KingCrimsonRenderer extends MobRenderer<KingCrimsonEntity, KingCrimsonModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png");

    public KingCrimsonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KingCrimsonModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(KingCrimsonEntity entity) {
        return TEXTURE;
    }
}

