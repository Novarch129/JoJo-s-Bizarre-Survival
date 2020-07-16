package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.FakePlayerEntity;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CStandSummonPacket implements IMessage<CStandSummonPacket> {
    @Override
    public void encode(CStandSummonPacket msg, PacketBuffer buffer) {
    }

    @Override
    public CStandSummonPacket decode(PacketBuffer buffer) {
        return new CStandSummonPacket();
    }

    @Override
    public void handle(CStandSummonPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity sender = ctx.get().getSender();
                if (sender == null || sender.isSpectator()) return;
                World world = sender.world;
                FakePlayerEntity fakePlayer = new FakePlayerEntity(world, sender);
                fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
                if (!world.isRemote) {
                    Stand.getLazyOptional(sender).ifPresent(props -> {
                        if (props.hasAct())
                            props.changeAct();
                        else
                            props.setStandOn(!props.getStandOn());
                        if (props.getStandOn()) {
                            if (props.getStandID() != 0 && props.getStandID() != Util.StandID.THE_EMPEROR) {
                                AbstractStandEntity stand = Util.getStandByID(props.getStandID(), sender.world);
                                if (Collections.frequency(Objects.requireNonNull(world.getServer()).getWorld(sender.dimension).getEntities().collect(Collectors.toList()), stand) > 0)
                                    return;
                                if (props.getStandID() == Util.StandID.AEROSMITH && props.getAbility())
                                    sender.world.addEntity(fakePlayer);
                                stand.setLocationAndAngles(sender.getPosX() + 0.1, sender.getPosY(), sender.getPosZ(), sender.rotationYaw, sender.rotationPitch);
                                stand.setMaster(sender);
                                stand.setMasterUUID(sender.getUniqueID());
                                if (!sender.world.isRemote)
                                    JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> sender), new SSyncStandMasterPacket(stand.getEntityId(), sender.getEntityId()));
                                sender.world.addEntity(stand);
                                stand.playSpawnSound();
                            } else if (props.getStandID() == Util.StandID.THE_EMPEROR) {
                                ItemStack itemStack = new ItemStack(ItemInit.THE_EMPEROR.get());
                                if (!sender.inventory.hasItemStack(itemStack)) {
                                    if (sender.inventory.getStackInSlot(sender.inventory.getBestHotbarSlot()).isEmpty()) {
                                        sender.inventory.currentItem = sender.inventory.getBestHotbarSlot();
                                        sender.inventory.add(sender.inventory.getBestHotbarSlot(), itemStack);
                                        sender.world.playSound(null, new BlockPos(sender.getPosX(), sender.getPosY(), sender.getPosZ()), SoundInit.SPAWN_THE_EMPEROR.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                                        props.setStandOn(true);
                                    } else
                                        sender.sendMessage(new StringTextComponent("Your hotbar is full!"));
                                } else {
                                    itemStack.shrink(1);
                                    props.setStandOn(false);
                                }
                            }
                        } else { //Kinda looks like an else if without the braces
                            if (fakePlayer.isAlive())
                                fakePlayer.remove();
                        }
                    });
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
