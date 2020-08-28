package io.github.novarch129.jojomod.client.entity.model;


import io.github.novarch129.jojomod.entity.stand.attack.EchoesAct3PunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesAct3PunchModel extends AbstractStandAttackModel<EchoesAct3PunchEntity> {
    private final ModelRenderer Punch;

    public EchoesAct3PunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(3, 3).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);
        Punch.setTextureOffset(16, 0).addBox(-1.5F, -3.0F, 0.3F, 3.0F, 3.0F, 4.0F, 0.0F, false);
        Punch.setTextureOffset(0, 14).addBox(-1.0F, -2.5F, -0.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        Punch.setTextureOffset(0, 0).addBox(-1.0F, -3.125F, -4.25F, 2.0F, 1.0F, 2.0F, 0.0F, false);
        Punch.setTextureOffset(0, 0).addBox(-1.0F, -3.15F, 1.3F, 2.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}