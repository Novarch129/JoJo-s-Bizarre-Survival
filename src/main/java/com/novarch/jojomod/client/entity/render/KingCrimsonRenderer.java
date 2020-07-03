package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.KingCrimsonModel;
import com.novarch.jojomod.entities.stands.KingCrimsonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KingCrimsonRenderer extends MobRenderer<KingCrimsonEntity, KingCrimsonModel<KingCrimsonEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png");

	public KingCrimsonRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new KingCrimsonModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final KingCrimsonEntity entity) {
		return TEXTURE;
	}
}

