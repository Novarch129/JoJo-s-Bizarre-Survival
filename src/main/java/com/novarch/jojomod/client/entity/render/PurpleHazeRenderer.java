package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.PurpleHazeModel;
import com.novarch.jojomod.entities.stands.PurpleHazeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class PurpleHazeRenderer extends MobRenderer<PurpleHazeEntity, PurpleHazeModel<PurpleHazeEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze.png");

	public PurpleHazeRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new PurpleHazeModel<PurpleHazeEntity>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final PurpleHazeEntity entity)
	{
		return PurpleHazeRenderer.texture;
	}
}

