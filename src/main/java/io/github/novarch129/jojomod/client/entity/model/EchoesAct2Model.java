package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.EchoesAct2Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesAct2Model extends AbstractStandModel<EchoesAct2Entity> {
    private final ModelRenderer EchoesAct2;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer Eyes3;
    private final ModelRenderer Eyes2;
    private final ModelRenderer headplate;
    private final ModelRenderer headplate3;
    private final ModelRenderer headplate5;
    private final ModelRenderer headplate7;
    private final ModelRenderer headplate6;
    private final ModelRenderer headplate8;
    private final ModelRenderer headplate4;
    private final ModelRenderer Number2;
    private final ModelRenderer Two;
    private final ModelRenderer Three;
    private final ModelRenderer headplate2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Chest2;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftHand2;
    private final ModelRenderer Crotch;
    private final ModelRenderer tail;
    private final ModelRenderer tail2;
    private final ModelRenderer tail3;
    private final ModelRenderer tail4;
    private final ModelRenderer tail5;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer LeftLeg2;
    private final ModelRenderer LeftFoot2;

    public EchoesAct2Model() {
        textureWidth = 128;
        textureHeight = 128;

        EchoesAct2 = new ModelRenderer(this);
        EchoesAct2.setRotationPoint(0.0F, 24.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -36.5F, 0.0F);
        EchoesAct2.addChild(HeadBase);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 15.5F, -2.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -10.2F, -4.0F, 8.0F, 8.0F, 9.0F, 0.0F, false);
        Head.setTextureOffset(33, 54).addBox(-3.0F, -11.2F, -3.0F, 6.0F, 5.0F, 7.0F, 0.0F, false);
        Head.setTextureOffset(0, 105).addBox(-6.0F, -8.2F, -1.0F, 12.0F, 4.0F, 4.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(2.0F, -16.0F, 0.0F);
        Head.addChild(Eyes);
        setRotationAngle(Eyes, 0.0F, 0.0F, 0.2182F);
        Eyes.setTextureOffset(0, 0).addBox(-3.449F, 8.9228F, -4.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Eyes3 = new ModelRenderer(this);
        Eyes3.setRotationPoint(-2.0F, -16.0F, 0.0F);
        Head.addChild(Eyes3);
        setRotationAngle(Eyes3, 0.0F, 0.0F, -0.2182F);
        Eyes3.setTextureOffset(0, 0).addBox(1.4246F, 8.9174F, -4.1F, 2.0F, 2.0F, 1.0F, 0.0F, true);

        Eyes2 = new ModelRenderer(this);
        Eyes2.setRotationPoint(0.0F, -4.0F, -5.0F);
        Head.addChild(Eyes2);
        Eyes2.setTextureOffset(0, 19).addBox(-2.0F, -2.2F, -0.2F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(27, 74).addBox(-1.75F, 0.55F, -0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(27, 74).addBox(0.75F, 0.55F, -0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(27, 74).addBox(-1.75F, -0.95F, -0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(27, 74).addBox(0.75F, -0.95F, -0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        headplate = new ModelRenderer(this);
        headplate.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate);
        setRotationAngle(headplate, 0.0F, 0.2182F, 0.0F);
        headplate.setTextureOffset(53, 51).addBox(-0.7836F, 5.0F, 3.0237F, 5.0F, 9.0F, 1.0F, 0.0F, false);

        headplate3 = new ModelRenderer(this);
        headplate3.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate3);
        setRotationAngle(headplate3, 0.0F, 1.1345F, 0.0F);
        headplate3.setTextureOffset(21, 19).addBox(-0.6254F, 5.0F, 4.7946F, 5.0F, 9.0F, 1.0F, 0.0F, false);

        headplate5 = new ModelRenderer(this);
        headplate5.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate5);
        setRotationAngle(headplate5, 0.0F, 1.789F, 0.0F);
        headplate5.setTextureOffset(36, 8).addBox(-0.0569F, 5.0F, 6.2602F, 5.0F, 8.0F, 1.0F, 0.0F, false);

        headplate7 = new ModelRenderer(this);
        headplate7.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate7);
        setRotationAngle(headplate7, 0.0F, -1.789F, 0.0F);
        headplate7.setTextureOffset(36, 8).addBox(-4.9431F, 5.0F, 6.2602F, 5.0F, 8.0F, 1.0F, 0.0F, true);

        headplate6 = new ModelRenderer(this);
        headplate6.setRotationPoint(-1.0F, 14.0F, 7.0F);
        HeadBase.addChild(headplate6);
        setRotationAngle(headplate6, 0.0F, 1.5272F, -0.7854F);
        headplate6.setTextureOffset(51, 21).addBox(5.2953F, 3.4142F, 5.7659F, 7.0F, 7.0F, 1.0F, 0.0F, false);
        headplate6.setTextureOffset(27, 2).addBox(7.7953F, 5.4142F, 5.8659F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        headplate8 = new ModelRenderer(this);
        headplate8.setRotationPoint(-13.0F, 14.0F, 1.0F);
        HeadBase.addChild(headplate8);
        setRotationAngle(headplate8, 0.0F, -1.5272F, 0.7854F);
        headplate8.setTextureOffset(51, 21).addBox(-5.8692F, -6.4853F, -3.8624F, 7.0F, 7.0F, 1.0F, 0.0F, true);
        headplate8.setTextureOffset(27, 2).addBox(-3.4547F, -4.5858F, -3.7591F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        headplate4 = new ModelRenderer(this);
        headplate4.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate4);
        setRotationAngle(headplate4, 0.0F, -1.1345F, 0.0F);
        headplate4.setTextureOffset(21, 19).addBox(-4.3746F, 5.0F, 4.7946F, 5.0F, 9.0F, 1.0F, 0.0F, true);

        Number2 = new ModelRenderer(this);
        Number2.setRotationPoint(-0.25F, -1.75F, 1.625F);
        headplate4.addChild(Number2);
        Number2.setTextureOffset(36, 5).addBox(-3.5659F, 8.054F, 3.3013F, 4.0F, 1.0F, 1.0F, 0.0F, true);
        Number2.setTextureOffset(36, 5).addBox(-3.7164F, 12.0F, 3.2987F, 4.0F, 1.0F, 1.0F, 0.0F, true);
        Number2.setTextureOffset(36, 5).addBox(-0.5659F, 8.554F, 3.3013F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Two = new ModelRenderer(this);
        Two.setRotationPoint(-1.6164F, 11.2665F, 3.7959F);
        Number2.addChild(Two);
        setRotationAngle(Two, 0.0F, 0.0F, 0.6109F);
        Two.setTextureOffset(36, 5).addBox(-2.0057F, -0.4655F, -0.4946F, 4.0F, 1.0F, 1.0F, 0.0F, true);

        Three = new ModelRenderer(this);
        Three.setRotationPoint(-3.2971F, 10.1194F, 3.8074F);
        Number2.addChild(Three);
        setRotationAngle(Three, 0.0F, 0.0F, 1.5708F);
        Three.setTextureOffset(36, 5).addBox(-1.5654F, -0.7311F, -0.5061F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        headplate2 = new ModelRenderer(this);
        headplate2.setRotationPoint(0.0F, 0.0F, 1.0F);
        HeadBase.addChild(headplate2);
        setRotationAngle(headplate2, 0.0F, -0.2182F, 0.0F);
        headplate2.setTextureOffset(36, 20).addBox(-4.2164F, 5.0F, 3.0237F, 5.0F, 9.0F, 1.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        EchoesAct2.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);


        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, 0.0F, 5.5F);
        BodyBase.addChild(Chest);
        Chest.setTextureOffset(14, 32).addBox(-4.5F, -23.767F, -9.7241F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        Chest2 = new ModelRenderer(this);
        Chest2.setRotationPoint(-14.0F, -5.0F, 6.5F);
        BodyBase.addChild(Chest2);
        setRotationAngle(Chest2, 0.0F, 0.0F, 0.7854F);
        Chest2.setTextureOffset(38, 31).addBox(-2.0F, -21.767F, -11.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 66).addBox(-6.5F, -5.2059F, -0.6644F, 7.0F, 4.0F, 4.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(-2.0F, 4.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-7.15F, -22.3015F, -1.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);


        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);


        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(7.3F, -27.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2182F, 0.0F, -0.5716F);
        LeftArm.setTextureOffset(0, 28).addBox(-2.9835F, 0.5455F, -1.05F, 3.0F, 6.0F, 3.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.3272F);
        LeftHand.setTextureOffset(0, 40).addBox(-1.2528F, 0.1022F, -2.4024F, 3.0F, 6.0F, 3.0F, 0.0F, true);
        LeftHand.setTextureOffset(20, 66).addBox(0.8722F, 2.1022F, -1.9024F, 1.0F, 2.0F, 2.0F, 0.0F, true);
        LeftHand.setTextureOffset(20, 66).addBox(-1.3778F, 2.1022F, -1.9024F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        LeftArm2 = new ModelRenderer(this);
        LeftArm2.setRotationPoint(-3.3F, -27.9765F, -1.3061F);
        Arms.addChild(LeftArm2);
        setRotationAngle(LeftArm2, -0.2182F, 0.0F, 0.5716F);
        LeftArm2.setTextureOffset(0, 28).addBox(-0.0165F, 0.5455F, -1.05F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        LeftHand2 = new ModelRenderer(this);
        LeftHand2.setRotationPoint(1.5624F, 5.6204F, 1.506F);
        LeftArm2.addChild(LeftHand2);
        setRotationAngle(LeftHand2, -0.1134F, 0.0F, -0.3272F);
        LeftHand2.setTextureOffset(0, 40).addBox(-1.7472F, 0.1022F, -2.4024F, 3.0F, 6.0F, 3.0F, 0.0F, false);
        LeftHand2.setTextureOffset(20, 66).addBox(-1.8722F, 2.1022F, -1.9024F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        LeftHand2.setTextureOffset(20, 66).addBox(0.3778F, 2.1022F, -1.9024F, 1.0F, 2.0F, 2.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(14, 44).addBox(-4.5F, -16.2058F, -4.347F, 9.0F, 4.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(44, 40).addBox(-1.0F, -14.2058F, -4.8F, 2.0F, 3.0F, 6.0F, 0.0F, false);
        Crotch.setTextureOffset(44, 40).addBox(-0.675F, -14.7058F, -5.05F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(44, 40).addBox(-0.25F, -14.7058F, -5.05F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 55).addBox(-5.0F, -16.2058F, -4.8F, 10.0F, 2.0F, 6.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(11.0F, -8.0F, 0.0F);
        Crotch.addChild(tail);
        tail.setTextureOffset(0, 75).addBox(-13.0F, -9.0F, 0.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(7.0F, -25.0F, 10.0F);
        Crotch.addChild(tail2);
        setRotationAngle(tail2, 1.0472F, -0.1745F, -1.5708F);
        tail2.setTextureOffset(24, 68).addBox(-12.7692F, -4.8505F, 4.4448F, 4.0F, 4.0F, 11.0F, 0.0F, false);

        tail3 = new ModelRenderer(this);
        tail3.setRotationPoint(-7.0F, -26.0F, 15.0F);
        Crotch.addChild(tail3);
        setRotationAngle(tail3, 2.5307F, 0.3927F, -1.6144F);
        tail3.setTextureOffset(0, 89).addBox(-12.7692F, -4.8505F, 4.4448F, 4.0F, 4.0F, 11.0F, 0.0F, false);

        tail4 = new ModelRenderer(this);
        tail4.setRotationPoint(-7.0F, -26.0F, 15.0F);
        Crotch.addChild(tail4);
        setRotationAngle(tail4, 2.5307F, 0.3927F, -1.6144F);
        tail4.setTextureOffset(10, 26).addBox(-11.7692F, -3.8505F, 15.4448F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        tail4.setTextureOffset(13, 20).addBox(-10.6442F, -3.3505F, 15.1948F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tail4.setTextureOffset(13, 20).addBox(-11.8942F, -3.3505F, 15.1948F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tail4.setTextureOffset(13, 20).addBox(-11.2692F, -2.7505F, 15.1948F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tail4.setTextureOffset(13, 20).addBox(-11.2692F, -3.9255F, 15.1948F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        tail5 = new ModelRenderer(this);
        tail5.setRotationPoint(-7.0F, -25.0F, 14.0F);
        Crotch.addChild(tail5);
        setRotationAngle(tail5, 2.5307F, 0.3927F, -1.6144F);
        tail5.setTextureOffset(27, 84).addBox(-10.7692F, -2.7F, 16.4448F, 1.0F, 1.0F, 10.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
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
        LeftLeg.setRotationPoint(2.275F, -14.25F, -1.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(51, 9).addBox(-1.0055F, 1.8005F, -1.4456F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(50, 30).addBox(-1.1887F, -7.1713F, 0.2958F, 3.0F, 6.0F, 3.0F, 0.0F, false);
        LeftFoot.setTextureOffset(0, 51).addBox(-1.0962F, -5.3583F, 1.3546F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        LeftFoot.setTextureOffset(0, 51).addBox(-1.3212F, -5.3583F, 1.3546F, 3.0F, 2.0F, 1.0F, 0.0F, true);

        LeftLeg2 = new ModelRenderer(this);
        LeftLeg2.setRotationPoint(-2.275F, -14.25F, -1.7375F);
        Legs.addChild(LeftLeg2);
        setRotationAngle(LeftLeg2, -0.1265F, -0.1396F, 0.1745F);
        LeftLeg2.setTextureOffset(51, 9).addBox(-1.9945F, 1.8005F, -1.4456F, 3.0F, 4.0F, 3.0F, 0.0F, true);

        LeftFoot2 = new ModelRenderer(this);
        LeftFoot2.setRotationPoint(0.3875F, 12.4197F, 1.0049F);
        LeftLeg2.addChild(LeftFoot2);
        setRotationAngle(LeftFoot2, 0.4058F, 0.0F, -0.0873F);
        LeftFoot2.setTextureOffset(50, 30).addBox(-1.8113F, -7.1713F, 0.2958F, 3.0F, 6.0F, 3.0F, 0.0F, true);
        LeftFoot2.setTextureOffset(0, 51).addBox(-1.9038F, -5.3583F, 1.3546F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        LeftFoot2.setTextureOffset(0, 51).addBox(-1.7288F, -5.3583F, 1.3546F, 3.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(EchoesAct2Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    protected ModelRenderer getHead() {
        return Head;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        EchoesAct2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}