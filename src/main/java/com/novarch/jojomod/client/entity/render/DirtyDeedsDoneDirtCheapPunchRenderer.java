package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.DirtyDeedsDoneDirtCheapPunchModel;
import com.novarch.jojomod.entities.stands.attacks.DirtyDeedsDoneDirtCheapPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DirtyDeedsDoneDirtCheapPunchRenderer extends StandPunchRenderer<DirtyDeedsDoneDirtCheapPunchEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c_punch.png");

	public DirtyDeedsDoneDirtCheapPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull DirtyDeedsDoneDirtCheapPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new DirtyDeedsDoneDirtCheapPunchModel());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final DirtyDeedsDoneDirtCheapPunchEntity entity) {
		return TEXTURE;
	}
}

