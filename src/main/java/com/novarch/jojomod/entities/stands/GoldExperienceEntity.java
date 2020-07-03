package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
public class GoldExperienceEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	private boolean transforming = false;

	private int transformTick = 0;

	public GoldExperienceEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_GOLD_EXPERIENCE.get();
		this.standID = Util.StandID.GOLD_EXPERIENCE;
	}

	public GoldExperienceEntity(World world) {
		super(EntityInit.GOLD_EXPERIENCE.get(), world);
		this.spawnSound = SoundInit.SPAWN_GOLD_EXPERIENCE.get();
		this.standID = Util.StandID.GOLD_EXPERIENCE;
	}

	public boolean isTransforming() {
		return transforming;
	}

	public void setTransforming(boolean transforming) {
		this.transforming = transforming;
	}

	public int getTransformTick() {
		return transformTick;
	}

	public void setTransformTick(int transformTick) {
		this.transformTick = transformTick;
	}

	@Override
	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> {
				this.ability = props.getAbility();

				//Cooldown handler
				if (props.getTransformed() > 0) {
					props.subtractCooldown(1);
				}
				if (props.getCooldown() <= 0) {
					props.setTransformed(0);
					props.setCooldown(80);
				}
			});

			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 2));
			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

			if (!player.isAlive())
				remove();
			if (player.isSprinting()) {
				if (attackSwing(player))
					this.oratick++;
				if (this.oratick == 1) {
					if (!this.world.isRemote)
						this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.MUDAGIORNO.get(), getSoundCategory(), 1.0F, 1.0F);

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
						AbstractStandPunchEntity.GoldExperience goldExperience = new AbstractStandPunchEntity.GoldExperience(this.world, this, player);
						goldExperience.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
						this.world.addEntity(goldExperience);
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
						AbstractStandPunchEntity.GoldExperience goldExperience1 = new AbstractStandPunchEntity.GoldExperience(this.world, this, player);
						goldExperience1.setRandomPositions();
						goldExperience1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
						this.world.addEntity(goldExperience1);
						AbstractStandPunchEntity.GoldExperience goldExperience2 = new AbstractStandPunchEntity.GoldExperience(this.world, this, player);
						goldExperience2.setRandomPositions();
						goldExperience2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
						this.world.addEntity(goldExperience2);
					}
				if (this.oratickr >= 110) {
					this.orarush = false;
					this.oratickr = 0;
				}
			}
		}
	}
}