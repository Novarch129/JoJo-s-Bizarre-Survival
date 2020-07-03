package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelTheWorld;
import com.novarch.jojomod.entities.stands.TheWorldEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderTheWorld extends MobRenderer<TheWorldEntity, ModelTheWorld<TheWorldEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world.png");

	public RenderTheWorld(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTheWorld<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final TheWorldEntity entity)
	{
		return RenderTheWorld.texture;
	}
}

