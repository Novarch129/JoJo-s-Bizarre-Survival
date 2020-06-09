package com.novarch.jojomod.entities.stands.whitesnake;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelWhitesnake<T extends EntityWhitesnake> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Helmet;
	private final ModelRenderer Mask1;
	private final ModelRenderer Mask2;
	private final ModelRenderer Spikes;
	private final ModelRenderer Neck;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer Chest;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightHand;
	private final ModelRenderer Beetle1;
	private final ModelRenderer Beetle5;
	private final ModelRenderer RightArm2;
	private final ModelRenderer RightHand2;
	private final ModelRenderer Crotch;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightFoot;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;

	public ModelWhitesnake() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.65F, 0.0F);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.1F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -11.65F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -8.65F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -8.65F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.1977F, -7.35F, -0.1818F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -4.3F, -4.3182F, 9.0F, 3.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -4.575F, -4.3182F, 9.0F, 1.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -1.725F, 3.6818F, 9.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-1.1977F, -1.3F, -4.3182F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(1.3023F, -0.3F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(1.3023F, 0.275F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, 0.275F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(1.3023F, 0.275F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(1.1273F, 0.275F, -4.3182F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-2.5227F, 0.275F, -4.3182F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(1.3023F, -0.3F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -0.3F, -4.3182F, 3.0F, 1.0F, 8.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(0.8023F, -0.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-2.1977F, -0.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(3.3023F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(3.3023F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(2.8023F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.1977F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, true);

		Mask1 = new ModelRenderer(this);
		Mask1.setRotationPoint(0.5773F, 1.2F, -0.3182F);
		Helmet.addChild(Mask1);
		setRotationAngle(Mask1, 0.0F, 0.0F, 0.9119F);
		Mask1.setTextureOffset(53, 0).addBox(-1.604F, -1.389F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);

		Mask2 = new ModelRenderer(this);
		Mask2.setRotationPoint(-0.9727F, 1.2F, -0.3182F);
		Helmet.addChild(Mask2);
		setRotationAngle(Mask2, 0.0F, 0.0F, -0.9119F);
		Mask2.setTextureOffset(53, 0).addBox(-0.396F, -1.389F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, true);

		Spikes = new ModelRenderer(this);
		Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(Spikes);
		Spikes.setTextureOffset(0, 74).addBox(3.3023F, -8.3F, -0.5682F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		Spikes.setTextureOffset(0, 74).addBox(-4.6977F, -8.3F, -0.5682F, 1.0F, 4.0F, 2.0F, 0.0F, true);
		Spikes.setTextureOffset(0, 74).addBox(-4.6977F, -8.3F, -4.3182F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		Spikes.setTextureOffset(0, 74).addBox(-1.0227F, -8.3F, -4.3182F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		Spikes.setTextureOffset(0, 74).addBox(3.3023F, -8.3F, -4.3182F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Spikes.setTextureOffset(0, 74).addBox(3.3023F, -8.3F, 3.6818F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		Spikes.setTextureOffset(0, 74).addBox(-4.6977F, -8.3F, 3.6818F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		Spikes.setTextureOffset(0, 74).addBox(-1.0227F, -8.3F, 3.6818F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Neck);
		Neck.setTextureOffset(0, 0).addBox(-1.75F, -4.45F, -1.25F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);


		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -6.2835F, -2.9126F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -27.1421F, -10.1842F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -12.6557F, -0.6344F, 7.0F, 8.0F, 4.0F, 0.0F, false);
		Abs.setTextureOffset(13, 78).addBox(-4.0F, -12.6568F, -0.7594F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);


		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-7.15F, -28.3015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-2.0989F, 0.3756F, -1.0955F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.8799F, -0.8544F, -3.0028F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		Beetle1 = new ModelRenderer(this);
		Beetle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle1);


		Beetle5 = new ModelRenderer(this);
		Beetle5.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle5);


		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(8.3F, -26.9765F, -1.3061F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
		RightArm2.setTextureOffset(27, 32).addBox(-2.6021F, -0.8058F, -1.4121F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.3259F, -1.25F, -2.9208F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.225F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(32, 68).addBox(1.5F, -20.6558F, -5.097F, 3.0F, 4.0F, 6.0F, 0.0F, false);
		Crotch.setTextureOffset(91, 96).addBox(-1.5F, -20.6558F, -4.447F, 3.0F, 4.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(91, 96).addBox(-1.5F, -20.6558F, -0.197F, 3.0F, 4.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(34, 89).addBox(-1.5F, -20.6558F, -5.097F, 3.0F, 2.0F, 6.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -20.6558F, -5.097F, 3.0F, 4.0F, 6.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);


		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-2.0081F, -2.5505F, -1.2085F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(-1.7604F, 9.8632F, 2.0442F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.1353F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-0.2476F, -3.8212F, -2.7626F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		RightFoot.setTextureOffset(0, 100).addBox(1.2869F, 3.859F, -2.9436F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		RightFoot.setTextureOffset(0, 100).addBox(1.2869F, 2.459F, -2.9436F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-1.7562F, -4.6205F, -1.591F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.0643F, -8.5493F, 0.7296F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		LeftFoot.setTextureOffset(0, 100).addBox(-0.6256F, -2.378F, 0.5088F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		LeftFoot.setTextureOffset(0, 100).addBox(-0.6256F, -0.978F, 0.5088F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		HeadBase.render(matrixStack, buffer, packedLight, packedOverlay);
		BodyBase.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}