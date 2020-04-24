package com.novarch.jojomod.entities.stands.kingCrimson;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.EntityStandPunch;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelKingCrimsonPunch <T extends EntityStandPunch.kingCrimson> extends EntityModel<T>
{
	private final ModelRenderer Punch;
	  
	 public ModelKingCrimsonPunch()
	 {
		this.textureWidth = 64;
	   	this.textureHeight = 64;
	   	
	    this.Punch = new ModelRenderer(this, 0, 0);
	    this.Punch.addBox(-1.0F, -1.0F, 0.0F, 3, 3, 7);
	    this.Punch.setRotationPoint(0.0F, 0.0F, 0.0F);
	    this.Punch.setTextureSize(64, 64);
	    setRotationAngle(this.Punch, 0.0F, 0.0F, 0.0F);
	 }
	  
	 @Override
	 public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) 
	 {
		 this.Punch.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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
}
