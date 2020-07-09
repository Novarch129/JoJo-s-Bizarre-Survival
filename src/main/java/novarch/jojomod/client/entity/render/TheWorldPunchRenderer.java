package novarch.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.TheWorldPunchModel;
import novarch.jojomod.entities.stands.attacks.TheWorldPunchEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TheWorldPunchRenderer extends StandPunchRenderer<TheWorldPunchEntity> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world_punch.png");

	public TheWorldPunchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(@Nonnull TheWorldPunchEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, new TheWorldPunchModel());
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(TheWorldPunchEntity entity) {
		return TEXTURE;
	}
}

