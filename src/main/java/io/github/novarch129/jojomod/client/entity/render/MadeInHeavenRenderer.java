package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.MadeInHeavenModel;
import io.github.novarch129.jojomod.entity.stand.MadeInHeavenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MadeInHeavenRenderer extends MobRenderer<MadeInHeavenEntity, MadeInHeavenModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/made_in_heaven.png");

    public MadeInHeavenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MadeInHeavenModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(MadeInHeavenEntity entity) {
        return TEXTURE;
    }
}

