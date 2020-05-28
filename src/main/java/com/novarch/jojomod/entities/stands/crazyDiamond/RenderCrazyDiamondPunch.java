package com.novarch.jojomod.entities.stands.crazyDiamond;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.crazyDiamond.ModelCrazyDiamondPunch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class RenderCrazyDiamondPunch extends EntityRenderer<EntityStandPunch.crazyDiamond>
{
	protected ModelCrazyDiamondPunch punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen_punch.png");

	public RenderCrazyDiamondPunch(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new ModelCrazyDiamondPunch();
	}

	@Override
	public void render(@Nonnull EntityStandPunch.crazyDiamond entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		renderEntityModel(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public void renderEntityModel(@Nonnull EntityStandPunch.crazyDiamond entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		this.renderManager.textureManager.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
		GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * packedLightIn - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * packedLightIn, 0.0F, 0.0F, 1.0F);
		float f10 = 0.05625F;
		GL11.glEnable(32826);
		float f11 = entityIn.arrowShake - packedLightIn;
		Minecraft.getInstance().textureManager.bindTexture(texture);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		this.punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityNoOutline(getEntityTexture(entityIn))), packedLightIn, 0);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.crazyDiamond entity)
	{
		return RenderCrazyDiamondPunch.texture;
	}
}

