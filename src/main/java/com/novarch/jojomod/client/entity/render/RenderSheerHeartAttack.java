package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelSheerHeartAttack;
import com.novarch.jojomod.entities.stands.SheerHeartAttackEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderSheerHeartAttack extends MobRenderer<SheerHeartAttackEntity, ModelSheerHeartAttack<SheerHeartAttackEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sheer_heart_attack.png");

	public RenderSheerHeartAttack(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSheerHeartAttack<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final SheerHeartAttackEntity entity)
	{
		return RenderSheerHeartAttack.texture;
	}
}

