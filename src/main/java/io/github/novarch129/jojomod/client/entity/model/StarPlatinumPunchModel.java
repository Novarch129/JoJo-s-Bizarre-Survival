package io.github.novarch129.jojomod.client.entity.model;


import io.github.novarch129.jojomod.entity.stand.attack.StarPlatinumPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class StarPlatinumPunchModel extends AbstractStandAttackModel<StarPlatinumPunchEntity> {
    private final ModelRenderer Punch;

    public StarPlatinumPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 4.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ModelRenderer elbowPad2 = new ModelRenderer(this);
        elbowPad2.setRotationPoint(-0.0828F, -1.3644F, 4.3044F);
        Punch.addChild(elbowPad2);
        setRotationAngle(elbowPad2, 1.5926F, 0.0131F, 1.5621F);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);
        elbowPad2.setTextureOffset(4, 29).addBox(-1.747F, -1.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad2.setTextureOffset(4, 29).addBox(-1.747F, -4.486F, -2.5709F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        elbowPad2.setTextureOffset(3, 21).addBox(-2.747F, -3.486F, -2.5709F, 4.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}