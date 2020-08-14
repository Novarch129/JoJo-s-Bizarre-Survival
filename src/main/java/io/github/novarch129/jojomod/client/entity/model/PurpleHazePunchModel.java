package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.PurpleHazePunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PurpleHazePunchModel extends AbstractStandAttackModel<PurpleHazePunchEntity> {
    private final ModelRenderer Punch;

    public PurpleHazePunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ModelRenderer elbowPad = new ModelRenderer(this);
        elbowPad.setRotationPoint(-0.6028F, 24.0006F, -5.1954F);
        Punch.addChild(elbowPad);
        elbowPad.setTextureOffset(0, 28).addBox(-1.2577F, -23.8977F, 5.1482F, 4.0F, 1.0F, 3.0F, 0.0F, false);
        elbowPad.setTextureOffset(0, 17).addBox(-0.9827F, -28.3977F, 6.1482F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad.setTextureOffset(0, 20).addBox(-1.4827F, -28.3977F, 6.1482F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad.setTextureOffset(0, 20).addBox(1.9923F, -27.8977F, 6.1482F, 1.0F, 5.0F, 1.0F, 0.0F, false);
        elbowPad.setTextureOffset(0, 20).addBox(-1.4327F, -27.8977F, 6.1482F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        ModelRenderer capsules1 = new ModelRenderer(this);
        capsules1.setRotationPoint(-5.1874F, -5.4686F, -7.1988F);
        Punch.addChild(capsules1);
        capsules1.setTextureOffset(22, 30).addBox(2.6714F, 1.6204F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        capsules1.setTextureOffset(22, 30).addBox(2.6714F, 2.9954F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
        capsules1.setTextureOffset(22, 30).addBox(2.6714F, 4.3704F, 2.7452F, 1.0F, 1.0F, 1.0F, 0.0F, true);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}