package com.novarch.jojomod.entities.stands.whitesnake;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityWhitesnake extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

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

	public EntityWhitesnake(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_WHITESNAKE.get();
		this.standID = JojoLibs.StandID.whitesnake;
	}

	public EntityWhitesnake(World world) {
		super(EntityInit.WHITESNAKE.get(), world);
		this.spawnSound = SoundInit.SPAWN_WHITESNAKE.get();
		this.standID = JojoLibs.StandID.whitesnake;
	}

	public void tick() {
		super.tick();
		this.fallDistance = 0.0F;

		if (getMaster() != null) {
			PlayerEntity player = getMaster();

			JojoProvider.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

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
						EntityStandPunch.whitesnake whitesnake = new EntityStandPunch.whitesnake(this.world, this, player);
						whitesnake.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.2f);
						this.world.addEntity(whitesnake);
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
						EntityStandPunch.whitesnake whitesnake1 = new EntityStandPunch.whitesnake(this.world, this, player);
						whitesnake1.setRandomPositions();
						whitesnake1.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.25F);
						this.world.addEntity(whitesnake1);
						EntityStandPunch.whitesnake whitesnake2 = new EntityStandPunch.whitesnake(this.world, this, player);
						whitesnake2.setRandomPositions();
						whitesnake2.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.25F);
						this.world.addEntity(whitesnake2);
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

	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(entityIn instanceof EntityStandBase || entityIn instanceof EntityStandPunch)
			super.applyEntityCollision(entityIn);
	}
}
