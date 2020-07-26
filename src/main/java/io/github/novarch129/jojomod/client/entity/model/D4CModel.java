package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.D4CEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class D4CModel extends AbstractStandModel<D4CEntity> {
    private final ModelRenderer D4C;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Ear1;
    private final ModelRenderer Ear2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer Legs;
    private final ModelRenderer Feet;
    private final ModelRenderer Foot1;
    private final ModelRenderer Foot2;
    private final ModelRenderer Chest;
    private final ModelRenderer Torso;
    private final ModelRenderer Abs;
    private final ModelRenderer Crotch;

    public D4CModel() {
        textureWidth = 128;
        textureHeight = 128;

        D4C = new ModelRenderer(this);
        D4C.setRotationPoint(0.0F, 20.5F, 0.5F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -23.1667F, -0.5F);
        D4C.addChild(HeadBase);
        HeadBase.setTextureOffset(0, 0).addBox(-4.0F, -7.8333F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Ear1 = new ModelRenderer(this);
        Ear1.setRotationPoint(-7.0F, 12.6667F, 0.0F);
        HeadBase.addChild(Ear1);
        Ear1.setTextureOffset(32, 1).addBox(4.0F, -27.5F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

        Ear2 = new ModelRenderer(this);
        Ear2.setRotationPoint(-2.0F, 12.6667F, 2.0F);
        HeadBase.addChild(Ear2);
        Ear2.setTextureOffset(32, 1).addBox(3.0F, -27.5F, -4.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 3.5F, -0.5F);
        D4C.addChild(BodyBase);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-16.9521F, -16.0605F, -2.4439F);
        BodyBase.addChild(RightArm);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.3491F);
        RightArm.setTextureOffset(67, 73).addBox(4.4341F, -10.4344F, -2.272F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(16.9521F, -16.0605F, 0.0561F);
        BodyBase.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.3491F);
        LeftArm.setTextureOffset(67, 73).addBox(-8.4106F, -9.8438F, -4.4431F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);
        Legs.setTextureOffset(53, 103).addBox(-4.35F, -16.6048F, -2.2967F, 4.0F, 9.0F, 4.0F, 0.0F, false);
        Legs.setTextureOffset(53, 103).addBox(0.325F, -16.6048F, -2.2967F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        Feet = new ModelRenderer(this);
        Feet.setRotationPoint(-0.0125F, -4.2437F, 0.6746F);
        Legs.addChild(Feet);
        setRotationAngle(Feet, 0.2618F, 0.0F, 0.0F);


        Foot1 = new ModelRenderer(this);
        Foot1.setRotationPoint(0.0F, 0.0F, 0.0F);
        Feet.addChild(Foot1);
        Foot1.setTextureOffset(36, 103).addBox(-4.3375F, -4.5647F, -2.2415F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        Foot2 = new ModelRenderer(this);
        Foot2.setRotationPoint(0.0F, 0.0F, 0.0F);
        Feet.addChild(Foot2);
        setRotationAngle(Foot2, 0.2618F, 0.0F, 0.0F);
        Foot2.setTextureOffset(36, 103).addBox(0.3375F, -4.075F, -1.2639F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(16, 16).addBox(-5.5F, -22.4406F, -8.0047F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -3.5F, 0.5F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(36, 91).addBox(-10.0F, -22.3911F, -7.9594F, 20.0F, 5.0F, 6.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(0.0F, -0.0425F, 0.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0091F, 0.0F, 0.0F);
        Abs.setTextureOffset(18, 28).addBox(-3.5F, -22.9992F, -1.8309F, 7.0F, 8.0F, 4.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 5.0F, 0.0F);
        BodyBase.addChild(Crotch);
        setRotationAngle(Crotch, -0.0349F, 0.0F, 0.0F);
        Crotch.setTextureOffset(36, 79).addBox(-4.5F, -22.4875F, -3.7154F, 9.0F, 4.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        D4C.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected ModelRenderer getHead() {
        return HeadBase;
    }
}