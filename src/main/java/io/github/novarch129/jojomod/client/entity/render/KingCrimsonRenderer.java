package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.KingCrimsonModel;
import io.github.novarch129.jojomod.entity.stands.KingCrimsonEntity;
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

