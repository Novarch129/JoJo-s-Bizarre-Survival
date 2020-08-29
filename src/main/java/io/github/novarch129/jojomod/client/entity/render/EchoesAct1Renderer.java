package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.EchoesAct1Model;
import io.github.novarch129.jojomod.entity.stand.EchoesAct1Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EchoesAct1Renderer extends AbstractStandRenderer<EchoesAct1Entity, EchoesAct1Model> {
    public EchoesAct1Renderer(EntityRendererManager manager) {
        super(manager, new EchoesAct1Model());
    }

    @Override
    public ResourceLocation getEntityTexture(EchoesAct1Entity entity) {
        return Util.ResourceLocations.ECHOES_ACT_1;
    }
}
