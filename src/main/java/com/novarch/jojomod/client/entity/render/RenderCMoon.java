package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelCMoon;
import com.novarch.jojomod.entities.stands.CMoonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderCMoon extends MobRenderer<CMoonEntity, ModelCMoon<CMoonEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon.png");

	public RenderCMoon(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelCMoon<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final CMoonEntity entity)
	{
		return RenderCMoon.texture;
	}
}

