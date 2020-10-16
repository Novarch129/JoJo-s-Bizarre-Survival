package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.SilverChariotSwordEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SilverChariotSwordModel extends AbstractStandAttackModel<SilverChariotSwordEntity> {
    private final ModelRenderer Sword;

    public SilverChariotSwordModel() {
        textureWidth = 64;
        textureHeight = 32;

        Sword = new ModelRenderer(this);
        Sword.setRotationPoint(0.0F, 5.0F, 1.0F);
        Sword.setTextureOffset(0, 0).addBox(-0.5F, -2.0F, 3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        Sword.setTextureOffset(0, 0).addBox(-0.5F, -2.0F, -16.0F, 1.0F, 1.0F, 18.0F, 0.0F, false);
        Sword.setTextureOffset(0, 8).addBox(-1.5F, -3.0F, 2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Sword;
    }
}