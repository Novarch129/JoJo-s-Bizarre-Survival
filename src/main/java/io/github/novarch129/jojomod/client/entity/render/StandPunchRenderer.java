package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public abstract class StandPunchRenderer<T extends AbstractStandAttackEntity> extends EntityRenderer<T> {
    protected StandPunchRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @SuppressWarnings("unused")
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityModel<T> punch) {
        renderManager.textureManager.bindTexture(getEntityTexture(entityIn));
        matrixStackIn.push();
        matrixStackIn.translate((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
        matrixStackIn.rotate(new Quaternion(0, 1, 0, MathHelper.lerp(entityIn.standMaster.rotationYaw, entityIn.standMaster.prevRotationYaw, partialTicks)));
        matrixStackIn.rotate(new Quaternion(1, 0, 0, entityIn.standMaster.rotationPitch));
        matrixStackIn.scale(2, 2, 2);
        matrixStackIn.pop();
        punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0, 0, 0, 0, 0);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Deprecated //Safe to @Override, do not call, use method above instead, it's the entire purpose of this class
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public abstract ResourceLocation getEntityTexture(T entity);
}
