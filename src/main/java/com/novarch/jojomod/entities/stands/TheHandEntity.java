package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.TheHandPunchEntity;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TheHandEntity extends AbstractStandEntity {
	private int oratick = 0;

	private int oratickr = 0;

	public TheHandEntity(EntityType<? extends AbstractStandEntity> type, World world) {
		super(type, world);
		spawnSound = SoundInit.SPAWN_MAGICIANS_RED.get();
		standID = Util.StandID.THE_HAND;
	}

	public TheHandEntity(World world) {
		super(EntityInit.THE_HAND.get(), world);
		spawnSound = SoundInit.SPAWN_MAGICIANS_RED.get();
		standID = Util.StandID.THE_HAND;
	}

	public void teleportEntity(int id) {
		Entity entity = world.getEntityByID(id);
		if(entity == null || getMaster() == null) return;
		float yaw = getMaster().rotationYaw;
		float pitch = getMaster().rotationPitch;
		double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
		double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * 1.0f);
		double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * 1.0f);
		if(!world.isRemote)
			entity.setMotion(-motionX * (entity.getDistance(getMaster()) / 4), -motionY * (entity.getDistance(getMaster()) / 4), -motionZ * (entity.getDistance(getMaster()) / 4));
	}

	@Deprecated  //Safe to call, @Deprecated because it's buggy
	public void teleportMaster() {
		PlayerEntity master = getMaster();
		if(master == null || world.isRemote) return;
		int distance = 20;
		float f1 = MathHelper.cos(-master.rotationYaw * 0.017453292f - (float)Math.PI);
		float f2 = MathHelper.sin(-master.rotationYaw * 0.017453292f - (float)Math.PI);
		float f3 = -MathHelper.cos(-master.rotationPitch * 0.017453292f);
		float f4 = MathHelper.sin(-master.rotationPitch * 0.017453292f);
		if(!master.world.isRemote)
			master.move(MoverType.PLAYER, new Vec3d(distance * f2 * f3, distance * f4, distance * f1 * f3));
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
					oratick++;
				if (oratick == 1) {
					if (!world.isRemote)
						orarush = true;
				}
			} else if (attackSwing(player)) {
				if (!world.isRemote) {
					oratick++;
					if (oratick == 1) {
						world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0F, 0.8F / (rand.nextFloat() * 0.4F + 1.2F) + 0.5F);
						TheHandPunchEntity theHand = new TheHandPunchEntity(world, this, player);
						theHand.shoot(player, player.rotationPitch, player.rotationYaw, 1.0f, 0.4f);
						world.addEntity(theHand);
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
						TheHandPunchEntity theHand1 = new TheHandPunchEntity(world, this, player);
						theHand1.setRandomPositions();
						theHand1.shoot(player, player.rotationPitch, player.rotationYaw, 0.8f, 0.5f);
						world.addEntity(theHand1);
						TheHandPunchEntity theHand2 = new TheHandPunchEntity(world, this, player);
						theHand2.setRandomPositions();
						theHand2.shoot(player, player.rotationPitch, player.rotationYaw, 0.8f, 0.5f);
						world.addEntity(theHand2);
					}
				if (oratickr >= 80) {
					orarush = false;
					oratickr = 0;
				}
			}
		}
	}
}
