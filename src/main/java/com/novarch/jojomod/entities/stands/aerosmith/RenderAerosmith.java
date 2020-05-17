package com.novarch.jojomod.entities.stands.aerosmith;

import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderAerosmith extends MobRenderer<EntityAerosmith, ModelAerosmith<EntityAerosmith>>
{
    protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith.png");

    public RenderAerosmith(EntityRendererManager manager)
    {
        super(manager, new ModelAerosmith<EntityAerosmith>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityAerosmith entity)
    {
        return RenderAerosmith.texture;
    }
}
