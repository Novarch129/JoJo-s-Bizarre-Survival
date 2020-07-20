package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.KillerQueenModel;
import io.github.novarch129.jojomod.entity.stand.KillerQueenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class KillerQueenRenderer extends MobRenderer<KillerQueenEntity, KillerQueenModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");

    public KillerQueenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KillerQueenModel(), 0.5f);
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(KillerQueenEntity entity) {
        return TEXTURE;
    }
}

