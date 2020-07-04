package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.KingCrimsonPunchEntity;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * You shouldn't be surprised if you're confused by this code, even I can barely read it now.
 */
@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KingCrimsonEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public KingCrimsonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_KING_CRIMSON.get();
		standID = Util.StandID.KING_CRIMSON;
	}

	public KingCrimsonEntity(World world) {
		super(EntityInit.KING_CRIMSON.get(), world);
		spawnSound = SoundInit.SPAWN_KING_CRIMSON.get();
		standID = Util.StandID.KING_CRIMSON;
	}

	/**
	 * Gets all entities in the {@link net.minecraft.world.server.ServerWorld} using {@link net.minecraft.world.server.ServerWorld}#getAllEntities,
	 * then applies the {@link com.novarch.jojomod.effects.CrimsonEffect} to them to make them glow.
	 * Also applies the {@link com.novarch.jojomod.effects.CrimsonEffectUser} to it's user, impairing his vision.
	 */
	@Override
	public void tick() {
		super.tick();

		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = !(props.getCooldown() > 0);

				if (!props.getAbility()) {
					if (!player.isCreative() && !player.isSpectator())
						player.setGameType(GameType.SURVIVAL);
					player.setInvulnerable(false);
				}

				if (ability && props.getAbility() && props.getStandOn()) {
					if (props.getTimeLeft() == 0)
						player.sendMessage(new StringTextComponent("Time Skip : ON"));
					if (props.getTimeLeft() > 800) {
						getMaster().setInvulnerable(true);
						player.addPotionEffect(new EffectInstance(EffectInit.CRIMSON_USER.get(), 10000, 255));
						if (!player.isCreative() && !player.isSpectator())
							player.setGameType(GameType.ADVENTURE);
						props.subtractTimeLeft(1);

						if (!world.isRemote)
							world.getServer().getWorld(dimension).getEntities()
									.filter(entity -> entity instanceof LivingEntity)
									.filter(entity -> !(entity instanceof GoldExperienceEntity))
									.filter(entity -> entity != this)
									.filter(Entity::isAlive)
									.forEach(entity -> {
										if (entity instanceof MobEntity) {
											if (((MobEntity) entity).getAttackTarget() == getMaster() || ((MobEntity) entity).getRevengeTarget() == getMaster()) {
												((MobEntity) entity).setAttackTarget(null);
												((MobEntity) entity).setRevengeTarget(null);
											}
											((MobEntity) entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
										}

										if (entity instanceof PlayerEntity)
											Stand.getLazyOptional((PlayerEntity) entity).ifPresent(prs -> {
												if (entity != player && prs.getStandID() != Util.StandID.GER) {
													if (prs.getStandID() == Util.StandID.KING_CRIMSON && prs.getStandOn() && prs.getAbility() && prs.getTimeLeft() > 800)
														return;
													((PlayerEntity) entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
												}
											});
									});
					} else {
						if (!player.isCreative() && !player.isSpectator())
							player.setGameType(GameType.SURVIVAL);
						player.setInvulnerable(false);
						ability = false;
						player.removePotionEffect(EffectInit.CRIMSON_USER.get());
						props.setCooldown(200);
					}
				}

				if (!ability) {
					if (props.getCooldown() == 200)
						player.sendMessage(new StringTextComponent("Time Skip : OFF"));
					if (props.getCooldown() > 0 && props.getAbility())
						props.subtractCooldown(1);
					if (props.getCooldown() <= 0) {
						props.setTimeLeft(1000);
						ability = true;
					}
				}

				if (!props.getAbility()) {
					if (player.isPotionActive(EffectInit.CRIMSON_USER.get()))
						player.removePotionEffect(EffectInit.CRIMSON_USER.get());
					if (props.getTimeLeft() < 1000)
						props.addTimeLeft(1);
				}

				if (!player.isAlive())
					remove();
				if (!ability || !props.getAbility()) {
					if (player.isSprinting()) {
						if (attackSwing(player))
							oratick++;
						if (oratick == 1) {
							if (!player.isCreative())
								player.getFoodStats().addStats(0, 0.0F);
							if (!world.isRemote)
								orarush = true;
						}
					} else if (attackSwing(getMaster())) {
						if (!world.isRemote) {
							oratick++;
							if (oratick == 1) {
								world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
								KingCrimsonPunchEntity kingCrimson = new KingCrimsonPunchEntity(world, this, player);
								kingCrimson.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								world.addEntity(kingCrimson);
							}
						}
					}
					if (player.swingProgressInt == 0)
						oratick = 0;
					if (orarush) {
						player.setSprinting(false);
						oratickr++;
						if (oratickr >= 10)
							if (!world.isRemote) {
								player.setSprinting(false);
								KingCrimsonPunchEntity kingCrimson1 = new KingCrimsonPunchEntity(world, this, player);
								kingCrimson1.setRandomPositions();
								kingCrimson1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								world.addEntity(kingCrimson1);
								KingCrimsonPunchEntity kingCrimson2 = new KingCrimsonPunchEntity(world, this, player);
								kingCrimson2.setRandomPositions();
								kingCrimson2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								world.addEntity(kingCrimson2);
							}
						if (oratickr >= 80) {
							orarush = false;
							oratickr = 0;
						}
					}
				}
			});
		}
	}
}
