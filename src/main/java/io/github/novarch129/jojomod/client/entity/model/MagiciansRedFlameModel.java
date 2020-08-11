package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.attack.MagiciansRedFlameEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MagiciansRedFlameModel extends AbstractStandAttackModel<MagiciansRedFlameEntity> {
    public final ModelRenderer Flames;
    public final ModelRenderer Flames2;

    public MagiciansRedFlameModel() {
        textureWidth = 64;
        textureHeight = 32;

        Flames = new ModelRenderer(this);
        Flames.setRotationPoint(0.0F, 12.55F, 0.0F);
        setRotationAngle(Flames, 0.0F, 0.0F, 3.1329F);
        Flames.setTextureOffset(0, 0).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Flames.setTextureOffset(0, 0).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer left = new ModelRenderer(this);
        left.setRotationPoint(-2.5F, -4.0F, 0.0F);
        Flames.addChild(left);

        ModelRenderer circle2 = new ModelRenderer(this);
        circle2.setRotationPoint(2.0F, -0.5F, 0.5F);
        left.addChild(circle2);
        setRotationAngle(circle2, 0.0F, 0.0F, 0.5454F);
        circle2.setTextureOffset(0, 0).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle3 = new ModelRenderer(this);
        circle3.setRotationPoint(-1.3053F, -1.0913F, 0.5F);
        left.addChild(circle3);
        setRotationAngle(circle3, 0.0F, 0.0F, 1.1563F);
        circle3.setTextureOffset(0, 0).addBox(-1.5096F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle4 = new ModelRenderer(this);
        circle4.setRotationPoint(-2.5136F, -3.8373F, 0.5F);
        left.addChild(circle4);
        setRotationAngle(circle4, 0.0F, 0.0F, 1.5926F);
        circle4.setTextureOffset(0, 0).addBox(-1.0638F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle6 = new ModelRenderer(this);
        circle6.setRotationPoint(2.0F, -0.5F, 0.5F);
        left.addChild(circle6);
        setRotationAngle(circle6, 0.0F, 0.0F, 2.2907F);
        circle6.setTextureOffset(0, 0).addBox(-3.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer left2 = new ModelRenderer(this);
        left2.setRotationPoint(2.5F, -4.0F, 0.0F);
        Flames.addChild(left2);

        ModelRenderer circle8 = new ModelRenderer(this);
        circle8.setRotationPoint(-2.0F, -0.5F, 0.5F);
        left2.addChild(circle8);
        setRotationAngle(circle8, 0.0F, 0.0F, -0.5454F);
        circle8.setTextureOffset(0, 0).addBox(0.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle9 = new ModelRenderer(this);
        circle9.setRotationPoint(1.3053F, -1.0913F, 0.5F);
        left2.addChild(circle9);
        setRotationAngle(circle9, 0.0F, 0.0F, -1.1563F);
        circle9.setTextureOffset(0, 0).addBox(-0.4904F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle10 = new ModelRenderer(this);
        circle10.setRotationPoint(2.5136F, -3.8373F, 0.5F);
        left2.addChild(circle10);
        setRotationAngle(circle10, 0.0F, 0.0F, -1.5926F);
        circle10.setTextureOffset(0, 0).addBox(-0.9362F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle11 = new ModelRenderer(this);
        circle11.setRotationPoint(-2.0F, -0.5F, 0.5F);
        left2.addChild(circle11);
        setRotationAngle(circle11, 0.0F, 0.0F, -2.2907F);
        circle11.setTextureOffset(0, 0).addBox(1.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle12 = new ModelRenderer(this);
        circle12.setRotationPoint(-1.3239F, 2.9863F, 0.5F);
        left2.addChild(circle12);
        setRotationAngle(circle12, 0.0F, 0.0F, -2.5613F);
        circle12.setTextureOffset(0, 0).addBox(4.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle7 = new ModelRenderer(this);
        circle7.setRotationPoint(-3.6761F, 2.9863F, 0.5F);
        left2.addChild(circle7);
        setRotationAngle(circle7, 0.0F, 0.0F, 2.5613F);
        circle7.setTextureOffset(0, 0).addBox(-6.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle = new ModelRenderer(this);
        circle.setRotationPoint(-2.0F, -4.0F, 0.0F);
        Flames.addChild(circle);
        circle.setTextureOffset(0, 0).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle13 = new ModelRenderer(this);
        circle13.setRotationPoint(-2.0F, -10.0F, 0.0F);
        Flames.addChild(circle13);
        circle13.setTextureOffset(0, 0).addBox(1.5196F, -1.468F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle5 = new ModelRenderer(this);
        circle5.setRotationPoint(-0.5F, -4.5F, 0.5F);
        Flames.addChild(circle5);
        setRotationAngle(circle5, 0.0F, 0.0F, 0.5454F);
        circle5.setTextureOffset(0, 0).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Flames2 = new ModelRenderer(this);
        Flames2.setRotationPoint(0.0F, 12.55F, 0.0F);
        setRotationAngle(Flames2, 0.0F, 0.0F, 3.1329F);
        Flames2.setTextureOffset(6, 9).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Flames2.setTextureOffset(6, 9).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer left3 = new ModelRenderer(this);
        left3.setRotationPoint(-2.5F, -4.0F, 0.0F);
        Flames2.addChild(left3);

        ModelRenderer circle14 = new ModelRenderer(this);
        circle14.setRotationPoint(2.0F, -0.5F, 0.5F);
        left3.addChild(circle14);
        setRotationAngle(circle14, 0.0F, 0.0F, 0.5454F);
        circle14.setTextureOffset(6, 9).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle15 = new ModelRenderer(this);
        circle15.setRotationPoint(-1.3053F, -1.0913F, 0.5F);
        left3.addChild(circle15);
        setRotationAngle(circle15, 0.0F, 0.0F, 1.1563F);
        circle15.setTextureOffset(6, 9).addBox(-1.5096F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle16 = new ModelRenderer(this);
        circle16.setRotationPoint(-2.5136F, -3.8373F, 0.5F);
        left3.addChild(circle16);
        setRotationAngle(circle16, 0.0F, 0.0F, 1.5926F);
        circle16.setTextureOffset(6, 9).addBox(-1.0638F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle17 = new ModelRenderer(this);
        circle17.setRotationPoint(2.0F, -0.5F, 0.5F);
        left3.addChild(circle17);
        setRotationAngle(circle17, 0.0F, 0.0F, 2.2907F);
        circle17.setTextureOffset(6, 9).addBox(-3.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer left4 = new ModelRenderer(this);
        left4.setRotationPoint(2.5F, -4.0F, 0.0F);
        Flames2.addChild(left4);

        ModelRenderer circle18 = new ModelRenderer(this);
        circle18.setRotationPoint(-2.0F, -0.5F, 0.5F);
        left4.addChild(circle18);
        setRotationAngle(circle18, 0.0F, 0.0F, -0.5454F);
        circle18.setTextureOffset(6, 9).addBox(0.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle19 = new ModelRenderer(this);
        circle19.setRotationPoint(1.3053F, -1.0913F, 0.5F);
        left4.addChild(circle19);
        setRotationAngle(circle19, 0.0F, 0.0F, -1.1563F);
        circle19.setTextureOffset(6, 9).addBox(-0.4904F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle20 = new ModelRenderer(this);
        circle20.setRotationPoint(2.5136F, -3.8373F, 0.5F);
        left4.addChild(circle20);
        setRotationAngle(circle20, 0.0F, 0.0F, -1.5926F);
        circle20.setTextureOffset(6, 9).addBox(-0.9362F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle21 = new ModelRenderer(this);
        circle21.setRotationPoint(-2.0F, -0.5F, 0.5F);
        left4.addChild(circle21);
        setRotationAngle(circle21, 0.0F, 0.0F, -2.2907F);
        circle21.setTextureOffset(6, 9).addBox(1.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle22 = new ModelRenderer(this);
        circle22.setRotationPoint(-1.3239F, 2.9863F, 0.5F);
        left4.addChild(circle22);
        setRotationAngle(circle22, 0.0F, 0.0F, -2.5613F);
        circle22.setTextureOffset(6, 9).addBox(4.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer circle23 = new ModelRenderer(this);
        circle23.setRotationPoint(-3.6761F, 2.9863F, 0.5F);
        left4.addChild(circle23);
        setRotationAngle(circle23, 0.0F, 0.0F, 2.5613F);
        circle23.setTextureOffset(6, 9).addBox(-6.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle24 = new ModelRenderer(this);
        circle24.setRotationPoint(-2.0F, -4.0F, 0.0F);
        Flames2.addChild(circle24);
        circle24.setTextureOffset(6, 9).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle25 = new ModelRenderer(this);
        circle25.setRotationPoint(-2.0F, -10.0F, 0.0F);
        Flames2.addChild(circle25);
        circle25.setTextureOffset(6, 9).addBox(1.5196F, -1.468F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer circle26 = new ModelRenderer(this);
        circle26.setRotationPoint(-0.5F, -4.5F, 0.5F);
        Flames2.addChild(circle26);
        setRotationAngle(circle26, 0.0F, 0.0F, 0.5454F);
        circle26.setTextureOffset(6, 9).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Flames.render(matrixStack, buffer, packedLight, packedOverlay);
        Flames2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setRotationAngles(MagiciansRedFlameEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        setRotationAngle(Flames, headPitch / 90, netHeadYaw / 90, headPitch / 90);
        setRotationAngle(Flames2, headPitch / 90, netHeadYaw / 90, headPitch / 90);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Flames;
    }
}