package com.novarch.jojomod.client.entity.model;// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.MadeInHeavenEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelMadeInHeaven <T extends MadeInHeavenEntity> extends EntityModel<T> {
	private final ModelRenderer Head;
	private final ModelRenderer SideLeft;
	private final ModelRenderer SideRight;
	private final ModelRenderer Clock;
	private final ModelRenderer Supports;
	private final ModelRenderer Feathers;
	private final ModelRenderer FeatherRight;
	private final ModelRenderer Feather1;
	private final ModelRenderer Feather3;
	private final ModelRenderer Feather4;
	private final ModelRenderer Feather5;
	private final ModelRenderer Feather2;
	private final ModelRenderer FeatherLeft;
	private final ModelRenderer Feather6;
	private final ModelRenderer Feather7;
	private final ModelRenderer Feather8;
	private final ModelRenderer Feather9;
	private final ModelRenderer Feather10;
	private final ModelRenderer Vines;
	private final ModelRenderer VinesRight;
	private final ModelRenderer Vine1;
	private final ModelRenderer Vine2;
	private final ModelRenderer Vine3;
	private final ModelRenderer Body;
	private final ModelRenderer Torso;
	private final ModelRenderer Clock1;
	private final ModelRenderer Supports2;
	private final ModelRenderer Clock2;
	private final ModelRenderer Supports3;
	private final ModelRenderer Arms;
	private final ModelRenderer RightArm;
	private final ModelRenderer LeftArm;
	private final ModelRenderer Hands;
	private final ModelRenderer RightHand;
	private final ModelRenderer LeftHand;
	private final ModelRenderer HorseStrap;
	private final ModelRenderer HorseStrap2;
	private final ModelRenderer HorseStrap3;
	private final ModelRenderer HorseStrap4;
	private final ModelRenderer HorseStrap5;
	private final ModelRenderer Abs;
	private final ModelRenderer Pipes;
	private final ModelRenderer PipeRight;
	private final ModelRenderer Pipe1;
	private final ModelRenderer Pipe3;
	private final ModelRenderer Pipe2;
	private final ModelRenderer Pipe4;
	private final ModelRenderer Pipe5;
	private final ModelRenderer PipeLeft;
	private final ModelRenderer Pipe6;
	private final ModelRenderer Pipe7;
	private final ModelRenderer Pipe8;
	private final ModelRenderer Pipe9;
	private final ModelRenderer Pipe10;
	private final ModelRenderer Chest;
	private final ModelRenderer Horse;
	private final ModelRenderer HorseBase;
	private final ModelRenderer HorseLegs;
	private final ModelRenderer Legs;
	private final ModelRenderer RightLeg;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer Feet;
	private final ModelRenderer RightFoot;
	private final ModelRenderer LeftFoot;
	private final ModelRenderer Clock6;
	private final ModelRenderer Supports7;
	private final ModelRenderer Clock7;
	private final ModelRenderer Supports8;
	private final ModelRenderer HorseModel;
	private final ModelRenderer Clock3;
	private final ModelRenderer Supports4;
	private final ModelRenderer Clock4;
	private final ModelRenderer Supports5;
	private final ModelRenderer Clock5;
	private final ModelRenderer Supports6;

	public ModelMadeInHeaven() {
		textureWidth = 256;
		textureHeight = 256;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 12.5F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -30.25F, 3.75F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		SideLeft = new ModelRenderer(this);
		SideLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(SideLeft);
		SideLeft.setTextureOffset(0, 0).addBox(3.475F, -27.225F, 8.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		SideLeft.setTextureOffset(4, 21).addBox(3.475F, -28.225F, 6.75F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		SideLeft.setTextureOffset(0, 0).addBox(3.475F, -27.225F, 5.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		SideRight = new ModelRenderer(this);
		SideRight.setRotationPoint(-8.0F, 0.0F, 0.0F);
		Head.addChild(SideRight);
		SideRight.setTextureOffset(0, 0).addBox(3.475F, -27.225F, 8.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		SideRight.setTextureOffset(4, 21).addBox(3.475F, -28.225F, 6.75F, 1.0F, 4.0F, 2.0F, 0.0F, false);
		SideRight.setTextureOffset(0, 0).addBox(3.475F, -27.225F, 5.75F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Clock = new ModelRenderer(this);
		Clock.setRotationPoint(0.0F, -0.55F, -1.95F);
		Head.addChild(Clock);
		Clock.setTextureOffset(0, 16).addBox(-2.0F, -28.8F, 5.3F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		Supports = new ModelRenderer(this);
		Supports.setRotationPoint(0.0F, -12.05F, -2.2F);
		Clock.addChild(Supports);
		Supports.setTextureOffset(0, 0).addBox(-2.0F, -15.75F, 7.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports.setTextureOffset(0, 0).addBox(1.0F, -15.75F, 7.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports.setTextureOffset(0, 0).addBox(-1.0F, -16.75F, 7.25F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Supports.setTextureOffset(0, 0).addBox(-1.0F, -13.75F, 7.25F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Feathers = new ModelRenderer(this);
		Feathers.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Feathers);
		

		FeatherRight = new ModelRenderer(this);
		FeatherRight.setRotationPoint(4.5F, -22.5F, -1.0F);
		Feathers.addChild(FeatherRight);
		setRotationAngle(FeatherRight, -0.3491F, 0.0F, 0.9599F);
		

		Feather1 = new ModelRenderer(this);
		Feather1.setRotationPoint(0.9832F, -0.5003F, -0.1821F);
		FeatherRight.addChild(Feather1);
		setRotationAngle(Feather1, 0.0873F, 0.0F, 0.2618F);
		Feather1.setTextureOffset(49, 0).addBox(-2.3528F, -8.0391F, 6.9306F, 1.0F, 8.0F, 0.0F, 0.0F, false);

		Feather3 = new ModelRenderer(this);
		Feather3.setRotationPoint(-0.2048F, -0.1347F, -0.049F);
		FeatherRight.addChild(Feather3);
		setRotationAngle(Feather3, 0.0873F, 0.0F, 0.0873F);
		Feather3.setTextureOffset(49, 0).addBox(-1.7116F, -8.5489F, 6.9752F, 1.0F, 8.0F, 0.0F, 0.0F, false);

		Feather4 = new ModelRenderer(this);
		Feather4.setRotationPoint(-1.3927F, 0.2308F, 0.084F);
		FeatherRight.addChild(Feather4);
		setRotationAngle(Feather4, 0.0873F, 0.0F, -0.0873F);
		Feather4.setTextureOffset(49, 0).addBox(-0.9912F, -8.9401F, 7.0094F, 1.0F, 8.0F, 0.0F, 0.0F, false);

		Feather5 = new ModelRenderer(this);
		Feather5.setRotationPoint(-1.6181F, 0.9428F, 0.3432F);
		FeatherRight.addChild(Feather5);
		setRotationAngle(Feather5, 0.0873F, 0.0F, -0.4363F);
		Feather5.setTextureOffset(49, 0).addBox(0.5977F, -9.3229F, 7.0429F, 1.0F, 8.0F, 0.0F, 0.0F, false);

		Feather2 = new ModelRenderer(this);
		Feather2.setRotationPoint(2.8905F, -0.7676F, -1.3436F);
		FeatherRight.addChild(Feather2);
		setRotationAngle(Feather2, 0.3491F, 0.0F, -0.1047F);
		Feather2.setTextureOffset(49, 0).addBox(-4.3543F, -3.952F, 8.1869F, 1.0F, 6.0F, 0.0F, 0.0F, false);

		FeatherLeft = new ModelRenderer(this);
		FeatherLeft.setRotationPoint(-5.5F, -22.5F, -1.0F);
		Feathers.addChild(FeatherLeft);
		setRotationAngle(FeatherLeft, -0.3491F, 0.0F, -0.9599F);
		

		Feather6 = new ModelRenderer(this);
		Feather6.setRotationPoint(-0.9832F, -0.5003F, -0.1821F);
		FeatherLeft.addChild(Feather6);
		setRotationAngle(Feather6, 0.0873F, 0.0F, -0.2618F);
		Feather6.setTextureOffset(49, 0).addBox(1.5302F, -7.5826F, 7.0313F, 1.0F, 8.0F, 0.0F, 0.0F, true);

		Feather7 = new ModelRenderer(this);
		Feather7.setRotationPoint(0.2048F, -0.1347F, -0.049F);
		FeatherLeft.addChild(Feather7);
		setRotationAngle(Feather7, 0.0873F, 0.0F, -0.0873F);
		Feather7.setTextureOffset(49, 0).addBox(0.7116F, -8.5489F, 6.9752F, 1.0F, 8.0F, 0.0F, 0.0F, true);

		Feather8 = new ModelRenderer(this);
		Feather8.setRotationPoint(1.3927F, 0.2308F, 0.084F);
		FeatherLeft.addChild(Feather8);
		setRotationAngle(Feather8, 0.0873F, 0.0F, 0.0873F);
		Feather8.setTextureOffset(49, 0).addBox(-0.0088F, -8.9401F, 7.0094F, 1.0F, 8.0F, 0.0F, 0.0F, true);

		Feather9 = new ModelRenderer(this);
		Feather9.setRotationPoint(1.6181F, 0.9428F, 0.3432F);
		FeatherLeft.addChild(Feather9);
		setRotationAngle(Feather9, 0.0873F, 0.0F, 0.4363F);
		Feather9.setTextureOffset(49, 0).addBox(-1.5977F, -9.3229F, 7.0429F, 1.0F, 8.0F, 0.0F, 0.0F, true);

		Feather10 = new ModelRenderer(this);
		Feather10.setRotationPoint(-2.8905F, -0.7676F, -1.3436F);
		FeatherLeft.addChild(Feather10);
		setRotationAngle(Feather10, 0.3491F, 0.0F, 0.1047F);
		Feather10.setTextureOffset(49, 0).addBox(3.6798F, -3.5726F, 8.1979F, 1.0F, 6.0F, 0.0F, 0.0F, true);

		Vines = new ModelRenderer(this);
		Vines.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Vines);
		

		VinesRight = new ModelRenderer(this);
		VinesRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		Vines.addChild(VinesRight);
		

		Vine1 = new ModelRenderer(this);
		Vine1.setRotationPoint(0.0F, 0.0F, 0.0F);
		VinesRight.addChild(Vine1);
		Vine1.setTextureOffset(60, 10).addBox(3.25F, -29.25F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Vine1.setTextureOffset(60, 10).addBox(-4.25F, -29.25F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Vine2 = new ModelRenderer(this);
		Vine2.setRotationPoint(0.0F, 1.0F, 5.0F);
		VinesRight.addChild(Vine2);
		Vine2.setTextureOffset(60, 10).addBox(3.25F, -29.25F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Vine2.setTextureOffset(60, 10).addBox(-4.45F, -30.25F, 3.4F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Vine3 = new ModelRenderer(this);
		Vine3.setRotationPoint(-3.0F, -0.2F, 7.0F);
		VinesRight.addChild(Vine3);
		Vine3.setTextureOffset(60, 10).addBox(2.25F, -29.25F, 4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Vine3.setTextureOffset(60, 10).addBox(5.25F, -29.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Vine3.setTextureOffset(60, 10).addBox(-0.75F, -27.0F, -3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 15.75F, 0.0F);
		

		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -8.25F, 0.0F);
		Body.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(11, 16).addBox(-10.0F, -18.6868F, 0.9514F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		Clock1 = new ModelRenderer(this);
		Clock1.setRotationPoint(1.85F, 7.7097F, -5.8266F);
		Torso.addChild(Clock1);
		setRotationAngle(Clock1, 0.0873F, -1.3963F, 0.0F);
		Clock1.setTextureOffset(0, 16).addBox(8.8914F, -26.3285F, -4.6934F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		Supports2 = new ModelRenderer(this);
		Supports2.setRotationPoint(7.0F, -8.2631F, -6.5377F);
		Clock1.addChild(Supports2);
		Supports2.setTextureOffset(0, 0).addBox(1.8914F, -17.0654F, 1.5943F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports2.setTextureOffset(0, 0).addBox(4.8914F, -17.0654F, 1.5943F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports2.setTextureOffset(0, 0).addBox(2.8914F, -18.0654F, 1.5943F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Supports2.setTextureOffset(0, 0).addBox(2.8914F, -15.0654F, 1.5943F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Clock2 = new ModelRenderer(this);
		Clock2.setRotationPoint(-4.025F, 8.4762F, -6.6274F);
		Torso.addChild(Clock2);
		setRotationAngle(Clock2, 0.0873F, 1.5708F, 0.0873F);
		Clock2.setTextureOffset(0, 16).addBox(-12.5264F, -26.7135F, -6.5571F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		Supports3 = new ModelRenderer(this);
		Supports3.setRotationPoint(-6.575F, -8.5267F, -6.3071F);
		Clock2.addChild(Supports3);
		Supports3.setTextureOffset(0, 0).addBox(-5.9514F, -17.1868F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports3.setTextureOffset(0, 0).addBox(-2.9514F, -17.1868F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports3.setTextureOffset(0, 0).addBox(-4.9514F, -18.1868F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Supports3.setTextureOffset(0, 0).addBox(-4.9514F, -15.1868F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 8.25F, 0.0F);
		Body.addChild(Arms);
		

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.2618F, 0.0F, 0.3491F);
		RightArm.setTextureOffset(11, 27).addBox(-18.8244F, -26.3116F, -1.295F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(7.5F, -10.0F, 1.15F);
		Arms.addChild(LeftArm);
		setRotationAngle(LeftArm, -0.2618F, 0.2618F, -0.2618F);
		LeftArm.setTextureOffset(11, 27).addBox(1.3701F, -21.3591F, 0.0142F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		Hands = new ModelRenderer(this);
		Hands.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Hands);
		

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(-28.2817F, 10.9497F, -12.2411F);
		Hands.addChild(RightHand);
		setRotationAngle(RightHand, -1.309F, -0.1745F, -0.0873F);
		RightHand.setTextureOffset(48, 88).addBox(21.7504F, -20.9482F, -21.0679F, 4.0F, 7.5F, 4.0F, 0.0F, false);

		LeftHand = new ModelRenderer(this);
		LeftHand.setRotationPoint(11.0078F, 0.095F, -13.793F);
		Hands.addChild(LeftHand);
		setRotationAngle(LeftHand, -1.309F, 0.1745F, -0.0873F);
		LeftHand.setTextureOffset(47, 87).addBox(-6.5165F, -23.4665F, -12.2338F, 4.0F, 7.5F, 4.0F, 0.0F, true);

		HorseStrap = new ModelRenderer(this);
		HorseStrap.setRotationPoint(8.5F, -2.5F, -6.75F);
		Hands.addChild(HorseStrap);
		setRotationAngle(HorseStrap, 0.0F, -0.7854F, 0.0F);
		HorseStrap.setTextureOffset(90, 49).addBox(-4.0199F, -11.25F, 4.9801F, 7.0F, 1.0F, 1.0F, 0.0F, false);

		HorseStrap2 = new ModelRenderer(this);
		HorseStrap2.setRotationPoint(-23.5F, -2.5F, -24.75F);
		Hands.addChild(HorseStrap2);
		setRotationAngle(HorseStrap2, 0.0F, 0.7854F, 0.0F);
		HorseStrap2.setTextureOffset(90, 49).addBox(-5.7583F, -11.25F, 28.3146F, 7.0F, 1.0F, 1.0F, 0.0F, false);

		HorseStrap3 = new ModelRenderer(this);
		HorseStrap3.setRotationPoint(5.7218F, -8.0F, 10.5845F);
		Hands.addChild(HorseStrap3);
		setRotationAngle(HorseStrap3, 0.0F, 2.4435F, -0.4363F);
		HorseStrap3.setTextureOffset(90, 49).addBox(7.3086F, -11.2428F, 2.4644F, 7.0F, 1.0F, 1.0F, 0.0F, false);

		HorseStrap4 = new ModelRenderer(this);
		HorseStrap4.setRotationPoint(23.7218F, 5.0F, -18.4155F);
		Hands.addChild(HorseStrap4);
		setRotationAngle(HorseStrap4, 0.0F, -2.4435F, 0.4363F);
		HorseStrap4.setTextureOffset(90, 49).addBox(28.8863F, -9.975F, 1.1479F, 7.0F, 1.0F, 1.0F, 0.0F, false);

		HorseStrap5 = new ModelRenderer(this);
		HorseStrap5.setRotationPoint(19.7218F, -5.0F, -18.1655F);
		Hands.addChild(HorseStrap5);
		setRotationAngle(HorseStrap5, 0.0F, -3.0543F, 0.0F);
		HorseStrap5.setTextureOffset(90, 49).addBox(19.1975F, -11.5208F, -19.9586F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(-0.5F, -4.3522F, -2.9287F);
		Body.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(28, 40).addBox(-3.25F, -17.6703F, 7.1946F, 7.0F, 8.0F, 4.0F, 0.0F, false);

		Pipes = new ModelRenderer(this);
		Pipes.setRotationPoint(0.0F, 0.0F, 0.0F);
		Abs.addChild(Pipes);
		

		PipeRight = new ModelRenderer(this);
		PipeRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		Pipes.addChild(PipeRight);
		

		Pipe1 = new ModelRenderer(this);
		Pipe1.setRotationPoint(-3.5F, -1.2086F, 5.2433F);
		PipeRight.addChild(Pipe1);
		setRotationAngle(Pipe1, -0.9599F, -0.7854F, 0.0F);
		Pipe1.setTextureOffset(54, 0).addBox(4.9968F, -8.0412F, 0.4557F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Pipe3 = new ModelRenderer(this);
		Pipe3.setRotationPoint(-3.5F, -11.2147F, 4.5806F);
		PipeRight.addChild(Pipe3);
		setRotationAngle(Pipe3, -1.1345F, 2.1817F, 0.0F);
		Pipe3.setTextureOffset(54, 0).addBox(-6.8678F, 0.9075F, -4.8153F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Pipe2 = new ModelRenderer(this);
		Pipe2.setRotationPoint(-6.3283F, -2.8769F, 7.5923F);
		PipeRight.addChild(Pipe2);
		setRotationAngle(Pipe2, -0.9599F, -1.5708F, 0.1745F);
		Pipe2.setTextureOffset(54, 0).addBox(6.1395F, -4.3047F, -3.1724F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Pipe4 = new ModelRenderer(this);
		Pipe4.setRotationPoint(-7.4625F, -8.2847F, 6.8602F);
		PipeRight.addChild(Pipe4);
		setRotationAngle(Pipe4, -0.9599F, -1.5708F, 1.0472F);
		Pipe4.setTextureOffset(54, 0).addBox(6.8716F, -3.1427F, -0.7F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Pipe5 = new ModelRenderer(this);
		Pipe5.setRotationPoint(-5.9479F, -9.7153F, 6.0794F);
		PipeRight.addChild(Pipe5);
		setRotationAngle(Pipe5, -0.6981F, -2.1817F, 1.4835F);
		Pipe5.setTextureOffset(54, 0).addBox(7.3283F, -0.2193F, -2.3252F, 1.0F, 3.5F, 1.0F, 0.0F, false);

		PipeLeft = new ModelRenderer(this);
		PipeLeft.setRotationPoint(1.0F, 0.0F, 0.0F);
		Pipes.addChild(PipeLeft);
		

		Pipe6 = new ModelRenderer(this);
		Pipe6.setRotationPoint(3.5F, -1.2086F, 5.2433F);
		PipeLeft.addChild(Pipe6);
		setRotationAngle(Pipe6, -0.9599F, 0.7854F, 0.0F);
		Pipe6.setTextureOffset(54, 0).addBox(-5.9968F, -8.0412F, 0.4557F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Pipe7 = new ModelRenderer(this);
		Pipe7.setRotationPoint(3.5F, -11.2147F, 4.5806F);
		PipeLeft.addChild(Pipe7);
		setRotationAngle(Pipe7, -1.1345F, -2.1817F, 0.0F);
		Pipe7.setTextureOffset(54, 0).addBox(5.8678F, 0.9075F, -4.8153F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Pipe8 = new ModelRenderer(this);
		Pipe8.setRotationPoint(6.3283F, -2.8769F, 7.5923F);
		PipeLeft.addChild(Pipe8);
		setRotationAngle(Pipe8, -0.9599F, 1.5708F, -0.1745F);
		Pipe8.setTextureOffset(54, 0).addBox(-7.1395F, -4.3047F, -3.1724F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Pipe9 = new ModelRenderer(this);
		Pipe9.setRotationPoint(7.4625F, -8.2847F, 6.8602F);
		PipeLeft.addChild(Pipe9);
		setRotationAngle(Pipe9, -0.9599F, 1.5708F, -1.0472F);
		Pipe9.setTextureOffset(54, 0).addBox(-7.8716F, -3.1427F, -0.7F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		Pipe10 = new ModelRenderer(this);
		Pipe10.setRotationPoint(5.9479F, -9.7153F, 6.0794F);
		PipeLeft.addChild(Pipe10);
		setRotationAngle(Pipe10, -0.6981F, 2.1817F, -1.4835F);
		Pipe10.setTextureOffset(54, 0).addBox(-8.3283F, -0.2193F, -2.3252F, 1.0F, 3.5F, 1.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(-0.25F, -11.75F, -0.925F);
		Body.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(28, 28).addBox(-5.5F, -15.6536F, 1.4385F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Horse = new ModelRenderer(this);
		Horse.setRotationPoint(-0.25F, 4.0F, 7.075F);
		

		HorseBase = new ModelRenderer(this);
		HorseBase.setRotationPoint(0.25F, 0.0F, 1.25F);
		Horse.addChild(HorseBase);
		HorseBase.setTextureOffset(53, 41).addBox(-4.75F, -2.75F, -5.25F, 9.0F, 12.0F, 6.0F, 0.0F, false);

		HorseLegs = new ModelRenderer(this);
		HorseLegs.setRotationPoint(-0.25F, 0.0F, -1.25F);
		HorseBase.addChild(HorseLegs);
		

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(2.5F, 13.0F, -13.0F);
		HorseLegs.addChild(Legs);
		setRotationAngle(Legs, -0.8727F, 0.0F, 0.0F);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(0.0F, -0.766F, 0.6428F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, 0.0873F, 0.0F, 0.0F);
		RightLeg.setTextureOffset(128, 111).addBox(-7.0F, -11.3604F, -1.0356F, 3.0F, 11.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(0.0F, 3.1566F, -3.6278F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.3491F, 0.0F, 0.0F);
		LeftLeg.setTextureOffset(128, 111).addBox(-1.0F, -15.2232F, -1.9335F, 3.0F, 11.0F, 4.0F, 0.0F, false);

		Feet = new ModelRenderer(this);
		Feet.setRotationPoint(0.0F, 20.75F, -12.75F);
		HorseLegs.addChild(Feet);
		setRotationAngle(Feet, 0.2618F, 0.0F, 0.0F);
		

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.0F, 0.0F, 0.0F);
		Feet.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.0F, 0.0F, -0.0873F);
		RightFoot.setTextureOffset(29, 89).addBox(-4.4433F, -8.3664F, 2.5246F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(0.0F, -6.5027F, 2.7777F);
		Feet.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.6981F, 0.0F, 0.0F);
		LeftFoot.setTextureOffset(29, 89).addBox(1.5F, -4.9983F, -0.6905F, 3.0F, 8.0F, 3.0F, 0.0F, true);

		Clock6 = new ModelRenderer(this);
		Clock6.setRotationPoint(-0.1962F, 12.3453F, -13.2404F);
		HorseBase.addChild(Clock6);
		setRotationAngle(Clock6, 0.0873F, -1.5708F, -0.0349F);
		Clock6.setTextureOffset(0, 16).addBox(8.8038F, -11.4009F, -4.2714F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		Supports7 = new ModelRenderer(this);
		Supports7.setRotationPoint(5.7962F, -10.3988F, -8.4489F);
		Clock6.addChild(Supports7);
		Supports7.setTextureOffset(0, 0).addBox(3.0076F, -0.0022F, 3.9275F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports7.setTextureOffset(0, 0).addBox(6.0076F, -0.0022F, 3.9275F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports7.setTextureOffset(0, 0).addBox(4.0076F, -1.0022F, 3.9275F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Supports7.setTextureOffset(0, 0).addBox(4.0076F, 1.9978F, 3.9275F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Clock7 = new ModelRenderer(this);
		Clock7.setRotationPoint(0.1962F, 12.3453F, -13.2404F);
		HorseBase.addChild(Clock7);
		setRotationAngle(Clock7, 0.0873F, 1.5708F, 0.0349F);
		Clock7.setTextureOffset(0, 16).addBox(-12.8038F, -11.4271F, -4.7708F, 4.0F, 4.0F, 1.0F, 0.0F, true);

		Supports8 = new ModelRenderer(this);
		Supports8.setRotationPoint(-5.7962F, -10.3988F, -8.4489F);
		Clock7.addChild(Supports8);
		Supports8.setTextureOffset(0, 0).addBox(-4.0076F, -0.0284F, 3.4282F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Supports8.setTextureOffset(0, 0).addBox(-7.0076F, -0.0284F, 3.4282F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		Supports8.setTextureOffset(0, 0).addBox(-6.0076F, -1.0284F, 3.4282F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Supports8.setTextureOffset(0, 0).addBox(-6.0076F, 1.9716F, 3.4282F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		HorseModel = new ModelRenderer(this);
		HorseModel.setRotationPoint(0.2583F, -6.0667F, -20.2417F);
		Horse.addChild(HorseModel);
		setRotationAngle(HorseModel, 0.8029F, 0.0F, 0.0F);
		HorseModel.setTextureOffset(68, 0).addBox(-2.7083F, -0.4534F, 7.3204F, 2.0F, 3.0F, 1.0F, 0.0F, true);
		HorseModel.setTextureOffset(68, 0).addBox(0.6917F, -0.4534F, 7.3204F, 2.0F, 3.0F, 1.0F, 0.0F, true);
		HorseModel.setTextureOffset(38, 0).addBox(-0.4833F, 1.009F, 4.8031F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		HorseModel.setTextureOffset(68, 0).addBox(-2.5083F, 1.6345F, 0.7746F, 5.0F, 5.0F, 7.0F, 0.0F, true);
		HorseModel.setTextureOffset(68, 12).addBox(-1.9583F, 1.7715F, 0.7648F, 4.0F, 20.0F, 8.0F, 0.0F, true);
		HorseModel.setTextureOffset(92, 18).addBox(-2.0083F, 1.9307F, -4.3432F, 4.0F, 3.0F, 6.0F, 0.0F, true);
		HorseModel.setTextureOffset(92, 27).addBox(-2.0083F, 4.9807F, -3.8432F, 4.0F, 2.0F, 5.0F, 0.0F, true);

		Clock3 = new ModelRenderer(this);
		Clock3.setRotationPoint(-0.0083F, 14.0478F, -24.3563F);
		HorseModel.addChild(Clock3);
		setRotationAngle(Clock3, -1.5882F, 0.0F, 0.0F);
		Clock3.setTextureOffset(0, 16).addBox(-2.0F, -28.5726F, -13.0677F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		Supports4 = new ModelRenderer(this);
		Supports4.setRotationPoint(0.0F, -18.5216F, -1.3449F);
		Clock3.addChild(Supports4);
		Supports4.setTextureOffset(0, 0).addBox(-2.0F, -9.0509F, -11.9727F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports4.setTextureOffset(0, 0).addBox(1.0F, -9.0509F, -11.9727F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		Supports4.setTextureOffset(0, 0).addBox(-1.0F, -10.0509F, -11.9727F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Supports4.setTextureOffset(0, 0).addBox(-1.0F, -7.0509F, -11.9727F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Clock4 = new ModelRenderer(this);
		Clock4.setRotationPoint(11.9917F, 4.8387F, -2.7629F);
		HorseModel.addChild(Clock4);
		setRotationAngle(Clock4, -1.2392F, 1.6581F, -1.2741F);
		Clock4.setTextureOffset(0, 104).addBox(-10.7251F, -3.6751F, -15.1032F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		Supports5 = new ModelRenderer(this);
		Supports5.setRotationPoint(2.0F, -14.9571F, -12.0548F);
		Clock4.addChild(Supports5);
		

		Clock5 = new ModelRenderer(this);
		Clock5.setRotationPoint(-12.66F, 12.2606F, 7.0962F);
		HorseModel.addChild(Clock5);
		setRotationAngle(Clock5, -1.2392F, -1.6581F, 1.2741F);
		Clock5.setTextureOffset(0, 104).addBox(-2.6233F, -11.8233F, -15.9326F, 4.0F, 6.0F, 1.0F, 0.0F, true);

		Supports6 = new ModelRenderer(this);
		Supports6.setRotationPoint(-11.3484F, -23.1054F, -12.8842F);
		Clock5.addChild(Supports6);
		
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Head.render(matrixStack, buffer, packedLight, packedOverlay);
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		Horse.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}