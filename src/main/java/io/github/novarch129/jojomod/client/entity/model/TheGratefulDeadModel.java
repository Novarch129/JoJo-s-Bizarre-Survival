package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TheGratefulDeadEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheGratefulDeadModel extends AbstractStandModel<TheGratefulDeadEntity> {
    private final ModelRenderer TheGratefulDead;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer moutpiece;
    private final ModelRenderer foreheadeye;
    private final ModelRenderer Eyes1;
    private final ModelRenderer Eyes4;
    private final ModelRenderer Eyes2;
    private final ModelRenderer Eyes3;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Eyes6;
    private final ModelRenderer Eyes5;
    private final ModelRenderer chesteyes1;
    private final ModelRenderer chesteyes2;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer rightclaw;
    private final ModelRenderer righteyes;
    private final ModelRenderer LeftArm;
    private final ModelRenderer leftclaw;
    private final ModelRenderer LeftHand;
    private final ModelRenderer lefteyes;

    public TheGratefulDeadModel() {
        textureWidth = 128;
        textureHeight = 128;

        TheGratefulDead = new ModelRenderer(this);
        TheGratefulDead.setRotationPoint(0.0F, 24.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -27.25F, -3.0F);
        TheGratefulDead.addChild(HeadBase);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -0.75F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -8.2F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, false);

        moutpiece = new ModelRenderer(this);
        moutpiece.setRotationPoint(0.0F, 1.0F, -5.0F);
        Head.addChild(moutpiece);
        moutpiece.setTextureOffset(31, 53).addBox(-1.5F, -3.0F, 0.5F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        moutpiece.setTextureOffset(31, 53).addBox(-0.5F, -3.5F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        moutpiece.setTextureOffset(31, 53).addBox(-1.0F, -3.1F, 0.1F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        foreheadeye = new ModelRenderer(this);
        foreheadeye.setRotationPoint(0.0F, -10.0F, -4.0F);
        Head.addChild(foreheadeye);
        foreheadeye.setTextureOffset(13, 104).addBox(-0.5F, 1.5F, -0.5F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        Eyes1 = new ModelRenderer(this);
        Eyes1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        Head.addChild(Eyes1);
        Eyes1.setTextureOffset(0, 0).addBox(2.5F, -4.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes1.setTextureOffset(23, 84).addBox(3.0F, -4.0F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes1.setTextureOffset(23, 84).addBox(-2.0F, -4.0F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes1.setTextureOffset(0, 0).addBox(-2.5F, -4.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes4 = new ModelRenderer(this);
        Eyes4.setRotationPoint(-1.0F, 0.0F, 8.0F);
        Head.addChild(Eyes4);
        Eyes4.setTextureOffset(13, 103).addBox(1.5F, -3.0F, -4.625F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes4.setTextureOffset(13, 103).addBox(-2.5F, -6.0F, -4.575F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes4.setTextureOffset(23, 84).addBox(-1.25F, -6.0F, -4.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes4.setTextureOffset(23, 84).addBox(2.75F, -3.0F, -4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes4.setTextureOffset(23, 84).addBox(-1.75F, -6.0F, -4.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes4.setTextureOffset(23, 84).addBox(2.25F, -3.0F, -4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes2 = new ModelRenderer(this);
        Eyes2.setRotationPoint(-15.0F, 0.0F, 0.0F);
        Head.addChild(Eyes2);
        setRotationAngle(Eyes2, 0.0F, 0.0F, 0.3229F);
        Eyes2.setTextureOffset(23, 84).addBox(16.175F, -6.5F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes2.setTextureOffset(23, 84).addBox(9.5212F, -10.0108F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes2.setTextureOffset(0, 0).addBox(15.65F, -6.5F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes2.setTextureOffset(0, 0).addBox(9.0F, -10.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes3 = new ModelRenderer(this);
        Eyes3.setRotationPoint(1.0F, 1.0F, 0.0F);
        Head.addChild(Eyes3);
        setRotationAngle(Eyes3, 0.0F, 0.0F, -0.4625F);
        Eyes3.setTextureOffset(23, 84).addBox(4.0F, -6.0F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes3.setTextureOffset(23, 84).addBox(-3.1288F, -3.5108F, -4.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes3.setTextureOffset(0, 0).addBox(-3.65F, -3.5F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes3.setTextureOffset(0, 0).addBox(3.5F, -6.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TheGratefulDead.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -26.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.1134F, 0.0F, 0.0F);
        Torso.setTextureOffset(63, 1).addBox(-8.0F, -1.7238F, -2.8066F, 16.0F, 5.0F, 6.0F, 0.0F, false);

        Eyes6 = new ModelRenderer(this);
        Eyes6.setRotationPoint(-1.0F, 3.9749F, 8.1918F);
        Torso.addChild(Eyes6);
        setRotationAngle(Eyes6, 0.0F, 0.0F, 0.3054F);
        Eyes6.setTextureOffset(13, 103).addBox(2.5F, 0.0F, -6.1F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes6.setTextureOffset(13, 103).addBox(-4.5F, -3.0F, -5.8F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes6.setTextureOffset(23, 84).addBox(3.25F, 0.0251F, -6.0918F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes6.setTextureOffset(23, 84).addBox(-3.75F, -3.0249F, -5.7668F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes6.setTextureOffset(23, 84).addBox(3.75F, 0.0251F, -6.0918F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes6.setTextureOffset(23, 84).addBox(-3.25F, -3.0249F, -5.7668F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes5 = new ModelRenderer(this);
        Eyes5.setRotationPoint(-1.0F, 4.5F, 7.0F);
        Torso.addChild(Eyes5);
        setRotationAngle(Eyes5, 0.0F, 0.0F, -0.3054F);
        Eyes5.setTextureOffset(23, 84).addBox(4.25F, -3.975F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes5.setTextureOffset(23, 84).addBox(-2.75F, 0.95F, -4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes5.setTextureOffset(23, 84).addBox(4.75F, -3.975F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes5.setTextureOffset(23, 84).addBox(-2.25F, 0.95F, -4.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes5.setTextureOffset(13, 103).addBox(3.4915F, -3.973F, -4.3484F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes5.setTextureOffset(13, 103).addBox(-3.483F, 0.946F, -4.6032F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        chesteyes1 = new ModelRenderer(this);
        chesteyes1.setRotationPoint(-1.0F, -5.0F, -4.5F);
        BodyBase.addChild(chesteyes1);
        setRotationAngle(chesteyes1, -0.3491F, 0.0F, 0.0873F);
        chesteyes1.setTextureOffset(23, 84).addBox(1.75F, -20.5F, -7.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes1.setTextureOffset(23, 84).addBox(-4.325F, -17.15F, -7.225F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes1.setTextureOffset(23, 84).addBox(2.25F, -20.5F, -7.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes1.setTextureOffset(23, 84).addBox(-3.825F, -17.15F, -7.225F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes1.setTextureOffset(13, 103).addBox(1.0F, -20.5F, -7.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes1.setTextureOffset(13, 103).addBox(-5.1705F, -17.1556F, -7.2119F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        chesteyes2 = new ModelRenderer(this);
        chesteyes2.setRotationPoint(4.0F, -4.0F, -1.5F);
        BodyBase.addChild(chesteyes2);
        setRotationAngle(chesteyes2, -0.2618F, -0.0873F, -0.2182F);
        chesteyes2.setTextureOffset(23, 84).addBox(-2.75F, -22.0F, -9.05F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes2.setTextureOffset(23, 84).addBox(3.25F, -18.0F, -9.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes2.setTextureOffset(23, 84).addBox(-3.25F, -22.0F, -9.05F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes2.setTextureOffset(23, 84).addBox(2.75F, -18.0F, -9.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes2.setTextureOffset(13, 103).addBox(-4.0F, -22.0F, -9.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        chesteyes2.setTextureOffset(13, 103).addBox(2.0F, -18.0F, -9.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -4.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(53, 21).addBox(-5.5F, -21.2874F, -9.6688F, 11.0F, 6.0F, 6.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 64).addBox(-7.5F, -8.2146F, -1.6731F, 9.0F, 4.0F, 6.0F, 0.0F, false);
        Abs.setTextureOffset(48, 37).addBox(-6.0F, -4.9738F, -0.9827F, 6.0F, 3.0F, 5.0F, 0.0F, false);
        Abs.setTextureOffset(7, 81).addBox(-2.0F, -3.9826F, 1.9999F, 1.0F, 8.0F, 1.0F, 0.0F, false);
        Abs.setTextureOffset(7, 81).addBox(-5.0F, -2.0001F, -0.0174F, 1.0F, 7.0F, 1.0F, 0.0F, false);
        Abs.setTextureOffset(7, 81).addBox(-4.0F, -1.9739F, 3.0086F, 1.0F, 8.0F, 1.0F, 0.0F, false);
        Abs.setTextureOffset(7, 81).addBox(-3.0F, -2.0F, 0.0087F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-7.15F, -29.3015F, -2.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.0374F, 0.1051F, 0.1401F);
        RightArm.setTextureOffset(1, 20).addBox(-1.9471F, 2.5275F, -0.983F, 5.0F, 11.0F, 5.0F, 0.0F, false);
        RightArm.setTextureOffset(34, 0).addBox(-2.35F, 0.0F, -2.0F, 4.0F, 8.0F, 7.0F, 0.0F, false);
        RightArm.setTextureOffset(18, 93).addBox(-2.75F, 2.6461F, -0.1939F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, 0.0175F, -0.0436F, -0.1134F);
        RightHand.setTextureOffset(0, 40).addBox(-3.2054F, 6.2479F, -2.4159F, 4.0F, 13.0F, 4.0F, 0.0F, false);
        RightHand.setTextureOffset(31, 53).addBox(-4.088F, 5.4808F, -1.5109F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        rightclaw = new ModelRenderer(this);
        rightclaw.setRotationPoint(-4.721F, 30.9243F, 2.471F);
        RightArm.addChild(rightclaw);
        setRotationAngle(rightclaw, 0.0436F, -0.0436F, -0.1309F);
        rightclaw.setTextureOffset(26, 24).addBox(3.7156F, -5.419F, -3.7536F, 6.0F, 4.0F, 6.0F, 0.0F, false);
        rightclaw.setTextureOffset(25, 42).addBox(5.8129F, -1.859F, -6.4532F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        rightclaw.setTextureOffset(25, 42).addBox(0.9858F, -2.0005F, -1.3348F, 5.0F, 1.0F, 2.0F, 0.0F, false);
        rightclaw.setTextureOffset(25, 42).addBox(7.0454F, -1.9973F, -0.9313F, 5.0F, 1.0F, 2.0F, 0.0F, false);

        righteyes = new ModelRenderer(this);
        righteyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        RightArm.addChild(righteyes);
        righteyes.setTextureOffset(23, 84).addBox(-2.6F, 10.3765F, 0.7561F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-2.6F, 10.3765F, 0.2561F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(0.55F, 17.8015F, 3.2811F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(0.75F, 17.1765F, -1.0189F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(0.05F, 17.8015F, 3.2811F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(0.25F, 17.1765F, -1.0189F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-0.775F, 11.1265F, 3.3311F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-0.525F, 9.2515F, -1.2689F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(2.175F, 17.9515F, 1.2811F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-1.075F, 20.6015F, 1.1061F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-0.775F, 10.6265F, 3.3311F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-0.525F, 8.7515F, -1.2689F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(2.175F, 17.4515F, 1.2811F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(23, 84).addBox(-1.075F, 20.1015F, 1.1061F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-0.7714F, 9.9106F, 3.3061F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-2.599F, 10.3682F, -0.452F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-0.5247F, 8.1184F, -1.2486F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-1.071F, 19.2467F, 1.1159F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-0.6384F, 17.8123F, 3.2505F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(-0.537F, 17.1845F, -0.9858F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        righteyes.setTextureOffset(13, 103).addBox(2.15F, 16.6799F, 1.2975F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(8.3F, -26.9765F, -2.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.0242F, -0.0904F, -0.1753F);
        LeftArm.setTextureOffset(1, 20).addBox(-3.9463F, 0.3252F, -0.5939F, 5.0F, 11.0F, 5.0F, 0.0F, true);
        LeftArm.setTextureOffset(18, 93).addBox(0.8F, 0.3211F, 0.3061F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        LeftArm.setTextureOffset(34, 0).addBox(-2.4459F, -2.257F, -1.6939F, 4.0F, 8.0F, 7.0F, 0.0F, false);

        leftclaw = new ModelRenderer(this);
        leftclaw.setRotationPoint(-9.171F, 26.5993F, 2.471F);
        LeftArm.addChild(leftclaw);
        setRotationAngle(leftclaw, 0.0436F, 0.0F, 0.1745F);
        leftclaw.setTextureOffset(26, 24).addBox(1.7156F, -5.419F, -3.7536F, 6.0F, 4.0F, 6.0F, 0.0F, false);
        leftclaw.setTextureOffset(25, 42).addBox(3.8129F, -1.859F, -6.4532F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        leftclaw.setTextureOffset(25, 42).addBox(-1.0142F, -2.0005F, -1.3348F, 5.0F, 1.0F, 2.0F, 0.0F, false);
        leftclaw.setTextureOffset(25, 42).addBox(5.0454F, -1.9973F, -0.9313F, 5.0F, 1.0F, 2.0F, 0.0F, false);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(0.0812F, 6.0454F, 1.0599F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.0261F, -0.0436F, 0.1963F);
        LeftHand.setTextureOffset(0, 39).addBox(-2.665F, 3.7558F, -0.9736F, 4.0F, 14.0F, 4.0F, 0.0F, true);
        LeftHand.setTextureOffset(31, 53).addBox(1.2266F, 3.8915F, -0.1538F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        lefteyes = new ModelRenderer(this);
        lefteyes.setRotationPoint(0.9806F, 0.1765F, -0.0846F);
        LeftArm.addChild(lefteyes);
        setRotationAngle(lefteyes, 0.0F, 0.0F, 0.0436F);
        lefteyes.setTextureOffset(23, 84).addBox(-3.2806F, 6.7F, -1.0343F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-0.3306F, 7.375F, 2.1907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-4.4306F, 20.15F, -0.6343F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-3.3806F, 14.9F, 3.2657F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-2.9306F, 6.55F, 3.9907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-1.7306F, 18.05F, 1.2907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-5.4306F, 16.55F, 0.6157F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-3.7806F, 6.7F, -1.0343F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-0.3306F, 6.875F, 2.1907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-4.4306F, 19.65F, -0.6343F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-3.3806F, 14.4F, 3.2657F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-3.4306F, 6.55F, 3.9907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-1.7306F, 18.05F, 1.7907F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(23, 84).addBox(-5.4306F, 16.55F, 1.1157F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-1.7806F, 18.049F, 0.5829F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-5.4194F, 16.5521F, -0.1138F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-0.3851F, 6.1146F, 2.1827F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-4.4148F, 18.8379F, -0.6093F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-4.5606F, 6.7017F, -0.9943F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-4.115F, 6.5616F, 3.9839F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        lefteyes.setTextureOffset(13, 103).addBox(-3.3925F, 13.6389F, 3.2599F, 1.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TheGratefulDead.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}