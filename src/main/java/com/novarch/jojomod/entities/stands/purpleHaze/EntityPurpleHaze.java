package com.novarch.jojomod.entities.stands.purpleHaze;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
public class EntityPurpleHaze extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntityPurpleHaze(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
		this.standID = Util.StandID.purpleHaze;
	}

	public EntityPurpleHaze(World world) {
		super(EntityInit.PURPLE_HAZE.get(), world);
		this.spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
		this.standID = Util.StandID.purpleHaze;
	}

	public void burstCapsule() {
		if (!world.isRemote)
			world.getServer().getWorld(dimension).getEntities()
					.filter(entity -> entity instanceof LivingEntity)
					.filter(entity -> !(entity instanceof EntityStandBase))
					.filter(entity -> entity.getDistance(this) < 80)
					.forEach(entity -> ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 200, 2)));
	}

	@Override
	public void playSpawnSound() {
		this.world.playSound(null, new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), this.getSpawnSound(), this.getSoundCategory(), 2.0f, 1.0f);
	}

	@Override
	public void tick() {
		super.tick();
		this.fallDistance = 0.0f;
		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			Stand.getLazyOptional(player).ifPresent(props -> {
				ability = props.getAbility();

				if (props.getCooldown() > 0 && ability)
					props.subtractCooldown(1);
			});

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if (!player.isAlive())
				remove();
			if (player.isSprinting()) {
				if (attackSwing(player))
					this.oratick++;
				if (this.oratick == 1) {
					if (!world.isRemote)
						world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PURPLE_HAZE_RUSH.get(), getSoundCategory(), 4.5f, 1.0f);

					if (!this.world.isRemote)
						this.orarush = true;
				}
			} else if (attackSwing(getMaster())) {
				if (!this.world.isRemote) {
					this.oratick++;
					if (this.oratick == 1) {
						this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (this.rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
						EntityStandPunch.PurpleHaze purpleHaze = new EntityStandPunch.PurpleHaze(this.world, this, player);
						purpleHaze.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						this.world.addEntity(purpleHaze);
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
						EntityStandPunch.PurpleHaze purpleHaze1 = new EntityStandPunch.PurpleHaze(this.world, this, player);
						purpleHaze1.setRandomPositions();
						purpleHaze1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						this.world.addEntity(purpleHaze1);
						EntityStandPunch.PurpleHaze purpleHaze2 = new EntityStandPunch.PurpleHaze(this.world, this, player);
						purpleHaze2.setRandomPositions();
						purpleHaze2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0f, 0.2f);
						this.world.addEntity(purpleHaze2);
					}
				if (this.oratickr >= 120) {
					this.orarush = false;
					this.oratickr = 0;
				}
			}
		}
	}
}
