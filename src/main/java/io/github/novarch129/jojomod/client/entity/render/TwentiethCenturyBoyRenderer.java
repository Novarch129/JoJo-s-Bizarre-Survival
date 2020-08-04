package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.DefaultStandModel;
import io.github.novarch129.jojomod.entity.stand.TwentiethCenturyBoyEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TwentiethCenturyBoyRenderer extends MobRenderer<TwentiethCenturyBoyEntity, DefaultStandModel<TwentiethCenturyBoyEntity>> {
    public TwentiethCenturyBoyRenderer(EntityRendererManager manager) {
        super(manager, new DefaultStandModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TwentiethCenturyBoyEntity entity) {
        return Util.ResourceLocations.TWENTIETH_CENTURY_BOY;
    }
}
