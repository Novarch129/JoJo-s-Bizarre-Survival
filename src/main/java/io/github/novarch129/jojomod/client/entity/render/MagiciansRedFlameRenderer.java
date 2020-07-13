package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.entity.stands.attacks.MagiciansRedFlameEntity;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.client.entity.model.MagiciansRedFlameModel;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MagiciansRedFlameRenderer extends StandPunchRenderer<MagiciansRedFlameEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red_flames.png");

	public MagiciansRedFlameRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull MagiciansRedFlameEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		MagiciansRedFlameModel flame = new MagiciansRedFlameModel();
		if (entityIn.isExplosive()) {
			flame.Flames.showModel = false;
			flame.Flames2.showModel = true;
		} else {
			flame.Flames.showModel = true;
			flame.Flames2.showModel = false;
		}
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, flame);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final MagiciansRedFlameEntity entity) {
		return TEXTURE;
	}
}

