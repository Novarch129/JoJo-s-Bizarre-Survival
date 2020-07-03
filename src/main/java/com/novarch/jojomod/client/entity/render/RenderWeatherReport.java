package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.ModelWeatherReport;
import com.novarch.jojomod.entities.stands.WeatherReportEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderWeatherReport extends MobRenderer<WeatherReportEntity, ModelWeatherReport<WeatherReportEntity>>
{
    public static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report.png");

    public RenderWeatherReport(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new ModelWeatherReport<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(WeatherReportEntity entity)
    {
        return RenderWeatherReport.texture;
    }
}
