package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Handles the head rotation of {@link AbstractStandEntity}s.
 */
public abstract class AbstractStandModel<T extends AbstractStandEntity> extends EntityModel<T> {
    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        setRotationAngle(getHead(), headPitch / 90, netHeadYaw / 90, getHead().rotateAngleZ);
    }

    protected void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    protected abstract ModelRenderer getHead();
}
