package com.novarch.jojomod.entities.stands.kingCrimson;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.novarch.jojomod.JojoMod;
import com.novarch.jojomod.entities.stands.EntityStandPunch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class RenderKingCrimsonPunch extends EntityRenderer<EntityStandPunch.kingCrimson>
{
	protected ModelKingCrimsonPunch punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoMod.MOD_ID, "textures/stands/kc_punch.png");

	public RenderKingCrimsonPunch(EntityRendererManager renderManagerIn) 
	{
		super(renderManagerIn);
		this.punch = new ModelKingCrimsonPunch();
	}

	@Override
	public void render(@Nonnull EntityStandPunch.kingCrimson entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		renderEntityModel(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public void renderEntityModel(@Nonnull EntityStandPunch.kingCrimson entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		/*this.renderManager.textureManager.bindTexture(texture);
		matrixStackIn.push();
		matrixStackIn.translate(entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ());
		matrixStackIn.rotate(new Quaternion(entityIn.prevRotationYaw + (entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks - 90.0F), 0.0F, 1.0F, 0.0F));
		matrixStackIn.rotate(new Quaternion(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F));
		float f10 = 0.05625F;
		float f11 = entityIn.arrowShake - packedLightIn;
		Minecraft.getInstance().textureManager.bindTexture(texture);
		matrixStackIn.rotate(new Quaternion(180.0f, 1.0f, 0.0f, 0.0f));
		matrixStackIn.rotate(new Quaternion(-90.0f, 0.0f, 1.0f, 0.0f));
		matrixStackIn.scale(2.0f, 2.0f, 2.0f);
		this.punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucent(getEntityTexture(entityIn))), packedLightIn, 1, 3.0f, 2.0f, 1.0f, 1.0f);
		matrixStackIn.pop();*/

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
		this.punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucent(getEntityTexture(entityIn))), packedLightIn, 1, 0.1f, 0.1f, 0.1f, 1.0f);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.kingCrimson entity)
	{
		return RenderKingCrimsonPunch.texture;
	}
}

