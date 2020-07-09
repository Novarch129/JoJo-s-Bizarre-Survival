package novarch.jojomod.client.entity.render;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.client.entity.model.SheerHeartAttackModel;
import novarch.jojomod.entities.stands.SheerHeartAttackEntity;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SheerHeartAttackRenderer extends MobRenderer<SheerHeartAttackEntity, SheerHeartAttackModel<SheerHeartAttackEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sheer_heart_attack.png");

	public SheerHeartAttackRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SheerHeartAttackModel<>(), 0.5f);
	}

	@Nonnull
	@Override
	public ResourceLocation getEntityTexture(SheerHeartAttackEntity entity) {
		return TEXTURE;
	}
}

