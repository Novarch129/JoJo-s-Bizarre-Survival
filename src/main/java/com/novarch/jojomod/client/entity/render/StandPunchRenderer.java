package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class StandPunchRenderer<T extends AbstractStandPunchEntity> extends EntityRenderer<T> {
    protected StandPunchRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @SuppressWarnings("unused")
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityModel<T> punch) {
        renderManager.textureManager.bindTexture(getEntityTexture(entityIn));
        GL11.glPushMatrix();
        GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
        GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * packedLightIn - 90, 0, 1, 0);
        Minecraft.getInstance().textureManager.bindTexture(getEntityTexture(entityIn));
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0, 0, 0, 0, 0);
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }

    @Deprecated //Safe to @Override, do not call, use method above instead, it's the entire purpose of this class
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public abstract ResourceLocation getEntityTexture(T entity);
}
