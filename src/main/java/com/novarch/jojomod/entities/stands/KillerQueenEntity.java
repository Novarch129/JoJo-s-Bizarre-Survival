package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KillerQueenEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	private LivingEntity bombEntity = null;

	SheerHeartAttackEntity sheerHeartAttack = new SheerHeartAttackEntity(world, this);

	private int shaCount = 0;

	public KillerQueenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		standID = Util.StandID.KILLER_QUEEN;
	}

	public KillerQueenEntity(World world) {
		super(EntityInit.KILLER_QUEEN.get(), world);
		spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		standID = Util.StandID.KILLER_QUEEN;
	}

	public LivingEntity getBombEntity() {
		return bombEntity;
	}

	public void setBombEntity(LivingEntity bombEntity) {
		this.bombEntity = bombEntity;
	}

	public void detonate() {
		if (getMaster() != null)
			Stand.getLazyOptional(getMaster()).ifPresent(props -> {
				if (props.getCooldown() <= 0) {
					if (bombEntity != null) {
						if (bombEntity.isAlive()) {
							props.setCooldown(140);
							if (bombEntity instanceof MobEntity) {
								Explosion explosion = new Explosion(bombEntity.world, getMaster(), bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
								((MobEntity) bombEntity).spawnExplosionParticle();
								explosion.doExplosionB(true);
								world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
								bombEntity.remove();
							} else if (bombEntity instanceof PlayerEntity) {
								Stand.getLazyOptional((PlayerEntity) bombEntity).ifPresent(bombProps -> {
									if (bombProps.getStandID() != Util.StandID.GER) {
										Explosion explosion = new Explosion(bombEntity.world, getMaster(), bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
										((PlayerEntity) bombEntity).spawnSweepParticles();
										explosion.doExplosionB(true);
										bombEntity.attackEntityFrom(DamageSource.FIREWORKS, 4.5f * bombEntity.getArmorCoverPercentage());
									} else {
										Explosion explosion = new Explosion(getMaster().world, getMaster(), getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ(), 4.0f, true, Explosion.Mode.NONE);
										getMaster().spawnSweepParticles();
										explosion.doExplosionB(true);
										getMaster().setHealth(0);
									}
								});
							}
							if (!getMaster().isCreative() && !getMaster().isSpectator())
								getMaster().getFoodStats().addStats(-2, 0);
						}
					}
				}
			});
	}
	
	public void toggleSheerHeartAttack() {
		if (getMaster() != null)
			Stand.getLazyOptional(getMaster()).ifPresent(props -> {
				if (shaCount <= 0) {
					sheerHeartAttack.setPosition(getPosX(), getPosY(), getPosZ());
					world.addEntity(sheerHeartAttack);
					shaCount++;
					props.setCooldown(300);
				} else {
					if (!world.isRemote)
						world.getServer().getWorld(dimension).getEntities()
								.filter(entity -> entity instanceof SheerHeartAttackEntity)
								.filter(entity -> ((SheerHeartAttackEntity) entity).getMaster().getEntityId() == getMaster().getEntityId())
								.forEach(entity -> {
									entity.remove();
									shaCount--;
								});
				}
			});
	}

	@Override
	public void tick() {
		super.tick();
		fallDistance = 0.0F;
		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> props.setAbility(false));

			if (standOn) {
				followMaster();
				setRotationYawHead(player.rotationYaw);
				setRotation(player.rotationYaw, player.rotationPitch);

				if (player.isSprinting()) {
					if (attackSwing(player))
						oratick++;
					if (oratick == 1) {
						if (!world.isRemote)
							orarush = true;
					}
				} else if (attackSwing(getMaster())) {
					if (!world.isRemote) {
						oratick++;
						if (oratick == 1) {
							world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
							AbstractStandPunchEntity.KillerQueen killerQueen = new AbstractStandPunchEntity.KillerQueen(world, this, player);
							killerQueen.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
							world.addEntity(killerQueen);
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
							AbstractStandPunchEntity.KillerQueen killerQueen1 = new AbstractStandPunchEntity.KillerQueen(world, this, player);
							killerQueen1.setRandomPositions();
							killerQueen1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
							world.addEntity(killerQueen1);
							AbstractStandPunchEntity.KillerQueen killerQueen2 = new AbstractStandPunchEntity.KillerQueen(world, this, player);
							killerQueen2.setRandomPositions();
							killerQueen2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
							world.addEntity(killerQueen2);
						}
					if (oratickr >= 80) {
						orarush = false;
						oratickr = 0;
					}
				}
			}
		}
	}
}
