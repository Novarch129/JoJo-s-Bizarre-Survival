package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class HierophantGreenModel extends AbstractStandModel<HierophantGreenEntity> {
    private final ModelRenderer HierophantGreen;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer Mask;
    private final ModelRenderer MouthGuard;
    private final ModelRenderer ForeheadGuard;
    private final ModelRenderer FGPart1;
    private final ModelRenderer FGPart2;
    private final ModelRenderer FGPart3;
    private final ModelRenderer Lines;
    private final ModelRenderer Right;
    private final ModelRenderer Line3;
    private final ModelRenderer Line4;
    private final ModelRenderer Line5;
    private final ModelRenderer Line6;
    private final ModelRenderer Line7;
    private final ModelRenderer Line2;
    private final ModelRenderer Left;
    private final ModelRenderer Line8;
    private final ModelRenderer Line9;
    private final ModelRenderer Line10;
    private final ModelRenderer Line11;
    private final ModelRenderer Line12;
    private final ModelRenderer Line13;
    private final ModelRenderer Line;
    private final ModelRenderer Ears;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer ChestGuard;
    private final ModelRenderer Triangle;
    private final ModelRenderer TPart1;
    private final ModelRenderer TPart3;
    private final ModelRenderer TPart4;
    private final ModelRenderer TPart5;
    private final ModelRenderer TPart2;
    private final ModelRenderer ChestLines;
    private final ModelRenderer LeftLines;
    private final ModelRenderer ChestLine1;
    private final ModelRenderer ChestLine5;
    private final ModelRenderer ChestLine2;
    private final ModelRenderer ChestLine3;
    private final ModelRenderer ChestLine4;
    private final ModelRenderer RightLines;
    private final ModelRenderer ChestLine7;
    private final ModelRenderer ChestLine8;
    private final ModelRenderer ChestLine9;
    private final ModelRenderer ChestLine10;
    private final ModelRenderer ChestLine11;
    private final ModelRenderer ChestLine12;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer RightArm2;
    private final ModelRenderer RightHand2;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;

    public HierophantGreenModel() {
        textureWidth = 128;
        textureHeight = 128;

        HierophantGreen = new ModelRenderer(this);
        HierophantGreen.setRotationPoint(0.0F, 24.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0, -28, 0);
        HierophantGreen.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -6.0F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -4.2F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(118, 26).addBox(-1.5F, -0.2F, 2.45F, 3.0F, 3.0F, 2.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -1.0F, -4.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.25F, -0.45F, -0.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.25F, -0.45F, -0.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Mask = new ModelRenderer(this);
        Mask.setRotationPoint(0.0F, 4.0F, 0.25F);
        Eyes.addChild(Mask);
        setRotationAngle(Mask, 0.1745F, 0.0F, 0.0F);


        MouthGuard = new ModelRenderer(this);
        MouthGuard.setRotationPoint(0.0F, 0.0F, 0.0F);
        Mask.addChild(MouthGuard);
        MouthGuard.setTextureOffset(105, 12).addBox(-1.0F, -2.75F, -0.6F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        MouthGuard.setTextureOffset(105, 12).addBox(-1.0F, -0.25F, -0.6F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ForeheadGuard = new ModelRenderer(this);
        ForeheadGuard.setRotationPoint(0.35F, 0.475F, -0.075F);
        Mask.addChild(ForeheadGuard);
        setRotationAngle(ForeheadGuard, -0.1658F, 0.0F, 0.0F);


        FGPart1 = new ModelRenderer(this);
        FGPart1.setRotationPoint(-0.7F, -5.2F, -0.6F);
        ForeheadGuard.addChild(FGPart1);
        setRotationAngle(FGPart1, 0.0F, 0.0F, -0.7854F);
        FGPart1.setTextureOffset(105, 12).addBox(-0.505F, -1.255F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        FGPart2 = new ModelRenderer(this);
        FGPart2.setRotationPoint(0.0F, -5.2F, -0.6F);
        ForeheadGuard.addChild(FGPart2);
        setRotationAngle(FGPart2, 0.0F, 0.0F, 0.7854F);
        FGPart2.setTextureOffset(105, 12).addBox(-0.5F, -1.25F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        FGPart2.setTextureOffset(105, 12).addBox(-0.75F, -0.25F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        FGPart3 = new ModelRenderer(this);
        FGPart3.setRotationPoint(0.0F, -5.725F, -0.6F);
        ForeheadGuard.addChild(FGPart3);
        setRotationAngle(FGPart3, 0.0F, 0.0F, 1.5708F);
        FGPart3.setTextureOffset(105, 12).addBox(-1.0053F, -0.0555F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        FGPart3.setTextureOffset(105, 12).addBox(-1.0053F, -1.2374F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Lines = new ModelRenderer(this);
        Lines.setRotationPoint(0.0F, 0.0F, 0.0F);
        Mask.addChild(Lines);


        Right = new ModelRenderer(this);
        Right.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lines.addChild(Right);


        Line3 = new ModelRenderer(this);
        Line3.setRotationPoint(-2.0F, -6.2F, 1.275F);
        Right.addChild(Line3);
        setRotationAngle(Line3, -0.0873F, 0.0F, -0.9599F);
        Line3.setTextureOffset(105, 12).addBox(-0.529F, -1.0867F, -0.6564F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        Line4 = new ModelRenderer(this);
        Line4.setRotationPoint(-4.8083F, -6.7824F, 1.4378F);
        Right.addChild(Line4);
        setRotationAngle(Line4, -0.0873F, -0.1222F, -1.5839F);
        Line4.setTextureOffset(105, 12).addBox(-0.5872F, 1.2094F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line4.setTextureOffset(105, 12).addBox(-0.5872F, 0.4594F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line4.setTextureOffset(105, 12).addBox(-0.8872F, 0.4594F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Line5 = new ModelRenderer(this);
        Line5.setRotationPoint(-4.7543F, -6.7701F, 1.1889F);
        Right.addChild(Line5);
        setRotationAngle(Line5, 0.0175F, -0.3796F, -1.5839F);
        Line5.setTextureOffset(105, 12).addBox(-0.7533F, 0.4518F, 0.609F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        Line5.setTextureOffset(105, 12).addBox(-0.7533F, 0.4518F, 0.359F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Line6 = new ModelRenderer(this);
        Line6.setRotationPoint(-4.7543F, -6.7701F, 1.1889F);
        Right.addChild(Line6);
        setRotationAngle(Line6, 0.0175F, -0.3796F, -1.5839F);
        Line6.setTextureOffset(105, 12).addBox(-1.6192F, 0.4384F, 4.8412F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        Line7 = new ModelRenderer(this);
        Line7.setRotationPoint(-4.7543F, -6.7701F, 1.1889F);
        Right.addChild(Line7);
        setRotationAngle(Line7, 0.0175F, -0.3796F, -1.5839F);
        Line7.setTextureOffset(105, 12).addBox(-1.6192F, 0.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line7.setTextureOffset(105, 12).addBox(-2.1192F, 0.9384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line7.setTextureOffset(105, 12).addBox(-2.6192F, 1.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line7.setTextureOffset(105, 12).addBox(-3.1192F, 1.9384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Line7.setTextureOffset(105, 12).addBox(-3.6192F, 2.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Line2 = new ModelRenderer(this);
        Line2.setRotationPoint(-4.7795F, -6.3625F, 3.1468F);
        Right.addChild(Line2);
        setRotationAngle(Line2, 0.0175F, -0.9032F, -1.5839F);
        Line2.setTextureOffset(105, 12).addBox(-0.2636F, 0.445F, 1.4691F, 1.0F, 1.0F, 2.0F, 0.0F, true);

        Left = new ModelRenderer(this);
        Left.setRotationPoint(0.0F, 0.0F, 0.0F);
        Lines.addChild(Left);


        Line8 = new ModelRenderer(this);
        Line8.setRotationPoint(2.0F, -6.2F, 1.275F);
        Left.addChild(Line8);
        setRotationAngle(Line8, -0.0873F, 0.0F, 0.9599F);
        Line8.setTextureOffset(105, 12).addBox(-0.471F, -1.0867F, -0.6564F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Line9 = new ModelRenderer(this);
        Line9.setRotationPoint(4.8083F, -6.7824F, 1.4378F);
        Left.addChild(Line9);
        setRotationAngle(Line9, -0.0873F, 0.1222F, 1.5839F);
        Line9.setTextureOffset(105, 12).addBox(-0.4128F, 1.2094F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line9.setTextureOffset(105, 12).addBox(-0.4128F, 0.4594F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line9.setTextureOffset(105, 12).addBox(-0.0878F, 0.4594F, -0.5286F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Line10 = new ModelRenderer(this);
        Line10.setRotationPoint(4.7543F, -6.7701F, 1.1889F);
        Left.addChild(Line10);
        setRotationAngle(Line10, 0.0175F, 0.3796F, 1.5839F);
        Line10.setTextureOffset(105, 12).addBox(-0.2467F, 0.4518F, 0.609F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        Line10.setTextureOffset(105, 12).addBox(-0.2467F, 0.4518F, 0.359F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Line11 = new ModelRenderer(this);
        Line11.setRotationPoint(4.7543F, -6.7701F, 1.1889F);
        Left.addChild(Line11);
        setRotationAngle(Line11, 0.0175F, 0.3796F, 1.5839F);
        Line11.setTextureOffset(105, 12).addBox(0.6192F, 0.4384F, 4.8412F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        Line12 = new ModelRenderer(this);
        Line12.setRotationPoint(4.7543F, -6.7701F, 1.1889F);
        Left.addChild(Line12);
        setRotationAngle(Line12, 0.0175F, 0.3796F, 1.5839F);
        Line12.setTextureOffset(105, 12).addBox(0.6192F, 0.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line12.setTextureOffset(105, 12).addBox(1.1192F, 0.9384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line12.setTextureOffset(105, 12).addBox(1.6192F, 1.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line12.setTextureOffset(105, 12).addBox(2.1192F, 1.9384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line12.setTextureOffset(105, 12).addBox(2.6192F, 2.4384F, 7.7662F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Line13 = new ModelRenderer(this);
        Line13.setRotationPoint(4.7795F, -6.3625F, 3.1468F);
        Left.addChild(Line13);
        setRotationAngle(Line13, 0.0175F, 0.9032F, 1.5839F);
        Line13.setTextureOffset(105, 12).addBox(-0.7364F, 0.445F, 1.4691F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        Line = new ModelRenderer(this);
        Line.setRotationPoint(0.0F, 0.55F, 0.0F);
        Lines.addChild(Line);
        setRotationAngle(Line, -0.1745F, 0.0F, 0.0F);
        Line.setTextureOffset(105, 12).addBox(-0.5F, -4.75F, -0.6F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Line.setTextureOffset(105, 12).addBox(-0.5F, -7.75F, -0.6F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Line.setTextureOffset(105, 12).addBox(-0.5F, -7.0F, 7.025F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        Line.setTextureOffset(105, 12).addBox(-0.5F, -8.0F, -0.6F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        Line.setTextureOffset(105, 12).addBox(-0.5F, -8.0F, 7.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Ears = new ModelRenderer(this);
        Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Ears);
        Ears.setTextureOffset(118, 26).addBox(-4.325F, -1.45F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        Ears.setTextureOffset(118, 26).addBox(3.325F, -1.45F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        HierophantGreen.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -27.875F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(26, 18).addBox(-10.0F, -2.5721F, -1.9874F, 20.0F, 5.0F, 6.0F, 0.0F, false);
        Torso.setTextureOffset(0, 67).addBox(-1.5F, -0.0811F, 3.5421F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        Torso.setTextureOffset(0, 67).addBox(-1.5F, -0.3311F, 3.5421F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(-0.025F, -27.875F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(45, 31).addBox(-5.475F, -2.1776F, -1.8308F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        ChestGuard = new ModelRenderer(this);
        ChestGuard.setRotationPoint(0.0F, -0.225F, -0.025F);
        Chest.addChild(ChestGuard);
        setRotationAngle(ChestGuard, 0.2094F, 0.0F, 0.0F);


        Triangle = new ModelRenderer(this);
        Triangle.setRotationPoint(0.375F, 9.35F, -3.325F);
        ChestGuard.addChild(Triangle);
        setRotationAngle(Triangle, -0.1658F, 0.0F, 0.0F);


        TPart1 = new ModelRenderer(this);
        TPart1.setRotationPoint(-0.7F, -5.2F, -0.6F);
        Triangle.addChild(TPart1);
        setRotationAngle(TPart1, 0.0F, 0.0F, -0.7854F);
        TPart1.setTextureOffset(109, 122).addBox(-0.505F, -4.255F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        TPart3 = new ModelRenderer(this);
        TPart3.setRotationPoint(-2.8213F, -8.9509F, -3.1358F);
        Triangle.addChild(TPart3);
        setRotationAngle(TPart3, 0.0F, 0.0F, 0.7854F);
        TPart3.setTextureOffset(109, 122).addBox(-0.1027F, -0.8427F, 2.5358F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        TPart4 = new ModelRenderer(this);
        TPart4.setRotationPoint(2.1213F, -8.9509F, -3.1358F);
        Triangle.addChild(TPart4);
        setRotationAngle(TPart4, 0.0F, 0.0F, -0.7854F);
        TPart4.setTextureOffset(109, 122).addBox(-4.8973F, -0.8427F, 2.5358F, 5.0F, 2.0F, 1.0F, 0.0F, true);

        TPart5 = new ModelRenderer(this);
        TPart5.setRotationPoint(-0.5858F, -9.5753F, -2.8039F);
        Triangle.addChild(TPart5);
        setRotationAngle(TPart5, 0.0F, 0.0F, -1.5708F);
        TPart5.setTextureOffset(109, 122).addBox(-1.9559F, -0.8161F, 2.2039F, 2.0F, 3.0F, 1.0F, 0.0F, true);
        TPart5.setTextureOffset(109, 122).addBox(-0.2059F, -1.3161F, 2.4539F, 1.0F, 3.0F, 1.0F, 0.0F, true);
        TPart5.setTextureOffset(109, 122).addBox(-0.9559F, -1.7123F, 2.2039F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        TPart2 = new ModelRenderer(this);
        TPart2.setRotationPoint(0.0F, -5.2F, -0.6F);
        Triangle.addChild(TPart2);
        setRotationAngle(TPart2, 0.0F, 0.0F, 0.7854F);
        TPart2.setTextureOffset(109, 122).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, true);
        TPart2.setTextureOffset(109, 122).addBox(-0.5F, -4.25F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        ChestLines = new ModelRenderer(this);
        ChestLines.setRotationPoint(0.0F, 0.0F, 0.0F);
        ChestGuard.addChild(ChestLines);


        LeftLines = new ModelRenderer(this);
        LeftLines.setRotationPoint(0.0F, 0.0F, 0.0F);
        ChestLines.addChild(LeftLines);


        ChestLine1 = new ModelRenderer(this);
        ChestLine1.setRotationPoint(3.6056F, -1.2291F, -0.4668F);
        LeftLines.addChild(ChestLine1);
        setRotationAngle(ChestLine1, 0.0F, 0.0F, 0.7461F);
        ChestLine1.setTextureOffset(77, 114).addBox(0.9514F, -4.0778F, -2.1541F, 1.0F, 6.0F, 1.0F, 0.0F, true);
        ChestLine1.setTextureOffset(77, 114).addBox(0.9514F, -4.0778F, -1.1541F, 1.0F, 1.0F, 6.0F, 0.0F, true);
        ChestLine1.setTextureOffset(77, 114).addBox(0.9514F, -4.0778F, 4.8459F, 1.0F, 6.0F, 1.0F, 0.0F, true);

        ChestLine5 = new ModelRenderer(this);
        ChestLine5.setRotationPoint(3.6056F, -1.2291F, -0.4668F);
        LeftLines.addChild(ChestLine5);
        setRotationAngle(ChestLine5, 0.0F, 0.0F, 1.5315F);
        ChestLine5.setTextureOffset(77, 114).addBox(1.739F, -0.0207F, 4.8459F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        ChestLine5.setTextureOffset(77, 114).addBox(2.239F, 0.4793F, 4.5959F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        ChestLine5.setTextureOffset(77, 114).addBox(2.739F, 0.9793F, 4.3459F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        ChestLine5.setTextureOffset(77, 114).addBox(3.239F, 1.4793F, 4.0959F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        ChestLine2 = new ModelRenderer(this);
        ChestLine2.setRotationPoint(2.3344F, -2.0583F, -0.3723F);
        LeftLines.addChild(ChestLine2);
        setRotationAngle(ChestLine2, 0.0F, 0.0F, 0.7461F);
        ChestLine2.setTextureOffset(77, 114).addBox(0.9514F, -3.0778F, -2.1541F, 1.0F, 5.0F, 1.0F, 0.0F, true);
        ChestLine2.setTextureOffset(77, 114).addBox(0.9514F, -3.0778F, -1.1541F, 1.0F, 1.0F, 7.0F, 0.0F, true);

        ChestLine3 = new ModelRenderer(this);
        ChestLine3.setRotationPoint(1.6556F, -1.3287F, -0.4554F);
        LeftLines.addChild(ChestLine3);
        setRotationAngle(ChestLine3, 0.0F, 0.0F, 0.7461F);
        ChestLine3.setTextureOffset(77, 114).addBox(0.9514F, 0.9222F, -1.1541F, 1.0F, 1.0F, 0.0F, 0.0F, true);
        ChestLine3.setTextureOffset(77, 114).addBox(0.9546F, -3.0744F, 4.9291F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        ChestLine4 = new ModelRenderer(this);
        ChestLine4.setRotationPoint(1.6556F, -1.3287F, -0.4554F);
        LeftLines.addChild(ChestLine4);
        setRotationAngle(ChestLine4, -0.3491F, 0.0F, 0.7461F);
        ChestLine4.setTextureOffset(77, 114).addBox(0.9546F, -2.0978F, 4.5461F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        RightLines = new ModelRenderer(this);
        RightLines.setRotationPoint(0.05F, 0.0F, 0.0F);
        ChestLines.addChild(RightLines);


        ChestLine7 = new ModelRenderer(this);
        ChestLine7.setRotationPoint(-3.6056F, -1.2291F, -0.4668F);
        RightLines.addChild(ChestLine7);
        setRotationAngle(ChestLine7, 0.0F, 0.0F, -0.7461F);
        ChestLine7.setTextureOffset(77, 114).addBox(-1.9514F, -4.0778F, -2.1541F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        ChestLine7.setTextureOffset(77, 114).addBox(-1.9514F, -4.0778F, -1.1541F, 1.0F, 1.0F, 6.0F, 0.0F, false);
        ChestLine7.setTextureOffset(77, 114).addBox(-1.9514F, -4.0778F, 4.8459F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        ChestLine8 = new ModelRenderer(this);
        ChestLine8.setRotationPoint(-4.6422F, -2.0964F, 1.1768F);
        RightLines.addChild(ChestLine8);
        setRotationAngle(ChestLine8, -0.4581F, 0.0F, -0.7461F);


        ChestLine9 = new ModelRenderer(this);
        ChestLine9.setRotationPoint(-3.6056F, -1.2291F, -0.4668F);
        RightLines.addChild(ChestLine9);
        setRotationAngle(ChestLine9, 0.0F, 0.0F, -1.5315F);
        ChestLine9.setTextureOffset(77, 114).addBox(-2.739F, -0.0207F, 4.8459F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        ChestLine9.setTextureOffset(77, 114).addBox(-3.239F, 0.4793F, 4.5959F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        ChestLine9.setTextureOffset(77, 114).addBox(-3.739F, 0.9793F, 4.3459F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        ChestLine9.setTextureOffset(77, 114).addBox(-4.239F, 1.4793F, 4.0959F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ChestLine10 = new ModelRenderer(this);
        ChestLine10.setRotationPoint(-2.3344F, -2.0583F, -0.3723F);
        RightLines.addChild(ChestLine10);
        setRotationAngle(ChestLine10, 0.0F, 0.0F, -0.7461F);
        ChestLine10.setTextureOffset(77, 114).addBox(-1.9514F, -3.0778F, -2.1541F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        ChestLine10.setTextureOffset(77, 114).addBox(-1.9514F, -3.0778F, -1.1541F, 1.0F, 1.0F, 7.0F, 0.0F, false);

        ChestLine11 = new ModelRenderer(this);
        ChestLine11.setRotationPoint(-1.6556F, -1.3287F, -0.4554F);
        RightLines.addChild(ChestLine11);
        setRotationAngle(ChestLine11, 0.0F, 0.0F, -0.7461F);
        ChestLine11.setTextureOffset(77, 114).addBox(-1.9514F, 0.9222F, -1.1541F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        ChestLine11.setTextureOffset(77, 114).addBox(-1.9546F, -3.0744F, 4.9291F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        ChestLine12 = new ModelRenderer(this);
        ChestLine12.setRotationPoint(-1.6556F, -1.3287F, -0.4554F);
        RightLines.addChild(ChestLine12);
        setRotationAngle(ChestLine12, -0.3491F, 0.0F, -0.7461F);
        ChestLine12.setTextureOffset(77, 114).addBox(-1.9546F, -2.0978F, 4.5461F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(0.2F, -21.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 39).addBox(-3.7F, -4.2061F, -0.7079F, 7.0F, 8.0F, 4.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-8.9F, -26.3015F, -1.0561F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
        RightArm.setTextureOffset(27, 32).addBox(-0.5509F, -0.5103F, -1.5917F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.5185F, 4.7923F, 0.2083F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
        RightHand.setTextureOffset(0, 53).addBox(-1.1414F, 0.3193F, -1.7494F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        RightArm2 = new ModelRenderer(this);
        RightArm2.setRotationPoint(7.55F, -26.4765F, -1.3061F);
        Arms.addChild(RightArm2);
        setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
        RightArm2.setTextureOffset(27, 32).addBox(-2.1284F, 0.2701F, -1.1238F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        RightHand2 = new ModelRenderer(this);
        RightHand2.setRotationPoint(1.2927F, 5.595F, 0.9322F);
        RightArm2.addChild(RightHand2);
        setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
        RightHand2.setTextureOffset(0, 53).addBox(-3.227F, 0.449F, -1.8597F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(-0.1F, -16.5F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-1.4F, -0.4558F, -4.347F, 3.0F, 2.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-1.9F, -0.2058F, -4.347F, 4.0F, 1.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-4.4F, -2.7058F, -4.347F, 9.0F, 3.0F, 5.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 7.5642F, -0.4045F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -0.5107F, -0.9002F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        RightFoot.setTextureOffset(111, 42).addBox(-2.3809F, -1.5956F, -1.2553F, 4.0F, 3.0F, 3.0F, 0.0F, false);
        RightFoot.setTextureOffset(111, 42).addBox(1.1191F, -1.5956F, -1.2553F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(80, 24).addBox(-2.0055F, -3.1995F, -1.4456F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 5.4197F, -1.2451F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(80, 41).addBox(-1.5784F, 0.1239F, -0.3897F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        LeftFoot.setTextureOffset(111, 42).addBox(-1.9284F, -0.6261F, -0.6397F, 4.0F, 3.0F, 3.0F, 0.0F, false);
        LeftFoot.setTextureOffset(111, 42).addBox(1.5716F, -0.6261F, -0.6397F, 1.0F, 3.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        HierophantGreen.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected ModelRenderer getHead() {
        return HeadBase;
    }
}