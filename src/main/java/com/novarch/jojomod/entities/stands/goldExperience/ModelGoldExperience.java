package com.novarch.jojomod.entities.stands.goldExperience;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelGoldExperience <T extends EntityGoldExperience> extends EntityModel<T>
{    
    private final ModelRenderer BodyBase;
    
    private final ModelRenderer Legs;
       
    private final ModelRenderer Head;
    
    private final ModelRenderer Shape10;
    
    private final ModelRenderer Shape3;
    
    private final ModelRenderer leftleg;
    
    private final ModelRenderer rightleg;
    
    private final ModelRenderer leftarm;
    
    private final ModelRenderer rightarm;
    
    private final ModelRenderer Body;
    
    private final ModelRenderer Shape4;
    
    private final ModelRenderer Shape2;
    
    private final ModelRenderer Shape1;
    
    private final ModelRenderer Shape8;

    private final ModelRenderer Shape9;

    private final ModelRenderer Shape6;

    private final ModelRenderer Hat;
    
    public ModelGoldExperience() 
    {
        textureWidth = 128;
        textureHeight = 128;
        
        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0f, 0.0f, 0.0f);
        
        Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		BodyBase.addChild(Legs);
           
        Head = new ModelRenderer(this, 0, 0);
        Head.setTextureOffset(0, 0).addBox(-4.0f, -11.0f, -4.0f, 8, 8, 8);
        Head.setRotationPoint(0.0f, 0.0f, 0.0f);
        Head.setTextureSize(128, 128);
        setRotationAngle(Head, 0.2617994f, 0.0f, 0.0f);
        BodyBase.addChild(Head);       
        
        Shape10 = new ModelRenderer(this, 36, 103);
        Shape10.addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
        Shape10.setRotationPoint(3.0f, 18.0f, -5.0f);
        Shape10.setTextureSize(128, 128);
        setRotationAngle(Shape10, 0.5759587f, 0.0f, 0.0f);
        BodyBase.addChild(Shape10);

        Shape9 = new ModelRenderer(this, 46, 74);
        Shape9.setTextureOffset(46, 74).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
        Shape9.setRotationPoint(0.0f, -1.0f, -5.0f);
        Shape9.setTextureSize(128, 128);
        Shape9.mirror = true;
        setRotationAngle(Shape9, -0.6320364f, 0.0f, 0.0f);
        Head.addChild(Shape9);

        Shape6 = new ModelRenderer(this, 0, 62);
        Shape6.setTextureOffset(0, 62).addBox(0.0f, 0.0f, 0.0f, 2, 6, 1);
        Shape6.setRotationPoint(-1.0f, -2.0f, -4.0f);
        Shape6.setTextureSize(128, 128);
        Shape6.mirror = true;
        setRotationAngle(Shape6, -0.7634008f, 0.0f, 0.0f);
        Head.addChild(Shape6);

        Hat = new ModelRenderer(this, 48, 0);
        Hat.setTextureOffset(48, 0).addBox(-5.0f, -12.0f, -5.0f, 10, 8, 10);
        Hat.setRotationPoint(0.0f, 0.0f, 0.0f);
        Hat.setTextureSize(128, 128);
        Hat.mirror = true;
        setRotationAngle(Hat, 0.2617994f, 0.0f, 0.0f);
        Head.addChild(Hat);

        Shape3 = new ModelRenderer(this, 36, 103);
        Shape3.addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
        Shape3.setRotationPoint(-3.0f, 18.0f, -2.0f);
        Shape3.setTextureSize(128, 128);
        setRotationAngle(Shape3, 0.2792527f, 0.0f, 0.0f);
        BodyBase.addChild(Shape3);
        
        leftleg = new ModelRenderer(this, 53, 103);
        leftleg.addBox(-1.5f, 0.0f, -2.0f, 4, 9, 4);
        leftleg.setRotationPoint(2.0f, 10.0f, -3.0f);
        leftleg.setTextureSize(128, 128);
        setRotationAngle(leftleg, -0.1570796f, 0.0f, 0.0f);
        Legs.addChild(leftleg);

        rightleg = new ModelRenderer(this, 53, 103);
        rightleg.addBox(-2.5f, 0.0f, -2.0f, 4, 9, 4);
        rightleg.setRotationPoint(-2.0f, 10.0f, -3.0f);
        rightleg.setTextureSize(128, 128);
        setRotationAngle(rightleg, 0.1396263f, 0.0f, 0.0f);
        Legs.addChild(rightleg);
        
        leftarm = new ModelRenderer(this, 67, 73);
        leftarm.addBox(0.0f, 1.0f, -3.0f, 4, 12, 4);
        leftarm.setRotationPoint(5.0f, 0.0f, 0.0f);
        leftarm.setTextureSize(128, 128);
        leftarm.mirror = true;
        setRotationAngle(leftarm, 0.0f, 0.0f, -0.3141593f);
        BodyBase.addChild(leftarm);
        
        rightarm = new ModelRenderer(this, 67, 73);
        rightarm.addBox(-3.0f, 1.0f, -2.0f, 4, 12, 4);
        rightarm.setRotationPoint(-6.0f, 0.0f, 0.0f);
        rightarm.setTextureSize(128, 128);
        setRotationAngle(rightarm, 0.0f, 0.0f, 0.3141593f);
        BodyBase.addChild(rightarm);
        
        Body = new ModelRenderer(this, 16, 16);
        Body.addBox(-5.5f, 0.0f, -3.0f, 11, 6, 5);
        Body.setRotationPoint(0.0f, -0.5f, -1.0f);
        Body.setTextureSize(128, 128);
        setRotationAngle(Body, -0.4770567f, 0.0f, 0.0f);
        BodyBase.addChild(Body);
        
        Shape4 = new ModelRenderer(this, 36, 91);
        Shape4.addBox(-10.0f, 0.0f, 0.0f, 20, 5, 6);
        Shape4.setRotationPoint(0.0f, -2.0f, -4.0f);
        Shape4.setTextureSize(128, 128);
        setRotationAngle(Shape4, 0.1919862f, 0.0f, 0.0f);
        BodyBase.addChild(Shape4);
        
        Shape2 = new ModelRenderer(this, 36, 79);
        Shape2.addBox(-4.5f, -1.0f, -3.0f, 9, 4, 6);
        Shape2.setRotationPoint(0.0f, 9.0f, -4.0f);
        Shape2.setTextureSize(128, 128);
        setRotationAngle(Shape2, 0.2094395f, 0.0f, 0.0f);
        BodyBase.addChild(Shape2);
        
        Shape1 = new ModelRenderer(this, 18, 28);
        Shape1.addBox(-3.5f, -3.466667f, -1.0f, 7, 7, 4);
        Shape1.setRotationPoint(0.0f, 6.0f, -5.0f);
        Shape1.setTextureSize(128, 128);
        setRotationAngle(Shape1, -0.0523599f, 0.0f, 0.0f);
        BodyBase.addChild(Shape1);
        
        Shape8 = new ModelRenderer(this, 46, 74);
        Shape8.addBox(-2.0f, 1.0f, 0.0f, 4, 4, 1);
        Shape8.setRotationPoint(0.0f, 10.0f, -7.0f);
        Shape8.setTextureSize(128, 128);
        setRotationAngle(Shape8, 0.0f, 0.0f, 0.0f);
        BodyBase.addChild(Shape8);
    }
    
    @Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) 
    {
		BodyBase.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadBaseYaw, float headPitch) 
	{

	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	public ModelRenderer getHead()
	{
		return Head;
	}
	
	public ModelRenderer getBody()
	{
		return BodyBase;
	}
	
	public ModelRenderer getLegs() 
	{
		return Legs;
	}

}
