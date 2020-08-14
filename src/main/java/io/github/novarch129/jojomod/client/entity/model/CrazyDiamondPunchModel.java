package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.CrazyDiamondPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class CrazyDiamondPunchModel extends AbstractStandAttackModel<CrazyDiamondPunchEntity> {
    private final ModelRenderer Punch;

    public CrazyDiamondPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ModelRenderer elbowPad1 = new ModelRenderer(this);
        elbowPad1.setRotationPoint(0.2614F, -2.314F, 0.1614F);
        Punch.addChild(elbowPad1);
        setRotationAngle(elbowPad1, -1.5708F, 0.0F, 0.0F);
        elbowPad1.setTextureOffset(30, 25).addBox(1.5F, -1.5F, -1.9125F, 1.0F, 3.0F, 4.0F, 0.0F, false);
        elbowPad1.setTextureOffset(53, 16).addBox(-2.5F, -0.5F, -2.1875F, 1.0F, 1.0F, 4.0F, 0.0F, false);
        elbowPad1.setTextureOffset(50, 28).addBox(-2.5F, -0.5F, -2.1625F, 5.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad1.setTextureOffset(49, 29).addBox(-2.5F, -0.5F, 1.4625F, 5.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}