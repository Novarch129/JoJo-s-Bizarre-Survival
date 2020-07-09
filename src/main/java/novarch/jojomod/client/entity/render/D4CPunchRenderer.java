package novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.D4CPunchModel;
import novarch.jojomod.entities.stands.attacks.D4CPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class D4CPunchRenderer extends StandPunchRenderer<D4CPunchEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c_punch.png");

	public D4CPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull D4CPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new D4CPunchModel());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final D4CPunchEntity entity) {
		return TEXTURE;
	}
}

