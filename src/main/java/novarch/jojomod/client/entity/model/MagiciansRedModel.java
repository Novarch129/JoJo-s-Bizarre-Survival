package novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import novarch.jojomod.entities.stands.MagiciansRedEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MagiciansRedModel<T extends MagiciansRedEntity> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Head2;
	private final ModelRenderer Eyes;
	private final ModelRenderer Beak;
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer Hair;
	private final ModelRenderer Strand1;
	private final ModelRenderer Strand2;
	private final ModelRenderer bone2;
	private final ModelRenderer Strand4;
	private final ModelRenderer bone3;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer Chest;
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

	public MagiciansRedModel() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(HeadBase, 0.0F, -0.0873F, 0.0F);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		setRotationAngle(Head, 0.0F, 0.7854F, 0.0F);


		Head2 = new ModelRenderer(this);
		Head2.setRotationPoint(0.5F, -6.2F, -0.5F);
		Head.addChild(Head2);
		setRotationAngle(Head2, 0.0F, 0.8727F, 0.0F);
		Head2.setTextureOffset(2, 0).addBox(-3.5F, -5.0F, -3.5F, 7.0F, 8.0F, 7.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(2.3F, -9.75F, -2.275F);
		Head.addChild(Eyes);
		setRotationAngle(Eyes, 0.0F, -0.6981F, 0.0F);
		Eyes.setTextureOffset(0, 0).addBox(-3.2144F, 0.5F, -1.0571F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(0, 0).addBox(0.5356F, 0.5F, -1.0571F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		Beak = new ModelRenderer(this);
		Beak.setRotationPoint(0.2153F, -4.75F, -3.3176F);
		Head.addChild(Beak);
		setRotationAngle(Beak, 0.3971F, 0.8116F, 0.5236F);


		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.3655F, -0.4292F, 2.0131F);
		Beak.addChild(bone);
		bone.setTextureOffset(101, 3).addBox(-4.0942F, -2.1889F, -2.0996F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		bone.setTextureOffset(106, 7).addBox(-4.0942F, -2.1889F, 1.1504F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(2.6691F, 0.7241F, 2.0226F);
		Beak.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.0785F);
		bone4.setTextureOffset(101, 3).addBox(-4.8918F, -2.259F, -2.0996F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		bone4.setTextureOffset(106, 7).addBox(-4.8918F, -2.259F, 1.1504F, 6.0F, 1.0F, 1.0F, 0.0F, false);

		Hair = new ModelRenderer(this);
		Hair.setRotationPoint(-0.9642F, 0.0F, 1.1491F);
		Head.addChild(Hair);
		setRotationAngle(Hair, 0.0F, 0.0873F, 0.0F);


		Strand1 = new ModelRenderer(this);
		Strand1.setRotationPoint(-0.5F, -10.5F, 0.5F);
		Hair.addChild(Strand1);
		setRotationAngle(Strand1, 0.0F, -0.7854F, 0.0F);
		Strand1.setTextureOffset(99, 25).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Strand2 = new ModelRenderer(this);
		Strand2.setRotationPoint(-0.0654F, -1.0F, 0.7471F);
		Hair.addChild(Strand2);
		setRotationAngle(Strand2, 0.0F, -0.7854F, 0.0F);


		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-0.5F, -10.5F, 3.0F);
		Strand2.addChild(bone2);
		setRotationAngle(bone2, -0.3316F, 0.0F, 0.0F);
		bone2.setTextureOffset(99, 25).addBox(-0.4821F, -0.3764F, -3.3467F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		Strand4 = new ModelRenderer(this);
		Strand4.setRotationPoint(0.8988F, -1.0F, -0.4019F);
		Head.addChild(Strand4);
		setRotationAngle(Strand4, 0.0F, -0.6981F, 0.0F);


		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-0.7615F, -10.5F, 0.0114F);
		Strand4.addChild(bone3);
		setRotationAngle(bone3, 0.5323F, 0.0F, 0.0F);
		bone3.setTextureOffset(100, 37).addBox(-0.0237F, -0.9533F, -2.9729F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);


		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -5.8469F, -2.8038F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -26.7153F, -10.0414F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -12.2058F, -0.6383F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);


		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-7.15F, -28.3015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-1.9824F, 0.7955F, -0.983F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.8117F, -0.4396F, -2.8423F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(8.3F, -26.9765F, -1.3061F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
		RightArm2.setTextureOffset(27, 32).addBox(-2.7205F, -0.3864F, -1.2997F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.3032F, -0.8304F, -2.7599F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -20.2058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);


		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-1.9891F, -2.1044F, -1.2641F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.2225F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.1291F, -7.9771F, -1.6395F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-1.8336F, -4.1795F, -1.5458F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.7549F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1184F, -7.4239F, 3.4818F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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