package com.novarch.jojomod.entities.stands.killerQueen;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderKillerQueen extends MobRenderer<EntityKillerQueen, ModelKillerQueen<EntityKillerQueen>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");

	public RenderKillerQueen(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelKillerQueen<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityKillerQueen entity)
	{
		return RenderKillerQueen.texture;
	}
}

