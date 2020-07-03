package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.AerosmithModel;
import com.novarch.jojomod.entities.stands.AerosmithEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AerosmithRenderer extends MobRenderer<AerosmithEntity, AerosmithModel<AerosmithEntity>>
{
    protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith.png");

    public AerosmithRenderer(EntityRendererManager manager)
    {
        super(manager, new AerosmithModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(AerosmithEntity entity)
    {
        return AerosmithRenderer.texture;
    }
}
