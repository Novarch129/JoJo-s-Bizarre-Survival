package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.DirtyDeedsDoneDirtCheapModel;
import com.novarch.jojomod.entities.stands.DirtyDeedsDoneDirtCheapEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DirtyDeedsDoneDirtCheapRenderer extends MobRenderer<DirtyDeedsDoneDirtCheapEntity, DirtyDeedsDoneDirtCheapModel<DirtyDeedsDoneDirtCheapEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c.png");

	public DirtyDeedsDoneDirtCheapRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new DirtyDeedsDoneDirtCheapModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(final DirtyDeedsDoneDirtCheapEntity entity) {
		return TEXTURE;
	}
}

