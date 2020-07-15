package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.D4CPunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class D4CPunchModel extends EntityModel<D4CPunchEntity> {
    private final ModelRenderer Punch;

    public D4CPunchModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.Punch = new ModelRenderer(this, 0, 0);
        this.Punch.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 12);
        this.Punch.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Punch.setTextureSize(64, 32);
        setRotationAngle(this.Punch, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.Punch.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    @Override
    public void setRotationAngles(D4CPunchEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadBaseYaw, float headPitch) {
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
