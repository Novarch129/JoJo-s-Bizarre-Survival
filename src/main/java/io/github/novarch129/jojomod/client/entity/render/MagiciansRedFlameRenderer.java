package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.client.entity.model.MagiciansRedFlameModel;
import io.github.novarch129.jojomod.entity.stand.attack.MagiciansRedFlameEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

import javax.annotation.Nonnull;

public class MagiciansRedFlameRenderer extends StandAttackRenderer<MagiciansRedFlameEntity> {
    public MagiciansRedFlameRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(@Nonnull MagiciansRedFlameEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (Minecraft.getInstance().player == null) return;
        MagiciansRedFlameModel flame = new MagiciansRedFlameModel();
        if (entityIn.isExplosive()) {
            flame.Flames.showModel = false;
            flame.Flames2.showModel = true;
        } else {
            flame.Flames.showModel = true;
            flame.Flames2.showModel = false;
        }
        Stand.getLazyOptional(Minecraft.getInstance().player).ifPresent(props -> {
            if (props.getStandID() != 0)
                super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, flame);
        });
    }
}

