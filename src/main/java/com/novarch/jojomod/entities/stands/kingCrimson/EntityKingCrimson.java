package com.novarch.jojomod.entities.stands.kingCrimson;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.goldExperience.EntityGoldExperience;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
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
public class EntityKingCrimson extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntityKingCrimson(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_KING_CRIMSON.get();
		this.standID = JojoLibs.StandID.kingCrimson;
	}

	public EntityKingCrimson(World world) {
		super(EntityInit.KING_CRIMSON.get(), world);
		this.spawnSound = SoundInit.SPAWN_KING_CRIMSON.get();
		this.standID = JojoLibs.StandID.kingCrimson;
	}

	@Override
	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;
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

				//King Crimson's Ability
				if (this.ability && props.getAbility() && props.getStandOn()) {
					if (props.getTimeLeft() == 0)
						player.sendMessage(new StringTextComponent("Time Skip : ON"));
					if (props.getTimeLeft() > 800) {
						this.getMaster().setInvulnerable(true);
						player.addPotionEffect(new EffectInstance(EffectInit.CRIMSON_USER.get(), 10000, 255));
						if (!player.isCreative() && !player.isSpectator())
							player.setGameType(GameType.ADVENTURE);
						props.subtractTimeLeft(1);

						world.getServer().getWorld(this.dimension).getEntities()
								.filter(entity -> entity instanceof LivingEntity)
								.filter(entity -> !(entity instanceof EntityGoldExperience))
								.filter(entity -> entity != this)
								.filter(Entity::isAlive)
								.forEach(entity -> {
									if (entity instanceof MobEntity) {
										if (((MobEntity) entity).getAttackTarget() == this.getMaster() || ((MobEntity) entity).getRevengeTarget() == this.getMaster()) {
											((MobEntity) entity).setAttackTarget(null);
											((MobEntity) entity).setRevengeTarget(null);
										}
										((MobEntity) entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
									}

									if (entity instanceof PlayerEntity)
										Stand.getLazyOptional((PlayerEntity) entity).ifPresent(prs -> {
											if (entity != player && prs.getStandID() != JojoLibs.StandID.GER) {
												if (prs.getStandID() == JojoLibs.StandID.kingCrimson && prs.getStandOn() && prs.getAbility() && prs.getTimeLeft() > 800)
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
				if (!this.ability || !props.getAbility()) {
					if (player.isSprinting()) {
						if (attackSwing(player))
							this.oratick++;
						if (this.oratick == 1) {
							if (!player.isCreative())
								player.getFoodStats().addStats(0, 0.0F);
							if (!this.world.isRemote)
								this.orarush = true;
						}
					} else if (attackSwing(getMaster())) {
						if (!this.world.isRemote) {
							this.oratick++;
							if (this.oratick == 1) {
								this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (this.rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
								EntityStandPunch.kingCrimson kingCrimson = new EntityStandPunch.kingCrimson(this.world, this, player);
								kingCrimson.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								this.world.addEntity(kingCrimson);
							}
						}
					}
					if (player.swingProgressInt == 0)
						this.oratick = 0;
					if (this.orarush) {
						player.setSprinting(false);
						this.oratickr++;
						if (this.oratickr >= 10)
							if (!this.world.isRemote) {
								player.setSprinting(false);
								EntityStandPunch.kingCrimson kingCrimson1 = new EntityStandPunch.kingCrimson(this.world, this, player);
								kingCrimson1.setRandomPositions();
								kingCrimson1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								this.world.addEntity(kingCrimson1);
								EntityStandPunch.kingCrimson kingCrimson2 = new EntityStandPunch.kingCrimson(this.world, this, player);
								kingCrimson2.setRandomPositions();
								kingCrimson2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
								this.world.addEntity(kingCrimson2);
							}
						if (this.oratickr >= 80) {
							this.orarush = false;
							this.oratickr = 0;
						}
					}
				}
			});
		}
	}
}
