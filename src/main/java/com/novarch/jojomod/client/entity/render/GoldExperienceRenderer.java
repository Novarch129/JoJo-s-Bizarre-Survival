package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.GoldExperienceModel;
import com.novarch.jojomod.entities.stands.GoldExperienceEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GoldExperienceRenderer extends MobRenderer<GoldExperienceEntity, GoldExperienceModel<GoldExperienceEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");

	public GoldExperienceRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new GoldExperienceModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final GoldExperienceEntity entity)
	{
		return GoldExperienceRenderer.texture;
	}
}

