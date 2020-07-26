package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.CrazyDiamondPunchModel;
import io.github.novarch129.jojomod.entity.stand.attack.CrazyDiamondPunchEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class CrazyDiamondPunchRenderer extends AbstractStandPunchRenderer<CrazyDiamondPunchEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/crazy_diamond_punch.png");

    public CrazyDiamondPunchRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(@Nonnull CrazyDiamondPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new CrazyDiamondPunchModel());
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(final CrazyDiamondPunchEntity entity) {
        return TEXTURE;
    }
}

