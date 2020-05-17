package com.novarch.jojomod.entities.stands.aerosmith;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelAerosmith<T extends EntityAerosmith> extends EntityModel<T> {
	private final ModelRenderer Aerosmith;
	private final ModelRenderer Cockpit;
	private final ModelRenderer Guns;
	private final ModelRenderer Gun1;
	private final ModelRenderer Gun2;
	private final ModelRenderer WingLeft;
	private final ModelRenderer WingLeft2;
	private final ModelRenderer WingLeft3;
	private final ModelRenderer CockpitCurve;
	private final ModelRenderer CockpitCurve2;
	private final ModelRenderer Body;
	private final ModelRenderer Nose;
	private final ModelRenderer Blades;
	private final ModelRenderer Blade1;
	private final ModelRenderer Blade2;
	private final ModelRenderer WingPart1;
	private final ModelRenderer WingPart2;
	private final ModelRenderer Wing1;
	private final ModelRenderer Wing4;
	private final ModelRenderer Wing3;
	private final ModelRenderer Wing2;
	private final ModelRenderer WingLeft4;
	private final ModelRenderer WingLeft5;
	private final ModelRenderer WingLeft6;

	public ModelAerosmith() {
		textureWidth = 64;
		textureHeight = 64;

		Aerosmith = new ModelRenderer(this);
		Aerosmith.setRotationPoint(0.0F, 23.0F, 0.0F);
		

		Cockpit = new ModelRenderer(this);
		Cockpit.setRotationPoint(0.0F, 0.0F, 0.0F);
		Aerosmith.addChild(Cockpit);
		Cockpit.setTextureOffset(14, 0).addBox(-1.5F, -6.0F, -2.5F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		Cockpit.setTextureOffset(18, 12).addBox(0.0F, -3.675F, -2.75F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		Cockpit.setTextureOffset(18, 12).addBox(-3.0F, -3.675F, -2.75F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		Cockpit.setTextureOffset(18, 18).addBox(0.0F, -3.0F, -2.75F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		Cockpit.setTextureOffset(18, 18).addBox(-3.0F, -3.0F, -2.75F, 2.0F, 1.0F, 3.0F, 0.0F, true);
		Cockpit.setTextureOffset(36, 0).addBox(4.078F, -2.4275F, -2.525F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Cockpit.setTextureOffset(36, 0).addBox(-6.078F, -2.4275F, -2.525F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Cockpit.setTextureOffset(36, 0).addBox(4.078F, -2.4275F, -0.975F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Cockpit.setTextureOffset(36, 0).addBox(-6.078F, -2.4275F, -0.975F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Guns = new ModelRenderer(this);
		Guns.setRotationPoint(5.7F, -1.5F, -1.75F);
		Cockpit.addChild(Guns);
		

		Gun1 = new ModelRenderer(this);
		Gun1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Guns.addChild(Gun1);
		Gun1.setTextureOffset(22, 0).addBox(-0.55F, -0.35F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		Gun1.setTextureOffset(7, 15).addBox(-1.075F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Gun2 = new ModelRenderer(this);
		Gun2.setRotationPoint(-11.4F, 0.0F, 0.0F);
		Guns.addChild(Gun2);
		Gun2.setTextureOffset(22, 0).addBox(0.55F, -0.35F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, true);
		Gun2.setTextureOffset(7, 15).addBox(0.075F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		WingLeft = new ModelRenderer(this);
		WingLeft.setRotationPoint(3.5F, -2.5F, -1.75F);
		Cockpit.addChild(WingLeft);
		setRotationAngle(WingLeft, 0.0F, 0.0F, 0.4189F);
		WingLeft.setTextureOffset(23, 5).addBox(-2.0357F, -0.2553F, -0.775F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		WingLeft.setTextureOffset(23, 5).addBox(-1.7857F, -0.2803F, 0.775F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		WingLeft2 = new ModelRenderer(this);
		WingLeft2.setRotationPoint(0.0F, 0.0F, 0.0F);
		WingLeft.addChild(WingLeft2);
		setRotationAngle(WingLeft2, 0.0F, 0.0F, -0.5541F);
		WingLeft2.setTextureOffset(26, 0).addBox(2.4099F, 0.4104F, -0.775F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		WingLeft2.setTextureOffset(26, 0).addBox(2.4099F, 0.4104F, 0.775F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		WingLeft3 = new ModelRenderer(this);
		WingLeft3.setRotationPoint(1.6993F, -0.7924F, 0.0F);
		WingLeft.addChild(WingLeft3);
		setRotationAngle(WingLeft3, 0.0F, 0.0F, -0.7854F);
		WingLeft3.setTextureOffset(0, 11).addBox(2.2073F, 0.7423F, -0.775F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		WingLeft3.setTextureOffset(0, 15).addBox(2.2073F, 0.7423F, 0.775F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		CockpitCurve = new ModelRenderer(this);
		CockpitCurve.setRotationPoint(-0.5F, -3.5F, -1.5F);
		Cockpit.addChild(CockpitCurve);
		setRotationAngle(CockpitCurve, 0.685F, 0.0F, 0.0F);
		CockpitCurve.setTextureOffset(10, 27).addBox(-1.0F, -1.3033F, 1.3562F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		CockpitCurve.setTextureOffset(29, 16).addBox(-1.0F, 0.6967F, 1.3562F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		CockpitCurve.setTextureOffset(29, 20).addBox(-1.0F, -0.5722F, 1.027F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		CockpitCurve2 = new ModelRenderer(this);
		CockpitCurve2.setRotationPoint(-0.5F, -3.5F, 1.5F);
		Cockpit.addChild(CockpitCurve2);
		setRotationAngle(CockpitCurve2, -0.4712F, 0.0F, 0.0F);
		CockpitCurve2.setTextureOffset(10, 11).addBox(-1.0F, -0.4116F, -4.699F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		CockpitCurve2.setTextureOffset(0, 0).addBox(-1.0F, 1.5884F, -4.699F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		CockpitCurve2.setTextureOffset(29, 19).addBox(-1.0F, 0.3195F, -4.3699F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Aerosmith.addChild(Body);
		Body.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -5.0F, 3.0F, 3.0F, 7.0F, 0.0F, false);
		Body.setTextureOffset(35, 5).addBox(-1.5F, -4.0F, 2.35F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(10, 23).addBox(-1.5F, -2.0F, 2.1F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		Body.setTextureOffset(3, 30).addBox(-1.5F, -2.25F, 3.85F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		Body.setTextureOffset(35, 11).addBox(-1.175F, -4.0F, 1.5F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		Body.setTextureOffset(35, 11).addBox(-1.825F, -4.0F, 1.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		Body.setTextureOffset(3, 26).addBox(-1.0F, -3.875F, 5.125F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Body.setTextureOffset(27, 10).addBox(-0.25F, -3.875F, 3.125F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		Body.setTextureOffset(27, 10).addBox(-1.75F, -3.875F, 3.125F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		Nose = new ModelRenderer(this);
		Nose.setRotationPoint(0.0F, -1.0F, -1.0F);
		Body.addChild(Nose);
		Nose.setTextureOffset(0, 18).addBox(-1.5F, -2.5F, -4.8F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		Nose.setTextureOffset(29, 25).addBox(-1.0F, 0.5F, -1.8F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		Nose.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -1.8F, 0.0F, 1.0F, 2.0F, 0.0F, false);
		Nose.setTextureOffset(17, 28).addBox(-0.5F, 0.9F, -1.1F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		Nose.setTextureOffset(0, 0).addBox(-0.575F, 0.975F, -1.05F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		Nose.setTextureOffset(0, 0).addBox(-1.425F, 0.975F, -1.05F, 1.0F, 0.0F, 1.0F, 0.0F, false);

		Blades = new ModelRenderer(this);
		Blades.setRotationPoint(0.0F, 0.0F, 0.0F);
		Nose.addChild(Blades);
		

		Blade1 = new ModelRenderer(this);
		Blade1.setRotationPoint(-1.25F, -0.5F, 0.0F);
		Blades.addChild(Blade1);
		setRotationAngle(Blade1, 0.0F, 0.0F, 0.6981F);
		Blade1.setTextureOffset(0, 22).addBox(-0.5F, -6.0F, -4.75F, 1.0F, 4.0F, 0.0F, 0.0F, false);

		Blade2 = new ModelRenderer(this);
		Blade2.setRotationPoint(0.25F, -2.5F, 0.0F);
		Blades.addChild(Blade2);
		setRotationAngle(Blade2, 0.0F, 0.0F, 0.6981F);
		Blade2.setTextureOffset(0, 22).addBox(-0.5F, 2.0F, -4.75F, 1.0F, 4.0F, 0.0F, 0.0F, false);

		WingPart1 = new ModelRenderer(this);
		WingPart1.setRotationPoint(2.375F, -0.875F, 1.1F);
		Body.addChild(WingPart1);
		setRotationAngle(WingPart1, 0.0F, -0.24F, 0.0F);
		WingPart1.setTextureOffset(20, 24).addBox(-1.0F, -3.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		WingPart2 = new ModelRenderer(this);
		WingPart2.setRotationPoint(-3.375F, -0.875F, 1.1F);
		Body.addChild(WingPart2);
		setRotationAngle(WingPart2, 0.0F, 0.24F, 0.0F);
		WingPart2.setTextureOffset(9, 19).addBox(-1.0F, -3.0F, 3.0F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		Wing1 = new ModelRenderer(this);
		Wing1.setRotationPoint(0.75F, -3.875F, 4.125F);
		Body.addChild(Wing1);
		setRotationAngle(Wing1, 0.0F, 0.0F, 0.0524F);
		Wing1.setTextureOffset(0, 29).addBox(-0.075F, -5.0F, -0.5F, 0.0F, 5.0F, 1.0F, 0.0F, false);

		Wing4 = new ModelRenderer(this);
		Wing4.setRotationPoint(-2.0103F, -8.8158F, 4.125F);
		Body.addChild(Wing4);
		setRotationAngle(Wing4, 0.0F, 0.0F, 0.1222F);
		Wing4.setTextureOffset(3, 23).addBox(-1.925F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, true);

		Wing3 = new ModelRenderer(this);
		Wing3.setRotationPoint(2.0103F, -8.8158F, 4.125F);
		Body.addChild(Wing3);
		setRotationAngle(Wing3, 0.0F, 0.0F, -0.1222F);
		Wing3.setTextureOffset(3, 23).addBox(-1.0675F, -0.1219F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, false);

		Wing2 = new ModelRenderer(this);
		Wing2.setRotationPoint(-0.8998F, -3.8671F, 4.125F);
		Body.addChild(Wing2);
		setRotationAngle(Wing2, 0.0F, 0.0F, -0.0524F);
		Wing2.setTextureOffset(0, 29).addBox(-0.75F, -5.0F, -0.5F, 0.0F, 5.0F, 1.0F, 0.0F, true);

		WingLeft4 = new ModelRenderer(this);
		WingLeft4.setRotationPoint(-3.5F, 20.5F, -1.75F);
		setRotationAngle(WingLeft4, 0.0F, 0.0F, -0.4189F);
		WingLeft4.setTextureOffset(23, 5).addBox(-0.9643F, -0.2553F, -0.775F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		WingLeft4.setTextureOffset(23, 5).addBox(-1.2143F, -0.2803F, 0.775F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		WingLeft5 = new ModelRenderer(this);
		WingLeft5.setRotationPoint(0.0F, 0.0F, 0.0F);
		WingLeft4.addChild(WingLeft5);
		setRotationAngle(WingLeft5, 0.0F, 0.0F, 0.5541F);
		WingLeft5.setTextureOffset(26, 0).addBox(-4.4099F, 0.4104F, -0.775F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		WingLeft5.setTextureOffset(26, 0).addBox(-4.4099F, 0.4104F, 0.775F, 2.0F, 1.0F, 1.0F, 0.0F, true);

		WingLeft6 = new ModelRenderer(this);
		WingLeft6.setRotationPoint(-1.6993F, -0.7924F, 0.0F);
		WingLeft4.addChild(WingLeft6);
		setRotationAngle(WingLeft6, 0.0F, 0.0F, 0.7854F);
		WingLeft6.setTextureOffset(0, 11).addBox(-4.2073F, 0.7423F, -0.775F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		WingLeft6.setTextureOffset(0, 15).addBox(-4.2073F, 0.7423F, 0.775F, 2.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		Aerosmith.render(matrixStack, buffer, packedLight, packedOverlay);
		WingLeft4.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}