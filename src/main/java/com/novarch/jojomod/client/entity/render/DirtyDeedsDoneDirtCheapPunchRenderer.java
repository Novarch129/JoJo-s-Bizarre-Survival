package com.novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.client.entity.model.DirtyDeedsDoneDirtCheapPunchModel;
import com.novarch.jojomod.entities.stands.AbstractStandPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DirtyDeedsDoneDirtCheapPunchRenderer extends EntityRenderer<AbstractStandPunchEntity.DirtyDeedsDoneDirtCheap>
{
	protected DirtyDeedsDoneDirtCheapPunchModel<AbstractStandPunchEntity.DirtyDeedsDoneDirtCheap> punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c_punch.png");

	public DirtyDeedsDoneDirtCheapPunchRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new DirtyDeedsDoneDirtCheapPunchModel<>();
	}

	@Override
	public void render(@Nonnull AbstractStandPunchEntity.DirtyDeedsDoneDirtCheap entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		renderEntityModel(entityIn, matrixStackIn, bufferIn, packedLightIn);
	}

	public void renderEntityModel(@Nonnull AbstractStandPunchEntity.DirtyDeedsDoneDirtCheap entityIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		this.renderManager.textureManager.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
		GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * packedLightIn - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * packedLightIn, 0.0F, 0.0F, 1.0F);
		GL11.glEnable(32826);
		Minecraft.getInstance().textureManager.bindTexture(texture);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		this.punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final AbstractStandPunchEntity.DirtyDeedsDoneDirtCheap entity)
	{
		return DirtyDeedsDoneDirtCheapPunchRenderer.texture;
	}
}

