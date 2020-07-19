package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.WeatherReportModel;
import io.github.novarch129.jojomod.entity.stand.WeatherReportEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WeatherReportRenderer extends MobRenderer<WeatherReportEntity, WeatherReportModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report.png");

    public WeatherReportRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeatherReportModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(WeatherReportEntity entity) {
        return TEXTURE;
    }
}
