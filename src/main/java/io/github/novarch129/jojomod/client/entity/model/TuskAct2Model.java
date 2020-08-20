package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TuskAct2Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TuskAct2Model extends AbstractStandModel<TuskAct2Entity> {
    private final ModelRenderer TuskAct2;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Stars;
    private final ModelRenderer Star;
    private final ModelRenderer Star2;
    private final ModelRenderer Star3;
    private final ModelRenderer Star4;
    private final ModelRenderer Star5;
    private final ModelRenderer Star6;
    private final ModelRenderer Stars2;
    private final ModelRenderer Star7;
    private final ModelRenderer Star8;
    private final ModelRenderer Star9;
    private final ModelRenderer Star10;
    private final ModelRenderer Star11;
    private final ModelRenderer Star12;
    private final ModelRenderer Eyes;
    private final ModelRenderer antenna;
    private final ModelRenderer antenna2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Abs;
    private final ModelRenderer Abs2;
    private final ModelRenderer Abs3;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer LeftHand2;
    private final ModelRenderer shoulderthing1;
    private final ModelRenderer plate;
    private final ModelRenderer finger;
    private final ModelRenderer finger2;
    private final ModelRenderer finger3;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftHand3;
    private final ModelRenderer LeftHand4;
    private final ModelRenderer shoulderthing2;
    private final ModelRenderer plate2;
    private final ModelRenderer finger4;
    private final ModelRenderer finger5;
    private final ModelRenderer finger6;
    private final ModelRenderer Crotch;
    private final ModelRenderer Stars3;
    private final ModelRenderer Star13;
    private final ModelRenderer Star14;
    private final ModelRenderer Star15;
    private final ModelRenderer Star16;
    private final ModelRenderer Star17;
    private final ModelRenderer Star18;
    private final ModelRenderer Stars4;
    private final ModelRenderer Star19;
    private final ModelRenderer Star20;
    private final ModelRenderer Star21;
    private final ModelRenderer Star22;
    private final ModelRenderer Star23;
    private final ModelRenderer Star24;
    private final ModelRenderer littleleg;
    private final ModelRenderer littleleg2;
    private final ModelRenderer littleleg3;

    public TuskAct2Model() {
        textureWidth = 128;
        textureHeight = 128;

        TuskAct2 = new ModelRenderer(this);
        TuskAct2.setRotationPoint(0.0F, 30.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -26.25F, -0.25F);
        TuskAct2.addChild(HeadBase);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -0.75F, 0.25F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.5F, -8.2F, -4.0F, 7.0F, 9.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(0, 28).addBox(-4.5F, -7.2F, -3.0F, 9.0F, 5.0F, 6.0F, 0.0F, false);
        Head.setTextureOffset(18, 20).addBox(-2.5F, -9.2F, -1.0F, 5.0F, 1.0F, 2.0F, 0.0F, false);
        Head.setTextureOffset(32, 4).addBox(2.4F, -9.5F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        Head.setTextureOffset(32, 4).addBox(-3.4F, -9.5F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        Head.setTextureOffset(0, 101).addBox(-4.0F, -1.1F, -5.1F, 8.0F, 2.0F, 9.0F, 0.0F, false);
        Head.setTextureOffset(0, 48).addBox(-5.2932F, -0.4363F, -4.3923F, 11.0F, 1.0F, 6.0F, 0.0F, false);
        Head.setTextureOffset(0, 41).addBox(-0.5F, -3.4F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(5, 42).addBox(-1.0F, -1.6F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 41).addBox(-3.0F, -3.1F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 41).addBox(2.0F, -3.1F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Stars = new ModelRenderer(this);
        Stars.setRotationPoint(4.425F, -4.5F, -0.025F);
        Head.addChild(Stars);
        setRotationAngle(Stars, 0.0218F, 0.0F, 0.0F);


        Star = new ModelRenderer(this);
        Star.setRotationPoint(-0.25F, 0.8F, -0.8F);
        Stars.addChild(Star);
        setRotationAngle(Star, -0.7854F, 0.0F, 0.0F);
        Star.setTextureOffset(33, 14).addBox(-0.5F, -0.928F, -0.447F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star2 = new ModelRenderer(this);
        Star2.setRotationPoint(-0.25F, 0.8F, 0.7F);
        Stars.addChild(Star2);
        setRotationAngle(Star2, 0.7854F, 0.0F, 0.0F);
        Star2.setTextureOffset(33, 14).addBox(-0.5F, -0.9F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star3 = new ModelRenderer(this);
        Star3.setRotationPoint(-0.25F, 0.2F, 0.725F);
        Stars.addChild(Star3);
        setRotationAngle(Star3, -1.0123F, 0.0F, 0.0F);
        Star3.setTextureOffset(33, 14).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star4 = new ModelRenderer(this);
        Star4.setRotationPoint(-0.25F, 0.2F, -0.325F);
        Stars.addChild(Star4);
        setRotationAngle(Star4, 1.0123F, 0.0F, 0.0F);
        Star4.setTextureOffset(33, 14).addBox(-0.5F, -1.765F, -0.5477F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star5 = new ModelRenderer(this);
        Star5.setRotationPoint(-0.25F, 0.3F, 0.0F);
        Stars.addChild(Star5);
        setRotationAngle(Star5, -0.0349F, 0.0F, 0.0F);
        Star5.setTextureOffset(33, 14).addBox(-0.5F, -1.3785F, -0.315F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Star5.setTextureOffset(33, 14).addBox(-0.5F, -1.3628F, -1.6307F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Star5.setTextureOffset(33, 14).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star6 = new ModelRenderer(this);
        Star6.setRotationPoint(-0.25F, -0.7F, 0.0F);
        Stars.addChild(Star6);
        setRotationAngle(Star6, -0.0349F, 0.0F, 0.0F);
        Star6.setTextureOffset(33, 14).addBox(-0.5F, -1.5F, -0.45F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Stars2 = new ModelRenderer(this);
        Stars2.setRotationPoint(4.425F, -4.5F, -0.025F);
        Head.addChild(Stars2);
        setRotationAngle(Stars2, 0.0218F, 0.0F, 0.0F);


        Star7 = new ModelRenderer(this);
        Star7.setRotationPoint(-0.25F, 0.8F, -0.8F);
        Stars2.addChild(Star7);
        setRotationAngle(Star7, -0.7854F, 0.0F, 0.0F);
        Star7.setTextureOffset(33, 14).addBox(-8.85F, -0.928F, -0.447F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star8 = new ModelRenderer(this);
        Star8.setRotationPoint(-0.25F, 0.8F, 0.7F);
        Stars2.addChild(Star8);
        setRotationAngle(Star8, 0.7854F, 0.0F, 0.0F);
        Star8.setTextureOffset(33, 14).addBox(-8.85F, -0.9F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star9 = new ModelRenderer(this);
        Star9.setRotationPoint(-0.25F, 0.2F, 0.725F);
        Stars2.addChild(Star9);
        setRotationAngle(Star9, -1.0123F, 0.0F, 0.0F);
        Star9.setTextureOffset(33, 14).addBox(-8.85F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star10 = new ModelRenderer(this);
        Star10.setRotationPoint(-0.25F, 0.2F, -0.325F);
        Stars2.addChild(Star10);
        setRotationAngle(Star10, 1.0123F, 0.0F, 0.0F);
        Star10.setTextureOffset(33, 14).addBox(-8.85F, -1.765F, -0.5477F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star11 = new ModelRenderer(this);
        Star11.setRotationPoint(-0.25F, 0.3F, 0.0F);
        Stars2.addChild(Star11);
        setRotationAngle(Star11, -0.0349F, 0.0F, 0.0F);
        Star11.setTextureOffset(33, 14).addBox(-8.85F, -1.3785F, -0.315F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Star11.setTextureOffset(33, 14).addBox(-8.85F, -1.3628F, -1.6307F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Star11.setTextureOffset(33, 14).addBox(-8.85F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star12 = new ModelRenderer(this);
        Star12.setRotationPoint(-0.25F, -0.7F, 0.0F);
        Stars2.addChild(Star12);
        setRotationAngle(Star12, -0.0349F, 0.0F, 0.0F);
        Star12.setTextureOffset(33, 14).addBox(-8.85F, -1.5F, -0.45F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 2.75F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-2.5F, -7.2F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.5F, -7.2F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        antenna = new ModelRenderer(this);
        antenna.setRotationPoint(-4.0F, -3.0F, 0.0F);
        Head.addChild(antenna);
        setRotationAngle(antenna, 0.0F, 0.0F, 0.4363F);
        antenna.setTextureOffset(23, 0).addBox(3.5F, -8.7F, -1.0F, 7.0F, 1.0F, 2.0F, 0.0F, false);
        antenna.setTextureOffset(0, 18).addBox(9.5937F, -10.2774F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        antenna.setTextureOffset(0, 18).addBox(12.5937F, -10.2774F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        antenna.setTextureOffset(2, 19).addBox(10.5937F, -10.2774F, -1.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
        antenna.setTextureOffset(4, 19).addBox(10.5937F, -10.2774F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        antenna.setTextureOffset(4, 19).addBox(10.5937F, -7.2774F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        antenna2 = new ModelRenderer(this);
        antenna2.setRotationPoint(4.0F, -3.0F, 0.0F);
        Head.addChild(antenna2);
        setRotationAngle(antenna2, 0.0F, 0.0F, -0.4363F);
        antenna2.setTextureOffset(23, 0).addBox(-10.5F, -8.7F, -1.0F, 7.0F, 1.0F, 2.0F, 0.0F, true);
        antenna2.setTextureOffset(0, 18).addBox(-10.5937F, -10.2774F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);
        antenna2.setTextureOffset(0, 18).addBox(-13.5937F, -10.2774F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);
        antenna2.setTextureOffset(2, 19).addBox(-12.5937F, -10.2774F, -1.0F, 2.0F, 4.0F, 3.0F, 0.0F, true);
        antenna2.setTextureOffset(4, 19).addBox(-12.5937F, -10.2774F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);
        antenna2.setTextureOffset(4, 19).addBox(-12.5937F, -7.2774F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TuskAct2.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        Torso.setTextureOffset(16, 83).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 5.0F, 3.0F, 0.0F, false);
        Torso.setTextureOffset(37, 21).addBox(-2.5F, 1.9F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        Torso.setTextureOffset(37, 21).addBox(1.5F, 1.9F, -3.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        Torso.setTextureOffset(40, 13).addBox(-1.5F, 1.9F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        Torso.setTextureOffset(37, 21).addBox(-0.5F, 2.0F, -3.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -11.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.2705F, 0.0F, 0.0F);
        Abs.setTextureOffset(35, 40).addBox(-5.5F, -13.8347F, 9.2154F, 5.0F, 5.0F, 2.0F, 0.0F, false);

        Abs2 = new ModelRenderer(this);
        Abs2.setRotationPoint(3.0F, -7.0F, -3.0F);
        BodyBase.addChild(Abs2);
        setRotationAngle(Abs2, -0.0349F, 0.0F, 0.0F);
        Abs2.setTextureOffset(0, 61).addBox(-5.5F, -15.7959F, 5.9F, 5.0F, 8.0F, 2.0F, 0.0F, false);

        Abs3 = new ModelRenderer(this);
        Abs3.setRotationPoint(3.0F, -12.0F, -10.0F);
        BodyBase.addChild(Abs3);
        setRotationAngle(Abs3, -0.9076F, 0.0F, 0.0F);
        Abs3.setTextureOffset(16, 60).addBox(-5.5F, -13.5008F, 5.3557F, 5.0F, 6.0F, 2.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-18.15F, -28.3015F, -1.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);


        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);


        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(8.3F, -23.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2182F, 0.0F, 0.1702F);


        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-2.1768F, 0.4527F, 0.1213F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, 0.1309F, 0.0873F, -0.6109F);
        LeftHand.setTextureOffset(25, 70).addBox(-0.1306F, -1.4683F, -1.5368F, 2.0F, 7.0F, 2.0F, 0.0F, true);

        LeftHand2 = new ModelRenderer(this);
        LeftHand2.setRotationPoint(1.796F, 6.1628F, -0.6614F);
        LeftArm.addChild(LeftHand2);
        setRotationAngle(LeftHand2, -1.3963F, 0.0873F, -0.1745F);
        LeftHand2.setTextureOffset(1, 94).addBox(-2.0084F, 2.7517F, -1.8659F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        LeftHand2.setTextureOffset(1, 94).addBox(-2.0084F, 2.7517F, -1.5909F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        LeftHand2.setTextureOffset(0, 94).addBox(-2.0084F, 2.0017F, 0.1591F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        LeftHand2.setTextureOffset(0, 89).addBox(-2.0084F, -2.2483F, -1.8659F, 3.0F, 5.0F, 3.0F, 0.0F, true);

        shoulderthing1 = new ModelRenderer(this);
        shoulderthing1.setRotationPoint(-8.0687F, 3.3987F, -1.1599F);
        LeftArm.addChild(shoulderthing1);
        setRotationAngle(shoulderthing1, -0.1745F, 0.1745F, -2.138F);
        shoulderthing1.setTextureOffset(27, 32).addBox(-0.9835F, 0.5455F, -0.05F, 1.0F, 6.0F, 1.0F, 0.0F, true);
        shoulderthing1.setTextureOffset(36, 75).addBox(-1.3525F, 6.3151F, -1.0547F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        shoulderthing1.setTextureOffset(45, 6).addBox(-1.1636F, 6.1759F, 2.9384F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        shoulderthing1.setTextureOffset(37, 49).addBox(-0.8418F, 0.441F, 2.9448F, 1.0F, 6.0F, 1.0F, 0.0F, true);

        plate = new ModelRenderer(this);
        plate.setRotationPoint(0.1693F, 0.7458F, 1.1896F);
        LeftArm.addChild(plate);
        setRotationAngle(plate, 0.2182F, -0.0436F, 0.3054F);
        plate.setTextureOffset(32, 31).addBox(-4.3954F, -2.7492F, -4.1563F, 6.0F, 1.0F, 6.0F, 0.0F, false);

        finger = new ModelRenderer(this);
        finger.setRotationPoint(0.3082F, -4.6634F, 2.039F);
        LeftArm.addChild(finger);
        setRotationAngle(finger, 0.1745F, 0.0873F, -0.1745F);


        finger2 = new ModelRenderer(this);
        finger2.setRotationPoint(7.0682F, -0.8445F, 2.8856F);
        LeftArm.addChild(finger2);
        setRotationAngle(finger2, 0.1745F, -0.0873F, 0.6981F);


        finger3 = new ModelRenderer(this);
        finger3.setRotationPoint(-2.4486F, 2.5168F, 4.6551F);
        LeftArm.addChild(finger3);
        setRotationAngle(finger3, -0.0436F, 0.0873F, -1.1345F);


        LeftArm2 = new ModelRenderer(this);
        LeftArm2.setRotationPoint(-8.3F, -23.9765F, -1.3061F);
        Arms.addChild(LeftArm2);
        setRotationAngle(LeftArm2, -0.2182F, 0.0F, -0.1702F);


        LeftHand3 = new ModelRenderer(this);
        LeftHand3.setRotationPoint(2.1768F, 0.4527F, 0.1213F);
        LeftArm2.addChild(LeftHand3);
        setRotationAngle(LeftHand3, 0.1309F, -0.0873F, 0.6109F);
        LeftHand3.setTextureOffset(25, 70).addBox(-1.8694F, -1.4683F, -1.5368F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        LeftHand4 = new ModelRenderer(this);
        LeftHand4.setRotationPoint(-1.796F, 6.1628F, -0.6614F);
        LeftArm2.addChild(LeftHand4);
        setRotationAngle(LeftHand4, -1.3963F, -0.0873F, 0.1745F);
        LeftHand4.setTextureOffset(0, 89).addBox(-0.9916F, -2.2483F, -1.8659F, 3.0F, 5.0F, 3.0F, 0.0F, false);
        LeftHand4.setTextureOffset(1, 94).addBox(-0.9916F, 2.7517F, -1.8659F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        LeftHand4.setTextureOffset(1, 94).addBox(-0.9916F, 2.7517F, -1.5909F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        LeftHand4.setTextureOffset(0, 94).addBox(-0.9916F, 2.0017F, 0.1591F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        shoulderthing2 = new ModelRenderer(this);
        shoulderthing2.setRotationPoint(8.0687F, 3.3987F, -1.1599F);
        LeftArm2.addChild(shoulderthing2);
        setRotationAngle(shoulderthing2, -0.1745F, -0.1745F, 2.138F);
        shoulderthing2.setTextureOffset(27, 32).addBox(-0.0165F, 0.5455F, -0.05F, 1.0F, 6.0F, 1.0F, 0.0F, false);
        shoulderthing2.setTextureOffset(36, 75).addBox(-0.6475F, 6.3151F, -1.0547F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        shoulderthing2.setTextureOffset(45, 6).addBox(-0.8364F, 6.1759F, 2.9384F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        shoulderthing2.setTextureOffset(37, 49).addBox(-0.1582F, 0.441F, 2.9448F, 1.0F, 6.0F, 1.0F, 0.0F, false);

        plate2 = new ModelRenderer(this);
        plate2.setRotationPoint(-0.1693F, 0.7458F, 1.1896F);
        LeftArm2.addChild(plate2);
        setRotationAngle(plate2, 0.2182F, 0.0436F, -0.3054F);
        plate2.setTextureOffset(32, 31).addBox(-1.3721F, -2.6203F, -4.1646F, 6.0F, 1.0F, 6.0F, 0.0F, true);

        finger4 = new ModelRenderer(this);
        finger4.setRotationPoint(-0.3082F, -4.6634F, 2.039F);
        LeftArm2.addChild(finger4);
        setRotationAngle(finger4, 0.1745F, -0.0873F, 0.1745F);


        finger5 = new ModelRenderer(this);
        finger5.setRotationPoint(-7.0682F, -0.8445F, 2.8856F);
        LeftArm2.addChild(finger5);
        setRotationAngle(finger5, 0.1745F, 0.0873F, -0.6981F);


        finger6 = new ModelRenderer(this);
        finger6.setRotationPoint(2.4486F, 2.5168F, 4.6551F);
        LeftArm2.addChild(finger6);
        setRotationAngle(finger6, -0.0436F, -0.0873F, 1.1345F);


        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(33, 82).addBox(-3.5F, -14.2058F, -4.347F, 2.0F, 5.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(33, 82).addBox(1.5F, -14.2058F, -4.347F, 2.0F, 5.0F, 5.0F, 0.0F, true);
        Crotch.setTextureOffset(34, 60).addBox(-1.5F, -12.2058F, -4.347F, 3.0F, 3.0F, 5.0F, 0.0F, true);
        Crotch.setTextureOffset(0, 75).addBox(-4.0F, -13.2058F, -3.347F, 1.0F, 3.0F, 3.0F, 0.0F, true);
        Crotch.setTextureOffset(0, 75).addBox(3.0F, -13.2058F, -3.347F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 98).addBox(-2.0F, -13.2058F, -3.347F, 4.0F, 3.0F, 3.0F, 0.0F, false);

        Stars3 = new ModelRenderer(this);
        Stars3.setRotationPoint(5.025F, -11.5F, -1.925F);
        Crotch.addChild(Stars3);
        setRotationAngle(Stars3, 0.0218F, 0.0F, 0.0F);


        Star13 = new ModelRenderer(this);
        Star13.setRotationPoint(-0.25F, 0.8F, -0.8F);
        Stars3.addChild(Star13);
        setRotationAngle(Star13, -0.7854F, 0.0F, 0.0F);
        Star13.setTextureOffset(33, 14).addBox(-8.85F, -0.978F, -0.447F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star14 = new ModelRenderer(this);
        Star14.setRotationPoint(-0.25F, 0.8F, 0.7F);
        Stars3.addChild(Star14);
        setRotationAngle(Star14, 0.7854F, 0.0F, 0.0F);
        Star14.setTextureOffset(33, 14).addBox(-8.85F, -0.9F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star15 = new ModelRenderer(this);
        Star15.setRotationPoint(-0.25F, 0.2F, 0.725F);
        Stars3.addChild(Star15);
        setRotationAngle(Star15, -1.0123F, 0.0F, 0.0F);
        Star15.setTextureOffset(33, 14).addBox(-8.85F, -0.9022F, -0.8937F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star16 = new ModelRenderer(this);
        Star16.setRotationPoint(-0.25F, 0.2F, -0.325F);
        Stars3.addChild(Star16);
        setRotationAngle(Star16, 1.0123F, 0.0F, 0.0F);
        Star16.setTextureOffset(33, 14).addBox(-8.85F, -1.1276F, -0.1702F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star17 = new ModelRenderer(this);
        Star17.setRotationPoint(-0.25F, 0.3F, 0.0F);
        Stars3.addChild(Star17);
        setRotationAngle(Star17, -0.0349F, 0.0F, 0.0F);
        Star17.setTextureOffset(33, 14).addBox(-8.85F, -1.371F, -0.8899F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Star17.setTextureOffset(33, 14).addBox(-8.85F, -1.3707F, -0.0307F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Star17.setTextureOffset(33, 14).addBox(-8.85F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star18 = new ModelRenderer(this);
        Star18.setRotationPoint(-0.25F, -0.7F, 0.0F);
        Stars3.addChild(Star18);
        setRotationAngle(Star18, -0.0349F, 0.0F, 0.0F);
        Star18.setTextureOffset(33, 14).addBox(-8.85F, -0.925F, -0.45F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Stars4 = new ModelRenderer(this);
        Stars4.setRotationPoint(-5.025F, -11.5F, -1.925F);
        Crotch.addChild(Stars4);
        setRotationAngle(Stars4, 0.0218F, 0.0F, 0.0F);


        Star19 = new ModelRenderer(this);
        Star19.setRotationPoint(0.25F, 0.8F, -0.8F);
        Stars4.addChild(Star19);
        setRotationAngle(Star19, -0.7854F, 0.0F, 0.0F);
        Star19.setTextureOffset(33, 14).addBox(7.85F, -0.978F, -0.447F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star20 = new ModelRenderer(this);
        Star20.setRotationPoint(0.25F, 0.8F, 0.7F);
        Stars4.addChild(Star20);
        setRotationAngle(Star20, 0.7854F, 0.0F, 0.0F);
        Star20.setTextureOffset(33, 14).addBox(7.85F, -0.9F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star21 = new ModelRenderer(this);
        Star21.setRotationPoint(0.25F, 0.2F, 0.725F);
        Stars4.addChild(Star21);
        setRotationAngle(Star21, -1.0123F, 0.0F, 0.0F);
        Star21.setTextureOffset(33, 14).addBox(7.85F, -0.9022F, -0.8937F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star22 = new ModelRenderer(this);
        Star22.setRotationPoint(0.25F, 0.2F, -0.325F);
        Stars4.addChild(Star22);
        setRotationAngle(Star22, 1.0123F, 0.0F, 0.0F);
        Star22.setTextureOffset(33, 14).addBox(7.85F, -1.1276F, -0.1702F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star23 = new ModelRenderer(this);
        Star23.setRotationPoint(0.25F, 0.3F, 0.0F);
        Stars4.addChild(Star23);
        setRotationAngle(Star23, -0.0349F, 0.0F, 0.0F);
        Star23.setTextureOffset(33, 14).addBox(7.85F, -1.371F, -0.8899F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Star23.setTextureOffset(33, 14).addBox(7.85F, -1.3707F, -0.0307F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Star23.setTextureOffset(33, 14).addBox(7.85F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star24 = new ModelRenderer(this);
        Star24.setRotationPoint(0.25F, -0.7F, 0.0F);
        Stars4.addChild(Star24);
        setRotationAngle(Star24, -0.0349F, 0.0F, 0.0F);
        Star24.setTextureOffset(33, 14).addBox(7.85F, -0.925F, -0.45F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        littleleg = new ModelRenderer(this);
        littleleg.setRotationPoint(-2.0F, 25.0F, 0.0F);
        TuskAct2.addChild(littleleg);
        setRotationAngle(littleleg, 0.0F, 0.0F, 0.3927F);
        littleleg.setTextureOffset(43, 0).addBox(-3.0F, -4.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        littleleg2 = new ModelRenderer(this);
        littleleg2.setRotationPoint(2.0F, 25.0F, 0.0F);
        TuskAct2.addChild(littleleg2);
        setRotationAngle(littleleg2, 0.0F, 0.0F, -0.3927F);
        littleleg2.setTextureOffset(43, 0).addBox(2.0F, -4.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        littleleg3 = new ModelRenderer(this);
        littleleg3.setRotationPoint(-3.0F, 23.0F, 5.0F);
        TuskAct2.addChild(littleleg3);
        setRotationAngle(littleleg3, 0.3491F, 0.0F, 0.0F);
        littleleg3.setTextureOffset(43, 0).addBox(2.5F, -4.0F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TuskAct2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}