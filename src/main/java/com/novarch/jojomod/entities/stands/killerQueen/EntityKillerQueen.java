package com.novarch.jojomod.entities.stands.killerQueen;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack.EntitySheerHeartAttack;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityKillerQueen extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	private LivingEntity bombEntity = null;

	private KeyBinding detonate = KeyHandler.keys[2];

	private KeyBinding summonSHA = KeyHandler.keys[10];

	private int shaCount = 0;

	public LivingEntity getBombEntity() {
		return bombEntity;
	}

	public void setBombEntity(LivingEntity bombEntity) {
		this.bombEntity = bombEntity;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public void readAdditional(CompoundNBT compoundNBT) {
		super.readAdditional(compoundNBT);
	}

	@Override
	public void writeAdditional(CompoundNBT compoundNBT) {
		super.writeAdditional(compoundNBT);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return super.createSpawnPacket();
	}

	public EntityKillerQueen(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		this.standID = JojoLibs.StandID.killerQueen;
	}

	public EntityKillerQueen(World world) {
		super(EntityInit.KILLER_QUEEN.get(), world);
		this.spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
		this.standID = JojoLibs.StandID.killerQueen;
	}

	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;
		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			//Killer Queen's ability
			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				props.setAbility(false);
				if (detonate.isPressed() && props.getCooldown() <= 0) {
					if (this.bombEntity != null) {
						if (bombEntity.isAlive()) {
							props.setCooldown(140);
							if (bombEntity instanceof MobEntity) {
								Explosion explosion = new Explosion(bombEntity.world, player, bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
								((MobEntity) bombEntity).spawnExplosionParticle();
								explosion.doExplosionB(true);
								this.world.playSound(null, new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
								bombEntity.remove();
							} else if (bombEntity instanceof PlayerEntity) {
								JojoProvider.getLazyOptional((PlayerEntity) bombEntity).ifPresent(bombProps -> {
									if (bombProps.getStandID() != JojoLibs.StandID.GER) {
										Explosion explosion = new Explosion(bombEntity.world, player, bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
										((PlayerEntity) bombEntity).spawnSweepParticles();
										explosion.doExplosionB(true);
										bombEntity.attackEntityFrom(DamageSource.FIREWORKS, 4.5f * bombEntity.getArmorCoverPercentage());
									} else {
										Explosion explosion = new Explosion(player.world, player, player.getPosX(), player.getPosY(), player.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
										player.spawnSweepParticles();
										explosion.doExplosionB(true);
										player.setHealth(0);
									}
								});
							}
							if (!player.isCreative() && !player.isSpectator())
								player.getFoodStats().addStats(-2, 0);
						}
					}
				}

				EntitySheerHeartAttack sheerHeartAttack = new EntitySheerHeartAttack(this.world, this);

				if (summonSHA.isPressed()) {
					if (shaCount == 0) {
						sheerHeartAttack.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
						shaCount++;
						this.world.addEntity(sheerHeartAttack);
						props.setCooldown(300);
					} else {
						if (!world.isRemote)
							world.getServer().getWorld(dimension).getEntities()
									.filter(entity -> entity instanceof EntitySheerHeartAttack)
									.filter(entity -> ((EntitySheerHeartAttack) entity).getMasterStand() == this)
									.forEach(entity -> {
										entity.remove();
										shaCount--;
									});
					}
				}
			});

			if (this.standOn) {
				followMaster();
				setRotationYawHead(player.rotationYaw);
				setRotation(player.rotationYaw, player.rotationPitch);

				//Orarush food check
				if (!player.isAlive())
					remove();
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
							EntityStandPunch.killerQueen killerQueen = new EntityStandPunch.killerQueen(this.world, this, player);
							killerQueen.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(killerQueen);
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
							EntityStandPunch.killerQueen killerQueen1 = new EntityStandPunch.killerQueen(this.world, this, player);
							killerQueen1.setRandomPositions();
							killerQueen1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(killerQueen1);
							EntityStandPunch.killerQueen killerQueen2 = new EntityStandPunch.killerQueen(this.world, this, player);
							killerQueen2.setRandomPositions();
							killerQueen2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
							this.world.addEntity(killerQueen2);
						}
					if (this.oratickr >= 80) {
						this.orarush = false;
						this.oratickr = 0;
					}
				}
			}
		}
	}


	public boolean isEntityInsideOpaqueBlock() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return super.canBeCollidedWith();
	}
}
