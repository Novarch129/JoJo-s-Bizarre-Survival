package com.novarch.jojomod.entities.stands.madeInHeaven;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.events.EventD4CTeleportProcessor;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

@MethodsReturnNonnullByDefault
public class EntityMadeInHeaven extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public int heaventickr = 3600;

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

	public EntityMadeInHeaven(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_MADE_IN_HEAVEN.get();
		this.standID = JojoLibs.StandID.madeInHeaven;
	}

	public EntityMadeInHeaven(World world) {
		super(EntityInit.MADE_IN_HEAVEN.get(), world);
		this.spawnSound = SoundInit.SPAWN_MADE_IN_HEAVEN.get();
		this.standID = JojoLibs.StandID.madeInHeaven;
	}

	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			JojoProvider.getLazyOptional(player).ifPresent(props -> props.setTimeLeft(heaventickr - 1200));
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 19));
			player.setHealth(20.0f);
			player.getFoodStats().addStats(20, 20.0f);

			if(player.isCrouching() && JojoBizarreSurvivalConfig.COMMON.madeInHeavenAbilityAccelerating.get())
				heaventickr-=200;

			//Made In Heaven's Ability
			if (heaventickr > 0)
				heaventickr--;

			if (heaventickr == 1200)
				player.sendMessage(new StringTextComponent("\"Heaven\" has begun!"));

			if (heaventickr < 1200) {
				world.setDayTime(this.world.getDayTime() + 50);
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 39));
				LightningBoltEntity lightning = new LightningBoltEntity(this.world, this.getPosX() + rand.nextInt(50), this.getPosY(), this.getPosZ() + rand.nextInt(50), false);
				lightning.setSilent(true);
				if (!world.isRemote)
					((ServerWorld) this.world).addLightningBolt(lightning);
				world.addEntity(lightning);
				world.getGameRules().write().putInt(GameRules.RANDOM_TICK_SPEED.getName(), this.world.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED) + 5);
			}

			if (heaventickr < 800) {
				world.setDayTime(this.world.getDayTime() + 80);
				world.setRainStrength(10.0f);
				world.getWorldInfo().setRaining(true);
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 99));
			}

			if (heaventickr < 400) {
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 255));
				player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 40, 2));
				world.createExplosion(this, getPosX() + rand.nextInt(100), getPosY() - fallDistance, this.getPosZ() + rand.nextInt(100), 4.0f, Explosion.Mode.DESTROY);
				world.setDayTime(this.world.getDayTime() + 500);
			}

			if (heaventickr <= 0) {
				world.getPlayers().forEach(entity -> JojoProvider.getLazyOptional(entity).ifPresent(prps -> {
					if (prps.getStandID() != JojoLibs.StandID.GER) {
						entity.inventory.clear();
						entity.getInventoryEnderChest().clear();
						EventD4CTeleportProcessor.madeInHeaven.add(entity);
						entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 99));
						entity.fallDistance = 0;
						entity.setSpawnDimenion(DimensionType.byName(JojoBizarreSurvival.MADE_IN_HEAVEN_DIMENSION_TYPE));
						prps.removeStand();
						entity.setInvulnerable(false);
					}
				}));
			}

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
						EntityStandPunch.madeInHeaven madeInHeaven = new EntityStandPunch.madeInHeaven(this.world, this, player);
						madeInHeaven.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
						this.world.addEntity(madeInHeaven);
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
						EntityStandPunch.madeInHeaven madeInHeaven1 = new EntityStandPunch.madeInHeaven(this.world, this, player);
						madeInHeaven1.setRandomPositions();
						madeInHeaven1.shoot(player, player.rotationPitch, player.rotationYaw, 4.0F, 0.15F);
						this.world.addEntity(madeInHeaven1);
						EntityStandPunch.madeInHeaven madeInHeaven2 = new EntityStandPunch.madeInHeaven(this.world, this, player);
						madeInHeaven2.setRandomPositions();
						madeInHeaven2.shoot(player, player.rotationPitch, player.rotationYaw, 4.0F, 0.15F);
						this.world.addEntity(madeInHeaven2);
					}
				if (this.oratickr >= 80) {
					this.orarush = false;
					this.oratickr = 0;
				}
			}
		}
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return super.canBeCollidedWith();
	}
}
