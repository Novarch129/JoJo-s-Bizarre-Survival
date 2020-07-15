package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.SilverChariotModel;
import io.github.novarch129.jojomod.entity.stand.SilverChariotEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class SilverChariotRenderer extends MobRenderer<SilverChariotEntity, SilverChariotModel<SilverChariotEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/silver_chariot.png");

	public SilverChariotRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SilverChariotModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(SilverChariotEntity entity) {
		return TEXTURE;
	}
}

