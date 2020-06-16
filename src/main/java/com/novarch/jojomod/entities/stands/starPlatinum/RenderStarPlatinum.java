package com.novarch.jojomod.entities.stands.starPlatinum;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderStarPlatinum extends MobRenderer<EntityStarPlatinum, ModelStarPlatinum<EntityStarPlatinum>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum.png");

	public RenderStarPlatinum(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelStarPlatinum<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityStarPlatinum entity)
	{
		return RenderStarPlatinum.texture;
	}
}

