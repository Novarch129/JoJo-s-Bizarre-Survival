package novarch.jojomod.client.entity.render;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.GoldExperienceModel;
import novarch.jojomod.entities.stands.GoldExperienceEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GoldExperienceRenderer extends MobRenderer<GoldExperienceEntity, GoldExperienceModel<GoldExperienceEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");

	public GoldExperienceRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GoldExperienceModel<>(), 0.5f);
	}

	public ResourceLocation getEntityTexture(final GoldExperienceEntity entity) {
		return TEXTURE;
	}
}

