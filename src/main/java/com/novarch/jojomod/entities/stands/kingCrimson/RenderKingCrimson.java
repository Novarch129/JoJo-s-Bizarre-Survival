package com.novarch.jojomod.entities.stands.kingCrimson;

import com.novarch.jojomod.StevesBizarreSurvival;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderKingCrimson extends MobRenderer<EntityKingCrimson, ModelKingCrimson<EntityKingCrimson>>
{
	protected static final ResourceLocation texture = new ResourceLocation(StevesBizarreSurvival.MOD_ID, "textures/stands/kc.png");

	public RenderKingCrimson(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelKingCrimson<EntityKingCrimson>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityKingCrimson entity)
	{
		return RenderKingCrimson.texture;
	}
}

