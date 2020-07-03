package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.FakePlayerModel;
import com.novarch.jojomod.entities.FakePlayerEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FakePlayerRenderer extends MobRenderer<FakePlayerEntity, FakePlayerModel<FakePlayerEntity>> {
	public FakePlayerRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new FakePlayerModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(FakePlayerEntity entity) {
		return Minecraft.getInstance().player == null ? new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png") : Minecraft.getInstance().player.getLocationSkin(); //Weird backup for if player is null, hope that never happens
	}
}

