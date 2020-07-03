package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.DirtyDeedsDoneDirtCheapEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderDirtyDeedsDoneDirtCheap extends MobRenderer<DirtyDeedsDoneDirtCheapEntity, ModelDirtyDeedsDoneDirtCheap<DirtyDeedsDoneDirtCheapEntity>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c.png");

	public RenderDirtyDeedsDoneDirtCheap(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDirtyDeedsDoneDirtCheap<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(final DirtyDeedsDoneDirtCheapEntity entity)
	{
		return RenderDirtyDeedsDoneDirtCheap.texture;
	}
}

