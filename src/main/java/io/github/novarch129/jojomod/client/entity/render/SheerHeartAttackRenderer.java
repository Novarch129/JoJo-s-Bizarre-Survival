package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.client.entity.model.SheerHeartAttackModel;
import io.github.novarch129.jojomod.entity.stand.SheerHeartAttackEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SheerHeartAttackRenderer extends MobRenderer<SheerHeartAttackEntity, SheerHeartAttackModel> {
    public SheerHeartAttackRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SheerHeartAttackModel(), 0);
    }

    @Override
    public void render(SheerHeartAttackEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (Minecraft.getInstance().player == null) return;
        Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            if (props.getStandID() != 0)
                super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        });
    }

    @Override
    public ResourceLocation getEntityTexture(SheerHeartAttackEntity entity) {
        return Util.ResourceLocations.SHEER_HEART_ATTACK;
    }
}

