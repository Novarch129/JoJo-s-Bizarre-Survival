package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.KingCrimsonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KingCrimsonModel<T extends KingCrimsonEntity> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Epitaph;
	private final ModelRenderer EpitaphFace;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer ShoulderPads;
	private final ModelRenderer NeckHalfcircle;
	private final ModelRenderer Part;
	private final ModelRenderer NeckParts1;
	private final ModelRenderer NeckPart1;
	private final ModelRenderer NeckPart2;
	private final ModelRenderer NeckPart3;
	private final ModelRenderer NeckParts2;
	private final ModelRenderer NeckPart4;
	private final ModelRenderer NeckPart5;
	private final ModelRenderer NeckPart6;
	private final ModelRenderer Chest;
	private final ModelRenderer Lines;
	private final ModelRenderer Line1;
	private final ModelRenderer Line2;
	private final ModelRenderer Line3;
	private final ModelRenderer Line4;
	private final ModelRenderer Lines2;
	private final ModelRenderer Line5;
	private final ModelRenderer Line6;
	private final ModelRenderer Line7;
	private final ModelRenderer Line8;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer ElbowPad2;
	private final ModelRenderer RightHand;
	private final ModelRenderer RightArm2;
	private final ModelRenderer ElbowPad1;
	private final ModelRenderer RightHand2;
	private final ModelRenderer Crotch;
	private final ModelRenderer CrotchPart1;
	private final ModelRenderer CrotchPart6;
	private final ModelRenderer CrotchPart2;
	private final ModelRenderer CrotchPart5;
	private final ModelRenderer CrotchPart3;
	private final ModelRenderer CrotchPart4;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer KneePad2;
	private final ModelRenderer RightFoot;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer KneePad1;

	public KingCrimsonModel() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -6.4F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(26, 5).addBox(1.0F, -4.0F, -4.15F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(26, 5).addBox(-3.0F, -4.0F, -4.15F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Epitaph = new ModelRenderer(this);
		Epitaph.setRotationPoint(0.0F, 0.0F, 0.625F);
		Head.addChild(Epitaph);
		Epitaph.setTextureOffset(0, 0).addBox(2.0F, -7.975F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Epitaph.setTextureOffset(0, 0).addBox(-3.0F, -7.975F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Epitaph.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Epitaph.setTextureOffset(0, 0).addBox(1.0F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Epitaph.setTextureOffset(0, 0).addBox(-1.0F, -5.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		EpitaphFace = new ModelRenderer(this);
		EpitaphFace.setRotationPoint(0.0F, 0.1F, 1.275F);
		Epitaph.addChild(EpitaphFace);
		EpitaphFace.setTextureOffset(26, 0).addBox(-2.0F, -8.0F, -5.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		EpitaphFace.setTextureOffset(26, 0).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		EpitaphFace.setTextureOffset(37, 0).addBox(-2.0F, -8.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		EpitaphFace.setTextureOffset(37, 0).addBox(1.0F, -8.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		EpitaphFace.setTextureOffset(43, 0).addBox(-1.0F, -7.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		ShoulderPads = new ModelRenderer(this);
		ShoulderPads.setRotationPoint(0.0F, -0.121F, -0.4851F);
		Torso.addChild(ShoulderPads);
		ShoulderPads.setTextureOffset(26, 49).addBox(4.45F, -5.3597F, -2.6604F, 6.0F, 6.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(26, 49).addBox(-10.45F, -5.3597F, -2.6604F, 6.0F, 6.0F, 7.0F, 0.0F, true);

		NeckHalfcircle = new ModelRenderer(this);
		NeckHalfcircle.setRotationPoint(0.0F, 0.3205F, -1.2856F);
		ShoulderPads.addChild(NeckHalfcircle);
		NeckHalfcircle.setTextureOffset(64, 64).addBox(3.775F, -7.347F, -1.5883F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		NeckHalfcircle.setTextureOffset(64, 64).addBox(-4.775F, -7.347F, -1.5883F, 1.0F, 3.0F, 6.0F, 0.0F, true);

		Part = new ModelRenderer(this);
		Part.setRotationPoint(0.0331F, -6.2985F, 7.3969F);
		NeckHalfcircle.addChild(Part);
		setRotationAngle(Part, 0.0F, 0.7767F, 0.0F);
		Part.setTextureOffset(37, 54).addBox(-0.772F, -1.0244F, -0.2615F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		NeckParts1 = new ModelRenderer(this);
		NeckParts1.setRotationPoint(0.0F, 0.0F, 0.0F);
		NeckHalfcircle.addChild(NeckParts1);
		

		NeckPart1 = new ModelRenderer(this);
		NeckPart1.setRotationPoint(0.0F, 0.0F, 0.0F);
		NeckParts1.addChild(NeckPart1);
		setRotationAngle(NeckPart1, 0.0F, -0.6109F, 0.0F);
		NeckPart1.setTextureOffset(37, 54).addBox(5.4419F, -7.347F, 0.875F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		NeckPart2 = new ModelRenderer(this);
		NeckPart2.setRotationPoint(0.0F, -0.9677F, 3.8812F);
		NeckParts1.addChild(NeckPart2);
		setRotationAngle(NeckPart2, 0.0F, -0.8727F, 0.0F);
		NeckPart2.setTextureOffset(37, 54).addBox(2.9934F, -6.3794F, -1.385F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		NeckPart3 = new ModelRenderer(this);
		NeckPart3.setRotationPoint(-3.0F, -0.9677F, 3.8812F);
		NeckParts1.addChild(NeckPart3);
		setRotationAngle(NeckPart3, 0.0F, -1.1345F, 0.0F);
		NeckPart3.setTextureOffset(37, 54).addBox(4.2843F, -6.3794F, -3.1584F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		NeckParts2 = new ModelRenderer(this);
		NeckParts2.setRotationPoint(0.0F, 0.0F, 0.0F);
		NeckHalfcircle.addChild(NeckParts2);
		

		NeckPart4 = new ModelRenderer(this);
		NeckPart4.setRotationPoint(0.0F, 0.0F, 0.0F);
		NeckParts2.addChild(NeckPart4);
		setRotationAngle(NeckPart4, 0.0F, 0.6109F, 0.0F);
		NeckPart4.setTextureOffset(37, 54).addBox(-6.4419F, -7.347F, 0.875F, 1.0F, 3.0F, 2.0F, 0.0F, true);

		NeckPart5 = new ModelRenderer(this);
		NeckPart5.setRotationPoint(0.0F, -0.9677F, 3.8812F);
		NeckParts2.addChild(NeckPart5);
		setRotationAngle(NeckPart5, 0.0F, 0.8727F, 0.0F);
		NeckPart5.setTextureOffset(37, 54).addBox(-3.9934F, -6.3794F, -1.385F, 1.0F, 3.0F, 2.0F, 0.0F, true);

		NeckPart6 = new ModelRenderer(this);
		NeckPart6.setRotationPoint(3.0F, -0.9677F, 3.8812F);
		NeckParts2.addChild(NeckPart6);
		setRotationAngle(NeckPart6, 0.0F, 1.1345F, 0.0F);
		NeckPart6.setTextureOffset(37, 54).addBox(-5.2843F, -6.3794F, -3.1584F, 1.0F, 3.0F, 2.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Lines = new ModelRenderer(this);
		Lines.setRotationPoint(0.0F, 0.0F, 0.0F);
		Chest.addChild(Lines);
		Lines.setTextureOffset(0, 86).addBox(-1.8178F, -20.3723F, -9.8421F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Lines.setTextureOffset(0, 86).addBox(0.8178F, -20.3723F, -9.8421F, 1.0F, 1.0F, 6.0F, 0.0F, true);

		Line1 = new ModelRenderer(this);
		Line1.setRotationPoint(-0.5F, -22.8079F, -9.9603F);
		Lines.addChild(Line1);
		setRotationAngle(Line1, 0.0F, 0.0F, -0.7854F);
		Line1.setTextureOffset(0, 80).addBox(-1.0833F, -5.6389F, 0.1182F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		Line2 = new ModelRenderer(this);
		Line2.setRotationPoint(0.5F, -22.8079F, -9.9603F);
		Lines.addChild(Line2);
		setRotationAngle(Line2, 0.0F, 0.0F, 0.7854F);
		Line2.setTextureOffset(0, 80).addBox(0.0833F, -5.6389F, 0.1182F, 1.0F, 9.0F, 1.0F, 0.0F, true);

		Line3 = new ModelRenderer(this);
		Line3.setRotationPoint(-7.5F, -21.2821F, -9.9506F);
		Lines.addChild(Line3);
		setRotationAngle(Line3, 0.0F, 0.0F, 0.7854F);
		Line3.setTextureOffset(0, 80).addBox(0.0833F, -7.6389F, 0.1182F, 1.0F, 6.0F, 1.0F, 0.0F, true);

		Line4 = new ModelRenderer(this);
		Line4.setRotationPoint(7.5F, -21.2821F, -9.9506F);
		Lines.addChild(Line4);
		setRotationAngle(Line4, 0.0F, 0.0F, -0.7854F);
		Line4.setTextureOffset(0, 80).addBox(-1.0833F, -7.6389F, 0.1182F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		Lines2 = new ModelRenderer(this);
		Lines2.setRotationPoint(0.0F, 0.0735F, -12.9773F);
		Chest.addChild(Lines2);
		Lines2.setTextureOffset(0, 86).addBox(-1.8178F, -20.3723F, 8.8421F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Lines2.setTextureOffset(0, 86).addBox(0.8178F, -20.3723F, 8.8421F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Lines2.setTextureOffset(0, 88).addBox(-1.6254F, -27.6247F, 8.8324F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		Line5 = new ModelRenderer(this);
		Line5.setRotationPoint(-0.5F, -22.8079F, 9.9603F);
		Lines2.addChild(Line5);
		setRotationAngle(Line5, 0.0F, 0.0F, -0.7854F);
		Line5.setTextureOffset(0, 80).addBox(-1.0833F, -5.6389F, -1.1182F, 1.0F, 9.0F, 1.0F, 0.0F, false);

		Line6 = new ModelRenderer(this);
		Line6.setRotationPoint(0.5F, -22.8079F, 9.9603F);
		Lines2.addChild(Line6);
		setRotationAngle(Line6, 0.0F, 0.0F, 0.7854F);
		Line6.setTextureOffset(0, 80).addBox(0.0833F, -5.6389F, -1.1182F, 1.0F, 9.0F, 1.0F, 0.0F, true);

		Line7 = new ModelRenderer(this);
		Line7.setRotationPoint(-7.5F, -21.2821F, 9.9506F);
		Lines2.addChild(Line7);
		setRotationAngle(Line7, 0.0F, 0.0F, 0.7854F);
		Line7.setTextureOffset(0, 80).addBox(0.0833F, -8.6389F, -1.1182F, 1.0F, 7.0F, 1.0F, 0.0F, true);

		Line8 = new ModelRenderer(this);
		Line8.setRotationPoint(7.5F, -21.2821F, 9.9506F);
		Lines2.addChild(Line8);
		setRotationAngle(Line8, 0.0F, 0.0F, -0.7854F);
		Line8.setTextureOffset(0, 80).addBox(-1.0833F, -8.6389F, -1.1182F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);
		

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-8.5F, -20.8015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-2.5694F, -5.0526F, -2.55F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		ElbowPad2 = new ModelRenderer(this);
		ElbowPad2.setRotationPoint(12.0F, 25.6515F, 0.3061F);
		RightArm.addChild(ElbowPad2);
		ElbowPad2.setTextureOffset(31, 85).addBox(-15.0346F, -26.4534F, -2.9035F, 1.0F, 3.0F, 4.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(31, 85).addBox(-11.0346F, -25.4534F, -3.1785F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(31, 85).addBox(-15.0346F, -25.4534F, -3.1535F, 5.0F, 1.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(31, 85).addBox(-15.0346F, -25.4534F, 0.2715F, 5.0F, 1.0F, 1.0F, 0.0F, true);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.6601F, 0.9349F, -2.46F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(8.5F, -20.8015F, -1.3061F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2487F);
		RightArm2.setTextureOffset(27, 32).addBox(-1.4306F, -5.0526F, -2.55F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		ElbowPad1 = new ModelRenderer(this);
		ElbowPad1.setRotationPoint(-12.0F, 25.6515F, 0.3061F);
		RightArm2.addChild(ElbowPad1);
		ElbowPad1.setTextureOffset(31, 85).addBox(14.0346F, -26.4534F, -2.9035F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(31, 85).addBox(10.0346F, -25.4534F, -3.1785F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(31, 85).addBox(10.0346F, -25.4534F, -3.1535F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		ElbowPad1.setTextureOffset(31, 85).addBox(10.0346F, -25.4534F, 0.2715F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.1134F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.3399F, 0.9349F, -2.46F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		

		CrotchPart1 = new ModelRenderer(this);
		CrotchPart1.setRotationPoint(3.0F, -14.0F, -3.0F);
		Crotch.addChild(CrotchPart1);
		CrotchPart1.setTextureOffset(32, 68).addBox(-7.5F, -3.2058F, -1.072F, 9.0F, 1.0F, 5.0F, 0.0F, false);

		CrotchPart6 = new ModelRenderer(this);
		CrotchPart6.setRotationPoint(3.0F, -13.0F, -3.0F);
		Crotch.addChild(CrotchPart6);
		CrotchPart6.setTextureOffset(32, 68).addBox(-6.5F, -3.2058F, -1.072F, 7.0F, 1.0F, 5.0F, 0.0F, false);

		CrotchPart2 = new ModelRenderer(this);
		CrotchPart2.setRotationPoint(3.0F, -18.0F, -3.0F);
		Crotch.addChild(CrotchPart2);
		CrotchPart2.setTextureOffset(32, 68).addBox(-2.5F, -0.2058F, -1.072F, 4.0F, 1.0F, 5.0F, 0.0F, false);

		CrotchPart5 = new ModelRenderer(this);
		CrotchPart5.setRotationPoint(3.0F, -19.0F, -3.0F);
		Crotch.addChild(CrotchPart5);
		CrotchPart5.setTextureOffset(32, 68).addBox(-1.5F, -0.2058F, -1.072F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		CrotchPart3 = new ModelRenderer(this);
		CrotchPart3.setRotationPoint(-3.0F, -18.0F, -3.0F);
		Crotch.addChild(CrotchPart3);
		CrotchPart3.setTextureOffset(32, 68).addBox(-1.5F, -0.2058F, -1.072F, 4.0F, 1.0F, 5.0F, 0.0F, true);

		CrotchPart4 = new ModelRenderer(this);
		CrotchPart4.setRotationPoint(-3.0F, -19.0F, -3.0F);
		Crotch.addChild(CrotchPart4);
		CrotchPart4.setTextureOffset(32, 68).addBox(-1.5F, -0.2058F, -1.072F, 3.0F, 1.0F, 5.0F, 0.0F, true);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.225F, -3.825F, -1.8125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, -0.2138F, 0.1745F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-3.287F, -11.6771F, -4.3207F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		KneePad2 = new ModelRenderer(this);
		KneePad2.setRotationPoint(0.2196F, -2.0737F, -0.0343F);
		RightLeg.addChild(KneePad2);
		setRotationAngle(KneePad2, 0.1571F, 0.0F, 0.0F);
		KneePad2.setTextureOffset(50, 82).addBox(-3.5F, -2.6F, -4.275F, 4.0F, 2.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(50, 82).addBox(-2.5F, -3.6F, -4.275F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(50, 82).addBox(-2.5F, -0.6F, -4.275F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(-1.2F, 3.75F, -0.8375F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.0F, -5.25F, -2.5375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.2094F, 0.0F, -0.1222F);
		LeftLeg.setTextureOffset(80, 24).addBox(-0.443F, -11.8692F, -3.288F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(1.175F, 3.75F, -0.8375F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1887F, -7.1713F, 0.2958F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad1 = new ModelRenderer(this);
		KneePad1.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.addChild(KneePad1);
		KneePad1.setTextureOffset(50, 82).addBox(-0.5F, -4.0F, -3.5F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(50, 82).addBox(0.5F, -5.0F, -3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(50, 82).addBox(0.5F, -2.0F, -3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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