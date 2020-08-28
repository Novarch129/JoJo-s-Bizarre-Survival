package io.github.novarch129.jojomod.network.message.client;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.*;
import io.github.novarch129.jojomod.entity.stand.attack.AbstractStandAttackEntity;
import io.github.novarch129.jojomod.network.message.IMessage;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

import static io.github.novarch129.jojomod.util.Util.StandID.*;

public class CSyncStandAbilitiesPacket implements IMessage<CSyncStandAbilitiesPacket> {
    private byte action;

    public CSyncStandAbilitiesPacket() {
    }

    public CSyncStandAbilitiesPacket(byte action) {
        this.action = action;
    }

    @Override
    public void encode(CSyncStandAbilitiesPacket message, PacketBuffer buffer) {
        buffer.writeByte(message.action);
    }

    @Override
    public CSyncStandAbilitiesPacket decode(PacketBuffer buffer) {
        return new CSyncStandAbilitiesPacket(buffer.readByte());
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void handle(CSyncStandAbilitiesPacket message, Supplier<Context> ctx) {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ctx.get().enqueueWork(() -> {
                PlayerEntity sender = ctx.get().getSender();
                if (sender == null) return;
                World world = sender.world;
                if (world != null) {
                    if (!world.isRemote) {
                        Stand.getLazyOptional(sender).ifPresent(props -> {
                            switch (props.getStandID()) {
                                case KING_CRIMSON: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof KingCrimsonEntity)
                                            .filter(entity -> ((KingCrimsonEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> ((KingCrimsonEntity) entity).epitaph());
                                    break;
                                }
                                case D4C: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof D4CEntity)
                                            .filter(entity -> ((D4CEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                if (message.action == 1)
                                                    ((D4CEntity) entity).teleport();
                                                else
                                                    ((D4CEntity) entity).grabEntity();
                                            });
                                    break;
                                }
                                case MADE_IN_HEAVEN: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof MadeInHeavenEntity)
                                            .filter(entity -> ((MadeInHeavenEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                switch (message.action) {
                                                    case 1: {
                                                        ((MadeInHeavenEntity) entity).teleport();
                                                        break;
                                                    }
                                                    case 2: {
                                                        ((MadeInHeavenEntity) entity).dodgeAttacks();
                                                        break;
                                                    }
                                                    default:
                                                        break;
                                                }
                                            });
                                    break;
                                }
                                case KILLER_QUEEN: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof KillerQueenEntity)
                                            .filter(entity -> ((KillerQueenEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                switch (message.action) {
                                                    case 1: {
                                                        ((KillerQueenEntity) entity).detonate();
                                                        break;
                                                    }
                                                    case 2: {
                                                        ((KillerQueenEntity) entity).toggleSheerHeartAttack();
                                                        break;
                                                    }
                                                    default: {
                                                        ((KillerQueenEntity) entity).turnItemOrBlockIntoBomb();
                                                        break;
                                                    }
                                                }
                                            });
                                    break;
                                }
                                case GER: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof GoldExperienceRequiemEntity)
                                            .filter(entity -> ((GoldExperienceRequiemEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                if (message.action == 1)
                                                    ((GoldExperienceRequiemEntity) entity).toggleTruth();
                                                else
                                                    ((GoldExperienceRequiemEntity) entity).toggleFlight();
                                            });
                                    break;
                                }
                                case AEROSMITH: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof AerosmithEntity)
                                            .filter(entity -> ((AerosmithEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> ((AerosmithEntity) entity).shootBomb());
                                    break;
                                }
                                case CRAZY_DIAMOND: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof CrazyDiamondEntity)
                                            .filter(entity -> ((CrazyDiamondEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> ((CrazyDiamondEntity) entity).repair());
                                    break;
                                }
                                case WEATHER_REPORT: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof WeatherReportEntity)
                                            .filter(entity -> ((WeatherReportEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> ((WeatherReportEntity) entity).changeWeather());
                                    break;
                                }
                                case THE_WORLD: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof TheWorldEntity)
                                            .filter(entity -> ((TheWorldEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                switch (message.action) {
                                                    case 1: {
                                                        ((TheWorldEntity) entity).teleport();
                                                        break;
                                                    }
                                                    case 2: {
                                                        ((TheWorldEntity) entity).dodgeAttacks();
                                                        break;
                                                    }
                                                    default:
                                                        break;
                                                }
                                            });
                                    break;
                                }
                                case STAR_PLATINUM: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof StarPlatinumEntity)
                                            .filter(entity -> ((StarPlatinumEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                switch (message.action) {
                                                    case 1: {
                                                        ((StarPlatinumEntity) entity).teleport();
                                                        break;
                                                    }
                                                    case 2: {
                                                        ((StarPlatinumEntity) entity).dodgeAttacks();
                                                        break;
                                                    }
                                                    default:
                                                        break;
                                                }
                                            });
                                    break;
                                }
                                case MAGICIANS_RED: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof MagiciansRedEntity)
                                            .filter(entity -> ((MagiciansRedEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> ((MagiciansRedEntity) entity).crossfireHurricane());
                                }
                                case THE_HAND: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof TheHandEntity)
                                            .filter(entity -> ((TheHandEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                if (message.action == 1) {
                                                    Entity entity1 = Minecraft.getInstance().getRenderViewEntity();
                                                    float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
                                                    if (entity1 != null) {
                                                        if (Minecraft.getInstance().world != null) {
                                                            Minecraft.getInstance().getProfiler().startSection("pick");
                                                            Minecraft.getInstance().pointedEntity = null;
                                                            Minecraft.getInstance().objectMouseOver = entity1.pick(Minecraft.getInstance().playerController.getBlockReachDistance(), partialTicks, false);
                                                            Vec3d vec3d = entity1.getEyePosition(partialTicks);
                                                            double range = 30;
                                                            Vec3d vec3d1 = entity1.getLook(1);
                                                            Vec3d vec3d2 = vec3d.add(vec3d1.x * range, vec3d1.y * range, vec3d1.z * range);
                                                            AxisAlignedBB axisalignedbb = entity1.getBoundingBox().expand(vec3d1.scale(range)).grow(1, 1, 1);
                                                            EntityRayTraceResult entityRayTraceResult =
                                                                    ProjectileHelper.rayTraceEntities(
                                                                            entity1,
                                                                            vec3d,
                                                                            vec3d2,
                                                                            axisalignedbb,
                                                                            Util.Predicates.STAND_PUNCH_TARGET.and((predicateEntity) -> predicateEntity != entity && !(predicateEntity instanceof AbstractStandAttackEntity)),
                                                                            3000);
                                                            if (entityRayTraceResult != null) {
                                                                Entity entity11 = entityRayTraceResult.getEntity();
                                                                Vec3d vec3d3 = entityRayTraceResult.getHitVec();
                                                                if (entity11 != null)
                                                                    ((TheHandEntity) entity).dragEntityToStand(entity11.getEntityId());
                                                                double d2 = vec3d.squareDistanceTo(vec3d3);
                                                                if (d2 < 30 || Minecraft.getInstance().objectMouseOver == null) {
                                                                    Minecraft.getInstance().objectMouseOver = entityRayTraceResult;
                                                                    if (entity11 instanceof LivingEntity || entity11 instanceof ItemFrameEntity) {
                                                                        Minecraft.getInstance().pointedEntity = entity11;
                                                                    }
                                                                }
                                                            }
                                                            Minecraft.getInstance().getProfiler().endSection();
                                                        }
                                                    }
                                                } else
                                                    ((TheHandEntity) entity).teleportMaster();
                                            });
                                    break;
                                }
                                case STICKY_FINGERS: {
                                    world.getServer().getWorld(sender.dimension).getEntities()
                                            .filter(entity -> entity instanceof StickyFingersEntity)
                                            .filter(entity -> ((StickyFingersEntity) entity).getMaster().equals(sender))
                                            .forEach(entity -> {
                                                if (message.action == 1) {
                                                    if (((StickyFingersEntity) entity).disguiseEntity == null) {
                                                        if (Minecraft.getInstance().objectMouseOver != null && Minecraft.getInstance().objectMouseOver.getType() == RayTraceResult.Type.ENTITY && ((EntityRayTraceResult) Minecraft.getInstance().objectMouseOver).getEntity() != null)
                                                            ((StickyFingersEntity) entity).disguise(((EntityRayTraceResult) Minecraft.getInstance().objectMouseOver).getEntity().getEntityId());
                                                        if (((StickyFingersEntity) entity).disguiseEntity != null) {
                                                            Minecraft.getInstance().setRenderViewEntity(((StickyFingersEntity) entity).disguiseEntity);
                                                            Minecraft.getInstance().gameSettings.thirdPersonView = 1;
                                                        }
                                                    } else {
                                                        Minecraft.getInstance().setRenderViewEntity(Minecraft.getInstance().world.getPlayerByUuid(sender.getUniqueID()));
                                                        Minecraft.getInstance().gameSettings.thirdPersonView = 0;
                                                        ((StickyFingersEntity) entity).disguise(-100);
                                                    }
                                                } else
                                                    ((StickyFingersEntity) entity).zipThroughWall();
                                            });
                                    break;
                                }
                                case TUSK_ACT_3: {
                                    if (props.getAct() == 0)
                                        world.getServer().getWorld(sender.dimension).getEntities()
                                                .filter(entity -> entity instanceof TuskAct3Entity)
                                                .filter(entity -> ((TuskAct3Entity) entity).getMaster().equals(sender))
                                                .forEach(entity -> ((TuskAct3Entity) entity).teleport());
                                    break;
                                }
                                case TUSK_ACT_4: {
                                    if (props.getAct() == 1)
                                        world.getServer().getWorld(sender.dimension).getEntities()
                                                .filter(entity -> entity instanceof TuskAct3Entity)
                                                .filter(entity -> ((TuskAct3Entity) entity).getMaster().equals(sender))
                                                .forEach(entity -> ((TuskAct3Entity) entity).teleport());
                                    break;
                                }
                                case ECHOES_ACT_2: {
                                    if (props.getAct() == 0)
                                        world.getServer().getWorld(sender.dimension).getEntities()
                                                .filter(entity -> entity instanceof EchoesAct2Entity)
                                                .filter(entity -> ((EchoesAct2Entity) entity).getMaster().equals(sender))
                                                .forEach(entity -> {
                                                    switch (message.action) {
                                                        case 1: {
                                                            ((EchoesAct2Entity) entity).addSoundEffect();
                                                            break;
                                                        }
                                                        case 2: {
                                                            ((EchoesAct2Entity) entity).removeAllSoundEffects();
                                                            break;
                                                        }
                                                        default:
                                                            break;
                                                    }
                                                });
                                    break;
                                }
                                default:
                                    break;
                            }
                        });
                    }
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }
}
