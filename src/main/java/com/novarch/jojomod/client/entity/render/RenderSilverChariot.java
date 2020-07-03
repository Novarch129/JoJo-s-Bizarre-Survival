package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelSilverChariot;
import com.novarch.jojomod.entities.stands.SilverChariotEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderSilverChariot extends MobRenderer<SilverChariotEntity, ModelSilverChariot<SilverChariotEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/silver_chariot.png");

	public RenderSilverChariot(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSilverChariot<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(SilverChariotEntity entity)
	{
		return RenderSilverChariot.texture;
	}
}

