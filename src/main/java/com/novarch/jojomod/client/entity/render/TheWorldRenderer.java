package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.TheWorldModel;
import com.novarch.jojomod.entities.stands.TheWorldEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TheWorldRenderer extends MobRenderer<TheWorldEntity, TheWorldModel<TheWorldEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world.png");

	public TheWorldRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new TheWorldModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final TheWorldEntity entity)
	{
		return TheWorldRenderer.texture;
	}
}

