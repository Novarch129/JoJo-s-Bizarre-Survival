package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.SoftAndWetPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SoftAndWetPunchModel extends AbstractStandAttackModel<SoftAndWetPunchEntity> {
    private final ModelRenderer Punch;

    public SoftAndWetPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(-6, 16, 4);
        Punch.setTextureOffset(2, 3).addBox(4, -16, -10, 4, 4, 10, 0, false);
        Punch.setTextureOffset(0, 0).addBox(4.5F, -15.5F, 0, 3, 3, 2, 0, false);
        Punch.setTextureOffset(22, 0).addBox(7.975F, -17, -10.175F, 1, 6, 6, 0, false);
        Punch.setTextureOffset(22, 0).addBox(6.975F, -17, -10.175F, 1, 1, 6, 0, false);
        Punch.setTextureOffset(22, 0).addBox(6.975F, -12, -10.175F, 1, 1, 6, 0, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}