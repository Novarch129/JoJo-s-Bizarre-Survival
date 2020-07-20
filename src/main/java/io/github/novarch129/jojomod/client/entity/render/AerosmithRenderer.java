package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.AerosmithModel;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AerosmithRenderer extends MobRenderer<AerosmithEntity, AerosmithModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith.png");

    public AerosmithRenderer(EntityRendererManager manager) {
        super(manager, new AerosmithModel(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(AerosmithEntity entity) {
        return TEXTURE;
    }
}
