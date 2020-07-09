package novarch.jojomod.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import novarch.jojomod.entities.stands.attacks.KingCrimsonPunchEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class KingCrimsonPunchModel extends EntityModel<KingCrimsonPunchEntity> {
	private final ModelRenderer Punch;

	public KingCrimsonPunchModel() {
		textureWidth = 64;
		textureHeight = 32;

		Punch = new ModelRenderer(this);
		Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
		Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(KingCrimsonPunchEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Punch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}