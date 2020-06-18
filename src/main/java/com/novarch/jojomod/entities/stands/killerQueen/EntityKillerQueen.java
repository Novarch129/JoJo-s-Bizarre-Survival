package com.novarch.jojomod.entities.stands.killerQueen;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack.EntitySheerHeartAttack;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
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
public class EntityKillerQueen extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	private LivingEntity bombEntity = null;

	EntitySheerHeartAttack sheerHeartAttack = new EntitySheerHeartAttack(world, this);

	private int shaCount = 0;

	public EntityKillerQueen(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		standID = JojoLibs.StandID.killerQueen;
	}

	public EntityKillerQueen(World world) {
		super(EntityInit.KILLER_QUEEN.get(), world);
		spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		standID = JojoLibs.StandID.killerQueen;
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
									if (bombProps.getStandID() != JojoLibs.StandID.GER) {
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
				if (shaCount == 0) {
					sheerHeartAttack.setPosition(getPosX(), getPosY(), getPosZ());
					shaCount++;
					world.addEntity(sheerHeartAttack);
					props.setCooldown(300);
				} else {
					if (!world.isRemote)
						world.getServer().getWorld(dimension).getEntities()
								.filter(entity -> entity instanceof EntitySheerHeartAttack)
								.filter(entity -> ((EntitySheerHeartAttack) entity).getMasterStand() == this)
								.forEach(entity -> {
									entity.remove();
									shaCount = 0;
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

			//Killer Queen's ability
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
							EntityStandPunch.killerQueen killerQueen = new EntityStandPunch.killerQueen(world, this, player);
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
							EntityStandPunch.killerQueen killerQueen1 = new EntityStandPunch.killerQueen(world, this, player);
							killerQueen1.setRandomPositions();
							killerQueen1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
							world.addEntity(killerQueen1);
							EntityStandPunch.killerQueen killerQueen2 = new EntityStandPunch.killerQueen(world, this, player);
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
