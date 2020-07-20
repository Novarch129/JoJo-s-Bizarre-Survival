package io.github.novarch129.jojomod.util;

import com.mojang.blaze3d.matrix.MatrixStack;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.data.EmptyModelData;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class Util {
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
        return new BlockPos(0, 65, 0);
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
        }
    }

    public static class Predicates {
        public static final Predicate<Entity> NOT_STAND = entity -> !(entity instanceof AbstractStandEntity);
        public static final Predicate<Entity> IS_STAND = entity -> entity instanceof AbstractStandEntity;

        public static final Predicate<Entity> STAND_PUNCH_TARGET =
                EntityPredicates.NOT_SPECTATING
                        .and(EntityPredicates.IS_ALIVE)
                        .and(Entity::canBeCollidedWith);

        public static final Predicate<Entity> BREATHS =
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
                THE_HAND
        };
    }

    public static class KeyCodes {
        public static final String SUMMON_STAND = KeyInit.SPAWN_STAND.getLocalizedName().toUpperCase();
        public static final String ABILITY_TOGGLE = KeyInit.TOGGLE_ABILITY.getLocalizedName().toUpperCase();
        public static final String ABILITY_1 = KeyInit.ABILITY1.getLocalizedName().toUpperCase();
        public static final String ABILITY_2 = KeyInit.ABILITY2.getLocalizedName().toUpperCase();
    }
}
