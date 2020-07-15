package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.KillerQueenEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KillerQueenModel<T extends KillerQueenEntity> extends EntityModel<T>
{
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Mouth;
	private final ModelRenderer Ears;
	private final ModelRenderer RightEar;
	private final ModelRenderer LeftEar;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer Skulls;
	private final ModelRenderer Chest;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightHand;
	private final ModelRenderer LeftArm;
	private final ModelRenderer LeftHand;
	private final ModelRenderer Crotch;
	private final ModelRenderer Circle;
	private final ModelRenderer Medal;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer KneePadRight;
	private final ModelRenderer RightFoot;
	private final ModelRenderer Skull3;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer Skull1;
	private final ModelRenderer Skull2;
	private final ModelRenderer KneePadLeft;

	public KillerQueenModel()
	{
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -10.2F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(0, 19).addBox(-1.0F, -4.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.775F, 0.0F);
		Head.addChild(Ears);
		

		RightEar = new ModelRenderer(this);
		RightEar.setRotationPoint(0.875F, -0.35F, 0.0F);
		Ears.addChild(RightEar);
		setRotationAngle(RightEar, 0.0F, 0.0F, -0.0916F);
		RightEar.setTextureOffset(68, 0).addBox(-3.0F, -13.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);

		LeftEar = new ModelRenderer(this);
		LeftEar.setRotationPoint(-0.875F, -0.35F, 0.0F);
		Ears.addChild(LeftEar);
		setRotationAngle(LeftEar, 0.0F, 0.0F, 0.0916F);
		LeftEar.setTextureOffset(33, 0).addBox(2.0F, -13.0F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, true);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Skulls = new ModelRenderer(this);
		Skulls.setRotationPoint(0.0F, 25.5F, 1.0F);
		Torso.addChild(Skulls);
		Skulls.setTextureOffset(49, 0).addBox(9.35F, -29.378F, -1.921F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		Skulls.setTextureOffset(49, 0).addBox(-10.35F, -29.378F, -1.921F, 1.0F, 3.0F, 3.0F, 0.0F, true);

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
		RightHand.setTextureOffset(49, 0).addBox(-2.8037F, 3.6741F, -1.6475F, 1.0F, 2.0F, 2.0F, 0.0F, true);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(8.3F, -26.9765F, -1.3061F);
		Arms.addChild(LeftArm);
		setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.2662F);
		LeftArm.setTextureOffset(27, 32).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		LeftHand = new ModelRenderer(this);
		LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		LeftArm.addChild(LeftHand);
		setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.3272F);
		LeftHand.setTextureOffset(49, 0).addBox(1.9384F, 3.5849F, -1.2892F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		LeftHand.setTextureOffset(0, 53).addBox(-1.2528F, 0.1022F, -2.4024F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(32, 82).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 4.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 90).addBox(-4.5F, -19.2058F, 0.653F, 9.0F, 4.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(3.5F, -19.2058F, -4.347F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 1.0F, 4.0F, 5.0F, 0.0F, true);

		Circle = new ModelRenderer(this);
		Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Circle);
		setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);
		

		Medal = new ModelRenderer(this);
		Medal.setRotationPoint(0.0F, -15.0F, -4.0F);
		Crotch.addChild(Medal);
		setRotationAngle(Medal, -0.2051F, 0.0F, 0.0F);
		Medal.setTextureOffset(0, 66).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.3F, -15.425F, -2.4375F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.2138F, 0.2618F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		KneePadRight = new ModelRenderer(this);
		KneePadRight.setRotationPoint(-0.0617F, 1.4598F, 0.1126F);
		RightLeg.addChild(KneePadRight);
		KneePadRight.setTextureOffset(0, 75).addBox(-1.49F, 3.6094F, -1.8355F, 3.0F, 3.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		Skull3 = new ModelRenderer(this);
		Skull3.setRotationPoint(0.1251F, 0.668F, 2.5845F);
		RightFoot.addChild(Skull3);
		setRotationAngle(Skull3, 1.501F, -0.0218F, 0.0611F);
		Skull3.setTextureOffset(63, 0).addBox(-0.6007F, -0.3407F, -0.7487F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.175F, -14.25F, -1.9375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, 0.1353F, 0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-2.0055F, -3.1995F, -1.4456F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1887F, -7.1713F, 0.2958F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		Skull1 = new ModelRenderer(this);
		Skull1.setRotationPoint(-0.1836F, 12.2299F, 3.08F);
		LeftLeg.addChild(Skull1);
		setRotationAngle(Skull1, 0.5192F, -0.0131F, 0.0611F);
		Skull1.setTextureOffset(63, 0).addBox(-0.9827F, -0.824F, -1.8549F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Skull2 = new ModelRenderer(this);
		Skull2.setRotationPoint(-0.1836F, 12.2299F, 3.08F);
		LeftLeg.addChild(Skull2);
		setRotationAngle(Skull2, 0.5192F, -0.0131F, 0.0611F);
		Skull2.setTextureOffset(63, 0).addBox(-0.9827F, -0.824F, -1.8549F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		KneePadLeft = new ModelRenderer(this);
		KneePadLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.addChild(KneePadLeft);
		KneePadLeft.setTextureOffset(0, 75).addBox(-1.49F, 3.6094F, -1.8355F, 3.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) { }

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		HeadBase.render(matrixStack, buffer, packedLight, packedOverlay);
		BodyBase.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}