package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.CMoonModel;
import io.github.novarch129.jojomod.entity.stand.CMoonEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CMoonRenderer extends MobRenderer<CMoonEntity, CMoonModel<CMoonEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon.png");

	public CMoonRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CMoonModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final CMoonEntity entity) {
		return TEXTURE;
	}
}

