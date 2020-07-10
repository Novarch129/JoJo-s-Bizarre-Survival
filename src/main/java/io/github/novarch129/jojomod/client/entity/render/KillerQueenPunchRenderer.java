package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.client.entity.model.KillerQueenPunchModel;
import io.github.novarch129.jojomod.entity.stands.attacks.KillerQueenPunchEntity;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KillerQueenPunchRenderer extends StandPunchRenderer<KillerQueenPunchEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen_punch.png");

	public KillerQueenPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull KillerQueenPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new KillerQueenPunchModel());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final KillerQueenPunchEntity entity) {
		return TEXTURE;
	}
}

