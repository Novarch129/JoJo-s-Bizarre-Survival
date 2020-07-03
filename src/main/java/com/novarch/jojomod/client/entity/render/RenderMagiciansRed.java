package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelMagiciansRed;
import com.novarch.jojomod.entities.stands.MagiciansRedEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderMagiciansRed extends MobRenderer<MagiciansRedEntity, ModelMagiciansRed<MagiciansRedEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red.png");

	public RenderMagiciansRed(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMagiciansRed<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final MagiciansRedEntity entity)
	{
		return RenderMagiciansRed.texture;
	}
}

