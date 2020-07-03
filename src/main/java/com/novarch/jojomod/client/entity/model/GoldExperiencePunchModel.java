package com.novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GoldExperiencePunchModel<T extends AbstractStandPunchEntity.GoldExperience> extends EntityModel<T> {
	private final ModelRenderer LeftHand;
	private final ModelRenderer Beetle2;
	private final ModelRenderer Beetle6;

	public GoldExperiencePunchModel() {
		textureWidth = 64;
		textureHeight = 32;

		LeftHand = new ModelRenderer(this);
		LeftHand.setRotationPoint(-4.7225F, 25.4467F, -8.6359F);
		setRotationAngle(LeftHand, -1.5621F, 0.0F, -0.0044F);
		LeftHand.setTextureOffset(0, 13).addBox(2.7025F, -14.5533F, -5.6359F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		Beetle2 = new ModelRenderer(this);
		Beetle2.setRotationPoint(-13.2015F, -13.2356F, 1.85F);
		LeftHand.addChild(Beetle2);
		

		Beetle6 = new ModelRenderer(this);
		Beetle6.setRotationPoint(17.0F, 0.0F, 0.0F);
		Beetle2.addChild(Beetle6);
		Beetle6.setTextureOffset(32, 8).addBox(2.249F, 6.5124F, -6.4702F, 1.0F, 3.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		LeftHand.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		LeftHand.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}