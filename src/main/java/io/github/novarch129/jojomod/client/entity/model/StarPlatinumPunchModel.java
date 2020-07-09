package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entities.stands.attacks.StarPlatinumPunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class StarPlatinumPunchModel extends EntityModel<StarPlatinumPunchEntity> {
    public final ModelRenderer Punch;
    private final ModelRenderer ElbowPad2;

    public StarPlatinumPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ElbowPad2 = new ModelRenderer(this);
        ElbowPad2.setRotationPoint(-0.0828F, -1.3644F, 4.3044F);
        Punch.addChild(ElbowPad2);
        setRotationAngle(ElbowPad2, 1.5926F, 0.0131F, 1.5621F);
        ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(54, 26).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(4, 29).addBox(-1.747F, -1.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(4, 29).addBox(-1.747F, -4.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(3, 21).addBox(-2.747F, -3.486F, -2.5709F, 4.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(StarPlatinumPunchEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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