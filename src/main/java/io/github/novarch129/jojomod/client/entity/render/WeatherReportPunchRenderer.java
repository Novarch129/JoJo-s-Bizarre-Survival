package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.WeatherReportPunchModel;
import io.github.novarch129.jojomod.entity.stand.attack.WeatherReportPunchEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class WeatherReportPunchRenderer extends StandPunchRenderer<WeatherReportPunchEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report_punch.png");

    public WeatherReportPunchRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(@Nonnull WeatherReportPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new WeatherReportPunchModel());
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(WeatherReportPunchEntity entity) {
        return TEXTURE;
    }
}

