package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.TheHandPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheHandPunchModel extends AbstractStandAttackModel<TheHandPunchEntity> {
    private final ModelRenderer Hand;

    public TheHandPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Hand = new ModelRenderer(this);
        Hand.setRotationPoint(0.0F, 4.0F, 0.0F);
        Hand.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ModelRenderer elbowPad1 = new ModelRenderer(this);
        elbowPad1.setRotationPoint(4.0F, -1.3954F, -7.0899F);
        Hand.addChild(elbowPad1);
        setRotationAngle(elbowPad1, -1.5708F, 0.0F, -3.1416F);
        elbowPad1.setTextureOffset(51, 2).addBox(5.6864F, -9.014F, -1.2511F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        elbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, -1.5261F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        elbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, -1.5011F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad1.setTextureOffset(51, 2).addBox(1.6864F, -8.014F, 1.9239F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Hand;
    }
}