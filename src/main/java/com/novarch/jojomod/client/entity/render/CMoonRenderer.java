package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.CMoonModel;
import com.novarch.jojomod.entities.stands.CMoonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CMoonRenderer extends MobRenderer<CMoonEntity, CMoonModel<CMoonEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon.png");

	public CMoonRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CMoonModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final CMoonEntity entity)
	{
		return CMoonRenderer.texture;
	}
}

