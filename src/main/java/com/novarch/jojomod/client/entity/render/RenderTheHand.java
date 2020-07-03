package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelTheHand;
import com.novarch.jojomod.entities.stands.TheHandEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderTheHand extends MobRenderer<TheHandEntity, ModelTheHand<TheHandEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");

	public RenderTheHand(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelTheHand<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final TheHandEntity entity)
	{
		return RenderTheHand.texture;
	}
}

