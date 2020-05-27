package com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelSheerHeartAttack<T extends EntitySheerHeartAttack> extends EntityModel<T> {
	private final ModelRenderer SheerHeartAttack;
	private final ModelRenderer Body;
	private final ModelRenderer Main;
	private final ModelRenderer Treads;
	private final ModelRenderer LeftTread;
	private final ModelRenderer RightTread;
	private final ModelRenderer Skull;
	private final ModelRenderer MainSkull;

	public ModelSheerHeartAttack() {
		textureWidth = 128;
		textureHeight = 128;

		SheerHeartAttack = new ModelRenderer(this);
		SheerHeartAttack.setRotationPoint(-2.0F, 24.0F, 4.0F);
		

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		SheerHeartAttack.addChild(Body);
		Body.setTextureOffset(48, 69).addBox(-3.5F, -11.25F, -9.75F, 11.0F, 10.0F, 11.0F, -4.0F, false);

		Main = new ModelRenderer(this);
		Main.setRotationPoint(0.0F, 4.0F, -12.0F);
		Body.addChild(Main);
		setRotationAngle(Main, 0.0F, 3.1416F, 0.0F);
		Main.setTextureOffset(0, 0).addBox(-10.0F, -14.0F, -16.0F, 16.0F, 14.0F, 16.0F, -4.0F, true);

		Treads = new ModelRenderer(this);
		Treads.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Treads);
		

		LeftTread = new ModelRenderer(this);
		LeftTread.setRotationPoint(0.0F, 0.0F, 0.0F);
		Treads.addChild(LeftTread);
		LeftTread.setTextureOffset(0, 37).addBox(-8.5F, -7.75F, -12.0F, 11.0F, 11.0F, 16.0F, -4.5F, false);
		LeftTread.setTextureOffset(0, 66).addBox(-8.35F, -7.7F, -12.275F, 11.0F, 10.0F, 10.0F, -4.5F, false);
		LeftTread.setTextureOffset(0, 66).addBox(-8.35F, -7.7F, -5.725F, 11.0F, 10.0F, 10.0F, -4.5F, false);
		LeftTread.setTextureOffset(0, 103).addBox(-7.675F, -6.575F, -11.0F, 10.0F, 11.0F, 14.0F, -4.5F, false);
		LeftTread.setTextureOffset(61, 102).addBox(-7.975F, -6.5F, -11.5F, 10.0F, 11.0F, 15.0F, -4.5F, true);
		LeftTread.setTextureOffset(0, 103).addBox(-8.175F, -6.575F, -11.0F, 10.0F, 11.0F, 14.0F, -4.5F, true);

		RightTread = new ModelRenderer(this);
		RightTread.setRotationPoint(4.0F, 0.0F, 0.0F);
		Treads.addChild(RightTread);
		RightTread.setTextureOffset(0, 66).addBox(-2.65F, -7.7F, -12.275F, 11.0F, 10.0F, 10.0F, -4.5F, true);
		RightTread.setTextureOffset(0, 66).addBox(-2.65F, -7.7F, -5.725F, 11.0F, 10.0F, 10.0F, -4.5F, true);
		RightTread.setTextureOffset(0, 37).addBox(-2.5F, -7.75F, -12.0F, 11.0F, 11.0F, 16.0F, -4.5F, true);
		RightTread.setTextureOffset(0, 103).addBox(-2.325F, -6.575F, -11.0F, 10.0F, 11.0F, 14.0F, -4.5F, true);
		RightTread.setTextureOffset(61, 102).addBox(-2.025F, -6.5F, -11.5F, 10.0F, 11.0F, 15.0F, -4.5F, false);
		RightTread.setTextureOffset(0, 103).addBox(-1.825F, -6.575F, -11.0F, 10.0F, 11.0F, 14.0F, -4.5F, false);

		Skull = new ModelRenderer(this);
		Skull.setRotationPoint(0.0F, 0.0F, -0.675F);
		Body.addChild(Skull);
		Skull.setTextureOffset(124, 19).addBox(3.1F, -6.9F, -8.45F, 1.0F, 3.0F, 1.0F, -1.0F, false);
		Skull.setTextureOffset(124, 19).addBox(0.1F, -6.9F, -8.45F, 1.0F, 3.0F, 1.0F, -1.0F, true);
		Skull.setTextureOffset(124, 57).addBox(2.925F, -6.35F, -8.325F, 1.0F, 6.0F, 1.0F, -1.0F, false);
		Skull.setTextureOffset(124, 32).addBox(1.45F, -5.7F, -8.525F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		MainSkull = new ModelRenderer(this);
		MainSkull.setRotationPoint(2.05F, -3.35F, -2.975F);
		Skull.addChild(MainSkull);
		setRotationAngle(MainSkull, 0.0F, 3.1416F, 0.0F);
		MainSkull.setTextureOffset(108, 0).addBox(-5.05F, -5.0F, 2.5F, 10.0F, 10.0F, 0.0F, -3.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		SheerHeartAttack.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}