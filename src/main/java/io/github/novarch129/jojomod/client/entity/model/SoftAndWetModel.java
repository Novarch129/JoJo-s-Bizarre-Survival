package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.SoftAndWetEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SoftAndWetModel extends AbstractStandModel<SoftAndWetEntity> {
    private final ModelRenderer SoftAndWet;
    private final ModelRenderer HeadBase;

    public SoftAndWetModel() {
        textureWidth = 128;
        textureHeight = 128;

        SoftAndWet = new ModelRenderer(this);
        SoftAndWet.setRotationPoint(0.0F, 24.0F, 1.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -28.75F, -1.75F);
        SoftAndWet.addChild(HeadBase);


        ModelRenderer head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.75F, -0.25F);
        HeadBase.addChild(head);
        head.setTextureOffset(0, 0).addBox(0.5F, -9.2F, -4.0F, 3.0F, 7.0F, 7.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-3.5F, -9.2F, -4.0F, 3.0F, 7.0F, 7.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(-0.5F, -9.2F, -4.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
        head.setTextureOffset(22, 0).addBox(-3.0F, -10.2F, -4.0F, 6.0F, 1.0F, 7.0F, 0.0F, false);
        head.setTextureOffset(67, 20).addBox(-0.5F, -7.2F, 2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(50, 0).addBox(-0.5F, -7.2F, -3.0F, 1.0F, 5.0F, 5.0F, 0.0F, false);

        ModelRenderer hornpiece = new ModelRenderer(this);
        hornpiece.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(hornpiece);
        hornpiece.setTextureOffset(0, 16).addBox(-6.0F, -6.2F, -1.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
        hornpiece.setTextureOffset(23, 9).addBox(-4.0F, -7.2F, -2.0F, 8.0F, 3.0F, 3.0F, 0.0F, false);

        ModelRenderer eyes = new ModelRenderer(this);
        eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(eyes);
        eyes.setTextureOffset(0, 0).addBox(1.5F, -7.0F, -4.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        eyes.setTextureOffset(0, 0).addBox(-2.5F, -7.0F, -4.1F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        ModelRenderer bodyBase = new ModelRenderer(this);
        bodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        SoftAndWet.addChild(bodyBase);


        ModelRenderer torso = new ModelRenderer(this);
        torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        bodyBase.addChild(torso);
        setRotationAngle(torso, -0.0261F, 0.0F, 0.0F);
        torso.setTextureOffset(0, 75).addBox(-8.0F, -2.5F, -2.0254F, 16.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer chest = new ModelRenderer(this);
        chest.setRotationPoint(0.0F, 0.0F, -1.5F);
        bodyBase.addChild(chest);
        setRotationAngle(chest, 0.0262F, 0.0F, 0.0F);
        chest.setTextureOffset(45, 31).addBox(0.5F, -25.7667F, -2.6979F, 3.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(45, 31).addBox(0.5F, -24.767F, -2.7241F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(45, 31).addBox(-3.5F, -29.7653F, 1.4067F, 7.0F, 9.0F, 1.0F, 0.0F, false);
        chest.setTextureOffset(45, 31).addBox(-1.5F, -20.7653F, 1.4067F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        chest.setTextureOffset(45, 31).addBox(-2.5F, -24.767F, -2.7241F, 2.0F, 1.0F, 5.0F, 0.0F, true);
        chest.setTextureOffset(45, 31).addBox(-3.5F, -25.7667F, -2.6979F, 3.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(47, 13).addBox(-4.5F, -25.7677F, -2.7764F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(54, 43).addBox(3.5F, -25.7677F, -2.7764F, 1.0F, 3.0F, 5.0F, 0.0F, true);
        chest.setTextureOffset(52, 53).addBox(0.5F, -22.7673F, -2.7503F, 4.0F, 2.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(34, 22).addBox(-4.5F, -22.7673F, -2.7503F, 4.0F, 2.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(65, 0).addBox(-2.5F, -20.7677F, -2.7764F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(65, 0).addBox(0.5F, -20.7677F, -2.7764F, 2.0F, 1.0F, 5.0F, 0.0F, true);
        chest.setTextureOffset(12, 64).addBox(1.5F, -27.7653F, -2.5933F, 4.0F, 2.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(68, 41).addBox(-0.5F, -27.3F, -2.5933F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(82, 0).addBox(0.5F, -29.7653F, -2.5933F, 6.0F, 2.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(65, 33).addBox(0.5F, -30.7653F, -2.5933F, 2.0F, 1.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(65, 33).addBox(-2.5F, -30.7653F, -2.5933F, 2.0F, 1.0F, 5.0F, 0.0F, true);
        chest.setTextureOffset(65, 33).addBox(-0.5F, -30.7653F, -0.5933F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        chest.setTextureOffset(82, 0).addBox(-6.5F, -29.7653F, -2.5933F, 6.0F, 2.0F, 5.0F, 0.0F, false);
        chest.setTextureOffset(12, 64).addBox(-5.5F, -27.7653F, -2.5933F, 4.0F, 2.0F, 5.0F, 0.0F, true);

        ModelRenderer abs = new ModelRenderer(this);
        abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        bodyBase.addChild(abs);
        setRotationAngle(abs, 0.0087F, 0.0F, 0.0F);
        abs.setTextureOffset(14, 21).addBox(-6.5F, -15.2058F, -0.647F, 7.0F, 8.0F, 2.0F, 0.0F, false);
        abs.setTextureOffset(40, 45).addBox(-4.5F, -7.2058F, -0.647F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        abs.setTextureOffset(37, 61).addBox(-4.5F, -16.2058F, -0.647F, 3.0F, 1.0F, 3.0F, 0.0F, false);

        ModelRenderer arms = new ModelRenderer(this);
        arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        bodyBase.addChild(arms);


        ModelRenderer leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(10.3F, -28.9765F, -1.3061F);
        arms.addChild(leftArm);
        setRotationAngle(leftArm, -0.0436F, 0.0F, -0.1789F);
        leftArm.setTextureOffset(0, 33).addBox(-4.484F, 0.3677F, -2.4017F, 3.0F, 7.0F, 3.0F, 0.0F, true);

        ModelRenderer leftHand = new ModelRenderer(this);
        leftHand.setRotationPoint(-3.6666F, 13.0757F, 3.5036F);
        leftArm.addChild(leftHand);
        setRotationAngle(leftHand, -0.1134F, 0.0F, 0.3272F);
        leftHand.setTextureOffset(14, 32).addBox(-2.6223F, -3.9094F, -6.3885F, 3.0F, 7.0F, 3.0F, 0.0F, true);
        leftHand.setTextureOffset(0, 46).addBox(0.3777F, -0.9094F, -6.8885F, 1.0F, 4.0F, 4.0F, 0.0F, true);
        leftHand.setTextureOffset(33, 17).addBox(-1.9223F, -5.9094F, -5.8885F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftShoulder = new ModelRenderer(this);
        leftShoulder.setRotationPoint(-0.984F, -0.1778F, -0.0078F);
        leftArm.addChild(leftShoulder);
        setRotationAngle(leftShoulder, 0.0F, 0.0F, -0.9599F);
        leftShoulder.setTextureOffset(0, 0).addBox(-0.9F, -0.1235F, -2.3939F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        leftShoulder.setTextureOffset(24, 1).addBox(-0.9F, 0.8765F, -1.3939F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        leftShoulder.setTextureOffset(0, 21).addBox(-0.9F, -3.1235F, -3.3939F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        leftShoulder.setTextureOffset(16, 1).addBox(-0.9F, -4.1235F, -3.3939F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        leftShoulder.setTextureOffset(16, 1).addBox(-0.9F, -4.1235F, -0.3939F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        ModelRenderer leftArm2 = new ModelRenderer(this);
        leftArm2.setRotationPoint(-10.3F, -28.9765F, -1.3061F);
        arms.addChild(leftArm2);
        setRotationAngle(leftArm2, -0.0436F, 0.0F, 0.1789F);
        leftArm2.setTextureOffset(0, 33).addBox(1.484F, 0.3677F, -2.4017F, 3.0F, 7.0F, 3.0F, 0.0F, false);

        ModelRenderer leftHand2 = new ModelRenderer(this);
        leftHand2.setRotationPoint(3.6666F, 13.0757F, 3.5036F);
        leftArm2.addChild(leftHand2);
        setRotationAngle(leftHand2, -0.1134F, 0.0F, -0.3272F);
        leftHand2.setTextureOffset(14, 32).addBox(-0.3777F, -3.9094F, -6.3885F, 3.0F, 7.0F, 3.0F, 0.0F, false);
        leftHand2.setTextureOffset(0, 46).addBox(-1.3777F, -0.9094F, -6.8885F, 1.0F, 4.0F, 4.0F, 0.0F, false);
        leftHand2.setTextureOffset(33, 17).addBox(-0.0777F, -5.9094F, -5.8885F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer leftShoulder2 = new ModelRenderer(this);
        leftShoulder2.setRotationPoint(0.984F, -0.1778F, -0.0078F);
        leftArm2.addChild(leftShoulder2);
        setRotationAngle(leftShoulder2, 0.0F, 0.0F, 0.9599F);
        leftShoulder2.setTextureOffset(0, 0).addBox(-0.1F, -0.1235F, -2.3939F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        leftShoulder2.setTextureOffset(24, 1).addBox(-0.1F, 0.8765F, -1.3939F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        leftShoulder2.setTextureOffset(0, 21).addBox(-0.1F, -3.1235F, -3.3939F, 1.0F, 3.0F, 5.0F, 0.0F, true);
        leftShoulder2.setTextureOffset(16, 1).addBox(-0.1F, -4.1235F, -3.3939F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        leftShoulder2.setTextureOffset(16, 1).addBox(-0.1F, -4.1235F, -0.3939F, 1.0F, 1.0F, 2.0F, 0.0F, true);

        ModelRenderer crotch = new ModelRenderer(this);
        crotch.setRotationPoint(0.0F, -2.0F, 0.0F);
        bodyBase.addChild(crotch);
        crotch.setTextureOffset(32, 68).addBox(-1.5F, -19.0F, -3.6924F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        crotch.setTextureOffset(28, 46).addBox(-1.5F, -17.0F, -4.6924F, 3.0F, 4.0F, 4.0F, 0.0F, false);
        crotch.setTextureOffset(12, 58).addBox(-4.5F, -17.2348F, -3.1F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer plate = new ModelRenderer(this);
        plate.setRotationPoint(-1.0F, 1.0F, 0.0F);
        crotch.addChild(plate);
        setRotationAngle(plate, 0.0F, 0.0F, -0.3054F);
        plate.setTextureOffset(64, 9).addBox(10.0F, -16.0F, -4.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
        plate.setTextureOffset(54, 25).addBox(10.0F, -14.0F, -3.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        plate.setTextureOffset(54, 25).addBox(10.0F, -13.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        plate.setTextureOffset(54, 25).addBox(10.0F, -17.0F, -3.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);

        ModelRenderer plate2 = new ModelRenderer(this);
        plate2.setRotationPoint(1.0F, 1.0F, 0.0F);
        crotch.addChild(plate2);
        setRotationAngle(plate2, 0.0F, 0.0F, 0.3054F);
        plate2.setTextureOffset(64, 9).addBox(-11.0F, -16.0F, -4.5F, 1.0F, 2.0F, 5.0F, 0.0F, true);
        plate2.setTextureOffset(54, 25).addBox(-11.0F, -14.0F, -3.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        plate2.setTextureOffset(54, 25).addBox(-11.0F, -13.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        plate2.setTextureOffset(54, 25).addBox(-11.0F, -17.0F, -3.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);

        ModelRenderer joint = new ModelRenderer(this);
        joint.setRotationPoint(-8.0F, -3.0F, 0.0F);
        crotch.addChild(joint);
        setRotationAngle(joint, 0.0F, 0.0F, 0.7418F);
        joint.setTextureOffset(67, 29).addBox(-1.6756F, -15.7373F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer joint2 = new ModelRenderer(this);
        joint2.setRotationPoint(8.0F, -3.0F, 0.0F);
        crotch.addChild(joint2);
        setRotationAngle(joint2, 0.0F, 0.0F, -0.7418F);
        joint2.setTextureOffset(67, 29).addBox(-0.3244F, -15.7373F, -3.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer legs = new ModelRenderer(this);
        legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        bodyBase.addChild(legs);


        ModelRenderer leftLeg2 = new ModelRenderer(this);
        leftLeg2.setRotationPoint(-2.275F, -12.25F, -2.7375F);
        legs.addChild(leftLeg2);
        setRotationAngle(leftLeg2, -0.1265F, 0.1658F, 0.1309F);
        leftLeg2.setTextureOffset(13, 46).addBox(-1.9945F, -2.1995F, -1.4456F, 3.0F, 6.0F, 3.0F, 0.0F, true);

        ModelRenderer leftFoot2 = new ModelRenderer(this);
        leftFoot2.setRotationPoint(0.3875F, 12.4197F, 1.0049F);
        leftLeg2.addChild(leftFoot2);
        setRotationAngle(leftFoot2, 0.144F, 0.0F, 0.0436F);
        leftFoot2.setTextureOffset(29, 32).addBox(-2.7125F, -7.1731F, -1.1674F, 3.0F, 7.0F, 3.0F, 0.0F, true);
        leftFoot2.setTextureOffset(0, 57).addBox(-2.2125F, -9.1731F, -0.6674F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        leftFoot2.setTextureOffset(0, 64).addBox(-2.7125F, -16.1731F, 0.3326F, 2.0F, 2.0F, 2.0F, 0.0F, true);

        ModelRenderer leftLeg3 = new ModelRenderer(this);
        leftLeg3.setRotationPoint(2.275F, -12.25F, -2.7375F);
        legs.addChild(leftLeg3);
        setRotationAngle(leftLeg3, -0.1265F, -0.1658F, -0.1309F);
        leftLeg3.setTextureOffset(13, 46).addBox(-1.0055F, -2.1995F, -1.4456F, 3.0F, 6.0F, 3.0F, 0.0F, false);

        ModelRenderer leftFoot3 = new ModelRenderer(this);
        leftFoot3.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        leftLeg3.addChild(leftFoot3);
        setRotationAngle(leftFoot3, 0.144F, 0.0F, -0.0436F);
        leftFoot3.setTextureOffset(29, 32).addBox(-0.2875F, -7.1731F, -1.1674F, 3.0F, 7.0F, 3.0F, 0.0F, false);
        leftFoot3.setTextureOffset(0, 57).addBox(0.2125F, -9.1731F, -0.6674F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        leftFoot3.setTextureOffset(0, 64).addBox(0.7125F, -16.1731F, 0.3326F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        SoftAndWet.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}