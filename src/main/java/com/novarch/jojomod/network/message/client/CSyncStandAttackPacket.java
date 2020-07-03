package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.AbstractStandEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncStandAttackPacket {
	public static void encode(CSyncStandAttackPacket msg, PacketBuffer buffer) {
	}

	public static CSyncStandAttackPacket decode(PacketBuffer buffer) {
		return new CSyncStandAttackPacket();
	}

	public static void handle(CSyncStandAttackPacket message, Supplier<Context> ctx) {
		ServerPlayerEntity player = (ctx.get().getSender());
		if (player != null) {
			World world = player.world;
			if (!world.isRemote) {
				Stand.getLazyOptional(player).ifPresent(props -> {
					if (props.getStandOn()) {
						List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D));
						if (!entityList.isEmpty())
							for (Entity entity : entityList) {
								if (entity instanceof AbstractStandEntity) {
									AbstractStandEntity stand = (AbstractStandEntity) entity;
									if (stand.getMaster() == player) {
										stand.setAttack(true);
										return;
									}
								}
							}
					}
				});
			}
		}
	}

}
