package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.HierophantGreenTailModel;
import io.github.novarch129.jojomod.entity.stand.attack.HierophantGreenTailEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class HierophantGreenTailRenderer extends AbstractStandPunchRenderer<HierophantGreenTailEntity> {
    protected static ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/hierophant_green_tail.png");

    public HierophantGreenTailRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(HierophantGreenTailEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new HierophantGreenTailModel());
    }

    @Override
    public ResourceLocation getEntityTexture(HierophantGreenTailEntity entity) {
        return TEXTURE;
    }
}
