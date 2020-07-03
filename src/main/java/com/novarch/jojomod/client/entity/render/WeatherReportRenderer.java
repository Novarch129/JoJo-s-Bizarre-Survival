package com.novarch.jojomod.client.entity.render;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.WeatherReportModel;
import com.novarch.jojomod.entities.stands.WeatherReportEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WeatherReportRenderer extends MobRenderer<WeatherReportEntity, WeatherReportModel<WeatherReportEntity>>
{
    public static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report.png");

    public WeatherReportRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new WeatherReportModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(WeatherReportEntity entity)
    {
        return WeatherReportRenderer.texture;
    }
}
