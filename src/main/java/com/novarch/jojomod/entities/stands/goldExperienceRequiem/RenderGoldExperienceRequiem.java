package com.novarch.jojomod.entities.stands.goldExperienceRequiem;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.novarch.jojomod.JojoBizarreSurvival;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGoldExperienceRequiem extends MobRenderer<EntityGoldExperienceRequiem, ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>>
{
	boolean yes = false;
	private ModelGoldExperienceRequiem requiem;
	protected static final ResourceLocation texture = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");

	public RenderGoldExperienceRequiem(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new ModelGoldExperienceRequiem<EntityGoldExperienceRequiem>(), 0.5f);
		this.requiem = new ModelGoldExperienceRequiem();
	}

	@Override
	public ResourceLocation getEntityTexture(final EntityGoldExperienceRequiem entity)
	{
		return RenderGoldExperienceRequiem.texture;
	}

	@Override
	public void render(EntityGoldExperienceRequiem entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		if(!yes)
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		else
		{
			this.renderManager.textureManager.bindTexture(texture);
			GL11.glPushMatrix();
			GL11.glTranslatef((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
			GL11.glRotatef(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * packedLightIn - 90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * packedLightIn, 0.0F, 0.0F, 1.0F);
			float f10 = 0.05625F;
			GL11.glEnable(32826);
			Minecraft.getInstance().textureManager.bindTexture(texture);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			this.requiem.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityNoOutline(getEntityTexture(entityIn))), packedLightIn, 0, 1, 1, 1, 1);
			GL11.glDisable(32826);
			GL11.glPopMatrix();
		}
	}
}

