package io.github.novarch129.jojomod.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.entity.KingCrimsonAfterimageEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class KingCrimsonAfterimageRenderer extends LivingRenderer<KingCrimsonAfterimageEntity, PlayerModel<KingCrimsonAfterimageEntity>> {
    public KingCrimsonAfterimageRenderer(EntityRendererManager renderManager) {
        this(renderManager, false);
    }

    public KingCrimsonAfterimageRenderer(EntityRendererManager renderManager, boolean useSmallArms) {
        super(renderManager, new PlayerModel<>(0, useSmallArms), 0.5f);
        addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5f), new BipedModel<>(1)));
        addLayer(new HeldItemLayer<>(this));
        addLayer(new ArrowLayer<>(this));
        addLayer(new HeadLayer<>(this));
        addLayer(new ElytraLayer<>(this));
        addLayer(new SpinAttackEffectLayer<>(this));
        addLayer(new BeeStingerLayer<>(this));
    }

    @Override
    public void render(KingCrimsonAfterimageEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        setModelVisibilities(entityIn);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public Vec3d getRenderOffset(KingCrimsonAfterimageEntity entityIn, float partialTicks) {
        return entityIn.isCrouching() ? new Vec3d(0, -0.125, 0) : super.getRenderOffset(entityIn, partialTicks);
    }

    private void setModelVisibilities(KingCrimsonAfterimageEntity afterimage) {
        PlayerModel<KingCrimsonAfterimageEntity> afterimagemodel = getEntityModel();
        if (afterimage.isSpectator()) {
            afterimagemodel.setVisible(false);
            afterimagemodel.bipedHead.showModel = true;
            afterimagemodel.bipedHeadwear.showModel = true;
        } else {
            ItemStack itemstack = afterimage.getHeldItemMainhand();
            ItemStack itemstack1 = afterimage.getHeldItemOffhand();
            afterimagemodel.setVisible(true);
            afterimagemodel.isSneak = afterimage.isCrouching();
            BipedModel.ArmPose bipedmodel$armpose = getArmPose(afterimage, itemstack, itemstack1, Hand.MAIN_HAND);
            BipedModel.ArmPose bipedmodel$armpose1 = getArmPose(afterimage, itemstack, itemstack1, Hand.OFF_HAND);
            if (afterimage.getPrimaryHand() == HandSide.RIGHT) {
                afterimagemodel.rightArmPose = bipedmodel$armpose;
                afterimagemodel.leftArmPose = bipedmodel$armpose1;
            } else {
                afterimagemodel.rightArmPose = bipedmodel$armpose1;
                afterimagemodel.leftArmPose = bipedmodel$armpose;
            }
        }

    }

    private BipedModel.ArmPose getArmPose(KingCrimsonAfterimageEntity afterimageEntity, ItemStack itemStackMain, ItemStack itemStackOff, Hand handIn) {
        BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = handIn == Hand.MAIN_HAND ? itemStackMain : itemStackOff;
        if (!itemstack.isEmpty()) {
            bipedmodel$armpose = BipedModel.ArmPose.ITEM;
            if (afterimageEntity.getItemInUseCount() > 0) {
                UseAction useaction = itemstack.getUseAction();
                if (useaction == UseAction.BLOCK)
                    bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
                else if (useaction == UseAction.BOW)
                    bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
                else if (useaction == UseAction.SPEAR)
                    bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
                else if (useaction == UseAction.CROSSBOW && handIn == afterimageEntity.getActiveHand())
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
            } else {
                boolean flag3 = itemStackMain.getItem() == Items.CROSSBOW;
                boolean flag = CrossbowItem.isCharged(itemStackMain);
                boolean flag1 = itemStackOff.getItem() == Items.CROSSBOW;
                boolean flag2 = CrossbowItem.isCharged(itemStackOff);
                if (flag3 && flag)
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
                if (flag1 && flag2 && itemStackMain.getItem().getUseAction(itemStackMain) == UseAction.NONE)
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
            }
        }
        return bipedmodel$armpose;
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getEntityTexture(KingCrimsonAfterimageEntity entity) {
        return entity.getMaster() instanceof ClientPlayerEntity ? ((ClientPlayerEntity) entity.getMaster()).getLocationSkin() : Util.ResourceLocations.KING_CRIMSON;
    }

    @Override
    protected void preRenderCallback(KingCrimsonAfterimageEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    protected void renderName(KingCrimsonAfterimageEntity entityIn, String displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        double d0 = renderManager.squareDistanceTo(entityIn);
        String displayName = entityIn.getCustomName() == null ? displayNameIn : entityIn.getDisplayName().getFormattedText();
        if (!(d0 > 4096)) {
            boolean flag = !entityIn.isDiscrete();
            float f = entityIn.getHeight() + 0.5f;
            int i = "deadmau5".equals(displayName) ? -10 : 0;
            matrixStackIn.push();
            matrixStackIn.translate(0, f, 0);
            matrixStackIn.rotate(renderManager.getCameraOrientation());
            matrixStackIn.scale(-0.025f, -0.025f, 0.025f);
            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
            float f1 = Minecraft.getInstance().gameSettings.getTextBackgroundOpacity(0.25f);
            int j = (int) (f1 * 255.0F) << 24;
            FontRenderer fontrenderer = getFontRendererFromRenderManager();
            float f2 = (float) (-fontrenderer.getStringWidth(displayName) / 2);
            fontrenderer.renderString(displayName, f2, (float) i, 553648127, false, matrix4f, bufferIn, flag, j, packedLightIn);
            if (flag)
                fontrenderer.renderString(displayName, f2, (float) i, -1, false, matrix4f, bufferIn, false, 0, packedLightIn);
            matrixStackIn.pop();
        }
    }
}
