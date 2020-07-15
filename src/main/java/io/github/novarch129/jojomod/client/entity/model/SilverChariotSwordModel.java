package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.SilverChariotSwordEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SilverChariotSwordModel extends EntityModel<SilverChariotSwordEntity> {
    private final ModelRenderer Sword;

    public SilverChariotSwordModel() {
        textureWidth = 64;
        textureHeight = 32;

        Sword = new ModelRenderer(this);
        Sword.setRotationPoint(0.0F, 24.0F, 1.0F);
        Sword.setTextureOffset(0, 0).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        Sword.setTextureOffset(0, 0).addBox(-0.5F, -2.0F, -16.0F, 1.0F, 1.0F, 18.0F, 0.0F, false);
        Sword.setTextureOffset(0, 8).addBox(-1.5F, -3.0F, 2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(SilverChariotSwordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Sword.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}