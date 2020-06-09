package com.novarch.jojomod.entities.stands.whitesnake;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderWhitesnake extends MobRenderer<EntityWhitesnake, ModelWhitesnake<EntityWhitesnake>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public RenderWhitesnake(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelWhitesnake<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityWhitesnake entity)
	{
		return RenderWhitesnake.texture;
	}
}

