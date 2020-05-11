package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class StandPunchEffects
{
	public static Entity bomb = null;
	
	public static void getStandSpecific(final RayTraceResult result, final LivingEntity entityIn, final EntityStandPunch punch, final boolean entityBlock, final int entityId) {
        switch (entityId) {
            case 0: {
                basicDefault(result, entityIn, entityBlock);
                break;
            }
            case JojoLibs.StandID.kingCrimson: {
                kingCrimson(result, entityIn, punch, entityBlock);
                break;
            }
            case JojoLibs.StandID.dirtyDeedsDoneDirtCheap: {
                dirtyDeedsDoneDirtCheap(result, entityIn, punch, entityBlock);
                break;
            }
            case JojoLibs.StandID.goldExperience: {
				goldExperience(result, entityIn, punch, entityBlock);
				break;
			}
            case JojoLibs.StandID.madeInHeaven: {
            	madeInHeaven(result, entityIn, punch, entityBlock);
            	break;
            }
            case JojoLibs.StandID.GER: {
            	goldExperienceRequiem(result, entityIn, punch, entityBlock);
            	break;
            }
            /*case 8: {
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
    
    /*public static void crazyDiamond(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock) 
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
                    //blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
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
    
    public static void dirtyDeedsDoneDirtCheap(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock)
    {
    	if (entityBlock) {
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
     * Made in Heaven's punch
     */
    
    public static void madeInHeaven(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock)
	{
		if (entityBlock) {
			if (punch.shootingStand.heaven) {
				if (punch.shootingStand.orarush) {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 140, 1));
				} else {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 130, 1));
				}
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 1.7f);
				LivingEntity.hurtResistantTime = 0;
				LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
				punch.remove();
			} else {
				final float p = 0.2f;
				final float p2 = 0.4f;
				if (LivingEntity instanceof WitherEntity) {
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 15.0f);
					LivingEntity.hurtResistantTime = 0;
					LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
					if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
					} else {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
					}
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
					punch.remove();
				}
				if (LivingEntity instanceof EnderDragonEntity) {
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 50.0f);
					LivingEntity.hurtResistantTime = 0;
					LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
					if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
					} else {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
					}
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
					punch.remove();
				} else {
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 2.0f);
					LivingEntity.hurtResistantTime = 0;
					LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
					if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
					} else {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
					}
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
					punch.remove();
				}
			}
		}
				else if (!entityBlock) {
					final Block blockB = punch.getinTile();
					final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
					final BlockState BlockState = punch.world.getBlockState(blockpos);
					final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
					if (hardness != -1.0f && hardness < 100.0f) {
						punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
						blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
						punch.remove();
					} else {
						punch.remove();
					}
				}
		}

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
                    blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
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

		public static void goldExperience (RayTraceResult result,final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock)
		{
			if (entityBlock) {
				if (punch.shootingStand.life) {
					if (punch.shootingStand.orarush) {
						LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
					} else {
						LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
					}
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 0.3f);
					LivingEntity.hurtResistantTime = 0;
					LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
					punch.remove();
					if (LivingEntity instanceof PlayerEntity) {
						LivingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
						LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
						LivingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
						LivingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
						((PlayerEntity) LivingEntity).jump();
					}
				} else {
					final float p = 0.2f;
					final float p2 = 0.4f;
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 2.0f);
					LivingEntity.hurtResistantTime = 0;
					LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
					if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
					} else {
						LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
					}
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
					punch.remove();
				}
			}
			if (!entityBlock) {
				final Block blockB = punch.getinTile();
				final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
				final BlockState BlockState = punch.world.getBlockState(blockpos);
				final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
				IStand props = JojoProvider.get(punch.shootingStand.getMaster());
				if (punch.shootingStand.life && blockB != Blocks.AIR&& blockB != Blocks.AIR) {
					if (hardness <= 15.0f) {
						if (props.getTransformed() == 0) {
							if (blockB == Blocks.GRASS || blockB == Blocks.GRASS_BLOCK || blockB == Blocks.NETHERRACK)
							{
								props.addTransformed(1);
								BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
								bat.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(bat);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.JUNGLE_LEAVES) {
								props.addTransformed(1);
								ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
								parrot.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(parrot);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.TNT) {
								props.addTransformed(1);
								CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
								creeper.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(creeper);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.SLIME_BLOCK) {
								props.addTransformed(1);
								SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
								slime.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(slime);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.STONE) {
								props.addTransformed(1);
								SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
								silverfish.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(silverfish);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.POTATOES || blockB == Blocks.OAK_LOG) {
								props.addTransformed(1);
								PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
								pig.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(pig);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.HAY_BLOCK) {
								props.addTransformed(1);
								HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
								horse.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(horse);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.MYCELIUM) {
								props.addTransformed(1);
								MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
								mooshroom.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(mooshroom);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE) {
								props.addTransformed(1);
								VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
								villager.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(villager);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.MOSSY_COBBLESTONE) {
								props.addTransformed(1);
								ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
								zombie.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(zombie);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF) {
								props.addTransformed(1);
								SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
								skeleton.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(skeleton);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.VINE) {
								props.addTransformed(1);
								SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
								spider.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(spider);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.WHITE_WOOL || blockB == Blocks.WHEAT) {
								props.addTransformed(1);
								SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
								sheep.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(sheep);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.BEETROOTS) {
								props.addTransformed(1);
								CowEntity cow = new CowEntity(EntityType.COW, punch.world);
								cow.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(cow);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.CARROTS) {
								props.addTransformed(1);
								RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
								rabbit.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(rabbit);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.FIRE) {
								props.addTransformed(1);
								BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
								blaze.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(blaze);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.MAGMA_BLOCK) {
								props.addTransformed(1);
								MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
								magma.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(magma);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.NETHER_BRICKS || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK) {
								props.addTransformed(1);
								ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
								zombiepig.setPosition(punch.getxTile() + 0.5f, (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(zombiepig);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX) {
								props.addTransformed(1);
								ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
								shulker.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(shulker);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.SAND || blockB == Blocks.SANDSTONE) {
								props.addTransformed(1);
								HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
								husk.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(husk);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.SEA_PICKLE) {
								props.addTransformed(1);
								SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
								salmon.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(salmon);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.SEA_LANTERN || blockB == Blocks.PRISMARINE) {
								props.addTransformed(1);
								GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
								guardian.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(guardian);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.DIAMOND_BLOCK) {
								props.addTransformed(1);
								DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
								dolphin.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(dolphin);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.TURTLE_EGG) {
								props.addTransformed(1);
								TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
								turtle.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
								punch.world.addEntity(turtle);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.remove();
							}
							if (blockB == Blocks.FARMLAND) {
								punch.standMaster.getFoodStats().addStats(2, 0.1f);
								punch.remove();
							}
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							if(punch.ticksExisted > 60)
							{
								punch.remove();
							}
							if (!punch.standMaster.isCreative())
							{
								punch.standMaster.getFoodStats().addStats(-2, 0.0f);
								punch.remove();
							}
							props.setCooldown(220);
						}
					}
				}
				if (hardness != -1.0f && hardness <= 3.0f && blockB != Blocks.AIR) {
					if (!punch.shootingStand.life) {
						punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
						blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
						punch.remove();
					}
				} else {
					punch.remove();
				}
			} else {
				punch.remove();
			}
		}
    
    /**
     * GER's punch
     */
    
    public static void goldExperienceRequiem(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean entityBlock)
    {
		if (entityBlock) {
			if (punch.shootingStand.ger) {
				if (punch.shootingStand.orarush) {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
				} else {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
				}
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 0.3f);
				LivingEntity.hurtResistantTime = 0;
				LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
				punch.remove();
				if (LivingEntity instanceof PlayerEntity) {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
					LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
					LivingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
					LivingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
					((PlayerEntity) LivingEntity).jump();
				}
			} else {
				final float p = 0.2f;
				final float p2 = 0.4f;
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity) punch.shootingStand.getMaster()), 2.0f);
				LivingEntity.hurtResistantTime = 0;
				LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
				if (LivingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p2, LivingEntity.getMotion().getZ());
				} else {
					LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY() - p, LivingEntity.getMotion().getZ());
				}
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
				punch.remove();
			}
		}
		if (!entityBlock) {
			final Block blockB = punch.getinTile();
			final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
			final BlockState BlockState = punch.world.getBlockState(blockpos);
			final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
			IStand props = JojoProvider.get(punch.shootingStand.getMaster());
			if (punch.shootingStand.ger && blockB != Blocks.AIR) {
					if (props.getTransformed() < 2) {
						if (blockB == Blocks.GRASS || blockB == Blocks.GRASS_BLOCK|| blockB == Blocks.NETHERRACK)
						{
							props.addTransformed(1);
							BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
							bat.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(bat);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.JUNGLE_LEAVES) {
							props.addTransformed(1);
							ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
							parrot.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(parrot);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.TNT) {
							props.addTransformed(1);
							CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
							creeper.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(creeper);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.SLIME_BLOCK) {
							props.addTransformed(1);
							SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
							slime.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(slime);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.STONE) {
							props.addTransformed(1);
							SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
							silverfish.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(silverfish);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.POTATOES || blockB == Blocks.OAK_LOG) {
							props.addTransformed(1);
							PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
							pig.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(pig);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.HAY_BLOCK) {
							props.addTransformed(1);
							HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
							horse.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(horse);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.MYCELIUM) {
							props.addTransformed(1);
							MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
							mooshroom.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(mooshroom);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE) {
							props.addTransformed(1);
							VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
							villager.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(villager);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.MOSSY_COBBLESTONE) {
							props.addTransformed(1);
							ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
							zombie.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(zombie);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF) {
							props.addTransformed(1);
							SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
							skeleton.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(skeleton);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.VINE) {
							props.addTransformed(1);
							SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
							spider.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(spider);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.WHITE_WOOL || blockB == Blocks.WHEAT) {
							props.addTransformed(1);
							SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
							sheep.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(sheep);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.BEETROOTS) {
							props.addTransformed(1);
							CowEntity cow = new CowEntity(EntityType.COW, punch.world);
							cow.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(cow);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.CARROTS) {
							props.addTransformed(1);
							RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
							rabbit.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(rabbit);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.FIRE) {
							props.addTransformed(1);
							BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
							blaze.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(blaze);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.MAGMA_BLOCK) {
							props.addTransformed(1);
							MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
							magma.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(magma);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.NETHER_BRICKS || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK) {
							props.addTransformed(1);
							ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
							zombiepig.setPosition(punch.getxTile() + 0.5f, (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(zombiepig);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX) {
							props.addTransformed(1);
							ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
							shulker.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(shulker);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.SAND || blockB == Blocks.SANDSTONE) {
							props.addTransformed(1);
							HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
							husk.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(husk);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if(blockB == Blocks.BEACON)
						{
							props.addTransformed(2);
							WitherEntity wither = new WitherEntity(EntityType.WITHER, punch.world);
							wither.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(wither);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if(blockB == Blocks.SPONGE || blockB == Blocks.WET_SPONGE)
						{
							props.addTransformed(2);
							ElderGuardianEntity elderGuardian = new ElderGuardianEntity(EntityType.ELDER_GUARDIAN, punch.world);
							elderGuardian.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(elderGuardian);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if(blockB == Blocks.WITHER_SKELETON_SKULL || blockB == Blocks.WITHER_SKELETON_WALL_SKULL)
						{
							props.addTransformed(1);
							WitherSkeletonEntity witherSkeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, punch.world);
							witherSkeleton.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(witherSkeleton);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.SEA_PICKLE) {
							props.addTransformed(1);
							SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
							salmon.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(salmon);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.SEA_LANTERN || blockB == Blocks.PRISMARINE) {
							props.addTransformed(1);
							GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
							guardian.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(guardian);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.DIAMOND_BLOCK) {
							props.addTransformed(1);
							DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
							dolphin.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(dolphin);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.TURTLE_EGG) {
							props.addTransformed(1);
							TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
							turtle.setPosition((double) (punch.getxTile() + 0.5f), (double) (punch.getyTile() + 0.5f), (double) (punch.getzTile() + 0.5f));
							punch.world.addEntity(turtle);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.remove();
						}
						if (blockB == Blocks.FARMLAND) {
							punch.standMaster.getFoodStats().addStats(2, 0.1f);
							punch.remove();
						}
						punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
						if(punch.ticksExisted > 60)
						{
							punch.remove();
						}
						if (!punch.standMaster.isCreative())
						{
							punch.standMaster.getFoodStats().addStats(-2, 0.0f);
							punch.remove();
						}
						props.setCooldown(220);
					}
			}
			if (blockB != Blocks.AIR) {
				if (!punch.shootingStand.ger) {
					punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
					blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
					punch.remove();
				}
			} else {
				punch.remove();
			}
		} else {
			punch.remove();
		}
    }
    
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
                    blockB.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockpos, BlockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
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
