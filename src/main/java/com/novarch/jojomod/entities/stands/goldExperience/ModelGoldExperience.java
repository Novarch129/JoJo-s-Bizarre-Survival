package com.novarch.jojomod.entities.stands.goldExperience;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelGoldExperience<T extends EntityGoldExperience> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer FacePaint;
	private final ModelRenderer Eyes;
	private final ModelRenderer Ears;
	private final ModelRenderer Helmet;
	private final ModelRenderer Lines;
	private final ModelRenderer Top;
	private final ModelRenderer Left;
	private final ModelRenderer Right;
	private final ModelRenderer Front;
	private final ModelRenderer Side;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer Wings;
	private final ModelRenderer LeftWing;
	private final ModelRenderer LeftWing2;
	private final ModelRenderer Chest;
	private final ModelRenderer Ladybugs;
	private final ModelRenderer ChestLines;
	private final ModelRenderer ChestLinesLeft;
	private final ModelRenderer ChestLinesLeft2;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightHand;
	private final ModelRenderer Beetle1;
	private final ModelRenderer Beetle5;
	private final ModelRenderer StandArrow2;
	private final ModelRenderer Right5;
	private final ModelRenderer Left6;
	private final ModelRenderer Left7;
	private final ModelRenderer RightArm2;
	private final ModelRenderer RightHand2;
	private final ModelRenderer Beetle2;
	private final ModelRenderer Beetle3;
	private final ModelRenderer Crotch;
	private final ModelRenderer Circle;
	private final ModelRenderer LadybugsCrotch;
	private final ModelRenderer Straps;
	private final ModelRenderer LeftStraps;
	private final ModelRenderer Strap1;
	private final ModelRenderer Strap2;
	private final ModelRenderer Strap3;
	private final ModelRenderer Strap4;
	private final ModelRenderer Strap5;
	private final ModelRenderer Strap6;
	private final ModelRenderer LeftStraps2;
	private final ModelRenderer Strap7;
	private final ModelRenderer Strap8;
	private final ModelRenderer Strap9;
	private final ModelRenderer Strap10;
	private final ModelRenderer Strap11;
	private final ModelRenderer Strap12;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer Heart1;
	private final ModelRenderer RightFoot;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer Heart2;
	private final ModelRenderer LeftFoot;

	public ModelGoldExperience() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.2F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
		Head.setTextureOffset(0, 22).addBox(-1.0F, -4.2F, -4.125F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-1.0F, -2.9F, -4.2F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		FacePaint = new ModelRenderer(this);
		FacePaint.setRotationPoint(4.0F, 0.0F, -4.0F);
		Head.addChild(FacePaint);
		FacePaint.setTextureOffset(25, 0).addBox(-2.0F, -5.2F, -0.175F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		FacePaint.setTextureOffset(25, 0).addBox(-7.0F, -5.2F, -0.175F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Eyes.setTextureOffset(0, 0).addBox(2.0F, -7.2F, -4.1F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);
		Ears.setTextureOffset(13, 17).addBox(-4.175F, -6.825F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		Ears.setTextureOffset(13, 17).addBox(3.175F, -6.825F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, true);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Helmet);
		Helmet.setTextureOffset(36, 0).addBox(-5.0F, -11.8F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		Helmet.setTextureOffset(27, 0).addBox(-2.0F, -11.1F, -5.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);

		Lines = new ModelRenderer(this);
		Lines.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(Lines);
		

		Top = new ModelRenderer(this);
		Top.setRotationPoint(0.0F, 0.0F, 0.0F);
		Lines.addChild(Top);
		Top.setTextureOffset(0, 17).addBox(-0.5F, -11.95F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		Top.setTextureOffset(0, 17).addBox(-0.5F, -11.85F, 4.075F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Left = new ModelRenderer(this);
		Left.setRotationPoint(0.0F, 0.0F, 0.0F);
		Top.addChild(Left);
		Left.setTextureOffset(0, 17).addBox(2.975F, -11.95F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Left.setTextureOffset(0, 17).addBox(2.975F, -11.95F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Left.setTextureOffset(0, 17).addBox(1.975F, -11.95F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Left.setTextureOffset(0, 17).addBox(0.975F, -11.95F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Left.setTextureOffset(0, 29).addBox(3.975F, -11.95F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		Right = new ModelRenderer(this);
		Right.setRotationPoint(0.0F, 0.0F, 0.0F);
		Top.addChild(Right);
		Right.setTextureOffset(0, 29).addBox(-4.975F, -11.95F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		Right.setTextureOffset(0, 17).addBox(-3.975F, -11.95F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Right.setTextureOffset(0, 17).addBox(-3.975F, -11.95F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Right.setTextureOffset(0, 17).addBox(-2.975F, -11.95F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Right.setTextureOffset(0, 17).addBox(-1.975F, -11.95F, 4.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Front = new ModelRenderer(this);
		Front.setRotationPoint(0.0F, 0.0F, 0.0F);
		Lines.addChild(Front);
		Front.setTextureOffset(9, 29).addBox(-1.975F, -11.775F, -5.1F, 4.0F, 1.0F, 1.0F, 0.0F, true);
		Front.setTextureOffset(9, 29).addBox(-1.975F, -11.775F, 4.1F, 4.0F, 1.0F, 1.0F, 0.0F, true);
		Front.setTextureOffset(9, 29).addBox(-5.075F, -10.825F, 4.1F, 5.0F, 1.0F, 1.0F, 0.0F, true);
		Front.setTextureOffset(9, 29).addBox(0.075F, -10.825F, 4.1F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		Front.setTextureOffset(9, 29).addBox(-1.975F, -8.775F, -5.1F, 4.0F, 1.0F, 1.0F, 0.0F, true);
		Front.setTextureOffset(10, 29).addBox(3.025F, -9.775F, -5.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Front.setTextureOffset(10, 29).addBox(-5.025F, -9.775F, -5.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Front.setTextureOffset(1, 30).addBox(-3.025F, -10.775F, -5.1F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Front.setTextureOffset(1, 30).addBox(2.025F, -10.775F, -5.1F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Side = new ModelRenderer(this);
		Side.setRotationPoint(0.0F, 0.0F, 0.0F);
		Lines.addChild(Side);
		Side.setTextureOffset(0, 29).addBox(4.125F, -10.825F, -1.975F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Side.setTextureOffset(0, 29).addBox(-5.125F, -10.825F, -1.975F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		Side.setTextureOffset(3, 32).addBox(-5.125F, -9.825F, -4.975F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		Side.setTextureOffset(3, 32).addBox(4.125F, -9.825F, -4.975F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Wings = new ModelRenderer(this);
		Wings.setRotationPoint(0.0F, 25.5F, 1.0F);
		Torso.addChild(Wings);
		setRotationAngle(Wings, -0.2182F, 0.0F, 0.0F);
		

		LeftWing = new ModelRenderer(this);
		LeftWing.setRotationPoint(10.1F, -28.0367F, -0.5098F);
		Wings.addChild(LeftWing);
		setRotationAngle(LeftWing, 0.0F, -1.5708F, 0.0F);
		LeftWing.setTextureOffset(66, 65).addBox(-7.683F, -1.2946F, -0.675F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		LeftWing.setTextureOffset(66, 73).addBox(-7.683F, -2.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftWing.setTextureOffset(65, 73).addBox(-6.958F, -2.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftWing.setTextureOffset(65, 73).addBox(-7.683F, -3.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		LeftWing.setTextureOffset(65, 73).addBox(-7.683F, -4.2946F, -0.675F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		LeftWing2 = new ModelRenderer(this);
		LeftWing2.setRotationPoint(-10.1F, -28.0367F, -0.5098F);
		Wings.addChild(LeftWing2);
		setRotationAngle(LeftWing2, 0.0F, 1.5708F, 0.0F);
		LeftWing2.setTextureOffset(66, 65).addBox(4.683F, -1.2946F, -0.675F, 3.0F, 3.0F, 1.0F, 0.0F, true);
		LeftWing2.setTextureOffset(66, 73).addBox(5.683F, -2.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		LeftWing2.setTextureOffset(65, 73).addBox(4.958F, -2.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		LeftWing2.setTextureOffset(65, 73).addBox(5.683F, -3.2946F, -0.675F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		LeftWing2.setTextureOffset(65, 73).addBox(6.683F, -4.2946F, -0.675F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Ladybugs = new ModelRenderer(this);
		Ladybugs.setRotationPoint(0.0F, 3.0F, 0.5F);
		Chest.addChild(Ladybugs);
		Ladybugs.setTextureOffset(24, 51).addBox(-4.75F, -25.425F, -10.65F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Ladybugs.setTextureOffset(24, 51).addBox(2.75F, -25.425F, -10.65F, 2.0F, 3.0F, 1.0F, 0.0F, true);

		ChestLines = new ModelRenderer(this);
		ChestLines.setRotationPoint(0.0F, 0.0F, 0.15F);
		Chest.addChild(ChestLines);
		

		ChestLinesLeft = new ModelRenderer(this);
		ChestLinesLeft.setRotationPoint(0.5F, 0.0F, 0.0F);
		ChestLines.addChild(ChestLinesLeft);
		ChestLinesLeft.setTextureOffset(33, 51).addBox(0.525F, -21.15F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ChestLinesLeft.setTextureOffset(33, 51).addBox(0.525F, -22.9F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		ChestLinesLeft.setTextureOffset(33, 51).addBox(2.525F, -24.425F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ChestLinesLeft2 = new ModelRenderer(this);
		ChestLinesLeft2.setRotationPoint(0.0F, 0.0F, 0.0F);
		ChestLines.addChild(ChestLinesLeft2);
		ChestLinesLeft2.setTextureOffset(33, 51).addBox(-3.025F, -21.15F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		ChestLinesLeft2.setTextureOffset(33, 51).addBox(-3.025F, -22.9F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		ChestLinesLeft2.setTextureOffset(33, 51).addBox(-5.025F, -24.425F, -9.95F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);
		Abs.setTextureOffset(32, 68).addBox(-7.5F, -5.2058F, -1.072F, 9.0F, 5.0F, 5.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);
		

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-8.5F, -20.8015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-2.5694F, -5.0526F, -2.55F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.6601F, 0.9349F, -2.46F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		Beetle1 = new ModelRenderer(this);
		Beetle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle1);
		

		Beetle5 = new ModelRenderer(this);
		Beetle5.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Beetle5);
		Beetle5.setTextureOffset(25, 44).addBox(-3.1834F, 2.1219F, -1.5596F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		StandArrow2 = new ModelRenderer(this);
		StandArrow2.setRotationPoint(-23.0938F, 2.9179F, 4.4602F);
		RightHand.addChild(StandArrow2);
		setRotationAngle(StandArrow2, 1.4835F, 0.0F, -1.5708F);
		StandArrow2.setTextureOffset(0, 79).addBox(-2.6356F, -6.1437F, -23.3087F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		StandArrow2.setTextureOffset(0, 97).addBox(-2.6356F, -7.1437F, -23.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		StandArrow2.setTextureOffset(52, 22).addBox(-2.6356F, -9.1437F, -23.3087F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		StandArrow2.setTextureOffset(52, 22).addBox(-3.1356F, -10.0437F, -23.3087F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		StandArrow2.showModel=false;

		Right5 = new ModelRenderer(this);
		Right5.setRotationPoint(-2.0F, -11.0F, 7.0F);
		StandArrow2.addChild(Right5);
		Right5.setTextureOffset(52, 22).addBox(0.8644F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-2.6856F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(1.4144F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(0.8644F, -0.9187F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(0.3144F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-1.5856F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-1.2606F, -0.8687F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-0.0106F, -0.8687F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-0.6356F, 0.8563F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-0.6106F, -2.5187F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-1.1356F, -1.6937F, -30.3087F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-2.9356F, 0.9063F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(1.6644F, 0.9063F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-2.1356F, -0.9187F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Right5.setTextureOffset(52, 22).addBox(-2.1356F, 0.0813F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Left6 = new ModelRenderer(this);
		Left6.setRotationPoint(0.0F, 0.0F, 0.0F);
		Right5.addChild(Left6);
		setRotationAngle(Left6, 0.0F, 0.0F, 0.4363F);
		Left6.setTextureOffset(52, 22).addBox(2.2204F, 0.6016F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Left7 = new ModelRenderer(this);
		Left7.setRotationPoint(-6.0F, 0.0F, 0.0F);
		Right5.addChild(Left7);
		setRotationAngle(Left7, 0.0F, 0.0F, -0.4363F);
		Left7.setTextureOffset(52, 22).addBox(1.9717F, 3.0228F, -30.3087F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(8.5F, -20.8015F, -1.3061F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2618F);
		RightArm2.setTextureOffset(27, 32).addBox(-1.4306F, -5.0526F, -2.55F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.1134F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.3399F, 0.9349F, -2.46F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Beetle2 = new ModelRenderer(this);
		Beetle2.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand2.addChild(Beetle2);
		

		Beetle3 = new ModelRenderer(this);
		Beetle3.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand2.addChild(Beetle3);
		Beetle3.setTextureOffset(25, 44).addBox(2.1834F, 2.1219F, -1.5596F, 1.0F, 3.0F, 2.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(0, 69).addBox(-4.5F, -19.2F, -4.675F, 9.0F, 1.0F, 6.0F, 0.0F, false);

		Circle = new ModelRenderer(this);
		Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Circle);
		setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);
		Circle.setTextureOffset(80, 17).addBox(-2.0F, -16.5416F, -6.6417F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		LadybugsCrotch = new ModelRenderer(this);
		LadybugsCrotch.setRotationPoint(0.0F, 0.1F, 0.0F);
		Crotch.addChild(LadybugsCrotch);
		LadybugsCrotch.setTextureOffset(19, 57).addBox(3.9334F, -18.6796F, -2.9907F, 1.0F, 4.0F, 3.0F, 0.0F, true);
		LadybugsCrotch.setTextureOffset(19, 57).addBox(-4.9334F, -18.6796F, -2.9907F, 1.0F, 4.0F, 3.0F, 0.0F, false);

		Straps = new ModelRenderer(this);
		Straps.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Straps);
		

		LeftStraps = new ModelRenderer(this);
		LeftStraps.setRotationPoint(0.0F, 0.0F, 0.0F);
		Straps.addChild(LeftStraps);
		

		Strap1 = new ModelRenderer(this);
		Strap1.setRotationPoint(5.0334F, -13.3046F, -1.2157F);
		LeftStraps.addChild(Strap1);
		setRotationAngle(Strap1, -0.5629F, 0.0F, -0.3491F);
		Strap1.setTextureOffset(81, 63).addBox(-0.5F, -0.816F, -2.3794F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Strap2 = new ModelRenderer(this);
		Strap2.setRotationPoint(7.0334F, -4.3046F, -5.2157F);
		LeftStraps.addChild(Strap2);
		setRotationAngle(Strap2, -1.9984F, 0.1571F, -0.3491F);
		Strap2.setTextureOffset(81, 63).addBox(0.5399F, 1.9355F, -9.1751F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Strap3 = new ModelRenderer(this);
		Strap3.setRotationPoint(4.0334F, -4.3046F, -17.2157F);
		LeftStraps.addChild(Strap3);
		setRotationAngle(Strap3, -2.4042F, 0.1571F, -0.6065F);
		Strap3.setTextureOffset(81, 63).addBox(3.3298F, -3.3912F, -14.8971F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Strap4 = new ModelRenderer(this);
		Strap4.setRotationPoint(3.9489F, -2.8571F, -22.8635F);
		LeftStraps.addChild(Strap4);
		setRotationAngle(Strap4, 2.7445F, 0.1571F, -0.6065F);
		Strap4.setTextureOffset(81, 63).addBox(3.3298F, 15.2708F, -12.966F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Strap5 = new ModelRenderer(this);
		Strap5.setRotationPoint(3.9489F, -2.8571F, -22.8635F);
		LeftStraps.addChild(Strap5);
		setRotationAngle(Strap5, 2.7445F, 0.1571F, -0.5018F);
		Strap5.setTextureOffset(81, 63).addBox(2.1304F, 17.7245F, -12.5698F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Strap6 = new ModelRenderer(this);
		Strap6.setRotationPoint(3.324F, -4.1973F, -22.2524F);
		LeftStraps.addChild(Strap6);
		setRotationAngle(Strap6, 2.7445F, 0.1571F, -0.3491F);
		Strap6.setTextureOffset(81, 63).addBox(0.2853F, 18.3602F, -12.0616F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		LeftStraps2 = new ModelRenderer(this);
		LeftStraps2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Straps.addChild(LeftStraps2);
		

		Strap7 = new ModelRenderer(this);
		Strap7.setRotationPoint(-5.0334F, -13.3046F, -1.2157F);
		LeftStraps2.addChild(Strap7);
		setRotationAngle(Strap7, -0.5629F, 0.0F, 0.3491F);
		Strap7.setTextureOffset(81, 63).addBox(-0.5F, -0.816F, -2.3794F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Strap8 = new ModelRenderer(this);
		Strap8.setRotationPoint(-7.0334F, -4.3046F, -5.2157F);
		LeftStraps2.addChild(Strap8);
		setRotationAngle(Strap8, -1.9984F, -0.1571F, 0.3491F);
		Strap8.setTextureOffset(81, 63).addBox(-1.5399F, 1.9355F, -9.1751F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Strap9 = new ModelRenderer(this);
		Strap9.setRotationPoint(-4.0334F, -4.3046F, -17.2157F);
		LeftStraps2.addChild(Strap9);
		setRotationAngle(Strap9, -2.4042F, -0.1571F, 0.6065F);
		Strap9.setTextureOffset(81, 63).addBox(-4.3298F, -3.3912F, -14.8971F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Strap10 = new ModelRenderer(this);
		Strap10.setRotationPoint(-3.9489F, -2.8571F, -22.8635F);
		LeftStraps2.addChild(Strap10);
		setRotationAngle(Strap10, 2.7445F, -0.1571F, 0.6065F);
		Strap10.setTextureOffset(81, 63).addBox(-4.3298F, 15.2708F, -12.966F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Strap11 = new ModelRenderer(this);
		Strap11.setRotationPoint(-3.9489F, -2.8571F, -22.8635F);
		LeftStraps2.addChild(Strap11);
		setRotationAngle(Strap11, 2.7445F, -0.1571F, 0.5018F);
		Strap11.setTextureOffset(81, 63).addBox(-3.1304F, 17.7245F, -12.5698F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Strap12 = new ModelRenderer(this);
		Strap12.setRotationPoint(-3.324F, -4.1973F, -22.2524F);
		LeftStraps2.addChild(Strap12);
		setRotationAngle(Strap12, 2.7445F, -0.1571F, 0.3491F);
		Strap12.setTextureOffset(81, 63).addBox(-1.2853F, 18.3602F, -12.0616F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.3F, -4.425F, -2.4125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.2138F, 0.4363F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-3.287F, -11.6771F, -4.3207F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		Heart1 = new ModelRenderer(this);
		Heart1.setRotationPoint(0.0229F, -0.5246F, 0.221F);
		RightLeg.addChild(Heart1);
		setRotationAngle(Heart1, 0.2531F, 0.0F, 0.0F);
		Heart1.setTextureOffset(80, 13).addBox(-1.762F, -2.9478F, -3.9542F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Heart1.setTextureOffset(80, 13).addBox(-2.762F, -3.9478F, -3.9542F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		Heart1.setTextureOffset(80, 13).addBox(-1.762F, -4.9478F, -3.9542F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(-1.2F, 3.75F, -0.8375F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		RightFoot.setTextureOffset(25, 44).addBox(-2.25F, -1.425F, 3.475F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.575F, -5.25F, -2.0875F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.2094F, 0.0F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-0.443F, -11.8692F, -3.288F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		Heart2 = new ModelRenderer(this);
		Heart2.setRotationPoint(0.0008F, -0.4998F, 0.8555F);
		LeftLeg.addChild(Heart2);
		Heart2.setTextureOffset(80, 13).addBox(1.057F, -2.8692F, -4.4505F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Heart2.setTextureOffset(80, 13).addBox(0.057F, -3.8692F, -4.4505F, 3.0F, 1.0F, 1.0F, 0.0F, true);
		Heart2.setTextureOffset(80, 13).addBox(1.057F, -4.8692F, -4.4505F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(1.175F, 3.75F, -0.8375F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1887F, -7.1713F, 0.2958F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		LeftFoot.setTextureOffset(25, 44).addBox(0.95F, -1.675F, 1.3F, 1.0F, 3.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if(entity.isTransforming())
			return;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		HeadBase.render(matrixStack, buffer, packedLight, packedOverlay);
		BodyBase.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}