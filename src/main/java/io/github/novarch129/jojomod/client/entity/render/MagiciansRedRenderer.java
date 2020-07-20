package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.MagiciansRedModel;
import io.github.novarch129.jojomod.entity.stand.MagiciansRedEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MagiciansRedRenderer extends MobRenderer<MagiciansRedEntity, MagiciansRedModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red.png");

    public MagiciansRedRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MagiciansRedModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(MagiciansRedEntity entity) {
        return TEXTURE;
    }
}

