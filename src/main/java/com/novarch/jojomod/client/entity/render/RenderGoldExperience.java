package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelGoldExperience;
import com.novarch.jojomod.entities.stands.GoldExperienceEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderGoldExperience extends MobRenderer<GoldExperienceEntity, ModelGoldExperience<GoldExperienceEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");

	public RenderGoldExperience(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelGoldExperience<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final GoldExperienceEntity entity)
	{
		return RenderGoldExperience.texture;
	}
}

