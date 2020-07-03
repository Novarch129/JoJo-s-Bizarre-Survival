package com.novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.PurpleHazeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PurpleHazeModel<T extends PurpleHazeEntity> extends EntityModel<T> {
	private final ModelRenderer PurpleHaze;
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Ears;
	private final ModelRenderer Neck;
	private final ModelRenderer Helmet;
	private final ModelRenderer HelmetPart1;
	private final ModelRenderer HelmetPart6;
	private final ModelRenderer HelmetPart7;
	private final ModelRenderer HelmetPart8;
	private final ModelRenderer HelmetPart9;
	private final ModelRenderer HelmetPart2;
	private final ModelRenderer HelmetPart10;
	private final ModelRenderer Spike;
	private final ModelRenderer HelmetPart3;
	private final ModelRenderer HelmetPart4;
	private final ModelRenderer HelmetPart5;
	private final ModelRenderer TopSpike;
	private final ModelRenderer TopSpikePart1;
	private final ModelRenderer TopSpikePart2;
	private final ModelRenderer TopSpikePart3;
	private final ModelRenderer Mouth;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Body;
	private final ModelRenderer Torso;
	private final ModelRenderer Cape;
	private final ModelRenderer CapePart1;
	private final ModelRenderer CapePart2;
	private final ModelRenderer Spikes;
	private final ModelRenderer ShoulderPads;
	private final ModelRenderer Chest;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm2;
	private final ModelRenderer RightHand2;
	private final ModelRenderer ElbowPad1;
	private final ModelRenderer Capsules1;
	private final ModelRenderer RightArm3;
	private final ModelRenderer RightHand3;
	private final ModelRenderer ElbowPad2;
	private final ModelRenderer Capsules;
	private final ModelRenderer Crotch;
	private final ModelRenderer Circle;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightFoot;
	private final ModelRenderer KneePad2;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer KneePad1;

	public PurpleHazeModel() {
		textureWidth = 128;
		textureHeight = 128;

		PurpleHaze = new ModelRenderer(this);
		PurpleHaze.setRotationPoint(0.0F, 24.0F, 2.0F);
		setRotationAngle(PurpleHaze, 0.144F, 0.0F, 0.0F);


		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -28.0F, 0.0F);
		PurpleHaze.addChild(HeadBase);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -0.6037F, -2.9438F);
		HeadBase.addChild(Head);
		setRotationAngle(Head, 0.3491F, 0.0F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -10.4454F, -0.5914F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.4454F, -0.6914F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.4454F, -0.6914F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);
		Ears.setTextureOffset(107, 29).addBox(3.25F, -8.4789F, 2.3072F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ears.setTextureOffset(106, 29).addBox(-4.25F, -8.4789F, 2.3072F, 1.0F, 2.0F, 2.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Neck);
		Neck.setTextureOffset(72, 0).addBox(-1.75F, -2.7401F, 2.2054F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);


		HelmetPart1 = new ModelRenderer(this);
		HelmetPart1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(HelmetPart1);
		HelmetPart1.setTextureOffset(38, 4).addBox(-4.0F, -11.4454F, -0.5914F, 8.0F, 1.0F, 8.0F, 0.0F, false);

		HelmetPart6 = new ModelRenderer(this);
		HelmetPart6.setRotationPoint(-0.25F, -10.75F, 8.25F);
		Helmet.addChild(HelmetPart6);
		setRotationAngle(HelmetPart6, -1.0472F, 0.0F, 0.0F);
		HelmetPart6.setTextureOffset(38, 4).addBox(-4.75F, 0.381F, -4.023F, 10.0F, 1.0F, 5.0F, 0.0F, false);
		HelmetPart6.setTextureOffset(38, 4).addBox(-4.75F, 0.381F, -4.523F, 10.0F, 1.0F, 0.0F, 0.0F, false);

		HelmetPart7 = new ModelRenderer(this);
		HelmetPart7.setRotationPoint(-0.25F, -8.6451F, 11.6514F);
		Helmet.addChild(HelmetPart7);
		setRotationAngle(HelmetPart7, -1.5795F, 0.0F, 0.0F);
		HelmetPart7.setTextureOffset(38, 4).addBox(-3.75F, 3.252F, -1.0399F, 8.0F, 1.0F, 7.0F, 0.0F, false);
		HelmetPart7.setTextureOffset(38, 4).addBox(-4.75F, 12.252F, -1.7899F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart7.setTextureOffset(38, 4).addBox(-4.75F, 12.252F, -2.7899F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart7.setTextureOffset(38, 4).addBox(-3.475F, 12.252F, -2.3899F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		HelmetPart8 = new ModelRenderer(this);
		HelmetPart8.setRotationPoint(8.0F, -10.1653F, 10.8029F);
		Helmet.addChild(HelmetPart8);
		setRotationAngle(HelmetPart8, -1.5795F, 0.0F, 0.0F);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 3.4808F, -1.257F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 2.4808F, 1.743F, 1.0F, 4.0F, 3.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 2.4054F, 1.7451F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-12.75F, 2.4054F, 1.7495F, 1.0F, 4.0F, 3.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 2.4054F, 1.7451F, 1.0F, 4.0F, 3.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-12.75F, 6.4054F, 1.7495F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 6.4054F, 1.7451F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 3.4054F, -1.2505F, 1.0F, 8.0F, 3.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 10.9677F, 0.6291F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 10.9677F, 0.6291F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 3.4054F, -1.2505F, 1.0F, 8.0F, 3.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 4.4054F, -2.1255F, 1.0F, 5.0F, 3.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 4.4054F, -2.1255F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		HelmetPart8.setTextureOffset(38, 4).addBox(-13.0F, 4.4054F, -2.8755F, 1.0F, 3.0F, 1.0F, 0.0F, true);
		HelmetPart8.setTextureOffset(38, 4).addBox(-4.0F, 4.4054F, -2.8755F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		HelmetPart9 = new ModelRenderer(this);
		HelmetPart9.setRotationPoint(7.75F, -10.1653F, 11.3029F);
		Helmet.addChild(HelmetPart9);
		setRotationAngle(HelmetPart9, -1.5795F, 0.0F, 0.0F);
		HelmetPart9.setTextureOffset(38, 4).addBox(-3.75F, 2.9054F, 0.7495F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart9.setTextureOffset(38, 4).addBox(-12.75F, 2.9054F, 0.7495F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart9.setTextureOffset(38, 4).addBox(-3.75F, 2.9054F, 0.4995F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart9.setTextureOffset(38, 4).addBox(-12.75F, 2.9054F, 0.4995F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		HelmetPart2 = new ModelRenderer(this);
		HelmetPart2.setRotationPoint(0.0F, -12.2954F, 2.7836F);
		Helmet.addChild(HelmetPart2);
		setRotationAngle(HelmetPart2, 0.4276F, 0.0F, 0.0F);
		HelmetPart2.setTextureOffset(35, 2).addBox(-5.0F, -0.7272F, -3.4599F, 10.0F, 1.0F, 7.0F, 0.0F, false);

		HelmetPart10 = new ModelRenderer(this);
		HelmetPart10.setRotationPoint(0.0F, -12.2954F, 2.7836F);
		Helmet.addChild(HelmetPart10);
		setRotationAngle(HelmetPart10, 0.4276F, 0.0F, 0.0F);
		HelmetPart10.setTextureOffset(38, 4).addBox(-5.0F, 0.6826F, -5.121F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart10.setTextureOffset(38, 4).addBox(4.0F, 0.6826F, -5.121F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Spike = new ModelRenderer(this);
		Spike.setRotationPoint(0.0F, -11.35F, -0.15F);
		Helmet.addChild(Spike);
		setRotationAngle(Spike, -0.2356F, 0.0F, 0.0F);


		HelmetPart3 = new ModelRenderer(this);
		HelmetPart3.setRotationPoint(0.0F, -0.1994F, 3.2427F);
		Spike.addChild(HelmetPart3);
		setRotationAngle(HelmetPart3, 0.3491F, 0.0F, 0.0F);
		HelmetPart3.setTextureOffset(0, 21).addBox(-2.15F, -1.2444F, -4.3682F, 4.0F, 1.0F, 2.0F, 0.0F, false);
		HelmetPart3.setTextureOffset(0, 21).addBox(1.1F, -1.2444F, -4.3682F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		HelmetPart4 = new ModelRenderer(this);
		HelmetPart4.setRotationPoint(-1.8391F, 0.6352F, -0.3883F);
		Spike.addChild(HelmetPart4);
		setRotationAngle(HelmetPart4, 0.3491F, -0.4058F, -0.1265F);
		HelmetPart4.setTextureOffset(0, 21).addBox(-0.5655F, -0.8106F, -3.4912F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		HelmetPart4.setTextureOffset(0, 21).addBox(-1.5655F, -0.8106F, -5.4912F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		HelmetPart4.setTextureOffset(0, 21).addBox(-0.5655F, -0.8106F, -3.9912F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		HelmetPart4.setTextureOffset(0, 21).addBox(-0.5655F, -0.8106F, -4.2412F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		HelmetPart5 = new ModelRenderer(this);
		HelmetPart5.setRotationPoint(1.8391F, 0.6352F, -0.3883F);
		Spike.addChild(HelmetPart5);
		setRotationAngle(HelmetPart5, 0.3491F, 0.4058F, 0.1265F);
		HelmetPart5.setTextureOffset(0, 21).addBox(-1.4345F, -0.8106F, -3.4912F, 2.0F, 1.0F, 5.0F, 0.0F, true);
		HelmetPart5.setTextureOffset(0, 21).addBox(-0.2345F, -0.8106F, -5.4912F, 2.0F, 1.0F, 8.0F, 0.0F, true);
		HelmetPart5.setTextureOffset(0, 21).addBox(-0.2345F, -0.8106F, -3.4912F, 2.0F, 1.0F, 6.0F, 0.0F, true);
		HelmetPart5.setTextureOffset(0, 21).addBox(-1.4345F, -0.8106F, -3.9912F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		HelmetPart5.setTextureOffset(0, 21).addBox(-0.4345F, -0.8106F, -4.2412F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		TopSpike = new ModelRenderer(this);
		TopSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(TopSpike);


		TopSpikePart1 = new ModelRenderer(this);
		TopSpikePart1.setRotationPoint(0.0F, -12.2954F, 2.7836F);
		TopSpike.addChild(TopSpikePart1);
		setRotationAngle(TopSpikePart1, 0.4276F, 0.0F, 0.0F);
		TopSpikePart1.setTextureOffset(38, 4).addBox(-1.0F, -2.7272F, -0.4599F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		TopSpikePart1.setTextureOffset(38, 4).addBox(-1.0F, -3.7272F, -0.4599F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		TopSpikePart2 = new ModelRenderer(this);
		TopSpikePart2.setRotationPoint(0.0F, -17.9658F, -0.6734F);
		TopSpike.addChild(TopSpikePart2);
		setRotationAngle(TopSpikePart2, -0.2225F, 0.0F, 0.0F);
		TopSpikePart2.setTextureOffset(38, 4).addBox(-1.0F, 0.454F, 0.7903F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		TopSpikePart3 = new ModelRenderer(this);
		TopSpikePart3.setRotationPoint(0.0F, -17.6986F, -1.637F);
		TopSpike.addChild(TopSpikePart3);
		setRotationAngle(TopSpikePart3, 0.4014F, 0.0F, 0.0F);
		TopSpikePart3.setTextureOffset(38, 4).addBox(-1.0F, 0.9607F, -0.6324F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(76, 74).addBox(-0.5F, -4.4454F, -0.6914F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Mouth.setTextureOffset(76, 74).addBox(-1.75F, -4.4454F, -0.6914F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Mouth.setTextureOffset(76, 74).addBox(0.75F, -4.4454F, -0.6914F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		PurpleHaze.addChild(BodyBase);


		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, -20.0F, -1.0F);
		BodyBase.addChild(Body);
		setRotationAngle(Body, 0.4145F, 0.0F, 0.0F);


		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -5.5F, 0.0F);
		Body.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -5.8299F, 0.6579F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Cape = new ModelRenderer(this);
		Cape.setRotationPoint(0.0F, 6.325F, 0.0F);
		Torso.addChild(Cape);


		CapePart1 = new ModelRenderer(this);
		CapePart1.setRotationPoint(0.0F, -9.5002F, 6.7321F);
		Cape.addChild(CapePart1);
		setRotationAngle(CapePart1, 0.1353F, 0.0F, 0.0F);
		CapePart1.setTextureOffset(101, 69).addBox(-5.0F, -0.5F, 0.0F, 10.0F, 13.0F, 0.0F, 0.0F, false);
		CapePart1.setTextureOffset(85, 93).addBox(-0.75F, 1.3045F, 0.0197F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		CapePart1.setTextureOffset(98, 118).addBox(-0.75F, 5.3045F, 0.0197F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		CapePart2 = new ModelRenderer(this);
		CapePart2.setRotationPoint(0.0F, -0.4934F, 11.077F);
		Cape.addChild(CapePart2);
		setRotationAngle(CapePart2, -0.2094F, 0.0F, 0.0F);
		CapePart2.setTextureOffset(60, 110).addBox(-4.0F, 5.8577F, -1.8976F, 8.0F, 8.0F, 0.0F, 0.0F, false);
		CapePart2.setTextureOffset(35, 126).addBox(-5.0F, 3.8577F, -1.8976F, 10.0F, 2.0F, 0.0F, 0.0F, false);

		Spikes = new ModelRenderer(this);
		Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cape.addChild(Spikes);
		Spikes.setTextureOffset(42, 98).addBox(-0.75F, -11.7642F, 6.0541F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		ShoulderPads = new ModelRenderer(this);
		ShoulderPads.setRotationPoint(0.0F, 0.0F, 0.0F);
		Torso.addChild(ShoulderPads);
		ShoulderPads.setTextureOffset(97, 1).addBox(5.0F, -6.3299F, 0.1579F, 6.0F, 6.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(98, 1).addBox(-11.0F, -6.3299F, 0.1579F, 6.0F, 6.0F, 7.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, 17.0F, -0.5F);
		Body.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -26.9701F, -6.5891F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, 6.0F, -2.0F);
		Body.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.3228F, 2.7089F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 20.0F, 1.0F);
		Body.addChild(Arms);


		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(7.85F, -29.139F, 0.0452F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.8203F, 0.0F, -0.2662F);
		RightArm2.setTextureOffset(27, 32).addBox(-2.9451F, -1.4016F, 2.1285F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.5286F, -1.3796F, 0.4952F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		ElbowPad1 = new ModelRenderer(this);
		ElbowPad1.setRotationPoint(-6.8904F, 24.5192F, -4.7465F);
		RightHand2.addChild(ElbowPad1);
		ElbowPad1.setTextureOffset(25, 94).addBox(5.2423F, -26.4534F, 8.7982F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(15, 83).addBox(5.5173F, -25.4534F, 4.7982F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(15, 83).addBox(8.4923F, -25.4534F, 4.7982F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(15, 83).addBox(5.0673F, -25.4534F, 4.7982F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		Capsules1 = new ModelRenderer(this);
		Capsules1.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand2.addChild(Capsules1);
		Capsules1.setTextureOffset(17, 99).addBox(1.6714F, 3.6204F, 0.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Capsules1.setTextureOffset(17, 99).addBox(1.6714F, 3.6204F, 1.9952F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Capsules1.setTextureOffset(17, 99).addBox(1.6714F, 3.6204F, 3.2452F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		RightArm3 = new ModelRenderer(this);
		RightArm3.setRotationPoint(-7.85F, -29.139F, 0.0452F);
		Arms.addChild(RightArm3);
		setRotationAngle(RightArm3, -0.8203F, 0.0F, 0.2662F);
		RightArm3.setTextureOffset(27, 32).addBox(-1.0549F, -1.4016F, 2.1285F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		RightHand3 = new ModelRenderer(this);
		RightHand3.setRotationPoint(1.5624F, 5.6204F, 1.506F);
		RightArm3.addChild(RightHand3);
		setRotationAngle(RightHand3, -0.1134F, 0.0F, -0.3272F);
		RightHand3.setTextureOffset(0, 53).addBox(-2.4714F, -1.3796F, 0.4952F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		ElbowPad2 = new ModelRenderer(this);
		ElbowPad2.setRotationPoint(6.7975F, 24.4093F, -4.7231F);
		RightHand3.addChild(ElbowPad2);
		ElbowPad2.setTextureOffset(18, 109).addBox(-9.2423F, -26.4534F, 8.7982F, 4.0F, 3.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(0, 104).addBox(-9.5173F, -25.4534F, 4.7982F, 4.0F, 1.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(0, 104).addBox(-9.4923F, -25.4534F, 4.7982F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(0, 104).addBox(-6.0673F, -25.4534F, 4.7982F, 1.0F, 1.0F, 5.0F, 0.0F, true);

		Capsules = new ModelRenderer(this);
		Capsules.setRotationPoint(12.6724F, -0.0093F, 0.0219F);
		RightHand3.addChild(Capsules);
		Capsules.setTextureOffset(18, 98).addBox(-15.3286F, 3.6204F, 0.7452F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Capsules.setTextureOffset(18, 98).addBox(-15.3286F, 3.6204F, 1.9952F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Capsules.setTextureOffset(18, 98).addBox(-15.3286F, 3.6204F, 3.2452F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, -0.4269F, -2.9442F);
		BodyBase.addChild(Crotch);
		setRotationAngle(Crotch, -0.144F, 0.0F, 0.0F);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -20.5308F, -0.422F, 9.0F, 5.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(3.75F, -20.5308F, -0.422F, 1.0F, 5.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-4.75F, -20.5308F, -0.422F, 1.0F, 5.0F, 5.0F, 0.0F, true);

		Circle = new ModelRenderer(this);
		Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Circle);
		setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);
		Circle.setTextureOffset(0, 71).addBox(-0.75F, -17.2808F, -2.922F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, -0.2009F, -1.3855F);
		BodyBase.addChild(Legs);


		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.225F, -14.825F, -1.7375F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.3883F, 0.3621F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-3.3434F, -3.1938F, 1.6398F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(1.1742F, 14.9699F, -0.6431F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.2749F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-4.5176F, -8.2F, 4.6847F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad2 = new ModelRenderer(this);
		KneePad2.setRotationPoint(3.792F, -5.2375F, 0.6591F);
		RightFoot.addChild(KneePad2);
		setRotationAngle(KneePad2, 0.0F, 0.0F, -0.048F);
		KneePad2.setTextureOffset(64, 101).addBox(-8.25F, -3.0F, 3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad2.setTextureOffset(31, 82).addBox(-8.25F, -2.5F, 7.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad2.setTextureOffset(31, 82).addBox(-5.0F, -2.5F, 3.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad2.setTextureOffset(31, 82).addBox(-8.4423F, -2.5F, 3.5F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		KneePad2.setTextureOffset(71, 90).addBox(-7.25F, -4.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad2.setTextureOffset(71, 90).addBox(-7.25F, -1.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.9F, -13.4497F, -2.2679F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.2007F, -0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-1.3353F, -4.7091F, 2.1206F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.1265F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-1.6681F, -8.1563F, 2.1623F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad1 = new ModelRenderer(this);
		KneePad1.setRotationPoint(-0.2913F, -4.0533F, -1.8654F);
		LeftFoot.addChild(KneePad1);
		setRotationAngle(KneePad1, 0.0F, 0.0F, -0.048F);
		KneePad1.setTextureOffset(63, 101).addBox(-1.25F, -4.0F, 3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(50, 83).addBox(-1.25F, -3.5F, 7.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(50, 83).addBox(2.0F, -3.5F, 3.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad1.setTextureOffset(50, 83).addBox(-1.4423F, -3.5F, 3.5F, 1.0F, 1.0F, 5.0F, 0.0F, true);
		KneePad1.setTextureOffset(71, 90).addBox(-0.25F, -5.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(71, 90).addBox(-0.25F, -2.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		PurpleHaze.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}