package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.EmeraldSplashModel;
import io.github.novarch129.jojomod.entity.stand.attack.EmeraldSplashEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EmeraldSplashRenderer extends AbstractStandPunchRenderer<EmeraldSplashEntity> {
    protected static ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/emerald_splash.png");

    public EmeraldSplashRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(EmeraldSplashEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new EmeraldSplashModel());
    }

    @Override
    public ResourceLocation getEntityTexture(EmeraldSplashEntity entity) {
        return TEXTURE;
    }
}
