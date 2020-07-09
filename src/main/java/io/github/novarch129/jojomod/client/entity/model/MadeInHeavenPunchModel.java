package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entities.stands.attacks.MadeInHeavenPunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MadeInHeavenPunchModel extends EntityModel<MadeInHeavenPunchEntity> {
    private final ModelRenderer Punch;

    public MadeInHeavenPunchModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Punch = new ModelRenderer(this, 0, 0);
        this.Punch.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 12);
        this.Punch.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Punch.setTextureSize(64, 32);
        setRotationAngle(this.Punch, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setRotationAngles(MadeInHeavenPunchEntity madeInHeavenPunchEntity, float v, float v1, float v2, float v3, float v4) {
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Punch.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
