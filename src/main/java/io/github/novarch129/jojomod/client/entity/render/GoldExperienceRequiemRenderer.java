package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.GoldExperienceRequiemModel;
import io.github.novarch129.jojomod.entity.stand.GoldExperienceRequiemEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoldExperienceRequiemRenderer extends MobRenderer<GoldExperienceRequiemEntity, GoldExperienceRequiemModel> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");

    public GoldExperienceRequiemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldExperienceRequiemModel(), 0.5f);
    }

    @Override
    public void render(GoldExperienceRequiemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(GoldExperienceRequiemEntity entity) {
        return TEXTURE;
    }
}

