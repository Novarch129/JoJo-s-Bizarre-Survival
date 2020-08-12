package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;

public abstract class AbstractStandRenderer<T extends AbstractStandEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {
    public AbstractStandRenderer(EntityRendererManager manager, M model) {
        super(manager, model, 0);
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (Minecraft.getInstance().player == null) return;
        Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            if (props.getStandID() != 0)
                super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        });
    }
}
