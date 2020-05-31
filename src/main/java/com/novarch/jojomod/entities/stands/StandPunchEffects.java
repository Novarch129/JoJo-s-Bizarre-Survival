package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.IStand;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.crazyDiamond.EntityCrazyDiamond;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;

public abstract class StandPunchEffects
{
	public static void getStandSpecific(final RayTraceResult result, final LivingEntity entityIn, final EntityStandPunch punch, final boolean isBlock, final int standID) {
        switch (standID) {
			case JojoLibs.StandID.kingCrimson: {
                kingCrimson(result, entityIn, punch, isBlock);
                break;
            }
            case JojoLibs.StandID.dirtyDeedsDoneDirtCheap: {
                dirtyDeedsDoneDirtCheap(result, entityIn, punch, isBlock);
                break;
            }
            case JojoLibs.StandID.goldExperience: {
				goldExperience(result, entityIn, punch, isBlock);
				break;
			}
            case JojoLibs.StandID.madeInHeaven: {
            	madeInHeaven(result, entityIn, punch, isBlock);
            	break;
            }
            case JojoLibs.StandID.GER: {
            	goldExperienceRequiem(result, entityIn, punch, isBlock);
            	break;
            }
            case JojoLibs.StandID.aerosmith: {
            	aerosmith(result, entityIn, punch, isBlock);
            	break;
			}
			case JojoLibs.StandID.weatherReport: {
				weatherReport(result, entityIn, punch, isBlock);
				break;
			}
			case JojoLibs.StandID.killerQueen: {
				killerQueen(result, entityIn, punch, isBlock);
				break;
			}
			case JojoLibs.StandID.crazyDiamond: {
				crazyDiamond(result, entityIn, punch, isBlock);
				break;
			}
            default: {
            	basicDefault(result, entityIn, isBlock);
            	break;
			}
        }
    }

	/**
	 * Default punch
	 */

    public static void basicDefault(final RayTraceResult result, final LivingEntity LivingEntity, final boolean isBlock) { }

    /**
     * King Crimson's punch
     */

    public static void kingCrimson(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean isBlock) {
        if (isBlock) {
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
        else if (!isBlock) {
            final Block blockB = punch.getInTile();
            final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
            final BlockState BlockState = punch.world.getBlockState(blockpos);
            final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
            if (hardness != -1.0f && hardness < 3.0f) {
                    punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                    /*Class clazz = blockB.getClass();
                    Field slipperiness = clazz.getDeclaredField("slipperiness");
                    slipperiness.setAccessible(true);
                    slipperiness.setInt(slipperiness, slipperiness.getModifiers() & ~Modifier.FINAL);
                    slipperiness.set(null, 100.0f);*/
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

    public static void dirtyDeedsDoneDirtCheap(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean isBlock)
    {
    	if (isBlock) {
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
        else if (!isBlock) {
            final Block blockB = punch.getInTile();
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

    public static void madeInHeaven(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean isBlock)
	{
		if (isBlock) {
			if (punch.shootingStand.ability) {
				if (punch.shootingStand.orarush) {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 140, 1));
				} else {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 130, 1));
				}
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 1.7f);
				LivingEntity.hurtResistantTime = 0;
				LivingEntity.setMotion(0, LivingEntity.getMotion().getY(), LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), 0, LivingEntity.getMotion().getZ());
				LivingEntity.setMotion(LivingEntity.getMotion().getX(), LivingEntity.getMotion().getY(), 0);
				punch.remove();
			} else {
				final float p = 0.2f;
				final float p2 = 0.4f;
				if (LivingEntity instanceof WitherEntity) {
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 15.0f);
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
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 50.0f);
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
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 2.0f);
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
				else if (!isBlock) {
					final Block blockB = punch.getInTile();
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
		 * Gold Experience's punch
		 */

		public static void goldExperience (RayTraceResult result,final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean isBlock)
		{
			if (isBlock) {
				if (punch.shootingStand.ability) {
					if (punch.shootingStand.orarush) {
						LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
					} else {
						LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
					}
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 0.3f);
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
					LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 2.0f);
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
			if (!isBlock) {
				final Block blockB = punch.getInTile();
				final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
				final BlockState BlockState = punch.world.getBlockState(blockpos);
				final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
				IStand props = JojoProvider.getCapabilityFromPlayer(punch.shootingStand.getMaster());
				if (punch.shootingStand.ability && blockB != Blocks.AIR && blockB != Blocks.AIR) {
					if (hardness <= 15.0f) {
						if (props.getTransformed() == 0) {
							if (blockB == Blocks.GRASS || blockB == Blocks.GRASS_BLOCK || blockB == Blocks.NETHERRACK)
							{
								props.addTransformed(1);
								BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
								bat.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(bat);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.JUNGLE_LEAVES) {
								props.addTransformed(1);
								ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
								parrot.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(parrot);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.TNT) {
								props.addTransformed(1);
								CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
								creeper.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(creeper);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.SLIME_BLOCK) {
								props.addTransformed(1);
								SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
								slime.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(slime);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.STONE) {
								props.addTransformed(1);
								SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
								silverfish.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(silverfish);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.POTATOES || blockB == Blocks.OAK_LOG) {
								props.addTransformed(1);
								PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
								pig.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(pig);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.HAY_BLOCK) {
								props.addTransformed(1);
								HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
								horse.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(horse);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.MYCELIUM) {
								props.addTransformed(1);
								MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
								mooshroom.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(mooshroom);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE) {
								props.addTransformed(1);
								VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
								villager.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(villager);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.MOSSY_COBBLESTONE) {
								props.addTransformed(1);
								ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
								zombie.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(zombie);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF) {
								props.addTransformed(1);
								SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
								skeleton.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(skeleton);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.VINE) {
								props.addTransformed(1);
								SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
								spider.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(spider);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.WHITE_WOOL || blockB == Blocks.WHEAT) {
								props.addTransformed(1);
								SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
								sheep.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(sheep);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.BEETROOTS) {
								props.addTransformed(1);
								CowEntity cow = new CowEntity(EntityType.COW, punch.world);
								cow.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(cow);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.CARROTS) {
								props.addTransformed(1);
								RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
								rabbit.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(rabbit);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.FIRE) {
								props.addTransformed(1);
								BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
								blaze.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(blaze);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.MAGMA_BLOCK) {
								props.addTransformed(1);
								MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
								magma.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(magma);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.NETHER_BRICKS || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK) {
								props.addTransformed(1);
								ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
								zombiepig.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(zombiepig);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX) {
								props.addTransformed(1);
								ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
								shulker.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(shulker);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.SAND || blockB == Blocks.SANDSTONE) {
								props.addTransformed(1);
								HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
								husk.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(husk);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.SEA_PICKLE) {
								props.addTransformed(1);
								SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
								salmon.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(salmon);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.SEA_LANTERN || blockB == Blocks.PRISMARINE) {
								props.addTransformed(1);
								GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
								guardian.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(guardian);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.DIAMOND_BLOCK) {
								props.addTransformed(1);
								DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
								dolphin.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								punch.world.addEntity(dolphin);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (blockB == Blocks.TURTLE_EGG) {
								props.addTransformed(1);
								TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
								turtle.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
								turtle.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
								punch.world.addEntity(turtle);
								punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative())
								{
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if(punch.ticksExisted > 60)
							{
								punch.remove();
							}
							props.setCooldown(80);
						}
					}
				}
				if (hardness != -1.0f && hardness <= 3.0f && blockB != Blocks.AIR) {
					if (!punch.shootingStand.ability) {
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

    public static void goldExperienceRequiem(RayTraceResult result, final LivingEntity LivingEntity, final EntityStandPunch punch, final boolean isBlock)
    {
		if (isBlock) {
			if (punch.shootingStand.ability) {
				if (punch.shootingStand.orarush) {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
				} else {
					LivingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
				}
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 0.3f);
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
				LivingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 2.0f);
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
		if (!isBlock) {
			final Block blockB = punch.getInTile();
			final BlockPos blockpos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
			final BlockState BlockState = punch.world.getBlockState(blockpos);
			final float hardness = BlockState.getBlockHardness(punch.world, blockpos);
			IStand props = JojoProvider.getCapabilityFromPlayer(punch.shootingStand.getMaster());
			if (punch.shootingStand.ability && blockB != Blocks.AIR) {
					if (props.getTransformed() < 2) {
						if (blockB == Blocks.GRASS || blockB == Blocks.GRASS_BLOCK|| blockB == Blocks.NETHERRACK)
						{
							props.addTransformed(1);
							BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
							BatEntity bat2 = new BatEntity(EntityType.BAT, punch.world);
							BatEntity bat3 = new BatEntity(EntityType.BAT, punch.world);
							bat.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							bat2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							bat3.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(bat);
							punch.world.addEntity(bat2);
							punch.world.addEntity(bat3);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.JUNGLE_LEAVES) {
							props.addTransformed(1);
							ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
							ParrotEntity parrot2 = new ParrotEntity(EntityType.PARROT, punch.world);
							parrot.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							parrot2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(parrot);
							punch.world.addEntity(parrot2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.TNT) {
							props.addTransformed(1);
							CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
							CreeperEntity creeper2 = new CreeperEntity(EntityType.CREEPER, punch.world);
							creeper.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							creeper2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(creeper);
							punch.world.addEntity(creeper2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.SLIME_BLOCK) {
							props.addTransformed(1);
							SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
							SlimeEntity slime2 = new SlimeEntity(EntityType.SLIME, punch.world);
							slime.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							slime2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(slime);
							punch.world.addEntity(slime2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.STONE) {
							props.addTransformed(1);
							SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
							SilverfishEntity silverfish2 = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
							silverfish.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							silverfish2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(silverfish);
							punch.world.addEntity(silverfish2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.POTATOES || blockB == Blocks.OAK_LOG) {
							props.addTransformed(1);
							PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
							PigEntity pig2 = new PigEntity(EntityType.PIG, punch.world);
							pig.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							pig2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(pig);
							punch.world.addEntity(pig2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.HAY_BLOCK) {
							props.addTransformed(1);
							HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
							HorseEntity horse2 = new HorseEntity(EntityType.HORSE, punch.world);
							horse.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							horse2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(horse);
							punch.world.addEntity(horse2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.MYCELIUM) {
							props.addTransformed(1);
							MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
							MooshroomEntity mooshroom2 = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
							mooshroom.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							mooshroom2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(mooshroom);
							punch.world.addEntity(mooshroom2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.EMERALD_BLOCK || blockB == Blocks.EMERALD_ORE) {
							props.addTransformed(1);
							VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
							VillagerEntity villager2 = new VillagerEntity(EntityType.VILLAGER, punch.world);
							villager.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							villager2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(villager);
							punch.world.addEntity(villager2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.MOSSY_COBBLESTONE) {
							props.addTransformed(1);
							ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
							ZombieEntity zombie2 = new ZombieEntity(EntityType.ZOMBIE, punch.world);
							zombie.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							zombie2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(zombie);
							punch.world.addEntity(zombie2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.BONE_BLOCK || blockB == Blocks.BOOKSHELF) {
							props.addTransformed(1);
							SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
							SkeletonEntity skeleton2 = new SkeletonEntity(EntityType.SKELETON, punch.world);
							skeleton.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							skeleton2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(skeleton);
							punch.world.addEntity(skeleton2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.VINE) {
							props.addTransformed(1);
							SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
							SpiderEntity spider2 = new SpiderEntity(EntityType.SPIDER, punch.world);
							spider.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							spider2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(spider);
							punch.world.addEntity(spider2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.WHITE_WOOL || blockB == Blocks.WHEAT) {
							props.addTransformed(1);
							SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
							SheepEntity sheep2 = new SheepEntity(EntityType.SHEEP, punch.world);
							sheep.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							sheep2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(sheep);
							punch.world.addEntity(sheep2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.BEETROOTS) {
							props.addTransformed(1);
							CowEntity cow = new CowEntity(EntityType.COW, punch.world);
							CowEntity cow2 = new CowEntity(EntityType.COW, punch.world);
							cow.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							cow2.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(cow);
							punch.world.addEntity(cow2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.CARROTS) {
							props.addTransformed(1);
							RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
							RabbitEntity rabbit2 = new RabbitEntity(EntityType.RABBIT, punch.world);
							rabbit.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							rabbit2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(rabbit);
							punch.world.addEntity(rabbit2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.FIRE) {
							props.addTransformed(1);
							BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
							BlazeEntity blaze2 = new BlazeEntity(EntityType.BLAZE, punch.world);
							blaze.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							blaze2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(blaze);
							punch.world.addEntity(blaze2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.MAGMA_BLOCK) {
							props.addTransformed(1);
							MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
							MagmaCubeEntity magma2 = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
							magma.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							magma2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(magma);
							punch.world.addEntity(magma2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.NETHER_BRICKS || blockB == Blocks.NETHER_WART || blockB == Blocks.NETHER_WART_BLOCK) {
							props.addTransformed(1);
							ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
							ZombiePigmanEntity zombiepig2 = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
							zombiepig.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							zombiepig2.setPosition(punch.getxTile() + 0.5f, (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(zombiepig);
							punch.world.addEntity(zombiepig2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.BLACK_SHULKER_BOX || blockB == Blocks.BLUE_SHULKER_BOX || blockB == Blocks.BROWN_SHULKER_BOX || blockB == Blocks.CYAN_SHULKER_BOX || blockB == Blocks.GRAY_SHULKER_BOX || blockB == Blocks.GREEN_SHULKER_BOX || blockB == Blocks.LIGHT_BLUE_SHULKER_BOX || blockB == Blocks.LIME_SHULKER_BOX || blockB == Blocks.MAGENTA_SHULKER_BOX || blockB == Blocks.ORANGE_SHULKER_BOX || blockB == Blocks.PINK_SHULKER_BOX || blockB == Blocks.PURPLE_SHULKER_BOX || blockB == Blocks.RED_SHULKER_BOX || blockB == Blocks.WHITE_SHULKER_BOX || blockB == Blocks.YELLOW_SHULKER_BOX) {
							props.addTransformed(1);
							ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
							ShulkerEntity shulker2 = new ShulkerEntity(EntityType.SHULKER, punch.world);
							shulker.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							shulker2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(shulker);
							punch.world.addEntity(shulker2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.SAND || blockB == Blocks.SANDSTONE) {
							props.addTransformed(1);
							HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
							HuskEntity husk2 = new HuskEntity(EntityType.HUSK, punch.world);
							husk.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							husk2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(husk);
							punch.world.addEntity(husk2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if(blockB == Blocks.BEACON)
						{
							props.addTransformed(2);
							WitherEntity wither = new WitherEntity(EntityType.WITHER, punch.world);
							wither.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(wither);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if(blockB == Blocks.SPONGE || blockB == Blocks.WET_SPONGE)
						{
							props.addTransformed(2);
							ElderGuardianEntity elderGuardian = new ElderGuardianEntity(EntityType.ELDER_GUARDIAN, punch.world);
							elderGuardian.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(elderGuardian);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if(blockB == Blocks.WITHER_SKELETON_SKULL || blockB == Blocks.WITHER_SKELETON_WALL_SKULL)
						{
							props.addTransformed(1);
							WitherSkeletonEntity witherSkeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, punch.world);
							WitherSkeletonEntity witherSkeleton2 = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, punch.world);
							witherSkeleton.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							witherSkeleton2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(witherSkeleton);
							punch.world.addEntity(witherSkeleton2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.SEA_PICKLE) {
							props.addTransformed(1);
							SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
							SalmonEntity salmon2 = new SalmonEntity(EntityType.SALMON, punch.world);
							salmon.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							salmon2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(salmon);
							punch.world.addEntity(salmon2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.SEA_LANTERN || blockB == Blocks.PRISMARINE) {
							props.addTransformed(1);
							GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
							GuardianEntity guardian2 = new GuardianEntity(EntityType.GUARDIAN, punch.world);
							guardian.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							guardian2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(guardian);
							punch.world.addEntity(guardian2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.DIAMOND_BLOCK) {
							props.addTransformed(1);
							DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
							DolphinEntity dolphin2 = new DolphinEntity(EntityType.DOLPHIN, punch.world);
							dolphin.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							dolphin2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(dolphin);
							punch.world.addEntity(dolphin2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (blockB == Blocks.TURTLE_EGG) {
							props.addTransformed(1);
							TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
							TurtleEntity turtle2 = new TurtleEntity(EntityType.TURTLE, punch.world);
							turtle.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							turtle.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
							turtle2.setPosition((punch.getxTile() + 0.5f), (punch.getyTile() + 0.5f), (punch.getzTile() + 0.5f));
							punch.world.addEntity(turtle);
							punch.world.addEntity(turtle2);
							punch.world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if(punch.ticksExisted > 60)
						{
							punch.remove();
						}
						props.setCooldown(60);
					}
			}
			if (blockB != Blocks.AIR) {
				if (!punch.shootingStand.ability) {
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

	public static void aerosmith(RayTraceResult result, final LivingEntity livingEntity, final EntityStandPunch bullet, final boolean isBlock)
	{
		if(isBlock)
		{
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(bullet.shootingStand.getMaster()), 1.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, 0, 0);
			bullet.remove();
			if (livingEntity.getPosY() > bullet.shootingStand.getPosY() + 3.0)
			{
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		}
		else
		{
			final Block block = bullet.getInTile();
			final BlockPos blockPos = new BlockPos(bullet.getxTile(), bullet.getyTile(), bullet.getzTile());
			final BlockState blockState = bullet.world.getBlockState(blockPos);
			final float hardness = blockState.getBlockHardness(bullet.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f)
			{
				if(block == Blocks.TNT)
				{
					bullet.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					bullet.world.createExplosion(bullet, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0.8f, Explosion.Mode.DESTROY);
				}
				bullet.world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
				bullet.remove();
			}
			else
			{
				bullet.remove();
			}
		}
	}

	public static void weatherReport(RayTraceResult result, final LivingEntity livingEntity, final EntityStandPunch punch, final boolean isBlock)
	{
		if(isBlock)
		{
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 1.0f);
			if(punch.shootingStand.ability)
				livingEntity.addPotionEffect(new EffectInstance(EffectInit.OXYGEN_POISIONING.get(), 300, 15));
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, 0, 0);
			punch.remove();
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0)
			{
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		}
		else
		{
			final Block block = punch.getInTile();
			final BlockPos blockPos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
			final BlockState blockState = punch.world.getBlockState(blockPos);
			final float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f)
			{
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItem(punch.standMaster.getActiveHand()));
			}
			punch.remove();
		}
	}

	public static void killerQueen(RayTraceResult result, final LivingEntity livingEntity, final EntityStandPunch punch, final boolean isBlock)
	{
		if (isBlock) {
			final float p = 0.2f;
			final float p2 = 0.4f;
			((EntityKillerQueen) punch.shootingStand).setBombEntity(livingEntity);
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 0.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - p2, livingEntity.getMotion().getZ());
			}
			else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - p, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			final Block block = punch.getInTile();
			final BlockPos blockPos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
			final BlockState blockState = punch.world.getBlockState(blockPos);
			final float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f)
			{
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockPos, blockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void crazyDiamond(RayTraceResult result, final LivingEntity livingEntity, final EntityStandPunch punch, final boolean isBlock)
	{
		if (isBlock) {
			final float p = 0.2f;
			final float p2 = 0.4f;
			if(punch.shootingStand.ability)
				livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 2));
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand.getMaster()), 0.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - p2, livingEntity.getMotion().getZ());
			}
			else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - p, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			final Block block = punch.getInTile();
			final BlockPos blockPos = new BlockPos(punch.getxTile(), punch.getyTile(), punch.getzTile());
			final BlockState blockState = punch.world.getBlockState(blockPos);
			final float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f)
			{
				if(block.getDefaultState().getMaterial() != Material.AIR && block.getDefaultState().getMaterial() != Material.WATER) {
					((EntityCrazyDiamond)punch.shootingStand).addBlock(block);
					if(punch.world.getBlockState(blockPos).getMaterial() != Material.AIR && punch.world.getBlockState(blockPos).getMaterial() != Material.WATER)
						((EntityCrazyDiamond)punch.shootingStand).addBlockPos(blockPos);
				}
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				if(!punch.shootingStand.ability)
					block.harvestBlock(punch.world, punch.shootingStand.getMaster(), blockPos, blockState, null, punch.shootingStand.getMaster().getHeldItemMainhand());
			}
		}
		punch.remove();
	}
}
