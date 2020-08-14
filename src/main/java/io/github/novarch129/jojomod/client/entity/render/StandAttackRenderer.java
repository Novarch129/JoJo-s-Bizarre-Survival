package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class StandAttackRenderer<T extends AbstractStandAttackEntity> extends EntityRenderer<T> {
    public StandAttackRenderer(EntityRendererManager manager) {
        super(manager);
    }

    protected void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityModel<T> model) {
        renderManager.textureManager.bindTexture(getEntityTexture(entityIn));
        matrixStackIn.push();
        matrixStackIn.translate((float) entityIn.getPosX(), (float) entityIn.getPosY(), (float) entityIn.getPosZ());
        matrixStackIn.scale(2, 2, 2);
        matrixStackIn.pop();
        model.setRotationAngles(entityIn, 0, 0, partialTicks, entityIn.rotationYaw, -entityIn.rotationPitch); //Rotates the punch to face away from the Stand user,
        model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntitySmoothCutout(getEntityTexture(entityIn))), packedLightIn, 0, 0, 0, 0, 0);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (Minecraft.getInstance().renderViewEntity instanceof AbstractStandEntity)
            render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, entityIn.getEntityModel());
        else if (Minecraft.getInstance().renderViewEntity instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) Minecraft.getInstance().renderViewEntity).ifPresent(props -> {
                if (props.getStandID() != 0)
                    render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, entityIn.getEntityModel());
            });
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return entity.getEntityTexture();
    }
}
