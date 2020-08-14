package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.WeatherReportModel;
import io.github.novarch129.jojomod.entity.stand.WeatherReportEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WeatherReportRenderer extends AbstractStandRenderer<WeatherReportEntity, WeatherReportModel> {
    public WeatherReportRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeatherReportModel());
    }

    @Override
    public ResourceLocation getEntityTexture(WeatherReportEntity entity) {
        return Util.ResourceLocations.WEATHER_REPORT;
    }
}
