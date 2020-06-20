package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.crazyDiamond.EntityCrazyDiamond;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.entities.stands.purpleHaze.EntityPurpleHaze;
import com.novarch.jojomod.events.custom.StandPunchEvent;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

@SuppressWarnings({"unused", "ConstantConditions"})
public abstract class StandPunchEffects {
	/**
	 * Gets the appropriate effect for an {@link EntityStandPunch} based on it's {@link Util.StandID}.
	 *
	 * @param result	The {@link RayTraceResult} of the {@link EntityStandPunch}.
	 * @param entityIn	The {@link LivingEntity} that the {@link EntityStandPunch} has hit, {@link Nullable}.
	 * @param punch		The {@link EntityStandPunch} that called the method
	 * @param isEntity	A <code>boolean</code> that defines whether the punch hit a {@link LivingEntity} or a {@link Block}.
	 * @param standID	The {@link Util.StandID} of the {@link EntityStandPunch} that called the method, used to determine what effect the punch will have.
	 */
	public static void getStandSpecific(RayTraceResult result, @Nullable LivingEntity entityIn, EntityStandPunch punch, boolean isEntity, int standID) {
		if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent(punch, result, entityIn, isEntity))) return;
		switch (standID) {
			case Util.StandID.kingCrimson: {
				kingCrimson(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.dirtyDeedsDoneDirtCheap: {
				dirtyDeedsDoneDirtCheap(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.goldExperience: {
				goldExperience(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.madeInHeaven: {
				madeInHeaven(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.GER: {
				goldExperienceRequiem(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.aerosmith: {
				aerosmith(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.weatherReport: {
				weatherReport(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.killerQueen: {
				killerQueen(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.crazyDiamond: {
				crazyDiamond(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.purpleHaze: {
				purpleHaze(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.whitesnake: {
				whitesnake(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.cMoon: {
				cMoon(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.theWorld: {
				theWorld(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.starPlatinum: {
				starPlatinum(result, entityIn, punch, isEntity);
				break;
			}
			case Util.StandID.magiciansRed: {
				magiciansRed(result, entityIn, punch, isEntity);
				break;
			}
			default: {
				basicDefault(result, entityIn, punch, isEntity);
				break;
			}
		}
	}

	public static void basicDefault(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if(isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			livingEntity.attackEntityFrom(DamageSource.GENERIC, 1.0f);
		}
		else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			punch.world.removeBlock(new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile()), false);
		}
	}

	public static void kingCrimson(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			if (punch.shootingStand.orarush)
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 1.5f);
			else
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 3.0f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void dirtyDeedsDoneDirtCheap(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 2.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void madeInHeaven(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if(Stand.getCapabilityFromPlayer(punch.standMaster).getAct() == 2) {
			whitesnake(result, livingEntity, punch, isEntity);
			return;
		} else if(Stand.getCapabilityFromPlayer(punch.standMaster).getAct() == 1) {
			cMoon(result, livingEntity, punch, isEntity);
			return;
		}
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if (punch.shootingStand.ability) {
				if (punch.shootingStand.orarush) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 140, 1));
				} else {
					livingEntity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 130, 1));
				}
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 3.0f);
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				livingEntity.setMotion(livingEntity.getMotion().getX(), 0, livingEntity.getMotion().getZ());
			} else {
				if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;

				if (livingEntity instanceof WitherEntity) {
					livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 15.0f);
					livingEntity.hurtResistantTime = 0;
					livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
					if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
						livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
					} else {
						livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
					}
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
					punch.remove();
				}
				if (livingEntity instanceof EnderDragonEntity) {
					livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 50.0f);
				} else {
					livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 2.0f);
				}
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
				} else {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
				}
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
			punch.remove();
		} else if (!isEntity) {
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 100.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
			punch.remove();
		}
	}

	public static void goldExperience(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if (punch.shootingStand.ability) {
				if (punch.shootingStand.orarush)
					livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
				else
					livingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				livingEntity.setMotion(livingEntity.getMotion().getX(), 0, livingEntity.getMotion().getZ());
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
				punch.remove();
				if (livingEntity instanceof PlayerEntity) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
					livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
					livingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
					livingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
					((PlayerEntity) livingEntity).jump();
				}
			} else {

				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 1.5f);
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
				} else {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
				}
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
				punch.remove();
			}
		} else {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			Stand.getLazyOptional(punch.standMaster).ifPresent(props -> {
				if (punch.shootingStand.ability && block != Blocks.AIR) {
					if (hardness <= 15.0f) {
						if (props.getTransformed() == 0) {
							if (block == Blocks.GRASS || block == Blocks.GRASS_BLOCK || block == Blocks.NETHERRACK) {
								props.addTransformed(1);
								BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
								bat.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(bat);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.JUNGLE_LEAVES) {
								props.addTransformed(1);
								ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
								parrot.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(parrot);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.TNT) {
								props.addTransformed(1);
								CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
								creeper.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(creeper);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.SLIME_BLOCK) {
								props.addTransformed(1);
								SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
								slime.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(slime);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.STONE) {
								props.addTransformed(1);
								SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
								silverfish.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(silverfish);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.POTATOES || block == Blocks.OAK_LOG) {
								props.addTransformed(1);
								PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
								pig.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(pig);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.HAY_BLOCK) {
								props.addTransformed(1);
								HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
								horse.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(horse);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.MYCELIUM) {
								props.addTransformed(1);
								MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
								mooshroom.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(mooshroom);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
								props.addTransformed(1);
								VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
								villager.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(villager);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.MOSSY_COBBLESTONE) {
								props.addTransformed(1);
								ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
								zombie.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(zombie);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.BONE_BLOCK || block == Blocks.BOOKSHELF) {
								props.addTransformed(1);
								SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
								skeleton.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(skeleton);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.VINE) {
								props.addTransformed(1);
								SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
								spider.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(spider);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.WHITE_WOOL || block == Blocks.WHEAT) {
								props.addTransformed(1);
								SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
								sheep.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(sheep);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.BEETROOTS) {
								props.addTransformed(1);
								CowEntity cow = new CowEntity(EntityType.COW, punch.world);
								cow.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(cow);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.CARROTS) {
								props.addTransformed(1);
								RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
								rabbit.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(rabbit);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.FIRE) {
								props.addTransformed(1);
								BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
								blaze.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(blaze);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.MAGMA_BLOCK) {
								props.addTransformed(1);
								MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
								magma.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(magma);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.NETHER_BRICKS || block == Blocks.NETHER_WART || block == Blocks.NETHER_WART_BLOCK) {
								props.addTransformed(1);
								ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
								zombiepig.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(zombiepig);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.BLACK_SHULKER_BOX || block == Blocks.BLUE_SHULKER_BOX || block == Blocks.BROWN_SHULKER_BOX || block == Blocks.CYAN_SHULKER_BOX || block == Blocks.GRAY_SHULKER_BOX || block == Blocks.GREEN_SHULKER_BOX || block == Blocks.LIGHT_BLUE_SHULKER_BOX || block == Blocks.LIME_SHULKER_BOX || block == Blocks.MAGENTA_SHULKER_BOX || block == Blocks.ORANGE_SHULKER_BOX || block == Blocks.PINK_SHULKER_BOX || block == Blocks.PURPLE_SHULKER_BOX || block == Blocks.RED_SHULKER_BOX || block == Blocks.WHITE_SHULKER_BOX || block == Blocks.YELLOW_SHULKER_BOX) {
								props.addTransformed(1);
								ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
								shulker.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(shulker);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.SAND || block == Blocks.SANDSTONE) {
								props.addTransformed(1);
								HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
								husk.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(husk);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.SEA_PICKLE) {
								props.addTransformed(1);
								SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
								salmon.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(salmon);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.SEA_LANTERN || block == Blocks.PRISMARINE) {
								props.addTransformed(1);
								GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
								guardian.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(guardian);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.DIAMOND_BLOCK) {
								props.addTransformed(1);
								DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
								dolphin.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								punch.world.addEntity(dolphin);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (block == Blocks.TURTLE_EGG) {
								props.addTransformed(1);
								TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
								turtle.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
								turtle.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
								punch.world.addEntity(turtle);
								punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
								punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
								if (!punch.standMaster.isCreative()) {
									punch.standMaster.getFoodStats().addStats(-2, 0.0f);
									punch.remove();
								}
								punch.remove();
							}
							if (punch.ticksExisted > 60) {
								punch.remove();
							}
							props.setCooldown(80);
						}
					}
				}
			});

			if (hardness != -1.0f && hardness <= 3.0f && block != Blocks.AIR) {
				if (!punch.shootingStand.ability) {
					punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
					punch.remove();
				}
			} else {
				punch.remove();
			}
		}
	}

	public static void goldExperienceRequiem(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if (punch.shootingStand.ability) {
				if (punch.shootingStand.orarush) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 40, 0));
				} else {
					livingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 1));
				}
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				livingEntity.setMotion(livingEntity.getMotion().getX(), 0, livingEntity.getMotion().getZ());
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
				punch.remove();
				if (livingEntity instanceof PlayerEntity) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
					livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
					livingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
					livingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
					((PlayerEntity) livingEntity).jump();
				}
			} else {

				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 5.0f);
				livingEntity.hurtResistantTime = 0;
				livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
				if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
				} else {
					livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
				}
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
				punch.remove();
			}
		} else {
			if (MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			Stand.getLazyOptional(punch.standMaster).ifPresent(props -> {
				if (punch.shootingStand.ability && block != Blocks.AIR) {
					if (props.getTransformed() < 2) {
						if (block == Blocks.GRASS || block == Blocks.GRASS_BLOCK || block == Blocks.NETHERRACK) {
							props.addTransformed(1);
							BatEntity bat = new BatEntity(EntityType.BAT, punch.world);
							BatEntity bat2 = new BatEntity(EntityType.BAT, punch.world);
							BatEntity bat3 = new BatEntity(EntityType.BAT, punch.world);
							bat.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							bat2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							bat3.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(bat);
							punch.world.addEntity(bat2);
							punch.world.addEntity(bat3);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.JUNGLE_LEAVES) {
							props.addTransformed(1);
							ParrotEntity parrot = new ParrotEntity(EntityType.PARROT, punch.world);
							ParrotEntity parrot2 = new ParrotEntity(EntityType.PARROT, punch.world);
							parrot.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							parrot2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(parrot);
							punch.world.addEntity(parrot2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.TNT) {
							props.addTransformed(1);
							CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, punch.world);
							CreeperEntity creeper2 = new CreeperEntity(EntityType.CREEPER, punch.world);
							creeper.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							creeper2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(creeper);
							punch.world.addEntity(creeper2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.SLIME_BLOCK) {
							props.addTransformed(1);
							SlimeEntity slime = new SlimeEntity(EntityType.SLIME, punch.world);
							SlimeEntity slime2 = new SlimeEntity(EntityType.SLIME, punch.world);
							slime.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							slime2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(slime);
							punch.world.addEntity(slime2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.STONE) {
							props.addTransformed(1);
							SilverfishEntity silverfish = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
							SilverfishEntity silverfish2 = new SilverfishEntity(EntityType.SILVERFISH, punch.world);
							silverfish.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							silverfish2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(silverfish);
							punch.world.addEntity(silverfish2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.POTATOES || block == Blocks.OAK_LOG) {
							props.addTransformed(1);
							PigEntity pig = new PigEntity(EntityType.PIG, punch.world);
							PigEntity pig2 = new PigEntity(EntityType.PIG, punch.world);
							pig.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							pig2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(pig);
							punch.world.addEntity(pig2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.HAY_BLOCK) {
							props.addTransformed(1);
							HorseEntity horse = new HorseEntity(EntityType.HORSE, punch.world);
							HorseEntity horse2 = new HorseEntity(EntityType.HORSE, punch.world);
							horse.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							horse2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(horse);
							punch.world.addEntity(horse2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.MYCELIUM) {
							props.addTransformed(1);
							MooshroomEntity mooshroom = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
							MooshroomEntity mooshroom2 = new MooshroomEntity(EntityType.MOOSHROOM, punch.world);
							mooshroom.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							mooshroom2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(mooshroom);
							punch.world.addEntity(mooshroom2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
							props.addTransformed(1);
							VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, punch.world);
							VillagerEntity villager2 = new VillagerEntity(EntityType.VILLAGER, punch.world);
							villager.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							villager2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(villager);
							punch.world.addEntity(villager2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.MOSSY_COBBLESTONE) {
							props.addTransformed(1);
							ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, punch.world);
							ZombieEntity zombie2 = new ZombieEntity(EntityType.ZOMBIE, punch.world);
							zombie.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							zombie2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(zombie);
							punch.world.addEntity(zombie2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.BONE_BLOCK || block == Blocks.BOOKSHELF) {
							props.addTransformed(1);
							SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, punch.world);
							SkeletonEntity skeleton2 = new SkeletonEntity(EntityType.SKELETON, punch.world);
							skeleton.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							skeleton2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(skeleton);
							punch.world.addEntity(skeleton2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.VINE) {
							props.addTransformed(1);
							SpiderEntity spider = new SpiderEntity(EntityType.SPIDER, punch.world);
							SpiderEntity spider2 = new SpiderEntity(EntityType.SPIDER, punch.world);
							spider.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							spider2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(spider);
							punch.world.addEntity(spider2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.WHITE_WOOL || block == Blocks.WHEAT) {
							props.addTransformed(1);
							SheepEntity sheep = new SheepEntity(EntityType.SHEEP, punch.world);
							SheepEntity sheep2 = new SheepEntity(EntityType.SHEEP, punch.world);
							sheep.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							sheep2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(sheep);
							punch.world.addEntity(sheep2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.BEETROOTS) {
							props.addTransformed(1);
							CowEntity cow = new CowEntity(EntityType.COW, punch.world);
							CowEntity cow2 = new CowEntity(EntityType.COW, punch.world);
							cow.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							cow2.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(cow);
							punch.world.addEntity(cow2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.CARROTS) {
							props.addTransformed(1);
							RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, punch.world);
							RabbitEntity rabbit2 = new RabbitEntity(EntityType.RABBIT, punch.world);
							rabbit.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							rabbit2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(rabbit);
							punch.world.addEntity(rabbit2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.FIRE) {
							props.addTransformed(1);
							BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, punch.world);
							BlazeEntity blaze2 = new BlazeEntity(EntityType.BLAZE, punch.world);
							blaze.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							blaze2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(blaze);
							punch.world.addEntity(blaze2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.MAGMA_BLOCK) {
							props.addTransformed(1);
							MagmaCubeEntity magma = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
							MagmaCubeEntity magma2 = new MagmaCubeEntity(EntityType.MAGMA_CUBE, punch.world);
							magma.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							magma2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(magma);
							punch.world.addEntity(magma2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.NETHER_BRICKS || block == Blocks.NETHER_WART || block == Blocks.NETHER_WART_BLOCK) {
							props.addTransformed(1);
							ZombiePigmanEntity zombiepig = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
							ZombiePigmanEntity zombiepig2 = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, punch.world);
							zombiepig.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							zombiepig2.setPosition(punch.getXTile() + 0.5f, (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(zombiepig);
							punch.world.addEntity(zombiepig2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.BLACK_SHULKER_BOX || block == Blocks.BLUE_SHULKER_BOX || block == Blocks.BROWN_SHULKER_BOX || block == Blocks.CYAN_SHULKER_BOX || block == Blocks.GRAY_SHULKER_BOX || block == Blocks.GREEN_SHULKER_BOX || block == Blocks.LIGHT_BLUE_SHULKER_BOX || block == Blocks.LIME_SHULKER_BOX || block == Blocks.MAGENTA_SHULKER_BOX || block == Blocks.ORANGE_SHULKER_BOX || block == Blocks.PINK_SHULKER_BOX || block == Blocks.PURPLE_SHULKER_BOX || block == Blocks.RED_SHULKER_BOX || block == Blocks.WHITE_SHULKER_BOX || block == Blocks.YELLOW_SHULKER_BOX) {
							props.addTransformed(1);
							ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, punch.world);
							ShulkerEntity shulker2 = new ShulkerEntity(EntityType.SHULKER, punch.world);
							shulker.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							shulker2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(shulker);
							punch.world.addEntity(shulker2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.SAND || block == Blocks.SANDSTONE) {
							props.addTransformed(1);
							HuskEntity husk = new HuskEntity(EntityType.HUSK, punch.world);
							HuskEntity husk2 = new HuskEntity(EntityType.HUSK, punch.world);
							husk.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							husk2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(husk);
							punch.world.addEntity(husk2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.BEACON) {
							props.addTransformed(2);
							WitherEntity wither = new WitherEntity(EntityType.WITHER, punch.world);
							wither.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(wither);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.SPONGE || block == Blocks.WET_SPONGE) {
							props.addTransformed(2);
							ElderGuardianEntity elderGuardian = new ElderGuardianEntity(EntityType.ELDER_GUARDIAN, punch.world);
							elderGuardian.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(elderGuardian);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.WITHER_SKELETON_SKULL || block == Blocks.WITHER_SKELETON_WALL_SKULL) {
							props.addTransformed(1);
							WitherSkeletonEntity witherSkeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, punch.world);
							WitherSkeletonEntity witherSkeleton2 = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, punch.world);
							witherSkeleton.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							witherSkeleton2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(witherSkeleton);
							punch.world.addEntity(witherSkeleton2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.SEA_PICKLE) {
							props.addTransformed(1);
							SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, punch.world);
							SalmonEntity salmon2 = new SalmonEntity(EntityType.SALMON, punch.world);
							salmon.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							salmon2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(salmon);
							punch.world.addEntity(salmon2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.SEA_LANTERN || block == Blocks.PRISMARINE) {
							props.addTransformed(1);
							GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, punch.world);
							GuardianEntity guardian2 = new GuardianEntity(EntityType.GUARDIAN, punch.world);
							guardian.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							guardian2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(guardian);
							punch.world.addEntity(guardian2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.DIAMOND_BLOCK) {
							props.addTransformed(1);
							DolphinEntity dolphin = new DolphinEntity(EntityType.DOLPHIN, punch.world);
							DolphinEntity dolphin2 = new DolphinEntity(EntityType.DOLPHIN, punch.world);
							dolphin.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							dolphin2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(dolphin);
							punch.world.addEntity(dolphin2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (block == Blocks.TURTLE_EGG) {
							props.addTransformed(1);
							TurtleEntity turtle = new TurtleEntity(EntityType.TURTLE, punch.world);
							TurtleEntity turtle2 = new TurtleEntity(EntityType.TURTLE, punch.world);
							turtle.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							turtle.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
							turtle2.setPosition((punch.getXTile() + 0.5f), (punch.getYTile() + 0.5f), (punch.getZTile() + 0.5f));
							punch.world.addEntity(turtle);
							punch.world.addEntity(turtle2);
							punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
							punch.world.playSound(null, punch.getPosX(), punch.getPosY(), punch.getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
							punch.remove();
						}
						if (punch.ticksExisted > 60) {
							punch.remove();
						}
						props.setCooldown(60);
					}
				}
			});
			if (block != Blocks.AIR) {
				if (!punch.shootingStand.ability) {
					punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
					punch.remove();
				}
			} else {
				punch.remove();
			}
		}
	}

	public static void aerosmith(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch bullet, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(bullet, result, livingEntity))) return;
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(bullet.shootingStand.getMaster()), 1.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, 0, 0);
			bullet.remove();
			if (livingEntity.getPosY() > bullet.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(bullet, result, livingEntity))) return;
			Block block = bullet.getInTile();
			BlockPos blockPos = new BlockPos(bullet.getXTile(), bullet.getYTile(), bullet.getZTile());
			BlockState blockState = bullet.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(bullet.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				if (block == Blocks.TNT) {
					bullet.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					bullet.world.createExplosion(bullet, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 0.8f, Explosion.Mode.DESTROY);
				}
				bullet.world.setBlockState(blockPos, bullet.world.rand.nextInt(2) == 1 ? Blocks.FIRE.getDefaultState() : Blocks.AIR.getDefaultState());
			}
			bullet.remove();
		}
	}

	public static void weatherReport(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 1.0f);
			if (punch.shootingStand.ability)
				livingEntity.addPotionEffect(new EffectInstance(EffectInit.OXYGEN_POISONING.get(), 300, 15));
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, 0, 0);
			punch.remove();
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItem(punch.standMaster.getActiveHand()));
			}
			punch.remove();
		}
	}

	public static void killerQueen(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			((EntityKillerQueen) punch.shootingStand).setBombEntity(livingEntity);
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 0.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void crazyDiamond(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			if (punch.shootingStand.ability)
				livingEntity.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 20, 2));
			else
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 2.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(result.getHitVec().getX(), result.getHitVec().getY(), result.getHitVec().getZ());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				if (blockState.getMaterial() != Material.AIR && blockState.getMaterial() != Material.WATER && punch.shootingStand.ability) {
					if (punch.world.getBlockState(blockPos).getMaterial() != Material.AIR && punch.world.getBlockState(blockPos).getMaterial() != Material.WATER)
						((EntityCrazyDiamond) punch.shootingStand).putRepairBlock(blockPos, blockState);
				}
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				if (!punch.shootingStand.ability)
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void purpleHaze(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			if (!punch.world.isRemote) {
				punch.world.getServer().getWorld(punch.dimension).getEntities()
						.filter(entity -> entity instanceof MobEntity)
						.filter(entity -> entity.getDistance(punch) < 25)
						.forEach(entity -> ((MobEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 150, 1)));
			}
			if (punch.shootingStand.ability)
				livingEntity.addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 600, 5));
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 0.85f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				if (blockState.getMaterial() != Material.AIR && blockState.getMaterial() != Material.WATER && blockState.getMaterial() != Material.LAVA) {
					punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
					if (punch.shootingStand.ability)
						((EntityPurpleHaze) punch.shootingStand).burstCapsule();
				}
			}
		}
		punch.remove();
	}

	public static void whitesnake(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			if (punch.shootingStand.ability)
				if(livingEntity instanceof PlayerEntity)
					Stand.getLazyOptional((PlayerEntity) livingEntity).ifPresent(props -> {
						if(props.getStandID() != 0 && props.getStandID() != Util.StandID.GER && livingEntity.getHealth() <= livingEntity.getMaxHealth() / 2) {
							ItemStack itemStack = new ItemStack(ItemInit.stand_disc.get());
							CompoundNBT nbt = itemStack.getTag() == null ? new CompoundNBT() : itemStack.getTag();
							if (punch.standMaster.inventory.getStackInSlot(punch.standMaster.inventory.getBestHotbarSlot()).isEmpty()) {
								punch.standMaster.inventory.currentItem = punch.standMaster.inventory.getBestHotbarSlot();
								nbt.putInt("StandID", props.getStandID());
								props.removeStand();
								itemStack.setTag(nbt);
								punch.standMaster.inventory.add(punch.standMaster.inventory.getBestHotbarSlot(), itemStack);
							}
						}
					});
			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 0.8f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				if (blockState.getMaterial() != Material.AIR && blockState.getMaterial() != Material.WATER && blockState.getMaterial() != Material.LAVA) {
					punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
				}
			}
		}
		punch.remove();
	}

	public static void cMoon(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		Stand.getLazyOptional(punch.standMaster).ifPresent(props -> {
			if(props.getAct() == 1 && props.getStandID() == Util.StandID.cMoon) {
				whitesnake(result, livingEntity, punch, isEntity);
			}
		});
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;

			livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), livingEntity.getHealth() / 2 + 0.0001f);
			if(punch.shootingStand.ability) {
				livingEntity.setCustomName(new StringTextComponent("Dinnerbone"));
				if(punch.standMaster.getRNG().nextInt(10) == 10)
					livingEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 5));
			}
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				if (blockState.getMaterial() != Material.AIR && blockState.getMaterial() != Material.WATER && blockState.getMaterial() != Material.LAVA) {
					punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
				}
			}
		}
		punch.remove();
	}

	public static void theWorld(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if (punch.shootingStand.orarush)
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 1.5f);
			else
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 3.0f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 4.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void starPlatinum(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if(livingEntity instanceof DolphinEntity) {
				if(!punch.standMaster.isCreative())
					punch.standMaster.attackEntityFrom(DamageSource.causeMobDamage(punch.shootingStand), 1.5f);
				return;
			}
			if (punch.shootingStand.orarush)
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), punch.standMaster.isPotionActive(Effects.DOLPHINS_GRACE) ? 2.0f : 1.4f);
			else
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), punch.standMaster.isPotionActive(Effects.DOLPHINS_GRACE) ? 3.5f : 2.9f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if (hardness != -1.0f && hardness < 4.0f) {
				punch.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
				block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
			}
		}
		punch.remove();
	}

	public static void silverChariot(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch sword, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(sword, result, livingEntity))) return;
			if (sword.shootingStand.orarush)
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(sword.standMaster), 1.2f);
			else
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(sword.standMaster), 2.5f);
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > sword.shootingStand.getPosY() + 3.0) {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			} else {
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			}
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(sword, result, livingEntity))) return;
			Block block = sword.getInTile();
			BlockPos blockPos = new BlockPos(sword.getXTile(), sword.getYTile(), sword.getZTile());
			BlockState blockState = sword.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(sword.world, blockPos);
			if (hardness != -1.0f && hardness < 3.0f) {
				block.harvestBlock(sword.world, sword.standMaster, blockPos, blockState, null, sword.standMaster.getHeldItemMainhand());
				sword.world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
			}
		}
		sword.remove();
	}

	public static void magiciansRed(RayTraceResult result, LivingEntity livingEntity, EntityStandPunch punch, boolean isEntity) {
		if (isEntity) {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.EntityHit(punch, result, livingEntity))) return;
			if (punch.shootingStand.orarush) {
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 0.5f);
				livingEntity.setFire(5);
			} else {
				livingEntity.attackEntityFrom(DamageSource.causeMobDamage(punch.standMaster), 0.75f);
				livingEntity.setFire(2);
			}
			livingEntity.hurtResistantTime = 0;
			livingEntity.setMotion(0, livingEntity.getMotion().getY(), livingEntity.getMotion().getZ());
			if (livingEntity.getPosY() > punch.shootingStand.getPosY() + 3.0)
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.4f, livingEntity.getMotion().getZ());
			else
				livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY() - 0.2f, livingEntity.getMotion().getZ());
			livingEntity.setMotion(livingEntity.getMotion().getX(), livingEntity.getMotion().getY(), 0);
		} else {
			if(MinecraftForge.EVENT_BUS.post(new StandPunchEvent.BlockHit(punch, result, livingEntity))) return;
			Block block = punch.getInTile();
			BlockPos blockPos = new BlockPos(punch.getXTile(), punch.getYTile(), punch.getZTile());
			BlockState blockState = punch.world.getBlockState(blockPos);
			float hardness = blockState.getBlockHardness(punch.world, blockPos);
			if(!((EntityStandPunch.magiciansRed)punch).isExplosive()) {
				if (hardness != -1.0f && hardness < 3.0f) {
					block.harvestBlock(punch.world, punch.standMaster, blockPos, blockState, null, punch.standMaster.getHeldItemMainhand());
					punch.world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
				}
			} else
				punch.world.createExplosion(punch, DamageSource.IN_FIRE, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 3.0f, true, Explosion.Mode.DESTROY);
		}
		punch.remove();
	}
}
