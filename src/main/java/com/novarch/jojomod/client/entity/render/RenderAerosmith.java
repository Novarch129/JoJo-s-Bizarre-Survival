package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelAerosmith;
import com.novarch.jojomod.entities.stands.AerosmithEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class RenderAerosmith extends MobRenderer<AerosmithEntity, ModelAerosmith<AerosmithEntity>>
{
    protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith.png");

    public RenderAerosmith(EntityRendererManager manager)
    {
        super(manager, new ModelAerosmith<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(AerosmithEntity entity)
    {
        return RenderAerosmith.texture;
    }
}
