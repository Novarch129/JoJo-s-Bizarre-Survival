package novarch.jojomod.client.entity.render;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.PurpleHazeModel;
import novarch.jojomod.entities.stands.PurpleHazeEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class PurpleHazeRenderer extends MobRenderer<PurpleHazeEntity, PurpleHazeModel<PurpleHazeEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze.png");

	public PurpleHazeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PurpleHazeModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(PurpleHazeEntity entity) {
		return PurpleHazeRenderer.TEXTURE;
	}
}

