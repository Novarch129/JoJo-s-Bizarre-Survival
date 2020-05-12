package com.novarch.jojomod.entities.stands.goldExperience;

import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGoldExperience extends MobRenderer<EntityGoldExperience, ModelGoldExperience<EntityGoldExperience>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ge.png");

	public RenderGoldExperience(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn, new ModelGoldExperience<EntityGoldExperience>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityGoldExperience entity)
	{
		return RenderGoldExperience.texture;
	}
}

