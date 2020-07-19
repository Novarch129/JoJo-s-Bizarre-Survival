package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.KingCrimsonPunchModel;
import io.github.novarch129.jojomod.entity.stand.attack.KingCrimsonPunchEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class KingCrimsonPunchRenderer extends StandPunchRenderer<KingCrimsonPunchEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson_punch.png");

    public KingCrimsonPunchRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(@Nonnull KingCrimsonPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new KingCrimsonPunchModel());
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(KingCrimsonPunchEntity entity) {
        return TEXTURE;
    }
}

