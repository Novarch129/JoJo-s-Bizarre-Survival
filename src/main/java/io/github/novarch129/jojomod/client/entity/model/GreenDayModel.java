package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.GreenDayEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GreenDayModel extends AbstractStandModel<GreenDayEntity> {
    private final ModelRenderer GreenDay;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Flower;
    private final ModelRenderer Mouth;
    private final ModelRenderer Eyes;
    private final ModelRenderer Spikes;
    private final ModelRenderer LowerSpikes;
    private final ModelRenderer Spike1;
    private final ModelRenderer Spike2;
    private final ModelRenderer Spike3;
    private final ModelRenderer Spike4;
    private final ModelRenderer TopSpikes;
    private final ModelRenderer TopSpike1;
    private final ModelRenderer TopSpike6;
    private final ModelRenderer TopSpike2;
    private final ModelRenderer TopSpike5;
    private final ModelRenderer TopSpike3;
    private final ModelRenderer TopSpike7;
    private final ModelRenderer TopSpike8;
    private final ModelRenderer TopSpike4;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Pipes;
    private final ModelRenderer Pipe1;
    private final ModelRenderer Pipe3;
    private final ModelRenderer Pipe2;
    private final ModelRenderer Pipe4;
    private final ModelRenderer SmallPipes;
    private final ModelRenderer SmallPipe1;
    private final ModelRenderer SmallPipe2;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;

    public GreenDayModel() {
        textureWidth = 128;
        textureHeight = 128;

        GreenDay = new ModelRenderer(this);
        GreenDay.setRotationPoint(0.0F, 24.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -30.5F, 0.0F);
        GreenDay.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -6.7F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

        Flower = new ModelRenderer(this);
        Flower.setRotationPoint(0.0F, 0.25F, 0.0F);
        Head.addChild(Flower);
        Flower.setTextureOffset(94, 0).addBox(-2.7F, -9.7F, -2.725F, 5.0F, 3.0F, 5.0F, 0.0F, false);
        Flower.setTextureOffset(111, 20).addBox(-2.2F, -10.45F, -2.225F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        Flower.setTextureOffset(101, 29).addBox(-2.3F, -9.7F, -2.725F, 5.0F, 3.0F, 5.0F, 0.0F, true);
        Flower.setTextureOffset(111, 20).addBox(-1.8F, -10.45F, -2.225F, 4.0F, 1.0F, 4.0F, 0.0F, true);
        Flower.setTextureOffset(101, 29).addBox(-2.7F, -9.7F, -2.275F, 5.0F, 3.0F, 5.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.475F, -9.7F, 2.725F, 1.0F, 3.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -9.7F, 2.725F, 1.0F, 3.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.7F, -9.7F, 0.475F, 0.0F, 3.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(2.725F, -9.7F, 0.475F, 0.0F, 3.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(-2.7F, -9.7F, -1.475F, 0.0F, 3.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(2.725F, -9.7F, -1.475F, 0.0F, 3.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -9.7F, -2.75F, 1.0F, 3.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -9.7F, -2.75F, 1.0F, 3.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -10.45F, -2.25F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.2F, -10.45F, 0.475F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(2.225F, -10.45F, 0.475F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(-2.2F, -10.45F, -1.475F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(2.225F, -10.45F, -1.475F, 0.0F, 1.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -9.7F, -2.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -9.7F, 1.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -9.7F, 1.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -10.45F, 2.25F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -10.45F, -2.25F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -9.7F, -2.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -10.45F, -2.25F, 1.0F, 0.0F, 4.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -10.45F, -2.25F, 1.0F, 0.0F, 4.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -10.45F, 1.25F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.45F, -10.45F, 1.25F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -9.7F, 1.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(1.725F, -9.7F, 0.475F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.725F, -9.7F, 0.475F, 1.0F, 0.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(1.725F, -9.7F, -1.475F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.775F, -10.45F, -1.475F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-1.775F, -10.45F, 0.475F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.175F, -10.45F, -1.475F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.175F, -10.45F, 0.475F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(-2.725F, -9.7F, -1.475F, 1.0F, 0.0F, 1.0F, 0.0F, true);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -9.7F, 1.75F, 1.0F, 0.0F, 1.0F, 0.0F, false);
        Flower.setTextureOffset(103, 51).addBox(0.5F, -10.45F, 2.25F, 1.0F, 1.0F, 0.0F, 0.0F, false);
        Flower.setTextureOffset(111, 20).addBox(-2.2F, -10.45F, -1.775F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        Flower.setTextureOffset(94, 0).addBox(-2.3F, -9.7F, -2.275F, 5.0F, 3.0F, 5.0F, 0.0F, true);
        Flower.setTextureOffset(111, 20).addBox(-1.8F, -10.45F, -1.775F, 4.0F, 1.0F, 4.0F, 0.0F, true);

        Mouth = new ModelRenderer(this);
        Mouth.setRotationPoint(0.0F, 2.5F, 0.0F);
        Head.addChild(Mouth);
        Mouth.setTextureOffset(28, 0).addBox(-1.0F, -4.45F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Mouth.setTextureOffset(28, 0).addBox(0.25F, -4.45F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Mouth.setTextureOffset(28, 0).addBox(-1.25F, -4.45F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Mouth.setTextureOffset(28, 0).addBox(0.4F, -4.3F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Mouth.setTextureOffset(28, 0).addBox(-1.4F, -4.3F, -4.1F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 2.5F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.45F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.45F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Spikes = new ModelRenderer(this);
        Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Spikes);

        LowerSpikes = new ModelRenderer(this);
        LowerSpikes.setRotationPoint(-0.5F, 27.5F, 0.5F);
        Spikes.addChild(LowerSpikes);

        Spike1 = new ModelRenderer(this);
        Spike1.setRotationPoint(0.0F, 0.0F, 0.0F);
        LowerSpikes.addChild(Spike1);
        Spike1.setTextureOffset(120, 2).addBox(-2.0F, -35.2F, -4.425F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Spike1.setTextureOffset(120, 2).addBox(0.0F, -35.2F, -4.425F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Spike1.setTextureOffset(120, 2).addBox(2.0F, -35.2F, -4.425F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Spike2 = new ModelRenderer(this);
        Spike2.setRotationPoint(0.0F, 0.0F, 0.0F);
        LowerSpikes.addChild(Spike2);
        Spike2.setTextureOffset(120, 2).addBox(0.0F, -35.2F, 2.425F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Spike2.setTextureOffset(120, 2).addBox(2.0F, -35.2F, 2.425F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Spike2.setTextureOffset(120, 2).addBox(-2.0F, -35.2F, 2.425F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Spike3 = new ModelRenderer(this);
        Spike3.setRotationPoint(-3.5F, 0.0F, -3.5F);
        LowerSpikes.addChild(Spike3);
        Spike3.setTextureOffset(120, 2).addBox(0.1F, -35.2F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Spike3.setTextureOffset(120, 2).addBox(0.1F, -35.2F, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Spike3.setTextureOffset(120, 2).addBox(0.1F, -35.2F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Spike4 = new ModelRenderer(this);
        Spike4.setRotationPoint(4.5F, 0.0F, -3.5F);
        LowerSpikes.addChild(Spike4);
        Spike4.setTextureOffset(120, 2).addBox(-1.1F, -35.2F, 2.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Spike4.setTextureOffset(120, 2).addBox(-1.1F, -35.2F, 0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Spike4.setTextureOffset(120, 2).addBox(-1.1F, -35.2F, 4.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        TopSpikes = new ModelRenderer(this);
        TopSpikes.setRotationPoint(-0.5F, 27.5F, 0.5F);
        Spikes.addChild(TopSpikes);

        TopSpike1 = new ModelRenderer(this);
        TopSpike1.setRotationPoint(3.4F, -36.5F, -0.5F);
        TopSpikes.addChild(TopSpike1);
        setRotationAngle(TopSpike1, 0.0F, 0.0F, -0.3927F);
        TopSpike1.setTextureOffset(120, 2).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        TopSpike6 = new ModelRenderer(this);
        TopSpike6.setRotationPoint(-2.4F, -36.5F, -0.5F);
        TopSpikes.addChild(TopSpike6);
        setRotationAngle(TopSpike6, 0.0F, 0.0F, 0.3927F);
        TopSpike6.setTextureOffset(120, 2).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        TopSpike2 = new ModelRenderer(this);
        TopSpike2.setRotationPoint(3.4F, -36.5F, -2.5F);
        TopSpikes.addChild(TopSpike2);
        setRotationAngle(TopSpike2, 0.0F, 0.7854F, -0.3927F);
        TopSpike2.setTextureOffset(120, 2).addBox(-0.7F, -0.475F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        TopSpike5 = new ModelRenderer(this);
        TopSpike5.setRotationPoint(3.4F, -36.5F, 1.5F);
        TopSpikes.addChild(TopSpike5);
        setRotationAngle(TopSpike5, 0.0F, -0.7854F, -0.3927F);
        TopSpike5.setTextureOffset(120, 2).addBox(-0.7F, -0.475F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

        TopSpike3 = new ModelRenderer(this);
        TopSpike3.setRotationPoint(-2.4F, -36.5F, -2.5F);
        TopSpikes.addChild(TopSpike3);
        setRotationAngle(TopSpike3, 0.0F, -0.7854F, 0.3927F);
        TopSpike3.setTextureOffset(120, 2).addBox(-1.3F, -0.475F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        TopSpike7 = new ModelRenderer(this);
        TopSpike7.setRotationPoint(0.5F, -36.55F, -4.45F);
        TopSpikes.addChild(TopSpike7);
        setRotationAngle(TopSpike7, -0.3054F, 0.0F, 0.0F);
        TopSpike7.setTextureOffset(120, 2).addBox(-0.5F, -0.752F, -0.1302F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        TopSpike8 = new ModelRenderer(this);
        TopSpike8.setRotationPoint(0.5F, -36.55F, 3.45F);
        TopSpikes.addChild(TopSpike8);
        setRotationAngle(TopSpike8, 0.3054F, 0.0F, 0.0F);
        TopSpike8.setTextureOffset(120, 2).addBox(-0.5F, -0.752F, -1.8698F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        TopSpike4 = new ModelRenderer(this);
        TopSpike4.setRotationPoint(-2.4F, -36.5F, 1.5F);
        TopSpikes.addChild(TopSpike4);
        setRotationAngle(TopSpike4, 0.0F, 0.7854F, 0.3927F);
        TopSpike4.setTextureOffset(120, 2).addBox(-1.3F, -0.475F, 0.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        GreenDay.addChild(BodyBase);

        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

        Pipes = new ModelRenderer(this);
        Pipes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Torso.addChild(Pipes);

        Pipe1 = new ModelRenderer(this);
        Pipe1.setRotationPoint(8.5F, -5.5731F, -1.4129F);
        Pipes.addChild(Pipe1);
        setRotationAngle(Pipe1, 0.48F, 0.2182F, 0.6109F);
        Pipe1.setTextureOffset(120, 2).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Pipe3 = new ModelRenderer(this);
        Pipe3.setRotationPoint(-8.5F, -5.5731F, -1.4129F);
        Pipes.addChild(Pipe3);
        setRotationAngle(Pipe3, 0.48F, -0.2182F, -0.6109F);
        Pipe3.setTextureOffset(120, 2).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        Pipe2 = new ModelRenderer(this);
        Pipe2.setRotationPoint(8.5F, -5.604F, 2.6065F);
        Pipes.addChild(Pipe2);
        setRotationAngle(Pipe2, -0.48F, -0.2182F, 0.6109F);
        Pipe2.setTextureOffset(120, 2).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Pipe4 = new ModelRenderer(this);
        Pipe4.setRotationPoint(-8.5F, -5.604F, 2.6065F);
        Pipes.addChild(Pipe4);
        setRotationAngle(Pipe4, -0.48F, 0.2182F, -0.6109F);
        Pipe4.setTextureOffset(120, 2).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);

        SmallPipes = new ModelRenderer(this);
        SmallPipes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Pipes.addChild(SmallPipes);

        SmallPipe1 = new ModelRenderer(this);
        SmallPipe1.setRotationPoint(0.5F, 0.0F, 0.0F);
        SmallPipes.addChild(SmallPipe1);
        SmallPipe1.setTextureOffset(120, 2).addBox(-6.0F, -6.0771F, -2.2878F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        SmallPipe1.setTextureOffset(120, 2).addBox(-6.0F, -6.0771F, -0.2878F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        SmallPipe1.setTextureOffset(120, 2).addBox(-6.0F, -6.0771F, 1.7122F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        SmallPipe2 = new ModelRenderer(this);
        SmallPipe2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        SmallPipes.addChild(SmallPipe2);
        SmallPipe2.setTextureOffset(120, 2).addBox(5.0F, -6.0771F, -2.2878F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        SmallPipe2.setTextureOffset(120, 2).addBox(5.0F, -6.0771F, -0.2878F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        SmallPipe2.setTextureOffset(120, 2).addBox(5.0F, -6.0771F, 1.7122F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);

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

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(8.3F, -26.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.2662F);
        LeftArm.setTextureOffset(27, 32).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.3272F);
        LeftHand.setTextureOffset(0, 53).addBox(-1.2528F, 0.1022F, -2.4024F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);

        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.2F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.8334F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(59, 46).addBox(-2.037F, -6.8997F, 2.4154F, 4.0F, 9.0F, 4.0F, 0.0F, false);

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
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        GreenDay.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}