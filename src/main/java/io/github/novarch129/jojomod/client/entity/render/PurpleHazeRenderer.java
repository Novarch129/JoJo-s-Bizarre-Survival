package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.PurpleHazeModel;
import io.github.novarch129.jojomod.entity.stand.PurpleHazeEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PurpleHazeRenderer extends MobRenderer<PurpleHazeEntity, PurpleHazeModel> {
    public PurpleHazeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PurpleHazeModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(PurpleHazeEntity entity) {
        return Util.ResourceLocations.PURPLE_HAZE;
    }
}

