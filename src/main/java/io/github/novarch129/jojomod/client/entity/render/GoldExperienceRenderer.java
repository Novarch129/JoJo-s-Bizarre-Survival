package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.GoldExperienceModel;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoldExperienceRenderer extends MobRenderer<GoldExperienceEntity, GoldExperienceModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");

    public GoldExperienceRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldExperienceModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(GoldExperienceEntity entity) {
        return TEXTURE;
    }
}

