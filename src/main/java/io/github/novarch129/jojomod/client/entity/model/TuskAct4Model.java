package io.github.novarch129.jojomod.client.entity.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TuskAct4Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TuskAct4Model extends AbstractStandModel<TuskAct4Entity> {
    private final ModelRenderer TuskAct4;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer plate;
    private final ModelRenderer plate2;
    private final ModelRenderer tiltedcuts;
    private final ModelRenderer tiltedcuts2;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer shoulder;
    private final ModelRenderer shoulder2;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer shoulder3;
    private final ModelRenderer shoulder4;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer tassle;
    private final ModelRenderer tassle2;
    private final ModelRenderer tassle3;
    private final ModelRenderer tassle4;
    private final ModelRenderer tassle5;
    private final ModelRenderer tassle6;
    private final ModelRenderer tassle7;
    private final ModelRenderer tassle8;
    private final ModelRenderer tassle9;
    private final ModelRenderer tassle10;
    private final ModelRenderer tassle11;
    private final ModelRenderer tassle12;
    private final ModelRenderer tassle13;
    private final ModelRenderer tassle14;
    private final ModelRenderer tassleside;
    private final ModelRenderer tassleside2;
    private final ModelRenderer tassleside3;
    private final ModelRenderer tassleside4;
    private final ModelRenderer tassleside5;
    private final ModelRenderer tassleside6;

    public TuskAct4Model() {
        textureWidth = 128;
        textureHeight = 128;

        TuskAct4 = new ModelRenderer(this);
        TuskAct4.setRotationPoint(0.0F, 22.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -22.5F, -5.75F);
        TuskAct4.addChild(HeadBase);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 3.5F, -4.25F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.0F, -10.2F, 0.0F, 6.0F, 7.0F, 5.0F, 0.0F, false);
        Head.setTextureOffset(0, 52).addBox(-0.475F, -5.2F, -0.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 52).addBox(1.525F, -4.2F, -0.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 52).addBox(-2.525F, -4.2F, -0.1F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Head.setTextureOffset(0, 52).addBox(1.525F, -4.7F, -0.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 52).addBox(-2.525F, -4.7F, -0.1F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(5.0F, 0.0F, 3.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-7.0F, -7.2F, -3.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(-4.0F, -7.2F, -3.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(16, 14).addBox(-3.425F, -7.2F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(16, 14).addBox(-7.575F, -7.2F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(16, 14).addBox(-4.575F, -7.2F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(16, 14).addBox(-6.425F, -7.2F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(16, 14).addBox(-4.0F, -7.75F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(16, 14).addBox(-7.0F, -7.75F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(16, 14).addBox(-4.0F, -6.65F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(16, 14).addBox(-7.0F, -6.65F, -3.025F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(79, 0).addBox(-4.0F, -10.225F, -3.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(79, 0).addBox(-7.0F, -10.225F, -3.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(79, 0).addBox(-7.0F, -9.225F, -3.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TuskAct4.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.0698F, 0.0F, 0.0F);


        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.0175F, 0.0F, 0.0F);
        Chest.setTextureOffset(0, 24).addBox(-11.5F, -27.8893F, -4.7252F, 23.0F, 12.0F, 10.0F, 0.0F, false);
        Chest.setTextureOffset(24, 49).addBox(-1.0F, -27.9959F, -5.3F, 2.0F, 5.0F, 11.0F, 0.0F, false);
        Chest.setTextureOffset(0, 68).addBox(-6.0F, -22.9962F, -5.2651F, 12.0F, 2.0F, 11.0F, 0.0F, false);

        plate = new ModelRenderer(this);
        plate.setRotationPoint(-8.0F, -2.9991F, 1.3952F);
        Chest.addChild(plate);
        setRotationAngle(plate, 0.0F, 0.0F, 0.2618F);
        plate.setTextureOffset(0, 69).addBox(8.0F, -27.8917F, -6.4008F, 4.0F, 6.0F, 1.0F, 0.0F, false);

        plate2 = new ModelRenderer(this);
        plate2.setRotationPoint(-23.0F, -1.9992F, 1.4127F);
        Chest.addChild(plate2);
        setRotationAngle(plate2, 0.0F, 0.0F, -0.2793F);
        plate2.setTextureOffset(0, 69).addBox(18.2972F, -20.5008F, -6.3135F, 4.0F, 6.0F, 1.0F, 0.0F, false);

        tiltedcuts = new ModelRenderer(this);
        tiltedcuts.setRotationPoint(-6.0F, 1.9997F, 0.0349F);
        Chest.addChild(tiltedcuts);
        setRotationAngle(tiltedcuts, 0.0F, 0.0F, 0.5236F);
        tiltedcuts.setTextureOffset(53, 50).addBox(-8.6339F, -23.2997F, -5.2965F, 2.0F, 6.0F, 11.0F, 0.0F, false);

        tiltedcuts2 = new ModelRenderer(this);
        tiltedcuts2.setRotationPoint(-17.0F, -6.1039F, 5.8943F);
        Chest.addChild(tiltedcuts2);
        setRotationAngle(tiltedcuts2, 0.0F, 0.0F, 1.0036F);
        tiltedcuts2.setTextureOffset(53, 50).addBox(-4.9F, -24.2961F, -11.1943F, 7.0F, 2.0F, 11.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-12.15F, -39.3015F, -1.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
        RightArm.setTextureOffset(43, 0).addBox(0.347F, 8.1926F, 1.267F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(-2.052F, 7.7899F, 1.052F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.2879F);
        RightHand.setTextureOffset(24, 0).addBox(-1.8411F, 10.5448F, 1.7936F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        shoulder = new ModelRenderer(this);
        shoulder.setRotationPoint(2.1907F, 0.6918F, -0.8499F);
        RightArm.addChild(shoulder);
        setRotationAngle(shoulder, 0.0F, -0.1309F, 0.3491F);
        shoulder.setTextureOffset(58, 15).addBox(1.0802F, 6.5453F, -1.4698F, 2.0F, 8.0F, 9.0F, 0.0F, false);
        shoulder.setTextureOffset(20, 51).addBox(4.3644F, 10.7656F, 4.9635F, 2.0F, 1.0F, 3.0F, 0.0F, true);

        shoulder2 = new ModelRenderer(this);
        shoulder2.setRotationPoint(14.5582F, 1.0085F, -2.8356F);
        RightArm.addChild(shoulder2);
        setRotationAngle(shoulder2, 0.0F, -0.1309F, 1.2217F);
        shoulder2.setTextureOffset(1, 49).addBox(1.2367F, 6.949F, 0.9492F, 2.0F, 8.0F, 9.0F, 0.0F, false);
        shoulder2.setTextureOffset(20, 51).addBox(1.1867F, 10.449F, 6.8992F, 2.0F, 1.0F, 3.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(13.3F, -30.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.0979F, 0.0601F, -0.174F);
        LeftArm.setTextureOffset(43, 0).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.4097F, 15.7912F, -0.4181F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.3752F, -0.0873F, 0.1963F);
        LeftHand.setTextureOffset(24, 0).addBox(-2.5F, -5.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        shoulder3 = new ModelRenderer(this);
        shoulder3.setRotationPoint(-5.563F, -4.6368F, 0.5149F);
        LeftArm.addChild(shoulder3);
        setRotationAngle(shoulder3, 0.0F, 0.0873F, -0.5672F);
        shoulder3.setTextureOffset(58, 15).addBox(1.0839F, 6.802F, -4.4529F, 2.0F, 8.0F, 9.0F, 0.0F, false);

        shoulder4 = new ModelRenderer(this);
        shoulder4.setRotationPoint(-3.1826F, -4.5193F, -0.2659F);
        LeftArm.addChild(shoulder4);
        setRotationAngle(shoulder4, 0.0436F, 0.0436F, -1.309F);
        shoulder4.setTextureOffset(1, 49).addBox(-4.1174F, -3.3042F, -3.8345F, 2.0F, 8.0F, 9.0F, 0.0F, false);
        shoulder4.setTextureOffset(20, 51).addBox(-4.0874F, 0.1517F, 2.1295F, 2.0F, 1.0F, 3.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, -3.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(49, 71).addBox(-5.5F, -16.2058F, -4.347F, 11.0F, 2.0F, 7.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -17.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(67, 34).addBox(-1.9049F, -0.1215F, -1.5112F, 4.0F, 10.0F, 5.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.0044F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(63, 0).addBox(-1.8265F, -5.2711F, -3.7366F, 4.0F, 9.0F, 5.0F, 0.0F, false);
        RightFoot.setTextureOffset(39, 70).addBox(-1.1654F, -7.654F, -4.183F, 3.0F, 5.0F, 1.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(2.275F, -17.25F, -2.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(67, 34).addBox(-2.5213F, -0.2595F, -1.1447F, 4.0F, 10.0F, 5.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(63, 0).addBox(-2.532F, -3.37F, -0.9009F, 4.0F, 10.0F, 5.0F, 0.0F, false);
        LeftFoot.setTextureOffset(39, 70).addBox(-2.037F, -5.2846F, -0.9469F, 3.0F, 5.0F, 1.0F, 0.0F, false);

        tassle = new ModelRenderer(this);
        tassle.setRotationPoint(-1.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle);
        tassle.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle2 = new ModelRenderer(this);
        tassle2.setRotationPoint(2.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle2);
        tassle2.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle2.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle3 = new ModelRenderer(this);
        tassle3.setRotationPoint(5.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle3);
        tassle3.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle3.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle4 = new ModelRenderer(this);
        tassle4.setRotationPoint(8.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle4);
        tassle4.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle4.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle5 = new ModelRenderer(this);
        tassle5.setRotationPoint(11.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle5);
        tassle5.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle5.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle6 = new ModelRenderer(this);
        tassle6.setRotationPoint(14.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle6);
        tassle6.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(2, 15).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(2, 15).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(2, 15).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle6.setTextureOffset(2, 15).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle7 = new ModelRenderer(this);
        tassle7.setRotationPoint(17.0F, -4.0F, 5.0F);
        TuskAct4.addChild(tassle7);
        tassle7.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle7.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle8 = new ModelRenderer(this);
        tassle8.setRotationPoint(17.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle8);
        tassle8.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle8.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle9 = new ModelRenderer(this);
        tassle9.setRotationPoint(14.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle9);
        tassle9.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle9.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle10 = new ModelRenderer(this);
        tassle10.setRotationPoint(11.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle10);
        tassle10.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle10.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle11 = new ModelRenderer(this);
        tassle11.setRotationPoint(8.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle11);
        tassle11.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle11.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle12 = new ModelRenderer(this);
        tassle12.setRotationPoint(5.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle12);
        tassle12.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle12.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle13 = new ModelRenderer(this);
        tassle13.setRotationPoint(2.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle13);
        tassle13.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle13.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassle14 = new ModelRenderer(this);
        tassle14.setRotationPoint(-1.0F, -4.0F, 14.0F);
        TuskAct4.addChild(tassle14);
        tassle14.setTextureOffset(10, 17).addBox(-8.5F, -15.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(10, 17).addBox(-8.5F, -11.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(10, 17).addBox(-8.5F, -7.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(10, 17).addBox(-8.5F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(10, 17).addBox(-8.5F, 1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(0, 22).addBox(-9.0F, -14.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(0, 22).addBox(-9.0F, -2.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(8, 21).addBox(-9.0F, 2.0F, -11.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(0, 22).addBox(-9.0F, -10.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        tassle14.setTextureOffset(0, 22).addBox(-9.0F, -6.0F, -11.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        tassleside = new ModelRenderer(this);
        tassleside.setRotationPoint(18.0F, -4.0F, 6.0F);
        TuskAct4.addChild(tassleside);
        tassleside.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        tassleside2 = new ModelRenderer(this);
        tassleside2.setRotationPoint(18.0F, -4.0F, 9.0F);
        TuskAct4.addChild(tassleside2);
        tassleside2.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside2.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside2.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside2.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside2.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside2.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside2.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside2.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside2.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside2.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        tassleside3 = new ModelRenderer(this);
        tassleside3.setRotationPoint(18.0F, -4.0F, 12.0F);
        TuskAct4.addChild(tassleside3);
        tassleside3.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside3.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside3.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside3.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside3.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside3.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside3.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside3.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside3.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside3.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        tassleside4 = new ModelRenderer(this);
        tassleside4.setRotationPoint(-3.0F, -4.0F, 12.0F);
        TuskAct4.addChild(tassleside4);
        tassleside4.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside4.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside4.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside4.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside4.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside4.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside4.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside4.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside4.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside4.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        tassleside5 = new ModelRenderer(this);
        tassleside5.setRotationPoint(-3.0F, -4.0F, 9.0F);
        TuskAct4.addChild(tassleside5);
        tassleside5.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside5.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside5.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside5.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside5.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside5.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside5.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside5.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside5.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside5.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);

        tassleside6 = new ModelRenderer(this);
        tassleside6.setRotationPoint(-3.0F, -4.0F, 6.0F);
        TuskAct4.addChild(tassleside6);
        tassleside6.setTextureOffset(10, 17).addBox(-8.0F, 1.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside6.setTextureOffset(10, 17).addBox(-8.0F, -3.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside6.setTextureOffset(10, 17).addBox(-8.0F, -11.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside6.setTextureOffset(10, 17).addBox(-8.0F, -15.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside6.setTextureOffset(10, 17).addBox(-8.0F, -7.0F, -10.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        tassleside6.setTextureOffset(0, 22).addBox(-8.0F, -14.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside6.setTextureOffset(0, 22).addBox(-8.0F, -2.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside6.setTextureOffset(8, 21).addBox(-8.0F, 2.0F, -11.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        tassleside6.setTextureOffset(0, 22).addBox(-8.0F, -10.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        tassleside6.setTextureOffset(0, 22).addBox(-8.0F, -6.0F, -11.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TuskAct4.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}