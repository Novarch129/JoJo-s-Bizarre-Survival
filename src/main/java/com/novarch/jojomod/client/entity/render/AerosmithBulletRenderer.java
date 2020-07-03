package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.AerosmithBulletModel;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AerosmithBulletRenderer extends EntityRenderer<AbstractStandPunchEntity.Aerosmith>
{
	protected AerosmithBulletModel<AbstractStandPunchEntity.Aerosmith> punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/a_bullet.png");

	public AerosmithBulletRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new AerosmithBulletModel<>();
	}

	@Override
	public void render(@Nonnull AbstractStandPunchEntity.Aerosmith entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final AbstractStandPunchEntity.Aerosmith entity)
	{
		return AerosmithBulletRenderer.texture;
	}
}

