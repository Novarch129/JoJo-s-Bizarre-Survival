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
public class TheWorldPunchModel<T extends AbstractStandPunchEntity.TheWorld> extends EntityModel<T> {
	public final ModelRenderer Punch;
	private final ModelRenderer ElbowPad2;
	private final ModelRenderer Heart7;
	private final ModelRenderer HeartPiece20;
	private final ModelRenderer HeartPiece21;
	private final ModelRenderer HeartPiece22;

	public TheWorldPunchModel() {
		textureWidth = 64;
		textureHeight = 32;

		Punch = new ModelRenderer(this);
		Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
		Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

		ElbowPad2 = new ModelRenderer(this);
		ElbowPad2.setRotationPoint(0.0247F, -1.3378F, -2.0572F);
		Punch.addChild(ElbowPad2);
		setRotationAngle(ElbowPad2, -1.6232F, 0.0044F, 1.5708F);
		ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		ElbowPad2.setTextureOffset(54, 26).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
		ElbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);

		Heart7 = new ModelRenderer(this);
		Heart7.setRotationPoint(4.0503F, 2.8628F, 3.4072F);
		ElbowPad2.addChild(Heart7);
		

		HeartPiece20 = new ModelRenderer(this);
		HeartPiece20.setRotationPoint(-0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece20);
		setRotationAngle(HeartPiece20, 0.0F, 0.0F, -0.7854F);
		HeartPiece20.setTextureOffset(58, 20).addBox(-13.1017F, 5.0588F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		HeartPiece21 = new ModelRenderer(this);
		HeartPiece21.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece21);
		setRotationAngle(HeartPiece21, 0.0F, 0.0F, 0.7854F);
		HeartPiece21.setTextureOffset(58, 20).addBox(5.3517F, 11.8088F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		HeartPiece22 = new ModelRenderer(this);
		HeartPiece22.setRotationPoint(0.5F, -18.0F, -4.5F);
		Heart7.addChild(HeartPiece22);
		setRotationAngle(HeartPiece22, 0.0F, 0.0F, 1.5708F);
		HeartPiece22.setTextureOffset(58, 20).addBox(11.8414F, 3.8587F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(58, 20).addBox(11.8414F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(58, 20).addBox(11.3664F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(58, 20).addBox(11.3664F, 3.8587F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		HeartPiece22.setTextureOffset(58, 20).addBox(12.0335F, 4.2445F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Punch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		Punch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}