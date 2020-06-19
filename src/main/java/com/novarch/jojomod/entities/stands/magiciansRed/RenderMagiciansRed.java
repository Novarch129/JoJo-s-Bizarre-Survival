package com.novarch.jojomod.entities.stands.magiciansRed;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderMagiciansRed extends MobRenderer<EntityMagiciansRed, ModelMagiciansRed<EntityMagiciansRed>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red.png");

	public RenderMagiciansRed(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMagiciansRed<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityMagiciansRed entity)
	{
		return RenderMagiciansRed.texture;
	}
}

