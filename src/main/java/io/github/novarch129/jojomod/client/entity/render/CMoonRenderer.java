package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.CMoonModel;
import io.github.novarch129.jojomod.entity.stand.CMoonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CMoonRenderer extends MobRenderer<CMoonEntity, CMoonModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon.png");

    public CMoonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CMoonModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(CMoonEntity entity) {
        return TEXTURE;
    }
}

