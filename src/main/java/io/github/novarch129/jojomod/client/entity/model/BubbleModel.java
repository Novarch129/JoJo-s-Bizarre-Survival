package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.BubbleEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BubbleModel extends EntityModel<BubbleEntity> {
    private final ModelRenderer Bubble;

    public BubbleModel() {
        textureWidth = 8;
        textureHeight = 8;

        Bubble = new ModelRenderer(this);
        Bubble.setRotationPoint(0, 24, 0);
        Bubble.setTextureOffset(0, 0).addBox(-1, -2, -1, 2, 2, 2, 0, false);
    }

    @Override
    public void setRotationAngles(BubbleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Bubble.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}