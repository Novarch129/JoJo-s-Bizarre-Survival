package com.novarch.jojomod.entities.stands.crazyDiamond;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderCrazyDiamond extends MobRenderer<EntityCrazyDiamond, ModelCrazyDiamond<EntityCrazyDiamond>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");

	public RenderCrazyDiamond(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelCrazyDiamond<EntityCrazyDiamond>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityCrazyDiamond entity)
	{
		return RenderCrazyDiamond.texture;
	}
}

