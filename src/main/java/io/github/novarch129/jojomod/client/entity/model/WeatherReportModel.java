package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.WeatherReportEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class WeatherReportModel extends EntityModel<WeatherReportEntity> {
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer Mouth;
    private final ModelRenderer Spikes;
    private final ModelRenderer Spike1;
    private final ModelRenderer Spike4;
    private final ModelRenderer Spike5;
    private final ModelRenderer Spike2;
    private final ModelRenderer Spike3;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer RightArm2;
    private final ModelRenderer RightHand2;
    private final ModelRenderer Crotch;
    private final ModelRenderer Circle;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;

    public WeatherReportModel() {
        textureWidth = 128;
        textureHeight = 128;

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -4.0F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -10.2F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.2F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

        Mouth = new ModelRenderer(this);
        Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Mouth);
        Mouth.setTextureOffset(34, 0).addBox(-1.0F, -5.25F, -4.25F, 2.0F, 3.0F, 1.0F, 0.0F, false);

        Spikes = new ModelRenderer(this);
        Spikes.setRotationPoint(0.0F, 0.0F, 0.0F);
        Head.addChild(Spikes);


        Spike1 = new ModelRenderer(this);
        Spike1.setRotationPoint(0.0F, 0.0F, 0.0F);
        Spikes.addChild(Spike1);
        Spike1.setTextureOffset(47, 0).addBox(-0.5F, -12.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Spike4 = new ModelRenderer(this);
        Spike4.setRotationPoint(0.125F, -1.6F, -3.075F);
        Spikes.addChild(Spike4);
        setRotationAngle(Spike4, -0.6327F, 0.4494F, 0.0F);
        Spike4.setTextureOffset(47, 0).addBox(-0.5F, -12.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Spike5 = new ModelRenderer(this);
        Spike5.setRotationPoint(-0.125F, -1.6F, -3.075F);
        Spikes.addChild(Spike5);
        setRotationAngle(Spike5, -0.6327F, -0.4494F, 0.0F);
        Spike5.setTextureOffset(47, 0).addBox(-0.5F, -12.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        Spike2 = new ModelRenderer(this);
        Spike2.setRotationPoint(4.775F, -10.225F, -0.5F);
        Spikes.addChild(Spike2);
        setRotationAngle(Spike2, 0.0F, 0.0F, 0.7679F);
        Spike2.setTextureOffset(47, 0).addBox(-0.6737F, -0.6798F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Spike3 = new ModelRenderer(this);
        Spike3.setRotationPoint(-4.775F, -10.225F, -0.5F);
        Spikes.addChild(Spike3);
        setRotationAngle(Spike3, 0.0F, 0.0F, -0.7679F);
        Spike3.setTextureOffset(47, 0).addBox(-0.3263F, -0.6798F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 24.0F, 0.0F);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);

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

        RightArm2 = new ModelRenderer(this);
        RightArm2.setRotationPoint(8.3F, -26.9765F, -1.3061F);
        Arms.addChild(RightArm2);
        setRotationAngle(RightArm2, -0.2618F, 0.0F, -0.2662F);
        RightArm2.setTextureOffset(27, 32).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        RightHand2 = new ModelRenderer(this);
        RightHand2.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        RightArm2.addChild(RightHand2);
        setRotationAngle(RightHand2, -0.1134F, 0.0F, 0.3272F);
        RightHand2.setTextureOffset(0, 53).addBox(-1.2528F, 0.1022F, -2.4024F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        Circle = new ModelRenderer(this);
        Circle.setRotationPoint(0.0F, 0.0F, 0.0F);
        Crotch.addChild(Circle);
        setRotationAngle(Circle, -0.0873F, 0.0F, 0.0F);


        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
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
    public void setRotationAngles(WeatherReportEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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