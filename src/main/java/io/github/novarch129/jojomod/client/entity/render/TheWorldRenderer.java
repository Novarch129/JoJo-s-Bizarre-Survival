package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.TheWorldModel;
import io.github.novarch129.jojomod.entity.stands.TheWorldEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TheWorldRenderer extends MobRenderer<TheWorldEntity, TheWorldModel<TheWorldEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world.png");

	public TheWorldRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new TheWorldModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final TheWorldEntity entity) {
		return TEXTURE;
	}
}

