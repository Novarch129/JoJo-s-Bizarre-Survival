package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.TheGratefulDeadModel;
import io.github.novarch129.jojomod.entity.stand.TheGratefulDeadEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TheGratefulDeadRenderer extends AbstractStandRenderer<TheGratefulDeadEntity, TheGratefulDeadModel> {
    public TheGratefulDeadRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new TheGratefulDeadModel());
    }

    @Override
    public ResourceLocation getEntityTexture(TheGratefulDeadEntity entity) {
        return Util.ResourceLocations.THE_GRATEFUL_DEAD;
    }
}

