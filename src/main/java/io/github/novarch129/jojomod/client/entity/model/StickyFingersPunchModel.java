package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.StickyFingersPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class StickyFingersPunchModel extends AbstractStandAttackModel<StickyFingersPunchEntity> {
    private final ModelRenderer Punch;

    public StickyFingersPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.6463F, 21.5277F, -2.9537F);
        Punch.setTextureOffset(2, 2).addBox(-2.6463F, -2.0463F, -3.3756F, 4.0F, 4.0F, 11.0F, 0.0F, true);
        Punch.setTextureOffset(42, 0).addBox(0.089F, -2.4463F, -2.3022F, 3.0F, 5.0F, 8.0F, 0.0F, false);
        Punch.setTextureOffset(0, 0).addBox(2.3537F, -0.9686F, -1.3049F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        Punch.setTextureOffset(0, 0).addBox(2.2703F, -0.4463F, -0.4712F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        Punch.setTextureOffset(58, 26).addBox(-1.7793F, -2.5463F, -1.0021F, 2.0F, 5.0F, 1.0F, 0.0F, false);
        Punch.setTextureOffset(58, 26).addBox(-1.7873F, -2.5463F, 1.956F, 2.0F, 5.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}