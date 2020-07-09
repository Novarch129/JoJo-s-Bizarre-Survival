package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entities.stands.attacks.MagiciansRedFlameEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MagiciansRedFlameModel extends EntityModel<MagiciansRedFlameEntity> {
    public final ModelRenderer Flames;
    public final ModelRenderer Flames2;
    private final ModelRenderer Left;
    private final ModelRenderer Circle2;
    private final ModelRenderer Circle3;
    private final ModelRenderer Circle4;
    private final ModelRenderer Circle6;
    private final ModelRenderer Left2;
    private final ModelRenderer Circle8;
    private final ModelRenderer Circle9;
    private final ModelRenderer Circle10;
    private final ModelRenderer Circle11;
    private final ModelRenderer Circle12;
    private final ModelRenderer Circle7;
    private final ModelRenderer Circle;
    private final ModelRenderer Circle13;
    private final ModelRenderer Circle5;
    private final ModelRenderer Left3;
    private final ModelRenderer Circle14;
    private final ModelRenderer Circle15;
    private final ModelRenderer Circle16;
    private final ModelRenderer Circle17;
    private final ModelRenderer Left4;
    private final ModelRenderer Circle18;
    private final ModelRenderer Circle19;
    private final ModelRenderer Circle20;
    private final ModelRenderer Circle21;
    private final ModelRenderer Circle22;
    private final ModelRenderer Circle23;
    private final ModelRenderer Circle24;
    private final ModelRenderer Circle25;
    private final ModelRenderer Circle26;

    public MagiciansRedFlameModel() {
        textureWidth = 64;
        textureHeight = 32;

        Flames = new ModelRenderer(this);
        Flames.setRotationPoint(0.0F, 12.55F, 0.0F);
        setRotationAngle(Flames, 0.0F, 0.0F, 3.1329F);
        Flames.setTextureOffset(0, 0).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Flames.setTextureOffset(0, 0).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        Left = new ModelRenderer(this);
        Left.setRotationPoint(-2.5F, -4.0F, 0.0F);
        Flames.addChild(Left);


        Circle2 = new ModelRenderer(this);
        Circle2.setRotationPoint(2.0F, -0.5F, 0.5F);
        Left.addChild(Circle2);
        setRotationAngle(Circle2, 0.0F, 0.0F, 0.5454F);
        Circle2.setTextureOffset(0, 0).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle3 = new ModelRenderer(this);
        Circle3.setRotationPoint(-1.3053F, -1.0913F, 0.5F);
        Left.addChild(Circle3);
        setRotationAngle(Circle3, 0.0F, 0.0F, 1.1563F);
        Circle3.setTextureOffset(0, 0).addBox(-1.5096F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle4 = new ModelRenderer(this);
        Circle4.setRotationPoint(-2.5136F, -3.8373F, 0.5F);
        Left.addChild(Circle4);
        setRotationAngle(Circle4, 0.0F, 0.0F, 1.5926F);
        Circle4.setTextureOffset(0, 0).addBox(-1.0638F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle6 = new ModelRenderer(this);
        Circle6.setRotationPoint(2.0F, -0.5F, 0.5F);
        Left.addChild(Circle6);
        setRotationAngle(Circle6, 0.0F, 0.0F, 2.2907F);
        Circle6.setTextureOffset(0, 0).addBox(-3.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Left2 = new ModelRenderer(this);
        Left2.setRotationPoint(2.5F, -4.0F, 0.0F);
        Flames.addChild(Left2);


        Circle8 = new ModelRenderer(this);
        Circle8.setRotationPoint(-2.0F, -0.5F, 0.5F);
        Left2.addChild(Circle8);
        setRotationAngle(Circle8, 0.0F, 0.0F, -0.5454F);
        Circle8.setTextureOffset(0, 0).addBox(0.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle9 = new ModelRenderer(this);
        Circle9.setRotationPoint(1.3053F, -1.0913F, 0.5F);
        Left2.addChild(Circle9);
        setRotationAngle(Circle9, 0.0F, 0.0F, -1.1563F);
        Circle9.setTextureOffset(0, 0).addBox(-0.4904F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle10 = new ModelRenderer(this);
        Circle10.setRotationPoint(2.5136F, -3.8373F, 0.5F);
        Left2.addChild(Circle10);
        setRotationAngle(Circle10, 0.0F, 0.0F, -1.5926F);
        Circle10.setTextureOffset(0, 0).addBox(-0.9362F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle11 = new ModelRenderer(this);
        Circle11.setRotationPoint(-2.0F, -0.5F, 0.5F);
        Left2.addChild(Circle11);
        setRotationAngle(Circle11, 0.0F, 0.0F, -2.2907F);
        Circle11.setTextureOffset(0, 0).addBox(1.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle12 = new ModelRenderer(this);
        Circle12.setRotationPoint(-1.3239F, 2.9863F, 0.5F);
        Left2.addChild(Circle12);
        setRotationAngle(Circle12, 0.0F, 0.0F, -2.5613F);
        Circle12.setTextureOffset(0, 0).addBox(4.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle7 = new ModelRenderer(this);
        Circle7.setRotationPoint(-3.6761F, 2.9863F, 0.5F);
        Left2.addChild(Circle7);
        setRotationAngle(Circle7, 0.0F, 0.0F, 2.5613F);
        Circle7.setTextureOffset(0, 0).addBox(-6.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle = new ModelRenderer(this);
        Circle.setRotationPoint(-2.0F, -4.0F, 0.0F);
        Flames.addChild(Circle);
        Circle.setTextureOffset(0, 0).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle13 = new ModelRenderer(this);
        Circle13.setRotationPoint(-2.0F, -10.0F, 0.0F);
        Flames.addChild(Circle13);
        Circle13.setTextureOffset(0, 0).addBox(1.5196F, -1.468F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Circle5 = new ModelRenderer(this);
        Circle5.setRotationPoint(-0.5F, -4.5F, 0.5F);
        Flames.addChild(Circle5);
        setRotationAngle(Circle5, 0.0F, 0.0F, 0.5454F);
        Circle5.setTextureOffset(0, 0).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Flames2 = new ModelRenderer(this);
        Flames2.setRotationPoint(0.0F, 12.55F, 0.0F);
        setRotationAngle(Flames2, 0.0F, 0.0F, 3.1329F);
        Flames2.setTextureOffset(6, 9).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Flames2.setTextureOffset(6, 9).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        Left3 = new ModelRenderer(this);
        Left3.setRotationPoint(-2.5F, -4.0F, 0.0F);
        Flames2.addChild(Left3);


        Circle14 = new ModelRenderer(this);
        Circle14.setRotationPoint(2.0F, -0.5F, 0.5F);
        Left3.addChild(Circle14);
        setRotationAngle(Circle14, 0.0F, 0.0F, 0.5454F);
        Circle14.setTextureOffset(6, 9).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle15 = new ModelRenderer(this);
        Circle15.setRotationPoint(-1.3053F, -1.0913F, 0.5F);
        Left3.addChild(Circle15);
        setRotationAngle(Circle15, 0.0F, 0.0F, 1.1563F);
        Circle15.setTextureOffset(6, 9).addBox(-1.5096F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle16 = new ModelRenderer(this);
        Circle16.setRotationPoint(-2.5136F, -3.8373F, 0.5F);
        Left3.addChild(Circle16);
        setRotationAngle(Circle16, 0.0F, 0.0F, 1.5926F);
        Circle16.setTextureOffset(6, 9).addBox(-1.0638F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle17 = new ModelRenderer(this);
        Circle17.setRotationPoint(2.0F, -0.5F, 0.5F);
        Left3.addChild(Circle17);
        setRotationAngle(Circle17, 0.0F, 0.0F, 2.2907F);
        Circle17.setTextureOffset(6, 9).addBox(-3.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Left4 = new ModelRenderer(this);
        Left4.setRotationPoint(2.5F, -4.0F, 0.0F);
        Flames2.addChild(Left4);


        Circle18 = new ModelRenderer(this);
        Circle18.setRotationPoint(-2.0F, -0.5F, 0.5F);
        Left4.addChild(Circle18);
        setRotationAngle(Circle18, 0.0F, 0.0F, -0.5454F);
        Circle18.setTextureOffset(6, 9).addBox(0.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle19 = new ModelRenderer(this);
        Circle19.setRotationPoint(1.3053F, -1.0913F, 0.5F);
        Left4.addChild(Circle19);
        setRotationAngle(Circle19, 0.0F, 0.0F, -1.1563F);
        Circle19.setTextureOffset(6, 9).addBox(-0.4904F, -1.9811F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle20 = new ModelRenderer(this);
        Circle20.setRotationPoint(2.5136F, -3.8373F, 0.5F);
        Left4.addChild(Circle20);
        setRotationAngle(Circle20, 0.0F, 0.0F, -1.5926F);
        Circle20.setTextureOffset(6, 9).addBox(-0.9362F, -2.5191F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle21 = new ModelRenderer(this);
        Circle21.setRotationPoint(-2.0F, -0.5F, 0.5F);
        Left4.addChild(Circle21);
        setRotationAngle(Circle21, 0.0F, 0.0F, -2.2907F);
        Circle21.setTextureOffset(6, 9).addBox(1.3244F, 4.1141F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle22 = new ModelRenderer(this);
        Circle22.setRotationPoint(-1.3239F, 2.9863F, 0.5F);
        Left4.addChild(Circle22);
        setRotationAngle(Circle22, 0.0F, 0.0F, -2.5613F);
        Circle22.setTextureOffset(6, 9).addBox(4.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Circle23 = new ModelRenderer(this);
        Circle23.setRotationPoint(-3.6761F, 2.9863F, 0.5F);
        Left4.addChild(Circle23);
        setRotationAngle(Circle23, 0.0F, 0.0F, 2.5613F);
        Circle23.setTextureOffset(6, 9).addBox(-6.3138F, 7.3613F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle24 = new ModelRenderer(this);
        Circle24.setRotationPoint(-2.0F, -4.0F, 0.0F);
        Flames2.addChild(Circle24);
        Circle24.setTextureOffset(6, 9).addBox(1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Circle25 = new ModelRenderer(this);
        Circle25.setRotationPoint(-2.0F, -10.0F, 0.0F);
        Flames2.addChild(Circle25);
        Circle25.setTextureOffset(6, 9).addBox(1.5196F, -1.468F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Circle26 = new ModelRenderer(this);
        Circle26.setRotationPoint(-0.5F, -4.5F, 0.5F);
        Flames2.addChild(Circle26);
        setRotationAngle(Circle26, 0.0F, 0.0F, 0.5454F);
        Circle26.setTextureOffset(6, 9).addBox(-2.1681F, -0.3132F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(MagiciansRedFlameEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Flames.render(matrixStack, buffer, packedLight, packedOverlay);
        Flames2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}