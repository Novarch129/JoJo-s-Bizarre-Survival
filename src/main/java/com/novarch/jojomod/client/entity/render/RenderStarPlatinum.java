package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelStarPlatinum;
import com.novarch.jojomod.entities.stands.StarPlatinumEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderStarPlatinum extends MobRenderer<StarPlatinumEntity, ModelStarPlatinum<StarPlatinumEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum.png");

	public RenderStarPlatinum(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelStarPlatinum<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final StarPlatinumEntity entity)
	{
		return RenderStarPlatinum.texture;
	}
}

