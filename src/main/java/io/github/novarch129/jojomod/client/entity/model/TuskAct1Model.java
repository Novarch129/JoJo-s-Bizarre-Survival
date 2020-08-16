package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TuskAct1Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TuskAct1Model extends AbstractStandModel<TuskAct1Entity> {
    private final ModelRenderer TuskAct1;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer ear;
    private final ModelRenderer ear2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftHand2;
    private final ModelRenderer legthings;
    private final ModelRenderer legthings2;

    public TuskAct1Model() {
        textureWidth = 64;
        textureHeight = 64;

        TuskAct1 = new ModelRenderer(this);
        TuskAct1.setRotationPoint(0.0F, 31.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -25.75F, -1.25F);
        TuskAct1.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, -2.25F, 1.25F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.5F, -4.2F, -5.0F, 7.0F, 8.0F, 5.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -10.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(20, 1).addBox(-3.0F, 8.8F, -5.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(56, 22).addBox(-1.0F, 10.8F, -6.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(56, 22).addBox(-0.5F, 11.3F, -7.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(20, 1).addBox(1.0F, 8.8F, -5.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(28, 0).addBox(1.5F, 8.8F, -5.125F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(28, 0).addBox(-2.5F, 8.8F, -5.125F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(56, 28).addBox(-1.0F, 6.1F, -5.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(3.0F, 8.8F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(3.0F, 6.8F, -3.1F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(-6.0F, 6.8F, -3.1F, 3.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(55, 17).addBox(-5.0F, 8.8F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(55, 17).addBox(3.0F, 10.8F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(-5.0F, 10.8F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(55, 17).addBox(2.0F, 12.8F, -4.8F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(2.0F, 4.2F, -3.8F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(55, 17).addBox(-3.0F, 4.2F, -3.8F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(55, 17).addBox(-3.0F, 12.8F, -4.8F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(0, 0).addBox(1.5F, 9.8F, -5.1F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(-2.5F, 9.8F, -5.1F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        ear = new ModelRenderer(this);
        ear.setRotationPoint(0.0F, -5.0F, 1.0F);
        Head.addChild(ear);
        setRotationAngle(ear, 1.0472F, 0.0F, 0.2182F);
        ear.setTextureOffset(0, 16).addBox(5.0F, -4.2F, -3.1F, 5.0F, 2.0F, 1.0F, 0.0F, false);

        ear2 = new ModelRenderer(this);
        ear2.setRotationPoint(0.0F, -5.0F, 1.0F);
        Head.addChild(ear2);
        setRotationAngle(ear2, 1.0472F, 0.0F, -0.2182F);
        ear2.setTextureOffset(0, 16).addBox(-10.0F, -4.2F, -3.1F, 5.0F, 2.0F, 1.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TuskAct1.addChild(BodyBase);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, 5.5F);
        BodyBase.addChild(Chest);
        Chest.setTextureOffset(36, 3).addBox(-4.5F, -22.8704F, -9.0895F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 39).addBox(-6.5F, -7.1972F, 0.3356F, 7.0F, 6.0F, 4.0F, 0.0F, false);
        Abs.setTextureOffset(9, 22).addBox(-6.0F, -1.1973F, 1.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);
        Abs.setTextureOffset(0, 55).addBox(-5.5F, 1.8026F, 1.0F, 5.0F, 1.0F, 3.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(5.3F, -27.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.6589F);
        LeftArm.setTextureOffset(0, 31).addBox(-4.4392F, 1.9012F, -0.6867F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, -0.0219F);
        LeftHand.setTextureOffset(0, 22).addBox(-2.92F, -0.4659F, -2.0332F, 2.0F, 4.0F, 2.0F, 0.0F, true);

        LeftArm2 = new ModelRenderer(this);
        LeftArm2.setRotationPoint(-5.3F, -27.9765F, -1.3061F);
        Arms.addChild(LeftArm2);
        setRotationAngle(LeftArm2, -0.2618F, 0.0F, 0.6589F);
        LeftArm2.setTextureOffset(0, 31).addBox(2.4392F, 1.9012F, -0.6867F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        LeftHand2 = new ModelRenderer(this);
        LeftHand2.setRotationPoint(1.5624F, 5.6204F, 1.506F);
        LeftArm2.addChild(LeftHand2);
        setRotationAngle(LeftHand2, -0.1134F, 0.0F, 0.0219F);
        LeftHand2.setTextureOffset(0, 22).addBox(0.92F, -0.4659F, -2.0332F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        legthings = new ModelRenderer(this);
        legthings.setRotationPoint(-8.0F, -4.0F, 0.0F);
        TuskAct1.addChild(legthings);
        setRotationAngle(legthings, 0.0F, 0.0F, 0.7418F);
        legthings.setTextureOffset(24, 14).addBox(0.9317F, -15.1829F, -1.1F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        legthings.setTextureOffset(24, 14).addBox(-0.4195F, -16.6574F, -0.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);

        legthings2 = new ModelRenderer(this);
        legthings2.setRotationPoint(8.0F, -5.0F, 0.0F);
        TuskAct1.addChild(legthings2);
        setRotationAngle(legthings2, 0.0F, 0.0F, -0.7418F);
        legthings2.setTextureOffset(24, 14).addBox(-4.6073F, -14.4456F, -1.1F, 3.0F, 1.0F, 1.0F, 0.0F, true);
        legthings2.setTextureOffset(24, 14).addBox(-4.2561F, -15.9201F, -0.1F, 4.0F, 1.0F, 1.0F, 0.0F, true);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TuskAct1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}