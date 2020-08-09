package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.KingCrimsonModel;
import io.github.novarch129.jojomod.entity.stand.KingCrimsonEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KingCrimsonRenderer extends MobRenderer<KingCrimsonEntity, KingCrimsonModel> {
    public KingCrimsonRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KingCrimsonModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(KingCrimsonEntity entity) {
        return Util.ResourceLocations.KING_CRIMSON;
    }
}

