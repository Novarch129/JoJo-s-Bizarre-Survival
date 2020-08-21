package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TuskAct3Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TuskAct3Model extends AbstractStandModel<TuskAct3Entity> {
    private final ModelRenderer TuskAct3;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer antenna;
    private final ModelRenderer antenna2;
    private final ModelRenderer Eyes;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
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
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer weirdconnectors;
    private final ModelRenderer weirdconnectors2;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer claw;
    private final ModelRenderer claw2;
    private final ModelRenderer claw3;
    private final ModelRenderer claw4;
    private final ModelRenderer claw5;
    private final ModelRenderer claw6;

    public TuskAct3Model() {
        textureWidth = 128;
        textureHeight = 128;

        TuskAct3 = new ModelRenderer(this);
        TuskAct3.setRotationPoint(0.0F, 27.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -21.5F, -8.0F);
        TuskAct3.addChild(HeadBase);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -2.5F, -2.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.0F, -8.2F, 0.0F, 6.0F, 10.0F, 4.0F, 0.0F, false);
        Head.setTextureOffset(0, 0).addBox(-3.0F, -8.2F, 4.0F, 6.0F, 10.0F, 4.0F, 0.0F, false);
        Head.setTextureOffset(52, 0).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 3.0F, 5.0F, 0.0F, false);
        Head.setTextureOffset(52, 0).addBox(-4.0F, -1.0F, 4.5F, 8.0F, 3.0F, 5.0F, 0.0F, false);
        Head.setTextureOffset(67, 29).addBox(-4.0F, -5.0F, 2.0F, 8.0F, 4.0F, 3.0F, 0.0F, false);
        Head.setTextureOffset(67, 29).addBox(-4.0F, -5.0F, 4.0F, 8.0F, 4.0F, 3.0F, 0.0F, false);
        Head.setTextureOffset(40, 8).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(40, 8).addBox(-4.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(40, 8).addBox(3.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(40, 11).addBox(2.0F, 1.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(40, 11).addBox(-3.0F, 1.0F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        antenna = new ModelRenderer(this);
        antenna.setRotationPoint(3.0F, -6.0F, 1.0F);
        Head.addChild(antenna);
        setRotationAngle(antenna, 0.0F, 0.0F, 0.6109F);
        antenna.setTextureOffset(53, 12).addBox(-1.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        antenna.setTextureOffset(52, 77).addBox(2.25F, -1.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        antenna2 = new ModelRenderer(this);
        antenna2.setRotationPoint(-2.0F, -13.0F, 1.0F);
        Head.addChild(antenna2);
        setRotationAngle(antenna2, 0.0F, 0.0F, -0.6109F);
        antenna2.setTextureOffset(53, 12).addBox(-8.3724F, 4.1622F, 0.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        antenna2.setTextureOffset(52, 77).addBox(-9.25F, 3.5F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -12.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-2.5F, 7.8F, -0.1F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        Eyes.setTextureOffset(30, 10).addBox(-0.5F, 0.8F, -0.1F, 1.0F, 9.0F, 5.0F, 0.0F, false);
        Eyes.setTextureOffset(30, 10).addBox(-0.5F, 2.8F, 4.9F, 1.0F, 7.0F, 5.0F, 0.0F, false);
        Eyes.setTextureOffset(42, 0).addBox(-0.5F, -0.2F, 0.9F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.5F, 7.8F, -0.1F, 1.0F, 1.0F, 0.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TuskAct3.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(0, 50).addBox(-11.0F, -2.9063F, -2.32F, 22.0F, 5.0F, 5.0F, 0.0F, false);

        Stars = new ModelRenderer(this);
        Stars.setRotationPoint(10.925F, -0.25F, 0.225F);
        Torso.addChild(Stars);
        setRotationAngle(Stars, 0.0218F, 0.0F, 0.0F);


        Star = new ModelRenderer(this);
        Star.setRotationPoint(-0.25F, 0.8F, -0.8F);
        Stars.addChild(Star);
        setRotationAngle(Star, -0.7854F, 0.0F, 0.0F);
        Star.setTextureOffset(45, 81).addBox(-0.5F, -0.928F, -0.447F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star2 = new ModelRenderer(this);
        Star2.setRotationPoint(-0.25F, 0.8F, 0.7F);
        Stars.addChild(Star2);
        setRotationAngle(Star2, 0.7854F, 0.0F, 0.0F);
        Star2.setTextureOffset(45, 81).addBox(-0.5F, -0.9F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star3 = new ModelRenderer(this);
        Star3.setRotationPoint(-0.25F, 0.2F, 0.725F);
        Stars.addChild(Star3);
        setRotationAngle(Star3, -1.0123F, 0.0F, 0.0F);
        Star3.setTextureOffset(45, 81).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star4 = new ModelRenderer(this);
        Star4.setRotationPoint(-0.25F, 0.2F, -0.325F);
        Stars.addChild(Star4);
        setRotationAngle(Star4, 1.0123F, 0.0F, 0.0F);
        Star4.setTextureOffset(45, 81).addBox(-0.5F, -1.765F, -0.5477F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Star5 = new ModelRenderer(this);
        Star5.setRotationPoint(-0.25F, 0.3F, 0.0F);
        Stars.addChild(Star5);
        setRotationAngle(Star5, -0.0349F, 0.0F, 0.0F);
        Star5.setTextureOffset(45, 81).addBox(-0.5F, -1.3785F, -0.315F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Star5.setTextureOffset(45, 81).addBox(-0.5F, -1.3628F, -1.6307F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Star5.setTextureOffset(45, 81).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Star6 = new ModelRenderer(this);
        Star6.setRotationPoint(-0.25F, -0.7F, 0.0F);
        Stars.addChild(Star6);
        setRotationAngle(Star6, -0.0349F, 0.0F, 0.0F);
        Star6.setTextureOffset(45, 81).addBox(-0.5F, -1.5F, -0.45F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Stars2 = new ModelRenderer(this);
        Stars2.setRotationPoint(-10.925F, -0.25F, 0.225F);
        Torso.addChild(Stars2);
        setRotationAngle(Stars2, 0.0218F, 0.0F, 0.0F);


        Star7 = new ModelRenderer(this);
        Star7.setRotationPoint(0.25F, 0.8F, -0.8F);
        Stars2.addChild(Star7);
        setRotationAngle(Star7, -0.7854F, 0.0F, 0.0F);
        Star7.setTextureOffset(45, 81).addBox(-0.5F, -0.928F, -0.447F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star8 = new ModelRenderer(this);
        Star8.setRotationPoint(0.25F, 0.8F, 0.7F);
        Stars2.addChild(Star8);
        setRotationAngle(Star8, 0.7854F, 0.0F, 0.0F);
        Star8.setTextureOffset(45, 81).addBox(-0.5F, -0.9F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star9 = new ModelRenderer(this);
        Star9.setRotationPoint(0.25F, 0.2F, 0.725F);
        Stars2.addChild(Star9);
        setRotationAngle(Star9, -1.0123F, 0.0F, 0.0F);
        Star9.setTextureOffset(45, 81).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star10 = new ModelRenderer(this);
        Star10.setRotationPoint(0.25F, 0.2F, -0.325F);
        Stars2.addChild(Star10);
        setRotationAngle(Star10, 1.0123F, 0.0F, 0.0F);
        Star10.setTextureOffset(45, 81).addBox(-0.5F, -1.765F, -0.5477F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Star11 = new ModelRenderer(this);
        Star11.setRotationPoint(0.25F, 0.3F, 0.0F);
        Stars2.addChild(Star11);
        setRotationAngle(Star11, -0.0349F, 0.0F, 0.0F);
        Star11.setTextureOffset(45, 81).addBox(-0.5F, -1.3785F, -0.315F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Star11.setTextureOffset(45, 81).addBox(-0.5F, -1.3628F, -1.6307F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Star11.setTextureOffset(45, 81).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Star12 = new ModelRenderer(this);
        Star12.setRotationPoint(0.25F, -0.7F, 0.0F);
        Stars2.addChild(Star12);
        setRotationAngle(Star12, -0.0349F, 0.0F, 0.0F);
        Star12.setTextureOffset(45, 81).addBox(-0.5F, -1.5F, -0.45F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, 6.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, 0.0262F, 0.0F, 0.0F);
        Chest.setTextureOffset(26, 29).addBox(-6.5F, -28.767F, -11.7241F, 13.0F, 11.0F, 9.0F, 0.0F, false);
        Chest.setTextureOffset(0, 17).addBox(-5.0F, -30.2779F, -11.8F, 10.0F, 2.0F, 8.0F, 0.0F, false);
        Chest.setTextureOffset(44, 15).addBox(-4.0F, -24.072F, -5.0644F, 8.0F, 10.0F, 3.0F, 0.0F, false);
        Chest.setTextureOffset(23, 0).addBox(-3.0F, -31.2514F, -11.2117F, 6.0F, 1.0F, 6.0F, 0.0F, false);
        Chest.setTextureOffset(0, 73).addBox(-3.0F, -25.1525F, -7.6578F, 6.0F, 12.0F, 5.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(72, 44).addBox(-7.5F, -11.2058F, 1.353F, 9.0F, 10.0F, 4.0F, 0.0F, false);
        Abs.setTextureOffset(49, 61).addBox(-8.5F, -11.2058F, 2.353F, 11.0F, 10.0F, 3.0F, 0.0F, false);
        Abs.setTextureOffset(27, 62).addBox(-5.5F, -7.2406F, -0.6468F, 5.0F, 7.0F, 3.0F, 0.0F, false);

        weirdconnectors = new ModelRenderer(this);
        weirdconnectors.setRotationPoint(2.0F, -2.9999F, 0.0261F);
        Abs.addChild(weirdconnectors);
        setRotationAngle(weirdconnectors, 0.0F, 0.0F, -0.48F);
        weirdconnectors.setTextureOffset(64, 12).addBox(-1.0F, -5.0172F, -1.9651F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        weirdconnectors.setTextureOffset(25, 85).addBox(-3.7704F, 0.3046F, -2.0173F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        weirdconnectors.setTextureOffset(48, 9).addBox(-8.3365F, -5.0845F, -1.9825F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        weirdconnectors2 = new ModelRenderer(this);
        weirdconnectors2.setRotationPoint(-8.0F, -1.9999F, 0.0174F);
        Abs.addChild(weirdconnectors2);
        setRotationAngle(weirdconnectors2, 0.0F, 0.0F, 0.48F);
        weirdconnectors2.setTextureOffset(64, 12).addBox(-0.5383F, -5.5919F, -1.9738F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        weirdconnectors2.setTextureOffset(25, 85).addBox(2.2321F, -0.2701F, -2.026F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        weirdconnectors2.setTextureOffset(48, 9).addBox(6.5751F, -6.0879F, -1.9564F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-6.775F, -27.2015F, -1.9311F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, 0.1309F, 0.0F, 0.48F);
        RightArm.setTextureOffset(0, 29).addBox(-1.7236F, 5.7285F, -0.733F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        RightArm.setTextureOffset(15, 93).addBox(-1.2236F, 2.7285F, -0.233F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
        RightHand.setTextureOffset(13, 28).addBox(-2.6601F, 0.4823F, -2.4857F, 3.0F, 6.0F, 3.0F, 0.0F, false);
        RightHand.setTextureOffset(0, 41).addBox(-3.3008F, 6.4155F, -2.9109F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        RightHand.setTextureOffset(1, 18).addBox(-2.7958F, 6.4155F, -0.5006F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        RightHand.setTextureOffset(1, 18).addBox(-2.7663F, 6.3822F, -2.5001F, 1.0F, 3.0F, 1.0F, 0.0F, false);
        RightHand.setTextureOffset(39, 75).addBox(-0.5365F, 7.5471F, -1.4814F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        RightHand.setTextureOffset(39, 75).addBox(-1.5365F, 8.5471F, -1.4814F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(9.3F, -24.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.2662F);
        LeftArm.setTextureOffset(0, 29).addBox(-1.9835F, 2.5455F, -1.3939F, 3.0F, 4.0F, 3.0F, 0.0F, true);
        LeftArm.setTextureOffset(24, 92).addBox(-1.4835F, 0.5455F, -0.8939F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.1237F, 8.256F, 0.1416F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.5497F, 0.0F, 0.0218F);
        LeftHand.setTextureOffset(13, 28).addBox(-0.8763F, -1.8978F, -2.8999F, 3.0F, 6.0F, 3.0F, 0.0F, true);
        LeftHand.setTextureOffset(0, 41).addBox(-1.3763F, 3.7561F, -3.2999F, 4.0F, 1.0F, 4.0F, 0.0F, true);
        LeftHand.setTextureOffset(1, 18).addBox(-0.9763F, 4.0872F, -3.0026F, 1.0F, 3.0F, 1.0F, 0.0F, true);
        LeftHand.setTextureOffset(1, 18).addBox(1.2009F, 4.0193F, -2.9355F, 1.0F, 3.0F, 1.0F, 0.0F, true);
        LeftHand.setTextureOffset(39, 75).addBox(0.1237F, 3.5801F, -0.4975F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        LeftHand.setTextureOffset(39, 75).addBox(0.1237F, 5.5801F, -1.4975F, 1.0F, 1.0F, 2.0F, 0.0F, true);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(-1.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-3.3F, -15.425F, -6.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.4843F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(0, 62).addBox(-0.947F, 2.8871F, -1.3877F, 3.0F, 5.0F, 3.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.2865F, 13.7052F, 5.3525F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 1.3134F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(13, 62).addBox(-1.037F, -7.8997F, 2.4154F, 3.0F, 6.0F, 3.0F, 0.0F, false);
        RightFoot.setTextureOffset(3, 93).addBox(-0.6499F, -8.3509F, 2.7614F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(4.275F, -13.25F, -4.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.3927F);
        LeftLeg.setTextureOffset(0, 62).addBox(-1.0055F, -0.1995F, -1.4456F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(13, 62).addBox(-1.1887F, -9.1713F, 0.2958F, 3.0F, 5.0F, 3.0F, 0.0F, false);
        LeftFoot.setTextureOffset(3, 93).addBox(-0.8509F, -9.2976F, -0.0885F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        claw = new ModelRenderer(this);
        claw.setRotationPoint(-3.2035F, 0.2193F, -6.5337F);
        LeftLeg.addChild(claw);
        setRotationAngle(claw, 0.2749F, 0.5672F, 0.0873F);
        claw.setTextureOffset(32, 84).addBox(-1.7858F, 8.4312F, 2.5392F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw.setTextureOffset(26, 77).addBox(-1.3715F, 8.8807F, 3.093F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        claw2 = new ModelRenderer(this);
        claw2.setRotationPoint(5.2525F, 0.3566F, -5.3183F);
        LeftLeg.addChild(claw2);
        setRotationAngle(claw2, 0.2749F, -0.5672F, -0.0873F);
        claw2.setTextureOffset(32, 84).addBox(-1.7858F, 8.4312F, 2.5392F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw2.setTextureOffset(26, 77).addBox(-1.3658F, 8.5433F, 3.4397F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        claw3 = new ModelRenderer(this);
        claw3.setRotationPoint(12.7909F, 3.1128F, -16.1153F);
        LeftLeg.addChild(claw3);
        setRotationAngle(claw3, 0.2313F, -0.0873F, 0.1309F);
        claw3.setTextureOffset(32, 84).addBox(-11.282F, 9.2885F, 16.8601F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw3.setTextureOffset(26, 77).addBox(-11.1146F, 10.1227F, 17.7325F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        claw4 = new ModelRenderer(this);
        claw4.setRotationPoint(-24.4906F, 18.19F, -1.1563F);
        LeftLeg.addChild(claw4);
        setRotationAngle(claw4, 1.7148F, 0.0F, 1.2217F);
        claw4.setTextureOffset(32, 84).addBox(-11.282F, 9.2885F, 16.8601F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw4.setTextureOffset(26, 77).addBox(-11.1146F, 10.1227F, 17.7325F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        claw5 = new ModelRenderer(this);
        claw5.setRotationPoint(9.0317F, 22.2585F, -1.9975F);
        LeftLeg.addChild(claw5);
        setRotationAngle(claw5, 1.7148F, 0.0F, -0.3927F);
        claw5.setTextureOffset(32, 84).addBox(-8.0101F, 10.6862F, 20.3731F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw5.setTextureOffset(26, 77).addBox(-7.8427F, 11.5204F, 21.2455F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        claw6 = new ModelRenderer(this);
        claw6.setRotationPoint(-5.1294F, -24.7249F, -1.8352F);
        LeftLeg.addChild(claw6);
        setRotationAngle(claw6, 1.7148F, 0.0F, -2.7925F);
        claw6.setTextureOffset(32, 84).addBox(-8.0101F, 10.6862F, 20.3731F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        claw6.setTextureOffset(26, 77).addBox(-7.4456F, 11.5204F, 21.2455F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(TuskAct3Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TuskAct3.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}