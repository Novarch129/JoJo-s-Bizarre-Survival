package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.TheGratefulDeadPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheGratefulDeadPunchModel extends AbstractStandAttackModel<TheGratefulDeadPunchEntity> {
    private final ModelRenderer Claw;
    private final ModelRenderer Eyes;
    private final ModelRenderer Eyes2;
    private final ModelRenderer Eyes3;
    private final ModelRenderer Fingers;
    private final ModelRenderer Finger2;
    private final ModelRenderer Finger3;
    private final ModelRenderer Finger1;

    public TheGratefulDeadPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Claw = new ModelRenderer(this);
        Claw.setRotationPoint(0.0F, 24.0F, 2.0F);
        Claw.setTextureOffset(0, 0).addBox(-2.075F, -4.175F, -3.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);
        Claw.setTextureOffset(27, 0).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -1.1921F, 0.3759F);
        Claw.addChild(Eyes);
        setRotationAngle(Eyes, -0.3054F, 0.0F, 0.0F);
        Eyes.setTextureOffset(0, 0).addBox(1.05F, -1.75F, 0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Eyes.setTextureOffset(26, 11).addBox(1.075F, -1.75F, 0.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes2 = new ModelRenderer(this);
        Eyes2.setRotationPoint(0.0F, -1.1921F, 0.3759F);
        Claw.addChild(Eyes2);
        setRotationAngle(Eyes2, 0.6109F, 0.0F, 0.0F);
        Eyes2.setTextureOffset(0, 0).addBox(-2.175F, 0.25F, 0.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Eyes2.setTextureOffset(26, 11).addBox(-2.2F, 0.25F, 1.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Eyes3 = new ModelRenderer(this);
        Eyes3.setRotationPoint(3.411F, -3.0625F, -1.5723F);
        Claw.addChild(Eyes3);
        setRotationAngle(Eyes3, -0.0873F, -0.3011F, 0.0F);
        Eyes3.setTextureOffset(19, 0).addBox(-2.95F, -1.5F, 0.75F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        Eyes3.setTextureOffset(26, 11).addBox(-2.95F, -1.525F, 1.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        Eyes3.setTextureOffset(26, 11).addBox(-2.95F, -1.525F, 1.95F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        Fingers = new ModelRenderer(this);
        Fingers.setRotationPoint(0.0F, 0.0F, 0.0F);
        Claw.addChild(Fingers);

        Finger2 = new ModelRenderer(this);
        Finger2.setRotationPoint(-3.6718F, -2.25F, -7.5334F);
        Fingers.addChild(Finger2);
        setRotationAngle(Finger2, 0.0F, -0.192F, 0.0F);
        Finger2.setTextureOffset(1, 14).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, 0.0F, true);

        Finger3 = new ModelRenderer(this);
        Finger3.setRotationPoint(3.2F, -1.475F, -7.5F);
        Fingers.addChild(Finger3);
        setRotationAngle(Finger3, 0.0F, 0.0785F, 0.0F);
        Finger3.setTextureOffset(1, 14).addBox(-2.45F, -1.025F, -0.5F, 5.0F, 2.0F, 1.0F, 0.0F, false);

        Finger1 = new ModelRenderer(this);
        Finger1.setRotationPoint(0.175F, -5.25F, -7.5F);
        Fingers.addChild(Finger1);
        setRotationAngle(Finger1, 0.2531F, 0.0F, 0.0F);
        Finger1.setTextureOffset(14, 14).addBox(-1.1F, -2.7F, -0.575F, 2.0F, 5.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Claw;
    }
}