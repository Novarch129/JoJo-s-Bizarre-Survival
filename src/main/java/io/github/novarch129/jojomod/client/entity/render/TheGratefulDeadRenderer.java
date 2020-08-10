package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.DefaultStandModel;
import io.github.novarch129.jojomod.entity.stand.TheGratefulDeadEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TheGratefulDeadRenderer extends MobRenderer<TheGratefulDeadEntity, DefaultStandModel<TheGratefulDeadEntity>> {
    public TheGratefulDeadRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new DefaultStandModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TheGratefulDeadEntity entity) {
        return Util.ResourceLocations.THE_GRATEFUL_DEAD;
    }
}

