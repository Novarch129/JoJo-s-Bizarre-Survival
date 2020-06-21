package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.entities.stands.crazyDiamond.EntityCrazyDiamond;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.entities.stands.theHand.EntityTheHand;
import com.novarch.jojomod.entities.stands.weatherReport.EntityWeatherReport;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

import static com.novarch.jojomod.util.Util.StandID.*;

@SuppressWarnings( "ConstantConditions")
public class CSyncStandAbilitiesPacket {
	private int action;

	public CSyncStandAbilitiesPacket(int action) {
		this.action = action;
	}

	public static void encode(CSyncStandAbilitiesPacket msg, PacketBuffer buffer) {
		buffer.writeInt(msg.action);
	}

	public static CSyncStandAbilitiesPacket decode(PacketBuffer buffer) {
		return new CSyncStandAbilitiesPacket(buffer.readInt());
	}

	public static void handle(CSyncStandAbilitiesPacket message, Supplier<Context> ctx) {
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
			ctx.get().enqueueWork(() -> {
				PlayerEntity player = ctx.get().getSender();
				assert player != null;
				World world = player.world;
				if (world != null)
					if (!world.isRemote) {
						Stand.getLazyOptional(player).ifPresent(props -> {
							switch (props.getStandID()) {
								case killerQueen: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityKillerQueen)
											.filter(entity -> ((EntityKillerQueen) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> {
												if (message.action == 1)
													((EntityKillerQueen) entity).detonate();
												else
													((EntityKillerQueen) entity).toggleSheerHeartAttack();
											});
									break;
								}
								case GER: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityGoldExperienceRequiem)
											.filter(entity -> ((EntityGoldExperienceRequiem) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> {
												if (message.action == 1)
													((EntityGoldExperienceRequiem) entity).toggleTruth();
												else
													((EntityGoldExperienceRequiem) entity).toggleFlight();
											});
									break;
								}
								case aerosmith: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityAerosmith)
											.filter(entity -> ((EntityAerosmith) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> ((EntityAerosmith) entity).shootBomb());
									break;
								}
								case crazyDiamond: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityCrazyDiamond)
											.filter(entity -> ((EntityCrazyDiamond) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> ((EntityCrazyDiamond) entity).repair());
									break;
								}
								case weatherReport: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityWeatherReport)
											.filter(entity -> ((EntityWeatherReport) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> ((EntityWeatherReport) entity).changeWeather());
									break;
								}
								case theHand: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityTheHand)
											.filter(entity -> ((EntityTheHand) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> {
												Entity entity1 = Minecraft.getInstance().getRenderViewEntity();
												float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
												if (entity1 != null) {
													if (Minecraft.getInstance().world != null) {
														Minecraft.getInstance().getProfiler().startSection("pick");
														Minecraft.getInstance().pointedEntity = null;
														Minecraft.getInstance().objectMouseOver = entity1.pick(Minecraft.getInstance().playerController.getBlockReachDistance(), partialTicks, false);
														Vec3d vec3d = entity1.getEyePosition(partialTicks);
														boolean flag = false;
														double d0 = 30.0D;
														Vec3d vec3d1 = entity1.getLook(1.0f);
														Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
														AxisAlignedBB axisalignedbb = entity1.getBoundingBox().expand(vec3d1.scale(d0)).grow(1.0D, 1.0D, 1.0D);
														EntityRayTraceResult entity1raytraceresult =
																ProjectileHelper.rayTraceEntities(
																		entity1,
																		vec3d,
																		vec3d2,
																		axisalignedbb,
																		(predicateEntity) -> !predicateEntity.isSpectator() && predicateEntity.canBeCollidedWith() && predicateEntity != entity && !(predicateEntity instanceof EntityStandPunch),
																		3000);
														if (entity1raytraceresult != null) {
															Entity entity11 = entity1raytraceresult.getEntity();
															Vec3d vec3d3 = entity1raytraceresult.getHitVec();
															if (entity11 != null)
																((EntityTheHand) entity).teleportEntity(entity11.getEntityId());
															double d2 = vec3d.squareDistanceTo(vec3d3);
															if (flag && d2 > 9.0D) {
																Minecraft.getInstance().objectMouseOver = BlockRayTraceResult.createMiss(vec3d3, Direction.getFacingFromVector(vec3d1.x, vec3d1.y, vec3d1.z), new BlockPos(vec3d3));
															} else if (d2 < 30 || Minecraft.getInstance().objectMouseOver == null) {
																Minecraft.getInstance().objectMouseOver = entity1raytraceresult;
																if (entity11 instanceof LivingEntity || entity11 instanceof ItemFrameEntity) {
																	Minecraft.getInstance().pointedEntity = entity11;
																}
															}
														}
														Minecraft.getInstance().getProfiler().endSection();
													}
												}
											});
									break;
								}
								default:
									break;
							}
						});
					}
			});
		}
		ctx.get().setPacketHandled(true);
	}
}
