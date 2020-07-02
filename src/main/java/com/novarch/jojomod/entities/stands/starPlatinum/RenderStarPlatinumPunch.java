package com.novarch.jojomod.entities.stands.starPlatinum;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RenderStarPlatinumPunch extends EntityRenderer<EntityStandPunch.StarPlatinum>
{
	protected ModelStarPlatinumPunch<EntityStandPunch.StarPlatinum> punch;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum_punch.png");

	public RenderStarPlatinumPunch(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn);
		this.punch = new ModelStarPlatinumPunch<>();
	}

	@Override
	public void render(@Nonnull EntityStandPunch.StarPlatinum entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		renderEntityModel(entityIn, matrixStackIn, bufferIn, packedLightIn, partialTicks);
	}

	public void renderEntityModel(@Nonnull EntityStandPunch.StarPlatinum entityIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float partialTicks)
	{
		renderManager.textureManager.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
		GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks - 90, 0, 1, 0);
//		GL11.glRotatef(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * packedLightIn, 0.0F, 0.0F, 1.0F);
		GL11.glEnable(32826);
		Minecraft.getInstance().textureManager.bindTexture(texture);
//		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
//		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		punch.Punch.mirror = entityIn.world.rand.nextInt(2) == 1;
		punch.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0);
		GL11.glDisable(32826);
		GL11.glPopMatrix();
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final EntityStandPunch.StarPlatinum entity)
	{
		return RenderStarPlatinumPunch.texture;
	}
}

