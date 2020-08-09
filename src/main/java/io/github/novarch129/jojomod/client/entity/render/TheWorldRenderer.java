package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TheWorldModel;
import io.github.novarch129.jojomod.entity.stand.TheWorldEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TheWorldRenderer extends MobRenderer<TheWorldEntity, TheWorldModel> {
    public TheWorldRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TheWorldModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TheWorldEntity entity) {
        return Util.ResourceLocations.THE_WORLD;
    }
}

