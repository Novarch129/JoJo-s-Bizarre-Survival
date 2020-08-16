package io.github.novarch129.jojomod.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.entity.stand.*;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.KeyInit;
import io.github.novarch129.jojomod.item.StandArrowItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Used for various utilities and constants.
 */
@SuppressWarnings("unused")
public class Util {
    /**
     * The true default Y motion for entities.
     */
    public static final double ENTITY_DEFAULT_Y_MOTION = -0.0784000015258789;

    public static int getHighestBlockInXZ(World world, BlockPos pos) {
        for (int height = world.getActualHeight(); height > 0; height--)
            if (world.getBlockState(new BlockPos(pos.getX(), height, pos.getZ())).getMaterial() != Material.AIR)
                return height;
        return -1;
    }

    public static BlockPos getNearestBlockEnd(World world, BlockPos pos) {
        for (int height = world.getActualHeight(); height > 0; height--) {
            if (pos.getX() > 0) {
                for (int x = pos.getX(); x > 0; x--)
                    if (world.getBlockState(new BlockPos(x, height, pos.getZ())).getMaterial() != Material.AIR)
                        return new BlockPos(x, height, pos.getZ());
            } else if (pos.getX() < 0) {
                for (int x = pos.getX(); x < 0; x++)
                    if (world.getBlockState(new BlockPos(x, height, pos.getZ())).getMaterial() != Material.AIR)
                        return new BlockPos(x, height, pos.getZ());
            }
        }
        return new BlockPos(0, 65, 0); //The location of the End exit portal.
    }

    public static boolean isClientHoldingShift() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    /**
     * Suppresses warning for unchecked casts.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o) {
        return (T) o;
    }

    /**
     * Got these values from <a href ="https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/modification-development/1435515-how-i-can-do-to-move-to-where-i-look#c5">this</a> thread, shortened it a little bit.
     */
    public static Vec3d getEntityForwardsMotion(Entity entity) {
        return new Vec3d(
                -MathHelper.sin(entity.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180 * (float) Math.PI),
                MathHelper.cos(entity.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180 * (float) Math.PI),
                MathHelper.cos(entity.rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180 * (float) Math.PI)
        );
    }

    /**
     * Statically renders the given {@link BlockState} at the given {@link BlockPos}, like {@link net.minecraft.client.renderer.entity.EntityRendererManager#renderEntityStatic(Entity, double, double, double, float, float, MatrixStack, IRenderTypeBuffer, int)}, but for blocks.
     */
    public static void renderBlockStatic(MatrixStack matrixStack, IRenderTypeBuffer.Impl buffer, World world, BlockState blockState, BlockPos blockPos, Vec3d projectedView, boolean occlusionCulling) {
        matrixStack.push();
        matrixStack.translate(-projectedView.x + blockPos.getX(), -projectedView.y + blockPos.getY(), -projectedView.z + blockPos.getZ());
        for (RenderType renderType : RenderType.getBlockRenderTypes()) {
            if (RenderTypeLookup.canRenderInLayer(blockState, renderType))
                Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(
                        world,
                        Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(blockState),
                        blockState,
                        blockPos,
                        matrixStack,
                        buffer.getBuffer(renderType),
                        occlusionCulling,
                        new Random(),
                        blockState.getPositionRandom(blockPos),
                        OverlayTexture.NO_OVERLAY,
                        EmptyModelData.INSTANCE
                );
        }
        matrixStack.pop();
        buffer.finish();
    }

    public static void renderBlockStatic(MatrixStack matrixStack, IRenderTypeBuffer.Impl buffer, World world, BlockState blockState, BlockPos blockPos, Vec3d projectedView, boolean occlusionCulling, RenderType renderType) {
        matrixStack.push();
        matrixStack.translate(-projectedView.x + blockPos.getX(), -projectedView.y + blockPos.getY(), -projectedView.z + blockPos.getZ());
        Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(
                world,
                Minecraft.getInstance().getBlockRendererDispatcher().getModelForState(blockState),
                blockState,
                blockPos,
                matrixStack,
                buffer.getBuffer(renderType),
                occlusionCulling,
                new Random(),
                blockState.getPositionRandom(blockPos),
                OverlayTexture.NO_OVERLAY,
                EmptyModelData.INSTANCE
        );
        matrixStack.pop();
        buffer.finish();
    }

    /**
     * Used to suppress warnings saying that <code>static final</code> fields are <code>null</code>.
     * Based on diesieben07's solution <a href="http://www.minecraftforge.net/forum/topic/60980-solved-disable-%E2%80%9Cconstant-conditions-exceptions%E2%80%9D-inspection-for-field-in-intellij-idea/?do=findCommentcomment=285024">here</a>.
     *
     * @return null
     */
    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T Null() {
        return null;
    }

    /**
     * Returns an {@link AbstractStandEntity} based on the StandID inputted.
     *
     * @param standID The StandID of the Stand, see {@link StandID}.
     * @param world   The {@link World} the Stand will be summoned in.
     */
    public static AbstractStandEntity getStandByID(int standID, World world) {
        switch (standID) {
            default:
                return Null();
            case StandID.KING_CRIMSON:
                return new KingCrimsonEntity(EntityInit.KING_CRIMSON.get(), world);
            case StandID.D4C:
                return new D4CEntity(EntityInit.D4C.get(), world);
            case StandID.GOLD_EXPERIENCE:
                return new GoldExperienceEntity(EntityInit.GOLD_EXPERIENCE.get(), world);
            case StandID.MADE_IN_HEAVEN:
                return new MadeInHeavenEntity(EntityInit.MADE_IN_HEAVEN.get(), world);
            case StandID.GER:
                return new GoldExperienceRequiemEntity(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), world);
            case StandID.AEROSMITH:
                return new AerosmithEntity(EntityInit.AEROSMITH.get(), world);
            case StandID.WEATHER_REPORT:
                return new WeatherReportEntity(EntityInit.WEATHER_REPORT.get(), world);
            case StandID.KILLER_QUEEN:
                return new KillerQueenEntity(EntityInit.KILLER_QUEEN.get(), world);
            case StandID.CRAZY_DIAMOND:
                return new CrazyDiamondEntity(EntityInit.CRAZY_DIAMOND.get(), world);
            case StandID.PURPLE_HAZE:
                return new PurpleHazeEntity(EntityInit.PURPLE_HAZE.get(), world);
            case StandID.WHITESNAKE:
                return new WhitesnakeEntity(EntityInit.WHITESNAKE.get(), world);
            case StandID.CMOON:
                return new CMoonEntity(EntityInit.CMOON.get(), world);
            case StandID.THE_WORLD:
                return new TheWorldEntity(EntityInit.THE_WORLD.get(), world);
            case StandID.STAR_PLATINUM:
                return new StarPlatinumEntity(EntityInit.STAR_PLATINUM.get(), world);
            case StandID.SILVER_CHARIOT:
                return new SilverChariotEntity(EntityInit.SILVER_CHARIOT.get(), world);
            case StandID.MAGICIANS_RED:
                return new MagiciansRedEntity(EntityInit.MAGICIANS_RED.get(), world);
            case StandID.THE_HAND:
                return new TheHandEntity(EntityInit.THE_HAND.get(), world);
            case StandID.HIEROPHANT_GREEN:
                return new HierophantGreenEntity(EntityInit.HIEROPHANT_GREEN.get(), world);
            case StandID.GREEN_DAY:
                return new GreenDayEntity(EntityInit.GREEN_DAY.get(), world);
            case StandID.TWENTIETH_CENTURY_BOY:
                return new TwentiethCenturyBoyEntity(EntityInit.TWENTIETH_CENTURY_BOY.get(), world);
            case StandID.THE_GRATEFUL_DEAD:
                return new TheGratefulDeadEntity(EntityInit.THE_GRATEFUL_DEAD.get(), world);
            case StandID.STICKY_FINGERS:
                return new StickyFingersEntity(EntityInit.STICKY_FINGERS.get(), world);
            case StandID.TUSK_ACT_1:
                return new TuskAct1Entity(EntityInit.TUSK_ACT_1.get(), world);
        }
    }

    public static class Predicates {
        public static final Predicate<Entity> NOT_STAND = entity -> !(entity instanceof AbstractStandEntity);
        public static final Predicate<Entity> IS_STAND = entity -> entity instanceof AbstractStandEntity;

        public static final Predicate<Entity> STAND_PUNCH_TARGET =
                EntityPredicates.NOT_SPECTATING
                        .and(EntityPredicates.IS_ALIVE)
                        .and(Entity::canBeCollidedWith);

        public static final Predicate<Entity> BREATHS = //Pretty much a list of every entity that doesn't breath.
                ((Predicate<Entity>) entity -> !(entity instanceof ZombieEntity))
                        .and(((Predicate<Entity>) entity -> !(entity instanceof HuskEntity))
                                .and(((Predicate<Entity>) entity -> !(entity instanceof DrownedEntity))
                                        .and(((Predicate<Entity>) entity -> !(entity instanceof ZombieHorseEntity))
                                                .and(((Predicate<Entity>) entity -> !(entity instanceof SkeletonEntity))
                                                        .and(((Predicate<Entity>) entity -> !(entity instanceof WitherSkeletonEntity))
                                                                .and(((Predicate<Entity>) entity -> !(entity instanceof SkeletonHorseEntity))
                                                                        .and(((Predicate<Entity>) entity -> !(entity instanceof GiantEntity))
                                                                                .and(((Predicate<Entity>) entity -> !(entity instanceof ZombieVillagerEntity))
                                                                                        .and(((Predicate<Entity>) entity -> !(entity instanceof StrayEntity))
                                                                                                .and(entity -> !(entity instanceof ZombiePigmanEntity))
                                                                                                .and(entity -> !(entity instanceof PhantomEntity))
                                                                                                .and(entity -> !(entity instanceof AbstractStandAttackEntity))
                                                                                                .and(entity -> !(entity instanceof AbstractStandEntity))
                                                                                                .and(entity -> !(entity instanceof ItemEntity)))))))))));
    }

    public static class StandID {
        public static final int KING_CRIMSON = 1;

        public static final int D4C = 2;

        public static final int GOLD_EXPERIENCE = 3;

        public static final int MADE_IN_HEAVEN = 4;

        public static final int GER = 5;

        public static final int AEROSMITH = 6;

        public static final int WEATHER_REPORT = 7;

        public static final int KILLER_QUEEN = 8;

        public static final int CRAZY_DIAMOND = 9;

        public static final int PURPLE_HAZE = 10;

        public static final int THE_EMPEROR = 11;

        public static final int WHITESNAKE = 12;

        public static final int CMOON = 13;

        public static final int THE_WORLD = 14;

        public static final int STAR_PLATINUM = 15;

        public static final int SILVER_CHARIOT = 16;

        public static final int MAGICIANS_RED = 17;

        public static final int THE_HAND = 18;

        public static final int HIEROPHANT_GREEN = 19;

        public static final int GREEN_DAY = 20;

        public static final int TWENTIETH_CENTURY_BOY = 21;

        public static final int THE_GRATEFUL_DEAD = 22;

        public static final int STICKY_FINGERS = 23;

        public static final int TUSK_ACT_1 = 24;

        /**
         * An array of Stand's that can be obtained through the {@link StandArrowItem}.
         */
        public static final int[] STANDS = {
                KING_CRIMSON,
                D4C,
                GOLD_EXPERIENCE,
                AEROSMITH,
                WEATHER_REPORT,
                KILLER_QUEEN,
                CRAZY_DIAMOND,
                PURPLE_HAZE,
                THE_EMPEROR,
                WHITESNAKE,
                THE_WORLD,
                STAR_PLATINUM,
                SILVER_CHARIOT,
                MAGICIANS_RED,
                THE_HAND,
                HIEROPHANT_GREEN,
                GREEN_DAY,
                TWENTIETH_CENTURY_BOY,
                THE_GRATEFUL_DEAD,
                STICKY_FINGERS,
                TUSK_ACT_1
        };
    }

    public static class KeyCodes {
        public static final String SUMMON_STAND = KeyInit.SPAWN_STAND.getLocalizedName().toUpperCase();
        public static final String ABILITY_TOGGLE = KeyInit.TOGGLE_ABILITY.getLocalizedName().toUpperCase();
        public static final String ABILITY_1 = KeyInit.ABILITY1.getLocalizedName().toUpperCase();
        public static final String ABILITY_2 = KeyInit.ABILITY2.getLocalizedName().toUpperCase();
        public static final String SWITCH_ACT = KeyInit.SWITCH_ACT.getLocalizedName().toUpperCase();
    }

    public static class ResourceLocations {
        public static final ResourceLocation KING_CRIMSON = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson.png");
        public static final ResourceLocation KING_CRIMSON_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/king_crimson_punch.png");
        public static final ResourceLocation D4C = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c.png");
        public static final ResourceLocation D4C_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/d4c_punch.png");
        public static final ResourceLocation GOLD_EXPERIENCE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience.png");
        public static final ResourceLocation GOLD_EXPERIENCE_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/gold_experience_punch.png");
        public static final ResourceLocation MADE_IN_HEAVEN = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/made_in_heaven.png");
        public static final ResourceLocation MADE_IN_HEAVEN_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/made_in_heaven_punch.png");
        public static final ResourceLocation GER = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger.png");
        public static final ResourceLocation GER_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/ger_punch.png");
        public static final ResourceLocation AEROSMITH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith.png");
        public static final ResourceLocation AEROSMITH_BULLET = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/aerosmith_bullet.png");
        public static final ResourceLocation WEATHER_REPORT = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report.png");
        public static final ResourceLocation WEATHER_REPORT_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/weather_report_punch.png");
        public static final ResourceLocation KILLER_QUEEN = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen.png");
        public static final ResourceLocation KILLER_QUEEN_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/killer_queen_punch.png");
        public static final ResourceLocation SHEER_HEART_ATTACK = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sheer_heart_attack.png");
        public static final ResourceLocation CRAZY_DIAMOND = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/crazy_diamond.png");
        public static final ResourceLocation CRAZY_DIAMOND_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/crazy_diamond_punch.png");
        public static final ResourceLocation PURPLE_HAZE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze.png");
        public static final ResourceLocation PURPLE_HAZE_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/purple_haze_punch.png");
        public static final ResourceLocation EMPEROR_BULLET = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/emperor_bullet.png");
        public static final ResourceLocation WHITESNAKE = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake.png");
        public static final ResourceLocation WHITESNAKE_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/whitesnake_punch.png");
        public static final ResourceLocation CMOON = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon.png");
        public static final ResourceLocation CMOON_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/cmoon_punch.png");
        public static final ResourceLocation THE_WORLD = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world.png");
        public static final ResourceLocation THE_WORLD_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_world_punch.png");
        public static final ResourceLocation STAR_PLATINUM = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum.png");
        public static final ResourceLocation STAR_PLATINUM_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/star_platinum_punch.png");
        public static final ResourceLocation SILVER_CHARIOT = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/silver_chariot.png");
        public static final ResourceLocation SILVER_CHARIOT_SWORD = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/silver_chariot_sword.png");
        public static final ResourceLocation MAGICIANS_RED = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red.png");
        public static final ResourceLocation MAGICIANS_RED_FLAME = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/magicians_red_flames.png");
        public static final ResourceLocation THE_HAND = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_hand.png");
        public static final ResourceLocation THE_HAND_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_hand_punch.png");
        public static final ResourceLocation HIEROPHANT_GREEN = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/hierophant_green.png");
        public static final ResourceLocation HIEROPHANT_GREEN_TAIL = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/hierophant_green_tail.png");
        public static final ResourceLocation GREEN_DAY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/green_day.png");
        public static final ResourceLocation GREEN_DAY_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/green_day_punch.png");
        public static final ResourceLocation TWENTIETH_CENTURY_BOY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/20th_century_boy.png");
        public static final ResourceLocation TWENTIETH_CENTURY_BOY_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/20th_century_boy_punch.png");
        public static final ResourceLocation THE_GRATEFUL_DEAD = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_grateful_dead.png");
        public static final ResourceLocation THE_GRATEFUL_DEAD_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/the_grateful_dead_punch.png");
        public static final ResourceLocation STICKY_FINGERS = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sticky_fingers.png");
        public static final ResourceLocation STICKY_FINGERS_PUNCH = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/sticky_fingers_punch.png");
        public static final ResourceLocation TUSK_ACT_1 = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/tusk_act_1.png");
        public static final ResourceLocation NAIL_BULLET = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/stands/nail_bullet.png");
    }
}
