package io.github.novarch129.jojomod.client.entity.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.StickyFingersEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class StickyFingersModel extends AbstractStandModel<StickyFingersEntity> {
    private final ModelRenderer StickyFingers;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer helmet;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;

    public StickyFingersModel() {
        textureWidth = 128;
        textureHeight = 128;

        StickyFingers = new ModelRenderer(this);
        StickyFingers.setRotationPoint(0.0F, 24.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -29.25F, 0.0F);
        StickyFingers.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 2.25F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -10.2F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -6.2F, -4.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(2.0F, -6.2F, -4.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(-1.0F, -3.125F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        helmet = new ModelRenderer(this);
        helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(helmet);
        helmet.setTextureOffset(35, 2).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
        helmet.setTextureOffset(35, 21).addBox(-2.0F, -11.6F, -5.4F, 4.0F, 6.0F, 11.0F, 0.0F, false);
        helmet.setTextureOffset(27, 1).addBox(-1.0F, -5.7F, -5.4F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -7.5F, -6.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -10.5F, -6.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -10.5F, 4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -7.5F, 4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -12.0F, -4.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -12.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        helmet.setTextureOffset(2, 52).addBox(-1.0F, -12.0F, 3.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        StickyFingers.addChild(BodyBase);

        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(29, 40).addBox(-9.0F, -3.9063F, -2.32F, 18.0F, 5.0F, 6.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(28, 54).addBox(-5.5F, -24.8187F, -9.4068F, 11.0F, 6.0F, 6.0F, 0.0F, false);
        Chest.setTextureOffset(0, 77).addBox(-1.0F, -26.0217F, -9.5F, 2.0F, 6.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(0, 0).addBox(-1.5F, -20.9111F, -9.826F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(0, 0).addBox(0.5F, -20.9111F, -9.826F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(0, 0).addBox(-0.5F, -18.9111F, -9.826F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(20, 20).addBox(-0.5F, -19.9111F, -9.751F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(0, 0).addBox(-0.5F, -20.9111F, -9.826F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 60).addBox(-6.5F, -11.2058F, -0.6557F, 7.0F, 6.0F, 4.0F, 0.0F, false);
        Abs.setTextureOffset(0, 0).addBox(-3.5F, -9.0084F, -0.9304F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-6.15F, -28.3015F, -2.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, 0.0763F, -0.0886F, 0.2467F);
        RightArm.setTextureOffset(2, 20).addBox(-1.4648F, 2.6615F, -0.483F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
        RightHand.setTextureOffset(2, 35).addBox(-2.5085F, 1.4042F, -2.1291F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        RightHand.setTextureOffset(10, 72).addBox(-3.1923F, 0.5132F, -2.6207F, 3.0F, 8.0F, 5.0F, 0.0F, false);
        RightHand.setTextureOffset(21, 26).addBox(-3.4958F, 5.6465F, -1.0607F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        RightHand.setTextureOffset(0, 0).addBox(-3.4958F, 1.8217F, -0.5552F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        RightHand.setTextureOffset(31, 80).addBox(-0.2872F, 6.2811F, -2.7071F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        RightHand.setTextureOffset(31, 80).addBox(-0.3458F, 3.308F, -2.7576F, 2.0F, 1.0F, 5.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(7.3F, -26.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.0328F, -0.0941F, -0.238F);
        LeftArm.setTextureOffset(2, 20).addBox(-3.2465F, 1.4774F, -0.8003F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5667F, 6.8452F, 0.7989F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.2399F);
        LeftHand.setTextureOffset(2, 35).addBox(-1.7333F, 0.0347F, -1.4928F, 4.0F, 8.0F, 4.0F, 0.0F, true);
        LeftHand.setTextureOffset(10, 72).addBox(0.002F, -1.0387F, -1.8928F, 3.0F, 8.0F, 5.0F, 0.0F, false);
        LeftHand.setTextureOffset(21, 26).addBox(2.2667F, 3.964F, -0.4151F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        LeftHand.setTextureOffset(0, 0).addBox(2.1833F, 0.1302F, 0.1072F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        LeftHand.setTextureOffset(31, 80).addBox(-1.8663F, 4.6611F, -1.9928F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        LeftHand.setTextureOffset(31, 80).addBox(-1.8743F, 1.7031F, -1.9928F, 2.0F, 1.0F, 5.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 4.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-1.0F, -19.5F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-1.5F, -18.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-1.5F, -17.0F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-2.0F, -16.0F, -5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(1.0F, -16.0F, -5.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-1.0F, -13.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(20, 19).addBox(-1.0F, -15.0F, -4.925F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(0, 0).addBox(-1.0F, -16.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, -0.0455F, 0.2653F, -0.0036F);
        RightLeg.setTextureOffset(69, 20).addBox(-1.8725F, -1.109F, -1.5708F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(-0.7616F, 13.4615F, -2.2432F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.1789F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(80, 41).addBox(-1.2639F, -8.9106F, 1.3327F, 4.0F, 11.0F, 5.0F, 0.0F, false);
        RightFoot.setTextureOffset(1, 75).addBox(0.2361F, -7.8441F, 1.1331F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(69, 20).addBox(-2.0055F, -3.1995F, -1.4456F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.1092F, 12.6696F, -0.9598F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.1004F, 0.0F, 0.1309F);
        LeftFoot.setTextureOffset(80, 41).addBox(-2.7658F, -9.1713F, -0.3027F, 4.0F, 11.0F, 5.0F, 0.0F, false);
        LeftFoot.setTextureOffset(1, 75).addBox(-1.1658F, -7.9933F, -0.5027F, 1.0F, 8.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        StickyFingers.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}