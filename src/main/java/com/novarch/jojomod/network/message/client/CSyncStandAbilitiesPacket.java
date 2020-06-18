package com.novarch.jojomod.network.message.client;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

import static com.novarch.jojomod.util.JojoLibs.StandID.GER;
import static com.novarch.jojomod.util.JojoLibs.StandID.killerQueen;

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
							switch(props.getStandID()) {
								case killerQueen: {
									world.getServer().getWorld(player.dimension).getEntities()
											.filter(entity -> entity instanceof EntityKillerQueen)
											.filter(entity -> ((EntityKillerQueen) entity).getMaster().getEntityId() == player.getEntityId())
											.forEach(entity -> {
												if(message.action == 1)
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
												if(message.action == 1)
													((EntityGoldExperienceRequiem) entity).toggleTruth();
												else
													((EntityGoldExperienceRequiem) entity).toggleFlight();
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
