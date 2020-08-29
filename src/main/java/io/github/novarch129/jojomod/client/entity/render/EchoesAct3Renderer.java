package io.github.novarch129.jojomod.client.entity.render;

import io.github.novarch129.jojomod.client.entity.model.EchoesAct3Model;
import io.github.novarch129.jojomod.entity.stand.EchoesAct3Entity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EchoesAct3Renderer extends AbstractStandRenderer<EchoesAct3Entity, EchoesAct3Model> {
    public EchoesAct3Renderer(EntityRendererManager manager) {
        super(manager, new EchoesAct3Model());
    }

    @Override
    public ResourceLocation getEntityTexture(EchoesAct3Entity entity) {
        return Util.ResourceLocations.ECHOES_ACT_3;
    }
}
