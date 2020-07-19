package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.entity.stand.attack.AerosmithBulletEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class AerosmithBulletRenderer extends EntityRenderer<AerosmithBulletEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith_bullet.png");

    public AerosmithBulletRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(@Nonnull AerosmithBulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(final AerosmithBulletEntity entity) {
        return TEXTURE;
    }
}

