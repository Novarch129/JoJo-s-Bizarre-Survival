package com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack;

import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderSheerHeartAttack extends MobRenderer<EntitySheerHeartAttack, ModelSheerHeartAttack<EntitySheerHeartAttack>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sheer_heart_attack.png");

	public RenderSheerHeartAttack(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSheerHeartAttack<EntitySheerHeartAttack>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntitySheerHeartAttack entity)
	{
		return RenderSheerHeartAttack.texture;
	}
}

