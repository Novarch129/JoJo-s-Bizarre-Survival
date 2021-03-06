package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.EmperorBulletEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EmperorBulletModel extends EntityModel<EmperorBulletEntity> {
    private final ModelRenderer Bullet;

    public EmperorBulletModel() {
        textureWidth = 8;
        textureHeight = 8;

        Bullet = new ModelRenderer(this);
        Bullet.setRotationPoint(0.0F, 24.0F, 0.0F);
        Bullet.setTextureOffset(0, 0).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(EmperorBulletEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Bullet.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay) {
        Bullet.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}