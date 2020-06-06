package com.novarch.jojomod.entities.stands.purpleHaze;

import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderPurpleHaze extends MobRenderer<EntityPurpleHaze, ModelPurpleHaze<EntityPurpleHaze>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze.png");

	public RenderPurpleHaze(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelPurpleHaze<EntityPurpleHaze>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityPurpleHaze entity)
	{
		return RenderPurpleHaze.texture;
	}
}

