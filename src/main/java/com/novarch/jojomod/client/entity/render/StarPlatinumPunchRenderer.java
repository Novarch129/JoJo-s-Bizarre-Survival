package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.StarPlatinumPunchModel;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StarPlatinumPunchRenderer extends StandPunchRenderer<AbstractStandPunchEntity.StarPlatinum> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum_punch.png");

	public StarPlatinumPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull AbstractStandPunchEntity.StarPlatinum entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new StarPlatinumPunchModel<>());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(AbstractStandPunchEntity.StarPlatinum entity) {
		return TEXTURE;
	}
}

