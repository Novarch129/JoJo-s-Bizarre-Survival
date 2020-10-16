package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.entity.stand.attack.EmeraldSplashEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class EmeraldSplashRenderer extends EntityRenderer<EmeraldSplashEntity> {
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean field_229126_f_;

    public EmeraldSplashRenderer(EntityRendererManager manager, ItemRenderer renderer, float scale, boolean b) {
        super(manager);
        this.itemRenderer = renderer;
        this.scale = scale;
        this.field_229126_f_ = b;
    }

    public EmeraldSplashRenderer(EntityRendererManager manager, ItemRenderer renderer) {
        this(manager, renderer, 1, false);
    }

    @Override
    protected int getBlockLight(EmeraldSplashEntity entity, float partialTicks) {
        return this.field_229126_f_ ? 15 : super.getBlockLight(entity, partialTicks);
    }

    public void render(EmeraldSplashEntity entity, float p_225623_2_, float p_225623_3_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLightIn) {
        matrixStack.push();
        matrixStack.scale(this.scale, this.scale, this.scale);
        matrixStack.rotate(this.renderManager.getCameraOrientation());
        matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
        itemRenderer.renderItem(new ItemStack(Items.EMERALD), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
        matrixStack.pop();
        super.render(entity, p_225623_2_, p_225623_3_, matrixStack, buffer, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(EmeraldSplashEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
    }
}
