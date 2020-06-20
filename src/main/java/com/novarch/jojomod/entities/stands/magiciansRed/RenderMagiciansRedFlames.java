package com.novarch.jojomod.entities.stands.magiciansRed;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class RenderMagiciansRedFlames extends EntityRenderer<EntityStandPunch.MagiciansRed>
{
	protected ModelMagiciansRedFlames<EntityStandPunch.MagiciansRed> punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red_flames.png");

	public RenderMagiciansRedFlames(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new ModelMagiciansRedFlames<>();
	}

	@Override
	public void render(@Nonnull EntityStandPunch.MagiciansRed entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		renderEntityModel(entityIn, matrixStackIn, bufferIn, packedLightIn);
	}

	public void renderEntityModel(@Nonnull EntityStandPunch.MagiciansRed entityIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		if(entityIn.isExplosive()) {
			LogManager.getLogger().debug("model good");
			punch.Flames.showModel = false;
			punch.Flames2.showModel = true;
		} else {
			punch.Flames.showModel = true;
			punch.Flames2.showModel = false;
		}
		entityIn.rotationYaw = entityIn.standMaster.rotationYaw;
		entityIn.rotationPitch = entityIn.standMaster.rotationPitch;
		renderManager.textureManager.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
		GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * packedLightIn - 90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * packedLightIn, 0.0F, 0.0F, 1.0F);
		GL11.glEnable(32826);
		Minecraft.getInstance().textureManager.bindTexture(texture);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.MagiciansRed entity)
	{
		return RenderMagiciansRedFlames.texture;
	}
}

