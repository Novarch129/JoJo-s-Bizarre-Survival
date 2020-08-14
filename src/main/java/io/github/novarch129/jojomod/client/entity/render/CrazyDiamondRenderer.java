package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.CrazyDiamondModel;
import io.github.novarch129.jojomod.entity.stand.CrazyDiamondEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class CrazyDiamondRenderer extends AbstractStandRenderer<CrazyDiamondEntity, CrazyDiamondModel> {
    public CrazyDiamondRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrazyDiamondModel());
    }

    @Override
    public ResourceLocation getEntityTexture(CrazyDiamondEntity entity) {
        return Util.ResourceLocations.CRAZY_DIAMOND;
    }
}

