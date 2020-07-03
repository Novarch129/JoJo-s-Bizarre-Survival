package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelKillerQueen;
import com.novarch.jojomod.entities.stands.KillerQueenEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderKillerQueen extends MobRenderer<KillerQueenEntity, ModelKillerQueen<KillerQueenEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");

	public RenderKillerQueen(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelKillerQueen<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final KillerQueenEntity entity)
	{
		return RenderKillerQueen.texture;
	}
}

