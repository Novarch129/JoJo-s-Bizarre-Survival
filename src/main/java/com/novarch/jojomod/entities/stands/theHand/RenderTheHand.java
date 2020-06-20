package com.novarch.jojomod.entities.stands.theHand;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderTheHand extends MobRenderer<EntityTheHand, ModelTheHand<EntityTheHand>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public RenderTheHand(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTheHand<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityTheHand entity)
	{
		return RenderTheHand.texture;
	}
}

