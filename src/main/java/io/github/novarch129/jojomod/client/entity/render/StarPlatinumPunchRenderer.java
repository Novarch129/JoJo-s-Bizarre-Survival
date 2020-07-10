package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.client.entity.model.StarPlatinumPunchModel;
import io.github.novarch129.jojomod.entity.stands.attacks.StarPlatinumPunchEntity;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StarPlatinumPunchRenderer extends StandPunchRenderer<StarPlatinumPunchEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum_punch.png");

	public StarPlatinumPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull StarPlatinumPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new StarPlatinumPunchModel());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(StarPlatinumPunchEntity entity) {
		return TEXTURE;
	}
}

