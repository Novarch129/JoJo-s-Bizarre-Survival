package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.EchoesSoundEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class EchoesSoundModel extends AbstractStandAttackModel<EchoesSoundEntity> {
    private final ModelRenderer SoundEffect;
    private final ModelRenderer Line1;
    private final ModelRenderer Line3;
    private final ModelRenderer Line2;

    public EchoesSoundModel() {
        textureWidth = 64;
        textureHeight = 32;

        SoundEffect = new ModelRenderer(this);
        SoundEffect.setRotationPoint(0.0F, 24.25F, 0.0F);
        SoundEffect.setTextureOffset(0, 0).addBox(-4.0F, -1.25F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(48, 0).addBox(-4.0F, -4.85F, 0.0F, 7.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(0, 30).addBox(-2.25F, -7.35F, 0.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(60, 27).addBox(-0.25F, -4.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        SoundEffect.setTextureOffset(19, 0).addBox(-1.25F, -8.1F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        Line1 = new ModelRenderer(this);
        Line1.setRotationPoint(1.0F, -1.5F, 0.5F);
        SoundEffect.addChild(Line1);
        setRotationAngle(Line1, 0.0F, 0.0F, 0.7854F);
        Line1.setTextureOffset(22, 9).addBox(-0.7071F, -1.9393F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        Line3 = new ModelRenderer(this);
        Line3.setRotationPoint(-4.25F, -1.5F, 0.5F);
        SoundEffect.addChild(Line3);
        setRotationAngle(Line3, 0.0F, 0.0F, -0.7854F);
        Line3.setTextureOffset(41, 10).addBox(-0.1161F, 0.2374F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

        Line2 = new ModelRenderer(this);
        Line2.setRotationPoint(-2.5F, -6.3F, 0.5F);
        SoundEffect.addChild(Line2);
        setRotationAngle(Line2, 0.0F, 0.0F, -0.7854F);
        Line2.setTextureOffset(34, 21).addBox(-1.2828F, -0.9494F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, true);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return SoundEffect;
    }
}