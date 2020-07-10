package io.github.novarch129.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stands.attacks.PurpleHazePunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PurpleHazePunchModel extends EntityModel<PurpleHazePunchEntity> {
	private final ModelRenderer Punch;
	private final ModelRenderer ElbowPad;
	private final ModelRenderer Capsules1;

	public PurpleHazePunchModel() {
		textureWidth = 64;
		textureHeight = 32;

		Punch = new ModelRenderer(this);
		Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
		Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

		ElbowPad = new ModelRenderer(this);
		ElbowPad.setRotationPoint(-0.6028F, 24.0006F, -5.1954F);
		Punch.addChild(ElbowPad);
		ElbowPad.setTextureOffset(0, 28).addBox(-1.2577F, -23.8977F, 5.1482F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		ElbowPad.setTextureOffset(0, 17).addBox(-0.9827F, -28.3977F, 6.1482F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		ElbowPad.setTextureOffset(0, 20).addBox(-1.4827F, -28.3977F, 6.1482F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		ElbowPad.setTextureOffset(0, 20).addBox(1.9923F, -27.8977F, 6.1482F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		ElbowPad.setTextureOffset(0, 20).addBox(-1.4327F, -27.8977F, 6.1482F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		Capsules1 = new ModelRenderer(this);
		Capsules1.setRotationPoint(-5.1874F, -5.4686F, -7.1988F);
		Punch.addChild(Capsules1);
		Capsules1.setTextureOffset(22, 30).addBox(2.6714F, 1.6204F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Capsules1.setTextureOffset(22, 30).addBox(2.6714F, 2.9954F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		Capsules1.setTextureOffset(22, 30).addBox(2.6714F, 4.3704F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(PurpleHazePunchEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Punch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}