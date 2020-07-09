package novarch.jojomod.client.entity.render;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.KillerQueenModel;
import novarch.jojomod.entities.stands.KillerQueenEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KillerQueenRenderer extends MobRenderer<KillerQueenEntity, KillerQueenModel<KillerQueenEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");

	public KillerQueenRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new KillerQueenModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(final KillerQueenEntity entity) {
		return KillerQueenRenderer.TEXTURE;
	}
}

