package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.EchoesAct3Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesAct3Model extends AbstractStandModel<EchoesAct3Entity> {
    private final ModelRenderer EchoesAct3;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer Eyes2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftHand4;
    private final ModelRenderer LeftHand5;
    private final ModelRenderer LeftHand6;
    private final ModelRenderer LeftHand8;
    private final ModelRenderer LeftArm3;
    private final ModelRenderer LeftHand2;
    private final ModelRenderer LeftHand3;
    private final ModelRenderer LeftHand7;
    private final ModelRenderer LeftHand9;
    private final ModelRenderer Crotch;
    private final ModelRenderer Three;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer LeftLeg2;
    private final ModelRenderer LeftFoot2;

    public EchoesAct3Model() {
        textureWidth = 128;
        textureHeight = 128;

        EchoesAct3 = new ModelRenderer(this);
        EchoesAct3.setRotationPoint(0.0F, 25.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -22.0F, -1.75F);
        EchoesAct3.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -3.0F, -0.25F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.5F, -5.2F, -4.0F, 7.0F, 8.0F, 8.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 6.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes2 = new ModelRenderer(this);
        Eyes2.setRotationPoint(9.0F, 6.0F, 0.0F);
        Head.addChild(Eyes2);
        Eyes2.setTextureOffset(34, 0).addBox(-10.0F, -7.5F, -4.5F, 2.0F, 3.0F, 9.0F, 0.0F, false);
        Eyes2.setTextureOffset(31, 0).addBox(-10.0F, -4.4F, -4.025F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes2.setTextureOffset(33, 14).addBox(-13.0F, -8.5F, -1.0F, 8.0F, 3.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(35, 0).addBox(-10.0F, -11.0F, -4.5F, 2.0F, 3.0F, 9.0F, 0.0F, false);
        Eyes2.setTextureOffset(32, 4).addBox(-10.0F, -11.5F, -3.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        Eyes2.setTextureOffset(32, 4).addBox(-10.0F, -11.5F, 0.5F, 2.0F, 1.0F, 3.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        EchoesAct3.addChild(BodyBase);

        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.25F);
        BodyBase.addChild(Torso);
        Torso.setTextureOffset(0, 0).addBox(-2.5F, 3.3F, -2.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, 6.5F);
        BodyBase.addChild(Chest);
        Chest.setTextureOffset(18, 20).addBox(-5.0F, -18.767F, -10.7241F, 10.0F, 5.0F, 5.0F, 0.0F, false);
        Chest.setTextureOffset(13, 18).addBox(-4.0F, -17.767F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(13, 18).addBox(2.0F, -17.767F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, true);
        Chest.setTextureOffset(24, 2).addBox(-1.0F, -13.767F, -10.5F, 2.0F, 3.0F, 1.0F, 0.0F, true);
        Chest.setTextureOffset(36, 37).addBox(2.0F, -17.767F, -6.575F, 2.0F, 3.0F, 1.0F, 0.0F, true);
        Chest.setTextureOffset(36, 37).addBox(-4.0F, -17.767F, -6.575F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(34, 41).addBox(-1.0F, -18.0F, -7.55F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        Chest.setTextureOffset(45, 31).addBox(-7.0F, -18.5F, -10.2F, 14.0F, 4.0F, 4.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 31).addBox(-6.5F, -4.206F, -0.6905F, 7.0F, 4.0F, 4.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);

        LeftArm2 = new ModelRenderer(this);
        LeftArm2.setRotationPoint(-8.3F, -23.9765F, -2.3061F);
        Arms.addChild(LeftArm2);
        setRotationAngle(LeftArm2, -0.0873F, 0.0F, 0.3098F);
        LeftArm2.setTextureOffset(0, 40).addBox(3.1076F, 1.7688F, -0.7668F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        LeftHand4 = new ModelRenderer(this);
        LeftHand4.setRotationPoint(1.5624F, 5.6204F, 1.506F);
        LeftArm2.addChild(LeftHand4);
        setRotationAngle(LeftHand4, -0.0261F, 0.0F, -0.2399F);
        LeftHand4.setTextureOffset(23, 31).addBox(0.3376F, 3.3605F, -1.837F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        LeftHand5 = new ModelRenderer(this);
        LeftHand5.setRotationPoint(0.6856F, -0.2888F, -0.0148F);
        LeftArm2.addChild(LeftHand5);
        setRotationAngle(LeftHand5, 0.0175F, 0.0F, -0.3272F);
        LeftHand5.setTextureOffset(0, 18).addBox(-0.1474F, 2.3619F, -2.0053F, 3.0F, 5.0F, 5.0F, 0.0F, false);
        LeftHand5.setTextureOffset(36, 32).addBox(-0.5856F, 3.5495F, -0.3791F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        LeftHand6 = new ModelRenderer(this);
        LeftHand6.setRotationPoint(2.5148F, 5.4039F, 0.4832F);
        LeftArm2.addChild(LeftHand6);
        setRotationAngle(LeftHand6, 0.0175F, 0.0F, -0.2399F);
        LeftHand6.setTextureOffset(23, 41).addBox(-0.1877F, 5.6303F, -1.3379F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        LeftHand8 = new ModelRenderer(this);
        LeftHand8.setRotationPoint(2.5148F, 5.4039F, 0.4832F);
        LeftArm2.addChild(LeftHand8);
        setRotationAngle(LeftHand8, 0.0175F, 0.0F, 0.0655F);
        LeftHand8.setTextureOffset(23, 41).addBox(1.2852F, 0.7688F, -1.6771F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        LeftArm3 = new ModelRenderer(this);
        LeftArm3.setRotationPoint(8.3F, -23.9765F, -2.3061F);
        Arms.addChild(LeftArm3);
        setRotationAngle(LeftArm3, -0.0873F, 0.0F, -0.3098F);
        LeftArm3.setTextureOffset(0, 40).addBox(-6.1076F, 1.7688F, -0.7668F, 3.0F, 7.0F, 3.0F, 0.0F, true);

        LeftHand2 = new ModelRenderer(this);
        LeftHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm3.addChild(LeftHand2);
        setRotationAngle(LeftHand2, -0.0261F, 0.0F, 0.2399F);
        LeftHand2.setTextureOffset(23, 31).addBox(-3.3376F, 3.3605F, -1.837F, 3.0F, 6.0F, 3.0F, 0.0F, true);

        LeftHand3 = new ModelRenderer(this);
        LeftHand3.setRotationPoint(-0.6856F, -0.2888F, -0.0148F);
        LeftArm3.addChild(LeftHand3);
        setRotationAngle(LeftHand3, 0.0175F, 0.0F, 0.3272F);
        LeftHand3.setTextureOffset(0, 18).addBox(-2.8526F, 2.3619F, -2.0053F, 3.0F, 5.0F, 5.0F, 0.0F, true);
        LeftHand3.setTextureOffset(36, 32).addBox(-2.4144F, 3.5495F, -0.3791F, 3.0F, 2.0F, 2.0F, 0.0F, true);

        LeftHand7 = new ModelRenderer(this);
        LeftHand7.setRotationPoint(-2.5148F, 5.4039F, 0.4832F);
        LeftArm3.addChild(LeftHand7);
        setRotationAngle(LeftHand7, 0.0175F, 0.0F, 0.2399F);
        LeftHand7.setTextureOffset(23, 41).addBox(-1.8123F, 5.6303F, -1.3379F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        LeftHand9 = new ModelRenderer(this);
        LeftHand9.setRotationPoint(-2.5148F, 5.4039F, 0.4832F);
        LeftArm3.addChild(LeftHand9);
        setRotationAngle(LeftHand9, 0.0175F, 0.0F, -0.0655F);
        LeftHand9.setTextureOffset(23, 41).addBox(-3.2852F, 0.7688F, -1.6771F, 2.0F, 2.0F, 3.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(14, 74).addBox(-4.5F, -14.2058F, -4.347F, 9.0F, 3.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 52).addBox(-5.2F, -14.5F, -4.5F, 5.0F, 7.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(25, 62).addBox(-5.2F, -14.5F, -3.5F, 1.0F, 7.0F, 4.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 52).addBox(0.2F, -14.5F, -4.5F, 5.0F, 7.0F, 1.0F, 0.0F, true);
        Crotch.setTextureOffset(25, 62).addBox(4.2F, -14.5F, -3.5F, 1.0F, 7.0F, 4.0F, 0.0F, true);
        Crotch.setTextureOffset(0, 63).addBox(-4.8F, -14.5F, 0.5F, 10.0F, 7.0F, 1.0F, 0.0F, true);
        Crotch.setTextureOffset(38, 64).addBox(-0.5F, -14.5F, -4.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);

        Three = new ModelRenderer(this);
        Three.setRotationPoint(0.0F, -0.75F, 0.0F);
        Crotch.addChild(Three);
        Three.setTextureOffset(45, 66).addBox(-1.5F, -13.25F, -4.625F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(-1.5F, -12.75F, -4.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(0.5F, -12.25F, -4.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(-0.5F, -11.25F, -4.625F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(0.5F, -10.25F, -4.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(-1.5F, -9.25F, -4.625F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Three.setTextureOffset(45, 66).addBox(-1.5F, -9.75F, -4.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 1.0F);
        BodyBase.addChild(Legs);

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(1.275F, -11.25F, -2.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(0, 73).addBox(-0.175F, -1.1995F, -1.4456F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(16, 49).addBox(-0.7875F, -9.072F, 0.8941F, 4.0F, 7.0F, 4.0F, 0.0F, false);
        LeftFoot.setTextureOffset(34, 55).addBox(-0.1375F, -9.322F, 0.8191F, 3.0F, 2.0F, 4.0F, 0.0F, false);
        LeftFoot.setTextureOffset(33, 48).addBox(-0.9225F, -7.448F, 1.7765F, 4.0F, 3.0F, 2.0F, 0.0F, false);
        LeftFoot.setTextureOffset(33, 48).addBox(-0.6725F, -7.448F, 1.7765F, 4.0F, 3.0F, 2.0F, 0.0F, false);

        LeftLeg2 = new ModelRenderer(this);
        LeftLeg2.setRotationPoint(-1.275F, -11.25F, -2.7375F);
        Legs.addChild(LeftLeg2);
        setRotationAngle(LeftLeg2, -0.1265F, -0.1396F, 0.1745F);
        LeftLeg2.setTextureOffset(0, 73).addBox(-2.825F, -1.1995F, -1.4456F, 3.0F, 5.0F, 3.0F, 0.0F, true);

        LeftFoot2 = new ModelRenderer(this);
        LeftFoot2.setRotationPoint(0.3875F, 12.4197F, 1.0049F);
        LeftLeg2.addChild(LeftFoot2);
        setRotationAngle(LeftFoot2, 0.4058F, 0.0F, -0.0873F);
        LeftFoot2.setTextureOffset(16, 50).addBox(-3.2125F, -8.072F, 0.8941F, 4.0F, 6.0F, 4.0F, 0.0F, true);
        LeftFoot2.setTextureOffset(33, 48).addBox(-3.0775F, -7.448F, 1.7765F, 4.0F, 3.0F, 2.0F, 0.0F, true);
        LeftFoot2.setTextureOffset(33, 48).addBox(-3.3275F, -7.448F, 1.7765F, 4.0F, 3.0F, 2.0F, 0.0F, true);
        LeftFoot2.setTextureOffset(34, 55).addBox(-2.7625F, -8.697F, 0.8191F, 3.0F, 2.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        EchoesAct3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected ModelRenderer getHead() {
        return HeadBase;
    }
}