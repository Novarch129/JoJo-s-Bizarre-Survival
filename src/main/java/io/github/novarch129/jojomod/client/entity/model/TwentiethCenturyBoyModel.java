package io.github.novarch129.jojomod.client.entity.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.novarch129.jojomod.entity.stand.TwentiethCenturyBoyEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TwentiethCenturyBoyModel extends AbstractStandModel<TwentiethCenturyBoyEntity> {
    private final ModelRenderer TwentiethCenturyBoy;
    private final ModelRenderer HeadBase;
    private final ModelRenderer Head;
    private final ModelRenderer Eyes;
    private final ModelRenderer Ears;
    private final ModelRenderer Ear1;
    private final ModelRenderer Ear2;
    private final ModelRenderer BodyBase;
    private final ModelRenderer Torso;
    private final ModelRenderer Chest;
    private final ModelRenderer Abs;
    private final ModelRenderer Arms;
    private final ModelRenderer RightArm;
    private final ModelRenderer RightHand;
    private final ModelRenderer LeftArm;
    private final ModelRenderer LeftHand;
    private final ModelRenderer Crotch;
    private final ModelRenderer Legs;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer LeftFoot;

    public TwentiethCenturyBoyModel() {
        textureWidth = 128;
        textureHeight = 128;

        TwentiethCenturyBoy = new ModelRenderer(this);
        TwentiethCenturyBoy.setRotationPoint(0.0F, 24.0F, 0.0F);


        HeadBase = new ModelRenderer(this);
        HeadBase.setRotationPoint(0.0F, -32.5F, 0.0F);
        TwentiethCenturyBoy.addChild(HeadBase);
        HeadBase.setTextureOffset(39, 3).addBox(-5.0F, 1.3F, -4.5F, 10.0F, 2.0F, 9.0F, 0.0F, false);

        Head = new ModelRenderer(this);
        Head.setRotationPoint(0.0F, 3.5F, 0.0F);
        HeadBase.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -10.2F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        Eyes = new ModelRenderer(this);
        Eyes.setRotationPoint(0.0F, -0.75F, 0.0F);
        Head.addChild(Eyes);
        Eyes.setTextureOffset(0, 0).addBox(-3.0F, -7.2F, -4.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        Eyes.setTextureOffset(0, 0).addBox(1.0F, -7.2F, -4.1F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        Ears = new ModelRenderer(this);
        Ears.setRotationPoint(0.0F, 0.5F, 0.0F);
        Head.addChild(Ears);


        Ear1 = new ModelRenderer(this);
        Ear1.setRotationPoint(-3.2156F, -13.7634F, -1.0F);
        Ears.addChild(Ear1);
        setRotationAngle(Ear1, 0.0F, 0.0F, -0.1571F);
        Ear1.setTextureOffset(120, 3).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
        Ear1.setTextureOffset(120, 3).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

        Ear2 = new ModelRenderer(this);
        Ear2.setRotationPoint(3.4626F, -13.7242F, -1.0F);
        Ears.addChild(Ear2);
        setRotationAngle(Ear2, 0.0F, 0.0F, 0.1571F);
        Ear2.setTextureOffset(120, 3).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 4.0F, 3.0F, 0.0F, true);
        Ear2.setTextureOffset(120, 3).addBox(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, true);

        BodyBase = new ModelRenderer(this);
        BodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        TwentiethCenturyBoy.addChild(BodyBase);


        Torso = new ModelRenderer(this);
        Torso.setRotationPoint(0.0F, -25.5F, -1.0F);
        BodyBase.addChild(Torso);
        setRotationAngle(Torso, -0.2443F, 0.0F, 0.0F);
        Torso.setTextureOffset(26, 18).addBox(-10.0F, -4.8766F, -2.5619F, 20.0F, 5.0F, 6.0F, 0.0F, false);
        Torso.setTextureOffset(97, 33).addBox(10.75F, -4.4766F, -2.0619F, 1.0F, 4.0F, 5.0F, 0.0F, false);
        Torso.setTextureOffset(97, 33).addBox(-11.75F, -4.4766F, -2.0619F, 1.0F, 4.0F, 5.0F, 0.0F, true);
        Torso.setTextureOffset(97, 17).addBox(5.0F, -5.4766F, -3.0619F, 6.0F, 6.0F, 7.0F, 0.0F, false);
        Torso.setTextureOffset(97, 17).addBox(-11.0F, -5.4766F, -3.0619F, 6.0F, 6.0F, 7.0F, 0.0F, true);

        Chest = new ModelRenderer(this);
        Chest.setRotationPoint(0.0F, -3.0F, -1.5F);
        BodyBase.addChild(Chest);
        setRotationAngle(Chest, -0.3229F, 0.0F, 0.0F);
        Chest.setTextureOffset(45, 31).addBox(-5.5F, -25.767F, -9.7241F, 11.0F, 6.0F, 5.0F, 0.0F, false);

        Abs = new ModelRenderer(this);
        Abs.setRotationPoint(3.0F, -14.0F, -3.0F);
        BodyBase.addChild(Abs);
        setRotationAngle(Abs, 0.0087F, 0.0F, 0.0F);
        Abs.setTextureOffset(0, 39).addBox(-6.5F, -11.2058F, -0.647F, 7.0F, 8.0F, 4.0F, 0.0F, false);

        Arms = new ModelRenderer(this);
        Arms.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Arms);


        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(-7.45F, -28.3015F, -1.3061F);
        Arms.addChild(RightArm);
        setRotationAngle(RightArm, -0.6424F, 0.0992F, 0.1336F);
        RightArm.setTextureOffset(27, 32).addBox(-1.7828F, 1.3666F, 0.495F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        RightHand = new ModelRenderer(this);
        RightHand.setRotationPoint(0.8458F, 6.7811F, 1.817F);
        RightArm.addChild(RightHand);
        setRotationAngle(RightHand, -0.1134F, 0.0F, -0.1134F);
        RightHand.setTextureOffset(0, 53).addBox(-2.678F, -0.0205F, -1.307F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(8.8F, -26.9765F, -0.5311F);
        Arms.addChild(LeftArm);
        setRotationAngle(LeftArm, -0.7272F, -0.1218F, 0.0202F);
        LeftArm.setTextureOffset(27, 32).addBox(-2.9835F, 0.5455F, -1.05F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        LeftHand = new ModelRenderer(this);
        LeftHand.setRotationPoint(-1.5624F, 5.6204F, 1.506F);
        LeftArm.addChild(LeftHand);
        setRotationAngle(LeftHand, -0.1134F, 0.0F, 0.3272F);
        LeftHand.setTextureOffset(0, 53).addBox(-1.2528F, 0.1022F, -2.4024F, 4.0F, 6.0F, 4.0F, 0.0F, true);

        Crotch = new ModelRenderer(this);
        Crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Crotch);
        Crotch.setTextureOffset(32, 68).addBox(-4.5F, -19.2058F, -4.347F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        Legs = new ModelRenderer(this);
        Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        BodyBase.addChild(Legs);


        RightLeg = new ModelRenderer(this);
        RightLeg.setRotationPoint(-2.3F, -15.425F, -2.2125F);
        Legs.addChild(RightLeg);
        setRotationAngle(RightLeg, 0.1353F, 0.2618F, 0.0436F);
        RightLeg.setTextureOffset(80, 24).addBox(-1.947F, -1.1129F, -1.3877F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        RightFoot = new ModelRenderer(this);
        RightFoot.setRotationPoint(0.14F, 14.3142F, 2.0955F);
        RightLeg.addChild(RightFoot);
        setRotationAngle(RightFoot, 0.4407F, 0.0F, 0.0F);
        RightFoot.setTextureOffset(59, 46).addBox(-2.087F, -7.2989F, -0.4088F, 4.0F, 9.0F, 4.0F, 0.0F, true);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setRotationPoint(2.275F, -14.25F, -2.6125F);
        Legs.addChild(LeftLeg);
        setRotationAngle(LeftLeg, -0.1265F, 0.1396F, -0.1745F);
        LeftLeg.setTextureOffset(80, 24).addBox(-2.0055F, -3.1995F, -1.4456F, 4.0F, 9.0F, 4.0F, 0.0F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setRotationPoint(-0.3875F, 12.4197F, 1.0049F);
        LeftLeg.addChild(LeftFoot);
        setRotationAngle(LeftFoot, 0.7025F, 0.0F, 0.0873F);
        LeftFoot.setTextureOffset(59, 46).addBox(-2.189F, -6.5834F, 2.3742F, 4.0F, 9.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TwentiethCenturyBoy.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelRenderer getHead() {
        return Head;
    }
}