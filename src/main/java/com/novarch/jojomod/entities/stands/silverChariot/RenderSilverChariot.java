package com.novarch.jojomod.entities.stands.silverChariot;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderSilverChariot extends MobRenderer<EntitySilverChariot, ModelSilverChariot<EntitySilverChariot>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public RenderSilverChariot(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSilverChariot<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntitySilverChariot entity)
	{
		return RenderSilverChariot.texture;
	}
}

