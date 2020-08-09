package io.github.novarch129.jojomod.client.entity.model;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * A default Stand model to be used for testing, will not be used in any release.
 */
@VisibleForTesting
public class DefaultStandModel<T extends AbstractStandEntity> extends AbstractStandModel<T> {
    private final ModelRenderer DefaultStandModel;
    private final ModelRenderer HeadBase;

    public DefaultStandModel() {
        textureWidth = 16; //Unimportant, won't use a texture with this anyway, would be a waste of time for a test class.
        textureHeight = 16;

        DefaultStandModel = new ModelRenderer(this);
        DefaultStandModel.setRotationPoint(0, 24, 0);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0, -30.5f, 0);
        DefaultStandModel.addChild(HeadBase);

        ModelRenderer head = new ModelRenderer(this);
        head.setRotationPoint(0, 2.5f, 0);
        HeadBase.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4, -10.2f, -4, 8, 8, 8, 0, false);

        ModelRenderer eyes = new ModelRenderer(this);
        eyes.setRotationPoint(0, 0, 0);
        head.addChild(eyes);
        eyes.setTextureOffset(0, 0).addBox(-3, -7.2f, -4.1f, 2, 1, 1, 0, false);
        eyes.setTextureOffset(0, 0).addBox(1, -7.2f, -4.1f, 2, 1, 1, 0, false);

        ModelRenderer bodyBase = new ModelRenderer(this);
        bodyBase.setRotationPoint(0, 0, 0);
        DefaultStandModel.addChild(bodyBase);

        ModelRenderer torso = new ModelRenderer(this);
        torso.setRotationPoint(0, -25.5f, -1);
        bodyBase.addChild(torso);
        setRotationAngle(torso, -0.2443f, 0, 0);
        torso.setTextureOffset(26, 18).addBox(-10, -4.8766f, -2.5619f, 20, 5, 6, 0, false);

        ModelRenderer chest = new ModelRenderer(this);
        chest.setRotationPoint(0, -3, -1.5f);
        bodyBase.addChild(chest);
        setRotationAngle(chest, -0.3229f, 0, 0);
        chest.setTextureOffset(45, 31).addBox(-5.5f, -25.767f, -9.7241f, 11, 6, 5, 0, false);

        ModelRenderer abs = new ModelRenderer(this);
        abs.setRotationPoint(3, -14, -3);
        bodyBase.addChild(abs);
        setRotationAngle(abs, 0.0087f, 0, 0);
        abs.setTextureOffset(0, 39).addBox(-6.5f, -11.2058f, -0.647f, 7, 8, 4, 0, false);

        ModelRenderer arms = new ModelRenderer(this);
        arms.setRotationPoint(0, 0, 0);
        bodyBase.addChild(arms);

        ModelRenderer rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-7.15f, -28.3015f, -1.3061f);
        arms.addChild(rightArm);
        setRotationAngle(rightArm, -0.2618f, 0, 0.2618f);
        rightArm.setTextureOffset(27, 32).addBox(-1.7236f, 1.7285f, -0.733f, 4, 6, 4, 0, false);

        ModelRenderer rightHand = new ModelRenderer(this);
        rightHand.setRotationPoint(0.8458f, 6.7811f, 1.817f);
        rightArm.addChild(rightHand);
        setRotationAngle(rightHand, -0.1134f, 0, -0.1134f);
        rightHand.setTextureOffset(0, 53).addBox(-2.6601f, 0.4823f, -2.4857f, 4, 6, 4, 0, false);

        ModelRenderer leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(8.3f, -26.9765f, -1.3061f);
        arms.addChild(leftArm);
        setRotationAngle(leftArm, -0.2618f, 0, -0.2662f);
        leftArm.setTextureOffset(27, 32).addBox(-2.9835f, 0.5455f, -1.05f, 4, 6, 4, 0, true);

        ModelRenderer leftHand = new ModelRenderer(this);
        leftHand.setRotationPoint(-1.5624f, 5.6204f, 1.506f);
        leftArm.addChild(leftHand);
        setRotationAngle(leftHand, -0.1134f, 0, 0.3272f);
        leftHand.setTextureOffset(0, 6).addBox(-1.2528f, 0.1022f, -2.4024f, 4, 6, 4, 0, true);

        ModelRenderer crotch = new ModelRenderer(this);
        crotch.setRotationPoint(0, 0, 0);
        bodyBase.addChild(crotch);
        crotch.setTextureOffset(32, 68).addBox(-4.5f, -19.2058f, -4.347f, 9, 5, 5, 0, false);

        ModelRenderer legs = new ModelRenderer(this);
        legs.setRotationPoint(0, 0, 0);
        bodyBase.addChild(legs);

        ModelRenderer rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-2.3f, -15.425f, -2.2125f);
        legs.addChild(rightLeg);
        setRotationAngle(rightLeg, 0.1353f, 0.2618f, 0.0436f);
        rightLeg.setTextureOffset(80, 24).addBox(-1.947f, -1.1129f, -1.3877f, 4, 9, 4, 0, true);

        ModelRenderer rightFoot = new ModelRenderer(this);
        rightFoot.setRotationPoint(0.14f, 14.3142f, 2.0955f);
        rightLeg.addChild(rightFoot);
        setRotationAngle(rightFoot, 0.8334f, 0, 0);
        rightFoot.setTextureOffset(59, 46).addBox(-2.037f, -6.8997f, 2.4154f, 4, 9, 4, 0, false);

        ModelRenderer leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(2.275f, -14.25f, -2.7375f);
        legs.addChild(leftLeg);
        setRotationAngle(leftLeg, -0.1265f, 0.1396f, -0.1745f);
        leftLeg.setTextureOffset(80, 24).addBox(-2.0055f, -3.1995f, -1.4456f, 4, 9, 4, 0, false);

        ModelRenderer leftFoot = new ModelRenderer(this);
        leftFoot.setRotationPoint(-0.3875f, 12.4197f, 1.0049f);
        leftLeg.addChild(leftFoot);
        setRotationAngle(leftFoot, 0.4058f, 0, 0.0873f);
        leftFoot.setTextureOffset(80, 41).addBox(-2.1887f, -7.1713f, 0.2958f, 4, 9, 4, 0, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        DefaultStandModel.render(matrixStack, buffer, packedLight, packedOverlay); //No need for RGB.
    }

    @Override
    protected ModelRenderer getHead() {
        return HeadBase; //Rotates the Stand's head to match it's master's rotation.
    }
}