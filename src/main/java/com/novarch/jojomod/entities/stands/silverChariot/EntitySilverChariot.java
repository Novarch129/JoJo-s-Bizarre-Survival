package com.novarch.jojomod.entities.stands.silverChariot;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntitySilverChariot extends EntityStandBase {
	private int oratick = 0;

	private int oratickr = 0;

	public EntitySilverChariot(EntityType<? extends EntityStandBase> type, World world) {
		super(type, world);
		this.spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
		this.standID = JojoLibs.StandID.silverChariot;
	}

	public EntitySilverChariot(World world) {
		super(EntityInit.SILVER_CHARIOT.get(), world);
		this.spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
		this.standID = JojoLibs.StandID.silverChariot;
	}

	@Override
	public void tick() {
		super.tick();

		if (getMaster() != null) {
			PlayerEntity player = getMaster();
			Stand.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());

			followMaster();
			setRotationYawHead(player.rotationYaw);
			setRotation(player.rotationYaw, player.rotationPitch);

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
						EntityStandPunch.silverChariot silverChariot = new EntityStandPunch.silverChariot(this.world, this, player);
						silverChariot.shoot(player, player.rotationPitch, player.rotationYaw, 4.0f, 0.001f);
						this.world.addEntity(silverChariot);
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
						EntityStandPunch.silverChariot silverChariot1 = new EntityStandPunch.silverChariot(this.world, this, player);
						silverChariot1.setRandomPositions();
						silverChariot1.shoot(player, player.rotationPitch, player.rotationYaw, 3.0f, 0.05f);
						this.world.addEntity(silverChariot1);
						EntityStandPunch.silverChariot silverChariot2 = new EntityStandPunch.silverChariot(this.world, this, player);
						silverChariot2.setRandomPositions();
						silverChariot2.shoot(player, player.rotationPitch, player.rotationYaw, 3.0f, 0.05f);
						this.world.addEntity(silverChariot2);
					}
				if (this.oratickr >= 80) {
					this.orarush = false;
					this.oratickr = 0;
				}
			}
		}
	}
}
