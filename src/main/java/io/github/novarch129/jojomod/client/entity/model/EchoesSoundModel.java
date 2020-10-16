package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.EchoesSoundEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesSoundModel extends AbstractStandAttackModel<EchoesSoundEntity> {
    private final ModelRenderer SoundEffect;

    public EchoesSoundModel() {
        textureWidth = 64;
        textureHeight = 32;

        SoundEffect = new ModelRenderer(this);
        SoundEffect.setRotationPoint(0.0F, 4.25F, 0.0F);
        SoundEffect.setTextureOffset(0, 0).addBox(-4.0F, -1.25F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(48, 0).addBox(-4.0F, -4.85F, 0.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(0, 30).addBox(-2.25F, -7.35F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(60, 27).addBox(-0.25F, -4.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(19, 0).addBox(-1.25F, -8.1F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        ModelRenderer line1 = new ModelRenderer(this);
        line1.setRotationPoint(1.0F, -1.5F, 0.5F);
        SoundEffect.addChild(line1);
        setRotationAngle(line1, 0.0F, 0.0F, 0.7854F);
        line1.setTextureOffset(22, 9).addBox(-0.7071F, -1.9393F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        ModelRenderer line3 = new ModelRenderer(this);
        line3.setRotationPoint(-4.25F, -1.5F, 0.5F);
        SoundEffect.addChild(line3);
        setRotationAngle(line3, 0.0F, 0.0F, -0.7854F);
        line3.setTextureOffset(41, 10).addBox(-0.1161F, 0.2374F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        ModelRenderer line2 = new ModelRenderer(this);
        line2.setRotationPoint(-2.5F, -6.3F, 0.5F);
        SoundEffect.addChild(line2);
        setRotationAngle(line2, 0.0F, 0.0F, -0.7854F);
        line2.setTextureOffset(34, 21).addBox(-1.2828F, -0.9494F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return SoundEffect;
    }
}