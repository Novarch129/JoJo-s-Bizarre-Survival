package novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import novarch.jojomod.entities.stands.TheWorldEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TheWorldModel<T extends TheWorldEntity> extends EntityModel<T> {
	private final ModelRenderer HeadBase;
	private final ModelRenderer Head;
	private final ModelRenderer Eyes;
	private final ModelRenderer Helmet;
	private final ModelRenderer Mask;
	private final ModelRenderer Mask1;
	private final ModelRenderer Mask2;
	private final ModelRenderer Tubes;
	private final ModelRenderer Tubes1;
	private final ModelRenderer Tube1;
	private final ModelRenderer Tube2;
	private final ModelRenderer Tube3;
	private final ModelRenderer Tube4;
	private final ModelRenderer Tube5;
	private final ModelRenderer Tubes2;
	private final ModelRenderer Tube6;
	private final ModelRenderer Tube7;
	private final ModelRenderer Tube8;
	private final ModelRenderer Tube9;
	private final ModelRenderer Tube10;
	private final ModelRenderer Tube11;
	private final ModelRenderer Heart;
	private final ModelRenderer HeartPiece9;
	private final ModelRenderer HeartPiece10;
	private final ModelRenderer HeartPiece11;
	private final ModelRenderer BodyBase;
	private final ModelRenderer Torso;
	private final ModelRenderer ShoulderPads;
	private final ModelRenderer Chest;
	private final ModelRenderer Abs;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightHand;
	private final ModelRenderer ElbowPad2;
	private final ModelRenderer Heart7;
	private final ModelRenderer HeartPiece20;
	private final ModelRenderer HeartPiece21;
	private final ModelRenderer HeartPiece22;
	private final ModelRenderer RightArm2;
	private final ModelRenderer RightHand2;
	private final ModelRenderer ElbowPad;
	private final ModelRenderer Heart6;
	private final ModelRenderer HeartPiece17;
	private final ModelRenderer HeartPiece18;
	private final ModelRenderer HeartPiece19;
	private final ModelRenderer Crotch;
	private final ModelRenderer Heart2;
	private final ModelRenderer HeartPiece2;
	private final ModelRenderer HeartPiece3;
	private final ModelRenderer HeartPiece4;
	private final ModelRenderer Heart3;
	private final ModelRenderer HeartPiece5;
	private final ModelRenderer HeartPiece6;
	private final ModelRenderer HeartPiece7;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightFoot;
	private final ModelRenderer KneePad2;
	private final ModelRenderer Heart5;
	private final ModelRenderer HeartPiece14;
	private final ModelRenderer HeartPiece15;
	private final ModelRenderer HeartPiece16;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer KneePad1;
	private final ModelRenderer Heart4;
	private final ModelRenderer HeartPiece8;
	private final ModelRenderer HeartPiece12;
	private final ModelRenderer HeartPiece13;

	public TheWorldModel() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -4.0F, 0.0F);
		

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -10.1F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -10.1F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		Head.setTextureOffset(13, 8).addBox(-1.0F, -2.85F, -0.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, -0.225F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.1F, -3.9F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.1F, -3.9F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(105, 113).addBox(-4.675F, -11.275F, -5.25F, 1.0F, 5.0F, 10.0F, 0.0F, true);
		Helmet.setTextureOffset(105, 113).addBox(3.675F, -11.275F, -5.25F, 1.0F, 5.0F, 10.0F, 0.0F, false);
		Helmet.setTextureOffset(81, 117).addBox(-4.675F, -11.375F, -5.25F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		Helmet.setTextureOffset(81, 117).addBox(3.675F, -11.375F, -5.25F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		Helmet.setTextureOffset(96, 112).addBox(-4.675F, -6.275F, -5.25F, 1.0F, 1.0F, 6.0F, 0.0F, true);
		Helmet.setTextureOffset(96, 112).addBox(3.675F, -6.275F, -5.25F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		Helmet.setTextureOffset(42, 118).addBox(-4.0F, -11.375F, -5.25F, 8.0F, 1.0F, 9.0F, 0.0F, false);
		Helmet.setTextureOffset(110, 123).addBox(-4.0F, -11.125F, -5.25F, 8.0F, 4.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(110, 123).addBox(-1.0F, -8.025F, -5.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(110, 123).addBox(3.0F, -7.275F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(110, 123).addBox(-4.0F, -7.275F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(110, 123).addBox(-4.0F, -6.275F, -5.25F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(109, 104).addBox(-4.0F, -11.375F, 3.75F, 8.0F, 5.0F, 1.0F, 0.0F, false);

		Mask = new ModelRenderer(this);
		Mask.setRotationPoint(-3.5F, -2.25F, -0.3F);
		Helmet.addChild(Mask);
		setRotationAngle(Mask, 0.0F, 0.0F, 0.7854F);
		

		Mask1 = new ModelRenderer(this);
		Mask1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Mask.addChild(Mask1);
		Mask1.setTextureOffset(110, 123).addBox(-0.9293F, -4.9293F, -4.94F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Mask2 = new ModelRenderer(this);
		Mask2.setRotationPoint(0.0F, -4.5F, -4.5F);
		Mask.addChild(Mask2);
		setRotationAngle(Mask2, 0.0F, 0.0F, 1.5708F);
		Mask2.setTextureOffset(110, 123).addBox(-1.4293F, -1.0707F, -0.44F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		Tubes = new ModelRenderer(this);
		Tubes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(Tubes);
		

		Tubes1 = new ModelRenderer(this);
		Tubes1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tubes.addChild(Tubes1);
		

		Tube1 = new ModelRenderer(this);
		Tube1.setRotationPoint(4.1995F, -5.8189F, 0.5937F);
		Tubes1.addChild(Tube1);
		setRotationAngle(Tube1, 0.4451F, 0.0F, -0.24F);
		Tube1.setTextureOffset(110, 123).addBox(-0.5238F, -0.9123F, -0.5418F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Tube2 = new ModelRenderer(this);
		Tube2.setRotationPoint(4.5696F, -4.3065F, 1.3363F);
		Tubes1.addChild(Tube2);
		setRotationAngle(Tube2, 1.3352F, 0.0F, -0.24F);
		Tube2.setTextureOffset(110, 123).addBox(-0.5238F, -0.8222F, 0.1543F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Tube3 = new ModelRenderer(this);
		Tube3.setRotationPoint(4.6902F, -3.8133F, 3.4512F);
		Tubes1.addChild(Tube3);
		setRotationAngle(Tube3, 1.3352F, -0.5149F, -0.24F);
		Tube3.setTextureOffset(110, 123).addBox(-1.0452F, -1.1075F, 0.1278F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Tube4 = new ModelRenderer(this);
		Tube4.setRotationPoint(3.8505F, -3.1152F, 5.1861F);
		Tubes1.addChild(Tube4);
		setRotationAngle(Tube4, 2.1424F, -0.5149F, -0.24F);
		Tube4.setTextureOffset(110, 123).addBox(-1.0452F, -0.7081F, 0.9245F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Tube5 = new ModelRenderer(this);
		Tube5.setRotationPoint(3.8505F, -3.1152F, 5.1861F);
		Tubes1.addChild(Tube5);
		setRotationAngle(Tube5, -2.6704F, -0.5149F, -0.24F);
		

		Tubes2 = new ModelRenderer(this);
		Tubes2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tubes.addChild(Tubes2);
		

		Tube6 = new ModelRenderer(this);
		Tube6.setRotationPoint(-4.1995F, -5.8189F, 0.5937F);
		Tubes2.addChild(Tube6);
		setRotationAngle(Tube6, 0.4451F, 0.0F, 0.24F);
		Tube6.setTextureOffset(110, 123).addBox(-0.4762F, -0.9123F, -0.5418F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Tube7 = new ModelRenderer(this);
		Tube7.setRotationPoint(-4.5696F, -4.3065F, 1.3363F);
		Tubes2.addChild(Tube7);
		setRotationAngle(Tube7, 1.3352F, 0.0F, 0.24F);
		Tube7.setTextureOffset(110, 123).addBox(-0.4762F, -0.8222F, 0.1543F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Tube8 = new ModelRenderer(this);
		Tube8.setRotationPoint(-4.6902F, -3.8133F, 3.4512F);
		Tubes2.addChild(Tube8);
		setRotationAngle(Tube8, 1.3352F, 0.5149F, 0.24F);
		Tube8.setTextureOffset(110, 123).addBox(0.0452F, -1.1075F, 0.1278F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Tube9 = new ModelRenderer(this);
		Tube9.setRotationPoint(-3.8505F, -3.1152F, 5.1861F);
		Tubes2.addChild(Tube9);
		setRotationAngle(Tube9, 2.1424F, 0.5149F, 0.24F);
		Tube9.setTextureOffset(110, 123).addBox(0.0452F, -0.7081F, 0.9245F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Tube10 = new ModelRenderer(this);
		Tube10.setRotationPoint(-4.6344F, -3.8783F, 4.2732F);
		Tubes2.addChild(Tube10);
		setRotationAngle(Tube10, -2.6704F, 0.0262F, 0.7418F);
		Tube10.setTextureOffset(110, 123).addBox(0.2421F, 1.0997F, -1.7946F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Tube11 = new ModelRenderer(this);
		Tube11.setRotationPoint(4.6344F, -3.8783F, 4.2732F);
		Tubes2.addChild(Tube11);
		setRotationAngle(Tube11, -2.6704F, -0.0262F, -0.7418F);
		Tube11.setTextureOffset(110, 123).addBox(-1.2421F, 1.0997F, -1.7946F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Heart = new ModelRenderer(this);
		Heart.setRotationPoint(-0.25F, 15.65F, 0.6F);
		Head.addChild(Heart);
		

		HeartPiece9 = new ModelRenderer(this);
		HeartPiece9.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece9);
		setRotationAngle(HeartPiece9, 0.0F, 0.0F, 0.7854F);
		HeartPiece9.setTextureOffset(0, 84).addBox(0.1811F, -0.495F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece10 = new ModelRenderer(this);
		HeartPiece10.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece10);
		setRotationAngle(HeartPiece10, 0.0F, 0.0F, -0.7854F);
		HeartPiece10.setTextureOffset(0, 84).addBox(-0.7979F, -0.1118F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece11 = new ModelRenderer(this);
		HeartPiece11.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart.addChild(HeartPiece11);
		setRotationAngle(HeartPiece11, 0.0F, 0.0F, -1.5708F);
		HeartPiece11.setTextureOffset(0, 84).addBox(-0.4851F, -0.6433F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(-0.4851F, 1.1851F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece11.setTextureOffset(0, 84).addBox(-0.6772F, -0.2575F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.7796F, -2.5377F, 20.0F, 5.0F, 6.0F, 0.0F, false);
		Torso.setTextureOffset(88, 0).addBox(0.5F, -4.7796F, 3.1623F, 3.0F, 7.0F, 2.0F, 0.0F, false);
		Torso.setTextureOffset(88, 0).addBox(-3.5F, -4.7796F, 3.1623F, 3.0F, 7.0F, 2.0F, 0.0F, true);

		ShoulderPads = new ModelRenderer(this);
		ShoulderPads.setRotationPoint(0.0F, 0.0F, 0.0F);
		Torso.addChild(ShoulderPads);
		ShoulderPads.setTextureOffset(104, 0).addBox(6.0F, -5.5296F, -3.0377F, 5.0F, 6.0F, 7.0F, 0.0F, false);
		ShoulderPads.setTextureOffset(104, 0).addBox(-11.0F, -5.5296F, -3.0377F, 5.0F, 6.0F, 7.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.6722F, -9.6924F, 11.0F, 6.0F, 6.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.075F, -3.0007F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.1058F, -0.6479F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);
		

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-7.15F, -28.3015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-1.6977F, 1.8218F, -0.708F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.6449F, 0.5745F, -2.45F, 4.0F, 7.0F, 4.0F, 0.0F, false);

		ElbowPad2 = new ModelRenderer(this);
		ElbowPad2.setRotationPoint(0.4747F, 10.2137F, 0.5989F);
		RightArm.addChild(ElbowPad2);
		setRotationAngle(ElbowPad2, -0.0654F, 1.5839F, -0.144F);
		ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(64, 85).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(64, 85).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		ElbowPad2.setTextureOffset(64, 85).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(64, 85).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);

		Heart7 = new ModelRenderer(this);
		Heart7.setRotationPoint(4.0503F, 2.8628F, 3.4072F);
		ElbowPad2.addChild(Heart7);
		

		HeartPiece20 = new ModelRenderer(this);
		HeartPiece20.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece20);
		setRotationAngle(HeartPiece20, 0.0F, 0.0F, -0.7854F);
		HeartPiece20.setTextureOffset(0, 84).addBox(-13.1017F, 5.0588F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece21 = new ModelRenderer(this);
		HeartPiece21.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece21);
		setRotationAngle(HeartPiece21, 0.0F, 0.0F, 0.7854F);
		HeartPiece21.setTextureOffset(0, 84).addBox(5.3517F, 11.8088F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece22 = new ModelRenderer(this);
		HeartPiece22.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece22);
		setRotationAngle(HeartPiece22, 0.0F, 0.0F, 1.5708F);
		HeartPiece22.setTextureOffset(0, 84).addBox(11.8414F, 3.8587F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(0, 84).addBox(11.8414F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(0, 84).addBox(11.3664F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(0, 84).addBox(11.3664F, 3.8587F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(0, 84).addBox(12.0335F, 4.2445F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		RightArm2 = new ModelRenderer(this);
		RightArm2.setRotationPoint(8.3F, -26.9765F, -1.3061F);
		Arms.addChild(RightArm2);
		setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
		RightArm2.setTextureOffset(27, 32).addBox(-3.0098F, 0.6387F, -1.025F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		RightHand2 = new ModelRenderer(this);
		RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
		RightArm2.addChild(RightHand2);
		setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
		RightHand2.setTextureOffset(0, 53).addBox(-1.2477F, 0.1954F, -2.3667F, 4.0F, 7.0F, 4.0F, 0.0F, true);

		ElbowPad = new ModelRenderer(this);
		ElbowPad.setRotationPoint(-1.2247F, 8.8887F, 0.3739F);
		RightArm2.addChild(ElbowPad);
		setRotationAngle(ElbowPad, -0.0654F, -1.5839F, 0.144F);
		ElbowPad.setTextureOffset(64, 85).addBox(1.9267F, -3.4988F, -1.5743F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		ElbowPad.setTextureOffset(64, 85).addBox(-1.6903F, -3.4988F, -1.5743F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		ElbowPad.setTextureOffset(64, 85).addBox(1.9267F, -3.4988F, -2.3243F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		ElbowPad.setTextureOffset(64, 85).addBox(-1.6903F, -3.4988F, -2.3243F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		ElbowPad.setTextureOffset(64, 85).addBox(-0.8233F, -3.4988F, 1.4257F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		ElbowPad.setTextureOffset(64, 85).addBox(-1.0733F, -3.4988F, -2.3243F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		Heart6 = new ModelRenderer(this);
		Heart6.setRotationPoint(-4.0503F, 2.8628F, 3.4072F);
		ElbowPad.addChild(Heart6);
		

		HeartPiece17 = new ModelRenderer(this);
		HeartPiece17.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart6.addChild(HeartPiece17);
		setRotationAngle(HeartPiece17, 0.0F, 0.0F, 0.7854F);
		HeartPiece17.setTextureOffset(0, 84).addBox(12.1016F, 5.0588F, -1.5466F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece18 = new ModelRenderer(this);
		HeartPiece18.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart6.addChild(HeartPiece18);
		setRotationAngle(HeartPiece18, 0.0F, 0.0F, -0.7854F);
		HeartPiece18.setTextureOffset(0, 84).addBox(-6.3517F, 11.8087F, -1.5466F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece19 = new ModelRenderer(this);
		HeartPiece19.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart6.addChild(HeartPiece19);
		setRotationAngle(HeartPiece19, 0.0F, 0.0F, -1.5708F);
		HeartPiece19.setTextureOffset(0, 84).addBox(-12.8414F, 3.8587F, -1.5466F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece19.setTextureOffset(0, 84).addBox(-12.8414F, 5.6871F, -1.5466F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece19.setTextureOffset(0, 84).addBox(-12.3664F, 5.6871F, -1.5466F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece19.setTextureOffset(0, 84).addBox(-12.3664F, 3.8587F, -1.5466F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece19.setTextureOffset(0, 84).addBox(-13.0334F, 4.2444F, -1.5466F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Crotch = new ModelRenderer(this);
		Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Crotch);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.1058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.1058F, -0.097F, 9.0F, 5.0F, 1.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(4.25F, -18.6058F, -3.722F, 1.0F, 4.0F, 4.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-5.25F, -18.6058F, -3.722F, 1.0F, 4.0F, 4.0F, 0.0F, true);

		Heart2 = new ModelRenderer(this);
		Heart2.setRotationPoint(-0.25F, -12.65F, 1.1F);
		Crotch.addChild(Heart2);
		

		HeartPiece2 = new ModelRenderer(this);
		HeartPiece2.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart2.addChild(HeartPiece2);
		setRotationAngle(HeartPiece2, 0.0F, 0.0F, 0.7854F);
		HeartPiece2.setTextureOffset(0, 84).addBox(8.2774F, 7.6014F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece3 = new ModelRenderer(this);
		HeartPiece3.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart2.addChild(HeartPiece3);
		setRotationAngle(HeartPiece3, 0.0F, 0.0F, -0.7854F);
		HeartPiece3.setTextureOffset(0, 84).addBox(-8.8943F, 7.9845F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece4 = new ModelRenderer(this);
		HeartPiece4.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart2.addChild(HeartPiece4);
		setRotationAngle(HeartPiece4, 0.0F, 0.0F, -1.5708F);
		HeartPiece4.setTextureOffset(0, 84).addBox(-11.9351F, -0.6433F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece4.setTextureOffset(0, 84).addBox(-11.9351F, 1.1851F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece4.setTextureOffset(0, 84).addBox(-11.4351F, 1.1851F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece4.setTextureOffset(0, 84).addBox(-11.4351F, -0.6433F, -1.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece4.setTextureOffset(0, 84).addBox(-12.1272F, -0.2575F, -1.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Heart3 = new ModelRenderer(this);
		Heart3.setRotationPoint(-0.25F, 0.625F, 0.225F);
		Crotch.addChild(Heart3);
		

		HeartPiece5 = new ModelRenderer(this);
		HeartPiece5.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart3.addChild(HeartPiece5);
		setRotationAngle(HeartPiece5, 0.0F, 0.0F, 0.7854F);
		HeartPiece5.setTextureOffset(0, 91).addBox(1.9135F, 0.2374F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		HeartPiece6 = new ModelRenderer(this);
		HeartPiece6.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart3.addChild(HeartPiece6);
		setRotationAngle(HeartPiece6, 0.0F, 0.0F, -0.7854F);
		HeartPiece6.setTextureOffset(0, 91).addBox(-2.5303F, 0.6206F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		HeartPiece7 = new ModelRenderer(this);
		HeartPiece7.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart3.addChild(HeartPiece7);
		setRotationAngle(HeartPiece7, 0.0F, 0.0F, -1.5708F);
		HeartPiece7.setTextureOffset(0, 91).addBox(-2.2522F, -1.3325F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-2.2522F, 1.8925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-3.1272F, -0.2575F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-1.2522F, 1.8925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-1.2522F, 1.2925F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-1.2522F, -0.7075F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece7.setTextureOffset(0, 91).addBox(-1.2522F, -1.3325F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-2.2F, -16.875F, -2.7625F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
		RightLeg.setTextureOffset(80, 24).addBox(-1.9428F, -1.0138F, -1.4F, 4.0F, 9.0F, 4.0F, 0.0F, true);
		RightLeg.setTextureOffset(54, 84).addBox(-0.3623F, 5.042F, -1.4386F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.14F, 13.5892F, 2.0955F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.1658F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(54, 84).addBox(-0.5523F, -4.5045F, -2.5202F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		RightFoot.setTextureOffset(59, 46).addBox(-2.0828F, -6.1579F, -2.458F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad2 = new ModelRenderer(this);
		KneePad2.setRotationPoint(2.5935F, -4.8213F, 1.6032F);
		RightFoot.addChild(KneePad2);
		setRotationAngle(KneePad2, -0.0654F, 0.0F, 0.0F);
		KneePad2.setTextureOffset(64, 85).addBox(-1.4779F, -1.0932F, -3.4771F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		KneePad2.setTextureOffset(64, 85).addBox(-5.0949F, -1.0932F, -3.4771F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		KneePad2.setTextureOffset(64, 85).addBox(-1.4779F, -1.0932F, -4.2271F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad2.setTextureOffset(64, 85).addBox(-5.0949F, -1.0932F, -4.2271F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		KneePad2.setTextureOffset(64, 85).addBox(-4.2279F, -1.0932F, -0.4771F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad2.setTextureOffset(64, 85).addBox(-4.4779F, -1.0932F, -4.2271F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		Heart5 = new ModelRenderer(this);
		Heart5.setRotationPoint(-0.4585F, -7.5928F, -0.8862F);
		KneePad2.addChild(Heart5);
		

		HeartPiece14 = new ModelRenderer(this);
		HeartPiece14.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart5.addChild(HeartPiece14);
		setRotationAngle(HeartPiece14, 0.0F, 0.0F, 0.7854F);
		HeartPiece14.setTextureOffset(0, 84).addBox(16.2487F, 19.1003F, 0.8441F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece15 = new ModelRenderer(this);
		HeartPiece15.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart5.addChild(HeartPiece15);
		setRotationAngle(HeartPiece15, 0.0F, 0.0F, -0.7854F);
		HeartPiece15.setTextureOffset(0, 84).addBox(-20.3932F, 15.9558F, 0.8441F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece16 = new ModelRenderer(this);
		HeartPiece16.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart5.addChild(HeartPiece16);
		setRotationAngle(HeartPiece16, 0.0F, 0.0F, -1.5708F);
		HeartPiece16.setTextureOffset(0, 84).addBox(-25.7026F, -3.1377F, 0.8441F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece16.setTextureOffset(0, 84).addBox(-25.7026F, -1.3092F, 0.8441F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece16.setTextureOffset(0, 84).addBox(-25.2276F, -1.3092F, 0.8441F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece16.setTextureOffset(0, 84).addBox(-25.2276F, -3.1377F, 0.8441F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece16.setTextureOffset(0, 84).addBox(-25.8947F, -2.7519F, 0.8441F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-2.0227F, -3.1015F, -1.4355F, 4.0F, 9.0F, 4.0F, 0.0F, false);
		LeftLeg.setTextureOffset(54, 84).addBox(-0.4587F, 3.2408F, -1.5162F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.4058F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(54, 84).addBox(-0.5625F, -5.9318F, 0.1889F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1973F, -7.0763F, 0.2659F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		KneePad1 = new ModelRenderer(this);
		KneePad1.setRotationPoint(-1.5539F, -3.2769F, 3.2188F);
		LeftFoot.addChild(KneePad1);
		setRotationAngle(KneePad1, -0.0654F, 0.0F, 0.0F);
		KneePad1.setTextureOffset(64, 85).addBox(2.5914F, -3.9032F, -2.4986F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(-1.0256F, -3.9032F, -2.4986F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		KneePad1.setTextureOffset(64, 85).addBox(2.5914F, -3.9032F, -3.2486F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(-1.0256F, -3.9032F, -3.2486F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		KneePad1.setTextureOffset(64, 85).addBox(-0.1586F, -3.9032F, 0.5014F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		KneePad1.setTextureOffset(64, 85).addBox(-0.4086F, -3.9032F, -3.2486F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		Heart4 = new ModelRenderer(this);
		Heart4.setRotationPoint(-0.4585F, -7.5928F, -0.8862F);
		KneePad1.addChild(Heart4);
		

		HeartPiece8 = new ModelRenderer(this);
		HeartPiece8.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart4.addChild(HeartPiece8);
		setRotationAngle(HeartPiece8, 0.0F, 0.0F, 0.7854F);
		HeartPiece8.setTextureOffset(0, 84).addBox(17.1392F, 14.2359F, 1.8225F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece12 = new ModelRenderer(this);
		HeartPiece12.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart4.addChild(HeartPiece12);
		setRotationAngle(HeartPiece12, 0.0F, 0.0F, -0.7854F);
		HeartPiece12.setTextureOffset(0, 84).addBox(-15.5287F, 16.8463F, 1.8225F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece13 = new ModelRenderer(this);
		HeartPiece13.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart4.addChild(HeartPiece13);
		setRotationAngle(HeartPiece13, 0.0F, 0.0F, -1.5708F);
		HeartPiece13.setTextureOffset(0, 84).addBox(-22.8926F, 0.9316F, 1.8225F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		HeartPiece13.setTextureOffset(0, 84).addBox(-22.8926F, 2.7601F, 1.8225F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece13.setTextureOffset(0, 84).addBox(-22.4176F, 2.7601F, 1.8225F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece13.setTextureOffset(0, 84).addBox(-22.4176F, 0.9316F, 1.8225F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		HeartPiece13.setTextureOffset(0, 84).addBox(-23.0847F, 1.3174F, 1.8225F, 1.0F, 2.0F, 1.0F, 0.0F, true);
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