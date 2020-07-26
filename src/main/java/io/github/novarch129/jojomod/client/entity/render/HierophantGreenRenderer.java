package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.HierophantGreenModel;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HierophantGreenRenderer extends MobRenderer<HierophantGreenEntity, HierophantGreenModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/hierophant_green.png");

    public HierophantGreenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HierophantGreenModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(HierophantGreenEntity entity) {
        return TEXTURE;
    }
}

