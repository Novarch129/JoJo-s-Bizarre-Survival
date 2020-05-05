package com.novarch.jojomod.entities.stands.goldExperienceRequiem;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.ModelGoldExperienceRequiem;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderGoldExperienceRequiem extends MobRenderer<EntityGoldExperienceRequiem, ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>>
{
	protected static final ResourceLocation texture = new ResourceLocation(StevesBizarreSurvival.MOD_ID, "textures/stands/ger.png");

	public RenderGoldExperienceRequiem(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityGoldExperienceRequiem entity)
	{
		return RenderGoldExperienceRequiem.texture;
	}
}

