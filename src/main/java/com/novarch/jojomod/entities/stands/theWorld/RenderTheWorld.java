package com.novarch.jojomod.entities.stands.theWorld;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderTheWorld extends MobRenderer<EntityTheWorld, ModelTheWorld<EntityTheWorld>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public RenderTheWorld(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTheWorld<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final EntityTheWorld entity)
	{
		return RenderTheWorld.texture;
	}
}

