package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.client.entity.model.EmperorBulletModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.attack.EmperorBulletEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class EmperorBulletRenderer extends EntityRenderer<EmperorBulletEntity> {
    protected EmperorBulletModel bullet;

    public EmperorBulletRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        bullet = new EmperorBulletModel();
    }

    @Override
    public void render(EmperorBulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (Minecraft.getInstance().renderViewEntity instanceof AbstractStandEntity)
            render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        else if (Minecraft.getInstance().renderViewEntity instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) Minecraft.getInstance().renderViewEntity).ifPresent(props -> {
                if (props.getStandID() != 0)
                    renderModel(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            });
    }

    private void renderModel(EmperorBulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        renderManager.textureManager.bindTexture(getEntityTexture(entityIn));
        matrixStackIn.push();
        matrixStackIn.translate((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
        matrixStackIn.scale(2, 2, 2);
        matrixStackIn.pop();
        bullet.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0, 0, 0, 0, 0);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(final EmperorBulletEntity entity) {
        return Util.ResourceLocations.EMPEROR_BULLET;
    }
}

