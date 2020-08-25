package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.EchoesAct1Entity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesAct1Model extends AbstractStandModel<EchoesAct1Entity> {
    private final ModelRenderer EchoesAct1;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Abs2;
    private final ModelRenderer Abs3;
    private final ModelRenderer Abs4;
    private final ModelRenderer Abs5;
    private final ModelRenderer Abs6;
    private final ModelRenderer Abs7;
    private final ModelRenderer Abs8;
    private final ModelRenderer Abs9;
    private final ModelRenderer Abs10;
    private final ModelRenderer Arms;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer LeftHand2;
    private final ModelRenderer Crotch;

    public EchoesAct1Model() {
        textureWidth = 128;
        textureHeight = 128;

        EchoesAct1 = new ModelRenderer(this);
        EchoesAct1.setRotationPoint(0.0F, 34.0F, 0.0F);

        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -28.25F, -2.5F);
        EchoesAct1.addChild(HeadBase);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 0.25F, 1.5F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 15).addBox(-3.5F, -7.2F, -4.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
        Head.setTextureOffset(0, 0).addBox(-4.5F, -8.2F, -5.0F, 9.0F, 5.0F, 9.0F, 0.0F, false);
        Head.setTextureOffset(30, 0).addBox(-1.5F, -8.8F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
        Head.setTextureOffset(29, 6).addBox(-0.5F, -9.8F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -3.0F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(44, 0).addBox(-1.0F, -0.2F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(23, 44).addBox(-4.5F, -0.2F, -3.0F, 9.0F, 1.0F, 7.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(0.4F, -0.2F, -4.1F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(-3.4F, -0.2F, -4.1F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        Eyes.setTextureOffset(68, 3).addBox(-1.0F, 0.925F, -5.1F, 2.0F, 2.0F, 2.0F, 0.0F, true);
        Eyes.setTextureOffset(34, 5).addBox(-1.0F, 0.8F, -6.1F, 2.0F, 1.0F, 2.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        EchoesAct1.addChild(BodyBase);
        BodyBase.setTextureOffset(0, 55).addBox(-3.0F, -28.2F, -3.0F, 6.0F, 1.0F, 2.0F, 0.0F, false);

        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, 0.4363F, 0.0F, 0.0F);
        Torso.setTextureOffset(14, 32).addBox(-8.0F, -1.9703F, -2.9845F, 16.0F, 4.0F, 5.0F, 0.0F, false);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, 0.3927F, 0.0F, 0.0F);
        Chest.setTextureOffset(38, 4).addBox(-5.5F, -23.2467F, 5.9735F, 11.0F, 5.0F, 6.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, 5.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.6632F, 0.0F, 0.0F);
        Abs.setTextureOffset(46, 19).addBox(-7.5F, -11.1971F, 0.353F, 9.0F, 4.0F, 4.0F, 0.0F, false);

        Abs2 = new ModelRenderer(this);
        Abs2.setRotationPoint(3.0F, -12.0F, 8.0F);
        BodyBase.addChild(Abs2);
        setRotationAngle(Abs2, 0.7505F, 0.0F, 0.0F);
        Abs2.setTextureOffset(0, 44).addBox(-6.5F, -11.2538F, 0.2866F, 7.0F, 4.0F, 4.0F, 0.0F, false);

        Abs3 = new ModelRenderer(this);
        Abs3.setRotationPoint(3.0F, -13.0F, 15.0F);
        BodyBase.addChild(Abs3);
        setRotationAngle(Abs3, 1.1432F, 0.0F, 0.0F);
        Abs3.setTextureOffset(12, 65).addBox(-4.0F, -12.2538F, 0.2866F, 2.0F, 3.0F, 2.0F, 0.0F, false);

        Abs4 = new ModelRenderer(this);
        Abs4.setRotationPoint(3.0F, -16.0F, 18.0F);
        BodyBase.addChild(Abs4);
        setRotationAngle(Abs4, 1.4922F, 0.0F, 0.0F);
        Abs4.setTextureOffset(20, 54).addBox(-4.5F, -11.2538F, -0.4F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        Abs4.setTextureOffset(0, 61).addBox(-9.5F, -10.3F, 0.6F, 13.0F, 1.0F, 1.0F, 0.0F, false);
        Abs4.setTextureOffset(51, 44).addBox(-0.5F, -10.7F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        Abs4.setTextureOffset(51, 44).addBox(-8.5F, -10.7F, 0.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        Abs5 = new ModelRenderer(this);
        Abs5.setRotationPoint(0.0F, -3.3188F, 13.5551F);
        Abs4.addChild(Abs5);
        setRotationAngle(Abs5, 1.1432F, 0.0F, 0.0F);
        Abs5.setTextureOffset(59, 29).addBox(-4.5F, -12.2538F, -0.7134F, 3.0F, 9.0F, 3.0F, 0.0F, false);
        Abs5.setTextureOffset(0, 39).addBox(-3.5F, -14.4419F, 0.8964F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Abs6 = new ModelRenderer(this);
        Abs6.setRotationPoint(0.0F, -4.3157F, 13.4766F);
        Abs4.addChild(Abs6);
        setRotationAngle(Abs6, 1.7104F, 0.0F, 0.0F);
        Abs6.setTextureOffset(35, 55).addBox(-4.5F, -3.3176F, -0.3329F, 3.0F, 11.0F, 3.0F, 0.0F, false);

        Abs7 = new ModelRenderer(this);
        Abs7.setRotationPoint(2.0F, -6.0975F, 23.3673F);
        Abs4.addChild(Abs7);
        setRotationAngle(Abs7, 2.1467F, 0.0F, 0.0F);
        Abs7.setTextureOffset(50, 56).addBox(-6.0F, -2.3176F, 0.6671F, 2.0F, 10.0F, 2.0F, 0.0F, false);

        Abs8 = new ModelRenderer(this);
        Abs8.setRotationPoint(3.0F, -3.5777F, 29.5843F);
        Abs4.addChild(Abs8);
        setRotationAngle(Abs8, 2.8885F, 0.0F, 0.0F);
        Abs8.setTextureOffset(0, 65).addBox(-7.0F, 6.3568F, 1.6126F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        Abs9 = new ModelRenderer(this);
        Abs9.setRotationPoint(3.0F, -3.5777F, 29.5843F);
        Abs4.addChild(Abs9);
        setRotationAngle(Abs9, 2.9758F, 0.0F, 0.0F);
        Abs9.setTextureOffset(60, 50).addBox(-8.0F, 10.5988F, 0.6423F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        Abs10 = new ModelRenderer(this);
        Abs10.setRotationPoint(4.0F, -3.7347F, 31.5781F);
        Abs4.addChild(Abs10);
        setRotationAngle(Abs10, 2.9758F, 0.0F, 0.0F);
        Abs10.setTextureOffset(53, 29).addBox(-7.5F, 12.0562F, 3.1219F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(8.3F, -26.9765F, -1.3061F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.2662F);
        LeftArm.setTextureOffset(31, 20).addBox(-2.9835F, 0.5455F, -1.05F, 3.0F, 4.0F, 3.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.3272F);
        LeftHand.setTextureOffset(0, 31).addBox(-1.2528F, -1.8978F, -2.4024F, 3.0F, 5.0F, 3.0F, 0.0F, true);

        LeftArm2 = new ModelRenderer(this);
        LeftArm2.setRotationPoint(-8.3F, -26.9765F, -1.3061F);
        Arms.addChild(LeftArm2);
        setRotationAngle(LeftArm2, -0.2618F, 0.0F, 0.2662F);
        LeftArm2.setTextureOffset(31, 20).addBox(-0.0165F, 0.5455F, -1.05F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        LeftHand2 = new ModelRenderer(this);
        LeftHand2.setRotationPoint(1.5624F, 5.6204F, 1.506F);
        LeftArm2.addChild(LeftHand2);
        setRotationAngle(LeftHand2, -0.1134F, 0.0F, -0.3272F);
        LeftHand2.setTextureOffset(0, 31).addBox(-1.7472F, -1.8978F, -2.4024F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        EchoesAct1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return HeadBase;
    }
}