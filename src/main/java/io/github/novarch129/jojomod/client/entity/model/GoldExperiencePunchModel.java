package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.GoldExperiencePunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GoldExperiencePunchModel extends AbstractStandAttackModel<GoldExperiencePunchEntity> {
    private final ModelRenderer Punch;

    public GoldExperiencePunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(-4.7225F, 25.4467F, -8.6359F);
        setRotationAngle(Punch, -1.5621F, 0.0F, -0.0044F);
        Punch.setTextureOffset(0, 13).addBox(2.7025F, -14.5533F, -5.6359F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        ModelRenderer beetle2 = new ModelRenderer(this);
        beetle2.setRotationPoint(-13.2015F, -13.2356F, 1.85F);
        Punch.addChild(beetle2);

        ModelRenderer beetle6 = new ModelRenderer(this);
        beetle6.setRotationPoint(17.0F, 0.0F, 0.0F);
        beetle2.addChild(beetle6);
        beetle6.setTextureOffset(32, 8).addBox(2.249F, 6.5124F, -6.4702F, 1.0F, 3.0F, 2.0F, 0.0F, true);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}