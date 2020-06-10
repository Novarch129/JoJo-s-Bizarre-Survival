package com.novarch.jojomod.util;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.entities.stands.cMoon.EntityCMoon;
import com.novarch.jojomod.entities.stands.crazyDiamond.EntityCrazyDiamond;
import com.novarch.jojomod.entities.stands.dirtyDeedsDoneDirtCheap.EntityDirtyDeedsDoneDirtCheap;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.entities.stands.kingCrimson.EntityKingCrimson;
import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import com.novarch.jojomod.entities.stands.purpleHaze.EntityPurpleHaze;
import com.novarch.jojomod.entities.stands.weatherReport.EntityWeatherReport;
import com.novarch.jojomod.entities.stands.whitesnake.EntityWhitesnake;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.entity.passive.horse.ZombieHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.function.Predicate;

@SuppressWarnings("unused")
public class JojoLibs
{
    public static int getHighestBlock(World world, BlockPos pos)
    {
        for(int height = world.getActualHeight(); height > 0; height--)
        {
            if(world.getBlockState(new BlockPos(pos.getX(), height, pos.getZ())).getMaterial() != Material.AIR)
            {
                return height;
            }
        }
        return -1;
    }

    public static BlockPos getNearestBlockEnd(World world, BlockPos pos)
    {
        for(int height = world.getActualHeight(); height > 0; height--)
        {
            if(pos.getX() > 0) {
                for (int x = pos.getX(); x > 0; x--) {
                    if (world.getBlockState(new BlockPos(x, height, pos.getZ())).getMaterial() != Material.AIR) {
                        JojoBizarreSurvival.LOGGER.debug(x);
                        return new BlockPos(x, height, pos.getZ());
                    }
                }
            }

            else if(pos.getX() < 0) {
                for (int x = pos.getX(); x < 0; x++) {
                    if (world.getBlockState(new BlockPos(x, height, pos.getZ())).getMaterial() != Material.AIR) {
                        JojoBizarreSurvival.LOGGER.debug(x);
                        return new BlockPos(x, height, pos.getZ());
                    }
                }
            }
        }
        return new BlockPos(0, 65, 0);
    }

    public static void sendStringMessage(PlayerEntity player, String message)
    {
        player.sendMessage(new StringTextComponent(message));
    }

    public static class Predicates
    {
        public static final Predicate<Entity> NOT_STAND = entity -> !(entity instanceof EntityStandBase);
        public static final Predicate<Entity> IS_STAND = entity -> entity instanceof EntityStandBase;

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
                                                                                                                .and(entity -> !(entity instanceof EntityStandPunch))
                                                                                                                        .and(entity -> !(entity instanceof EntityStandBase)))))))))));
    }

    public static class StandID
    {
        public static final int nullStand = 0;

        public static final int kingCrimson = 1;

        public static final int dirtyDeedsDoneDirtCheap = 2;

        public static final int goldExperience = 3;

        public static final int madeInHeaven = 4;

        public static final int GER = 5;

        public static final int aerosmith = 6;

        public static final int weatherReport = 7;

        public static final int killerQueen = 8;

        public static final int crazyDiamond = 9;

        public static final int purpleHaze = 10;

        public static final int theEmperor = 11;

        public static final int whitesnake = 12;

        public static final int cMoon = 13;

        public static int[] stands = {kingCrimson, dirtyDeedsDoneDirtCheap, goldExperience, aerosmith, weatherReport, killerQueen, crazyDiamond, purpleHaze, theEmperor, whitesnake};
    }

    public static class KeyCodes
    {
        public static String summonStand = KeyHandler.keys[0].getLocalizedName().toUpperCase();
        public static String abilityToggle = KeyHandler.keys[1].getLocalizedName().toUpperCase();
        public static String ability1 = KeyHandler.keys[2].getLocalizedName().toUpperCase();
        public static String ability2 = KeyHandler.keys[10].getLocalizedName().toUpperCase();
    }

    public static int numberOfStands = StandID.stands.length;

    public static EntityStandBase getStand(int standID, World world)
    {
        switch (standID) {
            case StandID.nullStand:
                return null;
            case StandID.kingCrimson:
                return new EntityKingCrimson(world);
            case StandID.dirtyDeedsDoneDirtCheap:
                return new EntityDirtyDeedsDoneDirtCheap(world);
            case StandID.goldExperience:
                return new EntityGoldExperience(world);
            case StandID.madeInHeaven:
                return new EntityMadeInHeaven(world);
            case StandID.GER:
                return new EntityGoldExperienceRequiem(world);
            case StandID.aerosmith:
                return new EntityAerosmith(world);
            case StandID.weatherReport:
                return new EntityWeatherReport(world);
            case StandID.killerQueen:
                return new EntityKillerQueen(world);
            case StandID.crazyDiamond:
                return new EntityCrazyDiamond(world);
            case StandID.purpleHaze:
                return new EntityPurpleHaze(world);
            case StandID.whitesnake:
                return new EntityWhitesnake(world);
            case StandID.cMoon:
                return new EntityCMoon(world);
        }
        return null;
    }
}
