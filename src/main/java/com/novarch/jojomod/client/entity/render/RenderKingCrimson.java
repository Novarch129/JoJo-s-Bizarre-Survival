package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelKingCrimson;
import com.novarch.jojomod.entities.stands.KingCrimsonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderKingCrimson extends MobRenderer<KingCrimsonEntity, ModelKingCrimson<KingCrimsonEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png");

	public RenderKingCrimson(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelKingCrimson<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final KingCrimsonEntity entity)
	{
		return RenderKingCrimson.texture;
	}
}

