package com.novarch.jojomod.entities.stands.goldExperienceRequiem;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderGoldExperienceRequiem extends MobRenderer<EntityGoldExperienceRequiem, ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");

	public RenderGoldExperienceRequiem(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelGoldExperienceRequiem<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(final EntityGoldExperienceRequiem entity)
	{
		return RenderGoldExperienceRequiem.texture;
	}

	@Override
	public void render(EntityGoldExperienceRequiem entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
}

