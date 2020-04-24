package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.init.SoundInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class StandPunchEffects
{
	public static Entity bomb = null;
	public static int cooldown = 220;
	public static int transformed = 0;
	public static int gerTransformed = 0;
	public static int gerCooldown = 170;
	
	public static void getStandSpecific(final RayTraceResult result, final LivingEntity entityIn, final EntityStandPunch punch, final boolean entityBlock, final int entityId) {
        switch (entityId) {
            case 0: {
                basicDefault(result, entityIn, entityBlock);
                break;
            }
            case 1: {
                kingCrimson(result, entityIn, punch, entityBlock);
                break;
            }
            case 2: {
                crazyDiamond(result, entityIn, punch, entityBlock);
                break;
            }
            /*case 3: {
                dirtyDeedsDoneDirtCheap(result, entityIn, punch, entityBlock);
                break;
            }
            case 4: {
            	madeInHeaven(result, entityIn, punch, entityBlock);
            	break;
            }
            case 5: {
            	weatherReport(result, entityIn, punch, entityBlock);
            	break;
            }
            case 6: {
            	goldExperience(result, entityIn, punch, entityBlock);
            	break;
            }
            case 7: {
            	goldExperienceRequiem(result, entityIn, punch, entityBlock);
            	break;
            }
            case 8: {
            	theWorld(result, entityIn, punch, entityBlock);
            	break;
            }
            case 9: {
            	killerQueen(result, entityIn, punch, entityBlock);
            	break;
            }
            case 10: {
            	killerQueen8(result, entityIn, punch, entityBlock);
            	break;
            }
            case 11: {
            	stickyFingers(result, entityIn, punch, entityBlock);
            	break;
            }*/
            default: {}
        }
    }
    
	/**
	 * Default punch
	 */
	
    public static void basicDefault(final RayTraceResult result, final LivingEntity LivingEntity, final boolean entityBlock) 
    {
        if (!entityBlock) 
        {
            if (!entityBlock) {}
        }
    }
   
    /**
     * Crazy Diamond's punch
     */
    
    public static void crazyDiamond(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
        if (entityBlock) 
        {
            if (punch.shootingStand.onheal) 
            {
                if (punch.shootingStand.orarush) 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
                }
                else 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
            else 
            {
                final float p = 0.2f;
                final float p2 = 0.4f;
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else
                    {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
            }
        }
        if (!entityBlock) 
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f)
            {
                if (punch.shootingStand.onheal) 
                {
                    final FallingBlockEntity entityfallingsand = new FallingBlockEntity(punch.world, (double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), BlockState);
                    entityfallingsand.setMotion(entityfallingsand.getMotion().getX(), 0.5, entityfallingsand.getMotion().getZ());
                    punch.world.addEntity((Entity)entityfallingsand);
                    punch.remove();
                }
                else
                {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    //blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
                }
            }
            else
            {
                punch.remove();
            }
        }
    }
    
    /**
     * King Crimson's punch
     */
    
    public static void kingCrimson(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) {
        if (entityBlock) {
            if (punch.shootingStand.timeSkipped) {
                if (punch.shootingStand.orarush) {
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 40, 0));
                }
                else {
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 40, 1));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
            else {
                final float p = 0.2f;
                final float p2 = 0.4f;
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
            }
        }
        else if (!entityBlock) {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
                    punch.remove();
            }
            else {
                punch.remove();
            }
        }
    }
    
    /**
     * D4C's punch
     */
    
    /*public static void dirtyDeedsDoneDirtCheap(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock)
    {
    	if (entityBlock) {
            if (punch.shootingStand.timeSkipped) {
                if (punch.shootingStand.orarush) {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
                }
                else {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
            else {
                final float p = 0.2f;
                final float p2 = 0.4f;
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
            }
        }
        else if (!entityBlock) {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
            }
            else {
                punch.remove();
            }
        }
    }*/
    
    /**
     * Made in Heaven's punch
     */
    
    /*public static void madeInHeaven(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
    	if (entityBlock) {
            if (punch.shootingStand.heaven || punch.shootingStand.heaven == false) {
                if (punch.shootingStand.orarush) {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 140, 1));
                }
                else {
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 130, 1));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 1.7f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
            else {
                final float p = 0.2f;
                final float p2 = 0.4f;
                    if (LivingEntity instanceof WitherEntity) {
                        final WitherEntity wither = (WitherEntity)LivingEntity;
                         	LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 15.0f);
                            LivingEntity.hurtResistantTime = 0;
                            LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                            if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                            }
                            else {
                                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                            }
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                            punch.remove();
                        }
                    else {
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 3.0f);
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.5f);
                        LivingEntity.hurtResistantTime = 0;
                        LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                        if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                        }
                        else {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                        }
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                        punch.remove();
                    }
                }
                else {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 3.0f);
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 3.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
        if (!entityBlock) {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 100.0f) {
                if (punch.shootingStand.heaven==false) {
                    final FallingBlockEntity entityfallingsand = new FallingBlockEntity(punch.world, (double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), BlockState);
                    entityfallingsand.motionY = 1.0;
                    punch.world.addEntity((Entity)entityfallingsand);
                    punch.remove();
                }
                else {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
                }
            }
            else {
                punch.remove();
            }
        }
    }
    }*/
    
    /**
     * Weather Report's punch
     */
    
    /*public static void weatherReport(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
    	if (entityBlock) {
            if (punch.shootingStand.heavyweather) {
                if (punch.shootingStand.orarush) {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 70, 10));
                }
                else {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 140, 4));
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.GLOWING, 400, 0));
                    
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
            else {
                final float p = 0.2f;
                final float p2 = 0.4f;
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 10.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
            }
        }
        else if (!entityBlock) {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
            }
            else {
                punch.remove();
            }
        }
    }*/
    
    /**
     * Gold Experience's punch
     */
    
   /*public static void goldExperience(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {   	
        if (entityBlock) 
        {
            if (punch.shootingStand.life) 
            {
                if (punch.shootingStand.orarush) 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
                }
                else 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.3f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
                if(LivingEntity instanceof PlayerEntity)
                {
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
                	((PlayerEntity) LivingEntity).jump();
                }
            }
            else 
            {
                final float p = 0.2f;
                final float p2 = 0.4f;
                if (LivingEntity instanceof EntityVampire) 
                {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 10.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else 
                    {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                else if (LivingEntity instanceof Entityhamonuser) 
                {
                    final Entityhamonuser hamonuser = (Entityhamonuser)LivingEntity;
                    if (hamonuser.getOwner() == punch.shootingStand.getMaster()) 
                    {
                        result = null;
                    }
                    else 
                    {
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                        LivingEntity.hurtResistantTime = 0;
                        LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                        if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) 
                        {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                        }
                        else 
                        {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                        }
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                        punch.remove();
                    }
                }
                else
                {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else
                    {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
            }
        }
        if (!entityBlock) 
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
                if (punch.shootingStand.life) 
                {
                	if(hardness<=15.0f)
                	{
                	if(transformed==0)
                	{
                	if(blockB == Blocks.GRASS || blockB == Blocks.NETHERRACK)
                	{
                		transformed = transformed + 1;
                		EntityBat bat = new EntityBat(punch.world);
                		bat.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(bat);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.LEAVES)
                	{
                		transformed = transformed + 1;
                		EntityParrot parrot = new EntityParrot(punch.world);
                		parrot.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(parrot);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.TNT)
                	{
                		transformed = transformed + 1;
                		EntityCreeper creeper = new EntityCreeper(punch.world);
                		creeper.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(creeper);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.SLIME_BLOCK)
                	{
                		transformed = transformed + 1;
                		EntitySlime slime = new EntitySlime(punch.world);
                		slime.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(slime);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.STONE)
                	{
                		transformed = transformed + 1;
                		EntitySilverfish silverfish = new EntitySilverfish(punch.world);
                		silverfish.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(silverfish);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.POTATOES || blockB == Blocks.LOG)
                	{
                		transformed = transformed + 1;
                		EntityPig pig = new EntityPig(punch.world);
                		pig.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(pig);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.HAY_BLOCK)
                	{
                		transformed = transformed + 1;
                		EntityHorse horse = new EntityHorse(punch.world);
                		horse.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(horse);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MYCELIUM)
                	{
                		transformed = transformed + 1;
                		EntityMooshroom mooshroom = new EntityMooshroom(punch.world);
                		mooshroom.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(mooshroom);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE)
                	{
                		transformed = transformed + 1;
                		EntityVillager villager = new EntityVillager(punch.world);
                		villager.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(villager);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MOSSY_COBBLESTONE)
                	{
                		transformed = transformed + 1;
                		EntityZombie zombie = new EntityZombie(punch.world);
                		zombie.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(zombie);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF)
                	{
                		transformed = transformed + 1;
                		EntitySkeleton skeleton = new EntitySkeleton(punch.world);
                		skeleton.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(skeleton);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.VINE)
                	{
                		transformed = transformed + 1;
                		EntitySpider spider = new EntitySpider(punch.world);
                		spider.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(spider);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.WOOL || blockB == Blocks.WHEAT)
                	{
                		transformed = transformed + 1;
                		EntitySheep sheep = new EntitySheep(punch.world);
                		sheep.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(sheep);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BEETROOTS)
                	{
                		transformed = transformed + 1;
                		EntityCow cow = new EntityCow(punch.world);
                		cow.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(cow);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.CARROTS)
                	{
                		transformed = transformed + 1;
                		EntityRabbit rabbit = new EntityRabbit(punch.world);
                		rabbit.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(rabbit);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.FIRE)
                	{
                		transformed = transformed + 1;
                		EntityBlaze blaze = new EntityBlaze(punch.world);
                		blaze.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(blaze);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MAGMA)
                	{
                		transformed = transformed + 1;
                		EntityMagmaCube magma = new EntityMagmaCube(punch.world);
                		magma.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(magma);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.NETHER_BRICK || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK)
                	{
                		transformed = transformed + 1;
                		EntityPigZombie zombiepig = new EntityPigZombie(punch.world);
                		zombiepig.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(zombiepig);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.SILVER_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX)
                	{
                		transformed = transformed + 1;
                		EntityShulker shulker = new EntityShulker(punch.world);
                		shulker.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(shulker);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.SAND || blockB == Blocks.SANDSTONE)
                	{
                		transformed = transformed + 1;
                		EntityHusk husk = new EntityHusk(punch.world);
                		husk.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(husk);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.FARMLAND)
                	{
                		Minecraft.getInstance().player.getFoodStats().addStats(2, 0.1f);
                		punch.remove();
                	}
                	punch.world.playSound((PlayerEntity)Minecraft.getInstance().player, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                		if(!Minecraft.getInstance().player.capabilities.isCreativeMode)
                		{
                		Minecraft.getInstance().player.getFoodStats().addStats(-2, 0.0f);
                		}
                	}
                }
                }
                if(hardness != -1.0f && hardness <= 3.0f)
                {
                	if(!punch.shootingStand.life)
                	{
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    blockB.getDr
                    punch.remove();
                	}
                }
                else
                {
                	punch.remove();
                }
        }
    }*/
    
    /**
     * GER's punch
     */
    
    /*public static void goldExperienceRequiem(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {   	
        if (entityBlock) 
        {
            if (punch.shootingStand.ger) 
            {
                if (punch.shootingStand.orarush) 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 2));
                }
                else 
                {
                    LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 3));
                }
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 0.3f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, 0, 0);
                punch.remove();
                if(LivingEntity instanceof PlayerEntity)
                {
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 3));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 4));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 3));
                	LivingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 5));
                	((PlayerEntity) LivingEntity).jump();
                }
            }
            else 
            {
                final float p = 0.2f;
                final float p2 = 0.4f;
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0)
                {
                	LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                }
                else
                {
                	LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                }
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
            }
        }
        if (!entityBlock) 
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
                if (punch.shootingStand.ger) 
                {
                	if(gerTransformed<2)
                	{
                	if(blockB == Blocks.GRASS || blockB == Blocks.NETHERRACK || blockB == Blocks.BEDROCK)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityBat bat = new EntityBat(punch.world);
                		bat.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(bat);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.OAK_LEAVES || blockB == Blocks.ACACIA_LEAVES || blockB == Blocks.SPRUCE_LEAVES || blockB == Blocks.BIRCH_LEAVES || blockB == Blocks.JUNGLE_LEAVES)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityParrot parrot = new EntityParrot(punch.world);
                		parrot.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(parrot);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.TNT)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityCreeper creeper = new EntityCreeper(punch.world);
                		creeper.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(creeper);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.SLIME_BLOCK)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntitySlime slime = new EntitySlime(punch.world);
                		slime.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(slime);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.STONE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntitySilverfish silverfish = new EntitySilverfish(punch.world);
                		silverfish.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(silverfish);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.POTATOES || blockB == Blocks.LOG)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityPig pig = new EntityPig(punch.world);
                		pig.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(pig);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.HAY_BLOCK)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityHorse horse = new EntityHorse(punch.world);
                		horse.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(horse);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MYCELIUM)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityMooshroom mooshroom = new EntityMooshroom(punch.world);
                		mooshroom.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(mooshroom);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityVillager villager = new EntityVillager(punch.world);
                		villager.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(villager);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MOSSY_COBBLESTONE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityZombie zombie = new EntityZombie(punch.world);
                		zombie.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(zombie);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntitySkeleton skeleton = new EntitySkeleton(punch.world);
                		skeleton.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(skeleton);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.VINE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntitySpider spider = new EntitySpider(punch.world);
                		spider.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(spider);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.WHITE_WOOL || blockB == Blocks.WHEAT)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntitySheep sheep = new EntitySheep(punch.world);
                		sheep.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(sheep);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BEETROOTS)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityCow cow = new EntityCow(punch.world);
                		cow.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(cow);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.CARROTS)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityRabbit rabbit = new EntityRabbit(punch.world);
                		rabbit.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(rabbit);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.FIRE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityBlaze blaze = new EntityBlaze(punch.world);
                		blaze.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(blaze);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.MAGMA_BLOCK)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityMagmaCube magma = new EntityMagmaCube(punch.world);
                		magma.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(magma);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.NETHER_BRICKS || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityPigZombie zombiepig = new EntityPigZombie(punch.world);
                		zombiepig.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(zombiepig);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.SILVER_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityShulker shulker = new EntityShulker(punch.world);
                		shulker.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(shulker);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.SAND || blockB == Blocks.SANDSTONE)
                	{
                		gerTransformed = gerTransformed + 1;
                		EntityHusk husk = new EntityHusk(punch.world);
                		husk.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(husk);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.BEACON)
                	{
                		gerTransformed = gerTransformed + 2;
                		WitherEntity wither = new WitherEntity(punch.world);
                		wither.setPosition((double)(punch.getxTile() + 0.5f), (double)(punch.getyTile() + 0.5f), (double)(punch.getzTile() + 0.5f), punch.rotationYaw, punch.rotationPitch);
                		punch.world.addEntity(wither);
                		punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                		punch.remove();
                	}
                	if(blockB == Blocks.FARMLAND)
                	{
                		Minecraft.getInstance().player.getFoodStats().addStats(2, 0.1f);
                		punch.remove();
                	}
                	punch.world.playSound((PlayerEntity)Minecraft.getInstance().player, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                	}
                }
                	if(!punch.shootingStand.ger)
                	{
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
                	}
                else
                {
                	punch.remove();
                }
        }
    }*/
    
    /**
     * The World's punch
     */
    
    /*public static void theWorld(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
        if (entityBlock) 
        {
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
                final float p = 0.2f;
                final float p2 = 0.4f;
                if (LivingEntity instanceof EntityVampire) {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 10.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                else if (LivingEntity instanceof Entityhamonuser) {
                    final Entityhamonuser hamonuser = (Entityhamonuser)LivingEntity;
                    if (hamonuser.getOwner() == punch.shootingStand.getMaster()) {
                        result = null;
                    }
                    else {
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                        LivingEntity.hurtResistantTime = 0;
                        LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                        if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                        }
                        else {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                        }
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                        punch.remove();
                    }
                }
                else {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
        }
        else if (!entityBlock) {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    blockB.dropBlockAsItemWithChance(punch.world, blockpos, BlockState, 1.0f, 0);
                    punch.remove();
            }
            else {
                punch.remove();
            }
        }
    }*/
    
    /**
     * Killer Queen's punch
     */
     
    /*public static void killerQueen(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
        if (entityBlock) 
        {
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
                final float p = 0.2f;
                final float p2 = 0.4f;
                if (LivingEntity instanceof EntityVampire) {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 10.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                else if (LivingEntity instanceof Entityhamonuser) {
                    final Entityhamonuser hamonuser = (Entityhamonuser)LivingEntity;
                    if (hamonuser.getOwner() == punch.shootingStand.getMaster()) {
                        result = null;
                    }
                    else {
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                        LivingEntity.hurtResistantTime = 0;
                        LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                        if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                        }
                        else {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                        }
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                        punch.remove();
                    }
                }
                else {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                if(punch.shootingStand.getLastAttackedEntity()!=null)
                {
                	StandPunchOnHit.bomb = punch.shootingStand.getLastAttackedEntity();
                }
        }
        else if (!entityBlock)
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) 
            {
            	EntityLiving particle = new EntityBat(punch.world);
            	particle.setPosition(blockpos.getX(), blockpos.getY(), blockpos.getZ(), punch.rotationYaw, punch.rotationPitch);
            	particle.spawnExplosionParticle();
            	punch.world.createExplosion(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.5f, true);
    			punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                punch.remove();
            }
            else
            {
                punch.remove();
            }
        }
    }*/
    
    /**
     * Killer Queen 8's punch
     */
     
    /*public static void killerQueen8(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
        if (entityBlock) 
        {
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
                LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                punch.remove();
                final float p = 0.2f;
                final float p2 = 0.4f;
                if (LivingEntity instanceof EntityVampire) {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 10.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                else if (LivingEntity instanceof Entityhamonuser) {
                    final Entityhamonuser hamonuser = (Entityhamonuser)LivingEntity;
                    if (hamonuser.getOwner() == punch.shootingStand.getMaster()) {
                        result = null;
                    }
                    else {
                        LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                        LivingEntity.hurtResistantTime = 0;
                        LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                        if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                        }
                        else {
                            LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                        }
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                        punch.remove();
                    }
                }
                else {
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                    punch.remove();
                }
                if(punch.shootingStand.getLastAttackedEntity()!=null)
                {
                	StandPunchOnHit.bomb = punch.shootingStand.getLastAttackedEntity();
                }
        }
        else if (!entityBlock)
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) 
            {
            	EntityLiving particle = new EntityBat(punch.world);
            	particle.setPosition(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            	particle.spawnExplosionParticle();
            	punch.world.createExplosion(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), 0.5f, true);
    			punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                punch.remove();
            }
            else
            {
                punch.remove();
            }
        }
    }*/
    
    /**
     * Sticky Finger's punch
     */
     
    /*public static void stickyFingers(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
    {
        if (entityBlock) 
        {
                LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                LivingEntity.hurtResistantTime = 0;
                LivingEntity.setMotion(0, 0, 0);
                punch.remove();
                final float p = 0.2f;
                final float p2 = 0.4f;
                    LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)punch.shootingStand.getMaster()), 2.0f);
                    LivingEntity.hurtResistantTime = 0;
                    LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
                    if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
                    }
                    else {
                        LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
                    }
                    LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
                if(punch.shootingStand.getLastAttackedEntity()!=null)
                {
                	StandPunchOnHit.bomb = punch.shootingStand.getLastAttackedEntity();
                }
        }
        else if (!entityBlock)
        {
            final Block blockB = punch.getinTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) 
            {
    			punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                punch.remove();
            }
            else
            {
                punch.remove();
            }
        }
    }*/
}
