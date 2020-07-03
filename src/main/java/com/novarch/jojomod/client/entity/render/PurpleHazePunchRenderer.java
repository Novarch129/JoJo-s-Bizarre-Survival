package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.PurpleHazePunchModel;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PurpleHazePunchRenderer extends StandPunchRenderer<AbstractStandPunchEntity.PurpleHaze> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze_punch.png");

	public PurpleHazePunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull AbstractStandPunchEntity.PurpleHaze entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new PurpleHazePunchModel<>());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final AbstractStandPunchEntity.PurpleHaze entity) {
		return TEXTURE;
	}
}

