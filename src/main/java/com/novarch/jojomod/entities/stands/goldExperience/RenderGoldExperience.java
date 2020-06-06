package com.novarch.jojomod.entities.stands.goldExperience;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderGoldExperience extends MobRenderer<EntityGoldExperience, ModelGoldExperience<EntityGoldExperience>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");

	public RenderGoldExperience(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelGoldExperience<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityGoldExperience entity)
	{
		return RenderGoldExperience.texture;
	}
}

