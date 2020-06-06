package com.novarch.jojomod.entities.stands.madeInHeaven;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderMadeInHeaven extends MobRenderer<EntityMadeInHeaven, ModelMadeInHeaven<EntityMadeInHeaven>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/made_in_heaven.png");

	public RenderMadeInHeaven(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMadeInHeaven<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityMadeInHeaven entity)
	{
		return RenderMadeInHeaven.texture;
	}
}

