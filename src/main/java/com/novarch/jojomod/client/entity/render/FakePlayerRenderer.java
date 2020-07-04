package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.FakePlayerModel;
import com.novarch.jojomod.entities.FakePlayerEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FakePlayerRenderer extends MobRenderer<FakePlayerEntity, FakePlayerModel<FakePlayerEntity>> {
	public FakePlayerRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FakePlayerModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(FakePlayerEntity entity) {
		return entity.getParent() == null && entity.getParent() instanceof ClientPlayerEntity ? new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png") : ((ClientPlayerEntity) entity.getParent()).getLocationSkin(); //Weird backup for if player is null, hope that never happens
	}
}

