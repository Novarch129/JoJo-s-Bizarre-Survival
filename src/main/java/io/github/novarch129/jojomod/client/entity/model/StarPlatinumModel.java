package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.StarPlatinumEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class StarPlatinumModel extends AbstractStandModel<StarPlatinumEntity> {
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Ears;
    private final ModelRenderer Eyes;
    private final ModelRenderer Hair;
    private final ModelRenderer Strand;
    private final ModelRenderer Strand3;
    private final ModelRenderer Strand1;
    private final ModelRenderer Strand2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer ShoulderPads;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer ElbowPad2;
    private final ModelRenderer RightArm2;
    private final ModelRenderer RightHand2;
    private final ModelRenderer ElbowPad;
    private final ModelRenderer Crotch;
    private final ModelRenderer Tarp;
    private final ModelRenderer Cape;
    private final ModelRenderer Cape1;
    private final ModelRenderer Cape2;
    private final ModelRenderer Tarp2;
    private final ModelRenderer Cape3;
    private final ModelRenderer Cape4;
    private final ModelRenderer Cape5;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer KneePad2;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer KneePad1;

    public StarPlatinumModel() {
        textureWidth = 128;
        textureHeight = 128;

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -6.75F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 2.75F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -9.2F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(51, 0).addBox(-1.0F, -3.45F, -4.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        Ears = new ModelRenderer(this);
        Ears.setRotationPoint(0.0F, 28.0F, 0.0F);
        Head.addChild(Ears);
        Ears.setTextureOffset(69, 0).addBox(3.15F, -35.95F, -1.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        Ears.setTextureOffset(69, 0).addBox(-4.15F, -35.95F, -1.25F, 1.0F, 3.0F, 3.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Hair = new ModelRenderer(this);
        Hair.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Hair);
        Hair.setTextureOffset(92, 62).addBox(-4.0F, -11.5F, -4.125F, 8.0F, 3.0F, 9.0F, 0.0F, false);
        Hair.setTextureOffset(37, 86).addBox(3.125F, -9.5F, -4.125F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Hair.setTextureOffset(37, 86).addBox(-4.125F, -9.5F, -4.125F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Hair.setTextureOffset(96, 109).addBox(-3.775F, -10.75F, -2.125F, 8.0F, 3.0F, 8.0F, 0.0F, false);
        Hair.setTextureOffset(97, 113).addBox(-3.775F, -8.5F, 1.875F, 8.0F, 2.0F, 4.0F, 0.0F, false);
        Hair.setTextureOffset(96, 109).addBox(-4.025F, -10.75F, -2.125F, 1.0F, 3.0F, 8.0F, 0.0F, false);
        Hair.setTextureOffset(104, 111).addBox(-4.025F, -8.5F, 1.875F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        Hair.setTextureOffset(103, 109).addBox(-4.0F, -11.5F, 3.25F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        Hair.setTextureOffset(90, 107).addBox(-3.775F, -11.5F, -2.75F, 8.0F, 2.0F, 10.0F, 0.0F, false);
        Hair.setTextureOffset(93, 83).addBox(-3.5F, -12.5F, -4.0F, 7.0F, 1.0F, 9.0F, 0.0F, false);

        Strand = new ModelRenderer(this);
        Strand.setRotationPoint(-1.65F, -6.1391F, 4.6432F);
        Hair.addChild(Strand);
        setRotationAngle(Strand, -0.6981F, 0.0F, 0.0F);
        Strand.setTextureOffset(97, 114).addBox(-2.125F, -1.1274F, -0.8124F, 8.0F, 1.0F, 4.0F, 0.0F, false);
        Strand.setTextureOffset(104, 111).addBox(-2.375F, -1.1274F, -0.8124F, 1.0F, 1.0F, 4.0F, 0.0F, false);

        Strand3 = new ModelRenderer(this);
        Strand3.setRotationPoint(-1.65F, -4.8535F, 6.1753F);
        Hair.addChild(Strand3);
        setRotationAngle(Strand3, -1.069F, 0.0F, 0.0F);
        Strand3.setTextureOffset(101, 116).addBox(-2.125F, -1.4812F, 0.6983F, 8.0F, 1.0F, 2.0F, 0.0F, false);
        Strand3.setTextureOffset(104, 111).addBox(-2.375F, -1.4812F, 0.6983F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        Strand1 = new ModelRenderer(this);
        Strand1.setRotationPoint(-1.6375F, -10.2332F, 8.7051F);
        Hair.addChild(Strand1);
        setRotationAngle(Strand1, -0.6894F, 0.0F, 0.0F);
        Strand1.setTextureOffset(98, 113).addBox(-2.1375F, -0.0519F, -1.9286F, 8.0F, 2.0F, 4.0F, 0.0F, false);
        Strand1.setTextureOffset(103, 109).addBox(-2.3625F, -0.0519F, -1.9286F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        Strand2 = new ModelRenderer(this);
        Strand2.setRotationPoint(-1.6375F, -6.4168F, 13.3349F);
        Hair.addChild(Strand2);
        setRotationAngle(Strand2, -1.0385F, 0.0F, 0.0F);
        Strand2.setTextureOffset(99, 112).addBox(-2.1375F, 1.2949F, -3.7094F, 8.0F, 2.0F, 2.0F, 0.0F, false);
        Strand2.setTextureOffset(103, 109).addBox(-2.3625F, 1.2949F, -3.7094F, 1.0F, 2.0F, 2.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

        ShoulderPads = new ModelRenderer(this);
        ShoulderPads.setRotationPoint(0.0F, 0.0F, 0.0F);
        Torso.addChild(ShoulderPads);
        ShoulderPads.setTextureOffset(102, 0).addBox(5.0F, -5.5296F, -3.0377F, 6.0F, 6.0F, 7.0F, 0.0F, false);
        ShoulderPads.setTextureOffset(102, 0).addBox(-11.0F, -5.5296F, -3.0377F, 6.0F, 6.0F, 7.0F, 0.0F, true);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-7.15F, -28.3015F, -1.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
        RightArm.setTextureOffset(27, 32).addBox(-1.7236F, 1.7285F, -0.733F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
        RightHand.setTextureOffset(0, 53).addBox(-2.6601F, 0.4823F, -2.4857F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        ElbowPad2 = new ModelRenderer(this);
        ElbowPad2.setRotationPoint(0.4626F, 9.5168F, 0.4122F);
        RightArm.addChild(ElbowPad2);
        setRotationAngle(ElbowPad2, -0.0654F, 1.5839F, -0.144F);
        ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(64, 85).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(64, 85).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(64, 85).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(5, 101).addBox(-1.747F, -1.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(5, 101).addBox(-1.747F, -4.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad2.setTextureOffset(19, 103).addBox(-2.747F, -3.486F, -2.5709F, 4.0F, 2.0F, 1.0F, 0.0F, false);

        RightArm2 = new ModelRenderer(this);
        RightArm2.setRotationPoint(8.3F, -26.9765F, -1.3061F);
        Arms.addChild(RightArm2);
        setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
        RightArm2.setTextureOffset(27, 32).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        RightHand2 = new ModelRenderer(this);
        RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        RightArm2.addChild(RightHand2);
        setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
        RightHand2.setTextureOffset(0, 53).addBox(-1.2528F, 0.1022F, -2.4024F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        ElbowPad = new ModelRenderer(this);
        ElbowPad.setRotationPoint(-1.1612F, 8.2046F, 0.1906F);
        RightArm2.addChild(ElbowPad);
        setRotationAngle(ElbowPad, -0.0654F, -1.5839F, 0.144F);
        ElbowPad.setTextureOffset(64, 85).addBox(1.9267F, -3.4988F, -1.5743F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        ElbowPad.setTextureOffset(64, 85).addBox(-1.6903F, -3.4988F, -1.5743F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        ElbowPad.setTextureOffset(64, 85).addBox(1.9267F, -3.4988F, -2.3243F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad.setTextureOffset(64, 85).addBox(-1.6903F, -3.4988F, -2.3243F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        ElbowPad.setTextureOffset(64, 85).addBox(-0.8233F, -3.4988F, 1.4257F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad.setTextureOffset(64, 85).addBox(-1.0733F, -3.4988F, -2.3243F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad.setTextureOffset(19, 103).addBox(-1.3233F, -3.4988F, -2.5243F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        ElbowPad.setTextureOffset(5, 101).addBox(-0.3233F, -4.4988F, -2.5243F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad.setTextureOffset(5, 101).addBox(-0.3233F, -1.4988F, -2.5243F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(54, 66).addBox(-4.5F, -19.2058F, -4.247F, 9.0F, 5.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2308F, -4.422F, 9.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-4.675F, -19.2308F, -0.122F, 9.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(3.675F, -19.2308F, -4.422F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-4.675F, -19.2308F, -4.422F, 1.0F, 1.0F, 5.0F, 0.0F, true);
        Crotch.setTextureOffset(32, 68).addBox(3.675F, -19.2308F, -0.122F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 0).addBox(3.25F, -18.9F, -3.75F, 2.0F, 2.0F, 4.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 0).addBox(-5.25F, -18.9F, -3.75F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        Tarp = new ModelRenderer(this);
        Tarp.setRotationPoint(-0.25F, 0.0F, 0.0F);
        Crotch.addChild(Tarp);


        Cape = new ModelRenderer(this);
        Cape.setRotationPoint(0.0833F, -18.5583F, -4.5F);
        Tarp.addChild(Cape);
        setRotationAngle(Cape, -0.0873F, 0.0F, 0.0F);
        Cape.setTextureOffset(0, 66).addBox(-2.3333F, -0.6667F, 0.0F, 5.0F, 6.0F, 0.0F, 0.0F, false);

        Cape1 = new ModelRenderer(this);
        Cape1.setRotationPoint(-0.0833F, 18.3333F, 4.5F);
        Cape.addChild(Cape1);
        setRotationAngle(Cape1, 0.1745F, 0.0F, 0.0F);
        Cape1.setTextureOffset(0, 66).addBox(-2.25F, -13.5837F, -2.1742F, 1.0F, 3.0F, 0.0F, 0.0F, false);

        Cape2 = new ModelRenderer(this);
        Cape2.setRotationPoint(-0.0833F, 18.3333F, 4.5F);
        Cape.addChild(Cape2);
        setRotationAngle(Cape2, 0.1745F, 0.0F, 0.0F);
        Cape2.setTextureOffset(0, 66).addBox(1.75F, -13.5839F, -2.1742F, 1.0F, 3.0F, 0.0F, 0.0F, true);
        Cape2.setTextureOffset(0, 66).addBox(-1.25F, -12.5839F, -2.1742F, 1.0F, 1.0F, 0.0F, 0.0F, true);
        Cape2.setTextureOffset(0, 66).addBox(0.75F, -12.5839F, -2.1742F, 1.0F, 1.0F, 0.0F, 0.0F, true);
        Cape2.setTextureOffset(0, 66).addBox(-1.25F, -13.5839F, -2.1742F, 3.0F, 1.0F, 0.0F, 0.0F, true);

        Tarp2 = new ModelRenderer(this);
        Tarp2.setRotationPoint(-0.25F, 0.0F, -3.5F);
        Crotch.addChild(Tarp2);


        Cape3 = new ModelRenderer(this);
        Cape3.setRotationPoint(0.0833F, -18.5583F, 4.5F);
        Tarp2.addChild(Cape3);
        setRotationAngle(Cape3, 0.0873F, 0.0F, 0.0F);
        Cape3.setTextureOffset(0, 66).addBox(-2.3333F, -0.6667F, 0.0F, 5.0F, 6.0F, 0.0F, 0.0F, false);

        Cape4 = new ModelRenderer(this);
        Cape4.setRotationPoint(-0.0833F, 18.3333F, -4.5F);
        Cape3.addChild(Cape4);
        setRotationAngle(Cape4, -0.1745F, 0.0F, 0.0F);
        Cape4.setTextureOffset(0, 66).addBox(-2.25F, -13.5837F, 2.1742F, 1.0F, 3.0F, 0.0F, 0.0F, false);

        Cape5 = new ModelRenderer(this);
        Cape5.setRotationPoint(-0.0833F, 18.3333F, -4.5F);
        Cape3.addChild(Cape5);
        setRotationAngle(Cape5, -0.1745F, 0.0F, 0.0F);
        Cape5.setTextureOffset(0, 66).addBox(1.75F, -13.5839F, 2.1742F, 1.0F, 3.0F, 0.0F, 0.0F, true);
        Cape5.setTextureOffset(0, 66).addBox(-1.25F, -12.5839F, 2.1742F, 1.0F, 1.0F, 0.0F, 0.0F, true);
        Cape5.setTextureOffset(0, 66).addBox(0.75F, -12.5839F, 2.1742F, 1.0F, 1.0F, 0.0F, 0.0F, true);
        Cape5.setTextureOffset(0, 66).addBox(-1.25F, -13.5839F, 2.1742F, 3.0F, 1.0F, 0.0F, 0.0F, true);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(80, 24).addBox(-1.8988F, -1.1133F, -1.3746F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.2663F, 10.2885F, 1.7249F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.4843F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(59, 46).addBox(-2.1651F, -3.5688F, -1.6246F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        KneePad2 = new ModelRenderer(this);
        KneePad2.setRotationPoint(4.16F, 0.5674F, -7.6021F);
        RightFoot.addChild(KneePad2);
        KneePad2.setTextureOffset(19, 103).addBox(-6.1331F, -4.222F, 5.3033F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad2.setTextureOffset(5, 101).addBox(-5.1331F, -5.222F, 5.3033F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        KneePad2.setTextureOffset(5, 101).addBox(-5.1331F, -2.222F, 5.3033F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        KneePad2.setTextureOffset(64, 85).addBox(-2.9431F, -4.2505F, 6.4634F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        KneePad2.setTextureOffset(64, 85).addBox(-6.5602F, -4.2505F, 6.4634F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        KneePad2.setTextureOffset(64, 85).addBox(-2.9431F, -4.2505F, 5.7134F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad2.setTextureOffset(64, 85).addBox(-6.5602F, -4.2505F, 5.7134F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        KneePad2.setTextureOffset(64, 85).addBox(-5.6931F, -4.2505F, 9.4634F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad2.setTextureOffset(64, 85).addBox(-5.9431F, -4.2505F, 5.7134F, 4.0F, 2.0F, 1.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(80, 24).addBox(-2.0055F, -3.1995F, -1.4456F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(80, 41).addBox(-2.1887F, -7.1713F, 0.2958F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        KneePad1 = new ModelRenderer(this);
        KneePad1.setRotationPoint(0.1125F, -3.4197F, -0.8049F);
        LeftFoot.addChild(KneePad1);
        KneePad1.setTextureOffset(19, 103).addBox(-2.2651F, -3.7319F, 0.3649F, 4.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(5, 101).addBox(-1.2651F, -4.7319F, 0.3649F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(5, 101).addBox(-1.2651F, -1.7319F, 0.3649F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(64, 85).addBox(0.9249F, -3.7604F, 1.5251F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        KneePad1.setTextureOffset(64, 85).addBox(-2.6921F, -3.7604F, 1.5251F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        KneePad1.setTextureOffset(64, 85).addBox(0.9249F, -3.7604F, 0.7751F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(64, 85).addBox(-2.6921F, -3.7604F, 0.7751F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        KneePad1.setTextureOffset(64, 85).addBox(-1.8251F, -3.7604F, 4.5251F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(64, 85).addBox(-2.0751F, -3.7604F, 0.7751F, 4.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        HeadBase.render(matrixStack, buffer, packedLight, packedOverlay);
        BodyBase.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}