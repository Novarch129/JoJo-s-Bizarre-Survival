package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.TheHandModel;
import io.github.novarch129.jojomod.entity.stand.TheHandEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TheHandRenderer extends MobRenderer<TheHandEntity, TheHandModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_hand.png");

    public TheHandRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TheHandModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(final TheHandEntity entity) {
        return TEXTURE;
    }
}

