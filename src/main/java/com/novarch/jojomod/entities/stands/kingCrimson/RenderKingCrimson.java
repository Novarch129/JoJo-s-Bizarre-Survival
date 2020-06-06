package com.novarch.jojomod.entities.stands.kingCrimson;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderKingCrimson extends MobRenderer<EntityKingCrimson, ModelKingCrimson<EntityKingCrimson>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png");

	public RenderKingCrimson(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelKingCrimson<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityKingCrimson entity)
	{
		return RenderKingCrimson.texture;
	}
}

