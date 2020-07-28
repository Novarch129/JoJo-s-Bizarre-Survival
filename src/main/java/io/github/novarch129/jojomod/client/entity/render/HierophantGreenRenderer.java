package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.HierophantGreenModel;
import io.github.novarch129.jojomod.entity.stand.HierophantGreenEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HierophantGreenRenderer extends MobRenderer<HierophantGreenEntity, HierophantGreenModel> {
    public HierophantGreenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HierophantGreenModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(HierophantGreenEntity entity) {
        return Util.ResourceLocations.HIEROPHANT_GREEN;
    }
}

