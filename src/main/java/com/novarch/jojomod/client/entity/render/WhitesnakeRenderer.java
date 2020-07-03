package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.WhitesnakeModel;
import com.novarch.jojomod.entities.stands.WhitesnakeEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WhitesnakeRenderer extends MobRenderer<WhitesnakeEntity, WhitesnakeModel<WhitesnakeEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public WhitesnakeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new WhitesnakeModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final WhitesnakeEntity entity)
	{
		return WhitesnakeRenderer.texture;
	}
}

