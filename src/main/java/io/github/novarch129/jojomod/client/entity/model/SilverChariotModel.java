package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.SilverChariotEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SilverChariotModel<T extends SilverChariotEntity> extends EntityModel<T> {
	public final ModelRenderer HeadBase;
	public final ModelRenderer Head;
	public final ModelRenderer Eyes;
	public final ModelRenderer Helmet;
	public final ModelRenderer Removable;
	public final ModelRenderer NoArmor;
	public final ModelRenderer Ears;
	public final ModelRenderer BodyBase;
	public final ModelRenderer Torso;
	public final ModelRenderer ChestPads;
	public final ModelRenderer ShoulderPads;
	public final ModelRenderer ShoulderPad1;
	public final ModelRenderer Spikes;
	public final ModelRenderer Spike;
	public final ModelRenderer Spike2;
	public final ModelRenderer Spike3;
	public final ModelRenderer Spike4;
	public final ModelRenderer Spike5;
	public final ModelRenderer ShoulderPad2;
	public final ModelRenderer Spikes2;
	public final ModelRenderer Spike6;
	public final ModelRenderer Spike7;
	public final ModelRenderer Spike8;
	public final ModelRenderer Spike9;
	public final ModelRenderer Spike10;
	public final ModelRenderer Chest;
	public final ModelRenderer NoArmorChest;
	public final ModelRenderer Abs;
	public final ModelRenderer Pipes;
	public final ModelRenderer Pipe1;
	public final ModelRenderer Pipe3;
	public final ModelRenderer Pipe2;
	public final ModelRenderer Pipe4;
	public final ModelRenderer Arms;
	public final ModelRenderer RightArm;
	public final ModelRenderer RightHand;
	public final ModelRenderer Sword;
	public final ModelRenderer RightArm2;
	public final ModelRenderer RightHand2;
	public final ModelRenderer Crotch;
	public final ModelRenderer Circle;
	public final ModelRenderer Legs;
	public final ModelRenderer RightLeg;
	public final ModelRenderer RightFoot;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer LeftFoot;

	public SilverChariotModel() {
		textureWidth = 128;
		textureHeight = 128;

		HeadBase = new ModelRenderer(this);
		HeadBase.setRotationPoint(0.0F, -5.0F, 0.0F);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		HeadBase.addChild(Head);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -11.2F, -4.0F, 8.0F, 9.0F, 8.0F, 0.0F, false);

		Eyes = new ModelRenderer(this);
		Eyes.setRotationPoint(0.0F, 0.0F, -0.25F);
		Head.addChild(Eyes);
		Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
		Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Helmet);
		Helmet.setTextureOffset(65, 90).addBox(-4.675F, -11.275F, -5.25F, 1.0F, 6.0F, 4.0F, 0.0F, true);
		Helmet.setTextureOffset(45, 98).addBox(-4.0F, -11.375F, -5.2F, 3.0F, 3.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(45, 98).addBox(1.0F, -11.375F, -5.2F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(65, 90).addBox(3.675F, -11.275F, -5.25F, 1.0F, 6.0F, 4.0F, 0.0F, false);
		Helmet.setTextureOffset(81, 87).addBox(-4.0F, -11.125F, -5.25F, 8.0F, 4.0F, 2.0F, 0.0F, false);
		Helmet.setTextureOffset(24, 106).addBox(-1.0F, -8.025F, -5.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(24, 106).addBox(3.0F, -7.275F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(24, 106).addBox(-4.0F, -7.275F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Helmet.setTextureOffset(24, 106).addBox(-4.0F, -6.275F, -5.25F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(2, 108).addBox(-3.5F, -4.275F, -5.25F, 7.0F, 2.0F, 1.0F, 0.0F, false);
		Helmet.setTextureOffset(2, 93).addBox(-4.675F, -5.275F, -5.25F, 9.0F, 1.0F, 4.0F, 0.0F, false);
		Helmet.setTextureOffset(2, 99).addBox(3.675F, -5.275F, -5.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Removable = new ModelRenderer(this);
		Removable.setRotationPoint(-1.325F, 26.925F, 0.75F);
		Helmet.addChild(Removable);
		Removable.setTextureOffset(24, 106).addBox(5.0F, -38.3F, -6.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		Removable.setTextureOffset(103, 70).addBox(2.325F, -38.3F, -5.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
		Removable.setTextureOffset(103, 70).addBox(-2.675F, -38.3F, -5.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
		Removable.setTextureOffset(24, 106).addBox(-3.35F, -38.3F, -6.0F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		Removable.setTextureOffset(37, 96).addBox(2.325F, -38.3F, 3.0F, 3.0F, 7.0F, 1.0F, 0.0F, false);
		Removable.setTextureOffset(37, 96).addBox(1.575F, -35.8F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Removable.setTextureOffset(37, 96).addBox(0.325F, -35.3F, 3.0F, 2.0F, 4.0F, 1.0F, 0.0F, true);
		Removable.setTextureOffset(37, 96).addBox(-0.175F, -35.8F, 3.0F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Removable.setTextureOffset(63, 88).addBox(-3.35F, -38.2F, -2.0F, 1.0F, 6.0F, 6.0F, 0.0F, true);
		Removable.setTextureOffset(63, 88).addBox(5.0F, -38.2F, -2.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
		Removable.setTextureOffset(37, 96).addBox(-2.675F, -38.3F, 3.0F, 3.0F, 7.0F, 1.0F, 0.0F, true);

		NoArmor = new ModelRenderer(this);
		NoArmor.setRotationPoint(0.0F, 0.0F, 0.0F);
		Helmet.addChild(NoArmor);
		NoArmor.setTextureOffset(41, 19).addBox(-4.0F, -11.375F, -5.2F, 8.0F, 3.0F, 4.0F, 0.0F, false);

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(Ears);
		Ears.setTextureOffset(37, 0).addBox(-4.85F, -8.25F, -2.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		Ears.setTextureOffset(37, 0).addBox(3.85F, -8.25F, -2.25F, 1.0F, 3.0F, 3.0F, 0.0F, true);

		BodyBase = new ModelRenderer(this);
		BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);


		Torso = new ModelRenderer(this);
		Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
		BodyBase.addChild(Torso);
		setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
		Torso.setTextureOffset(26, 18).addBox(-10.0F, -5.8469F, -2.8038F, 20.0F, 5.0F, 6.0F, 0.0F, false);

		ChestPads = new ModelRenderer(this);
		ChestPads.setRotationPoint(0.0F, 25.5F, 1.0F);
		Torso.addChild(ChestPads);
		ChestPads.setTextureOffset(111, 0).addBox(-6.25F, -30.5888F, -4.6503F, 6.0F, 5.0F, 1.0F, 0.0F, true);
		ChestPads.setTextureOffset(26, 18).addBox(-6.25F, -25.5888F, -4.6503F, 3.0F, 1.0F, 1.0F, 0.0F, true);
		ChestPads.setTextureOffset(111, 0).addBox(0.5F, -30.5888F, -4.6503F, 6.0F, 5.0F, 1.0F, 0.0F, false);
		ChestPads.setTextureOffset(26, 18).addBox(3.5F, -25.5888F, -4.6503F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ChestPads.setTextureOffset(111, 0).addBox(-6.25F, -31.3388F, 2.1665F, 6.0F, 5.0F, 1.0F, 0.0F, true);
		ChestPads.setTextureOffset(111, 0).addBox(0.25F, -31.3388F, 2.1665F, 6.0F, 5.0F, 1.0F, 0.0F, false);
		ChestPads.setTextureOffset(26, 18).addBox(3.25F, -26.3388F, 2.1665F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		ChestPads.setTextureOffset(26, 18).addBox(-6.25F, -26.3388F, 2.1665F, 3.0F, 1.0F, 1.0F, 0.0F, true);

		ShoulderPads = new ModelRenderer(this);
		ShoulderPads.setRotationPoint(0.0F, 0.0F, 0.0F);
		Torso.addChild(ShoulderPads);


		ShoulderPad1 = new ModelRenderer(this);
		ShoulderPad1.setRotationPoint(11.1206F, -1.2485F, 0.6555F);
		ShoulderPads.addChild(ShoulderPad1);
		setRotationAngle(ShoulderPad1, 0.0F, 0.0F, -0.1265F);
		ShoulderPad1.setTextureOffset(113, 13).addBox(-0.8775F, -5.4625F, -3.2419F, 1.0F, 5.0F, 6.0F, 0.0F, false);
		ShoulderPad1.setTextureOffset(98, 11).addBox(-0.6275F, -4.2125F, -1.7419F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		ShoulderPad1.setTextureOffset(115, 13).addBox(-0.8775F, -0.4625F, -1.7419F, 1.0F, 3.0F, 3.0F, 0.0F, false);

		Spikes = new ModelRenderer(this);
		Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderPad1.addChild(Spikes);


		Spike = new ModelRenderer(this);
		Spike.setRotationPoint(-0.3918F, -8.7548F, 2.1824F);
		Spikes.addChild(Spike);
		Spike.setTextureOffset(115, 13).addBox(-0.4858F, 0.5422F, -2.9243F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Spike2 = new ModelRenderer(this);
		Spike2.setRotationPoint(-0.4777F, -4.9613F, 3.4419F);
		Spikes.addChild(Spike2);
		setRotationAngle(Spike2, -0.7854F, 0.0F, 0.0F);
		Spike2.setTextureOffset(115, 13).addBox(-0.3775F, -1.7596F, -1.3517F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Spike3 = new ModelRenderer(this);
		Spike3.setRotationPoint(-0.4554F, -4.9194F, -3.3098F);
		Spikes.addChild(Spike3);
		setRotationAngle(Spike3, 0.7854F, 0.0F, 0.0F);
		Spike3.setTextureOffset(115, 13).addBox(-0.3775F, -2.1017F, 0.0096F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Spike4 = new ModelRenderer(this);
		Spike4.setRotationPoint(-0.5521F, -1.8522F, -4.2437F);
		Spikes.addChild(Spike4);
		setRotationAngle(Spike4, 1.5708F, 0.0F, 0.0F);
		Spike4.setTextureOffset(115, 13).addBox(-0.3775F, -1.4919F, 0.4625F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Spike5 = new ModelRenderer(this);
		Spike5.setRotationPoint(-0.5521F, -2.0336F, 4.205F);
		Spikes.addChild(Spike5);
		setRotationAngle(Spike5, -1.5708F, 0.0F, 0.0F);
		Spike5.setTextureOffset(115, 13).addBox(-0.3275F, -1.0081F, -1.4625F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		ShoulderPad2 = new ModelRenderer(this);
		ShoulderPad2.setRotationPoint(-11.1206F, -1.2485F, 0.6555F);
		ShoulderPads.addChild(ShoulderPad2);
		setRotationAngle(ShoulderPad2, 0.0F, 0.0F, 0.1265F);
		ShoulderPad2.setTextureOffset(113, 13).addBox(-0.1225F, -5.4625F, -3.2419F, 1.0F, 5.0F, 6.0F, 0.0F, true);
		ShoulderPad2.setTextureOffset(98, 11).addBox(-0.3725F, -4.2125F, -1.7419F, 1.0F, 3.0F, 3.0F, 0.0F, true);
		ShoulderPad2.setTextureOffset(115, 13).addBox(-0.1225F, -0.4625F, -1.7419F, 1.0F, 3.0F, 3.0F, 0.0F, true);

		Spikes2 = new ModelRenderer(this);
		Spikes2.setRotationPoint(0.0F, 0.0F, 0.0F);
		ShoulderPad2.addChild(Spikes2);


		Spike6 = new ModelRenderer(this);
		Spike6.setRotationPoint(0.3918F, -8.7548F, 2.1824F);
		Spikes2.addChild(Spike6);
		Spike6.setTextureOffset(115, 13).addBox(-0.5142F, 0.5422F, -2.9243F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Spike7 = new ModelRenderer(this);
		Spike7.setRotationPoint(0.4777F, -4.9613F, 3.4419F);
		Spikes2.addChild(Spike7);
		setRotationAngle(Spike7, -0.7854F, 0.0F, 0.0F);
		Spike7.setTextureOffset(115, 13).addBox(-0.6225F, -1.7596F, -1.3517F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Spike8 = new ModelRenderer(this);
		Spike8.setRotationPoint(0.4554F, -4.9194F, -3.3098F);
		Spikes2.addChild(Spike8);
		setRotationAngle(Spike8, 0.7854F, 0.0F, 0.0F);
		Spike8.setTextureOffset(115, 13).addBox(-0.6225F, -2.1017F, 0.0096F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Spike9 = new ModelRenderer(this);
		Spike9.setRotationPoint(0.5521F, -1.8522F, -4.2437F);
		Spikes2.addChild(Spike9);
		setRotationAngle(Spike9, 1.5708F, 0.0F, 0.0F);
		Spike9.setTextureOffset(115, 13).addBox(-0.6225F, -1.4919F, 0.4625F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Spike10 = new ModelRenderer(this);
		Spike10.setRotationPoint(0.5521F, -2.0336F, 4.205F);
		Spikes2.addChild(Spike10);
		setRotationAngle(Spike10, -1.5708F, 0.0F, 0.0F);
		Spike10.setTextureOffset(115, 13).addBox(-0.6725F, -1.0081F, -1.4625F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Chest = new ModelRenderer(this);
		Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
		BodyBase.addChild(Chest);
		setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
		Chest.setTextureOffset(45, 31).addBox(-5.5F, -26.7153F, -10.0414F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		NoArmorChest = new ModelRenderer(this);
		NoArmorChest.setRotationPoint(0.0F, 0.0F, 0.0F);
		Chest.addChild(NoArmorChest);
		NoArmorChest.setTextureOffset(96, 57).addBox(-5.5F, -26.398F, -10.9897F, 11.0F, 6.0F, 5.0F, 0.0F, false);

		Abs = new ModelRenderer(this);
		Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
		BodyBase.addChild(Abs);
		setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
		Abs.setTextureOffset(0, 39).addBox(-6.5F, -12.2058F, -0.6383F, 7.0F, 5.0F, 4.0F, 0.0F, false);
		Abs.setTextureOffset(0, 69).addBox(-4.5F, -7.9515F, -0.1579F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		Pipes = new ModelRenderer(this);
		Pipes.setRotationPoint(0.0F, 0.0F, 0.5F);
		Abs.addChild(Pipes);


		Pipe1 = new ModelRenderer(this);
		Pipe1.setRotationPoint(-1.0F, -6.4515F, 1.3421F);
		Pipes.addChild(Pipe1);
		setRotationAngle(Pipe1, -0.1745F, 0.0F, -0.6109F);
		Pipe1.setTextureOffset(66, 70).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Pipe3 = new ModelRenderer(this);
		Pipe3.setRotationPoint(-5.0F, -6.4515F, 1.3421F);
		Pipes.addChild(Pipe3);
		setRotationAngle(Pipe3, -0.1745F, 0.0F, 0.6109F);
		Pipe3.setTextureOffset(66, 70).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		Pipe2 = new ModelRenderer(this);
		Pipe2.setRotationPoint(-0.4351F, -5.6433F, 1.1755F);
		Pipes.addChild(Pipe2);
		setRotationAngle(Pipe2, -0.6981F, 0.0F, -0.6109F);
		Pipe2.setTextureOffset(66, 70).addBox(-0.4992F, -0.6794F, -0.8231F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		Pipe4 = new ModelRenderer(this);
		Pipe4.setRotationPoint(-5.5649F, -5.6433F, 1.1755F);
		Pipes.addChild(Pipe4);
		setRotationAngle(Pipe4, -0.6981F, 0.0F, 0.6109F);
		Pipe4.setTextureOffset(66, 70).addBox(-0.5008F, -0.6794F, -0.8231F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		Arms = new ModelRenderer(this);
		Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Arms);


		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-7.15F, -28.3015F, -1.3061F);
		Arms.addChild(RightArm);
		setRotationAngle(RightArm, -0.4363F, 0.0F, 0.2618F);
		RightArm.setTextureOffset(27, 32).addBox(-1.9824F, 0.7955F, -0.983F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		RightHand = new ModelRenderer(this);
		RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
		RightArm.addChild(RightHand);
		setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
		RightHand.setTextureOffset(0, 53).addBox(-2.8117F, -0.4396F, -2.8423F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		Sword = new ModelRenderer(this);
		Sword.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightHand.addChild(Sword);
		Sword.setTextureOffset(110, 99).addBox(-1.4016F, 3.3281F, -4.6066F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		Sword.setTextureOffset(92, 110).addBox(-1.4016F, 3.3281F, -6.6066F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Sword.setTextureOffset(92, 110).addBox(-1.4016F, 3.3281F, -23.6066F, 1.0F, 1.0F, 17.0F, 0.0F, false);
		Sword.setTextureOffset(82, 109).addBox(-2.4016F, 2.3281F, -5.6066F, 3.0F, 3.0F, 1.0F, 0.0F, false);

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
		Crotch.setTextureOffset(32, 68).addBox(-4.5F, -18.9558F, -4.347F, 9.0F, 2.0F, 5.0F, 0.0F, false);
		Crotch.setTextureOffset(32, 68).addBox(-3.5F, -16.9558F, -4.347F, 7.0F, 2.0F, 5.0F, 0.0F, false);

		Circle = new ModelRenderer(this);
		Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
		Crotch.addChild(Circle);
		setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);


		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);


		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.75F, -14.2F, -2.2125F);
		Legs.addChild(RightLeg);
		setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.1876F);
		RightLeg.setTextureOffset(80, 24).addBox(-2.3299F, -1.5715F, -1.5112F, 4.0F, 9.0F, 4.0F, 0.0F, true);

		RightFoot = new ModelRenderer(this);
		RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
		RightLeg.addChild(RightFoot);
		setRotationAngle(RightFoot, 0.3971F, 0.0F, 0.0F);
		RightFoot.setTextureOffset(59, 46).addBox(-2.4699F, -7.7447F, -0.6633F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(2.275F, -14.25F, -2.7375F);
		Legs.addChild(LeftLeg);
		setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
		LeftLeg.setTextureOffset(80, 24).addBox(-2.0771F, -2.0776F, -1.313F, 4.0F, 9.0F, 4.0F, 0.0F, false);

		LeftFoot = new ModelRenderer(this);
		LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
		LeftLeg.addChild(LeftFoot);
		setRotationAngle(LeftFoot, 0.7549F, 0.0F, 0.0873F);
		LeftFoot.setTextureOffset(80, 41).addBox(-2.1776F, -5.7238F, 2.2021F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		if(!entityIn.hasArmor()) {
			Removable.showModel = false;
			ChestPads.showModel = false;
			ShoulderPads.showModel = false;
			NoArmor.showModel = true;
			NoArmorChest.showModel = true;
		} else {
			Removable.showModel = true;
			ChestPads.showModel = true;
			ShoulderPads.showModel = true;
			NoArmor.showModel = false;
			NoArmorChest.showModel = false;
		}
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