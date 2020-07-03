package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.client.entity.model.FakePlayerModel;
import com.novarch.jojomod.entities.FakePlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FakePlayerRenderer extends MobRenderer<FakePlayerEntity, FakePlayerModel<FakePlayerEntity>>
{
	public FakePlayerRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new FakePlayerModel<FakePlayerEntity>(), 0.5f);
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

