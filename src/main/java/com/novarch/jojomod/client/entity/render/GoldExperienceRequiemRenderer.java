package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.GoldExperienceRequiemModel;
import com.novarch.jojomod.entities.stands.GoldExperienceRequiemEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GoldExperienceRequiemRenderer extends MobRenderer<GoldExperienceRequiemEntity, GoldExperienceRequiemModel<GoldExperienceRequiemEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");

	public GoldExperienceRequiemRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GoldExperienceRequiemModel<>(), 0.5f);
	}

	@Override
	public void render(GoldExperienceRequiemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(final GoldExperienceRequiemEntity entity) {
		return TEXTURE;
	}
}

