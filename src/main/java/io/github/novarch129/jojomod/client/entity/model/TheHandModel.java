package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TheHandEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheHandModel extends AbstractStandModel<TheHandEntity> {
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Spikes;
    private final ModelRenderer Spike1;
    private final ModelRenderer Spike8;
    private final ModelRenderer Spikes3;
    private final ModelRenderer Spike9;
    private final ModelRenderer Spike10;
    private final ModelRenderer Eyes;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer ShoulderPads;
    private final ModelRenderer ShoulderSpikes;
    private final ModelRenderer Spikes1;
    private final ModelRenderer Spike2;
    private final ModelRenderer Spike3;
    private final ModelRenderer Spike4;
    private final ModelRenderer Spikes2;
    private final ModelRenderer Spike5;
    private final ModelRenderer Spike6;
    private final ModelRenderer Spike7;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer ElbowPad2;
    private final ModelRenderer RightArm2;
    private final ModelRenderer RightHand2;
    private final ModelRenderer ElbowPad1;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer KneePad2;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;
    private final ModelRenderer KneePad1;

    public TheHandModel() {
        textureWidth = 128;
        textureHeight = 128;

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -6.25F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 2.25F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.5F, -10.2F, -4.0F, 7.0F, 8.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(0, 76).addBox(-3.8F, -10.2F, -4.25F, 1.0F, 8.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(0, 76).addBox(2.8F, -10.2F, -4.25F, 1.0F, 8.0F, 8.0F, 0.0F, true);
        Head.setTextureOffset(0, 76).addBox(2.8F, -10.5F, -4.25F, 1.0F, 1.0F, 8.0F, 0.0F, true);
        Head.setTextureOffset(0, 76).addBox(-3.8F, -10.5F, -4.25F, 1.0F, 1.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(0, 76).addBox(-3.8F, -10.5F, 3.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Head.setTextureOffset(0, 76).addBox(-3.2F, -10.5F, 3.25F, 7.0F, 1.0F, 1.0F, 0.0F, true);
        Head.setTextureOffset(0, 103).addBox(-3.2F, -10.2F, 3.25F, 7.0F, 8.0F, 1.0F, 0.0F, true);
        Head.setTextureOffset(0, 103).addBox(-3.8F, -10.2F, 3.25F, 1.0F, 8.0F, 1.0F, 0.0F, true);

        Spikes = new ModelRenderer(this);
        Spikes.setRotationPoint(0.15F, 28.0F, 0.0F);
        Head.addChild(Spikes);


        Spike1 = new ModelRenderer(this);
        Spike1.setRotationPoint(4.5F, -34.2F, 0.0F);
        Spikes.addChild(Spike1);
        setRotationAngle(Spike1, 0.0F, 0.0F, 0.4363F);
        Spike1.setTextureOffset(0, 69).addBox(-2.6371F, -0.3612F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Spike8 = new ModelRenderer(this);
        Spike8.setRotationPoint(4.5F, -34.75F, 0.0F);
        Spikes.addChild(Spike8);
        setRotationAngle(Spike8, 0.0F, 0.0F, -0.5149F);
        Spike8.setTextureOffset(0, 69).addBox(-2.3467F, -0.2497F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Spikes3 = new ModelRenderer(this);
        Spikes3.setRotationPoint(-0.15F, 28.0F, 0.0F);
        Head.addChild(Spikes3);


        Spike9 = new ModelRenderer(this);
        Spike9.setRotationPoint(-4.5F, -34.2F, 0.0F);
        Spikes3.addChild(Spike9);
        setRotationAngle(Spike9, 0.0F, 0.0F, -0.4363F);
        Spike9.setTextureOffset(0, 69).addBox(0.705F, -0.3295F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, true);

        Spike10 = new ModelRenderer(this);
        Spike10.setRotationPoint(-4.5F, -34.75F, 0.0F);
        Spikes3.addChild(Spike10);
        setRotationAngle(Spike10, 0.0F, 0.0F, 0.5149F);
        Spike10.setTextureOffset(0, 69).addBox(0.412F, -0.2866F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 0.25F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-2.75F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(0.75F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(27, 0).addBox(-1.0F, -4.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(27, 0).addBox(-0.5F, -3.95F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);

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
        ShoulderPads.setTextureOffset(48, 0).addBox(-10.5F, -5.6266F, -3.0619F, 5.0F, 6.0F, 7.0F, 0.0F, false);
        ShoulderPads.setTextureOffset(48, 0).addBox(5.5F, -5.6266F, -3.0619F, 5.0F, 6.0F, 7.0F, 0.0F, true);

        ShoulderSpikes = new ModelRenderer(this);
        ShoulderSpikes.setRotationPoint(0.0F, 25.5F, 1.0F);
        ShoulderPads.addChild(ShoulderSpikes);


        Spikes1 = new ModelRenderer(this);
        Spikes1.setRotationPoint(0.0F, 0.0F, 0.0F);
        ShoulderSpikes.addChild(Spikes1);


        Spike2 = new ModelRenderer(this);
        Spike2.setRotationPoint(8.0F, -31.6281F, 1.9667F);
        Spikes1.addChild(Spike2);
        setRotationAngle(Spike2, -0.6283F, 0.0F, 0.0F);
        Spike2.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        Spike3 = new ModelRenderer(this);
        Spike3.setRotationPoint(8.0F, -31.4467F, -2.6945F);
        Spikes1.addChild(Spike3);
        setRotationAngle(Spike3, 0.6283F, 0.0F, 0.0F);
        Spike3.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        Spike4 = new ModelRenderer(this);
        Spike4.setRotationPoint(8.0F, -32.2338F, -0.4181F);
        Spikes1.addChild(Spike4);
        setRotationAngle(Spike4, 0.0175F, 0.0F, 0.0F);
        Spike4.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        Spikes2 = new ModelRenderer(this);
        Spikes2.setRotationPoint(0.0F, 0.0F, 0.0F);
        ShoulderSpikes.addChild(Spikes2);


        Spike5 = new ModelRenderer(this);
        Spike5.setRotationPoint(-8.0F, -31.6281F, 1.9667F);
        Spikes2.addChild(Spike5);
        setRotationAngle(Spike5, -0.6283F, 0.0F, 0.0F);
        Spike5.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Spike6 = new ModelRenderer(this);
        Spike6.setRotationPoint(-8.0F, -31.4467F, -2.6945F);
        Spikes2.addChild(Spike6);
        setRotationAngle(Spike6, 0.6283F, 0.0F, 0.0F);
        Spike6.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Spike7 = new ModelRenderer(this);
        Spike7.setRotationPoint(-8.0F, -32.2338F, -0.4181F);
        Spikes2.addChild(Spike7);
        setRotationAngle(Spike7, 0.0175F, 0.0F, 0.0F);
        Spike7.setTextureOffset(118, 2).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(94, 63).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);
        Chest.setTextureOffset(120, 15).addBox(-5.175F, -25.367F, -10.0241F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        Chest.setTextureOffset(120, 15).addBox(2.175F, -25.367F, -10.0241F, 3.0F, 3.0F, 1.0F, 0.0F, true);

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
        ElbowPad2.setRotationPoint(10.4803F, 26.1954F, -1.5578F);
        RightHand.addChild(ElbowPad2);
        ElbowPad2.setTextureOffset(31, 85).addBox(-13.6864F, -27.014F, -1.2511F, 1.0F, 3.0F, 4.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(31, 85).addBox(-9.6864F, -26.014F, -1.5261F, 1.0F, 1.0F, 4.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(31, 85).addBox(-13.6864F, -26.014F, -1.5011F, 5.0F, 1.0F, 1.0F, 0.0F, true);
        ElbowPad2.setTextureOffset(31, 85).addBox(-13.6864F, -26.014F, 2.1239F, 5.0F, 1.0F, 1.0F, 0.0F, true);

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

        ElbowPad1 = new ModelRenderer(this);
        ElbowPad1.setRotationPoint(-10.2376F, 26.2061F, -1.1999F);
        RightHand2.addChild(ElbowPad1);
        ElbowPad1.setTextureOffset(31, 85).addBox(12.6864F, -27.014F, -1.2511F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(31, 85).addBox(8.6864F, -26.014F, -1.5261F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(31, 85).addBox(8.6864F, -26.014F, -1.5011F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        ElbowPad1.setTextureOffset(31, 85).addBox(8.6864F, -26.014F, 1.9239F, 5.0F, 1.0F, 1.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-1.5F, -19.2808F, -4.622F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(32, 68).addBox(-1.0F, -16.2808F, -4.622F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Crotch.setTextureOffset(67, 67).addBox(4.0F, -18.7058F, -3.847F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        Crotch.setTextureOffset(67, 67).addBox(-5.0F, -18.7058F, -3.847F, 1.0F, 4.0F, 4.0F, 0.0F, true);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        KneePad2 = new ModelRenderer(this);
        KneePad2.setRotationPoint(2.5211F, -3.6407F, 1.7641F);
        RightFoot.addChild(KneePad2);
        setRotationAngle(KneePad2, -0.0654F, 0.0F, 0.0F);
        KneePad2.setTextureOffset(62, 85).addBox(-4.5F, -3.575F, 0.15F, 4.0F, 3.0F, 1.0F, 0.0F, true);
        KneePad2.setTextureOffset(80, 85).addBox(-1.15F, -2.575F, 0.175F, 1.0F, 1.0F, 5.0F, 0.0F, true);
        KneePad2.setTextureOffset(80, 85).addBox(-5.05F, -2.575F, 0.175F, 1.0F, 1.0F, 5.0F, 0.0F, true);
        KneePad2.setTextureOffset(80, 85).addBox(-5.15F, -2.575F, 4.175F, 5.0F, 1.0F, 1.0F, 0.0F, true);

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
        KneePad1.setRotationPoint(-1.5539F, -3.2769F, 3.2188F);
        LeftFoot.addChild(KneePad1);
        setRotationAngle(KneePad1, -0.0654F, 0.0F, 0.0F);
        KneePad1.setTextureOffset(64, 85).addBox(-0.5F, -4.5F, -3.5F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        KneePad1.setTextureOffset(79, 85).addBox(2.85F, -3.5F, -3.475F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        KneePad1.setTextureOffset(79, 85).addBox(-1.05F, -3.5F, -3.475F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        KneePad1.setTextureOffset(79, 85).addBox(-1.15F, -3.5F, 0.525F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        HeadBase.render(matrixStack, buffer, packedLight, packedOverlay);
        BodyBase.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected ModelRenderer getHead() {
        return HeadBase;
    }
}