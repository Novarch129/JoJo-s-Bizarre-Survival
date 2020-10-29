package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.entity.stand.attack.BubbleEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BubbleRenderer extends EntityRenderer<BubbleEntity> {
    public BubbleRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(BubbleEntity entity) {
        return Util.ResourceLocations.SOFT_AND_WET_PUNCH;
    }
}
