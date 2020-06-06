package com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderDirtyDeedsDoneDirtCheap extends MobRenderer<EntityDirtyDeedsDoneDirtCheap, ModelDirtyDeedsDoneDirtCheap<EntityDirtyDeedsDoneDirtCheap>>
{
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c.png");

	public RenderDirtyDeedsDoneDirtCheap(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDirtyDeedsDoneDirtCheap<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(final EntityDirtyDeedsDoneDirtCheap entity)
	{
		return RenderDirtyDeedsDoneDirtCheap.texture;
	}
}

