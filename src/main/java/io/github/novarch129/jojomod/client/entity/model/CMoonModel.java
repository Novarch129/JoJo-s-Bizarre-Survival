package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.CMoonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CMoonModel<T extends CMoonEntity> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Helmet;
	private final ModelRenderer Mask1;
	private final ModelRenderer Mask2;
	private final ModelRenderer Neck;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer ShoulderPads;
	private final ModelRenderer Spikes1;
	private final ModelRenderer Spike1;
	private final ModelRenderer Spike2;
	private final ModelRenderer Spike3;
	private final ModelRenderer Arrow;
	private final ModelRenderer Spikes2;
	private final ModelRenderer Spike4;
	private final ModelRenderer Spike5;
	private final ModelRenderer Spike6;
	private final ModelRenderer Arrow2;
	private final ModelRenderer Chest;
	private final ModelRenderer Straps;
	private final ModelRenderer Strap1;
	private final ModelRenderer Strap3;
	private final ModelRenderer Straps2;
	private final ModelRenderer Strap2;
	private final ModelRenderer Strap4;
	private final ModelRenderer ChestSpikes;
	private final ModelRenderer ChestSpike1;
	private final ModelRenderer ChestSpike2;
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

	public CMoonModel() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.65F, 0.0F);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.1F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -11.675F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -8.65F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -8.65F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.1977F, -7.35F, -0.1818F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(69, 66).addBox(-4.6977F, -3.3F, -4.3182F, 9.0F, 2.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(69, 66).addBox(-4.6977F, -3.3F, -4.3182F, 9.0F, 2.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(69, 66).addBox(-4.6977F, -3.7F, -4.3182F, 9.0F, 1.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(102, 44).addBox(-0.6977F, -6.8F, -4.3182F, 1.0F, 1.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -5.9F, -0.3182F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -5.15F, -0.3182F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(3.3023F, -5.15F, -0.3182F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(-0.6977F, -5.8F, -4.3182F, 1.0F, 3.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(-0.6977F, -5.8F, 3.6818F, 1.0F, 3.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -1.725F, 3.6818F, 9.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, 2.775F, 3.6818F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-1.1977F, -1.3F, -4.3182F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(53, 0).addBox(-4.6977F, -0.3F, -4.3182F, 9.0F, 4.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(100, 15).addBox(-1.1977F, 1.7F, -4.3432F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(62, 88).addBox(3.3023F, -1.3F, -4.2932F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(62, 88).addBox(-4.6977F, -1.3F, -4.2932F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		Helmet.setTextureOffset(59, 0).addBox(2.8023F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Helmet.setTextureOffset(85, 87).addBox(-4.1977F, -1.3F, -4.3182F, 1.0F, 1.0F, 8.0F, 0.0F, true);

		Mask1 = new ModelRenderer(this);
		Mask1.setRotationPoint(0.5773F, 1.2F, -0.3182F);
		Helmet.addChild(Mask1);
		setRotationAngle(Mask1, 0.0F, 0.0F, 0.9119F);


		Mask2 = new ModelRenderer(this);
		Mask2.setRotationPoint(-0.9727F, 1.2F, -0.3182F);
		Helmet.addChild(Mask2);
		setRotationAngle(Mask2, 0.0F, 0.0F, -0.9119F);


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

		ShoulderPads = new ModelRenderer(this);
		ShoulderPads.setRotationPoint(0.0F, 0.0F, 0.0F);
		Torso.addChild(ShoulderPads);
		ShoulderPads.setTextureOffset(0, 74).addBox(5.5F, -7.0961F, -3.6646F, 5.0F, 6.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(0, 74).addBox(4.5F, -5.0961F, -3.6646F, 1.0F, 4.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(0, 74).addBox(1.5F, -2.0961F, -3.6646F, 4.0F, 1.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(0, 74).addBox(-2.0F, -1.0961F, -3.6646F, 4.0F, 1.0F, 7.0F, 0.0F, true);
		ShoulderPads.setTextureOffset(0, 74).addBox(-5.5F, -2.0961F, -3.6646F, 4.0F, 1.0F, 7.0F, 0.0F, true);
		ShoulderPads.setTextureOffset(0, 74).addBox(-5.5F, -5.0961F, -3.6646F, 1.0F, 4.0F, 7.0F, 0.0F, true);
		ShoulderPads.setTextureOffset(0, 74).addBox(-10.5F, -7.0961F, -3.6646F, 5.0F, 6.0F, 7.0F, 0.0F, true);

		Spikes1 = new ModelRenderer(this);
		Spikes1.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderPads.addChild(Spikes1);


		Spike1 = new ModelRenderer(this);
		Spike1.setRotationPoint(-7.05F, -7.6809F, -1.6231F);
		Spikes1.addChild(Spike1);
		setRotationAngle(Spike1, -0.1745F, 0.0F, 0.7854F);
		Spike1.setTextureOffset(100, 4).addBox(-0.5765F, -2.0964F, -1.3857F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Spike2 = new ModelRenderer(this);
		Spike2.setRotationPoint(-6.575F, -8.1522F, 0.7844F);
		Spikes1.addChild(Spike2);
		setRotationAngle(Spike2, -0.1353F, 0.0F, 0.6327F);
		Spike2.setTextureOffset(100, 4).addBox(-0.5619F, -1.9293F, -1.4872F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Spike3 = new ModelRenderer(this);
		Spike3.setRotationPoint(-6.575F, -8.2721F, 2.8157F);
		Spikes1.addChild(Spike3);
		setRotationAngle(Spike3, -0.1353F, 0.0F, 0.6327F);
		Spike3.setTextureOffset(100, 4).addBox(-0.5619F, -1.9293F, -1.4872F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Arrow = new ModelRenderer(this);
		Arrow.setRotationPoint(-8.969F, -8.7079F, 0.7869F);
		Spikes1.addChild(Arrow);
		setRotationAngle(Arrow, -0.1353F, 1.0603F, -0.6327F);
		Arrow.setTextureOffset(100, 4).addBox(-0.4381F, 0.0707F, -1.4872F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Arrow.setTextureOffset(100, 4).addBox(-0.4381F, -0.9293F, -1.9872F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Arrow.setTextureOffset(100, 4).addBox(-0.4381F, -1.6543F, -1.4872F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Spikes2 = new ModelRenderer(this);
		Spikes2.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderPads.addChild(Spikes2);


		Spike4 = new ModelRenderer(this);
		Spike4.setRotationPoint(7.05F, -7.6809F, -1.6231F);
		Spikes2.addChild(Spike4);
		setRotationAngle(Spike4, -0.1745F, 0.0F, -0.7854F);
		Spike4.setTextureOffset(101, 4).addBox(-0.4235F, -2.0964F, -1.3857F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Spike5 = new ModelRenderer(this);
		Spike5.setRotationPoint(6.575F, -8.1522F, 0.7844F);
		Spikes2.addChild(Spike5);
		setRotationAngle(Spike5, -0.1353F, 0.0F, -0.6327F);
		Spike5.setTextureOffset(101, 4).addBox(-0.4381F, -1.9293F, -1.4872F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Spike6 = new ModelRenderer(this);
		Spike6.setRotationPoint(6.575F, -8.2721F, 2.8157F);
		Spikes2.addChild(Spike6);
		setRotationAngle(Spike6, -0.1353F, 0.0F, -0.6327F);
		Spike6.setTextureOffset(101, 3).addBox(-0.4381F, -1.9293F, -1.4872F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Arrow2 = new ModelRenderer(this);
		Arrow2.setRotationPoint(8.969F, -8.7079F, 0.7869F);
		Spikes2.addChild(Arrow2);
		setRotationAngle(Arrow2, -0.1353F, -1.0603F, 0.6327F);
		Arrow2.setTextureOffset(101, 4).addBox(-0.5619F, 0.0707F, -1.4872F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Arrow2.setTextureOffset(101, 3).addBox(-0.5619F, -0.9293F, -1.9872F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Arrow2.setTextureOffset(101, 4).addBox(-0.5619F, -1.6543F, -1.4872F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -27.1421F, -10.1842F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Straps = new ModelRenderer(this);
		Straps.setRotationPoint(0.0F, 3.0F, 1.5F);
		Chest.addChild(Straps);


		Strap1 = new ModelRenderer(this);
		Strap1.setRotationPoint(-4.05F, -23.7604F, -11.5174F);
		Straps.addChild(Strap1);
		setRotationAngle(Strap1, 0.0F, 0.0F, 0.8378F);
		Strap1.setTextureOffset(0, 92).addBox(-1.0F, -2.5F, -0.35F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		Strap1.setTextureOffset(0, 92).addBox(-0.9973F, -3.5002F, 5.1967F, 2.0F, 5.0F, 1.0F, 0.0F, true);

		Strap3 = new ModelRenderer(this);
		Strap3.setRotationPoint(-4.05F, -23.5589F, -8.0228F);
		Straps.addChild(Strap3);
		setRotationAngle(Strap3, 1.5839F, 0.0F, 0.8639F);
		Strap3.setTextureOffset(0, 92).addBox(-1.1136F, -2.8626F, -1.3574F, 2.0F, 5.0F, 1.0F, 0.0F, true);

		Straps2 = new ModelRenderer(this);
		Straps2.setRotationPoint(0.0F, 3.0F, 1.5F);
		Chest.addChild(Straps2);


		Strap2 = new ModelRenderer(this);
		Strap2.setRotationPoint(4.05F, -23.7604F, -11.5174F);
		Straps2.addChild(Strap2);
		setRotationAngle(Strap2, 0.0F, 0.0F, -0.8378F);
		Strap2.setTextureOffset(0, 93).addBox(-1.0F, -2.5F, -0.35F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		Strap2.setTextureOffset(0, 93).addBox(-1.0027F, -3.5002F, 5.1967F, 2.0F, 5.0F, 1.0F, 0.0F, true);

		Strap4 = new ModelRenderer(this);
		Strap4.setRotationPoint(4.05F, -23.5589F, -8.0228F);
		Straps2.addChild(Strap4);
		setRotationAngle(Strap4, 1.5839F, 0.0F, -0.8639F);
		Strap4.setTextureOffset(0, 93).addBox(-0.8864F, -2.8626F, -1.3574F, 2.0F, 5.0F, 1.0F, 0.0F, true);

		ChestSpikes = new ModelRenderer(this);
		ChestSpikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Chest.addChild(ChestSpikes);


		ChestSpike1 = new ModelRenderer(this);
		ChestSpike1.setRotationPoint(-3.0966F, -23.3105F, -11.0814F);
		ChestSpikes.addChild(ChestSpike1);
		setRotationAngle(ChestSpike1, -1.0079F, 0.0F, -0.2138F);
		ChestSpike1.setTextureOffset(0, 106).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		ChestSpike2 = new ModelRenderer(this);
		ChestSpike2.setRotationPoint(3.0966F, -23.3105F, -11.0814F);
		ChestSpikes.addChild(ChestSpike2);
		setRotationAngle(ChestSpike2, -1.0079F, 0.0F, 0.2138F);
		ChestSpike2.setTextureOffset(0, 106).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(104, 122).addBox(-6.0F, -8.6558F, -0.6518F, 6.0F, 2.0F, 4.0F, 0.0F, false);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -12.6557F, -0.6344F, 7.0F, 4.0F, 4.0F, 0.0F, false);

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
		setRotationAngle(RightArm2, -0.3622F, 0.0F, -0.2662F);
		RightArm2.setTextureOffset(27, 32).addBox(-2.6021F, -0.8058F, -1.4121F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.3259F, -1.25F, -2.9208F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.225F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.6558F, -5.097F, 9.0F, 3.0F, 6.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 81).addBox(-4.0F, -20.6558F, -4.597F, 8.0F, 1.0F, 5.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);


		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.35F, -15.425F, -2.2125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.1396F, 0.6894F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-2.0081F, -2.5505F, -1.2085F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(-1.7604F, 9.8632F, 2.0442F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.4538F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-0.2476F, -4.4942F, -1.427F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		RightFoot.setTextureOffset(31, 98).addBox(1.3086F, 1.5343F, -1.499F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.275F, -14.25F, -3.1875F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.3316F, 0.0829F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-1.7562F, -4.6205F, -1.591F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.0643F, -8.5493F, 0.7296F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		LeftFoot.setTextureOffset(31, 98).addBox(-0.5643F, -2.5493F, 0.6546F, 1.0F, 3.0F, 1.0F, 0.0F, false);
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