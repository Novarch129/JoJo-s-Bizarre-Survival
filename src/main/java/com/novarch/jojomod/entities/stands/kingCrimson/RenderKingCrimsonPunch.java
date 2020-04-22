package com.novarch.jojomod.entities.stands.kingCrimson;

import com.novarch.jojomod.JojoMod;
import com.novarch.jojomod.entities.stands.EntityStandPunch;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderKingCrimsonPunch extends EntityRenderer<EntityStandPunch.kingCrimson>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoMod.MOD_ID, "textures/stands/kc_punch.png");

	public RenderKingCrimsonPunch(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn);
	}

	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.kingCrimson entity)
	{
		return RenderKingCrimsonPunch.texture;
	}
}

