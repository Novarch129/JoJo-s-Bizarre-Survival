package com.novarch.jojomod.entities.stands.aerosmith;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderAerosmithBullet extends EntityRenderer<EntityStandPunch.aerosmith>
{
	protected ModelAerosmithBullet<EntityStandPunch.aerosmith> punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/a_bullet.png");

	public RenderAerosmithBullet(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new ModelAerosmithBullet<>();
	}

	@Override
	public void render(@Nonnull EntityStandPunch.aerosmith entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.aerosmith entity)
	{
		return RenderAerosmithBullet.texture;
	}
}

