package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.CrazyDiamondModel;
import com.novarch.jojomod.entities.stands.CrazyDiamondEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrazyDiamondRenderer extends MobRenderer<CrazyDiamondEntity, CrazyDiamondModel<CrazyDiamondEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/crazy_diamond.png");

	public CrazyDiamondRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new CrazyDiamondModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final CrazyDiamondEntity entity)
	{
		return CrazyDiamondRenderer.texture;
	}
}

