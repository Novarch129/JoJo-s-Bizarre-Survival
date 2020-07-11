package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.FakePlayerEntity;
import io.github.novarch129.jojomod.entity.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.event.custom.StandEvent;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.network.message.server.SSyncStandMasterPacket;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class CSyncStandSummonPacket implements IMessage<CSyncStandSummonPacket> {
    @Override
    public void encode(CSyncStandSummonPacket msg, PacketBuffer buffer) {
    }

    @Override
    public CSyncStandSummonPacket decode(PacketBuffer buffer) {
        return new CSyncStandSummonPacket();
    }

    @Override
    public void handle(CSyncStandSummonPacket msg, Supplier<NetworkEvent.Context> supplier) {
        final NetworkEvent.Context ctx = supplier.get();
        if (ctx.getDirection().getReceptionSide().isServer()) {
            ctx.enqueueWork(() ->
            {
                ServerPlayerEntity sender = ctx.getSender();
                if (sender == null)
                    return;
                FakePlayerEntity fakePlayer = new FakePlayerEntity(sender.world, sender);
                fakePlayer.setPosition(fakePlayer.getParent().getPosX(), fakePlayer.getParent().getPosY(), fakePlayer.getParent().getPosZ());
                World world = sender.world;
                if (!world.isRemote) {
                    Stand.getLazyOptional(sender).ifPresent(props -> {
                        int delay = 0;
                        for (int i = 0; i < 20; i++) {
                            delay++;
                            if (delay == 20) {
                                delay = 0;
                                if (!sender.isCrouching()) {
                                    if (props.getStandOn()) {
                                        if (fakePlayer.isAlive())
                                            fakePlayer.remove();
                                        if (!props.hasAct())
                                            props.setStandOn(false);
                                        else
                                            props.changeAct();
                                        return;
                                    }
                                    if (!sender.isSpectator())
                                        if (props.getStandID() != 0 && props.getStandID() != Util.StandID.THE_EMPEROR) {
                                            AbstractStandEntity stand = Util.getStandByID(props.getStandID(), sender.world);
                                            if (!sender.world.getEntitiesInAABBexcluding(sender, sender.getBoundingBox().expand(1000.0, 1000.0, 1000.0), entity -> entity instanceof AbstractStandEntity).contains(stand)) {
                                                if (props.getStandID() == Util.StandID.AEROSMITH && props.getAbility())
                                                    sender.world.addEntity(fakePlayer);
                                                props.setStandOn(true);
                                                stand.setLocationAndAngles(sender.getPosX() + 0.1, sender.getPosY(), sender.getPosZ(), sender.rotationYaw, sender.rotationPitch);
                                                stand.setMaster(sender);
                                                if (!sender.world.isRemote)
                                                    JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> sender), new SSyncStandMasterPacket(stand.getEntityId(), sender.getEntityId()));
                                                CompoundNBT nbt = new CompoundNBT();
                                                nbt.putInt("MasterID", sender.getEntityId());
                                                stand.writeAdditional(nbt);
                                                sender.world.addEntity(stand);
                                                stand.playSpawnSound();
                                            } else {
                                                MinecraftForge.EVENT_BUS.post(new StandEvent.StandRemovedEvent(sender, stand));
                                                stand.remove();
                                            }
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
                                    return;
                                }
                            }
                        }
                    });
                }
            });
        }
        ctx.setPacketHandled(true);
    }
}
