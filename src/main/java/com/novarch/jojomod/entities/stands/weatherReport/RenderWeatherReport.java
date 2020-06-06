package com.novarch.jojomod.entities.stands.weatherReport;

import com.novarch.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderWeatherReport extends MobRenderer<EntityWeatherReport, ModelWeatherReport<EntityWeatherReport>>
{
    public static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report.png");

    public RenderWeatherReport(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelWeatherReport<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityWeatherReport entity)
    {
        return RenderWeatherReport.texture;
    }
}
