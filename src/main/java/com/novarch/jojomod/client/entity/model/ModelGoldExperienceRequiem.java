package com.novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.GoldExperienceRequiemEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModelGoldExperienceRequiem <T extends GoldExperienceRequiemEntity> extends EntityModel<T> {
	public final ModelRenderer HeadBase;
	public final ModelRenderer Head;
	public final ModelRenderer Spikes;
	public final ModelRenderer SpikeMiddle;
	public final ModelRenderer SpikeMiddleDiagonals;
	public final ModelRenderer SpikeMiddleDiagonal1;
	public final ModelRenderer SpikeMiddleDiagonal2;
	public final ModelRenderer Top;
	public final ModelRenderer SpikeMiddle2;
	public final ModelRenderer SpikeMiddleDiagonals2;
	public final ModelRenderer SpikeMiddleDiagonal3;
	public final ModelRenderer SpikeMiddleDiagonal4;
	public final ModelRenderer Top2;
	public final ModelRenderer SpikeMiddle3;
	public final ModelRenderer SpikeMiddleDiagonals3;
	public final ModelRenderer SpikeMiddleDiagonal5;
	public final ModelRenderer SpikeMiddleDiagonal6;
	public final ModelRenderer Top3;
	public final ModelRenderer Spike1;
	public final ModelRenderer Spike2;
	public final ModelRenderer Arrow;
	public final ModelRenderer ArrowDiagonals;
	public final ModelRenderer ArrowDiagonal1;
	public final ModelRenderer ArrowDiagonal2;
	public final ModelRenderer Dots;
	public final ModelRenderer Dot1;
	public final ModelRenderer Dot3;
	public final ModelRenderer BodyBase;
	public final ModelRenderer Torso;
	public final ModelRenderer Ring;
	public final ModelRenderer RingGroup1;
	public final ModelRenderer Ring1;
	public final ModelRenderer Ring2;
	public final ModelRenderer Ring3;
	public final ModelRenderer Ring5;
	public final ModelRenderer Ring4;
	public final ModelRenderer RingGroup2;
	public final ModelRenderer Ring6;
	public final ModelRenderer Ring7;
	public final ModelRenderer Ring8;
	public final ModelRenderer Ring9;
	public final ModelRenderer Ring10;
	public final ModelRenderer RingTop;
	public final ModelRenderer Chest;
	public final ModelRenderer Heart;
	public final ModelRenderer Abs;
	public final ModelRenderer Arms;
	public final ModelRenderer RightArm;
	public final ModelRenderer RightHand;
	public final ModelRenderer Beetle1;
	public final ModelRenderer Beetle5;
	public final ModelRenderer LeftArm;
	public final ModelRenderer LeftHand;
	public final ModelRenderer Beetle2;
	public final ModelRenderer Beetle6;
	public final ModelRenderer Bone;
	public final ModelRenderer Crotch;
	public final ModelRenderer Circle;
	public final ModelRenderer Legs;
	public final ModelRenderer RightLeg;
	public final ModelRenderer Heart1;
	public final ModelRenderer RightFoot;
	public final ModelRenderer Beetle3;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer Heart2;
	public final ModelRenderer LeftFoot;
	public final ModelRenderer Beetle4;

	public ModelGoldExperienceRequiem() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, 24.0F, 0.0F);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(29, 0).addBox(-4.0F, -36.0F, -4.0F, 8.0F, 8.0F, 5.0F, 0.0F, false);
		Head.setTextureOffset(0, 14).addBox(-3.5F, -35.5F, 1.0F, 7.0F, 7.0F, 3.0F, 0.0F, false);

		Spikes = new ModelRenderer(this);
		Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Spikes);

		SpikeMiddle = new ModelRenderer(this);
		SpikeMiddle.setRotationPoint(0.0F, -38.15F, -2.9F);
		Spikes.addChild(SpikeMiddle);
		setRotationAngle(SpikeMiddle, -0.6807F, 0.0F, 0.0F);
		SpikeMiddle.setTextureOffset(18, 0).addBox(-0.485F, -3.1794F, 0.4357F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		SpikeMiddle.setTextureOffset(0, 4).addBox(-1.5F, -2.7298F, 0.4218F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		SpikeMiddle.setTextureOffset(57, 0).addBox(-1.0F, -1.5584F, 0.2726F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		SpikeMiddleDiagonals = new ModelRenderer(this);
		SpikeMiddleDiagonals.setRotationPoint(0.0F, 0.0F, 0.0F);
		SpikeMiddle.addChild(SpikeMiddleDiagonals);

		SpikeMiddleDiagonal1 = new ModelRenderer(this);
		SpikeMiddleDiagonal1.setRotationPoint(-0.4923F, -3.2275F, 0.9218F);
		SpikeMiddleDiagonals.addChild(SpikeMiddleDiagonal1);
		setRotationAngle(SpikeMiddleDiagonal1, 0.0F, 0.0F, 0.6109F);
		SpikeMiddleDiagonal1.setTextureOffset(0, 11).addBox(-0.54F, -1.0143F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		SpikeMiddleDiagonal2 = new ModelRenderer(this);
		SpikeMiddleDiagonal2.setRotationPoint(0.5323F, -3.2418F, 0.9218F);
		SpikeMiddleDiagonals.addChild(SpikeMiddleDiagonal2);
		setRotationAngle(SpikeMiddleDiagonal2, 0.0F, 0.0F, -0.6109F);
		SpikeMiddleDiagonal2.setTextureOffset(0, 10).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Top = new ModelRenderer(this);
		Top.setRotationPoint(-0.0351F, -3.9108F, 0.8313F);
		SpikeMiddle.addChild(Top);
		setRotationAngle(Top, -0.0175F, 0.0F, 0.7854F);
		Top.setTextureOffset(11, 0).addBox(-0.4612F, -0.4945F, -0.4207F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		SpikeMiddle2 = new ModelRenderer(this);
		SpikeMiddle2.setRotationPoint(3.4F, -38.975F, 0.35F);
		Spikes.addChild(SpikeMiddle2);
		setRotationAngle(SpikeMiddle2, -1.3788F, -0.7854F, 1.1345F);
		SpikeMiddle2.setTextureOffset(18, 0).addBox(1.0848F, -1.9943F, 0.7981F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		SpikeMiddle2.setTextureOffset(0, 4).addBox(0.0698F, -1.5447F, 0.7842F, 3.0F, 5.0F, 1.0F, 0.0F, false);

		SpikeMiddleDiagonals2 = new ModelRenderer(this);
		SpikeMiddleDiagonals2.setRotationPoint(0.0F, 0.0F, 0.0F);
		SpikeMiddle2.addChild(SpikeMiddleDiagonals2);

		SpikeMiddleDiagonal3 = new ModelRenderer(this);
		SpikeMiddleDiagonal3.setRotationPoint(-0.4923F, -3.2275F, 0.9218F);
		SpikeMiddleDiagonals2.addChild(SpikeMiddleDiagonal3);
		setRotationAngle(SpikeMiddleDiagonal3, 0.0F, 0.0F, 0.6109F);
		SpikeMiddleDiagonal3.setTextureOffset(0, 11).addBox(1.4256F, -0.9439F, -0.1376F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		SpikeMiddleDiagonal4 = new ModelRenderer(this);
		SpikeMiddleDiagonal4.setRotationPoint(0.5323F, -3.2418F, 0.9218F);
		SpikeMiddleDiagonals2.addChild(SpikeMiddleDiagonal4);
		setRotationAngle(SpikeMiddleDiagonal4, 0.0F, 0.0F, -0.6109F);
		SpikeMiddleDiagonal4.setTextureOffset(0, 11).addBox(0.1061F, 0.8712F, -0.1376F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Top2 = new ModelRenderer(this);
		Top2.setRotationPoint(-0.0351F, -3.9108F, 0.8313F);
		SpikeMiddle2.addChild(Top2);
		setRotationAngle(Top2, -0.0175F, 0.0F, 0.7854F);
		Top2.setTextureOffset(11, 0).addBox(1.4868F, -0.7728F, -0.0631F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		SpikeMiddle3 = new ModelRenderer(this);
		SpikeMiddle3.setRotationPoint(-3.35F, -38.975F, 0.35F);
		Spikes.addChild(SpikeMiddle3);
		setRotationAngle(SpikeMiddle3, -1.3788F, 0.7854F, -1.1345F);
		SpikeMiddle3.setTextureOffset(18, 0).addBox(-2.0848F, -1.9943F, 0.7981F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		SpikeMiddle3.setTextureOffset(0, 4).addBox(-3.0847F, -1.5387F, 0.7368F, 3.0F, 5.0F, 1.0F, 0.0F, true);

		SpikeMiddleDiagonals3 = new ModelRenderer(this);
		SpikeMiddleDiagonals3.setRotationPoint(0.0F, 0.0F, 0.0F);
		SpikeMiddle3.addChild(SpikeMiddleDiagonals3);
		

		SpikeMiddleDiagonal5 = new ModelRenderer(this);
		SpikeMiddleDiagonal5.setRotationPoint(0.4923F, -3.2275F, 0.9218F);
		SpikeMiddleDiagonals3.addChild(SpikeMiddleDiagonal5);
		setRotationAngle(SpikeMiddleDiagonal5, 0.0F, 0.0F, -0.6109F);
		SpikeMiddleDiagonal5.setTextureOffset(0, 11).addBox(-2.4256F, -0.9439F, -0.1376F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		SpikeMiddleDiagonal6 = new ModelRenderer(this);
		SpikeMiddleDiagonal6.setRotationPoint(-0.5323F, -3.2418F, 0.9218F);
		SpikeMiddleDiagonals3.addChild(SpikeMiddleDiagonal6);
		setRotationAngle(SpikeMiddleDiagonal6, 0.0F, 0.0F, 0.6109F);
		SpikeMiddleDiagonal6.setTextureOffset(0, 0).addBox(-1.1061F, 0.8712F, -0.1376F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		SpikeMiddleDiagonal6.setTextureOffset(0, 11).addBox(-1.1061F, 0.8712F, -0.1376F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Top3 = new ModelRenderer(this);
		Top3.setRotationPoint(0.0351F, -3.9108F, 0.8313F);
		SpikeMiddle3.addChild(Top3);
		setRotationAngle(Top3, -0.0175F, 0.0F, -0.7854F);
		Top3.setTextureOffset(11, 0).addBox(-2.4868F, -0.7728F, -0.0631F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Spike1 = new ModelRenderer(this);
		Spike1.setRotationPoint(3.5F, -49.4788F, -13.6132F);
		Spikes.addChild(Spike1);
		setRotationAngle(Spike1, -0.6807F, 0.0F, 0.4363F);
		Spike1.setTextureOffset(5, 0).addBox(3.8329F, 0.0164F, 15.6212F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Spike2 = new ModelRenderer(this);
		Spike2.setRotationPoint(-3.5F, 49.4788F, -13.6132F);
		Spikes.addChild(Spike2);
		setRotationAngle(Spike2, -0.6807F, 0.0F, -0.4363F);
		Spike2.setTextureOffset(5, 0).addBox(36.9884F, -69.6827F, -40.82F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Arrow = new ModelRenderer(this);
		Arrow.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Arrow);
		Arrow.setTextureOffset(0, 0).addBox(-0.5F, -32.9F, -4.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Arrow.setTextureOffset(0, 0).addBox(-0.5F, -34.9F, -4.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Arrow.setTextureOffset(0, 0).addBox(-0.5F, -35.65F, -4.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		ArrowDiagonals = new ModelRenderer(this);
		ArrowDiagonals.setRotationPoint(0.0F, 0.0F, 0.0F);
		Arrow.addChild(ArrowDiagonals);

		ArrowDiagonal1 = new ModelRenderer(this);
		ArrowDiagonal1.setRotationPoint(0.8F, -34.4F, -3.9F);
		ArrowDiagonals.addChild(ArrowDiagonal1);
		setRotationAngle(ArrowDiagonal1, 0.0F, 0.0F, 0.9774F);
		ArrowDiagonal1.setTextureOffset(0, 0).addBox(-1.2041F, -0.4503F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ArrowDiagonal2 = new ModelRenderer(this);
		ArrowDiagonal2.setRotationPoint(-0.8F, -34.4F, -3.9F);
		ArrowDiagonals.addChild(ArrowDiagonal2);
		setRotationAngle(ArrowDiagonal2, 0.0F, 0.0F, -0.9774F);
		ArrowDiagonal2.setTextureOffset(0, 0).addBox(-0.7959F, -0.4503F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		Dots = new ModelRenderer(this);
		Dots.setRotationPoint(0.5F, -34.65F, -4.4F);
		Head.addChild(Dots);

		Dot1 = new ModelRenderer(this);
		Dot1.setRotationPoint(2.0F, 0.4F, 0.675F);
		Dots.addChild(Dot1);
		setRotationAngle(Dot1, 0.0F, 0.0F, -0.6109F);
		Dot1.setTextureOffset(57, 1).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Dot3 = new ModelRenderer(this);
		Dot3.setRotationPoint(-3.0F, 0.4F, 0.675F);
		Dots.addChild(Dot3);
		setRotationAngle(Dot3, 0.0F, 0.0F, 0.6109F);
		Dot3.setTextureOffset(57, 1).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(0, 27).addBox(-10.0F, -2.7419F, -2.0297F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Ring = new ModelRenderer(this);
		Ring.setRotationPoint(0.0F, 0.2419F, -0.9703F);
		Torso.addChild(Ring);
		setRotationAngle(Ring, -0.24F, 0.0F, 0.0742F);

		RingGroup1 = new ModelRenderer(this);
		RingGroup1.setRotationPoint(0.0413F, 0.5353F, 0.2059F);
		Ring.addChild(RingGroup1);
		setRotationAngle(RingGroup1, 0.0F, 0.0F, -0.0524F);

		Ring1 = new ModelRenderer(this);
		Ring1.setRotationPoint(5.875F, -3.8045F, -0.0746F);
		RingGroup1.addChild(Ring1);
		setRotationAngle(Ring1, -0.3491F, 0.0F, -0.2618F);
		Ring1.setTextureOffset(20, 41).addBox(-0.5491F, -1.1618F, -0.8985F, 1.0F, 3.0F, 5.0F, 0.0F, false);

		Ring2 = new ModelRenderer(this);
		Ring2.setRotationPoint(5.875F, -13.5074F, -2.4938F);
		RingGroup1.addChild(Ring2);
		setRotationAngle(Ring2, -0.3491F, 0.0F, -0.2618F);
		Ring2.setTextureOffset(20, 41).addBox(-3.0604F, 3.8179F, 4.5803F, 1.0F, 3.0F, 5.0F, 0.0F, false);

		Ring3 = new ModelRenderer(this);
		Ring3.setRotationPoint(4.9402F, -25.9212F, -6.2387F);
		RingGroup1.addChild(Ring3);
		setRotationAngle(Ring3, -0.3491F, -0.0262F, -0.6981F);
		Ring3.setTextureOffset(20, 41).addBox(-12.1286F, 7.6652F, 11.3703F, 1.0F, 3.0F, 5.0F, 0.0F, false);

		Ring5 = new ModelRenderer(this);
		Ring5.setRotationPoint(4.3649F, -33.3686F, -9.103F);
		RingGroup1.addChild(Ring5);
		setRotationAngle(Ring5, -0.3491F, 0.0F, -0.8727F);
		Ring5.setTextureOffset(20, 41).addBox(-19.3985F, 5.6949F, 14.8447F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		Ring4 = new ModelRenderer(this);
		Ring4.setRotationPoint(4.6526F, -29.6449F, -7.6708F);
		RingGroup1.addChild(Ring4);
		setRotationAngle(Ring4, -0.3491F, -0.0436F, -0.8727F);
		Ring4.setTextureOffset(20, 41).addBox(-16.341F, 4.4921F, 13.2406F, 1.0F, 3.0F, 5.0F, 0.0F, false);

		RingGroup2 = new ModelRenderer(this);
		RingGroup2.setRotationPoint(-0.7946F, 0.7053F, 0.6298F);
		Ring.addChild(RingGroup2);
		setRotationAngle(RingGroup2, 0.0175F, 0.0F, 0.0524F);

		Ring6 = new ModelRenderer(this);
		Ring6.setRotationPoint(-5.875F, -3.8045F, -0.0746F);
		RingGroup2.addChild(Ring6);
		setRotationAngle(Ring6, -0.3491F, 0.0F, 0.2618F);
		Ring6.setTextureOffset(20, 41).addBox(-0.4352F, -1.174F, -0.9064F, 1.0F, 3.0F, 5.0F, 0.0F, true);

		Ring7 = new ModelRenderer(this);
		Ring7.setRotationPoint(-5.875F, -13.5074F, -2.4938F);
		RingGroup2.addChild(Ring7);
		setRotationAngle(Ring7, -0.3491F, 0.0F, 0.2618F);
		Ring7.setTextureOffset(20, 41).addBox(2.0761F, 3.8057F, 4.5725F, 1.0F, 3.0F, 5.0F, 0.0F, true);

		Ring8 = new ModelRenderer(this);
		Ring8.setRotationPoint(-4.9402F, -25.9212F, -6.2387F);
		RingGroup2.addChild(Ring8);
		setRotationAngle(Ring8, -0.3491F, 0.0262F, 0.6981F);
		Ring8.setTextureOffset(20, 41).addBox(11.1369F, 7.648F, 11.3609F, 1.0F, 3.0F, 5.0F, 0.0F, true);

		Ring9 = new ModelRenderer(this);
		Ring9.setRotationPoint(-4.3649F, -33.3686F, -9.103F);
		RingGroup2.addChild(Ring9);
		setRotationAngle(Ring9, -0.3491F, 0.0F, 0.8727F);
		Ring9.setTextureOffset(20, 41).addBox(18.4033F, 5.6767F, 14.8346F, 1.0F, 1.0F, 5.0F, 0.0F, true);

		Ring10 = new ModelRenderer(this);
		Ring10.setRotationPoint(-4.6526F, -29.6449F, -7.6708F);
		RingGroup2.addChild(Ring10);
		setRotationAngle(Ring10, -0.3491F, 0.0436F, 0.8727F);
		Ring10.setTextureOffset(20, 41).addBox(15.3459F, 4.4738F, 13.2307F, 1.0F, 3.0F, 5.0F, 0.0F, true);

		RingTop = new ModelRenderer(this);
		RingTop.setRotationPoint(-0.3542F, -11.1894F, 5.24F);
		Ring.addChild(RingTop);
		setRotationAngle(RingTop, -0.1047F, 0.0F, 0.0F);
		RingTop.setTextureOffset(20, 43).addBox(-0.9906F, -0.262F, -2.3679F, 2.0F, 1.0F, 5.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(22, 15).addBox(-5.5F, -23.6807F, -9.026F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Heart = new ModelRenderer(this);
		Heart.setRotationPoint(0.0F, -0.2754F, 1.1466F);
		Chest.addChild(Heart);
		Heart.setTextureOffset(106, 18).addBox(-0.475F, -21.6816F, -10.8604F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Heart.setTextureOffset(106, 18).addBox(-1.475F, -20.6816F, -10.8604F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Heart.setTextureOffset(106, 18).addBox(-2.475F, -19.6816F, -10.8604F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Heart.setTextureOffset(106, 18).addBox(-2.475F, -18.6816F, -10.8604F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Heart.setTextureOffset(106, 18).addBox(0.525F, -18.6816F, -10.8604F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(56, 14).addBox(-6.5F, -9.0059F, -0.6662F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-8.5F, -20.8015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(55, 28).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 41).addBox(-2.3268F, 2.963F, -1.6753F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		Beetle1 = new ModelRenderer(this);
		Beetle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle1);
		Beetle1.setTextureOffset(40, 48).addBox(1.0F, 3.375F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		Beetle5 = new ModelRenderer(this);
		Beetle5.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle5);
		Beetle5.setTextureOffset(49, 48).addBox(-2.85F, 4.15F, -0.775F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(8.5F, -20.9765F, -5.3061F);
		Arms.addChild(LeftArm);
		setRotationAngle(LeftArm, -1.309F, -0.2269F, -0.2618F);
		LeftArm.setTextureOffset(55, 28).addBox(-1.3856F, -3.2841F, -4.2651F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		LeftHand = new ModelRenderer(this);
		LeftHand.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftArm.addChild(LeftHand);
		setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.1134F);
		LeftHand.setTextureOffset(0, 41).addBox(-1.0949F, 2.8699F, -3.9657F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Beetle2 = new ModelRenderer(this);
		Beetle2.setRotationPoint(-17.0F, 0.175F, 4.0F);
		LeftHand.addChild(Beetle2);
		Beetle2.setTextureOffset(40, 48).addBox(15.65F, 3.125F, -7.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

		Beetle6 = new ModelRenderer(this);
		Beetle6.setRotationPoint(17.0F, 0.0F, 0.0F);
		Beetle2.addChild(Beetle6);
		Beetle6.setTextureOffset(49, 48).addBox(2.25F, 4.0F, -6.95F, 1.0F, 3.0F, 2.0F, 0.0F, true);

		Bone = new ModelRenderer(this);
		Bone.setRotationPoint(-10.2653F, 21.7578F, 11.9427F);
		LeftArm.addChild(Bone);
		setRotationAngle(Bone, 0.0F, 0.0349F, -0.0873F);
		Bone.setTextureOffset(63, 6).addBox(11.4302F, -27.8241F, -13.6764F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(80, 0).addBox(-4.5F, -17.0F, -4.675F, 9.0F, 4.0F, 6.0F, 0.0F, false);

		Circle = new ModelRenderer(this);
		Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Circle);
		setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);
		Circle.setTextureOffset(95, 6).addBox(-1.0F, -14.35F, -6.7F, 2.0F, 3.0F, 1.0F, 0.0F, false);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.6F, -5.525F, -0.9875F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.2138F, 0.0F, 0.0436F);
		RightLeg.setTextureOffset(81, 14).addBox(-3.2F, -9.5378F, -3.8147F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		Heart1 = new ModelRenderer(this);
		Heart1.setRotationPoint(0.0012F, -0.0265F, -0.1222F);
		RightLeg.addChild(Heart1);
		Heart1.setTextureOffset(97, 6).addBox(-1.675F, -0.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Heart1.setTextureOffset(97, 6).addBox(-2.675F, -1.75F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Heart1.setTextureOffset(97, 6).addBox(-2.675F, -2.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Heart1.setTextureOffset(97, 6).addBox(-0.675F, -2.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(-1.2F, 3.75F, -0.8375F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.384F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(81, 35).addBox(-2.0F, -5.0909F, -1.1542F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		Beetle3 = new ModelRenderer(this);
		Beetle3.setRotationPoint(-3.6141F, -3.7566F, 0.8417F);
		RightFoot.addChild(Beetle3);
		Beetle3.setTextureOffset(40, 48).addBox(1.325F, 0.125F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.575F, -5.25F, -2.0875F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.2094F, 0.0F, -0.1745F);
		LeftLeg.setTextureOffset(81, 14).addBox(-0.825F, -9.75F, -2.8375F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		Heart2 = new ModelRenderer(this);
		Heart2.setRotationPoint(0.0008F, -0.4998F, 0.8555F);
		LeftLeg.addChild(Heart2);
		Heart2.setTextureOffset(97, 6).addBox(0.675F, -0.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Heart2.setTextureOffset(97, 6).addBox(-0.325F, -1.75F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, true);
		Heart2.setTextureOffset(97, 6).addBox(1.675F, -2.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Heart2.setTextureOffset(97, 6).addBox(-0.325F, -2.75F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(1.175F, 3.75F, -0.8375F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.6981F, 0.0F, 0.0F);
		LeftFoot.setTextureOffset(81, 35).addBox(-2.0F, -4.7328F, 1.3605F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		Beetle4 = new ModelRenderer(this);
		Beetle4.setRotationPoint(3.7917F, -3.3952F, 3.4833F);
		LeftFoot.addChild(Beetle4);
		Beetle4.setTextureOffset(40, 48).addBox(-2.6F, 0.125F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		//previously the render function, render code was moved to a method below
	}

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