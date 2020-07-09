package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entities.stands.CrazyDiamondEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CrazyDiamondModel<T extends CrazyDiamondEntity> extends EntityModel<T>
{
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Chin;
	private final ModelRenderer ChinPart1;
	private final ModelRenderer Helmet;
	private final ModelRenderer Pipes;
	private final ModelRenderer Pipes1;
	private final ModelRenderer Pipe1;
	private final ModelRenderer Pipe2;
	private final ModelRenderer Pipe3;
	private final ModelRenderer Pipe4;
	private final ModelRenderer Pipe5;
	private final ModelRenderer Pipe6;
	private final ModelRenderer Pipes2;
	private final ModelRenderer Pipe7;
	private final ModelRenderer Pipe8;
	private final ModelRenderer Pipe9;
	private final ModelRenderer Pipe10;
	private final ModelRenderer Pipe11;
	private final ModelRenderer Pipe12;
	private final ModelRenderer Pipes3;
	private final ModelRenderer Pipe13;
	private final ModelRenderer Pipe14;
	private final ModelRenderer Pipe15;
	private final ModelRenderer Pipe16;
	private final ModelRenderer Pipe17;
	private final ModelRenderer Pipe18;
	private final ModelRenderer Pipes4;
	private final ModelRenderer Pipe19;
	private final ModelRenderer Pipe20;
	private final ModelRenderer Pipe21;
	private final ModelRenderer Pipe22;
	private final ModelRenderer Pipe23;
	private final ModelRenderer Pipe24;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer Heart1;
	private final ModelRenderer HeartPiece1;
	private final ModelRenderer HeartPiece2;
	private final ModelRenderer HeartPiece3;
	private final ModelRenderer HeartPiece4;
	private final ModelRenderer Spikes;
	private final ModelRenderer Spike1;
	private final ModelRenderer Spike2;
	private final ModelRenderer Heart2;
	private final ModelRenderer HeartPiece5;
	private final ModelRenderer HeartPiece6;
	private final ModelRenderer HeartPiece7;
	private final ModelRenderer HeartPiece8;
	private final ModelRenderer Spikes2;
	private final ModelRenderer Spike3;
	private final ModelRenderer Spike4;
	private final ModelRenderer Chest;
	private final ModelRenderer Abs;
	private final ModelRenderer AbHeart;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightHand;
	private final ModelRenderer Beetle1;
	private final ModelRenderer Beetle5;
	private final ModelRenderer ElbowPad2;
	private final ModelRenderer RightArm2;
	private final ModelRenderer RightHand2;
	private final ModelRenderer ElbowPad1;
	private final ModelRenderer Crotch;
	private final ModelRenderer Heart;
	private final ModelRenderer HeartPiece9;
	private final ModelRenderer HeartPiece10;
	private final ModelRenderer HeartPiece11;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightFoot;
	private final ModelRenderer KneePad2;
	private final ModelRenderer KneePad4;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer KneePad1;
	private final ModelRenderer KneePad3;

	public CrazyDiamondModel()
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
		Eyes.setRotationPoint(0.0F, 0.0F, 0.2F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(72, 0).addBox(-3.0F, -7.2F, -4.525F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(72, 0).addBox(1.0F, -7.2F, -4.525F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(46, 0).addBox(-3.5F, -7.7F, -4.45F, 7.0F, 2.0F, 1.0F, 0.0F, false);

		Chin = new ModelRenderer(this);
		Chin.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Chin);
		

		ChinPart1 = new ModelRenderer(this);
		ChinPart1.setRotationPoint(-0.5F, -1.5F, -4.5F);
		Chin.addChild(ChinPart1);
		setRotationAngle(ChinPart1, 0.0F, 0.0F, -0.7854F);
		ChinPart1.setTextureOffset(0, 0).addBox(0.596F, -0.8889F, 0.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(92, 0).addBox(3.5F, -11.375F, -5.25F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		Helmet.setTextureOffset(83, 1).addBox(3.5F, -11.475F, -5.25F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		Helmet.setTextureOffset(92, 0).addBox(-4.5F, -11.475F, -5.25F, 1.0F, 5.0F, 10.0F, 0.0F, true);
		Helmet.setTextureOffset(93, 2).addBox(-4.5F, -11.475F, 4.0F, 1.0F, 5.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(96, 9).addBox(3.5F, -11.375F, 4.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(94, 5).addBox(3.5F, -6.375F, -5.25F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		Helmet.setTextureOffset(94, 5).addBox(-4.5F, -7.375F, -5.25F, 1.0F, 3.0F, 6.0F, 0.0F, true);
		Helmet.setTextureOffset(94, 5).addBox(3.5F, -4.375F, -5.25F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		Helmet.setTextureOffset(94, 5).addBox(-4.5F, -4.375F, -5.25F, 1.0F, 2.0F, 3.0F, 0.0F, true);
		Helmet.setTextureOffset(83, 1).addBox(-4.0F, -11.475F, -5.25F, 8.0F, 2.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(99, 7).addBox(1.0F, -4.225F, -5.25F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(99, 7).addBox(-4.0F, -4.225F, -5.25F, 3.0F, 2.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(99, 7).addBox(2.0F, -5.725F, -5.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(99, 7).addBox(-4.0F, -5.725F, -5.25F, 2.0F, 2.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(94, 6).addBox(-4.0F, -9.725F, -5.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(92, 5).addBox(-4.0F, -11.475F, 3.0F, 8.0F, 5.0F, 2.0F, 0.0F, false);

		Pipes = new ModelRenderer(this);
		Pipes.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Pipes);
		

		Pipes1 = new ModelRenderer(this);
		Pipes1.setRotationPoint(1.35F, 0.0F, 0.0F);
		Pipes.addChild(Pipes1);
		setRotationAngle(Pipes1, 0.0F, 0.0654F, 0.0F);
		

		Pipe1 = new ModelRenderer(this);
		Pipe1.setRotationPoint(-0.5F, 1.0802F, 3.3606F);
		Pipes1.addChild(Pipe1);
		setRotationAngle(Pipe1, 0.1309F, 0.0F, 0.0F);
		Pipe1.setTextureOffset(32, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe2 = new ModelRenderer(this);
		Pipe2.setRotationPoint(-0.5F, 0.2637F, 4.8801F);
		Pipes1.addChild(Pipe2);
		setRotationAngle(Pipe2, 0.4931F, 0.0F, 0.0F);
		Pipe2.setTextureOffset(32, 0).addBox(-0.5F, -0.1781F, -0.967F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe3 = new ModelRenderer(this);
		Pipe3.setRotationPoint(-0.5F, -0.8841F, 7.0163F);
		Pipes1.addChild(Pipe3);
		setRotationAngle(Pipe3, 0.9774F, 0.0F, 0.0F);
		Pipe3.setTextureOffset(32, 0).addBox(-0.5F, -0.9208F, -1.6146F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe4 = new ModelRenderer(this);
		Pipe4.setRotationPoint(-0.5F, -3.7857F, 8.9734F);
		Pipes1.addChild(Pipe4);
		setRotationAngle(Pipe4, 1.5228F, 0.0F, 0.0F);
		Pipe4.setTextureOffset(32, 0).addBox(-0.5F, -2.5481F, -2.7038F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe5 = new ModelRenderer(this);
		Pipe5.setRotationPoint(-0.5F, -5.7834F, 9.0694F);
		Pipes1.addChild(Pipe5);
		setRotationAngle(Pipe5, 2.282F, 0.0F, 0.0F);
		Pipe5.setTextureOffset(32, 0).addBox(-0.5F, -3.9841F, -0.8956F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe6 = new ModelRenderer(this);
		Pipe6.setRotationPoint(-0.5F, -5.7834F, 9.0694F);
		Pipes1.addChild(Pipe6);
		setRotationAngle(Pipe6, 2.906F, 0.0F, 0.0305F);
		Pipe6.setTextureOffset(32, 0).addBox(-0.4663F, -2.7612F, 2.6434F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipes2 = new ModelRenderer(this);
		Pipes2.setRotationPoint(-1.35F, 0.0F, -0.475F);
		Pipes.addChild(Pipes2);
		setRotationAngle(Pipes2, 0.0F, -0.0654F, 0.0F);
		

		Pipe7 = new ModelRenderer(this);
		Pipe7.setRotationPoint(0.5F, 1.0802F, 3.3606F);
		Pipes2.addChild(Pipe7);
		setRotationAngle(Pipe7, 0.1309F, 0.0F, 0.0F);
		Pipe7.setTextureOffset(32, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe8 = new ModelRenderer(this);
		Pipe8.setRotationPoint(0.5F, 0.2637F, 4.8801F);
		Pipes2.addChild(Pipe8);
		setRotationAngle(Pipe8, 0.4931F, 0.0F, 0.0F);
		Pipe8.setTextureOffset(32, 0).addBox(-0.5F, -0.1781F, -0.967F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe9 = new ModelRenderer(this);
		Pipe9.setRotationPoint(0.5F, -0.8841F, 7.0163F);
		Pipes2.addChild(Pipe9);
		setRotationAngle(Pipe9, 1.0385F, 0.0F, 0.0F);
		Pipe9.setTextureOffset(32, 0).addBox(-0.5F, -1.0195F, -1.6164F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe10 = new ModelRenderer(this);
		Pipe10.setRotationPoint(0.5F, -3.7857F, 8.9734F);
		Pipes2.addChild(Pipe10);
		setRotationAngle(Pipe10, 1.3788F, 0.0F, 0.0F);
		Pipe10.setTextureOffset(32, 0).addBox(-0.5F, -2.2579F, -2.8537F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe11 = new ModelRenderer(this);
		Pipe11.setRotationPoint(0.5F, -5.7834F, 9.0694F);
		Pipes2.addChild(Pipe11);
		setRotationAngle(Pipe11, 2.282F, 0.0F, 0.0F);
		Pipe11.setTextureOffset(32, 0).addBox(-0.5F, -3.8259F, -0.9914F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe12 = new ModelRenderer(this);
		Pipe12.setRotationPoint(0.5F, -5.7834F, 9.0694F);
		Pipes2.addChild(Pipe12);
		setRotationAngle(Pipe12, 2.7794F, 0.0F, -0.0305F);
		Pipe12.setTextureOffset(32, 0).addBox(-0.5328F, -2.9874F, 2.2404F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipes3 = new ModelRenderer(this);
		Pipes3.setRotationPoint(-1.6F, 1.075F, -0.475F);
		Pipes.addChild(Pipes3);
		setRotationAngle(Pipes3, 0.0F, -0.3185F, -0.1789F);
		

		Pipe13 = new ModelRenderer(this);
		Pipe13.setRotationPoint(0.5F, 1.0802F, 3.3606F);
		Pipes3.addChild(Pipe13);
		setRotationAngle(Pipe13, 0.1309F, 0.0F, 0.0F);
		Pipe13.setTextureOffset(32, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe14 = new ModelRenderer(this);
		Pipe14.setRotationPoint(0.5F, 0.2637F, 4.8801F);
		Pipes3.addChild(Pipe14);
		setRotationAngle(Pipe14, 0.4931F, 0.0F, 0.0F);
		Pipe14.setTextureOffset(32, 0).addBox(-0.5F, -0.1781F, -0.967F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe15 = new ModelRenderer(this);
		Pipe15.setRotationPoint(0.5F, -0.8841F, 7.0163F);
		Pipes3.addChild(Pipe15);
		setRotationAngle(Pipe15, 1.0385F, 0.0F, 0.0F);
		Pipe15.setTextureOffset(32, 0).addBox(-0.5F, -1.0195F, -1.6164F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe16 = new ModelRenderer(this);
		Pipe16.setRotationPoint(0.5F, -3.7857F, 8.9734F);
		Pipes3.addChild(Pipe16);
		setRotationAngle(Pipe16, 1.3788F, 0.0F, 0.0F);
		Pipe16.setTextureOffset(32, 0).addBox(-0.5F, -2.2579F, -2.8537F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe17 = new ModelRenderer(this);
		Pipe17.setRotationPoint(0.5F, -5.7834F, 9.0694F);
		Pipes3.addChild(Pipe17);
		setRotationAngle(Pipe17, 2.282F, 0.0F, 0.0F);
		Pipe17.setTextureOffset(32, 0).addBox(-0.5F, -3.8259F, -0.9914F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipe18 = new ModelRenderer(this);
		Pipe18.setRotationPoint(0.5F, -5.7834F, 9.0694F);
		Pipes3.addChild(Pipe18);
		setRotationAngle(Pipe18, 2.7794F, 0.0F, -0.0305F);
		Pipe18.setTextureOffset(32, 0).addBox(-0.5328F, -2.9874F, 2.2404F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		Pipes4 = new ModelRenderer(this);
		Pipes4.setRotationPoint(1.6F, 1.075F, -0.475F);
		Pipes.addChild(Pipes4);
		setRotationAngle(Pipes4, 0.0F, 0.3185F, 0.1789F);
		

		Pipe19 = new ModelRenderer(this);
		Pipe19.setRotationPoint(-0.5F, 1.0802F, 3.3606F);
		Pipes4.addChild(Pipe19);
		setRotationAngle(Pipe19, 0.1309F, 0.0F, 0.0F);
		Pipe19.setTextureOffset(32, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe20 = new ModelRenderer(this);
		Pipe20.setRotationPoint(-0.5F, 0.2637F, 4.8801F);
		Pipes4.addChild(Pipe20);
		setRotationAngle(Pipe20, 0.4931F, 0.0F, 0.0F);
		Pipe20.setTextureOffset(32, 0).addBox(-0.5F, -0.1781F, -0.967F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe21 = new ModelRenderer(this);
		Pipe21.setRotationPoint(-0.5F, -0.8841F, 7.0163F);
		Pipes4.addChild(Pipe21);
		setRotationAngle(Pipe21, 1.0385F, 0.0F, 0.0F);
		Pipe21.setTextureOffset(32, 0).addBox(-0.5F, -1.0195F, -1.6164F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe22 = new ModelRenderer(this);
		Pipe22.setRotationPoint(-0.5F, -3.7857F, 8.9734F);
		Pipes4.addChild(Pipe22);
		setRotationAngle(Pipe22, 1.3788F, 0.0F, 0.0F);
		Pipe22.setTextureOffset(32, 0).addBox(-0.5F, -2.2579F, -2.8537F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe23 = new ModelRenderer(this);
		Pipe23.setRotationPoint(-0.5F, -5.7834F, 9.0694F);
		Pipes4.addChild(Pipe23);
		setRotationAngle(Pipe23, 2.282F, 0.0F, 0.0F);
		Pipe23.setTextureOffset(32, 0).addBox(-0.5F, -3.8259F, -0.9914F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Pipe24 = new ModelRenderer(this);
		Pipe24.setRotationPoint(-0.5F, -5.7834F, 9.0694F);
		Pipes4.addChild(Pipe24);
		setRotationAngle(Pipe24, 2.7794F, 0.0F, 0.0305F);
		Pipe24.setTextureOffset(32, 0).addBox(-0.4672F, -2.9874F, 2.2404F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Heart1 = new ModelRenderer(this);
		Heart1.setRotationPoint(0.0F, 0.7636F, 1.0003F);
		Torso.addChild(Heart1);
		

		HeartPiece1 = new ModelRenderer(this);
		HeartPiece1.setRotationPoint(10.5F, -2.3766F, -2.0619F);
		Heart1.addChild(HeartPiece1);
		setRotationAngle(HeartPiece1, 0.7941F, 0.0F, 0.0F);
		HeartPiece1.setTextureOffset(0, 98).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		HeartPiece2 = new ModelRenderer(this);
		HeartPiece2.setRotationPoint(10.5F, -2.3766F, 4.0619F);
		Heart1.addChild(HeartPiece2);
		setRotationAngle(HeartPiece2, -0.7941F, 0.0F, 0.0F);
		HeartPiece2.setTextureOffset(0, 98).addBox(-0.5F, -0.1759F, -2.7839F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		HeartPiece3 = new ModelRenderer(this);
		HeartPiece3.setRotationPoint(10.5F, 1.2572F, -2.2465F);
		Heart1.addChild(HeartPiece3);
		setRotationAngle(HeartPiece3, 1.5577F, 0.0F, 2.9889F);
		HeartPiece3.setTextureOffset(0, 98).addBox(-1.2537F, -1.7958F, -5.7912F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		HeartPiece3.setTextureOffset(0, 98).addBox(-1.2855F, -1.7818F, -6.8098F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HeartPiece3.setTextureOffset(0, 98).addBox(-1.2855F, 2.2182F, -6.8098F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		HeartPiece4 = new ModelRenderer(this);
		HeartPiece4.setRotationPoint(10.5F, 2.2275F, -2.0046F);
		Heart1.addChild(HeartPiece4);
		setRotationAngle(HeartPiece4, 1.5577F, 0.0F, 2.9889F);
		HeartPiece4.setTextureOffset(0, 98).addBox(-1.2905F, -0.8224F, -6.0175F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HeartPiece4.setTextureOffset(0, 98).addBox(-1.1043F, -0.1335F, -4.963F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HeartPiece4.setTextureOffset(0, 98).addBox(-1.0452F, 0.9889F, -4.564F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Spikes = new ModelRenderer(this);
		Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Heart1.addChild(Spikes);
		

		Spike1 = new ModelRenderer(this);
		Spike1.setRotationPoint(10.7444F, -4.4338F, -2.5004F);
		Spikes.addChild(Spike1);
		setRotationAngle(Spike1, 0.0F, 0.2531F, -0.0785F);
		Spike1.setTextureOffset(10, 100).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Spike2 = new ModelRenderer(this);
		Spike2.setRotationPoint(11.0245F, -4.5609F, 1.4588F);
		Spikes.addChild(Spike2);
		setRotationAngle(Spike2, 0.0F, -0.2531F, -0.0785F);
		Spike2.setTextureOffset(10, 100).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Heart2 = new ModelRenderer(this);
		Heart2.setRotationPoint(0.0F, 0.4386F, 0.8753F);
		Torso.addChild(Heart2);
		

		HeartPiece5 = new ModelRenderer(this);
		HeartPiece5.setRotationPoint(-10.5F, -2.3766F, -2.0619F);
		Heart2.addChild(HeartPiece5);
		setRotationAngle(HeartPiece5, 0.7941F, 0.0F, 0.0F);
		HeartPiece5.setTextureOffset(0, 98).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		HeartPiece6 = new ModelRenderer(this);
		HeartPiece6.setRotationPoint(-10.5F, -2.3766F, 4.0619F);
		Heart2.addChild(HeartPiece6);
		setRotationAngle(HeartPiece6, -0.7941F, 0.0F, 0.0F);
		HeartPiece6.setTextureOffset(0, 98).addBox(-0.5F, -0.1759F, -2.7839F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		HeartPiece7 = new ModelRenderer(this);
		HeartPiece7.setRotationPoint(-10.5F, 1.2572F, -2.2465F);
		Heart2.addChild(HeartPiece7);
		setRotationAngle(HeartPiece7, 1.5577F, 0.0F, -2.9889F);
		HeartPiece7.setTextureOffset(0, 98).addBox(0.2537F, -1.7958F, -5.7912F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		HeartPiece7.setTextureOffset(0, 98).addBox(0.2855F, -1.7818F, -6.8098F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HeartPiece7.setTextureOffset(0, 98).addBox(0.2855F, 2.2182F, -6.8098F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		HeartPiece8 = new ModelRenderer(this);
		HeartPiece8.setRotationPoint(-10.5F, 2.2275F, -2.0046F);
		Heart2.addChild(HeartPiece8);
		setRotationAngle(HeartPiece8, 1.5577F, 0.0F, -2.9889F);
		HeartPiece8.setTextureOffset(0, 98).addBox(0.2905F, -0.8224F, -6.0175F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		HeartPiece8.setTextureOffset(0, 98).addBox(0.1043F, -0.1335F, -4.963F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		HeartPiece8.setTextureOffset(0, 98).addBox(0.0452F, 0.9889F, -4.564F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Spikes2 = new ModelRenderer(this);
		Spikes2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Heart2.addChild(Spikes2);
		

		Spike3 = new ModelRenderer(this);
		Spike3.setRotationPoint(-10.7444F, -4.4338F, -2.5004F);
		Spikes2.addChild(Spike3);
		setRotationAngle(Spike3, 0.0F, -0.2531F, 0.0785F);
		Spike3.setTextureOffset(10, 100).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Spike4 = new ModelRenderer(this);
		Spike4.setRotationPoint(-11.0245F, -4.5609F, 1.4588F);
		Spikes2.addChild(Spike4);
		setRotationAngle(Spike4, 0.0F, 0.2531F, 0.0785F);
		Spike4.setTextureOffset(10, 100).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);
		Chest.setTextureOffset(26, 46).addBox(-5.125F, -24.767F, -10.2241F, 4.0F, 3.0F, 5.0F, 0.0F, false);
		Chest.setTextureOffset(26, 46).addBox(1.125F, -24.767F, -10.2241F, 4.0F, 3.0F, 5.0F, 0.0F, true);
		Chest.setTextureOffset(26, 46).addBox(1.125F, -24.767F, -10.2241F, 4.0F, 3.0F, 5.0F, 0.0F, true);
		Chest.setTextureOffset(26, 46).addBox(0.875F, -24.767F, -10.2241F, 1.0F, 3.0F, 5.0F, 0.0F, true);
		Chest.setTextureOffset(26, 46).addBox(-5.125F, -21.767F, -10.2241F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		Chest.setTextureOffset(26, 46).addBox(2.125F, -21.767F, -10.2241F, 3.0F, 1.0F, 5.0F, 0.0F, true);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		AbHeart = new ModelRenderer(this);
		AbHeart.setRotationPoint(0.0F, 0.0998F, -0.0259F);
		Abs.addChild(AbHeart);
		AbHeart.setTextureOffset(8, 83).addBox(-3.5F, -7.5076F, -0.8434F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		AbHeart.setTextureOffset(8, 83).addBox(-4.5F, -8.5075F, -0.8347F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		AbHeart.setTextureOffset(8, 83).addBox(-5.5F, -9.5075F, -0.826F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		AbHeart.setTextureOffset(8, 83).addBox(-5.5F, -10.5075F, -0.8173F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		AbHeart.setTextureOffset(8, 83).addBox(-2.5F, -10.5075F, -0.8173F, 2.0F, 1.0F, 1.0F, 0.0F, false);

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

		Beetle1 = new ModelRenderer(this);
		Beetle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle1);
		

		Beetle5 = new ModelRenderer(this);
		Beetle5.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle5);
		

		ElbowPad2 = new ModelRenderer(this);
		ElbowPad2.setRotationPoint(11.3261F, 32.9765F, 0.2592F);
		RightArm.addChild(ElbowPad2);
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
		RightHand2.setTextureOffset(0, 53).addBox(-1.2606F, -0.1166F, -2.5599F, 4.0F, 6.0F, 4.0F, 0.0F, true);

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
		Crotch.setTextureOffset(64, 69).addBox(-4.5F, -19.2058F, -4.847F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(64, 69).addBox(4.0F, -19.2058F, -4.847F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Crotch.setTextureOffset(50, 83).addBox(4.5F, -18.7058F, -3.847F, 1.0F, 4.0F, 4.0F, 0.0F, false);
		Crotch.setTextureOffset(50, 85).addBox(-5.5F, -18.7058F, -3.847F, 1.0F, 4.0F, 4.0F, 0.0F, false);
		Crotch.setTextureOffset(64, 69).addBox(-5.0F, -19.2058F, -4.847F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		Crotch.setTextureOffset(64, 69).addBox(-4.5F, -19.2058F, 0.097F, 9.0F, 1.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(21, 98).addBox(-1.0F, -15.7058F, -4.647F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		Heart = new ModelRenderer(this);
		Heart.setRotationPoint(-0.25F, 0.625F, -0.275F);
		Crotch.addChild(Heart);
		

		HeartPiece9 = new ModelRenderer(this);
		HeartPiece9.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece9);
		setRotationAngle(HeartPiece9, 0.0F, 0.0F, 0.7854F);
		HeartPiece9.setTextureOffset(0, 84).addBox(0.1104F, -1.5657F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		HeartPiece10 = new ModelRenderer(this);
		HeartPiece10.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece10);
		setRotationAngle(HeartPiece10, 0.0F, 0.0F, -0.7854F);
		HeartPiece10.setTextureOffset(0, 84).addBox(-0.7272F, -1.1825F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		HeartPiece11 = new ModelRenderer(this);
		HeartPiece11.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece11);
		setRotationAngle(HeartPiece11, 0.0F, 0.0F, -1.5708F);
		HeartPiece11.setTextureOffset(0, 84).addBox(0.2978F, -1.3325F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(0.2978F, 1.8925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(-0.5772F, -0.2575F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(1.2978F, 1.8925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(1.2978F, 1.2925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(1.2978F, -0.7075F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(1.2978F, -1.3325F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.3F, -15.425F, -2.4125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.3011F, 0.2618F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad2 = new ModelRenderer(this);
		KneePad2.setRotationPoint(2.4935F, -3.9963F, 1.8032F);
		RightFoot.addChild(KneePad2);
		setRotationAngle(KneePad2, -0.0654F, 0.0F, 0.0F);
		KneePad2.setTextureOffset(62, 85).addBox(-4.5F, -3.35F, 0.15F, 4.0F, 2.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(62, 85).addBox(-1.15F, -3.35F, 0.175F, 1.0F, 2.0F, 5.0F, 0.0F, true);
		KneePad2.setTextureOffset(62, 85).addBox(-5.05F, -3.35F, 0.175F, 1.0F, 2.0F, 5.0F, 0.0F, true);
		KneePad2.setTextureOffset(62, 85).addBox(-5.15F, -3.35F, 4.175F, 5.0F, 2.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(62, 85).addBox(-3.5F, -4.35F, 0.15F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(62, 85).addBox(-3.5F, -1.35F, 0.15F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		KneePad4 = new ModelRenderer(this);
		KneePad4.setRotationPoint(2.5132F, 3.3819F, 1.1582F);
		RightFoot.addChild(KneePad4);
		setRotationAngle(KneePad4, -0.0654F, 0.0F, 0.0F);
		KneePad4.setTextureOffset(42, 98).addBox(-4.5F, -3.35F, 0.15F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad4.setTextureOffset(42, 98).addBox(-1.15F, -3.35F, 0.175F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad4.setTextureOffset(42, 98).addBox(-5.05F, -3.35F, 0.175F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad4.setTextureOffset(42, 98).addBox(-5.15F, -3.35F, 4.175F, 5.0F, 1.0F, 1.0F, 0.0F, false);

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
		KneePad1.setTextureOffset(64, 85).addBox(-0.5F, -4.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(2.85F, -4.0F, -3.475F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(-1.05F, -4.0F, -3.475F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(-1.15F, -4.0F, 0.525F, 5.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(0.5F, -5.0F, -3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(0.5F, -2.0F, -3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		KneePad3 = new ModelRenderer(this);
		KneePad3.setRotationPoint(-1.2482F, -1.9833F, 4.3397F);
		LeftFoot.addChild(KneePad3);
		setRotationAngle(KneePad3, -0.0654F, 0.0F, 0.0F);
		KneePad3.setTextureOffset(41, 98).addBox(-1.015F, 1.8072F, -4.9182F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad3.setTextureOffset(41, 98).addBox(2.335F, 1.8072F, -4.8932F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad3.setTextureOffset(41, 98).addBox(-1.565F, 1.8072F, -4.8932F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		KneePad3.setTextureOffset(41, 98).addBox(-1.665F, 1.8072F, -0.7682F, 5.0F, 1.0F, 1.0F, 0.0F, false);
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