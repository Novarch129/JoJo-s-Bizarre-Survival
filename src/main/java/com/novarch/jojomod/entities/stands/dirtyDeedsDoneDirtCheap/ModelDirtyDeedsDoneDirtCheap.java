package com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelDirtyDeedsDoneDirtCheap <T extends EntityDirtyDeedsDoneDirtCheap> extends EntityModel<T>
{
    private final ModelRenderer Head;
    private final ModelRenderer Ear1;
    private final ModelRenderer Ear2;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer Legs;
    private final ModelRenderer Feet;
    private final ModelRenderer bone2;
    private final ModelRenderer bone3;
    private final ModelRenderer Crotch;
    private final ModelRenderer bone;
    private final ModelRenderer Foot1;
    private final ModelRenderer Foot2;


    public ModelDirtyDeedsDoneDirtCheap() {
        textureWidth = 128;
        textureHeight = 128;

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 24.0F, 0.0F);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -34.5F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Ear1 = new ModelRenderer(this);
        Ear1.setRotationPoint(-7.0F, 10.0F, 0.0F);
        Ear1.setTextureOffset(32, 1).addBox(4.0F, -27.5F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

        Ear2 = new ModelRenderer(this);
        Ear2.setRotationPoint(5.0F, 0.0F, 2.0F);
        Ear1.addChild(Ear2);
        Ear2.setTextureOffset(32, 1).addBox(3.0F, -27.5F, -4.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-16.9521F, 7.9395F, -2.4439F);
        setRotationAngle(RightArm, -0.2618F, 0.0F, 0.3491F);
        RightArm.setTextureOffset(67, 73).addBox(4.4341F, -10.4344F, -2.272F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(16.9521F, 7.9395F, 0.0561F);
        setRotationAngle(LeftArm, -0.2618F, 0.0F, -0.3491F);
        LeftArm.setTextureOffset(67, 73).addBox(-8.4106F, -9.8438F, -4.4431F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 24.0F, 0.0F);
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

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(0.0F, 21.0F, -1.5F);
        setRotationAngle(bone2, -0.3229F, 0.0F, 0.0F);
        bone2.setTextureOffset(16, 16).addBox(-5.5F, -22.4406F, -8.0047F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 23.9575F, 0.0F);
        setRotationAngle(bone3, 0.0091F, 0.0F, 0.0F);
        bone3.setTextureOffset(18, 28).addBox(-3.5F, -22.9992F, -1.8309F, 7.0F, 8.0F, 4.0F, 0.0F, false);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 29.0F, 0.0F);
        setRotationAngle(Crotch, -0.0349F, 0.0F, 0.0F);
        Crotch.setTextureOffset(36, 79).addBox(-4.5F, -22.4875F, -3.7154F, 9.0F, 4.0F, 6.0F, 0.0F, false);

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 20.5F, 0.5F);
        setRotationAngle(bone, -0.2443F, 0.0F, 0.0F);
        bone.setTextureOffset(36, 91).addBox(-10.0F, -22.3911F, -7.9594F, 20.0F, 5.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(EntityDirtyDeedsDoneDirtCheap entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Head.render(matrixStack, buffer, packedLight, packedOverlay);
        Ear1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        Legs.render(matrixStack, buffer, packedLight, packedOverlay);
        bone2.render(matrixStack, buffer, packedLight, packedOverlay);
        bone3.render(matrixStack, buffer, packedLight, packedOverlay);
        Crotch.render(matrixStack, buffer, packedLight, packedOverlay);
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
