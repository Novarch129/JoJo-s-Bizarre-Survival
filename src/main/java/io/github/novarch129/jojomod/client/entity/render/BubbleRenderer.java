package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.client.entity.model.BubbleModel;
import io.github.novarch129.jojomod.entity.stand.attack.BubbleEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class BubbleRenderer extends LivingRenderer<BubbleEntity, BubbleModel> {
    public BubbleRenderer(EntityRendererManager manager) {
        super(manager, new BubbleModel(), 0);
    }

    @Override
    public ResourceLocation getEntityTexture(BubbleEntity entity) {
        return Util.ResourceLocations.BUBBLE;
    }

    @Override
    protected void renderName(BubbleEntity p_225629_1_, String p_225629_2_, MatrixStack p_225629_3_, IRenderTypeBuffer p_225629_4_, int p_225629_5_) {
    }
}
