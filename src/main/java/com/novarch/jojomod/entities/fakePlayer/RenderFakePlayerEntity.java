package com.novarch.jojomod.entities.fakePlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderFakePlayerEntity extends MobRenderer<FakePlayerEntity, ModelFakePlayerEntity<FakePlayerEntity>>
{
	public RenderFakePlayerEntity(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelFakePlayerEntity<FakePlayerEntity>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(final FakePlayerEntity entity)
	{
		if(Minecraft.getInstance().player == null)
			return null;
		else
			return Minecraft.getInstance().player.getLocationSkin();
	}
}

