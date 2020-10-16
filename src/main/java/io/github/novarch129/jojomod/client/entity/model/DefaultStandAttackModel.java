package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DefaultStandAttackModel<T extends AbstractStandAttackEntity> extends AbstractStandAttackModel<T> {
    private final ModelRenderer Punch;

    public DefaultStandAttackModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0, 24, 0);
        Punch.setTextureOffset(0, 0).addBox(-2, -24, -6, 4, 4, 12, 0, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}
