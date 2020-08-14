package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.MagiciansRedModel;
import io.github.novarch129.jojomod.entity.stand.MagiciansRedEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MagiciansRedRenderer extends AbstractStandRenderer<MagiciansRedEntity, MagiciansRedModel> {
    public MagiciansRedRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MagiciansRedModel());
    }

    @Override
    public ResourceLocation getEntityTexture(MagiciansRedEntity entity) {
        return Util.ResourceLocations.MAGICIANS_RED;
    }
}

