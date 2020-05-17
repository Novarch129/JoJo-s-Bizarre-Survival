package com.novarch.jojomod.entities.stands.goldExperienceRequiem;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGoldExperienceRequiem extends MobRenderer<EntityGoldExperienceRequiem, ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");

	public RenderGoldExperienceRequiem(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>(), 0.5f);
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

