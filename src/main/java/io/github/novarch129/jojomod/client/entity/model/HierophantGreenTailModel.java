package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.HierophantGreenTailEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class HierophantGreenTailModel extends EntityModel<HierophantGreenTailEntity> {
    private final ModelRenderer Tail;

    public HierophantGreenTailModel() {
        textureWidth = 64;
        textureHeight = 32;

        Tail = new ModelRenderer(this);
        Tail.setRotationPoint(0.0F, 24.0F, 0.0F);
        Tail.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -7.5F, 3.0F, 2.0F, 15.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(HierophantGreenTailEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Tail.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}