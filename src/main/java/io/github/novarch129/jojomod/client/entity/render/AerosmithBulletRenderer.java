package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.entity.stand.attack.AerosmithBulletEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class AerosmithBulletRenderer extends EntityRenderer<AerosmithBulletEntity> {
    public AerosmithBulletRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void render(AerosmithBulletEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
    }

    @Override
    public ResourceLocation getEntityTexture(AerosmithBulletEntity entity) {
        return Util.ResourceLocations.AEROSMITH_BULLET;
    }
}
