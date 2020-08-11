package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.KillerQueenModel;
import io.github.novarch129.jojomod.entity.stand.KillerQueenEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class KillerQueenRenderer extends AbstractStandRenderer<KillerQueenEntity, KillerQueenModel> {
    public KillerQueenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KillerQueenModel());
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(KillerQueenEntity entity) {
        return Util.ResourceLocations.KILLER_QUEEN;
    }
}

