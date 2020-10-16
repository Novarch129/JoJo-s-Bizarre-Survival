package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.TheGratefulDeadPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheGratefulDeadPunchModel extends AbstractStandAttackModel<TheGratefulDeadPunchEntity> {
    private final ModelRenderer Claw;

    public TheGratefulDeadPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Claw = new ModelRenderer(this);
        Claw.setRotationPoint(0.0F, 4.0F, 2.0F);
        Claw.setTextureOffset(0, 0).addBox(-2.075F, -4.175F, -3.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);
        Claw.setTextureOffset(27, 0).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

        ModelRenderer eyes = new ModelRenderer(this);
        eyes.setRotationPoint(0.0F, -1.1921F, 0.3759F);
        Claw.addChild(eyes);
        setRotationAngle(eyes, -0.3054F, 0.0F, 0.0F);
        eyes.setTextureOffset(0, 0).addBox(1.05F, -1.75F, 0.25F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        eyes.setTextureOffset(26, 11).addBox(1.075F, -1.75F, 0.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer eyes2 = new ModelRenderer(this);
        eyes2.setRotationPoint(0.0F, -1.1921F, 0.3759F);
        Claw.addChild(eyes2);
        setRotationAngle(eyes2, 0.6109F, 0.0F, 0.0F);
        eyes2.setTextureOffset(0, 0).addBox(-2.175F, 0.25F, 0.75F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        eyes2.setTextureOffset(26, 11).addBox(-2.2F, 0.25F, 1.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer eyes3 = new ModelRenderer(this);
        eyes3.setRotationPoint(3.411F, -3.0625F, -1.5723F);
        Claw.addChild(eyes3);
        setRotationAngle(eyes3, -0.0873F, -0.3011F, 0.0F);
        eyes3.setTextureOffset(19, 0).addBox(-2.95F, -1.5F, 0.75F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        eyes3.setTextureOffset(26, 11).addBox(-2.95F, -1.525F, 1.55F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        eyes3.setTextureOffset(26, 11).addBox(-2.95F, -1.525F, 1.95F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer fingers = new ModelRenderer(this);
        fingers.setRotationPoint(0.0F, 0.0F, 0.0F);
        Claw.addChild(fingers);

        ModelRenderer finger2 = new ModelRenderer(this);
        finger2.setRotationPoint(-3.6718F, -2.25F, -7.5334F);
        fingers.addChild(finger2);
        setRotationAngle(finger2, 0.0F, -0.192F, 0.0F);
        finger2.setTextureOffset(1, 14).addBox(-2.5F, -1.0F, -0.5F, 5.0F, 2.0F, 1.0F, 0.0F, true);

        ModelRenderer finger3 = new ModelRenderer(this);
        finger3.setRotationPoint(3.2F, -1.475F, -7.5F);
        fingers.addChild(finger3);
        setRotationAngle(finger3, 0.0F, 0.0785F, 0.0F);
        finger3.setTextureOffset(1, 14).addBox(-2.45F, -1.025F, -0.5F, 5.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer finger1 = new ModelRenderer(this);
        finger1.setRotationPoint(0.175F, -5.25F, -7.5F);
        fingers.addChild(finger1);
        setRotationAngle(finger1, 0.2531F, 0.0F, 0.0F);
        finger1.setTextureOffset(14, 14).addBox(-1.1F, -2.7F, -0.575F, 2.0F, 5.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Claw;
    }
}