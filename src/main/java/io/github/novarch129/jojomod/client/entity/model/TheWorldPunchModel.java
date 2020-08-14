package io.github.novarch129.jojomod.client.entity.model;

import io.github.novarch129.jojomod.entity.stand.attack.TheWorldPunchEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TheWorldPunchModel extends AbstractStandAttackModel<TheWorldPunchEntity> {
    public final ModelRenderer Punch;

    public TheWorldPunchModel() {
        textureWidth = 64;
        textureHeight = 32;

        Punch = new ModelRenderer(this);
        Punch.setRotationPoint(0.0F, 24.0F, 0.0F);
        Punch.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

        ModelRenderer elbowPad2 = new ModelRenderer(this);
        elbowPad2.setRotationPoint(0.0247F, -1.3378F, -2.0572F);
        Punch.addChild(elbowPad2);
        setRotationAngle(elbowPad2, -1.6232F, 0.0044F, 1.5708F);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -1.5747F, 1.0F, 2.0F, 4.0F, 0.0F, false);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(0.6903F, -3.4987F, -2.3247F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.1767F, -3.4987F, 1.4253F, 3.0F, 2.0F, 1.0F, 0.0F, true);
        elbowPad2.setTextureOffset(54, 26).addBox(-2.9267F, -3.4987F, -2.3247F, 4.0F, 2.0F, 1.0F, 0.0F, true);

        ModelRenderer heart7 = new ModelRenderer(this);
        heart7.setRotationPoint(4.0503F, 2.8628F, 3.4072F);
        elbowPad2.addChild(heart7);


        ModelRenderer heartPiece20 = new ModelRenderer(this);
        heartPiece20.setRotationPoint(-0.5F, -18.0F, -4.5F);
        heart7.addChild(heartPiece20);
        setRotationAngle(heartPiece20, 0.0F, 0.0F, -0.7854F);
        heartPiece20.setTextureOffset(58, 20).addBox(-13.1017F, 5.0588F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, true);

        ModelRenderer heartPiece21 = new ModelRenderer(this);
        heartPiece21.setRotationPoint(0.5F, -18.0F, -4.5F);
        heart7.addChild(heartPiece21);
        setRotationAngle(heartPiece21, 0.0F, 0.0F, 0.7854F);
        heartPiece21.setTextureOffset(58, 20).addBox(5.3517F, 11.8088F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer heartPiece22 = new ModelRenderer(this);
        heartPiece22.setRotationPoint(0.5F, -18.0F, -4.5F);
        heart7.addChild(heartPiece22);
        setRotationAngle(heartPiece22, 0.0F, 0.0F, 1.5708F);
        heartPiece22.setTextureOffset(58, 20).addBox(11.8414F, 3.8587F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        heartPiece22.setTextureOffset(58, 20).addBox(11.8414F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        heartPiece22.setTextureOffset(58, 20).addBox(11.3664F, 5.6871F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        heartPiece22.setTextureOffset(58, 20).addBox(11.3664F, 3.8587F, -1.547F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        heartPiece22.setTextureOffset(58, 20).addBox(12.0335F, 4.2445F, -1.547F, 1.0F, 2.0F, 1.0F, 0.0F, false);
    }

    @Override
    protected ModelRenderer getAttackModel() {
        return Punch;
    }
}