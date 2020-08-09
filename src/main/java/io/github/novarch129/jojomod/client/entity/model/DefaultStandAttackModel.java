package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DefaultStandAttackModel<T extends AbstractStandAttackEntity> extends EntityModel<T> {
    private final ModelRenderer Punch;

    public DefaultStandAttackModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0, 24, 0);
        Punch.setTextureOffset(0, 0).addBox(-2, -4, -6, 4, 4, 12, 0, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Punch.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
