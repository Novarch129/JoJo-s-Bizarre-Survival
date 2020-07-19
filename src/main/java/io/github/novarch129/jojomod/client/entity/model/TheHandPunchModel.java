package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.TheHandPunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheHandPunchModel extends EntityModel<TheHandPunchEntity> {
    private final ModelRenderer Punch;
    private final ModelRenderer ElbowPad1;

    public TheHandPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ElbowPad1 = new ModelRenderer(this);
        ElbowPad1.setRotationPoint(4.0F, -1.3954F, -7.0899F);
        Punch.addChild(ElbowPad1);
        setRotationAngle(ElbowPad1, -1.5708F, 0.0F, -3.1416F);
        ElbowPad1.setTextureOffset(51, 2).addBox(5.6864F, -9.014F, -1.2511F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, -1.5261F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, -1.5011F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, 1.9239F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(TheHandPunchEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Punch.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}